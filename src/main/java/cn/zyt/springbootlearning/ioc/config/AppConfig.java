package cn.zyt.springbootlearning.ioc.config;

import lombok.Data;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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

    @Bean("DbcpDataSource")
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
}
