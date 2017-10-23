package com.tangshengbo.design.strategy;

/**
 * Created by Tangshengbo on 2017/9/30.
 */
public class CollectFeesContext {

    private CollectFees collectFees;

    public CollectFeesContext(CollectFees collectFees) {
        this.collectFees = collectFees;
    }

    public double acceptCash(double money) {
        return collectFees.acceptCash(money);
    }
}
