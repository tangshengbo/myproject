package com.tangshengbo.redis.impl;

import com.tangshengbo.redis.JedisClient;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * redis集群版客户端
 * <p>Title: JedisClientCluster</p>
 * @version 1.0
 */
public class JedisClientCluster implements JedisClient {

	private ShardedJedis shardedJedis; // 分片客户端 --采用一致哈希分片算法

	private ShardedJedisPool shardedJedisPool; // 分片客户端连接池 --采用一致哈希分片算法

	public JedisClientCluster() {
		initShardedJedisPool();
		shardedJedis = shardedJedisPool.getResource();
	}

	// 初始化分片连接池
	public void initShardedJedisPool() {

		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(20);
		jedisPoolConfig.setMaxTotal(20 * 50);
		jedisPoolConfig.setMaxWaitMillis(20 * 100);
		jedisPoolConfig.setTestOnBorrow(false);

		List<JedisShardInfo> list = new ArrayList<JedisShardInfo>();
		list.add(new JedisShardInfo("localhost", 6379));
		list.add(new JedisShardInfo("localhost", 6380));
		list.add(new JedisShardInfo("localhost", 6381));

		shardedJedisPool = new ShardedJedisPool(jedisPoolConfig, list);
	}
	@Override
	public String get(String key) {
		String string = shardedJedis.get(key);
		return string;
	}

	@Override
	public String set(String key, String value) {
		String string = shardedJedis.set(key, value);
		return string;
	}

	@Override
	public long incr(String key) {
		Long result = shardedJedis.incr(key);
		return result;
	}

	@Override
	public Long hset(String hkey, String key, String value) {
		Long result = shardedJedis.hset(hkey, key, value);
		return result;
	}

	@Override
	public String hget(String hkey, String key) {
		String string = shardedJedis.hget(hkey, key);
		return string;
	}

	@Override
	public Long del(String key) {
		Long result = shardedJedis.del(key);
		return result;
	}

	@Override
	public Long hdel(String hkey, String key) {
		Long result = shardedJedis.hdel(hkey, key);
		return result;
	}

	@Override
	public Long expire(String key, int second) {
		Long result = shardedJedis.expire(key, second);
		return result;
	}

}
