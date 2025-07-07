package org.Pizzahut.models;

import org.Pizzahut.models.enums.MenuCategory;


public class MenuItem {
    private int id;
    private String name;
    private String description;
    private MenuCategory category;
    

    // Default constructor for JSON parsing
    public MenuItem() {
        
    }

    // Constructor
    public MenuItem(int id, String name, String description, MenuCategory category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        
    }

}