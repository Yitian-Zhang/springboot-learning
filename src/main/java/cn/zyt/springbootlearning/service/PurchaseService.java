package cn.zyt.springbootlearning.service;

import cn.zyt.springbootlearning.domain.business.PurchaseRecordPO;

import java.util.List;

public interface PurchaseService {

    /**
     * 购买处理业务逻辑
     * @param userId 用户ID
     * @param productId 商品ID
     * @param quantity 购买数量
     * @return 购买是否成功
     */
    boolean purchase(Long userId, Long productId, int quantity);

    boolean purchaseCAS(Long userId, Long productId, int quantity);

    boolean purchaseCASWithTime(Long userId, Long productId, int quantity);

    boolean purchaseCASWithCount(Long userId, Long productId, int quantity);

    boolean purchaseRedis(Long userId, Long productId, int quantity);

    boolean dealRedisPurchase(List<PurchaseRecordPO> records);
}
