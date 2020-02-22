package cn.zyt.springbootlearning.aop.aspect;

import org.aspectj.lang.annotation.*;

@Aspect
public class ManyAspect2 {
    @Pointcut("execution(* cn.zyt.springbootlearning.service.impl.UserServiceImpl.manyAspects(..))")
    public void manyAspects() {
    }

    @Before("manyAspects()")
    public void before() {
        System.out.println("ManyAspect2: before ...");
    }

    @After("manyAspects()")
    public void after() {
        System.out.println("ManyAspect2: after ...");
    }

    @AfterReturning("manyAspects()")
    public void afterReturning() {
        System.out.println("ManyAspect2: afterReturning ...");
    }

    @AfterThrowing("manyAspects()")
    public void afterThrowing() {
        System.out.println("ManyAspect2: afterThrowing ...");
    }
}
