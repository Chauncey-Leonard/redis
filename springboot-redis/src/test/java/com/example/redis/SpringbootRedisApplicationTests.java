package com.example.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;

@SpringBootTest
class SpringbootRedisApplicationTests {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void redisTemplateTest() {
        /*
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         *
         * redisTemplate 操作不同的数据类型
         * opsForValue String
         * opsForList
         * opsForHash
         * opsForSet
         * opsForZSet
         * opsForGeo
         * opsForHyperLogLog
         *
         * 除了基本的数据类型操作，其他常用的方法都可以通过redisTemplate直接操作
         * 比如，事务、和基本的CRUD
         *
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         */

        RedisConnection connection = Objects.requireNonNull(redisTemplate.getConnectionFactory())
                .getConnection();
        connection.flushDb();
        connection.flushAll();

        redisTemplate.opsForValue().set("name", "Chauncey");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }

}
