package org.pizzastore;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final MenuService menuService = new MenuService();

        public static void main(String[] args) {
        System.out.println("""
            ===================================
             Welcome to PizzaHut App by Shenal!
            ===================================
            """);
        showHomeView();
    }
    
    private static void showHomeView() {
        while (true) {
            System.out.println();
            System.out.println("To View our menu, press [1]");
            System.out.println("To place an Order, press [2]");
            System.out.print("Press [x] to exit the store :");
            
            String input = scanner.nextLine().trim().toLowerCase();
            
            switch (input) {
                case "1":
                    showMainMenuView();
                    break;
                case "2":
                    showOrderView();
                    break;
                case "x":
                    System.out.println("Thank you for visiting Pizza Hut, See you next time");
                    return;
                default:
                    System.out.println();
                    System.out.println("Please enter valid option from bellow");
                    System.out.println();
                    break; 
            }
        }
    }
    
    private static void showMainMenuView() {
        while (true) {
            menuService.displayMenu();
            
            String input = scanner.nextLine().trim();
            
            if (input.equals("0")) {
                return; 
            }
            
            try {
                int menuChoice = Integer.parseInt(input);
                Pizza selectedPizza = menuService.getPizzaById(menuChoice);
                
                if (selectedPizza != null) {
                    menuService.displayPizzaDetails(selectedPizza);
                    scanner.nextLine(); 
                } else {
                    showInvalidMenuInput();
                }
            } catch (NumberFormatException e) {
                showInvalidMenuInput();
            }
        }
    }
    
    private static void showInvalidMenuInput() {
        System.out.println();
        System.out.println("Please enter a valid input");
        System.out.println();
    }
    
    private static void showOrderView() {
        List<PizzaOrder> selectedOrders = new ArrayList<>();
        
        // First item selection
        PizzaOrder firstOrder = selectItemWithSize("You can buy 3 items.\nPlease select first item you want to buy.", 
                                                   "Press item number to select first item\nOR\nPress [0] to go back to Main menu");
        if (firstOrder == null) return; 
        selectedOrders.add(firstOrder);
        
        // Second item selection
        PizzaOrder secondOrder = selectItemWithSize("Please select second item you want to buy.", 
                                                    "Press item number to select as second item\nOR\nPress [E] to complete\nOR\nPress [0] to go back to Main menu");
        if (secondOrder == null) {
            return;
        }
        if (secondOrder.getPizza() != Pizza.COMPLETE_ORDER) {
            selectedOrders.add(secondOrder);
        } else {
            showReceipt(selectedOrders);
            return;
        }
        
        // Third item selection
        PizzaOrder thirdOrder = selectItemWithSize("Please select final item you want to buy.", 
                                                   "Press item number to select as third item\nOR\nPress [E] to complete\nOR\nPress [0] to go back to Main menu");
        if (thirdOrder == null) {
            return; 
        }
        if (thirdOrder.getPizza() != Pizza.COMPLETE_ORDER) {
            selectedOrders.add(thirdOrder);
        }
        
        showReceipt(selectedOrders);
    }
    
    private static PizzaOrder selectItemWithSize(String message, String instruction) {
        while (true) {
            menuService.displayMenuForOrder(message);
            System.out.println(instruction);
            
            String input = scanner.nextLine().trim().toLowerCase();
            
            if (input.equals("0")) {
                return null; 
            }
            
            if (input.equals("e")) {
                return new PizzaOrder(Pizza.COMPLETE_ORDER, "");
            }
            
            try {
                int choice = Integer.parseInt(input);
                Pizza selectedPizza = menuService.getPizzaById(choice);
                
                if (selectedPizza != null) {
                    // Now ask for size selection
                    return selectSize(selectedPizza);
                } else {
                    System.out.println("Please enter a valid item number");
                    System.out.println();
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid input");
                System.out.println();
            }
        }
    }
    
    private static PizzaOrder selectSize(Pizza pizza) {
        while (true) {
            System.out.println("You selected: " + pizza.getName());
            System.out.println("Please select size:");
            System.out.println("Press [L] for Large - " + String.format("%.2f LKR", pizza.getPriceBySize("L")));
            System.out.println("Press [M] for Medium - " + String.format("%.2f LKR", pizza.getPriceBySize("M")));
            System.out.println("Press [S] for Small - " + String.format("%.2f LKR", pizza.getPriceBySize("S")));
            System.out.println("Press [0] to go back to pizza selection");
            
            String sizeInput = scanner.nextLine().trim().toUpperCase();
            
            if (sizeInput.equals("0")) {
                return null; // Go back to pizza selection
            }
            
            if (pizza.isValidSize(sizeInput)) {
                return new PizzaOrder(pizza, sizeInput);
            } else {
                System.out.println("Please enter a valid size (L, M, or S)");
                System.out.println();
            }
        }
    }
    
    private static void showReceipt(List<PizzaOrder> selectedOrders) {
        double total = selectedOrders.stream().mapToDouble(PizzaOrder::getPrice).sum();
        
        System.out.println("You have ordered #" + selectedOrders.size() + " number of items");
        System.out.println("            Pizza Hut");
        System.out.println("-------------------------------------");
        
        for (PizzaOrder order : selectedOrders) {
            System.out.println(order);
        }
        
        System.out.println();
        System.out.println("        Total : " + String.format("%.2f LKR", total));
        System.out.println("-------------------------------------");
        System.out.println("     Thank You For Ordering");
        System.out.println("-------------------------------------");
        System.out.println("Thank you for visiting Pizza Hut, See you next time");
        
        System.exit(0); 
    }

}