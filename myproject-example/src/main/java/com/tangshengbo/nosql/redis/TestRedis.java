package com.tangshengbo.nosql.redis;

import com.tangshengbo.nosql.redis.impl.JedisClientCluster;
import com.tangshengbo.nosql.redis.impl.JedisClientPool;
import com.tangshengbo.thread.Student;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestRedis {

    private static JedisClient jedisClient = new JedisClientPool();

    private static final Logger logger = LoggerFactory.getLogger(TestRedis.class);

    public static void main(String[] args) {
//        RedisClient redisClient = new RedisClient();
//        //redisClient.keyOperate();
//        redisClient.stringOperate();
//        redisClient.listOperate();

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
        for (int i = 0; i < 100000; i++) {
            System.out.println(jedisClient.incr("ids"));
        }
        Student student = new Student();
        student.setName("");
        student.setAge(0);
    }

    @Test
    public void testSet() {
        Jedis jedis = jedisClient.getJedis();
        jedis.sadd("myset1","123","32","abc","def","123456","sdfasd");
        jedis.sadd("myset2","abc","345","123","fda");
        //获得A-B 获得差集合
        Set<String> sdiff = jedis.sdiff("myset1", "myset2");
        logger.info("sdiff:{}", sdiff);
        jedis.sdiffstore("myset3", "myset1", "myset2");
        //获得交集
        Set<String> sinter = jedis.sinter("myset1", "myset2");
        logger.info("sinter:{}", sinter);
        jedis.sinterstore("myset3", "myset1", "myset2");
        Set<String> sunion = jedis.sunion("myset1", "myset2");
        //获得并集合
        logger.info("sunion:{}", sunion);
        jedis.sunionstore("myset3", "myset1", "myset2");
    }

    @Test
    public void testSortSet() {
        Jedis jedis = jedisClient.getJedis();
        jedis.zadd("mysort",100.0, "zhangsan");
        jedis.zadd("mysort",200.0,"lisi");
        jedis.zadd("mysort",50.0,"wangwu");
        Map<String ,Double> map = new HashMap<>();
        map.put("mutouliu",70.0);

        jedis.zadd("mysort",map);
        Set<String> mysort = jedis.zrange("mysort", 0, -1);
        System.out.println(mysort);
        Set<String> mysort1 = jedis.zrange("mysort", 1, 2);
        System.out.println(mysort1);
        Set<Tuple> tupleSet = jedis.zrevrangeWithScores("mysort", 0, -1);
        tupleSet.forEach(t -> logger.info("{}", t.getElement() + "\t" + t.getScore()));
        Set<String> stringSet = jedis.zrevrangeByScore("mysort", 200, 0);
        logger.info("stringSet:{}", stringSet);
    }
}
