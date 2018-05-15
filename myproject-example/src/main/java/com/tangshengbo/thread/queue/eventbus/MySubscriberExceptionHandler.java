package com.tangshengbo.thread.queue.eventbus;

import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by TangShengBo on 2018/5/15.
 */
public class MySubscriberExceptionHandler implements SubscriberExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(MySubscriberExceptionHandler.class);

    @Override
    public void handleException(Throwable exception, SubscriberExceptionContext context) {
        logger.info("{}", ExceptionUtils.getStackTrace(exception));
        try {
            logger.info("{}", context.getSubscriberMethod());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
