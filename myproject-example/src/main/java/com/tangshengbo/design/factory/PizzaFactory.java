package com.tangshengbo.design.factory;

/**
 * Created by tangshengbo on 2017/2/19.
 */
public abstract class PizzaFactory {

    public Pizza orderPizza(String type) {
        Pizza pizza;

        pizza = createPizza(type);

        pizza.taste();

        return pizza;
    }

   abstract Pizza createPizza(String type);
}
