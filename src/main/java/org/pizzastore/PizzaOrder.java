package org.pizzastore;

import java.util.ArrayList;
import java.util.List;

public class PizzaOrder {
    private Pizza pizza;
    private String size;
    private List<Addon> addons;
    private double price;

    public PizzaOrder(Pizza pizza, String size) {
        this.pizza = pizza;
        this.size = size.toUpperCase();
        this.addons = new ArrayList<>();
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

    public List<Addon> getAddons() {
        return addons;
    }

    public void addAddon(Addon addon) {
        addons.add(addon);
        price += addon.getPrice();
    }

    public double getAddonsTotal() {
        return addons.stream().mapToDouble(Addon::getPrice).sum();
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
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("#%d %s (%s) - %.2f LKR", 
                pizza.getId(), pizza.getName(), getSizeDisplayName(), pizza.getPriceBySize(size)));
        
        if (!addons.isEmpty()) {
            sb.append("\n   Addons:");
            for (Addon addon : addons) {
                sb.append(String.format("\n   - %s (+%.2f LKR)", addon.getName(), addon.getPrice()));
            }
        }
        
        sb.append(String.format("\n   Order Total: %.2f LKR", price));
        return sb.toString();
    }
}
