package com.example.redis.utils;

import redis.clients.jedis.Jedis;

public class TestHash {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        jedis.flushDB();

        // 新增
        System.out.println(jedis.hset("fullName", "firstName", "Chauncey"));
        System.out.println(jedis.hset("fullName", "lastName", "Leonard"));

        // 获取hash 属性的值
        System.out.println(jedis.hget("fullName", "firstName"));

        // 获取指定键的全部属性值
        System.out.println(jedis.hgetAll("fullName"));

        // 删除
        System.out.println(jedis.hdel("fullName", "firstName"));

        // 是否存在
        System.out.println(jedis.hexists("fullName", "lastName"));

        // 获取指定键的所有键
        System.out.println(jedis.hkeys("fullName"));
        // 获取指定键的属性数量
        System.out.println(jedis.hlen("fullName"));
    }
}
