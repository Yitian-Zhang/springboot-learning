package cn.zyt.springbootlearning.dao;

import cn.zyt.springbootlearning.domain.Node;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeMapper {
    Node getNode(String name);
}
