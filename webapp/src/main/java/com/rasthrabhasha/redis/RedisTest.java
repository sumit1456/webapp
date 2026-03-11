package com.rasthrabhasha.redis;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    
   
//    @PostConstruct
//    public void testRedis() {
//        System.out.println("Testing Redis connection...");
//
//        redisTemplate.opsForValue().set("test-key", "hello-redis");
//
//        String value = redisTemplate.opsForValue().get("test-key");
//
//        System.out.println("Value from Redis: " + value);
//    }
}