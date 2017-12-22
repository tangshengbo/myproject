package com.tangshengbo.design.factory.car;

import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * Created by Tangshengbo on 2017/12/22.
 */
public class CarFactory {

    public static Car createCar(Class<? extends Car> c) {
        Car car = null;
        try {
            car = c.newInstance();
        } catch (ReflectiveOperationException e) {
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
        return car;
    }

    public static void main(String[] args) {
        Car car = CarFactory.createCar(BMWCar.class);
        car.run();
    }
}
