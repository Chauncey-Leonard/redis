package com.example.redis.test;

import redis.clients.jedis.Jedis;

/**
 * Jedis 基础API
 */
public class TestKey {
    public static void main(String[] args) {
        // 1、创建Jedis对象
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 测试Redis连接是否成功
        // System.out.println(jedis.ping()); // PONG

        // 清空当前数据库
        // System.out.println(jedis.flushDB());

        // 判断键是否存在
        // System.out.println(jedis.exists("username"));

        // 新增键值对
        // System.out.println(jedis.set("username", "Chauncey"));

        // 获取数据库中的键
        // System.out.println(jedis.keys("*"));

        // 删除指定键
        // System.out.println(jedis.del("username"));

        // 查看name值的类型
        // System.out.println(jedis.type("username"));

        // 随机返回一个key
        // System.out.println(jedis.randomKey());

        // 重命名username
        // System.out.println(jedis.rename("username", "name"));

        // 按索引查询
        // System.out.println(jedis.select(0));

        // 查询当前数据库中key的数量
        // System.out.println(jedis.dbSize());

        // 删除全部数据库中的键
        System.out.println(jedis.flushAll());
    }
}
