package org.Pizzahut.models;

import java.util.ArrayList;
import java.util.List;

import org.Pizzahut.models.enums.MenuCategory;


public class Order {
    private List<OrderItem> orderItems;
    private double totalAmount;

    // Constructor
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

        // Add addon 
        public void addAddon(Addon addon) {
            if (addon.isApplicableToCategory(menuItem.getCategory())) {
                addons.add(addon);
                addonTotal += addon.getPrice();
                itemTotal = itemPrice + addonTotal;
            }
        }

        // Remove addon
        public void removeAddon(Addon addon) {
            if (addons.remove(addon)) {
                addonTotal -= addon.getPrice();
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
