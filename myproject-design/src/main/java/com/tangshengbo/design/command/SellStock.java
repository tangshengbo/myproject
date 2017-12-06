package com.tangshengbo.design.command;

/**
 * Created by tangshengbo on 2017/2/22.
 */
public class SellStock implements Order {

    private Stock stock;

    public SellStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.sell();
    }
}
