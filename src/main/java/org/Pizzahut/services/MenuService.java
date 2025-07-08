package org.Pizzahut.services;

import org.Pizzahut.models.MenuItem;
import org.Pizzahut.models.enums.MenuCategory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;


public class MenuService {
    private static MenuService instance;
    private Gson gson;
    
    private MenuService() {
        this.gson = new Gson();
    }
    
    public static MenuService getInstance() {
        if (instance == null) {
            instance = new MenuService();
        }
        return instance;
    }
    
    public List<MenuItem> getMenuByCategory(MenuCategory category) {
        List<MenuItem> menuItems = new ArrayList<>();
        
        try {
            
            String fileName = "/menus/" + category.getJsonFileName();
            InputStream inputStream = getClass().getResourceAsStream(fileName);
            
            if (inputStream == null) {
                System.out.println("Could not find file: " + fileName);
                return menuItems;
            }
            
            InputStreamReader reader = new InputStreamReader(inputStream);
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            
            
            String arrayKey = getArrayKey(category);
            JsonArray itemsArray = jsonObject.getAsJsonArray(arrayKey);
            
            int id = 1;
            for (JsonElement element : itemsArray) {
                JsonObject itemJson = element.getAsJsonObject();
                MenuItem menuItem = parseMenuItem(itemJson, category, id++);
                if (menuItem != null) {
                    menuItems.add(menuItem);
                }
            }
            
            reader.close();
            
        } catch (Exception e) {
            System.out.println("Error loading menu for category: " + category.getDisplayName());
            e.printStackTrace();
        }
        
        return menuItems;
    }
    
    private String getArrayKey(MenuCategory category) {
        return switch (category) {
            case PIZZA -> "pizzaItems";
            case SOFT_DRINKS -> "softDrinksItems";
            case HOT_BEVERAGES -> "hotBeveragesItems";
            case CAKES -> "cakeItems";
        };
    }
    
    private MenuItem parseMenuItem(JsonObject itemJson, MenuCategory category, int id) {
        try {
            MenuItem menuItem = new MenuItem();
            menuItem.setId(id);
            menuItem.setName(itemJson.get("name").getAsString());
            menuItem.setDescription(itemJson.get("description").getAsString());
            menuItem.setCategory(category);
            
            parseSizes(itemJson, menuItem, category);
            
            return menuItem;
            
        } catch (Exception e) {
            System.out.println("Error parsing menu item: " + e.getMessage());
            return null;
        }
    }
    
    private void parseSizes(JsonObject itemJson, MenuItem menuItem, MenuCategory category) {
        for (String sizeCode : category.getSizeCodes()) {
            String sizeKey = getSizeKey(sizeCode);
            
            if (itemJson.has(sizeKey) && !itemJson.get(sizeKey).isJsonNull()) {
                JsonObject sizeJson = itemJson.getAsJsonObject(sizeKey);
                
                MenuItem.SizeInfo sizeInfo = new MenuItem.SizeInfo();
                sizeInfo.setName(sizeJson.get("name").getAsString());
                sizeInfo.setCode(sizeJson.get("code").getAsString());
                
                if (sizeJson.has("price") && !sizeJson.get("price").isJsonNull()) {
                    sizeInfo.setPrice(sizeJson.get("price").getAsDouble());
                } else {
                    sizeInfo.setPrice(null);
                }
                
                menuItem.getSizes().put(sizeKey, sizeInfo);
            }
            // If the size is null or doesn't exist, we simply skip it
        }
    }
    
    private String getSizeKey(String sizeCode) {
        return switch (sizeCode.toUpperCase()) {
            case "L" -> "large";
            case "M" -> "medium";
            case "S" -> "small";
            case "R" -> "regular";
            case "F" -> "full";
            case "H" -> "half";
            default -> sizeCode.toLowerCase();
        };
    }
    
    public MenuItem getItemById(int id, MenuCategory category) {
        List<MenuItem> menuItems = getMenuByCategory(category);
        
        for (MenuItem item : menuItems) {
            if (item.getId() == id) {
                return item;
            }
        }
        
        return null;
    }
    
    public List<MenuItem> getAvailableItems(MenuCategory category) {
        List<MenuItem> allItems = getMenuByCategory(category);
        List<MenuItem> availableItems = new ArrayList<>();
        
        for (MenuItem item : allItems) {
            // Check if item has at least one available size (non-null price)
            boolean hasAvailableSize = false;
            for (String sizeCode : category.getSizeCodes()) {
                if (item.isAvailableInSize(sizeCode)) {
                    hasAvailableSize = true;
                    break;
                }
            }
            
            if (hasAvailableSize) {
                availableItems.add(item);
            }
        }
        
        return availableItems;
    }
    
    public void displayMenu(MenuCategory category) {
        List<MenuItem> items = getAvailableItems(category);
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           " + category.getDisplayName().toUpperCase() + " MENU");
        System.out.println("=".repeat(50));
        
        if (items.isEmpty()) {
            System.out.println("No items available in this category.");
            return;
        }
        
        for (MenuItem item : items) {
            System.out.println(item.toString());
        }
        
        System.out.println("\nPress [0] to go back");
        System.out.println("Enter item number for details:");
    }
}
