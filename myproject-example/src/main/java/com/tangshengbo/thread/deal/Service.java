package com.tangshengbo.thread.deal;

/**
 * Created by TangShengBo on 2017-10-24.
 */
public class Service {

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void methodA() {
        synchronized (lock1) {
            System.out.println("methodA begin");
            boolean isContinueRun = true;
            while (isContinueRun) {

            }
            System.out.println("methodB end");
        }
    }

    public void methodB() {
        synchronized (lock2) {
            System.out.println("methodB begin");
            System.out.println("methodB end");
        }
    }
}
