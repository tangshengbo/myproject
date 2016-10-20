package com.tangshengbo.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.SortingParams;

public class RedisClient {
	private Jedis jedis; // 非分片客户端连接
	private JedisPool jedisPool;// 非分片客户端连接池
	private ShardedJedis shardedJedis;// 分片客户端 --采用一致哈希分片算法
	private ShardedJedisPool shardedJedisPool;// 分片客户端连接池 --采用一致哈希分片算法

	public RedisClient() {
		initJedisPool();
		initShardedJedisPool();
		jedis = jedisPool.getResource();
		shardedJedis = shardedJedisPool.getResource();

	}

	// 初始化非分片连接池
	public void initJedisPool() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(20);
		jedisPoolConfig.setMaxTotal(20 * 50);
		jedisPoolConfig.setMaxWaitMillis(20 * 100);
		jedisPoolConfig.setTestOnBorrow(false);
		jedisPool = new JedisPool(jedisPoolConfig, "localhost", 6379);

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

		shardedJedisPool = new ShardedJedisPool(jedisPoolConfig, list);

	}

	public void show() {

	}

	public void keyOperate() {
		System.out.println("======================key==========================");
		// 清空数据
		System.out.println("清空库中所有数据：" + jedis.flushDB());
		// 判断key否存在
		System.out.println("判断key999键是否存在：" + shardedJedis.exists("key999"));
		System.out.println("新增key001,value001键值对：" + shardedJedis.set("key001", "value001"));
		System.out.println("判断key001是否存在：" + shardedJedis.exists("key001"));
		System.out.println("新增key002,value002键值对：" + shardedJedis.set("key002", "value002"));
		// 输出系统中所有的key
		System.out.println("系统中所有键如下：");
		Set<String> keys = jedis.keys("*");
		if (null != keys) {
			for (String string : keys) {
				System.out.println(string);
			}
		}
		// 删除某个key,若key不存在，则忽略该命令。
		System.out.println("系统中删除key002: " + jedis.del("key002"));
		System.out.println("判断key002是否存在：" + shardedJedis.exists("key002"));
		// 设置 key001的过期时间
		System.out.println("设置 key001的过期时间为5秒:" + jedis.expire("key001", 5));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 查看某个key的剩余生存时间,单位【秒】.永久生存或者不存在的都返回-1
		System.out.println("查看key001的剩余生存时间：" + jedis.ttl("key001"));
		// 移除某个key的生存时间
		System.out.println("移除key001的生存时间：" + jedis.persist("key001"));

		System.out.println("查看key001的剩余生存时间：" + jedis.ttl("key001"));
		// 查看key所储存的值的类型
		System.out.println("查看key所储存的值的类型：" + jedis.type("key001"));

		/*
		 * 一些其他方法：1、修改键名：jedis.rename("key6", "key0");
		 * 2、将当前db的key移动到给定的db当中：jedis.move("foo", 1)
		 */

	}

	public void stringOperate() {
		System.out.println("======================String_1==========================");
		// 清空数据
		System.out.println("清空库中所有数据：" + jedis.flushDB());
		System.out.println("=============增=============");
		jedis.set("key001", "value001");
		jedis.set("key002", "value002");
		jedis.set("key003", "value003");
		System.out.println("已新增的3个键值对如下：");
		System.out.println(jedis.get("key001"));
		System.out.println(jedis.get("key002"));
		System.out.println(jedis.get("key003"));
		System.out.println("=============删=============");
		System.out.println("删除key003键值对：" + jedis.del("key003"));
		System.out.println("获取key003键对应的值：" + jedis.get("key003"));
		System.out.println("=============改=============");
		// 1、直接覆盖原来的数据
		System.out.println("直接覆盖key001原来的数据：" + jedis.set("key001", "value001-update"));
		System.out.println("获取key001对应的新值：" + jedis.get("key001"));
		// 2、直接覆盖原来的数据
		System.out.println("在key002原来值后面追加：" + jedis.append("key002", "+appendString"));
		System.out.println("获取key002对应的新值" + jedis.get("key002"));

		/**
		 * mset,mget同时新增，修改，查询多个键值对 等价于： jedis.set("name","ssss");
		 * jedis.set("jarorwar","xxxx");
		 */
		System.out.println("一次性新增key201,key202,key203,key204及其对应值："
				+ jedis.mset("key201", "value201", "key202", "value202", "key203", "value203", "key204", "value204"));

		System.out.println(
				"一次性获取key201,key202,key203,key204各自对应的值：" + jedis.mget("key201", "key202", "key203", "key204"));

		System.out.println("一次性删除key201,key202：" + jedis.del(new String[] { "key201", "key202" }));

		System.out.println(
				"一次性获取key201,key202,key203,key204各自对应的值：" + jedis.mget("key201", "key202", "key203", "key204"));

		System.out.println();

		// jedis具备的功能shardedJedis中也可直接使用，下面测试一些前面没用过的方法

		System.out.println("======================String_2==========================");
		// 清空数据
		System.out.println("清空库中所有数据：" + jedis.flushDB());
		System.out.println("原先key301不存在时，新增key301：" + shardedJedis.setnx("key301", "value301"));
		System.out.println("原先key302不存在时，新增key302：" + shardedJedis.setnx("key302", "value302"));
		System.out.println("当key302存在时，尝试新增key302：" + shardedJedis.set("key302", "value302_new"));
		System.out.println("获取key301对应的值：" + shardedJedis.get("key301"));
		System.out.println("获取key302对应的值：" + shardedJedis.get("key302"));
		System.out.println("=============超过有效期键值对被删除=============");
		// 设置key的有效期，并存储数据
		System.out.println("新增key303，并指定过期时间为2秒" + shardedJedis.setex("key303", 2, "key303-2second"));
		System.out.println("获取key303对应的值：" + shardedJedis.get("key303"));

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}
		System.out.println("3秒之后，获取key303对应的值：" + shardedJedis.get("key303"));

		System.out.println("=============获取原值，更新为新值一步完成=============");
		System.out.println("key302原值：" + shardedJedis.getSet("key302", "value302-after-getset"));
		System.out.println("key302新值：" + shardedJedis.get("key302"));
		System.out.println("=============获取子串=============");
		System.out.println("获取key302对应值中的子串：" + shardedJedis.getrange("key302", 5, 7));

	}

	public void listOperate() {

		System.out.println("======================list==========================");
		// 清空数据
		System.out.println("清空库中所有数据：" + jedis.flushDB());
		System.out.println("=============增=============");
		shardedJedis.lpush("stringlists", "vector");
		shardedJedis.lpush("stringlists", "ArrayList");
		shardedJedis.lpush("stringlists", "vector");
		shardedJedis.lpush("stringlists", "vector");
		shardedJedis.lpush("stringlists", "LinkedList");
		shardedJedis.lpush("stringlists", "MapList");
		shardedJedis.lpush("stringlists", "SerialList");
		shardedJedis.lpush("stringlists", "HashList");
		shardedJedis.lpush("numberlists", "3");
		shardedJedis.lpush("numberlists", "1");
		shardedJedis.lpush("numberlists", "5");
		shardedJedis.lpush("numberlists", "2");
		
		System.out.println("所有元素-stringlists："+shardedJedis.lrange("stringlists", 0, -1));
        System.out.println("所有元素-numberlists："+shardedJedis.lrange("numberlists", 0, -1));
        System.out.println("=============删=============");
        // 删除列表指定的值 ，第二个参数为删除的个数（有重复时），后add进去的值先被删，类似于出栈
        System.out.println("成功删除指定元素个数-stringlists："+shardedJedis.lrem("stringlists", 2, "vector")); 
        System.out.println("删除指定元素之后-stringlists："+shardedJedis.lrange("stringlists", 0, -1));
        // 删除区间以外的数据 
        System.out.println("删除下标0-3区间之外的元素："+shardedJedis.ltrim("stringlists", 0, 3));
        
        System.out.println("删除指定区间之外元素后-stringlists："+shardedJedis.lrange("stringlists", 0, -1));
        // 列表元素出栈 
        System.out.println("出栈元素："+shardedJedis.lpop("stringlists")); 
        System.out.println("元素出栈后-stringlists："+shardedJedis.lrange("stringlists", 0, -1));
        
        
        // 数组长度 
        System.out.println("长度-stringlists："+shardedJedis.llen("stringlists"));
        System.out.println("长度-numberlists："+shardedJedis.llen("numberlists"));
        
        
        SortingParams sortingParameters = new SortingParams();
        //sortingParameters.alpha();
        //sortingParameters.asc();
        sortingParameters.desc();
        sortingParameters.limit(0, 3);
       // System.out.println("返回排序后的结果-stringlists："+shardedJedis.sort("stringlists",sortingParameters)); 
        System.out.println("返回排序后的结果-numberlists："+shardedJedis.sort("numberlists",sortingParameters));
        // 子串：  start为元素下标，end也为元素下标；-1代表倒数一个元素，-2代表倒数第二个元素
        System.out.println("子串-第二个开始到结束："+shardedJedis.lrange("stringlists", 1, -1));
        // 获取列表指定下标的值 
        System.out.println("获取下标为2的元素："+shardedJedis.lindex("stringlists", 2)+"\n");
        
        
        
        
        
        
		
		
		
		
		

	}

}
