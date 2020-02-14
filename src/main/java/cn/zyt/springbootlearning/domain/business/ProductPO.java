package cn.zyt.springbootlearning.domain.business;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@Alias("Product")
public class ProductPO implements Serializable {
    private static final long serialVersionUID = 4006220787381707992L;

    private Long id;
    private String productName;
    private int stock;
    private double price;
    private int version;
    private String note;

    public ProductPO() {

    }

    public ProductPO(String productName, int stock, double price, int version, String note) {
        this.productName = productName;
        this.stock = stock;
        this.price = price;
        this.version = version;
        this.note = note;
    }
}
