package cn.zyt.springbootlearning.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis配置类，不属于Spring集成Redis的过程
 * 该类作用为：理解Redis在Spring中的使用
 *
 * @author yitian
 */
@Configuration
public class RedisConfiguration {

    private RedisConnectionFactory connectionFactory = null;

    @Bean(name = "RedisConnectionFactory")
    public RedisConnectionFactory initRedisConnectionFactory() {
        if (this.connectionFactory != null) {
            return this.connectionFactory;
        }
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(30);          // 最大空闲数
        poolConfig.setMaxIdle(50);          // 最大连接数
        poolConfig.setMaxWaitMillis(2000);  // 最大等待毫秒数

        // 使用JedisConnectionFactory连接redis
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
        connectionFactory.setHostName("172.16.226.128"); // Redis服务所在IP
        connectionFactory.setPort(6379);                 // 连接端口（默认为6379）
//        connectionFactory.setPassword("123456");       // 密码无需设置，若设置会报错
        this.connectionFactory = connectionFactory;
        return connectionFactory;
    }

    @Bean(name = "RedisTemplate")
    public RedisTemplate<Object, Object> initRedisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();

        // 设置Redis字符串序列化器
        RedisSerializer stringRedisSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(RedisSerializer.string());

        // 使用RedisConnectionFactory连接redis
        redisTemplate.setConnectionFactory(initRedisConnectionFactory());
        return redisTemplate;
    }




}
