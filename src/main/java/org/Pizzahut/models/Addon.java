package org.Pizzahut.models;

import org.Pizzahut.models.enums.MenuCategory;

public class Addon {
    private int id;
    private String name;
    private double price;
    private MenuCategory category;

    
    public Addon() {}

    
    public Addon(int id, String name, double price, MenuCategory category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

}
