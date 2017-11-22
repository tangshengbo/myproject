package com.tangshengbo.design.oop.calc;

import com.google.common.collect.Lists;
import com.tangshengbo.design.oop.calc.impl.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tangshengbo on 2017/9/22.
 */
public class CalcTest {

    public static void main(String[] args) {
//        calcPrice();

        upperBoundsWildcards();
        lowerBoundsWildcards();

    }

    private static void calcPrice() {
        ElectronicBalance electronicBalance = new ElectronicBalance();
        List<Goods> goodses = Lists.newArrayList();
        goodses.add(new Apple());
        goodses.add(new Banana());
        goodses.add(new Peach());
        goodses.add(new YibaoWater());
        goodses.add(new KsfInstantNoodles());
        electronicBalance.calcPrice(goodses);
    }

    /**
     * 上界通配符 Plate<？ extends Fruit>
     */
    private static void upperBoundsWildcards() {
        List<? extends Fruit> flist = new ArrayList<>();
        // complie error:
        // flist.add(new Apple());
        // flist.add(new Fruit());
        // flist.add(new Object());
        flist.add(null); // only work for null
        Fruit fruit = flist.get(0);
        Apple apple = (Apple)flist.get(0);
        System.out.println(apple);
        flist.contains(new Banana());
        flist.contains(new Apple());
    }

    /**
     * 下界通配符
     * Plate<？ super Fruit>
     */
    private static void lowerBoundsWildcards() {
        Plate<? super Fruit> plate = new Plate<>(new Apple());
        plate.set(new Banana());
        System.out.println(plate.get());

    }
}
