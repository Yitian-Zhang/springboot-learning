package cn.zyt.springbootlearning.dao;

import cn.zyt.springbootlearning.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    User getUserById(Long id);

    void addUser(User user);

    int insertUser(User user);

    List<User> getUserList();

    List<User> searchUser(Long userId, String userName);
}
