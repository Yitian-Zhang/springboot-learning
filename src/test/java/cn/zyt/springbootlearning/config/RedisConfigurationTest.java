package cn.zyt.springbootlearning.config;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.Assert.*;

public class RedisConfigurationTest {

    private void useRedisCallback(RedisTemplate redisTemplate) {
        redisTemplate.execute((RedisConnection rc) -> {
            rc.set("key1".getBytes(), "value1".getBytes());
            rc.hSet("hash".getBytes(), "field".getBytes(), "hvalue".getBytes());
            return null;
        });
    }

    /**
     * 使用SessionCallback接口，在让RedisTemplate进行回调，通过他们可以在同一条连接下执行多个Redis命令
     */
    private void useSessionCallback(RedisTemplate redisTemplate) {
        redisTemplate.execute((RedisOperations ro) -> {
            ro.opsForValue().set("key1", "value1");
            ro.opsForHash().put("hash", "field", "hvalue");
            return null;
        });
    }

    /**
     * 连接成功的Dedis设置
     * 1. 启动成功
     * 2. 安全模式关闭
     * 3. bind IP注释掉（任何客户端IP都可以连接，或者设置连接客户端的IP）
     */
    @Test
    public void startTest() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfiguration.class);
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);
        redisTemplate.opsForValue().set("key1", "value1");
        redisTemplate.opsForHash().put("hash", "field", "hvalue");
    }

}