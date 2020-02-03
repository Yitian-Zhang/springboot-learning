package cn.zyt.springbootlearning.controller;

import cn.zyt.springbootlearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/session")
@SessionAttributes(names = {"user"}, types = Long.class)
public class SessionController {

    @Autowired
    private UserService userService;

    @RequestMapping("/page")
    public String sessionPage() {
        return "session";
    }

    @GetMapping("/test")
    public String sessionTest(@SessionAttribute("id") Long id, Model model) {
        System.out.println("session id: " + id);
        model.addAttribute("id_new", id);
        model.addAttribute("user", userService.getUserById(id));
        return "sessionDetail";
    }

}
