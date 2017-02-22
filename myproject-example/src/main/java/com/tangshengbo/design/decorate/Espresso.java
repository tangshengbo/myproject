package com.tangshengbo.design.decorate;

/**
 * Created by tangshengbo on 2017/2/22.
 */
public class Espresso extends Beverage {

    public Espresso(){
        // 饮料的描述。description实例变量继承自Beverage
        description = "Espresso";
    }

    // 浓缩咖啡的价格
    @Override
    public double cost() {
        return 10.1;
    }
}
