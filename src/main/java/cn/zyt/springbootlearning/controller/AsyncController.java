package cn.zyt.springbootlearning.controller;

import cn.zyt.springbootlearning.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 使用Spring中的异步线程池，开启异步方法
 *
 * @author yitian
 */
@Controller
@RequestMapping("/thread")
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    /**
     * 调用异步方法，输出如下：
     *
     * Controller请求线程名称：[http-nio-8080-exec-1]
     * Async报表线程名称：[ThreadPoolTaskExecutor-1]
     */
    @RequestMapping("/async")
    @ResponseBody
    public void generateReportAsync() {
        System.out.println("Controller请求线程名称：[" + Thread.currentThread().getName() + "]");
        asyncService.generateReportAsync();
    }

    /**
     * 调用同步方法，输出如下：
     *
     * Controller请求线程名称：[http-nio-8080-exec-1]
     * Sync报表线程名称：[http-nio-8080-exec-1]
     */
    @RequestMapping("/sync")
    @ResponseBody
    public void generateReportSync() {
        System.out.println("Controller请求线程名称：[" + Thread.currentThread().getName() + "]");
        asyncService.generateReportSync();
    }
}
