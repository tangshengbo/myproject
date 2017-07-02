package com.tangshengbo.tutorial.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by TangShengBo on 2017-07-02.
 */
public class Producer implements Runnable {

    private BreadStack box = null;

    public Producer(BreadStack box) {
        this.box = box;
    }

    @Override
    public void run() {
        Bread bread;
        for (int i = 0; i < 20; i++) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bread = new Bread(i);
            box.push(bread);
            System.out.println(Thread.currentThread().getName() + "\t生产了" + bread.toString());
        }
    }
}
