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
    

}