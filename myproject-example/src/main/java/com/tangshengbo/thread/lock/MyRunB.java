package com.tangshengbo.thread.lock;

/**
 * Created by TangShengBo on 2017-10-25.
 */
public class MyRunB implements Runnable {

    private MyService myService;

    public MyRunB(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.methodB();
    }
}
