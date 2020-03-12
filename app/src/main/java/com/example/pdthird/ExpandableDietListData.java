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


            String[] labels = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

            String[] colorCodes = new String[]{"#594287", "#84B83C", "#F9EC00", "#FB8A20", "#87D3E7"};

            List<DietNestedItem>[] arrayOfList = new List[5];

            arrayOfList[0] = protein;
            arrayOfList[1] = fibre;
            arrayOfList[2] = lowFat;
            arrayOfList[3] = lowCarb;
            arrayOfList[4] = balanced;



            for(int i = 0; i < arrayOfList.length; i++){

                for(int j = 0; j < labels.length; j++){

                    arrayOfList[i].add(new DietNestedItem(labels[j], colorCodes[i]));
                }

            }

            expandableListDetail.put("High-protein diet", protein);
            expandableListDetail.put("High-fibre diet", fibre);
            expandableListDetail.put("Low-fat diet", lowFat);
            expandableListDetail.put("Low-carb diet", lowCarb);
            expandableListDetail.put("Balanced diet", balanced);

            return expandableListDetail;
        }
    }
