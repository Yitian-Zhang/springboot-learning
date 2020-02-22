package cn.zyt.springbootlearning.config;

import cn.zyt.springbootlearning.aop.aspect.MyAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AspectConfiguration {

    @Bean(name = "myAspect")
    public MyAspect initMyAspect() {
        return new MyAspect();
    }
}
