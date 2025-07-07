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

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public MenuCategory getCategory() {
        return category;
    }

    public void setCategory(MenuCategory category) {
        this.category = category;
    }

    
    public String getFormattedPrice() {
        return String.format("%.2f LKR", price);
    }

    public boolean isApplicableToCategory(MenuCategory targetCategory) {
        return this.category == targetCategory;
    }

    // UI diplay methods
    @Override
    public String toString() {
        return "#" + id + " " + name + " - " + getFormattedPrice();
    }

    public String getDisplayName() {
        return name + " (+" + getFormattedPrice() + ")";
    }

    // For order receipt display
    public String getReceiptFormat() {
        return "- " + name + " (+" + getFormattedPrice() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Addon addon = (Addon) obj;
        return id == addon.id && category == addon.category;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id) + (category != null ? category.hashCode() : 0);
    }
}
