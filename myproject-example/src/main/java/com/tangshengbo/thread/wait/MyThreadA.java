package com.tangshengbo.thread.wait;

import jodd.util.ThreadUtil;
import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;

/**
 * Created by TangShengBo on 2017-11-07.
 */
public class MyThreadA extends Thread {

    private final Object lock;

    public MyThreadA(Object lock) {
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
            ThreadUtil.wait(lock);
            System.out.println(Thread.currentThread().getName() + " wait end " + DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()));
        }
    }

    private void runMethodA() {
        synchronized (lock) {
            if (MyList.size() != 5) {
                System.out.println("wait begin " + DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()));
                ThreadUtil.wait(lock);
                System.out.println("wait end " + DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()));
            }
        }
    }
}
