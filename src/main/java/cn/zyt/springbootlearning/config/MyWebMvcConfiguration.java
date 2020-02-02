package cn.zyt.springbootlearning.config;

import cn.zyt.springbootlearning.component.MyMultiInterceptor1;
import cn.zyt.springbootlearning.component.MyMultiInterceptor2;
import cn.zyt.springbootlearning.component.MyMultiInterceptor3;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfiguration implements WebMvcConfigurer {

    /**
     * 注册自定义拦截器方法
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 1. 注册自定义拦截器MyInterceptor1到Spring MVC，然后返回一个拦截器注册
//        InterceptorRegistration registration = registry.addInterceptor(new MyInterceptor1());
        // 指定拦截器匹配模式，限制拦截器拦截请求
//        registration.addPathPatterns("/interceptor/*");

        // 2. 注册多个拦截器
        InterceptorRegistration registration1 = registry.addInterceptor(new MyMultiInterceptor1());
        registration1.addPathPatterns("/interceptor/*");

        InterceptorRegistration registration2 = registry.addInterceptor(new MyMultiInterceptor2());
        registration2.addPathPatterns("/interceptor/*");

        InterceptorRegistration registration3 = registry.addInterceptor(new MyMultiInterceptor3());
        registration3.addPathPatterns("/interceptor/*");

    }
}
