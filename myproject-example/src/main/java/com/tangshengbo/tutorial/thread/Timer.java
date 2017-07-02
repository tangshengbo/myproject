package com.tangshengbo.tutorial.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by TangShengBo on 2017-07-02.
 */
public class Timer {

    private static int num = 0;

    public void add(String threadName) {
        synchronized (this) {
            num++;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + "\t你是第" + num + "个线程");
        }
    }

    public synchronized void del(String threadName) {
        //synchronized (this) {
        num++;
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadName + "\t你是第" + num + "个线程");
        // }
    }

    public void printNum() {
        System.out.println("printNum:\t" + num);
    }
}
