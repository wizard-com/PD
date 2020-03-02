package com.example.pdthird;

import android.graphics.Color;

public class BMIResult {

    private double bmi;
    private String colorCode;
    private String text;

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }


    public String getColorCode() {
        return colorCode;
    }

    public void setColor(String colorCode) {
        this.colorCode = colorCode;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BMIResult(double bmi, String colorCode, String text) {
        this.bmi = bmi;
        this.colorCode = colorCode;
        this.text = text;
    }





}
