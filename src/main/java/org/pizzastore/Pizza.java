package org.pizzastore;

public class Pizza {
    public static final Pizza COMPLETE_ORDER = new Pizza(-1, "COMPLETE", "Complete Order", 0);

    private int id;
    private String name;
    private String description;
    private double price;

    public Pizza() {}

    public Pizza(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

}
