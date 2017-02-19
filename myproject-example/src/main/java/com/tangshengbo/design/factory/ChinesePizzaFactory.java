package com.tangshengbo.design.factory;

/**
 * Created by tangshengbo on 2017/2/19.
 */
public class ChinesePizzaFactory extends PizzaFactory {

    @Override
    public Pizza createPizza(String type) {
        if (type == null) {
            return null;
        }
        if ("hotdog".equals(type)) {
            return new ChineseHotdogPizza();
        }
        return new ChinesePizza();
    }
}
