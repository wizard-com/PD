package com.example.pdthird;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

    public class ExpandableDietListData {
        public static HashMap<String, List<String>> getData() {
            HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

            List<String> protein = new ArrayList<String>();
            protein.add("Fish");
            protein.add("Chicken");
            protein.add("Pork");
            protein.add("Beef");
            protein.add("Eggs");

            List<String> fibre = new ArrayList<String>();
            fibre.add("Spinach");
            fibre.add("Lettuce");
            fibre.add("Cabbage");
            fibre.add("Broccoli");
            fibre.add("Cucumber");

            List<String> lowFat = new ArrayList<String>();
            lowFat.add("No diary products");
            lowFat.add("No nuts");
            lowFat.add("No fatty fish");
            lowFat.add("No fried food");

            List<String> lowCarb = new ArrayList<String>();
            lowCarb.add("No rice");
            lowCarb.add("No bread");
            lowCarb.add("No sweet potatoes");
            lowCarb.add("No pizza");
            lowCarb.add("No other carbs");

            List<String> balanced = new ArrayList<String>();
            balanced.add("United States");
            balanced.add("Spain");
            balanced.add("Argentina");
            balanced.add("France");
            balanced.add("Russia");

            expandableListDetail.put("High-protein diet", protein);
            expandableListDetail.put("High-fibre diet", fibre);
            expandableListDetail.put("Low-fat diet", lowFat);
            expandableListDetail.put("Low-carb diet", lowCarb);
            expandableListDetail.put("Balanced diet", balanced);
            return expandableListDetail;
        }
    }
