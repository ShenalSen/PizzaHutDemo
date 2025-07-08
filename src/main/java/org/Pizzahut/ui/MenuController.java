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
        // TODO: Implement main menu
        System.out.println("Main menu (to be implemented)");
    }
}
