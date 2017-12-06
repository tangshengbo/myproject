package com.tangshengbo.design.observer;

/**
 * Created by tangshengbo on 2017/2/19.
 *  这是主题接口，对象使用此接口注册为观察者，或者把自己从观察者中删除
 */
public interface Subject {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();

    void attach(Observer observer);

    void detach(Observer observer);

    void notifyALl();
}

