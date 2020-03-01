package com.example.pdthird;

import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;

import java.util.ArrayList;

public class PageItem {

    private AnyChart anyChart;
    private ArrayList<DataEntry> dataEntries;

    public AnyChart getAnyChart() {
        return anyChart;
    }

    public void setAnyChart(AnyChart anyChart) {
        this.anyChart = anyChart;
    }


    public ArrayList<DataEntry> getDataEntries() {
        return dataEntries;
    }

    public void setDataEntries(ArrayList<DataEntry> dataEntries) {
        this.dataEntries = dataEntries;
    }


    public PageItem(AnyChart anyChart, ArrayList<DataEntry> entries) {
        this.anyChart = anyChart;
        this.dataEntries = entries;
    }


}
