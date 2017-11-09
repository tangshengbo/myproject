package com.tangshengbo.thread.condition;

import jodd.util.ThreadUtil;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by TangShengBo on 2017-11-09.
 */
public class MyRunB {

    private volatile static int nextPrintWho = 1;

    private static ReentrantLock lock = new ReentrantLock();

    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();


    public static void main(String[] args) {
        Thread threadA = new Thread() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    while (nextPrintWho != 1) {
                        conditionA.await();
                    }
                    for (int i = 0; i < 3; i++) {
                        System.out.println("threadA " + (i + 1));
                    }
                    nextPrintWho = 2;
                    conditionB.signalAll();
                } catch (InterruptedException e) {
                    System.out.println(e);
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread threadB = new Thread() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    while (nextPrintWho != 2) {
                        conditionB.await();
                    }
                    for (int i = 0; i < 3; i++) {
                        System.out.println("threadB " + (i + 1));
                    }
                    nextPrintWho = 3;
                    conditionC.signalAll();
                } catch (InterruptedException e) {
                    System.out.println(e);
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread threadC = new Thread() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    while (nextPrintWho != 3) {
                        conditionA.await();
                    }
                    for (int i = 0; i < 3; i++) {
                        System.out.println("threadC " + (i + 1));
                    }
                    nextPrintWho = 1;
                    conditionA.signalAll();
                } catch (InterruptedException e) {
                    System.out.println(e);
                } finally {
                    lock.unlock();
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(threadA).start();
            ThreadUtil.sleep(2000);
            new Thread(threadB).start();
            ThreadUtil.sleep(2000);
            new Thread(threadC).start();
            ThreadUtil.sleep(2000);
        }
    }
}
