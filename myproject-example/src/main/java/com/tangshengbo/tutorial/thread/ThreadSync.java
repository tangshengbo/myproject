package com.tangshengbo.tutorial.thread;

/**
 * Created by TangShengBo on 2017-07-02.
 */
public class ThreadSync implements Runnable {

    private Timer timer = new Timer();

    @Override
    public void run() {
        timer.add(Thread.currentThread().getName());
        timer.del(Thread.currentThread().getName());
        timer.printNum();
    }
}
