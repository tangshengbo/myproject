package com.tangshengbo.thread.lock;

/**
 * Created by TangShengBo on 2017-10-25.
 */
public class RunTest {

    public static void main(String[] args) {
        MyService myService = new MyService();
        MyRunA runA = new MyRunA(myService);
        MyRunB runB = new MyRunB(myService);
        Thread threadA = new Thread(runA, "threadA");
        threadA.start();
        Thread threadB = new Thread(runA, "threadB");
        threadB.start();
        Thread threadC = new Thread(runB, "threadC");
        threadC.start();
        Thread threadD = new Thread(runB, "threadC");
        threadD.start();
    }
}
