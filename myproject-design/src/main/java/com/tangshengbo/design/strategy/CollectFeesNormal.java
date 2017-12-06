package com.tangshengbo.design.strategy;

/**
 * Created by Tangshengbo on 2017/9/30.
 */
public class CollectFeesNormal implements CollectFees {

    @Override
    public double acceptCash(double money) {
        return money;
    }
}
