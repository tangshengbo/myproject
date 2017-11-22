package com.tangshengbo.design.oop.dip;

/**
 * Created by TangShengBo on 2017-10-22.
 */
public class GeneralDriver implements Driver {

    @Override
    public void driver(Car car) {
        car.run();
    }
}
