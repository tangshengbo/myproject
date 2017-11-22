package com.tangshengbo.thread.scheduled.callback;

import java.util.concurrent.TimeUnit;

/**
 * Created by TangShengBo on 2017-07-03.
 */
public class CallBackTest {

    public static void main(String[] args) {
        Teacher teacher = new Teacher(new Jack("Jack"));
        Thread thread = new Thread(teacher);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("continue..............");
    }
}
