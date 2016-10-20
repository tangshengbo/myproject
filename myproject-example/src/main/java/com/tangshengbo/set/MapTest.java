package com.tangshengbo.set;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class MapTest {
	public static void main(String[] args) throws UnsupportedEncodingException {

		MapTest mapTest = new MapTest();
		//mapTest.hashMap();
		// mapTest.linkedMap();
		//mapTest.treeMap();
		//mapTest.testRedis();
		//mapTest.testRedisPool();
		mapTest.testRedisPools();
		/*byte[] b = new byte[0x16];
		System.out.println(b);
		String temp = new String(b);
		System.out.println(temp);
		System.out.println(Integer.SIZE);
		
		System.out.println(111%4);
		*/
		
	   
	}

	public void hashMap() {
		HashMap map = new HashMap();

		map.put("a3", "aa");
		map.put("a2", "bb");
		map.put("b1", "cc");
		map.get("");
		map.remove("");

		/*
		 * Iterator it = map.values().iterator(); while (it.hasNext()) {
		 * map.remove(it.next()); System.out.println("没有报错");
		 * 
		 * }
		 */

		Set keys = map.keySet();
		Iterator it = keys.iterator();
		while (it.hasNext()) {
			map.remove(it.next());
			System.out.println("没有报错");
		}

	}

	public void linkedMap() {
		Map map = new HashMap();
		map.put("a3", "aa");
		map.put("a2", "bb");
		map.put("b1", "cc");
		Iterator it = map.values().iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public void treeMap() {
		Map map = new TreeMap(new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
				return o2.toString().compareTo(o1.toString());
			}
		});
		map.put("a3", "aa");
		map.put("a2", "bb");
		map.put("b1", "cc");
		Iterator it = map.values().iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
		

	}
	public void testRedis(){
		
		
		// 创建连接
		/*String host = "172.0.0.1";
		int port = 6379;*/
		Jedis client = new Jedis();
		execute(client);
		
		
	}
	public void testRedisPool(){
		
		// 生成连接池配置信息
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(10);//闲置连接数
		config.setMaxTotal(30);//总连接数
		config.setMaxWaitMillis(3*1000);//超时时间
		// 在应用初始化的时候生成连接池
		JedisPool pool = new JedisPool(config, "localhost", 6379);
		// 在业务操作时，从连接池获取连接
		Jedis clien = pool.getResource();
		try {
			execute(clien);
		} finally {
			if(null != clien){
				pool.returnResource(clien);
			}
		}
		pool.destroy();
		
		
		
		
	}
	public void testRedisPools(){
		// 生成多机连接信息列表
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		//shards.add( new JedisShardInfo("127.0.0.1", 6379) );
		shards.add( new JedisShardInfo("192.168.17.129", 6379) );

		// 生成连接池配置信息
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(10);
		config.setMaxTotal(30);
		config.setMaxWaitMillis(3*1000);

		// 在应用初始化的时候生成连接池
		ShardedJedisPool pool = new ShardedJedisPool(config, shards);

		// 在业务操作时，从连接池获取连接
		ShardedJedis client = pool.getResource();
		try {
		    // 执行指令
		    String result = client.set("key-string", "Hello, Redis!sssssss");
		    System.out.println( String.format("set指令执行结果:%s", result) );
		    String value = client.get("key-string");
		    System.out.println( String.format("get指令执行结果:%s", value) );
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		    // 业务操作完成，将连接返回给连接池
		    if (null != client) {
		        pool.returnResource(client);
		    }
		} // end of try block

		// 应用关闭时，释放连接池资源
		pool.destroy();
		
	}
	
	
	private void execute(Jedis client){
		
		String result = client.set("key-string", "Hello, Redis!");
		System.out.println( String.format("set指令执行结果:%s", result) );

		// 执行get指令
		String value = client.get("key-string");
		
		System.out.println( String.format("get指令执行结果:%s", value) );
	}

}
