package com.tangshengbo.thread.wait;

import jodd.util.ThreadUtil;

/**
 * Created by TangShengBo on 2017-11-07.
 */
public class WaitTest {

    public static void main(String[] args) {
        Object lock = new Object();
        MyThreadA threadA = new MyThreadA(lock);
        MyThreadB threadB = new MyThreadB(lock);
        threadA.start();
        ThreadUtil.sleep(2000);
        threadB.start();
    }
}
