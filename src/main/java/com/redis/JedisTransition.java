package com.redis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * Jedis事务
 *
 * @author 53137
 */
public class JedisTransition {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.199.246", 6379);
        jedis.auth("lihaipeng");
        jedis.flushDB();
        //开启事务
        Transaction multi = jedis.multi();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "lihaipeng");
            jsonObject.put("age", 19);
            multi.set("user1", jsonObject.toJSONString());
            multi.set("user:name", "lihaipeng");
            //出现异常----事务终止执行
            //int a = 1 / 0;
            //执行事务
            multi.exec();
        } catch (Exception e) {
            //放弃事务
            multi.discard();
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user:name"));
            //关闭连接
            jedis.close();
        }
    }
}
