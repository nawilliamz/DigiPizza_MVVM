package com.nathan.digipizza;

import java.util.UUID;

public class Order {

    public UUID orderId;
    public String orderItemName;
    public int numberOfOrders;
    public String customerName;
    public int customerId;

    public Order(UUID orderId) {
        this.orderId = orderId;
    }


}
