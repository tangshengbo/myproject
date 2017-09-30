package com.tangshengbo.design.facade.fund;

import java.util.Date;

/**
 * Created by Tangshengbo on 2017/9/30.
 */
public class StockB extends AbstractStock {

    public StockB(String name) {
        super(name);
    }

    @Override
    public void buy() {
        System.out.println(String.format("购买%s %tF", name, new Date()));
    }

    @Override
    public void sell() {
        System.out.println(String.format("卖出%s %tF", name, new Date()));
    }
}
