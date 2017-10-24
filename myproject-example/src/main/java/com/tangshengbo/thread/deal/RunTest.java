package com.tangshengbo.thread.deal;

/**
 * Created by TangShengBo on 2017-10-24.
 */
public class RunTest {

    public static void main(String[] args) {
        Service service = new Service();
        ThreadA threadA = new ThreadA(service);
        threadA.setName("threadA");
        threadA.start();

        ThreadB threadB = new ThreadB(service);
        threadA.setName("threadB");
        threadB.start();
    }
}
