package com.tangshengbo.design.observer.number;

/**
 * Created by TangShengBo on 2017-10-15.
 */
public class ObserverTest {

    public static void main(String[] args) {
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        Observer digitObserver = new DigitObserver();
        Observer graphObserver = new GraphObserver();
        Observer frameObserver = new FrameObserver();
//        numberGenerator.addObserver(digitObserver);
//        numberGenerator.addObserver(graphObserver);
//        numberGenerator.execute();

        numberGenerator = new IncrementalNumberGenerator(10, 50, 5);
        numberGenerator.addObserver(digitObserver);
        numberGenerator.addObserver(graphObserver);
        numberGenerator.addObserver(frameObserver);
        numberGenerator.execute();
    }
}
