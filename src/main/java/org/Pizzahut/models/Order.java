package org.Pizzahut.models;
import java.util.List;
import java.util.ArrayList;

public class Order {
    private List<OrderItem> orderItems;
    private double totalAmount;

    
    public Order() {
        this.orderItems = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    
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

}
