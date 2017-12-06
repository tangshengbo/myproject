package com.tangshengbo.design.observer.number;

import java.util.concurrent.TimeUnit;

/**
 * Created by TangShengBo on 2017-10-15.
 */
public class DigitObserver implements Observer {

    @Override
    public void update(NumberGenerator numberGenerator) {
        System.out.println(String.format("DigitObserver: %s", numberGenerator.getNumber()));
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
