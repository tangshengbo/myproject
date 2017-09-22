package com.tangshengbo.design.oop.calc;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Tangshengbo on 2017/9/22.
 */
public class CalcTest {

    public static void main(String[] args) {
        ElectronicBalance electronicBalance = new ElectronicBalance();
        List<Goods> goodses = Lists.newArrayList();
        goodses.add(new Apple());
        goodses.add(new Banana());
        goodses.add(new Water());
        goodses.add(new InstantNoodles());
        electronicBalance.calcPrice(goodses);
    }
}
