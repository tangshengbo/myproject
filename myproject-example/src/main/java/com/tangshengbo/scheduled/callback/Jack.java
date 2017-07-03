package com.tangshengbo.scheduled.callback;

import java.util.concurrent.TimeUnit;

/**
 * Created by TangShengBo on 2017-07-03.
 */
public class Jack implements Student {

    private String name;

    public Jack(String name) {
        this.name = name;
    }

    @Override
    public void doWork(CallBack callBack) {
        System.out.println("开始做作业...............");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callBack.advice(name);
    }
}
