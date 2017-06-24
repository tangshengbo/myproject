package com.tangshengbo.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TangShengBo on 2017-05-17.
 */
public class BossSubject implements Subject {

    // 我们加上一个ArrayList来记录观察者，此ArrayList是在构造器中建立的。
    private List<Observer> observers;

    public BossSubject() {
        observers = new ArrayList<>();
    }


    @Override
    public void registerObserver(Observer observer) {

    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObservers() {

    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        if (observers.contains(observers)) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyALl() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
