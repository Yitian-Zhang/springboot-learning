package cn.zyt.springbootlearning.config;

import cn.zyt.springbootlearning.component.MyMultiInterceptor1;
import cn.zyt.springbootlearning.component.MyMultiInterceptor2;
import cn.zyt.springbootlearning.component.MyMultiInterceptor3;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * Spring MVC配置类，配置自定义拦截器和国际化
 *
 * @author yitian
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    /**
     * 国际化参数拦截器
     */
    private LocaleChangeInterceptor localeChangeInterceptor;

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

        // 注册国际化参数拦截器
        // 这里将通过国家化拦截器的preHandle方法对请求的国际化区域参数进行修改
        registry.addInterceptor(localeChangeInterceptor());

    }

    /**
     * 国际化解析器BEAN，注意这个Bean的名称必须为localeResolver
     */
    @Bean(name = "localeResolver")
    public LocaleResolver initLocaleResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        // 设置默认的国际化区域
        sessionLocaleResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return sessionLocaleResolver;
    }

    /**
     * 给处理器添加国际化参数拦截器
     * 拦截的参数名称为：language
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        if (localeChangeInterceptor != null) {
            return localeChangeInterceptor;
        }
        localeChangeInterceptor = new LocaleChangeInterceptor();
        // 设置拦截的参数名
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 为登出设置相应页面
        registry.addViewController("logoutResult").setViewName("logoutResult");
    }

    /**
     * 解决React跨域请求时的异常
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedOrigins("*");

                // 更新spring boot跨域设置
                registry.addMapping("/**")
                        //设置允许跨域请求的域名
                        .allowedOrigins("*")
                        //是否允许证书 不再默认开启（在跨域情况下使用cookie时开启，需要axios开启该对应的选项）
                        .allowCredentials(true)
                        //设置允许的方法
                        .allowedMethods("*")
                        //跨域允许时间
                        .maxAge(3600);
            }
        };
    }
}
