package cn.zyt.springbootlearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class ApplicationController {

    @GetMapping("/shutdown")
    public String shutdown() {
        return "shutdown";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }
}
