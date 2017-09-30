package com.tangshengbo.design.facade.fund;

/**
 * Created by Tangshengbo on 2017/9/30.
 */
public class FundTest {

    public static void main(String[] args) {
        FundFaced fundFaced = new FundFaced();
        fundFaced.applyForPurchase();
        fundFaced.redemption();
    }
}
