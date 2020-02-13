package cn.zyt.springbootlearning.controller;

import cn.zyt.springbootlearning.vo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Redis的其他操作示例控制器类
 * 包括：
 *  事务处理
 *  Redis Pipeline
 *  执行Lua脚本
 *
 * @author yitian
 */
@Controller
@RequestMapping("/redis")
public class RedisMultiController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 这里探究Redis中的事务机制
     * Redis中的事务机制使用的为：watch...multi...exec的命令组合。
     * 其中watch指明需要检测的key，multi表示开始事务，开始事务后客户端的命令并不会马上执行，而是将其放入到一个队列中等待执行，
     * 此时如果对命令中的变量进行取值，结果为是null。exec命令表示事务的执行，在执行之前需要判断watch监测的key值是否发生改变
     * （即使是将相同的值赋给watch的key也是不行的），如果没有发生变化则执行原子性的事务，如果watch的key值发生了变化，则不会
     * 执行此次的Redis事务。
     *
     * @return
     */
    @GetMapping("/multi")
    @ResponseBody
    public CommonResult redisMultiOps() {
        redisTemplate.opsForValue().set("key1", "value1");

        List list = (List) redisTemplate.execute((RedisOperations operations) -> {
            // 设置要监控key1
            operations.watch("key1");
            operations.multi();
            operations.opsForValue().set("key2", "value2");

            // 这里会抛出异常，是为了说明Redis中的事务与MySQL中的不同，对于Redis事务在让命令进入了队列是并不会发现该处的问题，只有在
            // exec方法被执行时才会发现异常，但此时只能抛出错误，命令无法撤回，因此在该异常之后的命令仍可以被执行，也就是key2和key3的
            // 数据仍可以正常的存入到redis中
//            operations.opsForValue().increment("key1", 1);

            // 获取值将为Null，因为redis只是将命令放入队列中
            Object value2 = operations.opsForValue().get("key2");
            System.out.println("命令在队列中，所以value为null，[" + value2 + "]");

            operations.opsForValue().set("key3", "value3");
            Object value3 = operations.opsForValue().get("key3");
            System.out.println("命令在队列中，所以value为null，[" + value3 + "]");

            return operations.exec();
        });

        System.out.println(list);
        return new CommonResult(true, "操作成功，见Console输出");
    }

    /**
     * Redis的pipeline操作，批量执行Redis命令，
     */
    @GetMapping("/pipeline")
    @ResponseBody
    public CommonResult redisPipelineOps() {
        Long start= System.currentTimeMillis();
        List list = redisTemplate.executePipelined((RedisOperations opertions) -> {
            for (int i = 1; i <= 10000; i++) {
                opertions.opsForValue().set("pipeline_" + i, "value_" + i);
                String value = (String) opertions.opsForValue().get("pipeline_" + i);
                if (i == 10000) {
                    System.out.println("命令只是进入队列，所以此时值为空 {" + value + "}");
                }
            }
            return null;
        });

        Long end = System.currentTimeMillis();
        System.out.println("10000次写操作，耗时： " + (end - start) + "毫秒");
        return new CommonResult(true, "操作成功");
    }


    /**
     * Redis中使用Lua脚本
     * Redis使用两种方式运行脚本：
     * 一种是直接发送lua脚本到Redis服务器去执行，另一种是先吧lua发送给Redis，Redis会对Lua脚本进行缓存，然后返回一个SHA1的32为编码，
     * 之后只需要发送SHA1和相关参数给Redis即可执行脚本。为的是避免lua脚本网络传输过程中的延迟，提高redis性能。
     */
    @GetMapping("/lua1")
    @ResponseBody
    public CommonResult redisLuaScript() {
        // 设置需要运行的lua脚本
        DefaultRedisScript<String> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText("return 'hello Redis'");
        // 定义脚本的返回值类型。如果没有这个定义，Spring不会返回结果
        redisScript.setResultType(String.class);

        // 执行定义的lua脚本
        RedisSerializer<String> stringRedisSerializer = redisTemplate.getStringSerializer();
        // 两个序列化器分别是：第一个为key序列化器，第二个是参数序列化器
        String str = (String) redisTemplate.execute(redisScript, stringRedisSerializer, stringRedisSerializer, null);
        return new CommonResult(true, str);
    }

    @GetMapping("/lua2")
    public CommonResult redisLuaScript2(String key1, String key2, String value1, String value2) {
        String lua = "redis.call('set', KEYS[1], ARGV[1]) \n"
                + "redis.call('set', KEYS[2], ARGV[1]) \n"
                + "local str1 = redis.call('get', KEYS[1]) \n"
                + "local str2 = redis.call('set', KEYS[2]) \n"
                + "if str1 == str2 then \n"
                + "return 1 \n"
                + "end \n"
                + "return 0 \n";
        System.out.println(lua);

        // 设置脚本返回值类型为Long
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(lua);
        redisScript.setResultType(Long.class);

        // 定义key参数
        List<String> keyList = new ArrayList<>();
        keyList.add(key1);
        keyList.add(key2);

        // 使用StringSerializer序列化器
        RedisSerializer<String> stringRedisSerializer = redisTemplate.getStringSerializer();
        Long result = (Long) redisTemplate.execute(redisScript, stringRedisSerializer, stringRedisSerializer,
                keyList, value1, value2);
        return new CommonResult(true, "操作成功", result);
    }

}
