package com.example.redis.utils;

import redis.clients.jedis.Jedis;

public class RedisUtilTest {
    public static void main(String[] args) {
        // 1、创建Jedis对象
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 测试Redis连接是否成功
        System.out.println(jedis.ping()); // PONG
    }
}
