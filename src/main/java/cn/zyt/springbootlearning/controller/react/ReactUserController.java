package cn.zyt.springbootlearning.controller.react;

import cn.zyt.springbootlearning.domain.User;
import cn.zyt.springbootlearning.service.UserService;
import cn.zyt.springbootlearning.vo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * React(my-ice-start)项目接口Controller
 * @author yitian
 */
@RestController
@RequestMapping("/react-user")
public class ReactUserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/detail")
    public CommonResult getUserDetail(Long id) {
        if (id == null) {
            return CommonResult.error("用户ID不能为空");
        }
        return new CommonResult(true, 200, userService.getUserById(id));
    }

    @RequestMapping("/create")
    public CommonResult createUser(@RequestBody User user) {
        if (user == null) {
            return CommonResult.error("添加用户为空");
        }
        System.out.println(user);

        int result = userService.insertUser(user);
        boolean success = result == 1;
        String msgInfo = success ? "添加成功" : "添加失败";
        return new CommonResult(success, msgInfo, user);
    }


    @RequestMapping("/list")
    public CommonResult userList() {
        List<User> userList = userService.getUserList();
        return new CommonResult(true, "获取成功", userList);
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult updateUser(@RequestBody User user) {
        if (user == null || user.getId() == null) {
            return CommonResult.error("待更新用户信息为空");
        }
        System.out.println(user);

        int result = userService.updateUserById(user);
        boolean success = result == 1;
        String msg = success ? "更新成功" : "更新失败";
        return new CommonResult(success, msg, userService.getUserById(user.getId()));
    }

    @RequestMapping("/delete")
    public CommonResult deleteUser(Long id) {
        if (id == null) {
            return CommonResult.error("UserId不能为空");
        }
        int result = userService.deleteUser(id);
        boolean success = result == 1;
        String msg = success ? "删除成功" : "删除失败";
        return new CommonResult(success, msg, userService.getUserList());
    }
}
