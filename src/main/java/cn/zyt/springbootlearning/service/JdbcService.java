package cn.zyt.springbootlearning.service;

import cn.zyt.springbootlearning.domain.User;

public interface JdbcService {

    int insertUser(User user);
}
