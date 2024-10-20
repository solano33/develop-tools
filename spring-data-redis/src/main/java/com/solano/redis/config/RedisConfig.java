package com.solano.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author github.com/solano33
 * @date 2024/9/10 22:19
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        // 设置连接工厂
        template.setConnectionFactory(redisConnectionFactory);

        // 创建json序列化工具
        GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer();

        // key序列化方式
        template.setKeySerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());

        // value序列化方式
        template.setValueSerializer(jsonSerializer);
        template.setHashValueSerializer(jsonSerializer);

        return template;
    }

    @Bean
    public RedisTemplate<String, Object> redisQueueTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 6.序列化类，对象映射设置
        FastJsonRedisSerializer<Object> valueSerializer = new FastJsonRedisSerializer<>(Object.class);
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        redisTemplate.setKeySerializer(stringSerializer);
        // hash的key也采用String的序列化方式
        redisTemplate.setHashKeySerializer(stringSerializer);
        // value序列化方式采用jackson
        redisTemplate.setValueSerializer(valueSerializer);
        // hash的value序列化方式采用jackson
        redisTemplate.setHashValueSerializer(valueSerializer);
        redisTemplate.setDefaultSerializer(valueSerializer);

        return redisTemplate;
    }
}
