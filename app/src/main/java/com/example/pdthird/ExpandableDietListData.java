package com.example.pdthird;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

    public class ExpandableDietListData {
        public static HashMap<String, List<DietNestedItem>> getData() {
            HashMap<String, List<DietNestedItem>> expandableListDetail = new HashMap<String, List<DietNestedItem>>();

            List<DietNestedItem> protein = new ArrayList<DietNestedItem>();

            List<DietNestedItem> fibre = new ArrayList<DietNestedItem>();

            List<DietNestedItem> lowFat = new ArrayList<DietNestedItem>();

            List<DietNestedItem> lowCarb = new ArrayList<DietNestedItem>();

            List<DietNestedItem> balanced = new ArrayList<DietNestedItem>();

            expandableListDetail.put("High-protein diet", protein);
            expandableListDetail.put("High-fibre diet", fibre);
            expandableListDetail.put("Low-fat diet", lowFat);
            expandableListDetail.put("Low-carb diet", lowCarb);
            expandableListDetail.put("Balanced diet", balanced);

            return expandableListDetail;
        }
    }
