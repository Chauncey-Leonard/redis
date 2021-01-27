package com.example.redis.test;

import redis.clients.jedis.Jedis;

public class TestSortedSet {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();

        // 新增一个或多个元素至排序集合
        System.out.println(jedis.zadd("myzset", 1, "one"));
        System.out.println(jedis.zadd("myzset", 1, "uno"));
        System.out.println(jedis.zadd("myzset", 2, "two"));
        System.out.println(jedis.zadd("myzset", 3, "three"));

        // 获取集合中的元素数量
        System.out.println(jedis.zcard("myzset"));

        // 获取集合中指定区间的元素个数
        System.out.println(jedis.zcount("myzset", "-inf", "+inf"));
        System.out.println(jedis.zcount("myzset", "(1", "3"));

        System.out.println(jedis.zadd("zset1", 1, "one"));
        System.out.println(jedis.zadd("zset1", 2, "two"));
        System.out.println(jedis.zadd("zset1", 3, "three"));
        System.out.println(jedis.zadd("zset2", 1, "one"));
        System.out.println(jedis.zadd("zset2", 2, "two"));
        System.out.println(jedis.zrangeWithScores("zset1", 1, 3));
    }
}
