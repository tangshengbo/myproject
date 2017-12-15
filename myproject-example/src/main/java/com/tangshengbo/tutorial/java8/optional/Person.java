package com.tangshengbo.tutorial.java8.optional;

import java.util.Optional;

/**
 * Created by Tangshengbo on 2017/12/15.
 */
public class Person {

    private Optional<Car> car;

    public Person(Optional<Car> car) {
        this.car = car;
    }

    public Optional<Car> getCar() {
        return car;
    }
}
