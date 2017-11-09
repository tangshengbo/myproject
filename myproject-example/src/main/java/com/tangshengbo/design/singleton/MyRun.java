package com.tangshengbo.design.singleton;

/**
 * Created by TangShengBo on 2017-11-09.
 */
public class MyRun implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "" + HolderSingleton.getInstance().hashCode());
    }

    public static void main(String[] args) {
        MyRun run = new MyRun();
        for (int i = 0; i < 5; i++) {
            new Thread(run, "Thread-" + (i + 1)).start();
        }
    }
}
