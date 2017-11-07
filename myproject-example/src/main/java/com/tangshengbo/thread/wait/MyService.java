package com.tangshengbo.thread.wait;

import jodd.util.ThreadUtil;
import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;

/**
 * Created by TangShengBo on 2017-11-07.
 */
public class MyService implements Runnable{

    private final Object lock;

    public MyService(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        runMethodA();
    }

    public void runMethodA() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " wait begin " + DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()));
            ThreadUtil.wait(lock);
            System.out.println(Thread.currentThread().getName() + " wait end " + DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()));
        }
    }

    public void runMethodB() {
        synchronized (lock) {
//            lock.notify();
            lock.notifyAll();
        }
    }

    public static void main(String[] args) {
        Object lock = new Object();
        MyService service = new MyService(lock);
        for (int i = 0; i < 5; i++) {
            new Thread(service, "Thread-" + i).start();
        }
        ThreadUtil.sleep(2000);
        service.runMethodB();
    }
}
