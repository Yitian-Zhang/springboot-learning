package cn.zyt.springbootlearning.controller;

import cn.zyt.springbootlearning.dao.UserMapper;
import cn.zyt.springbootlearning.domain.SexEnum;
import cn.zyt.springbootlearning.domain.User;
import cn.zyt.springbootlearning.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 该类为UserMapper中方法的测试控制类，后面可以使用UnitTest替代
 *
 * @author yitian
 */
@Controller
@RequestMapping("/mapper")
public class MapperTestController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/annotation/getUserById")
    @ResponseBody
    public User getUserByIdAnnotationTest(Long id) {
        User user = userMapper.getUserByIdAnnotation(id);
        return user;
    }

    @GetMapping("/annotation/searchUser")
    @ResponseBody
    public User searchUserAnnotationTest(Long id, String userName) {
        return userMapper.searchUserAnnotation(id, userName);
    }

    @GetMapping("/provider/getUser")
    @ResponseBody
    public User getUserUsingProviderTest(Long id) {
        return userMapper.getUserUsingProvider(id);
    }

    @GetMapping("/provider/getUserList")
    @ResponseBody
    public List<User> getUserList() {
        return userMapper.getUserListAnnotation();
    }

    @GetMapping("/annotation/insertUser")
    @ResponseBody
    public ResultVO insertUserAnnotation(String userName, Integer sex, String note) {
        User user = new User(userName, sex, note);
        int result = userMapper.insertUserAnnotation(user);

        System.out.println(result);
        return new ResultVO(result > 0, result > 0 ? "插入成功" : "插入失败");
    }

    @GetMapping("/provider/insertUser")
    @ResponseBody
    public ResultVO insertUserProvider(String userName, Integer sex, String note) {
        User user = new User(userName, sex, note);
        int result = userMapper.insertUserProvider(user);

        System.out.println(result);
        return new ResultVO(result > 0, result > 0 ? "插入成功" : "插入失败");
    }

    @GetMapping("/annotation/updateUser")
    @ResponseBody
    public User updateUserAnnotation(Long id, String userName, Integer sex, String note) {
        User user = userMapper.getUserById(id);
        System.out.println("Before update: " + user.toString());

        user.setUserName(userName);
        user.setSex(SexEnum.getEnumById(sex));
        user.setNote(note);
        userMapper.updateUserAnnotation(user);

        User updatedUser = userMapper.getUserById(user.getId());
        System.out.println("After update: " + updatedUser.toString());
        return updatedUser;
    }

    @GetMapping("/provider/updateUser")
    @ResponseBody
    public User updateUserProvider(Long id, String userName, Integer sex, String note) {
        User user = userMapper.getUserById(id);
        System.out.println("Before update: " + user.toString());

        user.setUserName(userName);
        user.setSex(SexEnum.getEnumById(sex));
        user.setNote(note);
        userMapper.updateUserProvider(user);

        User updatedUser = userMapper.getUserById(user.getId());
        System.out.println("After update: " + updatedUser.toString());
        return updatedUser;
    }

    @GetMapping("/annotation/deleteUser")
    @ResponseBody
    public ResultVO deleteUserAnnotation(Long id) {
        User user = userMapper.getUserById(id);
        System.out.println("Before delete: " + user.toString());

        int result = userMapper.deleteUserAnnotation(id);
        return new ResultVO(result > 0, result > 0 ? "删除成功" : "删除失败");
    }

    @GetMapping("/provider/deleteUser")
    @ResponseBody
    public ResultVO deleteUserProvider(Long id) {
        User user = userMapper.getUserById(id);
        System.out.println("Before delete: " + user.toString());

        int result = userMapper.deleteUserProvider(id);
        return new ResultVO(result > 0, result > 0 ? "删除成功" : "删除失败");
    }
}
