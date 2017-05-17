package com.tangshengbo.thread;

/**
 * Created by TangShengBo on 2017-05-16.
 */
public class VolatileTest {

    private static final int THREAD_COUNT = 20;

    public static volatile int race = 0;

    public static void increase() {
        race++;
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (Thread thread : threads) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        increase();
                    }
                }
            });
            thread.start();
        }
        System.out.println(race);
    }

}
