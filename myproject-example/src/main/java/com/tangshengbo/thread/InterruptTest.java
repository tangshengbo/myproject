package com.tangshengbo.thread;

import jodd.util.ThreadUtil;

/**
 * Created by Tangshengbo on 2017/12/22.
 */
public class InterruptTest {

    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + ":开始......");
            ThreadUtil.sleep(5000);
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            System.out.println(Thread.currentThread().getName() + ":结束......");
        };

        Thread thread = new Thread(r, "ThreadA");
        thread.start();
        ThreadUtil.sleep(1000);
        thread.interrupt();
    }
}
