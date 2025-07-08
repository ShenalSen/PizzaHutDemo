package org.Pizzahut.services;

import org.Pizzahut.models.MenuItem;
import org.Pizzahut.models.enums.MenuCategory;
import com.google.gson.Gson;
import java.util.List;


public class MenuService {
    private static MenuService instance;
    
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
        
    }
    
    public MenuItem getItemById(int id, MenuCategory category) {
        
    }
}
