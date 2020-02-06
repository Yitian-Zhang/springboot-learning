package cn.zyt.springbootlearning.dao;

import cn.zyt.springbootlearning.domain.mybatis.Author;
import cn.zyt.springbootlearning.provider.AuthorProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorMapper {

    /**
     * 简单的SQL查询，可以不需要Provider和mapper.xml文件中的<select>元素
     * 但由于Author的属性值和表的列名不对应，所以需要mapper.xml文件中定义ResultMap进行映射
     */
    @Select("SELECT * FROM tb_author WHERE author_id=#{id}")
    @ResultMap("authorMap")
    Author getAuthor(Integer id);

    @SelectProvider(type = AuthorProvider.class, method = "getAuthor")
    @ResultMap("authorMap")
    Author getAuthorProvider(Integer id);
}
