package cn.zyt.springbootlearning.controller;

import cn.zyt.springbootlearning.domain.User;
import cn.zyt.springbootlearning.service.UserService;
import cn.zyt.springbootlearning.vo.ResultVO;
import cn.zyt.springbootlearning.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/restful")
public class RestController {

    @Autowired
    private UserService userService;

    @GetMapping("/page")
    public String restfulPage() {
        return "restful";
    }


    @PostMapping("/user")
    @ResponseBody
    public ResultVO insertUser(@RequestBody UserVO  userVO) {
        User user = changeToPO(userVO);
        userService.insertUser(user);
        return new ResultVO(true, "创建用户资源成功");
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public UserVO getUser(@PathVariable("id") Long id) {
        UserVO userVO = changeToVO(userService.getUserById(id));
        return userVO;
    }

    @GetMapping("/user/{userName}/{start}/{limit}")
    @ResponseBody
    public List<UserVO> findUsers(
            @PathVariable("userName") String userName,
            @PathVariable("start") int start,
            @PathVariable("limit") int limit) {
        List<User> userList = userService.findUsers(userName, start, limit);
        return changeToVOs(userList);
    }

    @PutMapping("/user/{id}")
    @ResponseBody
    public UserVO updateUser(@PathVariable("id") Long id, @RequestBody UserVO userVO) {
        User user = changeToPO(userVO);
        user.setId(id);
        userService.updateUserById(user);
        return changeToVO(userService.getUserById(id));
    }

    @DeleteMapping("/user/{id}")
    @ResponseBody
    public ResultVO deleteUser(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResultVO(false, "用户为空");
        }
        int result = userService.deleteUser(id);
        return new ResultVO(result > 0, result > 0 ? "删除成功" : "删除失败");
    }

    private User changeToPO(UserVO userVO) {
        return new User(userVO.getId(), userVO.getUserName(), userVO.getSexCode(), userVO.getNote());
    }

    private UserVO changeToVO(User user) {
        return new UserVO(user.getId(), user.getUserName(), user.getSex().getId(), user.getSex().getName(), user.getNote());
    }

    private List<UserVO> changeToVOs(List<User> userList) {
        List<UserVO> userVOList = new ArrayList<>();
        for (User user : userList) {
            UserVO userVO = changeToVO(user);
            userVOList.add(userVO);
        }
        return userVOList;
    }
}
