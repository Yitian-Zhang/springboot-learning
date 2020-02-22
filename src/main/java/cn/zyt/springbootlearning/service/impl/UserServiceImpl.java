package cn.zyt.springbootlearning.service.impl;

import cn.zyt.springbootlearning.dao.UserMapper;
import cn.zyt.springbootlearning.domain.User;
import cn.zyt.springbootlearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
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

    @Override
    public void manyAspects() {
        System.out.println("测试多个Aspect的运行顺序");
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
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 1)
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

    /**
     * select * from t_user where user_name="userName" limit start, limit;
     * @param userName 查询用户名
     * @param start 查询起始位置，默认为0
     * @param limit 查询条数限制
     * @return 查询结果集合
     */
    @Override
    public List<User> findUsers(String userName, Integer start, Integer limit) {
        return userMapper.findUsers(userName, start, limit);
    }

    @Override
    public int updateUserById(User user) {
        return userMapper.updateUserById(user);
    }

    @Override
    public int deleteUser(Long id) {
        return userMapper.deleteUser(id);
    }
}
