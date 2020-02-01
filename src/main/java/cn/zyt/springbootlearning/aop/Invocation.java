package cn.zyt.springbootlearning.aop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yitian
 */
public class Invocation {

    private Object[] params;
    private Method method;
    private Object target;

    public Invocation(Object target, Method method, Object[] params) {
        this.target = target;
        this.method = method;
        this.params = params;
    }

    /**
     * 以反射的形式调用原有方法
     * @return 方法调用结果
     * @throws InvocationTargetException 异常
     * @throws IllegalAccessException 异常
     */
    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, params);
    }
}
