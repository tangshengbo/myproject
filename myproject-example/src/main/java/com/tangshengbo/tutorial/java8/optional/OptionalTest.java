package com.tangshengbo.tutorial.java8.optional;


import java.util.Optional;

/**
 * Created by Tangshengbo on 2017/12/15.
 */
public class OptionalTest {

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public static void main(String[] args) {
        Insurance insurance = null;
        Optional<Insurance> insuranceOptional = Optional.empty();
        Car car = new Car(insuranceOptional);
        Person person = new Person(Optional.of(car));
        System.out.println("OK");
    }
}
