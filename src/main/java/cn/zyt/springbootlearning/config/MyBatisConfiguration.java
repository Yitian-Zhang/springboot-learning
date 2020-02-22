package cn.zyt.springbootlearning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;

/**
 * @author yitian
 */
@Configuration
public class MyBatisConfiguration {

    @Autowired
    PlatformTransactionManager transactionManager;

    @PostConstruct
    public void viewTransactionManager() {
        System.out.println("-----------------------------------");
        System.out.println(transactionManager.getClass().getName());
        System.out.println("-----------------------------------");
    }
}
