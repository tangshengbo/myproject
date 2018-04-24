package com.tangshengbo.nosql.redis;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Tangshengbo on 2018/4/24.
 */
public class JedisUtil {

    public static JedisPoolConfig getDefaultPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(20);
        jedisPoolConfig.setMaxTotal(20 * 50);
        jedisPoolConfig.setMaxWaitMillis(20 * 100);
        jedisPoolConfig.setTestOnBorrow(false);
        return jedisPoolConfig;
    }
}
