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

        // 获取set中的成员数量
        System.out.println(jedis.scard("myset")); // 2

        // 获取第一个集合与其他多个集合的差集
        System.out.println(jedis.sadd("key1", "a"));
        System.out.println(jedis.sadd("key1", "b"));
        System.out.println(jedis.sadd("key1", "c"));
        System.out.println(jedis.sadd("key2", "c"));
        System.out.println(jedis.sadd("key2", "d"));
        System.out.println(jedis.sadd("key2", "e"));
        System.out.println(jedis.sdiff("key1", "key2"));

        // 获取所有给定集合的交集所得的集合成员
        System.out.println(jedis.sadd("key3", "c"));
        System.out.println(jedis.sinter("key1", "key2", "key3"));
    }
}
