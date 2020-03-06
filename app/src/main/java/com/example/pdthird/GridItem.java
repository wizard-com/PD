package com.example.pdthird;

public class GridItem {

    private String title;
    private int imageID;
    private String colorCode;

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }


    public GridItem(String title, int imageID, String colorCode) {
        this.title = title;
        this.imageID = imageID;
        this.colorCode = colorCode;
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
