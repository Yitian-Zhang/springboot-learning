package cn.zyt.springbootlearning.controller;

import cn.zyt.springbootlearning.domain.User;
import cn.zyt.springbootlearning.service.UserBatchService;
import cn.zyt.springbootlearning.service.UserService;
import cn.zyt.springbootlearning.service.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserBatchService userBatchService;

    /**
     * AOP测试
     */
    @RequestMapping("/print")
    @ResponseBody
    public User printUser(Long id, String username, int sex, String note) {
        User user = new User(id, username, sex, note);
        userService.printUser(user);
        return user;
    }

    /**
     * AOP测试
     */
    @RequestMapping("/checkandprint")
    @ResponseBody
    public User checkAndPrintUser(Long id, String username, int sex, String note) {
        User user = new User(id, username, sex, note);
        UserValidator userValidator = (UserValidator) userService;
        if (userValidator.validate(user)) {
            userService.printUser(user);
        }
        return user;
    }

    /**
     * AOP测试
     */
    @RequestMapping("/manyAspects")
    public void manyAspects() {
        userService.manyAspects();
    }

    @RequestMapping("/getUserById")
    @ResponseBody
    public User getUserById(Long id) {
        User user = userService.getUserById(id);
        return user;
    }

    /**
     * 用户注册页面
     */
    @RequestMapping("/register")
    public String register() {
        return "/register";
    }

    /**
     * 注册用户
     */
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    @ResponseBody
    public User createUser(@RequestBody User user) {
        System.out.println(user);
        userService.addUser(user);
        return user;
    }

    /**
     * 测试Transactional
     */
    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Long id) {
        return userService.getUserById(id);
    }

    /**
     * 测试Transactional
     */
    @RequestMapping("/insertUser")
    @ResponseBody
    public Map<String, Object> insertUser(String userName, int sex, String note) {
        User user = new User(userName, sex, note);
        int update = userService.insertUser(user);
        Map<String, Object> result = new HashMap<>();
        result.put("success", update == 1);
        result.put("user", user);
        return result;
    }

    /**
     * 测试Transactional数据库事务隔离机制和传播方式
     */
    @RequestMapping("/insertUsers")
    @ResponseBody
    public Map<String, Object> insertBatchUser(String userName1, int sex1, String note1,
                                               String userName2, int sex2, String note2) {
        User user1 = new User(userName1, sex1, note1);
        User user2 = new User(userName2, sex2, note2);
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        int insertCount = userBatchService.insertUsers(userList);
        Map<String, Object> result = new HashMap<>();
        result.put("success", insertCount > 0);
        result.put("user", userList);
        return result;
    }

    /**
     * UserList页面
     */
    @RequestMapping("/userList")
    @ResponseBody
    public ModelAndView getUserList() {
        List<User> userList = userService.getUserList();

        ModelAndView view = new ModelAndView();
        view.setViewName("userList");
        view.addObject("userList", userList);
        return view;
    }

    /**
     * UserList页面中的搜索功能
     */
    @RequestMapping("/searchUser")
    @ResponseBody
    public List<User> searchUser(
            @RequestParam(value = "userName", required = false) String userName,
            @RequestParam(value = "userId", required = false) String userId) {
        List<User> userList = userService.searchUser(Long.valueOf(userId), userName);
        System.out.println(userList);
        return userList;
    }
}
