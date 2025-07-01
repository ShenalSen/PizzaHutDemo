package org.pizzastore;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final MenuService menuService = new MenuService();

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