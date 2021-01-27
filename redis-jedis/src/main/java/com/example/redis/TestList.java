package com.example.redis;

import redis.clients.jedis.Jedis;

public class TestList {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        jedis.flushDB();

        System.out.println("===================== 新增 =======================");
        jedis.lpush("collections", "ArrayList", "LinkedList", "Vector");
        jedis.lpush("collections", "HashMap");
        jedis.lpush("collections", "ConcurrentHashMap");

        System.out.println("collections:");
        System.out.println(jedis.lrange("collections", 0, -1));
        System.out.println(jedis.lrange("collections", 0, 3));

        System.out.println("删除指定元素的个数: " + jedis.lrem("collections", 1, "HashMap"));
        System.out.println(jedis.lrange("collections", 0, -1));

        System.out.println("删除区间0-2之间的元素: " + jedis.ltrim("collections", 0, 2));
        System.out.println("collections: " + jedis.lrange("collections", 0, -1));

        System.out.println("左端出栈: " + jedis.lpop("collections"));
        System.out.println("collections: " + jedis.lrange("collections", 0, -1));

        System.out.println("右端添加数据: " + jedis.rpush("collections", "HashSet"));
        System.out.println("collections: " + jedis.lrange("collections", 0, -1));

        System.out.println("修改指定下标的值: " + jedis.lset("collections", 0, "lset"));
        System.out.println("collections: " + jedis.lrange("collections", 0, -1));

        System.out.println("collections的长度: " + jedis.llen("collections"));
        System.out.println("collections下标为2的值: " + jedis.lindex("collections", 2));

        jedis.lpush("sortedList", "3", "2", "6", "5", "1");
        System.out.println("排序前: " + jedis.lrange("sortedList", 0, -1));
        jedis.sort("sortedList");
        System.out.println("排序后: " + jedis.lrange("sortedList", 0, -1));

    }
}
