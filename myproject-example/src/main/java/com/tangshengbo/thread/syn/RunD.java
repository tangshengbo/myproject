package com.tangshengbo.thread.syn;

import jodd.util.StringUtil;
import jodd.util.SystemUtil;
import jodd.util.ThreadUtil;

import java.util.concurrent.TimeUnit;

/**
 * Created by Tangshengbo on 2017/10/25.
 */
public class RunD implements Runnable {

    private int num = 100;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public synchronized void methodA() {
        System.out.println(Thread.currentThread().getName() + " begin time：" + System.currentTimeMillis());
        num = 1000;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " end time：" + System.currentTimeMillis());
    }

    public synchronized void methodB() {
        System.out.println(Thread.currentThread().getName() + " begin time：" + System.currentTimeMillis());
        num = 2000;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " end time：" + System.currentTimeMillis());
    }

    @Override
    public void run() {
        methodA();
    }

    public static void main(String[] args) {
//        String string = "3242*23432";
//        try {
//            System.out.println(string.split("*"));
//        } catch (Exception e) {
//            throw new IllegalArgumentException("非法参数异常", e);
//        }
        RunD runD = new RunD();
        Thread threadA = new Thread(runD, "threadA");
        threadA.start();
        ThreadUtil.join(threadA);
        runD.methodB();
        ThreadUtil.sleep(5000);
        System.out.println(Thread.currentThread().getName() + "\t:" + runD.getNum());
    }
}
