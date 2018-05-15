package com.tangshengbo.thread.queue.eventbus;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by TangShengBo on 2018/5/15.
 */
public class DeadEventListener {

    private static final Logger logger = LoggerFactory.getLogger(IntegerObserver.class);

    @Subscribe
    public void listen(DeadEvent event) {
        logger.info("{}", event);
    }
}
