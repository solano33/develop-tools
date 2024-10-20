package com.solano.redis;

import com.solano.redis.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.Map;
import java.util.Random;

/**
 * @author github.com/solano33
 * @date 2024/9/10 22:00
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedissonQueueTest {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void testQueue() {
        RPriorityQueue<Integer> queue = redissonClient.getPriorityQueue("anyQueue");
        queue.trySetComparator(new MyComparator()); // 指定对象比较器
        queue.add(3);
        queue.add(1);
        queue.add(2);

        queue.removeAsync(0);
//        queue.addAsync(5);

        log.info("queue.poll(): {}", queue.poll());

        RKeys keys = redissonClient.getKeys();
        Iterable<String> iterable = keys.getKeys(1000);
        iterable.forEach(e -> log.info("key: {}", e));
    }

    @Test
    public void testBoundedBlockingQueue() {
        RBoundedBlockingQueue<Integer> queue = redissonClient.getBoundedBlockingQueue("bbq");
        RSortedSet<Object> sortedSet = redissonClient.getSortedSet("");
        queue.trySetCapacity(5);
//        queue.trySetComparator(new MyComparator()); // 指定对象比较器
        queue.add(3);
        queue.add(1);
        queue.add(2);

        queue.removeAsync(0);
//        queue.addAsync(5);

        log.info("queue.poll(): {}", queue.poll());

        RKeys keys = redissonClient.getKeys();
        Iterable<String> iterable = keys.getKeys(1000);
        iterable.forEach(e -> log.info("key: {}", e));
    }

    @Test
    public void testString() {
        ValueOperations opsForValue = redisTemplate.opsForValue();
        opsForValue.set("name", "solano");
        Object value = opsForValue.get("name");
        System.out.println("name = " + value);
    }

    @Test
    public void testJson() {
        ValueOperations opsForValue = redisTemplate.opsForValue();
        opsForValue.set("solano", new User(1, "solano", 25));
        Object value = opsForValue.get("solano");
        System.out.println("solano = " + value);
    }

    @Test
    public void testString2() {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set("name2", "solano2");
        Object value = valueOperations.get("name2");
        System.out.println("name2 = " + value);
    }

    @Test
    public void testHash() {
        HashOperations<String, Object, Object> hashOperations = stringRedisTemplate.opsForHash();
        hashOperations.put("hash", "name", "duo");
        hashOperations.put("hash", "age", "20");
        Map<Object, Object> entries = hashOperations.entries("hash");
        System.out.println("entries = " + entries);
    }


    /**
     * ZSet<task_queue_work_type_id, task_id, priority>
     */
    @Test
    public void testZSet() {
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            zSetOperations.add(keyGenerator(100000), i, random.nextInt(100));
        }
    }

    private String keyGenerator(int workerTypeId) {
        return "task_queue:" + workerTypeId;
    }

    /**
     * 任务创建时：落库，判断redis该类型的队列是否满了，没满则入队
     * 任务废弃/删除时：从队列中移除，然后修改DB状态
     * 任务领取：从redis队列中获取优先级最高的一条，并从中移除，修改DB状态
     * 任务重试：修改DB状态，尝试入队
     */

}

