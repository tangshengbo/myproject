package com.tangshengbo.thread.lock;

import jodd.util.ThreadUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by TangShengBo on 2017-10-25.
 */
public class MyService {

    private Lock lock = new ReentrantLock(true);

    public void methodA() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + " 得到了锁");
                try {
                    System.out.println(Thread.currentThread().getName() + " :methodA begin time：" + System.currentTimeMillis());
                    for (int i = 0; i < 5; i++) {
                        ThreadUtil.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + ":" + (i + 1));
                    }
                    System.out.println(Thread.currentThread().getName() + " :methodA end time：" + System.currentTimeMillis());
                } finally {
                    System.out.println(Thread.currentThread().getName() + "释放了锁");
                    lock.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " 获取锁失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void methodB() {
//        lock.lock();
//        try {
//            System.out.println(Thread.currentThread().getName() + " :methodB begin time：" + System.currentTimeMillis());
//            for (int i = 0; i < 5; i++) {
//                ThreadUtil.sleep(1000);
//                System.out.println(Thread.currentThread().getName() + ":" + (i + 1));
//            }
//            System.out.println(Thread.currentThread().getName() + " :methodB end time：" + System.currentTimeMillis());
//        } finally {
//            lock.unlock();
//        }
    }
}
