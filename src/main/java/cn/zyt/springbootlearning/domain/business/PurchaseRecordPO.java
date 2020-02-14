package cn.zyt.springbootlearning.domain.business;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Alias("PurchaseRecord")
public class PurchaseRecordPO implements Serializable {
    private static final long serialVersionUID = -6438217907743195649L;

    private Long id;
    private Long userId;
    private Long productId;
    private Double price;
    private int quantity;
    private Double totalPrice;
    private Timestamp purchaseTime;
    private String note;

    public PurchaseRecordPO() {

    }

    public PurchaseRecordPO(Long userId, Long productId, Double price, int quantity, Double totalPrice,
                            Timestamp purchaseTime, String note) {
        this.userId = userId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.purchaseTime = purchaseTime;
        this.note = note;
    }
}
