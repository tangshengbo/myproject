package com.tangshengbo.design.command;

/**
 * Created by tangshengbo on 2017/2/22.
 */
public class CommandTest {

    public static void main(String[] args) {
        Stock stock = new Stock("ABC", 10);
        Broker broker = new Broker();

        BuyStock buyStock = new BuyStock(stock);
        broker.takeOrder(buyStock);

        SellStock sellStock = new SellStock(stock);
        broker.takeOrder(sellStock);

        broker.placeOrders();
    }
}
