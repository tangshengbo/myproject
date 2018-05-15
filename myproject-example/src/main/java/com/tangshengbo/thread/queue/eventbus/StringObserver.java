package com.tangshengbo.thread.queue.eventbus;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by TangShengBo on 2018/5/15.
 */
public class StringObserver {

    private static final Logger logger = LoggerFactory.getLogger(StringObserver.class);

    /**
     * 只有通过@Subscribe注解的方法才会被注册进EventBus
     * 而且方法有且只能有1个参数
     *
     * @param msg
     */
    @Subscribe
    public void func(String msg) {
        logger.info("String msg: " + msg);
    }
}
