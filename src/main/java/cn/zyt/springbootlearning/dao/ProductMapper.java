package cn.zyt.springbootlearning.dao;

import cn.zyt.springbootlearning.domain.business.ProductPO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {

    ProductPO getProduct(@Param("id") Long productId);

    int insertProduct(ProductPO product);

    int decreaseProduct(@Param("id") Long productId, int quantity);

    @Select("select * from tb_product")
    @ResultMap("productMap")
    List<ProductPO> getProductList();
}
