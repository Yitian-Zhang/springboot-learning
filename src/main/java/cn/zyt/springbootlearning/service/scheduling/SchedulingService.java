package cn.zyt.springbootlearning.service.scheduling;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Spring定时调度服务类
 *
 * /@scheduled注解的配置项包括：
 *      cron：           使用表达式定义任务执行时间
 *      zone：           通过它可以设定区域时间
 *      fixedDelay：     表示从上一个任务完成到开始下一个任务开始的时间间隔，单位为ms
 *      initialDelay：   表示在Spring IOC容器初始化完成后，首次任务执行的延迟时间，单位为ms
 *      fixedRate：      从上一个任务开始到下一个任务开始的时间间隔，单位为ms
 *
 *      cron表达式的结构：
 *          秒 分 时 天 月 星期 年（可不配置）
 *      可以使用的通配符如下：
 *          * 任意值
 *          ? 不指定至
 *          - 指定时间区间
 *          / 指定时间间隔执行
 *          L 最后的
 *          # 第几个
 *          , 列举多项
 *
 * @author yitian
 */
@Service
public class SchedulingService {

    // 定义计数器
    private int count1 = 1;
    private int count2 = 1;
    private int count3 = 1;
    private int count4 = 1;

    @Async                          // 开启异步方法
    @Scheduled(fixedRate = 1000)    // 每隔1s执行一次
    public void job1() {
        System.out.println("【" + Thread.currentThread().getName() +"】【job1】每秒钟执行一次，执行第【" + count1 + "】次");
        count1++;
    }

    @Async
    @Scheduled(fixedRate = 1000)
    public void job2() {
        System.out.println("【" + Thread.currentThread().getName() +"】【job2】每秒钟执行一次，执行第【" + count2 + "】次");
        count2++;
    }

    @Async
    @Scheduled(initialDelay = 3000, fixedRate = 1000)
    public void job3() {
        System.out.println("【" + Thread.currentThread().getName() +"】【job3】每秒钟执行一次，执行第【" + count3 + "】次");
        count3++;
    }

    /**
     * 11:00-11:59，每分钟执行一次
     */
    @Async
    @Scheduled(cron = "0 * 11 * * ?")
    public void job4() {
        System.out.println("【" + Thread.currentThread().getName() +"】【job4】每分钟执行一次，执行第【" + count4 + "】次");
        count4++;
    }
}
