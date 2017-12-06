package com.tangshengbo.design.adapter;

/**
 * Created by tangshengbo on 2017/2/19.
 *  首先，你需要实现想转换成的类型接口，也就是你的客户期望看到的接口
 */
public class TurkeyDuck implements Duck {

    private Turkey turkey;

    // 接着，需要取得要适配的对象引用，这里我们引用构造器取得这个引用
    public TurkeyDuck(Turkey turkey) {
        this.turkey = turkey;
    }

    // 现在我们需要实现接口中所有的方法。quack()在类之间的转换很简单，
    // 只要调用gobble()接可以了
    @Override
    public void quack() {
        turkey.gobble();
    }

    // 固然两个接口都具备了fly()方法，火鸡的飞行距离很短，不像鸭子可以长途飞行。
    // 要让鸭子的飞行和火鸡的飞行能够对应，必须连续五次调用火鸡的fly()来完成
    @Override
    public void fly() {
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }
}
