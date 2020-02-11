package cn.zyt.springbootlearning.controller;

import cn.zyt.springbootlearning.vo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * Redis基础数据结构操作类
 *
 * @author yitian
 */
@Controller
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * Redis字符串String操作示例
     */
    @GetMapping("/string")
    @ResponseBody
    public CommonResult redisStringOps() {
        // 向Redis中写入数据，形式为： <key, value>
        redisTemplate.opsForValue().set("key1", "value1");
        redisTemplate.opsForValue().set("int_key", "1");
        stringRedisTemplate.opsForValue().set("int", "1");
        stringRedisTemplate.opsForValue().increment("int", 5);

        System.out.println(redisTemplate.opsForValue().get("key1")); // output: value1
        System.out.println(redisTemplate.opsForValue().get("int_key")); // output: 1
        System.out.println(stringRedisTemplate.opsForValue().get("int")); // output: 6, 这里如果使用redisTemplate会存在NPE

        // RedisTemplate不支持-1操作，所以需要获取底层的Jedis连接进行操作
        Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        jedis.decr("int");
        System.out.println(stringRedisTemplate.opsForValue().get("int")); // output: 5

        return new CommonResult(true, "见Console输出");
    }

    /**
     * Redis散列表Hash操作示例
     */
    @GetMapping("/hash")
    @ResponseBody
    public CommonResult redisHashOps() {

        // 存入一个Hash类型数据，第一个为存入Hash数据结构的整体key，后面为对应存入的值，也就是如下结构：
        // Map<Key, Map<field, value>>
        Map<String, Object> hash = new HashMap<>();
        hash.put("field1", "value1");
        hash.put("field2", "value2");
        stringRedisTemplate.opsForHash().putAll("hash", hash);
        System.out.println(stringRedisTemplate.opsForHash().get("hash", "field1")); // output: value1

        // 向key为hash的散列表中存入单条数据
        stringRedisTemplate.opsForHash().put("hash", "field3", "value3");
        System.out.println(stringRedisTemplate.opsForHash().get("hash", "field3")); // output: value3

        // 绑定散列表Hash进行操作，可以对一个hash进行多次操作
        BoundHashOperations hashOps = stringRedisTemplate.boundHashOps("hash");
        hashOps.put("field4", "value4");
        hashOps.delete("field4");

        // 输出名为hash散列表中的所有key和value
        System.out.println(hashOps.keys()); // output: [filed, field1, field2, field3]
        System.out.println(hashOps.values()); // output: [hvalue, value1, value2, value3]

        // 创建另一个散列表进行操作
        stringRedisTemplate.opsForHash().put("hash1", "hash1_key1", "hash1_value1");
        System.out.println(stringRedisTemplate.opsForHash().get("hash1", "hash1_key1")); // output: hash1_value1

        return new CommonResult(true, "见Console输出");
    }

    /**
     * Redis列表操作示例
     * List为链表结构，查询性能低，插入性能高
     */
    @GetMapping("/list")
    @ResponseBody
    public CommonResult redisListOps() {
        // 在开始操作前判断并删除已存在的list，否则下面的过程会对同一个list进行累计操作
        deleteExistedList("list1");
        deleteExistedList("list2");

        // 从左插入一个链表，名为list1，顺序为,v8, v6, v4, v2
        stringRedisTemplate.opsForList().leftPushAll("list1", "v2", "v4", "v6", "v8");
        printRedisList("list1"); // List: list1, elements: v8, v6, v4, v2,

        // 从右侧插入一个列表，名为list2，顺序为, v1, v3, v7, v9
        stringRedisTemplate.opsForList().rightPushAll("list2", "v1", "v3", "v7", "v9");
        printRedisList("list2"); // List: list2, elements: v1, v3, v7, v9,

        // pop方法会将list中的元素弹出后删除
        System.out.println(stringRedisTemplate.opsForList().leftPop("list1")); // v8
        System.out.println(stringRedisTemplate.opsForList().rightPop("list1")); // v2
        System.out.println(stringRedisTemplate.opsForList().leftPop("list2")); // v1
        System.out.println(stringRedisTemplate.opsForList().rightPop("list2")); // v9

        // 使用BoundOperations绑定名为list2的列表进行多次操作
        BoundListOperations listOps = stringRedisTemplate.boundListOps("list2");
        // 从list2右侧输出一个元素
        Object result1 = listOps.rightPop();
        System.out.println(result1); // v7

        // 输出list2中index=1的元素，（List中从0开始）
        Object result2 = listOps.index(1);
        System.out.println(result2); // null，因为此时list2经过上述的pop操作只剩下一个元素

        // 从左侧向list2中加入一个元素为v0
        listOps.leftPush("v0");

        // 输出List2中元素的总个数
        Long size = listOps.size();
        System.out.println(size); // 2

        // 取出list2中【0，size-1】范围内的所有元素
        List elements = listOps.range(0, size - 1);

        System.out.println(elements); // [v0, v3]
        // 测试range方法是否会pop出list中的元素，答案：不会，list中的元素以复制的方式进行返回
        System.out.println(listOps.size());

        return new CommonResult(true, "见Console输出");
    }

    /**
     * Redis中无序集合操作Set，元素无序且不能重复
     */
    @GetMapping("/set")
    @ResponseBody
    public CommonResult redisSetOps() {
        // 再开始操作set之前，判断并清空set集合中的元素
        emptySet("set1");
        emptySet("set2");

        // 分别向set1和set2集合添加元素
        stringRedisTemplate.opsForSet().add("set1", "v1", "v1", "v2", "v3", "v4", "v5");
        stringRedisTemplate.opsForSet().add("set2", "v2", "v4", "v6", "v8");
        System.out.println(stringRedisTemplate.opsForSet().members("set1")); // [v4, v2, v3, v1, v5]
        System.out.println(stringRedisTemplate.opsForSet().members("set2")); // [v6, v4, v2, v8]

        // 绑定一个set1集合进行多次操作
        BoundSetOperations setOps = stringRedisTemplate.boundSetOps("set1");
        // 添加元素
        setOps.add("v6", "v7");
        // 删除集合中的元素
        setOps.remove("v1", "v7");
        System.out.println(setOps.members()); // [v2, v5, v3, v6, v4]
        System.out.println(setOps.size()); // 5

        // 求交集
        Set inter = setOps.intersect("set2");
        System.out.println(inter); // [v6, v4, v2]

        // 求差集
        Set diff = setOps.diff("set2");
        System.out.println(diff); // [v5, v3]
        // 测试求差集后是否会影响原set中的元素
        System.out.println(setOps.members()); // [v2, v5, v3, v6, v4]

        // 求差集并使用新的集合保存
        setOps.diffAndStore("set2", "diff1");
        printRedisList("diff1");

        // 求并集
        Set union = setOps.union("set2");
        System.out.println(union); // [v2, v5, v3, v6, v4, v8]

        // 求并集并使用新的集合保存
        setOps.unionAndStore("set2", "union1");
        printSetMembers("union1");

        return new CommonResult(true, "见Console输出");
    }

    /**
     * Redis中ZSet集合示例操作
     * Zset集合根据score值进行排序，所以每个元素的结构为/<value, score>
     */
    @GetMapping("/zset")
    @ResponseBody
    public CommonResult redisZsetOps() {

        // 使用TypedTuple创建ZSET
        Set<ZSetOperations.TypedTuple<String>> typedTuples = new HashSet<>();
        for (int i = 1; i <= 9; i++) {
            double score = i * 0.1;
            ZSetOperations.TypedTuple<String> typedTuple = new DefaultTypedTuple<>("value" + i, score);
            typedTuples.add(typedTuple);
        }
        stringRedisTemplate.opsForZSet().add("zset1", typedTuples);

        // 输出Zset的集合大小
        BoundZSetOperations zsetOps = stringRedisTemplate.boundZSetOps("zset1");
        Long size = zsetOps.size();
        System.out.println(size); // 8

        // 输出Zset结合中的所有元素
        Set members = zsetOps.range(0, size - 1);
        System.out.println(members); // [value1, value2, value3, value4, value5, value6, value7, value8]

        // 加入一个元素
        zsetOps.add("value10", 0.26);

        // 输出score在该范围中的所有元素
        Set setScore = zsetOps.rangeByScore(0.2, 0.6);
        System.out.println(setScore); // [value2, value10, value3, value4, value5]

        // 定义值范围
        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
//        range.gt("value3"); // 大于value3
//        range.lt("value8"); // 小于value8
        range.lte("value8"); // 小于等于value8
        // 按值排序
        Set setLex = zsetOps.rangeByLex(range);
        System.out.println(setLex); // [value1, value2, value10, value3, value4, value5, value6, value7, value8]

        // 删除元素
        zsetOps.remove("value9", "value2");
        System.out.println(zsetOps.range(0, zsetOps.size() - 1)); // [value1, value10, value3, value4, value5, value6, value7, value8]

        // 得到value值对应的分数
        Double score = zsetOps.score("value8");
        System.out.println(score); // 0.8

        // 在下标区间内，按分数排序，同时返回value和score
        Set<ZSetOperations.TypedTuple<String>> rangeSet = zsetOps.rangeWithScores(1, 6);
        System.out.println(rangeSet.toArray());

        // 在分数区间内，按分数排序，同时返回value和score
        Set<ZSetOperations.TypedTuple<String>> scoreSet = zsetOps.rangeByScoreWithScores(1, 6);
        System.out.println(scoreSet); // []

        // 从大到小排序，默认为从小到达排序
        Set<String> reverseSet = zsetOps.reverseRange(2, 8);
        System.out.println(reverseSet); // [value6, value5, value4, value3, value10, value1]

        return new CommonResult(true, "见Console输出");
    }

    /**
     * 使用List中的range方法来获取list中的所有元素
     * range方法不会像pop方法一样将元素从redis中删除
     */
    private void printRedisList(String listKey) {
        BoundListOperations listOps = stringRedisTemplate.boundListOps(listKey);
        Long size = listOps.size();
        List elemetns = listOps.range(0, size - 1);

        StringBuilder sb = new StringBuilder();
        sb.append("List: " + listKey + ", elements: ");
        for (int i = 0; i < elemetns.size(); i++) {
            sb.append(elemetns.get(i) + ", ");
        }
        System.out.println(sb.toString());
    }

    /**
     * 判断list是否存在并进行删除
     */
    private void deleteExistedList(String listKey) {
        if (stringRedisTemplate.opsForList().size(listKey) != null) {
            stringRedisTemplate.delete(listKey);
        }
    }

    /**
     * 清空已存在的set集合，避免多次操作过程中历史数据对示例操作的影响
     */
    private void emptySet(String setKey) {
        if (!stringRedisTemplate.opsForSet().members(setKey).isEmpty()) {
            stringRedisTemplate.opsForSet().members(setKey).clear();
        }
    }

    /**
     * 打印set结合中的所有元素
     */
    private void printSetMembers(String setKey) {
        BoundSetOperations setOps = stringRedisTemplate.boundSetOps(setKey);
        System.out.println(setOps.members());
    }

}
