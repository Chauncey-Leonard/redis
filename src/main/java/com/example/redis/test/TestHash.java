package com.example.redis.test;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

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

        // 批量设置属性值
        Map<String, String> hash = new HashMap<>();
        hash.put("field1", "value1");
        hash.put("field2", "value2");
        hash.put("field3", "value3");
        hash.put("field4", "value4");
        jedis.hmset("myHash", hash);

        // 批量获取属性值
        System.out.println(jedis.hmget("myHash", "field1", "field2"));

        // 获取指定键的全部属性值
        System.out.println(jedis.hvals("myHash"));
    }
}
