package com.tangshengbo.design.observer.number;

import java.util.Random;



/**
 * Created by TangShengBo on 2017-10-15.
 */
public class RandomNumberGenerator extends NumberGenerator {

    private Random random = new Random();

    private int number;

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void execute() {
        for (int i = 0; i < 100; i++) {
            number = random.nextInt(100);
            notifyObservers();
        }
    }
}
