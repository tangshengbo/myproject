package com.tangshengbo.design.observer.number;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by TangShengBo on 2017-10-15.
 */
public abstract class NumberGenerator {

    private List<Observer> observers = Lists.newArrayList();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public abstract int getNumber();

    public abstract void execute();
}
