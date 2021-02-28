package com.redis;


import redis.clients.jedis.Jedis;

/**
 * @author 53137
 */
public class TestJedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.199.246",6379);
        jedis.auth("lihaipeng");
        System.out.println(jedis.ping());
    }
}
