package cn.zyt.springbootlearning.service.impl;

import cn.zyt.springbootlearning.domain.User;
import cn.zyt.springbootlearning.service.UserValidator;

public class UserValidatorImpl implements UserValidator {
    @Override
    public boolean validate(User user) {
        System.out.println("为UserService接口引入新接口：" + UserValidator.class.getSimpleName());
        return user != null;
    }
}
