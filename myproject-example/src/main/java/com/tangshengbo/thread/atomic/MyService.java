package com.tangshengbo.thread.atomic;

/**
 * Created by Tangshengbo on 2017/11/4.
 */
public class MyService {

    private boolean isContinue = true;

    public void runMethod() {
        while (isContinue) {
            System.out.println("停不下来了!");
        }
        System.out.println("停下来了!");
    }

    public void stopMethod() {
        isContinue = false;
    }
}
