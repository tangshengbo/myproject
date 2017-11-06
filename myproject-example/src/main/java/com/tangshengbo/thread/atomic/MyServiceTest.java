package com.tangshengbo.thread.atomic;

import jodd.util.ThreadUtil;

/**
 * Created by Tangshengbo on 2017/11/4.
 */
public class MyServiceTest {

    public static void main(String[] args) {
        MyService service = new MyService();

        MyRunA runA = new MyRunA(service);
        MyRunB runB = new MyRunB(service);

        Thread threadA = new Thread(runA, "threadA");
        threadA.start();
        ThreadUtil.sleep(3000);
        Thread threadB = new Thread(runB, "threadB");
        threadB.start();
        System.out.println("已经发出停止命令了!");
    }
}
