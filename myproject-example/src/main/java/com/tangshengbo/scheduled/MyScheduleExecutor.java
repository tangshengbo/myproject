package com.tangshengbo.scheduled;

import java.util.concurrent.TimeUnit;

/**
 * Created by Tang on 2017/6/29.
 */
public class MyScheduleExecutor implements Runnable {

    private String jobName;

    public MyScheduleExecutor(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(jobName +":\tis Running........." + Thread.currentThread().getName());
    }
}
