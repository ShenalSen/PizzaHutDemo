package org.pizzastore;

import java.util.Map;

public class Pizza {
    public static final Pizza COMPLETE_ORDER = new Pizza(-1, "COMPLETE", "Complete Order", Map.of());

    private int id;
    private String name;
    private String description;
    private Map<String, Double> sizes;

    public Pizza() {}

    public Pizza(int id, String name, String description, Map<String, Double> sizes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sizes = sizes;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Double> getSizes() {
        return sizes;
    }

    public void setSizes(Map<String, Double> sizes) {
        this.sizes = sizes;
    }

    public double getPriceBySize(String size) {
        return sizes.getOrDefault(size.toUpperCase(), 0.0);
    }

    public boolean isValidSize(String size) {
        return sizes.containsKey(size.toUpperCase());
    }

    @Override
    public String toString() {
        if (sizes == null || sizes.isEmpty()) {
            return String.format("#%d %s", id, name);
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("#%d %s - ", id, name));
        
        
        String[] sizeOrder = {"L", "M", "S"};
        boolean first = true;
        
        for (String size : sizeOrder) {
            if (sizes.containsKey(size)) {
                if (!first) {
                    sb.append(" | ");
                }
                sb.append(String.format("%s %.2f LKR", size, sizes.get(size)));
                first = false;
            }
        }
        
        return sb.toString();
    }
}
