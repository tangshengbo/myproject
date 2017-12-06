package com.tangshengbo.design.command.example;

/**
 * Created by Tangshengbo on 2017/9/29.
 */
public class CommandTest {

    public static void main(String[] args) {
        Barbecuer barbecuer = new Barbecuer();

        Command bakeMuttonCommand = new BakeMuttonCommand(barbecuer, "考羊肉");
        Command bakeChickenCommand = new BakeChickenCommand(barbecuer, "考鸡翅");

        Waiter waiter = new Waiter();

        waiter.setOrder(bakeChickenCommand);
        waiter.setOrder(bakeMuttonCommand);
        waiter.setOrder(bakeChickenCommand);
        waiter.setOrder(new BakeBeefCommand(barbecuer, "考牛肉"));
        waiter.cancelOrder(bakeChickenCommand);
        waiter.cancelOrder(bakeMuttonCommand);
        waiter.notifyBarbecuer();
    }
}
