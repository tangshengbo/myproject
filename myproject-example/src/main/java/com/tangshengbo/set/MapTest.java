package com.tangshengbo.set;

import redis.clients.jedis.*;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MapTest {
    public static void main(String[] args) throws UnsupportedEncodingException {

        MapTest mapTest = new MapTest();

        mapTest.dateDiff("2016-8-23 10:12", "2010-8-23 20:52", "yyyy-MM-dd HH:mm");
        //mapTest.hashMap();
        // mapTest.linkedMap();
        //mapTest.treeMap();
        //mapTest.testRedis();
        //mapTest.testRedisPool();
       // mapTest.testRedisPools();
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

    public void testRedis() {


        // 创建连接
		/*String host = "172.0.0.1";
		int port = 6379;*/
        Jedis client = new Jedis();
        execute(client);


    }

    public void testRedisPool() {

        // 生成连接池配置信息
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(10);//闲置连接数
        config.setMaxTotal(30);//总连接数
        config.setMaxWaitMillis(3 * 1000);//超时时间
        // 在应用初始化的时候生成连接池
        JedisPool pool = new JedisPool(config, "localhost", 6379);
        // 在业务操作时，从连接池获取连接
        Jedis clien = pool.getResource();
        try {
            execute(clien);
        } finally {
            if (null != clien) {
                pool.returnResource(clien);
            }
        }
        pool.destroy();


    }

    public void testRedisPools() {
        // 生成多机连接信息列表
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        //shards.add( new JedisShardInfo("127.0.0.1", 6379) );
        shards.add(new JedisShardInfo("192.168.17.129", 6379));

        // 生成连接池配置信息
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(10);
        config.setMaxTotal(30);
        config.setMaxWaitMillis(3 * 1000);

        // 在应用初始化的时候生成连接池
        ShardedJedisPool pool = new ShardedJedisPool(config, shards);

        // 在业务操作时，从连接池获取连接
        ShardedJedis client = pool.getResource();
        try {
            // 执行指令
            String result = client.set("key-string", "Hello, Redis!sssssss");
            System.out.println(String.format("set指令执行结果:%s", result));
            String value = client.get("key-string");
            System.out.println(String.format("get指令执行结果:%s", value));
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


    private void execute(Jedis client) {

        String result = client.set("key-string", "Hello, Redis!");
        System.out.println(String.format("set指令执行结果:%s", result));

        // 执行get指令
        String value = client.get("key-string");

        System.out.println(String.format("get指令执行结果:%s", value));
    }

    public void dateDiff(String startTime, String endTime, String format) {
        //按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
        long nh = 1000 * 60 * 60;//一小时的毫秒数
        long nm = 1000 * 60;//一分钟的毫秒数
        long ns = 1000;//一秒钟的毫秒数
        long diff;
        try {
            //获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            long day = diff / nd;//计算差多少天
            long hour = diff % nd / nh;//计算差多少小时
            long min = diff % nd % nh / nm;//计算差多少分钟
            long sec = diff % nd % nh % nm / ns;//计算差多少秒
            //输出结果
            //System.out.println("时间相差：" + day + "天" + hour + "小时" + min + "分钟" + sec + "秒。");
            System.out.println(hour + "小时" + min + "分钟" + sec + "秒。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
