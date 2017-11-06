package com.tangshengbo.thread.condition;

/**
 * Created by TangShengBo on 2017-11-06.
 */
public class ConditionTest {

    public static void main(String[] args) {
        Service service = new Service();
        MyThreadA threadA = new MyThreadA(service);
        MyThreadB threadB = new MyThreadB(service);
        threadA.start();
        threadB.start();
    }
}
