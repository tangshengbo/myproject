package com.tangshengbo.design.singleton;

import jodd.util.ObjectUtil;
import jodd.util.ObjectXmlUtil;
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
            ObjectXmlUtil.writeObjectAsXml("E:/cas.xml",  CASSingleton.getInstance());
            System.out.println("begin:" + Thread.currentThread().getName() + "" +  CASSingleton.getInstance().hashCode());
            ObjectUtil.writeObject("E:/cas.txt", CASSingleton.getInstance());
            ThreadUtil.sleep(1000);
            CASSingleton casSingleton = (CASSingleton) ObjectUtil.readObject("E:/cas.txt");
            System.out.println("end:" + Thread.currentThread().getName() + "" + casSingleton.hashCode());
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
