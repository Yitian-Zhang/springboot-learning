package cn.zyt.springbootlearning.service.impl;

import cn.zyt.springbootlearning.dao.UserMapper;
import cn.zyt.springbootlearning.domain.User;
import cn.zyt.springbootlearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void printUser(User user) {
        if (user == null) {
            throw new RuntimeException("检查用户参数是否为空!");
        }
        System.out.println(user);
    }

    /**
     * 使用数据库事务
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1)
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    /**
     * 使用数据库事务
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1)
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public List<User> searchUser(Long userId, String userName) {
        return userMapper.searchUser(userId, userName);
    }
}
