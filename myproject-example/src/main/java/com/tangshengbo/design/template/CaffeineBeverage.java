package com.tangshengbo.design.template;

/**
 * Created by tangshengbo on 2017/2/19.
 */
public abstract class CaffeineBeverage {

    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    // 煮沸水
    public  void boilWater() {
        System.out.println("Boiling water");
    }

    //把饮料倒进杯子里
    public void pourInCup() {
        System.out.println("Pouring into cup");
    }

    // 因为咖啡和茶处理这些方法的做法不同，所以这两个方法必须被声明为抽象，
    // 剩余的东西留给子类去操心
    abstract void addCondiments();

    abstract void brew();
}
