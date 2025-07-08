package org.Pizzahut.services;

import org.Pizzahut.models.Addon;
import org.Pizzahut.models.enums.MenuCategory;
import com.google.gson.Gson;


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
}    