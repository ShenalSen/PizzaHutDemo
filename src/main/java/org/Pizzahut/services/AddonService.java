package org.Pizzahut.services;

import org.Pizzahut.models.Addon;
import org.Pizzahut.models.enums.MenuCategory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;

public class AddonService {
    private static AddonService instance;
    private Gson gson;
    
    private AddonService() {
        this.gson = new Gson();
    }
    
    public static AddonService getInstance() {
        if (instance == null) {
            instance = new AddonService();
        }
        return instance;
    }

    public List<Addon> getAddonsByCategory(MenuCategory category) {
        List<Addon> addons = new ArrayList<>();
        
        try {
            
            String fileName = "/addons/" + category.getAddonFileName();
            InputStream inputStream = getClass().getResourceAsStream(fileName);
            
            if (inputStream == null) {
                System.out.println("Could not find addon file: " + fileName);
                return addons;
            }
            
            InputStreamReader reader = new InputStreamReader(inputStream);
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            
            // Get the addons array
            JsonArray addonsArray = jsonObject.getAsJsonArray("addons");
            
            int id = 1;
            for (JsonElement element : addonsArray) {
                JsonObject addonJson = element.getAsJsonObject();
                Addon addon = parseAddon(addonJson, category, id++);
                if (addon != null) {
                    addons.add(addon);
                }
            }
            
            reader.close();
            
        } catch (Exception e) {
            System.out.println("Error loading addons for category: " + category.getDisplayName());
            e.printStackTrace();
        }
        
        return addons;
    }

    private Addon parseAddon(JsonObject addonJson, MenuCategory category, int id) {
        try {
            Addon addon = new Addon();
            addon.setId(id);
            addon.setName(addonJson.get("name").getAsString());
            addon.setPrice(addonJson.get("price").getAsDouble());
            addon.setCategory(category);
            
            return addon;
            
        } catch (Exception e) {
            System.out.println("Error parsing addon: " + e.getMessage());
            return null;
        }
    }

    
    public Addon getAddonById(int id, MenuCategory category) {
        List<Addon> addons = getAddonsByCategory(category);
        
        for (Addon addon : addons) {
            if (addon.getId() == id) {
                return addon;
            }
        }
        
        return null;
    }

    public boolean hasAddons(MenuCategory category) {
        List<Addon> addons = getAddonsByCategory(category);
        return !addons.isEmpty();
    }

    public void displayAddons(MenuCategory category) {
        List<Addon> addons = getAddonsByCategory(category);
        
        System.out.println("\n" + "=".repeat(40));
        System.out.println("        " + category.getDisplayName().toUpperCase() + " ADDONS");
        System.out.println("=".repeat(40));
        
        if (addons.isEmpty()) {
            System.out.println("No addons available for this category.");
            return;
        }
        
        for (Addon addon : addons) {
            System.out.println(addon.toString());
        }
        
        System.out.println("\nPress [0] to skip addons");
        System.out.println("Enter addon numbers (comma separated):");
    }

    public void displaySelectedAddons(List<Addon> selectedAddons) {
        if (selectedAddons.isEmpty()) {
            System.out.println("No addons selected.");
            return;
        }
        
        System.out.println("\nSelected Addons:");
        for (Addon addon : selectedAddons) {
            System.out.println("- " + addon.getDisplayName());
        }
        
        double totalAddonPrice = selectedAddons.stream()
                .mapToDouble(Addon::getPrice)
                .sum();
        
        System.out.println("Total Addon Cost: " + String.format("%.1f LKR", totalAddonPrice));
    }

    // Add this method to the class
    public List<Addon> parseAddonSelections(String input, MenuCategory category) {
        List<Addon> selectedAddons = new ArrayList<>();
        
        if (input == null || input.trim().isEmpty() || input.trim().equals("0")) {
            return selectedAddons; // Return empty list for no addons
        }
        
        try {
            String[] addonIds = input.split(",");
            List<Addon> availableAddons = getAddonsByCategory(category);
            
            for (String idStr : addonIds) {
                int id = Integer.parseInt(idStr.trim());
                
                for (Addon addon : availableAddons) {
                    if (addon.getId() == id) {
                        selectedAddons.add(addon);
                        break;
                    }
                }
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid addon selection format. Please use numbers separated by commas.");
        }
        
        return selectedAddons;
    }
}