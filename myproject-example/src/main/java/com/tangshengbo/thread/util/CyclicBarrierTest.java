package com.tangshengbo.thread.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by TangShengBo on 2017-11-12.
 */
public class CyclicBarrierTest {

    private static CyclicBarrier c = new CyclicBarrier(3);

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        new Thread(() -> {
            try {
                System.out.println(1);
                c.await();
                System.out.println(Thread.currentThread().getName() + ":结束");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

        }).start();
        new Thread(() -> {
            try {
                System.out.println(2);
                c.await();
                System.out.println(Thread.currentThread().getName() + ":结束");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
        c.await();
        System.out.println(3);
        System.out.println(Thread.currentThread().getName() + ":结束");
    }
}
