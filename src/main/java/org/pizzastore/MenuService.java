package org.pizzastore;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class MenuService {
    private List<Pizza> pizzas;

    public MenuService() {
        loadMenu();
    }

    private void loadMenu() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("pizzaMenuList.json");
            if (inputStream == null) {
                throw new RuntimeException("Could not find menuList.json");
            }
            
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Pizza>>(){}.getType();
            pizzas = gson.fromJson(new InputStreamReader(inputStream), listType);
        } catch (Exception e) {
            throw new RuntimeException("Error loading menu from menuList.json: " + e.getMessage(), e);
        }
    }

    public List<Pizza> getAllPizzas() {
        return pizzas;
    }

    public Pizza getPizzaById(int id) {
        return pizzas.stream()
                .filter(pizza -> pizza.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void displayMenu() {
        System.out.println("""
            -------------
            PizzaHut Menu
            -------------""");
        for (Pizza pizza : pizzas) {
            System.out.println(pizza);
        }
        System.out.println();
        System.out.println();
        System.out.println("Press item number to view description");
        System.out.println("OR");
        System.out.println("Press [0] to go back");
    }

    public void displayPizzaDetails(Pizza pizza) {
        System.out.println("Name        : " + pizza.getName());
        System.out.println("Description : " + pizza.getDescription());
        System.out.println("Sizes & Prices:");
        
        // need to fix 12
        String[] sizeOrder = {"L", "M", "S"};
        for (String size : sizeOrder) {
            if (pizza.getSizes().containsKey(size)) {
                String sizeDisplay = size.equals("L") ? "Large" : 
                                   size.equals("M") ? "Medium" : "Small";
                System.out.println("  " + sizeDisplay + " (" + size + ") : " + 
                                 String.format("%.2f LKR", pizza.getPriceBySize(size)));
            }
        }
        System.out.println();
        System.out.println("Press any to go back");
    }

    // need to fix 10
    public void displayMenuForOrder(String message) {
        System.out.println(message);
        System.out.println();
        for (Pizza pizza : pizzas) {
            System.out.println(pizza);
        }
        System.out.println();
        System.out.println();
    }
}
