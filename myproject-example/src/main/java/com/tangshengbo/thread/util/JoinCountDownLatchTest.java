package com.tangshengbo.thread.util;

import jodd.util.ThreadUtil;

import java.util.concurrent.CountDownLatch;

/**
 * Created by TangShengBo on 2017-11-12.
 */
public class JoinCountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
//        join();
        countDownLatch();

    }

    private static void countDownLatch() throws InterruptedException {
        CountDownLatch c = new CountDownLatch(2);
        new Thread(() -> {
            ThreadUtil.sleep(2000);
            System.out.println(1);
            c.countDown();
            System.out.println(Thread.currentThread().getName() + ":结束");
        }).start();
        new Thread(() -> {
            ThreadUtil.sleep(2000);
            System.out.println(2);
            c.countDown();
            System.out.println(Thread.currentThread().getName() + ":结束");
        }).start();
        c.await();
        System.out.println("3");
    }

    private static void join() throws InterruptedException {
        Thread parser1 = new Thread(() -> {
            ThreadUtil.sleep(1000);
            System.out.println("parser1 finish");
        });
        Thread parser2 = new Thread(() -> {
            System.out.println("parser2 finish");
        });
        parser1.start();
        parser2.start();
        parser1.join();
        parser2.join();
        System.out.println("all parser finish");
    }
}
