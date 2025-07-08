package org.Pizzahut.ui;

import org.Pizzahut.models.*;
import org.Pizzahut.models.enums.MenuCategory;
import org.Pizzahut.services.MenuService;
import org.Pizzahut.services.AddonService;
import java.util.Scanner;
import java.util.List;

public class MenuController {
    private MenuService menuService;
    private AddonService addonService;
    private Scanner scanner;
    private Order currentOrder;
    
    public MenuController() {
        this.menuService = MenuService.getInstance();
        this.addonService = AddonService.getInstance();
        this.scanner = new Scanner(System.in);
        this.currentOrder = new Order();
    }
    
    public void start() {
        displayWelcome();
        showMainMenu();
    }
    
    private void displayWelcome() {
        System.out.println("Welcome to PizzaHut!");
        System.out.println("\nCheck out our new items!");
        System.out.println("Password : XU0zA5yt19");
    }
    
    private void showMainMenu() {
        while (true) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("                    MAIN MENU");
            System.out.println("=".repeat(50));
            System.out.println("[1] Pizza Menu");
            System.out.println("[2] Soft Drinks Menu");
            System.out.println("[3] Hot Beverages Menu");
            System.out.println("[4] Cakes Menu");
            System.out.println("[5] Order Items");
            System.out.println("[x] Exit");
            System.out.print("\nEnter your choice: ");
            
            String choice = scanner.nextLine().trim().toLowerCase();
            
            switch (choice) {
                case "1" -> showCategoryMenu(MenuCategory.PIZZA);
                case "2" -> showCategoryMenu(MenuCategory.SOFT_DRINKS);
                case "3" -> showCategoryMenu(MenuCategory.HOT_BEVERAGES);
                case "4" -> showCategoryMenu(MenuCategory.CAKES);
                case "5" -> startOrderProcess();
                case "x" -> {
                    System.out.println("Thank you for visiting Pizza Hut!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    
    private void showCategoryMenu(MenuCategory category) {
        while (true) {
            menuService.displayMenu(category);
            System.out.print("\nEnter your choice: ");
            
            String choice = scanner.nextLine().trim();
            
            if (choice.equals("0")) {
                return; // Go back to main menu
            }
            
            try {
                int itemId = Integer.parseInt(choice);
                MenuItem item = menuService.getItemById(itemId, category);
                
                if (item != null) {
                    showItemDetails(item);
                } else {
                    System.out.println("Invalid item number. Please try again.");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
    
    private void showItemDetails(MenuItem item) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                 ITEM DETAILS");
        System.out.println("=".repeat(60));
        System.out.println(item.getDetailedView());
        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }
    
    private void startOrderProcess() {
        currentOrder = new Order(); // Reset order
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                 START ORDERING");
        System.out.println("=".repeat(50));
        
        for (int itemCount = 1; itemCount <= 3; itemCount++) {
            System.out.println("\nSelecting item #" + itemCount);
            
            // Step 1: Category Selection
            MenuCategory selectedCategory = selectCategory();
            if (selectedCategory == null) return; // User chose to exit
            
            // Step 2: Item Selection
            MenuItem selectedItem = selectItem(selectedCategory);
            if (selectedItem == null) return; // User chose to go back
            
            // TODO: Add size and addon selection
            System.out.println("Selected item: " + selectedItem.getName());
            System.out.println("Press any key to continue...");
            scanner.nextLine();
            break; // For now, just test item selection
        }
    }
    
    // Add this new method:
    private MenuItem selectItem(MenuCategory category) {
        while (true) {
            List<MenuItem> items = menuService.getAvailableItems(category);
            
            System.out.println("\n" + "=".repeat(50));
            System.out.println("           " + category.getDisplayName().toUpperCase() + " MENU");
            System.out.println("=".repeat(50));
            
            for (MenuItem item : items) {
                System.out.println(item.toString());
            }
            
            System.out.println("\n[0] Back to category selection");
            System.out.println("[E] Complete order with current items");
            System.out.print("\nEnter item number: ");
            
            String choice = scanner.nextLine().trim().toUpperCase();
            
            if (choice.equals("0")) {
                return null; // Go back
            }
            
            if (choice.equals("E") && !currentOrder.isEmpty()) {
                displayFinalReceipt();
                return null;
            }
            
            try {
                int itemId = Integer.parseInt(choice);
                MenuItem item = menuService.getItemById(itemId, category);
                
                if (item != null) {
                    return item;
                } else {
                    System.out.println("Invalid item number. Please try again.");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
    
    // Add placeholder for receipt
    private void displayFinalReceipt() {
        System.out.println("Receipt generation (to be implemented)");
        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }
    
    // Keep existing selectCategory() method unchanged
    private MenuCategory selectCategory() {
        while (true) {
            System.out.println("\n" + "=".repeat(40));
            System.out.println("           SELECT CATEGORY");
            System.out.println("=".repeat(40));
            System.out.println("[1] Pizza");
            System.out.println("[2] Soft Drinks");
            System.out.println("[3] Hot Beverages");
            System.out.println("[4] Cakes");
            System.out.println("[0] Back to Main Menu");
            System.out.print("\nEnter category number: ");
            
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1" -> { return MenuCategory.PIZZA; }
                case "2" -> { return MenuCategory.SOFT_DRINKS; }
                case "3" -> { return MenuCategory.HOT_BEVERAGES; }
                case "4" -> { return MenuCategory.CAKES; }
                case "0" -> { return null; }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
