package cn.zyt.springbootlearning;

import cn.zyt.springbootlearning.aspect.MyAspect;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@SpringBootApplication(scanBasePackages = {"cn.zyt.springbootlearning.*"})
@MapperScan(basePackages = "cn.zyt.springbootlearning.*", annotationClass = Repository.class)
public class SpringbootLearningApplication {

    private static Logger logger = LoggerFactory.getLogger(SpringbootLearningApplication.class);

    public static void main(String[] args) {
        try {
            SpringApplication.run(SpringbootLearningApplication.class, args);
            System.out.println("-----------------------------");
            System.out.println("Application start done!");
            System.out.println("-----------------------------");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
