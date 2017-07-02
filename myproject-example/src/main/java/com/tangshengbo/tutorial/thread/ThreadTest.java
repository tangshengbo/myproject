package com.tangshengbo.tutorial.thread;

/**
 * Created by TangShengBo on 2017-07-02.
 */
public class ThreadTest {

    public static void main(String[] args) {
        testThreadSync();
    }

    public static void testThreadMethods() {
        Runner runner = new Runner();
        Thread t1 = new Thread(runner, "T1");
        Thread t2 = new Thread(runner, "T2");
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 100; i++) {
            System.out.println("Main run.................." + i);
        }
    }

    public static void testThreadSync() {
        ThreadSync sync = new ThreadSync();
        Thread t1 = new Thread(sync, "T1");
        Thread t2 = new Thread(sync, "T2");
        Thread t3 = new Thread(sync, "T3");
        t1.start();
        t2.start();
        t3.start();
    }

}
