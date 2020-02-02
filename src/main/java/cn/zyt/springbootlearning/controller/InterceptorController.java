package cn.zyt.springbootlearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 拦截器控制器类
 * @author yitian
 */
@Controller
@RequestMapping("/interceptor")
public class InterceptorController {

    /**
     * 自定义拦截器1中运行时的处理过程：
     * 1. 处理器前方法
     * 2. InterceptorController执行处理器逻辑 ...
     * 3. 处理器后方法
     * 4. Welcome视图页面渲染 ...
     * 5. 处理器完成方法
     *
     * 多个拦截器拦截同一个请求路径时，使用责任链模式：
     * [ MyMultiInterceptor1 ] 处理前方法
     * [ MyMultiInterceptor2 ] 处理前方法
     * [ MyMultiInterceptor3 ] 处理前方法
     * InterceptorController执行处理器逻辑 ...
     * [ MyMultiInterceptor3 ] 处理后方法
     * [ MyMultiInterceptor2 ] 处理后方法
     * [ MyMultiInterceptor1 ] 处理后方法
     * Welcome视图页面渲染 ...
     * [ MyMultiInterceptor3 ] 处理器完成方法
     * [ MyMultiInterceptor2 ] 处理器完成方法
     * [ MyMultiInterceptor1 ] 处理器完成方法
     */
    @GetMapping("/start")
    public String start() {
        System.out.println("InterceptorController执行处理器逻辑 ...");
        return "welcome";
    }
}
