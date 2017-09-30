package com.tangshengbo.design.facade.fund;

/**
 * Created by Tangshengbo on 2017/9/30.
 */
public class StockTest {

    public static void main(String[] args) {
        Stock stockA = new StockA("A股");
        Stock stockB = new StockB("B股");
        Stock stockC = new StockC("C股");

        stockA.buy();
        stockA.sell();

        stockB.buy();
        stockB.sell();

        stockC.buy();
        stockC.sell();
    }
}
