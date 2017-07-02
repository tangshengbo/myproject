package com.tangshengbo.tutorial.thread;

/**
 * Created by TangShengBo on 2017-07-02.
 */
public class Consumer implements Runnable {

    private BreadStack box;

    public Consumer(BreadStack box) {
        this.box = box;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            Bread bread = box.pop();
            System.out.println(Thread.currentThread().getName() + "\t消费了:" +bread.toString());
        }
    }
}
