package com.tangshengbo.design.factory;

/**
 * Created by tangshengbo on 2017/2/19.
 */
public class AmericanPizzaFactory extends PizzaFactory {

    @Override
    public Pizza createPizza(String type) {
        if (type == null) {
            return null;
        }
        if ("cheese".equals(type)) {
            return new AmericanCheesePizza();
        }
        return new AmericanPizza();
    }
}
