package cn.zyt.springbootlearning.provider;

import org.apache.ibatis.jdbc.SQL;

public class PurchaseRecordProvider {

    private final static String TABLE_NAME = "tb_purchase_record";

    public String searchRecords(Long userId, Long productId) {
        return new SQL() {
            {
                SELECT("*");
                FROM(TABLE_NAME);
                if (userId != null) {
                    WHERE("user_id=#{userId}");
                }
                if (productId != null) {
                    WHERE("product_id=#{productId}");
                }
                ORDER_BY("id");
            }
        }.toString();
    }
}
