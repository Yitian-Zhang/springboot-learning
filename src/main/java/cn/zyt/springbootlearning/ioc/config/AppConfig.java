package cn.zyt.springbootlearning.ioc.config;

import lombok.Data;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(value = "cn.zyt.springbootlearning.ioc.*",
        excludeFilters = {@ComponentScan.Filter(classes = {Service.class})})
public class AppConfig {
//    @Bean(name = "UserBean")
//    public UserBean getInit() {
//        return new UserBean(1L, "yitian", 1, "none");
//    }

    /**
     * 注入第三方的Bean
     */
//    @Bean("DbcpDataSource")
    public DataSource getDataSource() {
        Properties properties = new Properties();
        properties.setProperty("driver", "com.mysql.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://localhost:3306/springboot_dev");
        properties.setProperty("username", "root");
        properties.setProperty("password", "123456");

        DataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    @Conditional(DatabaseConditional.class)
    public DataSource getDataSourceWithConditional(
            @Value("${database.drivername}") String driver,
            @Value("${database.url}") String url,
            @Value("${database.username}") String username,
            @Value("${database.password}") String password) {
        Properties properties = new Properties();
        properties.setProperty("driver", driver);
        properties.setProperty("url", url);
        properties.setProperty("username", username);
        properties.setProperty("password", password);

        DataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean("DevDataSource")
    @Profile("dev")
    public DataSource getDevDataSource() {
        Properties properties = new Properties();
        properties.setProperty("driver", "com.mysql.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://localhost:3306/dev");
        properties.setProperty("username", "root");
        properties.setProperty("password", "123456");

        DataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean("TestDataSource")
    @Profile("test")
    public DataSource getTestDataSource() {
        Properties properties = new Properties();
        properties.setProperty("driver", "com.mysql.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://localhost:3306/test");
        properties.setProperty("username", "root");
        properties.setProperty("password", "123456");

        DataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

}
