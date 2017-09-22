package com.tangshengbo.design.oop.calc;

import com.google.common.collect.Lists;
import com.tangshengbo.design.oop.calc.impl.*;

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
        goodses.add(new Peach());
        goodses.add(new YibaoWater());
        goodses.add(new KsfInstantNoodles());
        electronicBalance.calcPrice(goodses);
    }
}
