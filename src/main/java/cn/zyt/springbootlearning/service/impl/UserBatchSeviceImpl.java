package cn.zyt.springbootlearning.service.impl;

import cn.zyt.springbootlearning.domain.User;
import cn.zyt.springbootlearning.service.UserBatchService;
import cn.zyt.springbootlearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserBatchSeviceImpl implements UserBatchService {

    @Autowired
    private UserService userService;

    /**
     * 数据库事务传播机制REQUESTED
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int insertUsers(List<User> userList) {
        int count = 0;
        for (User user : userList) {
            count += userService.insertUser(user);
        }
        return count;
    }
}
