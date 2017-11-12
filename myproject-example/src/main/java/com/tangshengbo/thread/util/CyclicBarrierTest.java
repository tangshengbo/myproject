package com.tangshengbo.thread.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by TangShengBo on 2017-11-12.
 */
public class CyclicBarrierTest {

    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(1);
            }
        }).start();
        c.await();
        System.out.println(2);
    }
}
