package com.tangshengbo.tutorial.thread;

/**
 * Created by TangShengBo on 2017-07-02.
 */
public class Runner implements Runnable {


    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("Runner run................." + Thread.currentThread().getName() + "\t" + i);
        }
    }


}
