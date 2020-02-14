package cn.zyt.springbootlearning.service.impl;

import cn.zyt.springbootlearning.dao.ProductMapper;
import cn.zyt.springbootlearning.dao.PurchaseRecordMapper;
import cn.zyt.springbootlearning.domain.business.ProductPO;
import cn.zyt.springbootlearning.domain.business.PurchaseRecordPO;
import cn.zyt.springbootlearning.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private PurchaseRecordMapper purchaseRecordMapper;

    /**
     * 购买处理逻辑实现
     */
    @Override
    @Transactional
    public boolean purchase(Long userId, Long productId, int quantity) {
        ProductPO product = productMapper.getProduct(productId);
        if (product.getStock() < quantity) {
            return false;
        }

        productMapper.decreaseProduct(productId, quantity);
        PurchaseRecordPO purchaseRecord = initPurchaseRecord(userId, product, quantity);
        purchaseRecordMapper.insertPurchaseRecord(purchaseRecord);
        return true;
    }

    private boolean commonPurchase(Long userId, Long productId, int quantity) {
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
