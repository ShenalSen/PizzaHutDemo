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
        System.out.println("Order process (to be implemented)");
        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }
}
