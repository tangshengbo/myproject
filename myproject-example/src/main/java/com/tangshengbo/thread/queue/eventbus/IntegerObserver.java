package com.tangshengbo.thread.queue.eventbus;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by TangShengBo on 2018/5/15.
 */
public class IntegerObserver {

    private static final Logger logger = LoggerFactory.getLogger(IntegerObserver.class);

    /**
     * post() 不支持自动装箱功能,只能使用Integer,不能使用int,否则handlersByType的Class会是int而不是Intege
     * 而传入的int msg参数在post(int msg)的时候会被包装成Integer,导致无法匹配到
     */
    @AllowConcurrentEvents
    @Subscribe
    public void func(Integer msg) {
        if (msg.equals(9999)) {
            throw new IllegalArgumentException("");
        }
        logger.info("Integer msg: " + msg);
    }
}
