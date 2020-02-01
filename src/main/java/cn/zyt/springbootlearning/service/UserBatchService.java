package cn.zyt.springbootlearning.service;

import cn.zyt.springbootlearning.domain.User;
import java.util.List;

public interface UserBatchService {
    int insertUsers(List<User> userList);
}
