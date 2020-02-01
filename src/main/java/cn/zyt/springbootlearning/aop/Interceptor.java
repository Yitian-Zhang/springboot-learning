package cn.zyt.springbootlearning.aop;

import java.lang.reflect.InvocationTargetException;

/**
 * 拦截器接口
 *
 * @author yitian
 */
public interface Interceptor {

    /**
     * 事前方法
     */
    boolean before();

    /**
     * 事后方法
     */
    void after();

    /**
     * 取代原有事件方法
     * @param invocation 回调参数，使用invocation的proceed方法，回调原有事件
     * @return 原有事件返回对象
     * @throws InvocationTargetException 异常
     * @throws IllegalAccessException 异常
     */
    Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException;

    /**
     * 事后返回方法，事件没有发生异常时执行
     */
    void afterRunning();

    /**
     * 事后异常方法，事件发生异常时执行
     */
    void afterThrowing();

    /**
     * 是否使用around方法取代原有方法标识
     */
    boolean useAround();
}
