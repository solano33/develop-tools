package com.solano.redis.redis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author wuhongbin@xiaomi.com
 * @date 2024/10/22 19:10
 */
@Slf4j
@Service
public class AirBoundedPriorityQueueService {

    private final String TOPIC_PREFIX = "TC:ABPQ:";
    private final int CAPACITY = 100;

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource(name = "redisQueueTemplate")
    private RedisTemplate<String, Long> redisQueueTemplate;

    private final ResourceScriptSource taskQueueTakeMoreScript;

    public AirBoundedPriorityQueueService() {
        taskQueueTakeMoreScript = new ResourceScriptSource(new ClassPathResource("lua/taskQueueTakeMore.lua"));
    }

    @Getter
    @AllArgsConstructor
    public static class TaskQueueItem implements ZSetOperations.TypedTuple<Long> {

        /**
         * 任务id
         */
        private Long taskId;

        /**
         * 优先级
         */
        private double priority;

        @Override
        public Long getValue() {
            return taskId;
        }

        @Override
        public Double getScore() {
            return priority;
        }

        @Override
        public int compareTo(ZSetOperations.TypedTuple<Long> o) {
            return Double.compare(this.getScore(), o.getScore());
        }
    }

    public int capacity() {
        return CAPACITY;
    }

    /**
     * 往队列添加单个任务
     *
     * @param data
     */
    public void offer(TaskQueueItem data, Integer taskType) {
        offer(Collections.singletonList(data), taskType);
    }

    /**
     *
     */
    public void offer(Collection<TaskQueueItem> data, Integer taskType) {
        ZSetOperations<String, Long> operations = redisQueueTemplate.opsForZSet();
        Long addCount = operations.add(TOPIC_PREFIX + taskType, new HashSet<>(data));
        log.info("addCount: {}", addCount);
    }


    public Optional<Long> take(Integer taskType) {
        List<Long> takeList = take(taskType, 1);
        return takeList.isEmpty() ? Optional.empty() : Optional.of(takeList.get(0));
    }

    public List<Long> take(Integer taskType, Integer count) {
        DefaultRedisScript<List> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(taskQueueTakeMoreScript);
        redisScript.setResultType(List.class);
        // 序列化出来后是Long类型
        List<Long> takeList = redisQueueTemplate.execute(redisScript, Collections.singletonList(TOPIC_PREFIX + taskType), count);
        log.info("takeList: {}", takeList);
        return takeList;
    }

    public List<Long> takeBak(Integer taskType, Integer count) {
        DefaultRedisScript<List> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(taskQueueTakeMoreScript);
        redisScript.setResultType(List.class);
        // 序列化出来后是字符串类型
        List<Long> takeList = stringRedisTemplate.execute(redisScript, Collections.singletonList(TOPIC_PREFIX + taskType), String.valueOf(count));
        log.info("takeList: {}", takeList);
        return null;
    }
}

