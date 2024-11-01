package com.solano.redis.redis;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.solano.redis.pojo.TaskMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class RedisZSetQOps {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private String groupName;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate<String, Integer> redisQueueTemplate;

    private final ResourceScriptSource ZSetEnqueueScript;
    private final ResourceScriptSource ZSetDequeueSingleScript;
    private final ResourceScriptSource ZSetDequeueMultiScript;
    private final ResourceScriptSource ZSetFindRangeScript;
    private final ResourceScriptSource enqueueBulkScript;
    private final ResourceScriptSource testScript;

    public RedisZSetQOps() {
        ZSetEnqueueScript = new ResourceScriptSource(new ClassPathResource("lua/ZSetEnqueue.lua"));
        ZSetDequeueSingleScript = new ResourceScriptSource(new ClassPathResource("lua/ZSetDequeueSingle.lua"));
        ZSetDequeueMultiScript = new ResourceScriptSource(new ClassPathResource("lua/ZSetDequeueMulti.lua"));
        ZSetFindRangeScript = new ResourceScriptSource(new ClassPathResource("lua/ZSetFindRange.lua"));
        enqueueBulkScript = new ResourceScriptSource(new ClassPathResource("lua/EnqueueBulk.lua"));
        testScript = new ResourceScriptSource(new ClassPathResource("lua/test.lua"));
    }

    public RedisZSetQOps(String groupName) {
        this.groupName = groupName;
        ZSetEnqueueScript = new ResourceScriptSource(new ClassPathResource("lua/ZSetEnqueue.lua"));
        ZSetDequeueSingleScript = new ResourceScriptSource(new ClassPathResource("lua/ZSetDequeueSingle.lua"));
        ZSetDequeueMultiScript = new ResourceScriptSource(new ClassPathResource("lua/ZSetDequeueMulti.lua"));
        ZSetFindRangeScript = new ResourceScriptSource(new ClassPathResource("lua/ZSetFindRange.lua"));
        enqueueBulkScript = new ResourceScriptSource(new ClassPathResource("lua/EnqueueBulkScript.lua"));
        testScript = new ResourceScriptSource(new ClassPathResource("lua/test.lua"));
    }

    public RedisZSetQOps self() {
        return this;
    }

    public Object test() {
//        DefaultRedisScript<Map<Object, Object>> redisScript = new DefaultRedisScript<>();
//        redisScript.setScriptSource(testScript);
//        Object execute = redisTemplate.execute(redisScript, Collections.singletonList("TC:AIC:123456789"), 444);
//        log.info("execute: {}", execute);
//        return null;
        String luaScript = "local tmp = {}\n" +
                "tmp['instanceId'] = 666\n" +
                "tmp['lastBeatTime'] = '2024-11-01T09:00:00'\n" +
                "return tmp";
        DefaultRedisScript<Map> redisScript = new DefaultRedisScript<>(luaScript, Map.class);
        Map results = redisTemplate.execute(redisScript, Arrays.asList("TC:AIC:123456789"), 777);
        log.info("results: {}", results);
        return null;
    }



    /**
     * 消息入队
     * @param value 消息实例
     * @param priority 优先级
     */
    public void enqueue(int value, int priority) {
        DefaultRedisScript<Object> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(ZSetEnqueueScript);
        String statusKey = "tc:pq:100000";
//        redisTemplate.boundValueOps(statusKey).set(value);

        redisTemplate.execute(redisScript,
            Collections.singletonList(statusKey),
            value, priority);
    }

    public static void main(String[] args) {
        JSONObject parse = JSONObject.parse("{\"test\": \"555\"}");
        System.out.println(parse);
    }
    public void enqueue(List<TaskMessage> messages) {
        DefaultRedisScript<Object> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(enqueueBulkScript);
//        redisScript.setScriptSource(testScript);
        redisScript.setResultType(Object.class);
        String statusKey = "test:100005";
//        redisTemplate.boundValueOps(statusKey).set(value);

        List<Integer> messageStrings = new ArrayList<>(messages.size() * 2);
        for (TaskMessage message : messages) {
            messageStrings.add(message.getTaskId());
            messageStrings.add(message.getPriority());
        }
        Integer[] array = messageStrings.toArray(new Integer[0]);
        List<Integer> execute = (List<Integer>)redisQueueTemplate.execute(redisScript,
                Collections.singletonList(statusKey),
                array);
//        System.out.println(execute.getClass());
        log.info("execute: {}", execute);
//        log.info("execute: {}", execute.get(0));
    }
//
//    /**
//     * 单条消息出队
//     * @param queueName 队列名
//     * @return 消息实例
//     */
//    public Message dequeue(String queueName) {
//        DefaultRedisScript<String> redisScript = new DefaultRedisScript();
//        redisScript.setScriptSource(ZSetDequeueSingleScript);
//        redisScript.setResultType(String.class);
//        String messageId = redisTemplate.execute(redisScript, Collections.singletonList(KeyUtil.taskRankKey(groupName, queueName)));
//        if (messageId == null) {
//            return null;
//        }
//        String statusKey = KeyUtil.taskStatusKeyPrefix(groupName, queueName) + messageId;
//        Message message = (Message) redisTemplate.boundValueOps(statusKey).get();
//        if (message == null) {
//            log.warn("消息单条弹出排队队列异常: key: {}, value: {}", statusKey, JsonUtil.obj2String(message));
//        }
//        return message;
//    }
//
//    /**
//     * 多条消息出队
//     * @param queueName 队列名
//     * @param rows 拉取条数
//     * @return 消息实例集合
//     */
//    public List<Message> dequeue(String queueName, int rows) {
//        DefaultRedisScript<List> redisScript = new DefaultRedisScript<>();
//        redisScript.setScriptSource(ZSetDequeueMultiScript);
//        redisScript.setResultType(List.class);
//        List messageIds = redisTemplate.execute(redisScript, Collections.singletonList(KeyUtil.taskRankKey(groupName, queueName)), --rows);
//        if (CollectionUtils.isEmpty(messageIds)) {
//            return Collections.emptyList();
//        }
//        List<Message> messages = (List<Message>) messageIds.stream().map(messageId ->
//                redisTemplate.boundValueOps(KeyUtil.taskStatusKeyPrefix(groupName, queueName) + messageId).get()
//            )
//            .filter(Objects::nonNull)
//            .collect(Collectors.toList());
//        if (messageIds.size() != messages.size()) {
//            log.warn("消息批量弹出排队队列异常: keys: {}, values: {}", JsonUtil.obj2String(messageIds), JsonUtil.obj2String(messages));
//        }
//        return messages;
//    }
//
//    public Long removeRunningMessage(Message message) {
//        Long remove = redisTemplate.boundListOps(KeyUtil.taskRunningKey(groupName, message.getQueueName())).remove(0, message.getId());
//        if (remove > 0) {
//            redisTemplate.delete(KeyUtil.taskStatusKeyPrefix(groupName, message.getQueueName()) + message.getId());
//        }
//        return remove;
//    }
//
//    /**
//     * 查询zset中指定元素之后限定数量的元素列表
//     */
//    public List<Message> findRankRange(String queueName, int limit, String member) {
//        DefaultRedisScript<List> redisScript = new DefaultRedisScript<>();
//        redisScript.setScriptSource(ZSetFindRangeScript);
//        redisScript.setResultType(List.class);
//        List messageIds = redisTemplate.execute(redisScript,
//            Collections.singletonList(KeyUtil.taskRankKey(groupName, queueName)), limit, member);
//        return (List<Message>) messageIds.stream().map(messageId ->
//                redisTemplate.boundValueOps(KeyUtil.taskStatusKeyPrefix(groupName, queueName) + messageId).get()
//            )
//            .collect(Collectors.toList());
//    }
//
//    public int findRankCount(String queueName) {
//        Long count = redisTemplate.boundZSetOps(KeyUtil.taskRankKey(groupName, queueName)).zCard();
//        return count.intValue();
//    }
//
//    /**
//     * 删除队列消息
//     * @param queueName 队列名
//     * @param id
//     * @return 被删除的消息
//     */
//    public Message removeRankMessage(String queueName, String id) {
//        long remove = redisTemplate.boundZSetOps(KeyUtil.taskRankKey(groupName, queueName)).remove(id);
//        if (remove > 0) {
//            Object o = redisTemplate.boundValueOps(KeyUtil.taskStatusKeyPrefix(groupName, queueName) + id).get();
//            redisTemplate.delete(KeyUtil.taskStatusKeyPrefix(groupName, queueName) + id);
//            return (Message) o;
//        }
//        return null;
//    }
//
//    /**
//     * 清空队列
//     * @param queueName 队列名
//     * @return 被删除的消息
//     */
//    public List<Message> clearRankMessage(String queueName) {
//        BoundZSetOperations<String, Object> zsetOps = redisTemplate.boundZSetOps(KeyUtil.taskRankKey(groupName, queueName));
//        Set<Object> messageIds = zsetOps.range(0, -1);
//        if (CollectionUtils.isEmpty(messageIds)) {
//            return Collections.emptyList();
//        }
//        List<Message> removed = new ArrayList<>();
//        for (Object messageId : messageIds) {
//            if (zsetOps.remove(messageId) > 0) {
//                Object o = redisTemplate.boundValueOps(KeyUtil.taskStatusKeyPrefix(groupName, queueName) + messageId).get();
//                redisTemplate.delete(KeyUtil.taskStatusKeyPrefix(groupName, queueName) + messageId);
//                removed.add((Message) o);
//            }
//        }
//        return removed;
//    }
//
//    public List<Message> findRankAll(String queueName) {
//        Set<Object> rankMessages = redisTemplate.opsForZSet().range(KeyUtil.taskRankKey(groupName, queueName), 0, -1);
//        return rankMessages.stream().map(messageId ->
//                (Message) redisTemplate.boundValueOps(
//                    KeyUtil.taskStatusKeyPrefix(groupName, queueName) + messageId).get()
//            )
//            .collect(Collectors.toList());
//    }
//
//    public String getGroupName() {
//        return groupName;
//    }
//
//    public RedisZSetQOps setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//        return this;
//    }

}
