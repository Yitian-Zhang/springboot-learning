package cn.zyt.springbootlearning.component.indicator;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * 自定义健康指标，监测服务器是否可以访问互联网
 * @author yitian
 */
@Component
public class WwwHealthIndicator extends AbstractHealthIndicator {

    private final static String BAIDU_HOST = "www.baidu.com";

    private final static int TIME_OUT = 3000;

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        Boolean status = ping();
        if (status) {
            builder.withDetail("message", "当前服务器可以访问互联网").up();
        } else {
            builder.withDetail("message", "当前服务器无法访问互联网").outOfService();
        }
    }

    private Boolean ping() throws Exception {
        try {
            // 本地运行的话，应该是无法访问互联网
            return InetAddress.getByName(BAIDU_HOST).isReachable(TIME_OUT);
        } catch (Exception e) {
            return false;
        }

    }
}
