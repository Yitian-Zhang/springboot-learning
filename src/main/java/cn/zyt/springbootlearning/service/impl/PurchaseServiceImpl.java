package cn.zyt.springbootlearning.service.impl;

import cn.zyt.springbootlearning.dao.ProductMapper;
import cn.zyt.springbootlearning.dao.PurchaseRecordMapper;
import cn.zyt.springbootlearning.domain.business.ProductPO;
import cn.zyt.springbootlearning.domain.business.PurchaseRecordPO;
import cn.zyt.springbootlearning.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yitian
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private PurchaseRecordMapper purchaseRecordMapper;

    /**
     * 购买处理逻辑实现
     * 使用MySQL for update行锁（悲观锁）
     */
    @Override
    @Transactional
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
