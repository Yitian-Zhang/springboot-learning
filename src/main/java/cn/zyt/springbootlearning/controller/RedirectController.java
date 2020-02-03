package cn.zyt.springbootlearning.controller;

import cn.zyt.springbootlearning.domain.User;
import cn.zyt.springbootlearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 重定向控制器
 *
 * @author yitian
 */
@Controller
@RequestMapping("/redirect")
public class RedirectController {

    @Autowired
    private UserService userService;

    /**
     * 用于重定向的userDetail页面
     */
    @RequestMapping("/showById")
    public String showUserById(Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "userDetail";
    }

    /**
     * 重定向1：插入用户后重定向到userDetail页面
     */
    @RequestMapping("/redirect1ById")
    public String redirect1ById(String userName, int sex, String note) {
        User user = new User(userName, sex, note);
        userService.insertUser(user);
        return "redirect:/redirect/showById?id=" + user.getId();
    }

    /**
     * 重定向2：插入用户后重定向到userDetail页面
     * 与上述方法作用一样，实现不同
     */
    @RequestMapping("/redirect2ById")
    public ModelAndView redirect2ById(String userName, int sex, String note, ModelAndView view) {
        User user = new User(userName, sex, note);
        userService.insertUser(user);
        view.setViewName("redirect:/redirect/showById?id=" + user.getId());
        view.addObject("user", user);
        return view;
    }

    /**
     * 重定向后不再根据id对user进行查找，直接根据参数传递返回
     */
    @RequestMapping("/showUser")
    public String showUser(User user, Model model) {
        model.addAttribute("user", user);
        return "userDetail";
    }

    /**
     * 使用RedirectAttribution存储重定向过程中的参数
     */
    @RequestMapping("/redirectUser1")
    public String redirectUser1(String userName, int sex, String note, RedirectAttributes attributes) {
        User user = new User(userName, sex, note);
        userService.insertUser(user);
        attributes.addFlashAttribute("user", user);
        return "redirect:/redirect/showUser";
    }

    @RequestMapping("/redirectUser2")
    public ModelAndView redirectUser2(String userName, int sex, String note, ModelAndView view, RedirectAttributes attributes) {
        User user = new User(userName, sex, note);
        userService.insertUser(user);

        attributes.addFlashAttribute("user", user);
        view.setViewName("redirect:/redirect/showUser");
        return view;

    }
}
