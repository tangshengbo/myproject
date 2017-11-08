package com.tangshengbo.thread.condition;

import jodd.util.ThreadUtil;

/**
 * Created by TangShengBo on 2017-11-02.
 */
public class MyRunA implements Runnable {

    private MyService myService;

    public MyRunA(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.await();
    }

    public static void main(String[] args) {
        MyService service = new MyService();
        MyRunA myRunA = new MyRunA(service);
        Thread threadA = new Thread(myRunA, "threadA");
        threadA.start();
        ThreadUtil.sleep(2000);
//        threadA.interrupt();
//        service.signal();
    }
}
