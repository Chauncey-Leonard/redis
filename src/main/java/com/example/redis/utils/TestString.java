package com.example.redis.utils;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * String
 */
@Slf4j
public class TestString {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        // 新增
        // System.out.println(jedis.set("firstName", "Chauncey"));
        // System.out.println(jedis.set("lastName", "Leonard"));
        // System.out.println(jedis.set("fullName", "Chauncey Leonard"));

        // 删除
        // System.out.println(jedis.del("key1"));

        // 获取键的值
        System.out.println(jedis.get("firstName"));
    }
}
