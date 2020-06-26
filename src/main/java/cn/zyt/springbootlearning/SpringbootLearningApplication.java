package cn.zyt.springbootlearning;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

@SpringBootApplication(scanBasePackages = {"cn.zyt.springbootlearning.*"})
@MapperScan(basePackages = "cn.zyt.springbootlearning.*", annotationClass = Repository.class)
//@PropertySource(value = {"classpath:jdbc.properties"}, ignoreResourceNotFound = true)
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
