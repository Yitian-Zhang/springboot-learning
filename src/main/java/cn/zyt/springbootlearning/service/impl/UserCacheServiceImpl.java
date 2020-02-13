package cn.zyt.springbootlearning.service.impl;

import cn.zyt.springbootlearning.dao.UserMapper;
import cn.zyt.springbootlearning.domain.User;
import cn.zyt.springbootlearning.service.UserCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Spring Redis Cache服务类
 *
 * @author yitian
 */
@Service
public class UserCacheServiceImpl implements UserCacheService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 使用Transactional注解开启事务
     * Cacheable注解表示先从缓存中通过定义的键值进行查询，如果查询不到则进行数据库查询并将查询结果保存到缓存中，其中：
     * value属性为spring application.properties配置文件中设置的缓存名称
     * key表示缓存的键值名称，其中id说明该方法需要一个名为id的参数
     */
    @Override
    @Transactional
    @Cacheable(value = "redisCache", key = "'redis_user_'+#id")
    public User getUser(Long id) {
        return userMapper.getUserById(id);
    }

    /**
     * CachePut注解表示将方法的返回结果存放到缓存中
     * value和key属性与上述意义一样，需要注意的是，key中的使用了result.id的方式
     * 这里的result表示该方法的返回值对象，即为user，id为取该对象的id属性值
     *
     * 这里在插入user时，传入的user参数是不存在id属性的，在mapper.xml文件中insertUser使用了如下的属性设置：
     * useGeneratedKeys="true" keyProperty="id"
     * 意味着，user的id属性会进行自增，并在use插入成功后会将指定的id属性进行回填，因此如下方法的返回值为带有id属性的完整user对象
     */
    @Override
    @Transactional
    @CachePut(value = "redisCache", key = "'redis_user_'+#result.id")
    public User insertUser(User user) {
        userMapper.insertUser(user);
        System.out.println("After insert, User is: " + user);
        return user;
    }

    /**
     * 这里在CachePut注解中使用了condition配置项，它是一个Spring的EL，这个表达式要求返回Boolean类型的值，如果为true
     * 则使用缓存操作，否则不使用。
     *
     * 但需要注意的是，这里的缓存注解会失效。原因为：
     * 。。。
     *
     */
    @Override
    @Transactional
    @CachePut(value = "redisCache", condition = "#result != null ", key = "'redis_user_'+#id")
    public User updateUserName(Long id, String userName) {
        User user = userMapper.getUserById(id);
        if (user == null) {
            return null;
        }
        user.setUserName(userName);
        userMapper.updateUserById(user);
        return user;
    }

    /**
     * 命中率低，所以不采用缓存机制
     */
    @Override
    @Transactional
    public List<User> findUsers(String userName) {
        return userMapper.getUsersByName(userName);
    }

    /**
     * CacheEvict注解通过定义的键移除相应的缓存，beforeInvocation属性表示是在方法执行之前还是之后移除缓存，默认为false，即为方法之后
     * 移除缓存
     */
    @Override
    @Transactional
    @CacheEvict(value = "redisCache", key = "'redis_user_'+#id", beforeInvocation = false)
    public int deleteUser(Long id) {
        return userMapper.deleteUser(id);
    }
}
