package cn.zyt.springbootlearning.service.impl;

import cn.zyt.springbootlearning.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceImpl implements AsyncService {

    /**
     * 异步方法实现
     */
    @Async // @Async注解配合@EnableAsync注解使用，指明采用异步线程的方法
    @Override
    public void generateReportAsync() {
        // 打印该线程的名称
        try {
            Thread.sleep(5000);
            System.out.println("Async报表线程名称：[" + Thread.currentThread().getName() + "]");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void generateReportSync() {
        // 打印该线程的名称
        try {
            Thread.sleep(5000);
            System.out.println("Sync报表线程名称：[" + Thread.currentThread().getName() + "]");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
