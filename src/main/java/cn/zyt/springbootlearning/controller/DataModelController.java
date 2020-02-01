package cn.zyt.springbootlearning.controller;

import cn.zyt.springbootlearning.domain.User;
import cn.zyt.springbootlearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author yitian
 */
@Controller
@RequestMapping("/data")
public class DataModelController {

    @Autowired
    private UserService userService;

    @GetMapping("/model")
    public String userModel(Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "userDetail";
    }

    @GetMapping("/modelMap")
    @ResponseBody
    public ModelAndView userModelMap(Long id, ModelMap modelMap) {
        User user = userService.getUserById(id);
        ModelAndView view = new ModelAndView();
        view.setViewName("userDetail");
        modelMap.put("user", user);
        return view;
    }

    @GetMapping("/modelandview")
    @ResponseBody
    public ModelAndView userModelAndView(Long id, ModelAndView view) {
        User user = userService.getUserById(id);
        view.addObject("user", user);
        view.setViewName("userDetail");
        return view;
    }
}
