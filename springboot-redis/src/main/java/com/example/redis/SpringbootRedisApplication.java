package com.example.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 在 springboot2.x 之后，原来使用的 jedis 被替换为了 lettuce
 * <p>
 * Jedis：采用的是直接连接服务，在多线程下是不安全的，如果想要避免不安全的情况，需要使用 Jedis pool BIO
 * <p>
 * lettuce：采用的是 netty，实例可以在多个线程中共享，不存在线程不安全的情况，可以减少线程的数量 NIO
 */
@SpringBootApplication
public class SpringbootRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedisApplication.class, args);
    }

}
