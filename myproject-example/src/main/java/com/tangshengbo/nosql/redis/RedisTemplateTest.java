package com.tangshengbo.nosql.redis;

import jodd.util.ThreadUtil;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StopWatch;

/**
 * Created by Tangshengbo on 2018/4/24.
 */
public class RedisTemplateTest {

    private static final Logger logger = LoggerFactory.getLogger(RedisTemplateTest.class);

    private RedisTemplate<String, Object> redisTemplate;
    @Before
    public void init() {
        redisTemplate = new RedisTemplate<>();
        redisConfig();
    }

    @Test
    public void testQuery() {
        StopWatch watch = new StopWatch();
        watch.start();
        for (int i = 0; i < 20000; i++) {
            Object value = redisTemplate.opsForValue().get(String.valueOf(i));
            logger.info("{}", value);
        }
        watch.stop();
//        redisTemplate.convertAndSend();
        logger.info("{}", watch.prettyPrint());
    }

    @Test
    public void testPublish() {
        for (int i = 0; i < 100000; i++) {
            redisTemplate.convertAndSend("topic.channel", "Redis" + i);
        }
        ThreadUtil.sleep(100000);
    }


    private void redisConfig() {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setHostName("localhost");
        connectionFactory.setPort(6379);
        connectionFactory.setUsePool(true);
        connectionFactory.setPoolConfig(JedisUtil.getDefaultPoolConfig());
        connectionFactory.afterPropertiesSet();
        redisTemplate.setConnectionFactory(connectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
    }
}
