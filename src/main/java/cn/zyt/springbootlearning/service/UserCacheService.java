package cn.zyt.springbootlearning.service;

import cn.zyt.springbootlearning.domain.User;

import java.util.List;

/**
 * 开启Spring redis cache时，创建的测试Service类
 * 其实现重用UserMapper接口中的方法
 *
 * @author yitian
 */
public interface UserCacheService {

    User getUser(Long id);

    User insertUser(User user);

    User updateUserName(Long id, String userName);

    List<User> findUsers(String userName);

    int deleteUser(Long id);
}
