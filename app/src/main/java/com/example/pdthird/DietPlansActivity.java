package com.example.pdthird;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class DietPlansActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    DietListAdapter expandableListAdapter;
    ArrayList<DietTitleItem> expandableListTitle;

    List<String> proteinDiets = new ArrayList<String>(Arrays.asList("Fish", "Chicken", "Pork", "Eggs", "", "", ""));
    List<String> fibreDiets = new ArrayList<String>(Arrays.asList("Spinach", "Broccoli", "Cabbage", "Lettuce", "", "", ""));
    List<String> lowFatDiets = new ArrayList<String>(Arrays.asList("No diary", "No nuts", "No fatty fish", "No oily food", "", "", ""));
    List<String> lowCarbDiets = new ArrayList<String>(Arrays.asList("No bread", "Small rice", "No wheat", "No potatoes", "", "", ""));
    List<String> balancedDiets = new ArrayList<String>(Arrays.asList("Balanced", "Balanced", "Balanced", "Balanced", "", "", ""));

    List<DietNestedItem> protein = new ArrayList<DietNestedItem>();

    List<DietNestedItem> fibre = new ArrayList<DietNestedItem>();

    List<DietNestedItem> lowFat = new ArrayList<DietNestedItem>();

    List<DietNestedItem> lowCarb = new ArrayList<DietNestedItem>();

    List<DietNestedItem> balanced = new ArrayList<DietNestedItem>();

    LinkedHashMap<String, List<String>> thirdLevelProtein = new LinkedHashMap<>();
    LinkedHashMap<String, List<String>> thirdLevelFibre = new LinkedHashMap<>();
    LinkedHashMap<String, List<String>> thirdLevelLowFat = new LinkedHashMap<>();
    LinkedHashMap<String, List<String>> thirdLevelLowCarb = new LinkedHashMap<>();
    LinkedHashMap<String, List<String>> thirdLevelBalanced = new LinkedHashMap<>();

    List<List<DietNestedItem>> secondLevel = new ArrayList<>();
    List<LinkedHashMap<String, List<String>>> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plans);

        setupAdapter();

    }

    private void setupAdapter(){

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

        secondLevel.add(protein);
        secondLevel.add(fibre);
        secondLevel.add(lowFat);
        secondLevel.add(lowCarb);
        secondLevel.add(balanced);

        thirdLevelProtein.put(secondLevel.get(0).get(0).getLabel(), proteinDiets);
        thirdLevelProtein.put(secondLevel.get(0).get(1).getLabel(), proteinDiets);
        thirdLevelProtein.put(secondLevel.get(0).get(2).getLabel(), proteinDiets);
        thirdLevelProtein.put(secondLevel.get(0).get(3).getLabel(), proteinDiets);
        thirdLevelProtein.put(secondLevel.get(0).get(4).getLabel(), proteinDiets);
        thirdLevelProtein.put(secondLevel.get(0).get(5).getLabel(), proteinDiets);
        thirdLevelProtein.put(secondLevel.get(0).get(6).getLabel(), proteinDiets);

        thirdLevelFibre.put(secondLevel.get(1).get(0).getLabel(), fibreDiets);
        thirdLevelFibre.put(secondLevel.get(1).get(1).getLabel(), fibreDiets);
        thirdLevelFibre.put(secondLevel.get(1).get(2).getLabel(), fibreDiets);
        thirdLevelFibre.put(secondLevel.get(1).get(3).getLabel(), fibreDiets);
        thirdLevelFibre.put(secondLevel.get(1).get(4).getLabel(), fibreDiets);
        thirdLevelFibre.put(secondLevel.get(1).get(5).getLabel(), fibreDiets);
        thirdLevelFibre.put(secondLevel.get(1).get(6).getLabel(), fibreDiets);

        thirdLevelLowFat.put(secondLevel.get(2).get(0).getLabel(), lowFatDiets);
        thirdLevelLowFat.put(secondLevel.get(2).get(1).getLabel(), lowFatDiets);
        thirdLevelLowFat.put(secondLevel.get(2).get(2).getLabel(), lowFatDiets);
        thirdLevelLowFat.put(secondLevel.get(2).get(3).getLabel(), lowFatDiets);
        thirdLevelLowFat.put(secondLevel.get(2).get(4).getLabel(), lowFatDiets);
        thirdLevelLowFat.put(secondLevel.get(2).get(5).getLabel(), lowFatDiets);
        thirdLevelLowFat.put(secondLevel.get(2).get(6).getLabel(), lowFatDiets);

        thirdLevelLowCarb.put(secondLevel.get(3).get(0).getLabel(), lowFatDiets);
        thirdLevelLowCarb.put(secondLevel.get(3).get(1).getLabel(), lowFatDiets);
        thirdLevelLowCarb.put(secondLevel.get(3).get(2).getLabel(), lowFatDiets);
        thirdLevelLowCarb.put(secondLevel.get(3).get(3).getLabel(), lowFatDiets);
        thirdLevelLowCarb.put(secondLevel.get(3).get(4).getLabel(), lowFatDiets);
        thirdLevelLowCarb.put(secondLevel.get(3).get(5).getLabel(), lowFatDiets);
        thirdLevelLowCarb.put(secondLevel.get(3).get(6).getLabel(), lowFatDiets);

        thirdLevelBalanced.put(secondLevel.get(4).get(0).getLabel(), balancedDiets);
        thirdLevelBalanced.put(secondLevel.get(4).get(1).getLabel(), balancedDiets);
        thirdLevelBalanced.put(secondLevel.get(4).get(2).getLabel(), balancedDiets);
        thirdLevelBalanced.put(secondLevel.get(4).get(3).getLabel(), balancedDiets);
        thirdLevelBalanced.put(secondLevel.get(4).get(4).getLabel(), balancedDiets);
        thirdLevelBalanced.put(secondLevel.get(4).get(5).getLabel(), balancedDiets);
        thirdLevelBalanced.put(secondLevel.get(4).get(6).getLabel(), balancedDiets);


        data.add(thirdLevelProtein);
        data.add(thirdLevelFibre);
        data.add(thirdLevelLowFat);
        data.add(thirdLevelLowCarb);
        data.add(thirdLevelBalanced);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableDietList);
        expandableListTitle = new ArrayList<DietTitleItem>();
        expandableListTitle.add(new DietTitleItem("#594287", "High-protein diet", R.drawable.protein));
        expandableListTitle.add(new DietTitleItem("#84B83C", "High-fibre diet", R.drawable.vegetables));
        expandableListTitle.add(new DietTitleItem("#F9EC00", "Low-fat diet", R.drawable.lowfat));
        expandableListTitle.add(new DietTitleItem("#FB8A20", "Low-carb diet", R.drawable.lowcarb));
        expandableListTitle.add(new DietTitleItem("#87D3E7", "Balanced diet", R.drawable.balanced_diet));
        //passing three level of information to constructor
        DietListAdapter expandableListAdapter = new DietListAdapter(this, expandableListTitle, secondLevel, data);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListAdapter.notifyDataSetChanged();

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });


    }
}
