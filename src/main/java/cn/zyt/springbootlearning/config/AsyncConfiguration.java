package cn.zyt.springbootlearning.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 开启Spring异步线程池，并定义线程池的配置
 *
 * @author yitian
 */
@Configuration
@EnableAsync // 开启Spring异步线程池可用，配合使用@Async注解驱动Spring使用异步操作
public class AsyncConfiguration implements AsyncConfigurer {

    /**
     * 覆盖默认方法，定义线程池
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);       // 线城市核心线程数
        taskExecutor.setMaxPoolSize(30);        // 线程池最大线程数
        taskExecutor.setQueueCapacity(2000);    // 线程队列最大线程数
        taskExecutor.initialize();              // 初始化线程池
        return taskExecutor;
    }
}
