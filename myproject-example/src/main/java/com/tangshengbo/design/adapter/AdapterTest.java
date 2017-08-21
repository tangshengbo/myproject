package com.tangshengbo.design.adapter;

/**
 * Created by tangshengbo on 2017/2/19.
 */
public class AdapterTest {

    public static void main(String[] args) {
        Duck duck = new TurkeyDuck(new WildTurkey());
        duck.quack();
        duck.fly();

        //内存中的对象实际是TurkeyDuck
        if (duck instanceof TurkeyDuck) {
            TurkeyDuck turkeyDuck = (TurkeyDuck) duck;
            System.out.println("instanceof");
        }
    }
}
