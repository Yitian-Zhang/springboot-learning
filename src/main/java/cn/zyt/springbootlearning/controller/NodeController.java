package cn.zyt.springbootlearning.controller;

import cn.zyt.springbootlearning.dao.NodeMapper;
import cn.zyt.springbootlearning.domain.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配合tb_node表进行使用（暂时未启用该数据库）
 *
 * @author yitian
 */
@RestController
@RequestMapping("/node")
public class NodeController {

    @Autowired
    private NodeMapper nodeMapper;

    @RequestMapping("/getNode")
    public Node getNode(String name) {
        return nodeMapper.getNode(name);
    }
}
