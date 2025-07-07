package org.Pizzahut.models.enums;

import java.util.Arrays;
import java.util.List;

public enum MenuCategory {
    PIZZA("Pizza", Arrays.asList("L", "M", "S"), Arrays.asList("Large", "Medium", "Small")),
    SOFT_DRINKS("Soft Drinks", Arrays.asList("L", "R", "S"), Arrays.asList("Large", "Regular", "Small")),
    HOT_BEVERAGES("Hot Beverages", Arrays.asList("L", "R", "S"), Arrays.asList("Large", "Regular", "Small")),
    CAKES("Cakes", Arrays.asList("F", "H", "S"), Arrays.asList("Full", "Half", "Slice"));

    private final String displayName;
    private final List<String> sizeCodes;
    private final List<String> sizeNames;

    MenuCategory(String displayName, List<String> sizeCodes, List<String> sizeNames) {
        this.displayName = displayName;
        this.sizeCodes = sizeCodes;
        this.sizeNames = sizeNames;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<String> getSizeCodes() {
        return sizeCodes;
    }

    public List<String> getSizeNames() {
        return sizeNames;
    }

    public String getSizeNameByCode(String code) {
        int index = sizeCodes.indexOf(code.toUpperCase());
        return index != -1 ? sizeNames.get(index) : null;
    }

    public boolean isValidSize(String sizeCode) {
        return sizeCodes.contains(sizeCode.toUpperCase());
    }


}