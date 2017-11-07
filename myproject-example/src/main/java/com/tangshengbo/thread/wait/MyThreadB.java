package com.tangshengbo.thread.wait;

import jodd.util.ThreadUtil;
import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;

/**
 * Created by TangShengBo on 2017-11-07.
 */
public class MyThreadB extends Thread {

    private final Object lock;

    public MyThreadB(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
//        runMethodA();
        runMethodB();
    }

    private void runMethodB() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " wait begin " + DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()));
            ThreadUtil.notify(lock);
            System.out.println(Thread.currentThread().getName() + " wait end " + DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()));
        }
    }

    private void runMethodA() {
        synchronized (lock) {
            for (int i = 0; i < 10; i++) {
                MyList.add();
                if (MyList.size() == 5) {
                    lock.notify();
                    System.out.println("已发出通知!");
                }
                System.out.println("添加了" + (i + 1) + "个元素");
                ThreadUtil.sleep(1000);
            }
        }
    }

    private void runMethodC() {
        synchronized (lock) {
            lock.notify();
        }
    }
}
