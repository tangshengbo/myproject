package com.tangshengbo.thread.syn;

import jodd.util.ThreadUtil;
import org.apache.commons.lang.time.FastDateFormat;

import java.util.Date;
import java.util.Objects;

/**
 * Created by TangShengBo on 2017-10-31.
 */
public class RunE implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if (Objects.isNull(Tools.threadLocal.get())) {
                System.out.println("set(new Date())");
                Tools.threadLocal.set(new Date());
            }
            System.out.println(Thread.currentThread().getName() + " time：" + FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss")
                    .format(Tools.threadLocal.get()));
            ThreadUtil.sleep(2000);
        }
    }

    public static void main(String[] args) {
        RunE run = new RunE();
        Thread threadA = new Thread(run, "threadA");
        threadA.start();
        ThreadUtil.sleep(1000);
        Thread threadB = new Thread(run, "threadB");
        threadB.start();
    }
}
