package com.tangshengbo.thread.lock;

import jodd.util.ThreadUtil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Tangshengbo on 2017/10/27.
 */
public class LockInterruptThread implements Runnable {

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            lock.lockInterruptibly();
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + " 中断了锁");
        }
        try {
            System.out.println(Thread.currentThread().getName() + " 得到了锁");
            ThreadUtil.sleep(2000);
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " 释放了锁");
        }
    }

    public static void main(String[] args) {
        LockInterruptThread interruptThread = new LockInterruptThread();
        Thread threadA = new Thread(interruptThread, "threadA");
        Thread threadB = new Thread(interruptThread, "ThreadB");
        threadA.start();
        ThreadUtil.sleep(5000);
        threadB.start();
        threadB.interrupt();
    }
}
