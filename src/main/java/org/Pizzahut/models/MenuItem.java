package org.Pizzahut.models;

import org.Pizzahut.models.enums.MenuCategory;
import java.util.Map;
import java.util.HashMap;

public class MenuItem {
    private int id;
    private String name;
    private String description;
    private MenuCategory category;
    private Map<String, SizeInfo> sizes;

    // Default constructor for JSON parsing
    public MenuItem() {
        this.sizes = new HashMap<>();
    }

    // Constructor
    public MenuItem(int id, String name, String description, MenuCategory category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.sizes = new HashMap<>();
    }

    // Inner class to match your JSON structure
    public static class SizeInfo {
        private String name;
        private String code;
        private Double price;

        public SizeInfo() {}

        public SizeInfo(String name, String code, Double price) {
            this.name = name;
            this.code = code;
            this.price = price;
        }

        // Getters and setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
        
        public Double getPrice() { return price; }
        public void setPrice(Double price) { this.price = price; }

        @Override
        public String toString() {
            return name + " (" + code + ") - " + (price != null ? price + " LKR" : "N/A");
        }
    }

    // Main getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public MenuCategory getCategory() { return category; }
    public void setCategory(MenuCategory category) { this.category = category; }

    public Map<String, SizeInfo> getSizes() { return sizes; }
    public void setSizes(Map<String, SizeInfo> sizes) { this.sizes = sizes; }

    // Business methods
    public Double getPriceForSize(String sizeCode) {
        SizeInfo sizeInfo = sizes.get(sizeCode.toLowerCase());
        return sizeInfo != null ? sizeInfo.getPrice() : null;
    }

    public SizeInfo getSizeInfo(String sizeCode) {
        return sizes.get(sizeCode.toLowerCase());
    }

    public boolean isAvailableInSize(String sizeCode) {
        SizeInfo sizeInfo = sizes.get(sizeCode.toLowerCase());
        return sizeInfo != null && sizeInfo.getPrice() != null;
    }

    public String getAvailableSizesDisplay() {
        StringBuilder sb = new StringBuilder();
        for (String sizeCode : category.getSizeCodes()) {
            SizeInfo sizeInfo = sizes.get(sizeCode.toLowerCase());
            if (sizeInfo != null && sizeInfo.getPrice() != null) {
                if (sb.length() > 0) sb.append(" | ");
                sb.append(sizeCode).append(" ").append(sizeInfo.getPrice()).append(" LKR");
            }
        }
        return sb.toString();
    }


}