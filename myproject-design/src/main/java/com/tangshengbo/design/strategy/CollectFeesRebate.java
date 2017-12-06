package com.tangshengbo.design.strategy;

/**
 * Created by Tangshengbo on 2017/9/30.
 */
public class CollectFeesRebate implements CollectFees {

    private double moneyRebate;

    public CollectFeesRebate(double moneyRebate) {
        this.moneyRebate = moneyRebate;
    }

    @Override
    public double acceptCash(double money) {
        return money * moneyRebate;
    }
}
