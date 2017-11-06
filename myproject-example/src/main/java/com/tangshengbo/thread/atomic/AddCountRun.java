package com.tangshengbo.thread.atomic;

import jodd.util.ThreadUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Tangshengbo on 2017/11/4.
 */
public class AddCountRun implements Runnable {

    private AtomicInteger count = new AtomicInteger(1);

//    private volatile int count;

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            ThreadUtil.sleep(1);
            System.out.println(Thread.currentThread().getName() + ":" + count.getAndIncrement());
//            System.out.println(Thread.currentThread().getName() + ":" + count++);
        }
    }

    public static void main(String[] args) {
        AddCountRun run = new AddCountRun();
        for (int i = 0; i < 5; i++) {
            new Thread(run, "AddCountRun" + i).start();
        }
    }
}
