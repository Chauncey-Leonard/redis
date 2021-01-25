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
        // System.out.println(jedis.get("firstName"));

        // 修改
        // System.out.println(jedis.set("lastName", "Tang"));

        // 在fullName值后追加值
        // System.out.println(jedis.append("fullName", "appendValue"));
        // System.out.println(jedis.get("fullName"));

        // 新增多个键值对
        // System.out.println(jedis.mset("key1", "value1", "key2", "value2"));

        // 获取多个键值对
        // System.out.println(jedis.mget("key1", "key2"));

        // 删除多个键值对
        System.out.println(jedis.del("key1", "key2"));
    }
}
