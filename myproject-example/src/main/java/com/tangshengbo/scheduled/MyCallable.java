package com.tangshengbo.scheduled;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tang on 2017/6/29.
 */
public class MyCallable  implements Callable<Integer> {

    private int num;

    public MyCallable(int num) {
        this.num = num;
    }

    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        for (int i = 0; i < Math.random() * 100; i++) {
            num++;
        }
        System.out.println(Thread.currentThread().getName() + "\t" + num);
        return num;
    }
}
