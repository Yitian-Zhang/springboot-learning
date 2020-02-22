package cn.zyt.springbootlearning.ioc.service;

import cn.zyt.springbootlearning.ioc.config.UserBean;
import org.springframework.stereotype.Service;

//@Service
public class UserBeanService {
    public void printUser(UserBean userBean) {
        System.out.println(userBean.toString());
    }
}
