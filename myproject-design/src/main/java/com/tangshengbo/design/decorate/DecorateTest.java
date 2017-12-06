package com.tangshengbo.design.decorate;

/**
 * Created by tangshengbo on 2017/2/22.
 */
public class DecorateTest {

    public static void main(String[] args) {
        // 订一杯Espresso，不需要调料，打印出它的描述与价钱
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $:" + beverage.cost());
        // 制造出一个DarkRoast对象
        Beverage darkRoast = new DarkRoast();
        darkRoast = new Mocha(darkRoast);
        System.out.println(darkRoast.getDescription() + " $:" + darkRoast.cost());

        darkRoast = new Milk(darkRoast);
        System.out.println(darkRoast.getDescription() + " $:" + darkRoast.cost());

        darkRoast = new Whip(darkRoast);
        System.out.println(darkRoast.getDescription() + " $:" + darkRoast.cost());
    }

}
