package cn.zyt.springbootlearning.controller;

import cn.zyt.springbootlearning.domain.User;
import cn.zyt.springbootlearning.service.UserCacheService;
import cn.zyt.springbootlearning.vo.CommonResult;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 使用Spring Redis Cache来操作User数据表控制器类
 *
 * @author yitian
 */
@Controller
@RequestMapping("/user/cache")
public class UserCacheController {

    @Autowired
    private UserCacheService userCacheService;

    /**
     * 根据ID获取User
     */
    @RequestMapping("/getUser")
    @ResponseBody
    public CommonResult getUser(Long id) {
        User user = userCacheService.getUser(id);
        return new CommonResult(true, "获取成功", user);
    }

    /**
     * 插入一个新User
     */
    @RequestMapping("/insertUser")
    @ResponseBody
    public CommonResult insertUser(String userName, int sex, String note) {
        User user = new User(userName, sex, note);
        User resultUser = userCacheService.insertUser(user);
        return new CommonResult(true, "新增成功", resultUser);
    }

    /**
     * 根据Id查找用户并更新username
     */
    @RequestMapping("/updateUserName")
    @ResponseBody
    public CommonResult updateUserName(Long id, String userName) {
        User user = userCacheService.updateUserName(id, userName);
        boolean flag = user != null;
        String msg = flag ? "更新成功" : "更新失败";
        return new CommonResult(flag, msg, user);
    }

    /**
     * 根据Username查找UserList
     */
    @RequestMapping("/findUsers")
    @ResponseBody
    public CommonResult findUsers(String userName) {
        List<User> users = userCacheService.findUsers(userName);
        return new CommonResult(true, "查找成功", users);
    }

    /**
     * 删除用户
     */
    @RequestMapping("/deleteUser")
    @ResponseBody
    public CommonResult deleteUser(Long id) {
        int result = userCacheService.deleteUser(id);
        boolean flag = result == 1;
        String msg = flag ? "删除成功" : "删除失败";
        return new CommonResult(false, msg);

    }
}
