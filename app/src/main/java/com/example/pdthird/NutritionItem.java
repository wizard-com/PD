package com.example.pdthird;

public class NutritionItem {
    private String name;
    private int qty;

    public NutritionItem(String name, int qty) {
        this.name = name;
        this.qty = qty;
    }
    public int getQty() {
        return qty;
    }

    public String getName() {
        return name;
    }
}
