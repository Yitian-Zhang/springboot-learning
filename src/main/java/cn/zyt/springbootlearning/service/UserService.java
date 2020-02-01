package cn.zyt.springbootlearning.service;

import cn.zyt.springbootlearning.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    /**
     * 这里的printUser方法即为连接点方法
     */
    void printUser(User user);

    User getUserById(Long id);

    void addUser(User user);

    int insertUser(User user);

    List<User> getUserList();

    List<User> searchUser(Long userId, String userName);
}
