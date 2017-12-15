package com.tangshengbo.tutorial.java8.optional;

import java.util.Optional;

/**
 * Created by Tangshengbo on 2017/12/15.
 */
public class Car {

    private Optional<Insurance> insurance;

    public Car(Optional<Insurance> insurance) {
        this.insurance = insurance;
    }

    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}
