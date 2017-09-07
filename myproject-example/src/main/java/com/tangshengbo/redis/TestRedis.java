package com.tangshengbo.redis;

import com.tangshengbo.redis.impl.JedisClientCluster;
import com.tangshengbo.redis.impl.JedisClientPool;

public class TestRedis {

    public static void main(String[] args) {
//        RedisClient redisClient = new RedisClient();
//        //redisClient.keyOperate();
//        redisClient.stringOperate();
//        redisClient.listOperate();
        JedisClient jedisClient = new JedisClientPool();
//        System.out.println(jedisClient.set("tang", "1"));
//        System.out.println(jedisClient.expire("tang", 10));
        System.out.println(jedisClient.del("tang"));
        for (int i = 0; i < 100; i++) {
            System.out.println(jedisClient.incr("orderId"));
        }
//        jedisClient.hset("users", "tang", "唐声波");
        System.out.println(jedisClient.hget("users", "tang"));
        System.out.println("设置成功。。。。。。。。。。。。。。");

        jedisClient = new JedisClientCluster();
        for (int i = 0; i < 10000; i++) {
            System.out.println(jedisClient.incr("ids"));
        }

    }
}
