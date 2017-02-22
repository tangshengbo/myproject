package com.tangshengbo.design.decorate;

/**
 * Created by tangshengbo on 2017/2/22.
 */
public abstract class CondimentDecorator extends Beverage {

    // 所有的调料装饰者都必须重新实现getDescription()方法
    public abstract String getDescription();
}
