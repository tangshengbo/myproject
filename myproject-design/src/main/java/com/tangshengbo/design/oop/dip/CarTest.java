package com.tangshengbo.design.oop.dip;

/**
 * Created by TangShengBo on 2017-10-22.
 */
public class CarTest {

    public static void main(String[] args) {
        Driver driver = new GeneralDriver();
        Car benzCar = new BenzCar();
        driver.driver(benzCar);
        Car bmwCat = new BMWCat();
        driver.driver(bmwCat);
    }
}
