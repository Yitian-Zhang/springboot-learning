package cn.zyt.springbootlearning.service;

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
}
