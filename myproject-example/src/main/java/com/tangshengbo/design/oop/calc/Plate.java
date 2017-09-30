package com.tangshengbo.design.oop.calc;

/**
 * Created by Tangshengbo on 2017/9/30.
 */
public class Plate<T> {

    private T item;

    public Plate(T t) {
        item = t;
    }

    public void set(T t) {
        item = t;
    }

    public T get() {
        return item;
    }
}
