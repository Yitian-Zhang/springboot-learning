package cn.zyt.springbootlearning.aop;

import java.lang.reflect.InvocationTargetException;

/**
 * @author yitian
 */
public class MyInterceptor implements Interceptor {
    @Override
    public boolean before() {
        System.out.println("before ...");
        return true;
    }

    @Override
    public void after() {
        System.out.println("after ...");
    }

    @Override
    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("around before ...");
        Object obj = invocation.proceed();
        System.out.println("around after ...");
        return obj;
    }

    @Override
    public void afterRunning() {
        System.out.println("afterRunning ...");
    }

    @Override
    public void afterThrowing() {
        System.out.println("afterThrowing ...");
    }

    @Override
    public boolean useAround() {
        return true;
    }
}
