package com.tangshengbo.design.oop.calc;

import java.util.List;

/**
 * Created by Tangshengbo on 2017/9/22.
 */
public class ElectronicBalance {

    public void calcPrice(List<Goods> goodses) {
        int total = 0;
        for (Goods goods : goodses) {
            total += goods.getPrice();
        }
        System.out.println(total);
    }
}
