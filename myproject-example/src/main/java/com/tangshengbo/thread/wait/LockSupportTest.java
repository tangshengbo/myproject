package com.tangshengbo.thread.wait;

import jodd.util.ThreadUtil;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by Tangshengbo on 2017/11/15.
 */
public class LockSupportTest {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("LockSupport.park start");
                LockSupport.park();
                System.out.println("LockSupport.park end");
            }
        });
        thread.start();
        ThreadUtil.sleep(10000);
        LockSupport.unpark(thread);
        System.out.println("结束");
    }
}
