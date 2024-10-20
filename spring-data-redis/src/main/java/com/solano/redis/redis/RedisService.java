package com.solano.redis.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class RedisService {

    /**
     * 限流脚本
     */
    private final String LIMIT_SCRIPT = "local key = KEYS[1]\n" +
            "local time = tonumber(ARGV[1])\n" +
            "local count = tonumber(ARGV[2])\n" +
            "local current = redis.call('get', key);\n" +
            "if current and tonumber(current) > count then\n" +
            "    return tonumber(current);\n" +
            "end\n" +
            "current = redis.call('incr', key)\n" +
            "if tonumber(current) == 1 then\n" +
            "    redis.call('expire', key, time)\n" +
            "end\n" +
            "return tonumber(current);";

    /**
     * 批量查询脚本
     */
    private final String BATCH_SCRIPT = "local result = {} " +
            "for _, key in ipairs(KEYS) do " +
            "   local value = redis.call('get', key) " +
            "   table.insert(result, value) " +
            "end " +
            "return result";

    @Autowired
    private RedisTemplate redisTemplate;

    public void multiSet(Map<String, String> map) {
        redisTemplate.opsForValue().multiSet(map);
    }

    public List multiGet(List<String> keys) {
        DefaultRedisScript<List> redisScript = new DefaultRedisScript<>(BATCH_SCRIPT, List.class);
        // 执行Lua脚本
        List values = (List) redisTemplate.execute(redisScript, keys);
        return values;
    }

    /**
     * 删除缓存对象
     * @param collection 多个对象
     */
    public long delete(Collection collection) {
        return redisTemplate.delete(collection);
    }

    /**
     * 限流
     *
     * @param key 缓存键
     * @param time 限流时间，单位秒
     * @param count 限流次数
     */
    public boolean limit(String key, int time, int count) {
        DefaultRedisScript<Long> limitScript = new DefaultRedisScript<>(LIMIT_SCRIPT, Long.class);
        // 当前请求次数
        Long currentRequestCount = (Long) redisTemplate.execute(limitScript, Arrays.asList(key), time, count);
        return currentRequestCount > count;
    }

}

