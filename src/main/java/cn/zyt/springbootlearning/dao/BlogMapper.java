package cn.zyt.springbootlearning.dao;

import cn.zyt.springbootlearning.domain.mybatis.Blog;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogMapper {

    Blog getBlog(Integer id);
}
