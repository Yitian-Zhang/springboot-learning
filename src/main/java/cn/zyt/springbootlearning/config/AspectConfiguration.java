package cn.zyt.springbootlearning.config;

import cn.zyt.springbootlearning.aop.aspect.ManyAspect1;
import cn.zyt.springbootlearning.aop.aspect.ManyAspect2;
import cn.zyt.springbootlearning.aop.aspect.ManyAspect3;
import cn.zyt.springbootlearning.aop.aspect.MyAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AspectConfiguration {

    @Bean(name = "myAspect")
    public MyAspect initMyAspect() {
        return new MyAspect();
    }

    @Bean(name = "ManyAspect1")
    public ManyAspect1 initManyAspect1() {
        return new ManyAspect1();
    }

    @Bean(name = "ManyAspect2")
    public ManyAspect2 initManyAspect2() {
        return new ManyAspect2();
    }

    @Bean(name = "ManyAspect3")
    public ManyAspect3 initManyAspect3() {
        return new ManyAspect3();
    }
}
