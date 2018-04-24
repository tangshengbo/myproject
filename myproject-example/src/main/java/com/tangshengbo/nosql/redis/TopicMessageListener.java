package com.tangshengbo.nosql.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;



/**
 * Created by Tangshengbo on 2018/4/24.
 */
public class TopicMessageListener implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(TopicMessageListener.class);

    private RedisTemplate redisTemplate;

    public TopicMessageListener() {
    }

    public TopicMessageListener(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        logger.info("接收到消息{}", message);
    }
}
