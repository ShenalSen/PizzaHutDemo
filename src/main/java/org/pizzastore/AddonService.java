package org.pizzastore;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class AddonService {
    private List<Addon> addons;

    public AddonService() {
        loadAddons();
    }

    private void loadAddons() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("pizzaAddon.json");
            if (inputStream == null) {
                throw new RuntimeException("Could not find pizzaAddon.json");
            }
            
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Addon>>(){}.getType();
            addons = gson.fromJson(new InputStreamReader(inputStream), listType);
        } catch (Exception e) {
            throw new RuntimeException("Error loading addons from pizzaAddon.json: " + e.getMessage(), e);
        }
    }

    
}
