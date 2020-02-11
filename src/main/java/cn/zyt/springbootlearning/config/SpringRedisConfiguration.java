package cn.zyt.springbootlearning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;

/**
 * SpringBoot中整合Redis时，设置redisTemplate中的序列化器
 *
 * @author yitian
 */
@Configuration
public class SpringRedisConfiguration {

    @Autowired
    private RedisTemplate redisTemplate;

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
}
