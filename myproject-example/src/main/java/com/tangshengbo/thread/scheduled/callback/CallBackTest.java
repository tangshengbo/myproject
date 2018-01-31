package com.tangshengbo.thread.scheduled.callback;

import jodd.util.ThreadUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by TangShengBo on 2017-07-03.
 */
public class CallBackTest {

    private static final Logger logger = LoggerFactory.getLogger(CallBackTest.class);

    public static void main(String[] args) {
        Teacher teacher = new Teacher(new Jack("Jack"));
        Thread thread = new Thread(teacher);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("continue..............");
    }

    @Test
    public void testDigest() {
        InstanceCallBack instanceCallBack = new InstanceCallBack("E:/http.java");
        instanceCallBack.calculateDigest();
        logger.info("{}", "异步做其他操作");
        ThreadUtil.sleep(10000);
    }
}
