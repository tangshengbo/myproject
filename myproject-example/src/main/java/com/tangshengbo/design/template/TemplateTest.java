package com.tangshengbo.design.template;

/**
 * Created by tangshengbo on 2017/2/19.
 */
public class TemplateTest {

    public static void main(String[] args) {
        CaffeineBeverage caffeineBeverage = new Coffee();
        caffeineBeverage.prepareRecipe();

        caffeineBeverage = new Tea();
        caffeineBeverage.prepareRecipe();
    }
}
