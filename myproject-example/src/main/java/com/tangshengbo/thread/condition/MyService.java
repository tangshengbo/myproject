package com.tangshengbo.thread.condition;

import org.apache.commons.lang.time.DateUtils;

import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by TangShengBo on 2017-11-02.
 */
public class MyService {

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void await() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 得到了锁");
            System.out.println("await时间为 " + System.currentTimeMillis());
            condition.awaitUntil(DateUtils.addSeconds(new Date(), 5));
//            condition.awaitUninterruptibly();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " 释放了锁");
        }
    }

    public void signal() {
        try {
            lock.lock();
            System.out.println("signal时间为 " + System.currentTimeMillis());
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
