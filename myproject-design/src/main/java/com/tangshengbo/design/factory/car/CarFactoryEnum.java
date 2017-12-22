package com.tangshengbo.design.factory.car;

/**
 * Created by Tangshengbo on 2017/12/22.
 */
public enum CarFactoryEnum {

    BenzCar,
    BMWCar;

    public Car create() {
        switch (this) {
            case BenzCar:
                return new BenzCar();
            case BMWCar:
                return new BMWCar();
            default:
                throw new AssertionError("无效参数");
        }
    }

    public static void main(String[] args) {
        CarFactoryEnum.BenzCar.create().run();
        CarFactoryEnum.BMWCar.create().run();
    }
}
