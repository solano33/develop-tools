package com.solano.redis;

import com.solano.redis.redis.AirBoundedPriorityQueueService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author wuhongbin@xiaomi.com
 * @date 2024/10/23 10:02
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AirBoundedPriorityQueueServiceTest {

    @Resource
    private RedissonClient redissonClient;
    @Resource
    private AirBoundedPriorityQueueService airBoundedPriorityQueueService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void testAddAll() {
        List<AirBoundedPriorityQueueService.TaskQueueItem> taskQueueItems = Arrays.asList(new AirBoundedPriorityQueueService.TaskQueueItem(1L, 11.1),
                new AirBoundedPriorityQueueService.TaskQueueItem(2L, 22.2),
                new AirBoundedPriorityQueueService.TaskQueueItem(4L, 44.4),
                new AirBoundedPriorityQueueService.TaskQueueItem(3L, 33.3),
                new AirBoundedPriorityQueueService.TaskQueueItem(2L, 55.5));
        airBoundedPriorityQueueService.offer(taskQueueItems, 110);
    }

    @Test
    public void testTake() {
        List<Long> takeList = airBoundedPriorityQueueService.take(110, 1);
        log.info("testTaskMore#takeList: {}", takeList);
    }

    @Test
    public void testTakeBak() {
        List<Long> takeList = airBoundedPriorityQueueService.takeBak(110, 1);
        log.info("testTaskMore#takeList: {}", takeList);
    }

    @Test
    public void testScript() {
        DefaultRedisScript<List> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText("local zsetKey = KEYS[1] local score = tonumber(ARGV[1]) local value = ARGV[2] redis.call('ZADD', zsetKey, score, value) return redis.call('ZRANGE', zsetKey, 0, -1)");
        redisScript.setResultType(List.class);
        Object execute = redisTemplate.execute(redisScript, Collections.singletonList("TC:ABPQ:110"), 10.0, 10);
        log.info("execute: {}", execute);
    }


}
