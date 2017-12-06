package com.tangshengbo.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangshengbo on 2017/2/19.
 */
public class WeatherSubject implements Subject {

    // 我们加上一个ArrayList来记录观察者，此ArrayList是在构造器中建立的。
    private List observers;

    private Weather weather;

    public WeatherSubject() {
        observers = new ArrayList();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.update(this.weather);
        }
    }

    // 当从气象站得到更新观测值时，我们通知观察者
    public void measurementsChanged(){
        notifyObservers();
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
        measurementsChanged();
    }

    @Override
    public void attach(Observer observer) {

    }

    @Override
    public void detach(Observer observer) {

    }

    @Override
    public void notifyALl() {

    }
}
