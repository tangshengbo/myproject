package com.tangshengbo.design.factory;

/**
 * Created by tangshengbo on 2017/2/19.
 */
public class FactoryMethodTest {

    public  static void main(String[] args) {
        PizzaFactory pizzaFactory = new ChinesePizzaFactory();
        pizzaFactory.orderPizza("hotdog");

        pizzaFactory = new AmericanPizzaFactory();
        pizzaFactory.orderPizza("cheese");
    }
}
