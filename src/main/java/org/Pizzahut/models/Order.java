package org.Pizzahut.models;

import java.util.ArrayList;
import java.util.List;

import org.Pizzahut.models.enums.MenuCategory;


public class Order {
    private List<OrderItem> orderItems;
    private double totalAmount;

    // Constructor
    public Order() {
        this.orderItems = new ArrayList<>();
        this.totalAmount = 0.0;
    }
}
