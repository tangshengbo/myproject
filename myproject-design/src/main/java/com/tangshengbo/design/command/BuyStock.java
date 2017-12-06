package com.tangshengbo.design.command;

/**
 * Created by tangshengbo on 2017/2/22.
 */
public class BuyStock implements Order {

    private Stock stock;

    public BuyStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.buy();
    }
}
