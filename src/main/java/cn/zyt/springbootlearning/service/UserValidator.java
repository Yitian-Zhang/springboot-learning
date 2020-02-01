package cn.zyt.springbootlearning.service;

import cn.zyt.springbootlearning.domain.User;

/**
 * 为UserService接口额外引入的新接口
 */
public interface UserValidator {
    /**
     * 检查用户对象是否为空
     */
    boolean validate(User user);
}
