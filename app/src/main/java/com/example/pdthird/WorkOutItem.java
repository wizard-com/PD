package com.example.pdthird;

public class WorkOutItem {

    private String type;
    private String colorCode;
    private int imageID;


    public WorkOutItem(String type, String colorCode, int imageID) {
        this.type = type;
        this.colorCode = colorCode;
        this.imageID = imageID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
