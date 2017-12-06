package com.tangshengbo.design.singleton;

import jodd.util.ObjectUtil;
import jodd.util.ThreadUtil;

import java.io.IOException;

/**
 * Created by TangShengBo on 2017-11-09.
 */
public class MyRun implements Runnable {

    @Override
    public synchronized void run() {
//        System.out.println(Thread.currentThread().getName() + "" + EnumSingleton.INSTANCE.hashCode());
        try {
            System.out.println(Thread.currentThread().getName() + "-begin:" +  HolderSingleton.getInstance().hashCode());
            ObjectUtil.writeObject("E:/cas.txt", HolderSingleton.getInstance());
            ThreadUtil.sleep(1000);
            HolderSingleton casSingleton = (HolderSingleton) ObjectUtil.readObject("E:/cas.txt");
            System.out.println(Thread.currentThread().getName() + "-end:" + casSingleton.hashCode());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyRun run = new MyRun();
        for (int i = 0; i < 5; i++) {
            new Thread(run, "Thread-" + (i + 1)).start();
        }
    }
}
