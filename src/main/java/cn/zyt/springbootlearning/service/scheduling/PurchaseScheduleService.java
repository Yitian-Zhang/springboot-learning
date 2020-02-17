package cn.zyt.springbootlearning.service.scheduling;

import cn.zyt.springbootlearning.domain.business.PurchaseRecordPO;
import cn.zyt.springbootlearning.service.PurchaseService;
import org.omg.CORBA.TIMEOUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 将Redis中的购买记录同步至MySQL中
 *
 * @author yitian
 */
//@Service
public class PurchaseScheduleService {

    private static final String PURCHASE_PRODUCT_LIST_PREFIX = "purchase_list_";

    private static final String PRODUCT_SCHEDULE_SET = "product_schedule_set";

    private static final int BATCH_SIZE = 200;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private PurchaseService purchaseService;

    // DateTimeFormatter线程安全，SimpleDateFormat线程不安全
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Redis购买记录数据同步至MySQL定时任务
     * // @Scheduled(cron = "0 0 1 * * ?") // 设置每天凌晨1点执行数据同步事务
     */
    @Scheduled(fixedRate = 1000 * 5 * 60) // 设置每隔5分钟进行数据同步
    public void purchaseTask() {
        System.out.println("Redis数据同步至MySQL任务开始，START AT：" + dateTimeFormatter.format(LocalDateTime.now()));
        // 根据PRODUCT_SCHEDULE_SET key，获取redis中进行抢购的商品id set
        Set<String> productIdList = stringRedisTemplate.opsForSet().members(PRODUCT_SCHEDULE_SET);
        List<PurchaseRecordPO> purchaseRecords = new ArrayList<>();
        for (String productIdStr : productIdList) {
            Long productId = Long.valueOf(productIdStr);
            String purchaseKey = PURCHASE_PRODUCT_LIST_PREFIX + productId;

            // 对指定id的商品相应的购买记录列表进行绑定操作
            BoundListOperations<String, String> operations = stringRedisTemplate.boundListOps(purchaseKey);
            // 计算每次同步的记录数，避免一次读取的数据量过大导致JVM内存溢出
            Long size = operations.size();
            Long times = size % BATCH_SIZE == 0 ? size / BATCH_SIZE : size / BATCH_SIZE + 1;
            // 分批同步购买记录数据
            for (int i = 0; i < times; i++) {
                List<String> recordList = null;
                if (i == 0) {
                    recordList = operations.range(i * BATCH_SIZE, (i + 1) * BATCH_SIZE);
                } else {
                    recordList = operations.range(i * BATCH_SIZE + 1, (i + 1) * BATCH_SIZE);
                }
                // 将redis中key为purchase_list_id的列表中保存的购买记录字符串取出，将其转换为PurchaseRecordPO对象
                for (String recordStr : recordList) {
                    PurchaseRecordPO record = createPurchaseRecord(productId, recordStr);
                    purchaseRecords.add(record);
                }

                // 调用事务执行数据同步，此时事务的传播机制被设置为REQUESTED_NEW，即在分批同步数据的过程中每一次同步的执行都是新建一个事务进行处理
                // 当子事务出现问题时，不会导致全局事务的回滚，仅回滚子事务
                try {
                    purchaseService.dealRedisPurchase(purchaseRecords);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 清空列表，等待下一批次数据的同步
                purchaseRecords.clear();
            }
            // 同步完成后，清除Redis中的相关数据：购买商品id集合中的当前id以及购买记录列表
            stringRedisTemplate.delete(purchaseKey);
            stringRedisTemplate.opsForSet().remove(PRODUCT_SCHEDULE_SET, productIdStr);
        }
        System.out.println("同步结束，END AT: " + dateTimeFormatter.format(LocalDateTime.now()));
    }

    /**
     * Redis中存储的record格式为：
     * userId,quantity,total_price,price,purchase_time
     *
     * PurchaseRecordPO(Long userId, Long productId, Double price, int quantity, Double totalPrice,
     *                             Timestamp purchaseTime, String note)
     */
    private PurchaseRecordPO createPurchaseRecord(Long productId, String recordStr) {
        String[] array = recordStr.split(",");
        Long  userId = Long.parseLong(array[0]);
        Integer quantity = Integer.parseInt(array[1]);
        Double totalPrice = Double.valueOf(array[2]);
        Double price = Double.valueOf(array[3]);
        Long time = Long.valueOf(array[4]);
        Timestamp purchaserTime = new Timestamp(time);
        return new PurchaseRecordPO(userId, productId, price, quantity, totalPrice, purchaserTime,
                "Purchase Log: " + purchaserTime.getTime());
    }
}
