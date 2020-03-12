package com.example.pdthird;

public class DietNestedItem {

    private String label;
    private String colorCode;


    public DietNestedItem(String label, String colorCode) {
        this.label = label;
        this.colorCode = colorCode;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }
}
