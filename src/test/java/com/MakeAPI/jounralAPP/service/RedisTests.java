package com.MakeAPI.jounralAPP.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Disabled
    @Test
    void redisTest(){
        Object salary = redisTemplate.opsForValue().get("salary");
        redisTemplate.opsForValue().set("email","abc@email.com");

    }
}