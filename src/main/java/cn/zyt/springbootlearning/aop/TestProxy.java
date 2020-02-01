package cn.zyt.springbootlearning.aop;

import cn.zyt.springbootlearning.service.HelloService;
import cn.zyt.springbootlearning.service.impl.HelloServiceImpl;
import org.junit.Test;

/**
 * @author yitian
 */
public class TestProxy {

    @Test
    public void testProxy() {
        HelloService helloService = new HelloServiceImpl();
        HelloService proxy = (HelloService) ProxyBean.getProxyBean(helloService, new MyInterceptor());
        proxy.sayHello("yitian");
        System.out.println("-------------name is null!--------------");
        proxy.sayHello(null);
    }
}
