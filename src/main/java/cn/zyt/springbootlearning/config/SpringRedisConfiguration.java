package cn.zyt.springbootlearning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;
import java.time.Duration;

/**
 * SpringBoot中整合Redis时，设置redisTemplate中的序列化器
 *
 * @author yitian
 */
@Configuration
@EnableCaching // 开启Spring Redis Cache，使用注解驱动缓存机制
public class SpringRedisConfiguration {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 利用Bean生命周期使用PostConstruct注解自定义后初始化方法
     */
    @PostConstruct
    public void init() {
        initRedisTemplate();
    }

    /**
     * 设置RedisTemplate的序列化器
     */
    private void initRedisTemplate() {
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        // 将Key和其散列表数据类型的filed都修改为使用StringRedisSerializer进行序列化
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
    }

    /**
     * 自定义RedisCacheManager
     */
//    @Bean(name = "redisCacheManager")
    public RedisCacheManager initRedisCacheManager() {
        // 获取Redis加锁的写入器
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory);
        // 启动Redis缓存的默认设置
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        // 设置JDK序列化器
        config = config.serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer()));

        // 自定义设置：禁用前缀
        config = config.disableKeyPrefix();
        // 设置失效时间
        config = config.entryTtl(Duration.ofMinutes(10));
        // 创建Redis缓存管理器
        RedisCacheManager redisCacheManager = new RedisCacheManager(writer, config);
        return redisCacheManager;
    }
}
