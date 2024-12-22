package com.solano.redis;

import com.alibaba.fastjson2.JSONArray;
import com.solano.redis.pojo.TaskMessage;
import com.solano.redis.redis.RedisZSetQOps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author github.com/solano33
 * @date 2024/10/19 16:17
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ZsetQueueTest {

    @Resource
    private RedisZSetQOps redisZSetQOps;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Resource(name = "redisMapStringTemplate")
    private RedisTemplate<String, String> redisMapStringTemplate;

    /**
     * WRONGTYPE Operation against a key holding the wrong kind of value script
     */
    @Test
    public void testEneueue() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            redisZSetQOps.enqueue(i, random.nextInt(100));
        }
    }

    @Test
    public void test() {
        String luaScript = "local tmp = {}\n" +
                "tmp['instanceId'] = 666\n" +
                "tmp['lastBeatTime'] = '2024-11-01T09:00:00'\n" +
                "return tmp";
        luaScript = "return redis.call('hgetall', 'hash')";
        DefaultRedisScript<Map> redisScript = new DefaultRedisScript<>(luaScript, Map.class);
        redisScript.setResultType(Map.class);
        Object results = redisTemplate.execute(redisScript, Arrays.asList("hash"), "ooo");
        log.info("results: {}", results);
    }

    @Test
    public void test2() {
        HashOperations hashOperations = stringRedisTemplate.opsForHash();
        Map hash = hashOperations.entries("tchash");
        log.info("hash: {}", hash);
    }

    /**
     * test
     */
    @Test
    public void testEneueueBulk() {
        Random random = new Random();
        List<TaskMessage> messageList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            messageList.add(new TaskMessage(i, random.nextInt(100)));
        }
        redisZSetQOps.enqueue(messageList);
    }

    @Test
    public void toArray() {
        Random random = new Random();
        List<TaskMessage> messageList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            messageList.add(new TaskMessage(i, random.nextInt(100)));
        }
        JSONArray jsonArray = JSONArray.from(messageList);
        System.out.println(jsonArray);
    }


    /**
     * 批量查询脚本
     */
    private final String BATCH_SCRIPT = "local result = {} " +
            "for _, key in ipairs(KEYS) do " +
            "   local value = redis.call('get', key) " +
            "   table.insert(result, value) " +
            "end " +
            "return result";
    @Test
    public void testQueryBulk() {
        DefaultRedisScript<List> redisScript = new DefaultRedisScript<>(BATCH_SCRIPT, List.class);
        // 执行Lua脚本
        List<String> keys = Arrays.asList("name", "age", "school");
        List values = (List) stringRedisTemplate.execute(redisScript, keys);
        log.info("values: {}", values);
    }

    private final String batch_insert =
            "local queueName = KEYS[1]\n" +
                    "local maxCapacity = 10\n" +
                    "\n" +
                    "-- 检查队列当前长度\n" +
                    "local currentSize = redis.call('ZCARD', queueName)\n" +
//                    "redis.call('set', 'currentSize', currentSize)" +
                    "-- 计算需要移除的元素数量\n" +
                    "local elementsToAdd = #ARGV / 2\n" +
//                    "redis.call('set', 'elementsToAdd', elementsToAdd)" +
                    "local elementsToRemove = math.max(0, currentSize + elementsToAdd - maxCapacity)\n" +
//                    "redis.call('set', 'elementsToRemove', elementsToRemove)" +
                    "-- 如果需要移除元素，移除最低优先级（即最旧的）元素\n" +
                    "if elementsToRemove > 0 then\n" +
                    "    redis.call('ZREMRANGEBYRANK', queueName, 0, elementsToRemove - 1)\n" +
                    "end\n" +
                    "-- 循环遍历所有传入的消息和优先级，将它们加入队列\n" +
                    "for i = 1, #ARGV, 2 do\n" +
                    "    local msgId = ARGV[i]\n" +
                    "    local priority = ARGV[i + 1]\n" +
                    "    redis.call('set', 'for' .. i, msgId .. ':' .. priority)" +
                    "    redis.call('ZADD', queueName, priority, msgId)\n" +
                    "end";
    @Test
    public void testInsertBulk() {
        DefaultRedisScript<Object> redisScript = new DefaultRedisScript<>(batch_insert);
        Object result = redisTemplate.execute(redisScript, Arrays.asList("insertBulk"), 1, 11, 2, 22);
        log.info("result: {}", result);
    }

    @Test
    public void testLuaInsertQueueBulk() {
//        redisZSetQOps.enqueue();
    }
}
