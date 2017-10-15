package com.tangshengbo.design.observer.number;

import java.util.concurrent.TimeUnit;

/**
 * Created by TangShengBo on 2017-10-15.
 */
public class GraphObserver implements Observer {

    @Override
    public void update(NumberGenerator numberGenerator) {
        System.out.print("GraphObserver:");
        int count = numberGenerator.getNumber();
        for (int i = 0; i < count; i++) {
            System.out.print("*");
        }
        System.out.println("");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
