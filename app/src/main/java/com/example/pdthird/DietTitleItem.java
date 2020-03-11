package com.example.pdthird;

public class DietTitleItem {

    private String colorCode;
    private String title;
    private int imageID;

    public DietTitleItem(String colorCode, String title, int imageID) {
        this.colorCode = colorCode;
        this.title = title;
        this.imageID = imageID;
    }
    public String getColorCode() {
        return colorCode;
    }
    public void setColorCode(String colorCode) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
