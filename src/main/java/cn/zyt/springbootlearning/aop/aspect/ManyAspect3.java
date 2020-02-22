package cn.zyt.springbootlearning.aop.aspect;

import org.aspectj.lang.annotation.*;

@Aspect
public class ManyAspect3 {

    @Pointcut("execution(* cn.zyt.springbootlearning.service.impl.UserServiceImpl.manyAspects(..))")
    public void manyAspects() {
    }

    @Before("manyAspects()")
    public void before() {
        System.out.println("ManyAspect3: before ...");
    }

    @After("manyAspects()")
    public void after() {
        System.out.println("ManyAspect3: after ...");
    }

    @AfterReturning("manyAspects()")
    public void afterReturning() {
        System.out.println("ManyAspect3: afterReturning ...");
    }

    @AfterThrowing("manyAspects()")
    public void afterThrowing() {
        System.out.println("ManyAspect3: afterThrowing ...");
    }
}
