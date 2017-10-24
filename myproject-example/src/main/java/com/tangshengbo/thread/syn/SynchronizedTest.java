package com.tangshengbo.thread.syn;

/**
 * Created by Tangshengbo on 2017/10/24.
 */
public class SynchronizedTest {

    public static void main(String[] args) {
        ObjectService objectService = new ObjectService();
        ObjectService objectService2 = new ObjectService();

        RunA runA = new RunA(objectService);
        Thread threadA = new Thread(runA, "threadA");
        threadA.start();
//
//        RunB runB = new RunB(objectService);
//        Thread threadB = new Thread(runB, "threadB");
//        threadB.start();
    }
}
