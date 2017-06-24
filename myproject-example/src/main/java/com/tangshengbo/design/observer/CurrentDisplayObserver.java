package com.tangshengbo.design.observer;

/**
 * Created by tangshengbo on 2017/2/19.
 */
public class CurrentDisplayObserver implements Observer,Displayer {

    private Weather weather;

    private Subject subject;

    public CurrentDisplayObserver(Subject subject) {
        this.subject = subject;
        this.subject.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println(weather.toString());
    }

    @Override
    public void update(Weather weather) {
        this.weather = weather;
        this.display();
    }

    @Override
    public void update() {

    }
}
