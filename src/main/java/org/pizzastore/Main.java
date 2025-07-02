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
                    System.out.println("Please enter valid option from bellow");
                    System.out.println("To View our menu, press [1]");
                    System.out.println("To place an Order, press [2]");
                    System.out.println();
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
        System.out.println("Please enter a valid input");
        menuService.displayMenu();
    }
    
    private static void showOrderView() {
        List<Pizza> selectedPizzas = new ArrayList<>();
        
        // First item selection
        Pizza firstPizza = selectItem("You can buy 3 items.\nPlease select first item you want to buy.", 
                                     "Press item number to select first item\nOR\nPress [0] to go back to Main menu");
        if (firstPizza == null) return; 
        selectedPizzas.add(firstPizza);
        
        // Second item selection
        Pizza secondPizza = selectItem("Please select second item you want to buy.", 
                                      "Press item number to select as second item\nOR\nPress [E] to complete\nOR\nPress [0] to go back to Main menu");
        if (secondPizza == null) {
            // Check if user wants to complete order or go back
            return;
        }
        if (secondPizza != Pizza.COMPLETE_ORDER) {
            selectedPizzas.add(secondPizza);
        } else {
            showReceipt(selectedPizzas);
            return;
        }
        
        // Third item selection
        Pizza thirdPizza = selectItem("Please select final item you want to buy.", 
                                     "Press item number to select as third item\nOR\nPress [E] to complete\nOR\nPress [0] to go back to Main menu");
        if (thirdPizza == null) {
            return; 
        }
        if (thirdPizza != Pizza.COMPLETE_ORDER) {
            selectedPizzas.add(thirdPizza);
        }
        
        showReceipt(selectedPizzas);
    }
    
    private static Pizza selectItem(String message, String instruction) {
        while (true) {
            menuService.displayMenuForOrder(message);
            System.out.println(instruction);
            
            String input = scanner.nextLine().trim().toLowerCase();
            
            if (input.equals("0")) {
                return null; 
            }
            
            if (input.equals("e")) {
                return Pizza.COMPLETE_ORDER; 
            }
            
            try {
                int choice = Integer.parseInt(input);
                Pizza selectedPizza = menuService.getPizzaById(choice);
                
                if (selectedPizza != null) {
                    return selectedPizza;
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
    
    private static void showReceipt(List<Pizza> selectedPizzas) {
        double total = selectedPizzas.stream().mapToDouble(Pizza::getPrice).sum();
        
        System.out.println("You have ordered #" + selectedPizzas.size() + " number of items");
        System.out.println("            Pizza Hut");
        System.out.println("-------------------------------------");
        
        for (Pizza pizza : selectedPizzas) {
            System.out.println(pizza);
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