package com.tangshengbo.test.thread;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test {

    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    private Lock lock = new ReentrantLock();

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) throws InterruptedException {
        final Test test = new Test();

        // new Thread() {
        // public void run() {
        // try {
        // com.tangshengbo.service.insert(Thread.currentThread());
        // } catch (InterruptedException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // };
        // }.start();
        //
        // Thread thread = new Thread() {
        // public void run() {
        // try {
        // com.tangshengbo.service.insert(Thread.currentThread());
        // } catch (InterruptedException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // };
        // };
        // thread.start();
        // thread.sleep(2000);
        // thread.interrupt();
        long start = System.currentTimeMillis(); // 获取开始时间6588ms
        new Thread() {
            public void run() {
                // com.tangshengbo.service.get(Thread.currentThread());
                test.getByLock(Thread.currentThread());
            };

        }.start();

        new Thread() {
            public void run() {
                // com.tangshengbo.service.get(Thread.currentThread());
                test.getByLock(Thread.currentThread());
            };

        }.start();

        Thread.sleep(5000);
        long end = System.currentTimeMillis(); // 获取结束时间
        System.out.println("程序运行时间： " + (end - start) + "ms");

    }

    public void insert(Thread thread) throws InterruptedException {
        try {
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            System.out.println(thread.getName() + "中断");
            return;
        }

        try {
            System.out.println(thread.getName() + "得到了锁");
            for (int i = 0; i < 20; i++) {
                thread.sleep(2000);
                arrayList.add(i);
            }
        } finally {
            System.out.println(thread.getName() + "释放了锁");
            lock.unlock();
        }

    }

    public synchronized void get(Thread thread) {
        for (int i = 0; i < 1000; i++) {
            System.out.println(thread.getName() + "正在进行读操作");
        }
        System.out.println(thread.getName() + "读操作完毕");
    }

    public void getByLock(Thread thread) {
        readWriteLock.readLock().lock();
        try {
            for (int i = 0; i < 200000; i++) {
                System.out.println(thread.getName() + "正在进行读操作");
            }
            System.out.println(thread.getName() + "读操作完毕");

        } finally {
            readWriteLock.readLock().unlock();
        }

        System.out.println("============================================");

        readWriteLock.writeLock().lock();
        try {
            System.out.println(thread.getName() + "得到了锁");
            for (int i = 0; i < 20; i++) {
                arrayList.add(i);
            }
        } finally {
            readWriteLock.writeLock().unlock();
        }


    }

}
