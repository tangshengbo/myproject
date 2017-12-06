package com.tangshengbo.design.observer;

/**
 * Created by TangShengBo on 2017-05-17.
 */
public class NBAObserver implements Observer {

    @Override
    public void update(Weather weather) {

    }

    @Override
    public void update() {
        System.out.println("关闭NBA，继续工作..............");
    }
}
