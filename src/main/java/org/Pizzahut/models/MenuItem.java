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
        String sizeKey = getSizeKey(sizeCode);
        SizeInfo sizeInfo = sizes.get(sizeKey);
        return sizeInfo != null ? sizeInfo.getPrice() : null;
    }

    public SizeInfo getSizeInfo(String sizeCode) {
        String sizeKey = getSizeKey(sizeCode);
        return sizes.get(sizeKey);
    }

    public boolean isAvailableInSize(String sizeCode) {
        String sizeKey = getSizeKey(sizeCode);
        SizeInfo sizeInfo = sizes.get(sizeKey);
        return sizeInfo != null && sizeInfo.getPrice() != null;
    }

    private String getSizeKey(String sizeCode) {
        return switch (sizeCode.toUpperCase()) {
            case "L" -> "large";
            case "M" -> "medium";
            case "S" -> "small";
            case "R" -> "regular";
            case "F" -> "full";
            case "H" -> "half";
            default -> sizeCode.toLowerCase();
        };
    }

    public String getAvailableSizesDisplay() {
        StringBuilder sb = new StringBuilder();
        for (String sizeCode : category.getSizeCodes()) {
            String sizeKey = getSizeKey(sizeCode);
            SizeInfo sizeInfo = sizes.get(sizeKey);
            if (sizeInfo != null && sizeInfo.getPrice() != null) {
                if (sb.length() > 0) sb.append(" | ");
                sb.append(sizeCode).append(" ").append(sizeInfo.getPrice()).append(" LKR");
            }
        }
        return sb.toString();
    }

    // ui meths
    @Override
    public String toString() {
        return "#" + id + " " + name + " - " + getAvailableSizesDisplay();
    }

    public String getDetailedView() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name : ").append(name).append("\n");
        sb.append("Description : ").append(description).append("\n");
        sb.append("Available Sizes : ");
        
        boolean first = true;
        for (String sizeCode : category.getSizeCodes()) {
            String sizeKey = getSizeKey(sizeCode);
            SizeInfo sizeInfo = sizes.get(sizeKey);
            if (sizeInfo != null && sizeInfo.getPrice() != null) {
                if (!first) sb.append(" | ");
                sb.append(sizeInfo.getName());
                first = false;
            }
        }
        sb.append("\n");
        
        for (String sizeCode : category.getSizeCodes()) {
            String sizeKey = getSizeKey(sizeCode);
            SizeInfo sizeInfo = sizes.get(sizeKey);
            if (sizeInfo != null && sizeInfo.getPrice() != null) {
                sb.append(sizeInfo.getName()).append(" Price : ").append(sizeInfo.getPrice()).append(" LKR\n");
            }
        }
        
        return sb.toString();
    }
}
