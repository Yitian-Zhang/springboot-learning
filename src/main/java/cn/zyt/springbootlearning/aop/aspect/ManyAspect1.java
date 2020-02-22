package cn.zyt.springbootlearning.aop.aspect;

import org.aspectj.lang.annotation.*;

@Aspect
public class ManyAspect1 {

    @Pointcut("execution(* cn.zyt.springbootlearning.service.impl.UserServiceImpl.manyAspects(..))")
    public void manyAspects() {
    }

    @Before("manyAspects()")
    public void before() {
        System.out.println("ManyAspect1: before ...");
    }

    @After("manyAspects()")
    public void after() {
        System.out.println("ManyAspect1: after ...");
    }

    @AfterReturning("manyAspects()")
    public void afterReturning() {
        System.out.println("ManyAspect1: afterReturning ...");
    }

    @AfterThrowing("manyAspects()")
    public void afterThrowing() {
        System.out.println("ManyAspect1: afterThrowing ...");
    }
}
