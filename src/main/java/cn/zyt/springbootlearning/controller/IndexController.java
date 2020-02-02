package cn.zyt.springbootlearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yitian
 */
@Controller
@RequestMapping("/web")
public class IndexController {

    /**
     * 项目主页
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
