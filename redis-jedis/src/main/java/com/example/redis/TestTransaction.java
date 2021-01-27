package com.example.redis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * 测试事务
 */
public class TestTransaction {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello", "world");
        jsonObject.put("name", "Chauncey");

        // 开启事务
        Transaction multi = jedis.multi();
        String value = jsonObject.toJSONString();

        try {
            multi.set("user1", value);
            multi.set("user2", value);

            int i = 1 / 0; // 代码抛出异常，事务执行失败

            multi.exec(); // 执行事务
        } catch (Exception e) {
            e.printStackTrace();
            // 失败放弃事务
            multi.discard();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            // 关闭连接
            jedis.close();
        }

    }
}
