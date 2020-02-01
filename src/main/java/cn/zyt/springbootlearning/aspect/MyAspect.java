package cn.zyt.springbootlearning.aspect;

import cn.zyt.springbootlearning.domain.User;
import cn.zyt.springbootlearning.service.UserValidator;
import cn.zyt.springbootlearning.service.impl.UserValidatorImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 自定义的切面类，用于定义流程的织入
 *
 * @author yitian
 */
@Aspect
public class MyAspect {

    /**
     * 为UserService接口引入UserValidator接口
     */
    @DeclareParents(value = "cn.zyt.springbootlearning.service.impl.UserServiceImpl+",
    defaultImpl = UserValidatorImpl.class)
    public UserValidator userValidator;

    @Pointcut("execution(* cn.zyt.springbootlearning.service.impl.UserServiceImpl.printUser(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before() {
        System.out.println("before ...");
    }

    @After("pointCut()")
    public void after() {
        System.out.println("after ...");
    }

    @AfterReturning("pointCut()")
    public void afterRunning() {
        System.out.println("after returning ...");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("after throwing ...");
    }

    /**
     * 环绕通知
     */
    @Around("pointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around before ...");
        // 回调目标对象的原有方法
        joinPoint.proceed();
        System.out.println("around after ...");
    }

    /**
     * 在前置通知中获取参数（带参数的前置通知）
     * @param joinPoint 连接点
     * @param user 参数名
     */
    @Before("pointCut() && args(user)")
    public void beforeParam(JoinPoint joinPoint, User user) {
        Object[] args = joinPoint.getArgs();
        System.out.println("before ... and get args: " + args);
    }
}
