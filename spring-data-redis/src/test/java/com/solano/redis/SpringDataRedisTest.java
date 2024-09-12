package com.solano.redis;

import com.solano.redis.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author github.com/solano33
 * @date 2024/9/10 22:00
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataRedisTest {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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

}
