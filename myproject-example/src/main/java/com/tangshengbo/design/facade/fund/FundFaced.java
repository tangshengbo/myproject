package com.tangshengbo.design.facade.fund;

/**
 * Created by Tangshengbo on 2017/9/30.
 */
public class FundFaced {

    private Stock stockA;
    private Stock stockB;
    private Stock stockC;

    public FundFaced() {
        stockA = new StockA("A股");
        stockB = new StockB("B股");
        stockC = new StockC("C股");
    }

    /**
     * 申购
     */
    public void applyForPurchase() {
        stockA.buy();
        stockB.buy();
        stockC.buy();

    }

    /**
     * 赎回
     */
    public void redemption() {
        stockA.sell();
        stockB.sell();
        stockC.sell();
    }

}
