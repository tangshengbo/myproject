package com.tangshengbo.thread.queue.eventbus;

import com.tangshengbo.thread.Account;
import jodd.util.ThreadUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by TangShengBo on 2018/5/15.
 */
public class EventBusTest {

    private static final Logger logger = LoggerFactory.getLogger(EventBusTest.class);

    @Test
    public void testEventBus() {
        StringObserver stringObserver = new StringObserver();
        EventBusCenter.register(stringObserver);
        EventBusCenter.register(new IntegerObserver());
        EventBusCenter.register(new DeadEventListener());

        EventBusCenter.post(new Account());
        logger.info("============   start  ====================");
        // 只有注册的参数类型为String的方法会被调用
        for (int i = 0; i < 10000; i++) {
            EventBusCenter.post("post string method " + i);
            EventBusCenter.post(i);
        }
        logger.info("============ after un register ============");
        EventBusCenter.unregister(stringObserver);
        EventBusCenter.post("post string method");
        EventBusCenter.post(123);
        ThreadUtil.sleep(10000);
    }

}
