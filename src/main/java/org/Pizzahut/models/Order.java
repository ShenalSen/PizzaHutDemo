package org.Pizzahut.models;

import org.Pizzahut.models.enums.MenuCategory;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;

public class Order {
    private List<OrderItem> orderItems;
    private double totalAmount;

    // Constructor
    public Order() {
        this.orderItems = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    // Inner class to represent individual order items
    public static class OrderItem {
        private MenuItem menuItem;
        private String selectedSize;
        private String selectedSizeName;
        private double itemPrice;
        private List<Addon> addons;
        private double addonTotal;
        private double itemTotal;

        public OrderItem(MenuItem menuItem, String selectedSize) {
            this.menuItem = menuItem;
            this.selectedSize = selectedSize.toUpperCase();
            this.selectedSizeName = menuItem.getCategory().getSizeNameByCode(selectedSize);
            this.itemPrice = menuItem.getPriceForSize(selectedSize);
            this.addons = new ArrayList<>();
            this.addonTotal = 0.0;
            this.itemTotal = this.itemPrice;
        }

        // Add addon to this order item
        public void addAddon(Addon addon) {
            if (addon.isApplicableToCategory(menuItem.getCategory())) {
                addons.add(addon);
                addonTotal += addon.getPrice();
                itemTotal = itemPrice + addonTotal;
            }
        }

        // Getters
        public MenuItem getMenuItem() { return menuItem; }
        public String getSelectedSize() { return selectedSize; }
        public String getSelectedSizeName() { return selectedSizeName; }
        public double getItemPrice() { return itemPrice; }
        public List<Addon> getAddons() { return addons; }
        public double getAddonTotal() { return addonTotal; }
        public double getItemTotal() { return itemTotal; }
    }

    // Order management methods
    public void addItem(MenuItem menuItem, String selectedSize) {
        OrderItem orderItem = new OrderItem(menuItem, selectedSize);
        orderItems.add(orderItem);
        recalculateTotal();
    }

    public void addItemWithAddons(MenuItem menuItem, String selectedSize, List<Addon> addons) {
        OrderItem orderItem = new OrderItem(menuItem, selectedSize);
        for (Addon addon : addons) {
            orderItem.addAddon(addon);
        }
        orderItems.add(orderItem);
        recalculateTotal();
    }

    public OrderItem getLastItem() {
        return orderItems.isEmpty() ? null : orderItems.get(orderItems.size() - 1);
    }

    public void addAddonToLastItem(Addon addon) {
        OrderItem lastItem = getLastItem();
        if (lastItem != null) {
            lastItem.addAddon(addon);
            recalculateTotal();
        }
    }

    private void recalculateTotal() {
        totalAmount = orderItems.stream()
                .mapToDouble(OrderItem::getItemTotal)
                .sum();
    }

    
    public List<OrderItem> getOrderItems() { 
        return orderItems; 
    }
    
    public double getTotalAmount() { 
        return totalAmount; 
    }
    
    public int getItemCount() { 
        return orderItems.size(); 
    }
    
    public boolean isEmpty() { 
        return orderItems.isEmpty(); 
    }


    @SuppressWarnings("unused")
    public Map<MenuCategory, List<OrderItem>> getItemsByCategory() {
        Map<MenuCategory, List<OrderItem>> categoryMap = new LinkedHashMap<>();
        
        for (OrderItem item : orderItems) {
            MenuCategory category = item.getMenuItem().getCategory();
            categoryMap.computeIfAbsent(category, k -> new ArrayList<>()).add(item);
        }
        
        return categoryMap;
    }


    public String generateReceipt() {
        if (isEmpty()) {
            return "No items in order.";
        }

        StringBuilder receipt = new StringBuilder();
        receipt.append("You have ordered #").append(getItemCount()).append(" number of items\n");
        receipt.append("            Pizza Hut\n");
        receipt.append("---------------------------------------------\n");

        Map<MenuCategory, List<OrderItem>> itemsByCategory = getItemsByCategory();
        
        int itemNumber = 1;
        for (Map.Entry<MenuCategory, List<OrderItem>> entry : itemsByCategory.entrySet()) {
            receipt.append("[ ").append(entry.getKey().getDisplayName()).append(" ]\n");
            
            for (OrderItem item : entry.getValue()) {
                receipt.append("#").append(itemNumber++).append(" ").append(item.getMenuItem().getName()).append("\n");
                receipt.append("          - ").append(item.getSelectedSizeName()).append(" Size - ")
                       .append(String.format("%.1f", item.getItemPrice())).append(" LKR\n");
                
                if (!item.getAddons().isEmpty()) {
                    receipt.append("   Addons\n");
                    for (Addon addon : item.getAddons()) {
                        receipt.append("          - ").append(addon.getName()).append(" - ")
                               .append(String.format("%.1f", addon.getPrice())).append(" LKR\n");
                    }
                }
            }
        }

        receipt.append("\n        Total : ").append(String.format("%.1f", getTotalAmount())).append(" LKR\n");
        receipt.append("---------------------------------------------\n");
        receipt.append("        Thank You For Ordering\n");
        receipt.append("---------------------------------------------\n");
        receipt.append("Thank you for visiting Pizza Hut, See you next time\n");

        return receipt.toString();
    }

    @Override
    public String toString() {
        return "Order [Items: " + getItemCount() + ", Total: " + String.format("%.1f LKR", totalAmount) + "]";
    }
}
