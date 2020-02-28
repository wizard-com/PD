package com.example.pdthird;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NutritionValueActivity extends AppCompatActivity {
    Button btnViewData;
    ListView lvNutritionItems;
    ArrayList<NutritionItem> nutritionItems;
    NutritionListViewAdapter nutritionLVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_calculator_form);
        btnViewData = findViewById(R.id.viewLineGraph);
        lvNutritionItems = findViewById(R.id.listViewNutrition);

        nutritionItems = new ArrayList<NutritionItem>();
        nutritionLVAdapter = new NutritionListViewAdapter(NutritionValueActivity.this, R.layout.custom_nutrition_list, nutritionItems);
        lvNutritionItems.setAdapter(nutritionLVAdapter);


        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(NutritionValueActivity.this, NutritionDataActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
