package com.example.pdthird;

import android.widget.ImageView;

public class ReminderItem {
    private String label;
    private int imageID;

    public ReminderItem(String label, int image) {
        this.label = label;
        this.imageID = image;
    }
    public int getImageID() {
        return imageID;
    }

    public String getLabel() {
        return label;
    }


}
