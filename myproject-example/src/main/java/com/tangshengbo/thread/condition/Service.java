package com.tangshengbo.thread.condition;

import jodd.util.ThreadUtil;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by TangShengBo on 2017-11-06.
 */
public class Service {

    private ReentrantLock lock = new ReentrantLock(true);

    private Condition condition = lock.newCondition();

    private boolean hasValue = false;

    public void set() {
        try {
            ThreadUtil.sleep(1000);
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 获取锁");
            while (hasValue) {
                System.out.println(Thread.currentThread().getName() + " await");
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + " 打印A");
            hasValue = true;
            condition.signal();
        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " 释放锁");
        }
    }

    public void get() {
        try {
            ThreadUtil.sleep(1000);
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 获取锁");
            while (!hasValue) {
                System.out.println(Thread.currentThread().getName() + " await");
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + " 打印B");
            hasValue = false;
            condition.signal();
        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " 释放锁");
        }
    }
}
