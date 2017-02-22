package com.tangshengbo.design.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangshengbo on 2017/2/22.
 */
public class Broker {

    private List<Order> orders = new ArrayList<>();

    public void takeOrder(Order order) {
        if (order == null) {
            return;
        }
        orders.add(order);
    }

    public void placeOrders() {
        for (Order order : orders) {
            order.execute();
        }
        orders.clear();
    }
}
