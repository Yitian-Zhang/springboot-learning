package cn.zyt.springbootlearning.service.impl;

import cn.zyt.springbootlearning.dao.ProductMapper;
import cn.zyt.springbootlearning.dao.PurchaseRecordMapper;
import cn.zyt.springbootlearning.domain.business.ProductPO;
import cn.zyt.springbootlearning.domain.business.PurchaseRecordPO;
import cn.zyt.springbootlearning.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author yitian
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {

    /**
     * Lua脚本，根据如下调用命令：
     * jedis.evalsha(sha1,
     *                     2,
     *                     PRODUCT_SCHEDULE_SET,
     *                     PURCHASE_PRODUCT_LIST_PREFIX,
     *                     userId + "",
     *                     productId + "",
     *                     quantity + "",
     *                     purchaseTime + "");
     *
     * lua中占位符与参数之间的对应关系如下：
     * KEYS[1] -> PRODUCT_SCHEDULE_SET
     * KEYS[2] -> PURCHASE_PRODUCT_LIST_PREFIX
     * ARGV[1] -> userId
     * ARGV[2] -> productId
     * ARGV[3] -> quantity
     * ARGV[4] -> purchaseTime
     *
     * Redis中数据初始化为：
     * hmset product_7 id 7 stock 1000 price 5.00
     */
    private String PURCHASE_LUA_SCRIPT = "redis.call('sadd', KEYS[1], ARGV[2]) \n"  // sadd product_schedule_set 1
            + "local productPurchaseList = KEYS[2]..ARGV[2] \n"                     // local productPurchaseList = purchase_product_list_1
            + "local userId = ARGV[1] \n"                                           // local userId = 1
            + "local product = 'product_'..ARGV[2] \n"                              // local product = product_1
            + "local quantity = tonumber(ARGV[3]) \n"                               // lcoal quantity = 1
            + "local stock = tonumber(redis.call('hget', product, 'stock')) \n"     // local stock = hget product_1 'stock'
            + "local price = tonumber(redis.call('hget', product, 'price')) \n"     // local price = hget product_1 'price'
            + "local purchase_time = ARGV[4] \n"                                    // local purchase_time purchaseTime
            + "if stock < quantity then return 0 end \n"                            // if stock < quantity then return 0 end
            + "stock = stock - quantity \n"                                         // stock = stock - quantity
            + "redis.call('hset', product, 'stock', tostring(stock)) \n"            // hset product_1 'stock' stock
            + "local total_price = price * quantity \n"                             // local total_price = price * quantity
            + "local purchaseRecord = userId..','..quantity..','..total_price..','..price..','..purchase_time \n"   // local purchaseRecord = userId,quantity,total_price,price,purchase_time
            + "redis.call('rpush', productPurchaseList, purchaseRecord) \n"         // rpush purchase_product_list_1 purchaseRecord
            + "return 1 \n";

    /**
     * 购买记录集合前缀
     */
    private static final String PURCHASE_PRODUCT_LIST_PREFIX = "purchase_list_";

    /**
     * 抢购商品集合
     */
    private static final String PRODUCT_SCHEDULE_SET = "product_schedule_set";

    /**
     * 32位SHA1编码，第一次执行时lua脚本会被缓存到Redis
     */
    private String sha1 = null;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private PurchaseRecordMapper purchaseRecordMapper;

    /**
     * 购买处理逻辑实现
     * 使用MySQL for update行锁（悲观锁）
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean purchase(Long userId, Long productId, int quantity) {
        ProductPO product = productMapper.getProduct(productId);
        // 在这里添加 || product.getStock() <= 0的条件是无法避免超发问题的
        if (product.getStock() < quantity) {
            return false;
        }

        productMapper.decreaseProduct(productId, quantity);
        PurchaseRecordPO purchaseRecord = initPurchaseRecord(userId, product, quantity);
        purchaseRecordMapper.insertPurchaseRecord(purchaseRecord);
        return true;
    }

    /**
     * 使用乐观锁CAS的方式解决高并发问题
     * 悲观锁的方式是在一个线程对共享变量进行操作时，使用事务的方式对该变量进行锁住，其他线程需要等待该线程操作完成后才能对共享变量进行
     * 处理，这种方式虽然可以解决并发问题，但其对性能的影响也比较大。
     *
     * CAS的方式并不是通过加锁的方式解决多线程中的问题。而是通过如下的过程进行：
     * 1. 首先，在业务逻辑处理之前读取共享资源stock的值（这里成为旧值）并进行保存
     * 2. 然后进行业务逻辑处理过程
     * 3. 等需要对共享数据进行修改时，将之前保存的旧值与当前数据库中的值进行比较
     *      如果两个值一致，则进行修改。
     *      如果不一致，则什么都不做
     *
     * 以上即是CAS的一般思想。但该过程在多线程的情况下会出现ABA的问题。为了避免该问题，引入了版本号的机制（version）。
     * 也就是对共享变量所在的数据加入version字段，version在进行共享变量的操作时会进行累加（无论是否回退，都是会累加）
     * 那么在在对共享变量进行修改时，就可以通过version在对旧值与新值的一致性做出判断，从而避免ABA问题中使用错误的数据，
     * 继续相关的操作。
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean purchaseCAS(Long userId, Long productId, int quantity) {
        ProductPO product = productMapper.getProduct(productId);
        if (product.getStock() < quantity) {
            return false;
        }
        // 获取当前的版本号
        int version = product.getVersion();
        // 在减少stock时，加入version的对比，从而对数据的一致性做出判断
        int result = productMapper.decreaseProductCAS(productId, quantity, version);
        if (result == 0) {
            return false;
        }

        PurchaseRecordPO purchaseRecord = initPurchaseRecord(userId, product, quantity);
        purchaseRecordMapper.insertPurchaseRecord(purchaseRecord);
        return true;
    }

    /**
     * 使用时间戳键值重入的乐观锁
     * 在上面使用带有version的CAS方式处理并发问题时，会有大量的失败请求，所以这为CAS引入重入机制，也就是在一次操作失败后，在多次几次，
     * 以增加成功的几率。对于重入的方式，包括两种：
     * 1. 使用时间戳限制重入操作，也就是在一定时间范围内进行重试，例如下面的代码使用100ms的时间范围来对失败的操作进行重试。（本方法）
     * 2. 使用次数限制的方式。如果系统比价忙绿，在100ms时间内可能重试不了几次，因此可使用限定重试次数的方式来进行。（下一个方法）
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean purchaseCASWithTime(Long userId, Long productId, int quantity) {
        long start = System.currentTimeMillis();
        // 循环尝试直至成功
        while (true) {
            long end = System.currentTimeMillis();
            // 如果循环时间大于100ms，终止循环
            if (end - start > 100) {
                return false;
            }
            // 如下是同样的乐观锁过程
            ProductPO product = productMapper.getProduct(productId);
            if (product.getStock() < quantity) {
                return false;
            }

            int version = product.getVersion();
            int result = productMapper.decreaseProductCAS(productId, quantity, version);
            if (result == 0) {
                // 如果修改stock失败，则在100ms时间间隔内进行循环尝试
                continue;
            }
            PurchaseRecordPO purchaseRecord = initPurchaseRecord(userId, product, quantity);
            purchaseRecordMapper.insertPurchaseRecord(purchaseRecord);
            return true;
        }
    }

    /**
     * 限定次数重入的乐观锁
     * 当重入次数限定为3时，有97个stock没有被消费。当count增加为5时，所有记录可以全部被消费。
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean purchaseCASWithCount(Long userId, Long productId, int quantity) {
        for (int i = 0; i < 5; i++) {
            ProductPO product = productMapper.getProduct(productId);
            if (product.getStock() < quantity) {
                return false;
            }

            int version = product.getVersion();
            int result = productMapper.decreaseProductCAS(productId, quantity, version);
            if (result == 0) {
                continue;
            }
            PurchaseRecordPO purchaseRecord = initPurchaseRecord(userId, product, quantity);
            purchaseRecordMapper.insertPurchaseRecord(purchaseRecord);
            return true;
        }
        return false;
    }

    /**
     * 使用Redis处理高并发
     */
    @Override
    public boolean purchaseRedis(Long userId, Long productId, int quantity) {
        Long purchaseTime = System.currentTimeMillis();
        Jedis jedis = null;

        try {
            // 获取Jedis原始连接
            jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
            // 如果该脚本没有被加载过，则先将脚本加载到Redis服务器中，让其返回sha1编码
            if (sha1 == null) {
                sha1 = jedis.scriptLoad(PURCHASE_LUA_SCRIPT);
                System.out.println(PURCHASE_LUA_SCRIPT);
            }

            // 执行脚本并返回结果
            Object object = jedis.evalsha(sha1,
                    2,
                    PRODUCT_SCHEDULE_SET,
                    PURCHASE_PRODUCT_LIST_PREFIX,
                    userId + "",
                    productId + "",
                    quantity + "",
                    purchaseTime + "");
            Long result = (Long) object;
            return result == 1;
        } finally {
            // 关闭jedis连接
            if (jedis != null && jedis.isConnected()) {
                jedis.close();
            }
        }

    }

    /**
     * 保存购买记录方法
     * 保存记录启用了数据库事务，并将传播行为设置为REQUEST_NEW，即调用它会将当前事务挂起，开启新的事务
     * 这样在回滚时只会回滚这个方法内部的事务，而不会影响全局事务
     *
     * @param records 购买记录
     * @return true
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean dealRedisPurchase(List<PurchaseRecordPO> records) {
        for (PurchaseRecordPO record : records) {
            purchaseRecordMapper.insertPurchaseRecord(record);
            productMapper.decreaseProduct(record.getProductId(), record.getQuantity());
        }
        return true;
    }


    private PurchaseRecordPO initPurchaseRecord(Long userId, ProductPO product, int quantity) {
        PurchaseRecordPO purchaseRecord = new PurchaseRecordPO();
        purchaseRecord.setUserId(userId);
        purchaseRecord.setProductId(product.getId());
        purchaseRecord.setPrice(product.getPrice());
        purchaseRecord.setQuantity(quantity);
        purchaseRecord.setTotalPrice(product.getPrice() * quantity);
        purchaseRecord.setNote("Log time: " + System.currentTimeMillis());
        return purchaseRecord;
    }
}
