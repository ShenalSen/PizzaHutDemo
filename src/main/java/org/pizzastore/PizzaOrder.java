package org.pizzastore;

public class PizzaOrder {
    private Pizza pizza;
    private String size;
    private double price;

    public PizzaOrder(Pizza pizza, String size) {
        this.pizza = pizza;
        this.size = size.toUpperCase();
        this.price = pizza.getPriceBySize(this.size);
    }

    public Pizza getPizza() {
        return pizza;
    }

    public String getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public String getSizeDisplayName() {
        return switch (size) {
            case "L" -> "Large";
            case "M" -> "Medium";
            case "S" -> "Small";
            default -> size;
        };
    }

    @Override
    public String toString() {
        return String.format("#%d %s (%s) - %.2f LKR", 
                pizza.getId(), pizza.getName(), getSizeDisplayName(), price);
    }
}
