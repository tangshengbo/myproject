package com.tangshengbo.thread.lock;

import jodd.util.ThreadUtil;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Tangshengbo on 2017/10/27.
 */
public class LockReadWriteThread implements Runnable {

    // 数据存放
    private StringBuilder sb = new StringBuilder();

    // 锁对象
    private ReadWriteLock rwl = new ReentrantReadWriteLock(true);

    @Override
    public void run() {
        if (Thread.currentThread().getName().startsWith("Read")) {
            rwl.readLock().lock();// 取到读锁
            try {
                System.out.println(Thread.currentThread().getName() + " 正在读取...");
                ThreadUtil.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " 结果:" + sb.toString());
            } finally {
                rwl.readLock().unlock();// 释放读锁
            }
        } else if (Thread.currentThread().getName().startsWith("Write")) {
            rwl.writeLock().lock();// 取到写锁
            try {
                System.out.println(Thread.currentThread().getName() + " 正在写入...");
                ThreadUtil.sleep(500);
                String writeData = "" + new Random().nextInt(100);
                sb.append(writeData.concat(","));
                System.out.println(Thread.currentThread().getName() + " 结果:" + writeData);
            } finally {
                rwl.writeLock().unlock();// 释放写锁
            }
        }
    }

    public static void main(String[] args) {
//        testReadWriteLock();
        testReadWriteCache();
    }

    private static void testReadWriteCache() {
        MyService service = new MyService();
        MyRunD read = new MyRunD(service);
        MyRunE write = new MyRunE(service);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            executorService.execute(read);
            executorService.submit(write);
        }
    }

    private static void testReadWriteLock() {
        LockReadWriteThread lt = new LockReadWriteThread();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(lt, "Write" + i);
            t.start();
        }
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(lt, "Read" + i);
            t.start();
        }
    }
}
