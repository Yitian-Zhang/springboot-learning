package cn.zyt.springbootlearning.dao;

import cn.zyt.springbootlearning.domain.business.PurchaseRecordPO;
import cn.zyt.springbootlearning.provider.PurchaseRecordProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRecordMapper {

    int insertPurchaseRecord(PurchaseRecordPO purchaseRecord);

    @Select("select * from tb_purchase_record")
    @ResultMap("purchaseRecordMap")
    List<PurchaseRecordPO> getPurchaseRecordAll();

    @SelectProvider(type = PurchaseRecordProvider.class, method = "searchRecords")
    @ResultMap("purchaseRecordMap")
    List<PurchaseRecordPO> searchRecords(Long userId, Long productId);
}
