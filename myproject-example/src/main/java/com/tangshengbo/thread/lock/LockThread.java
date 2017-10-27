package com.tangshengbo.thread.lock;

import jodd.util.ThreadUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Tangshengbo on 2017/10/27.
 */
public class LockThread implements Runnable {

    private Integer key = 0;

    // 锁对象
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        testLock();
//        testTryLock();
    }

    private void testLock() {
        // 需要结果是key实现自增长，如果没有同步块，则可能会出现重复key值的现象
        try {
            lock.lockInterruptibly();
//            Thread.currentThread().interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            key++;
            System.out.println(Thread.currentThread().getName() + ":" + key);
            ThreadUtil.sleep(100);
        } finally {
            // 上述代码实现功能与使用sychronized同步代码块一样。
            // sychronized同步代码块或同步方法在代码执行完之后锁自动释放；而用Lock则需要手工释放锁。
            // 为了保证锁最终被释放，释放锁代码放在finally块内。
            lock.unlock();
        }
    }

    public void testTryLock() {
        // 需要结果是key实现自增长，如果没有同步块，则可能会出现重复key值的现象
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + " 得到了锁");
                try {
                    key++;
                    System.out.println(Thread.currentThread().getName() + ":" + key);
                    ThreadUtil.sleep(1000);
                } finally {
                    // 上述代码实现功能与使用sychronized同步代码块一样。
                    // sychronized同步代码块或同步方法在代码执行完之后锁自动释放；而用Lock则需要手工释放锁。
                    // 为了保证锁最终被释放，释放锁代码放在finally块内。
                    System.out.println(Thread.currentThread().getName() + " 释放了锁");
                    lock.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " 获取锁失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LockThread lockThread = new LockThread();

        for (int i = 0; i < 100; i++) {
            new Thread(lockThread, "Thread" + i).start();
        }
    }
}
