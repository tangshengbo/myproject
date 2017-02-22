package com.tangshengbo.design.decorate;

/**
 * Created by tangshengbo on 2017/2/19.
 */
public abstract class Beverage {

    // 由每个子类设置，用来描述饮料，如“超优深焙咖啡豆”
    protected String description;

    // 各种调料的布尔值
    private boolean milk;
    private boolean soy;
    private boolean mocha;
    private boolean whip;

    public String getDescription(){
        return description;
    }

    // cost()方法是抽象的，子类必须实现cost()来返回饮料的价格
    // 现在，Beverage类中的cost()不再是一个抽象方法，
    // 我们提供了cost()的实现，让它计算要加入各种饮料的调料价格。
    // 子类仍将覆盖cost()，但是会调用超类的cost()，计算出基本饮料加上调料的价格。
    public double cost() {
        double cost = 0.0;
        if (isMilk()) {
            cost += 1.0;
        }
        if (isMocha()) {
            cost += 2.5;
        }
        if (isSoy()) {
            cost += 1.5;
        }
        if (isWhip()) {
            cost += 2.0;
        }
        return cost;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isMilk() {
        return milk;
    }

    public void setMilk(boolean milk) {
        this.milk = milk;
    }

    public boolean isSoy() {
        return soy;
    }

    public void setSoy(boolean soy) {
        this.soy = soy;
    }

    public boolean isMocha() {
        return mocha;
    }

    public void setMocha(boolean mocha) {
        this.mocha = mocha;
    }

    public boolean isWhip() {
        return whip;
    }

    public void setWhip(boolean whip) {
        this.whip = whip;
    }
}
