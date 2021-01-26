package com.example.redis.utils;

import redis.clients.jedis.Jedis;

public class TestSet {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();

        // 新增
        System.out.println(jedis.sadd("myset", "Hello")); // 1
        System.out.println(jedis.sadd("myset", "World")); // 1
        System.out.println(jedis.sadd("myset", "World")); // 0
    }
}
