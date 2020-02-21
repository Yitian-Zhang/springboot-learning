package cn.zyt.springbootlearning.ioc;

import cn.zyt.springbootlearning.ioc.config.AppConfig;
import cn.zyt.springbootlearning.ioc.config.ScopeBean;
import cn.zyt.springbootlearning.ioc.config.UserBean;
import cn.zyt.springbootlearning.ioc.injection.BusinessPerson;
import cn.zyt.springbootlearning.ioc.injection.Person;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

public class IoCTest {

    private static Logger logger = LoggerFactory.getLogger(IoCTest.class);

    /**
     * 测试Profile注解
     */
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(DataSource.class);
    }

    @Test
    public void testUserBean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserBean userBean = context.getBean(UserBean.class);
        logger.info(userBean.getUserName());
    }

    @Test
    public void testPerson() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Person person = context.getBean(BusinessPerson.class);
        person.service();
    }

    @Test
    public void beanLifecycleTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.close();
    }

    @Test
    public void beanScopeTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ScopeBean scopeBean = context.getBean(ScopeBean.class);
        ScopeBean scopeBean1 = context.getBean(ScopeBean.class);
        System.out.println(scopeBean == scopeBean1);
    }
}
