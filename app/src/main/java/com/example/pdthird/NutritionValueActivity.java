package com.example.pdthird;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NutritionValueActivity extends AppCompatActivity {

    EditText etFood, etQty;
    Button btnViewData, btnAdd;
    ListView lvNutritionItems;
    ArrayList<NutritionItem> nutritionItems;
    NutritionListViewAdapter nutritionLVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_calculator_form);
        btnViewData = findViewById(R.id.viewLineGraph);
        btnAdd = findViewById(R.id.add);
        lvNutritionItems = findViewById(R.id.listViewNutrition);
        etFood = findViewById(R.id.editTextFood);
        etQty = findViewById(R.id.editTextQty);

        nutritionItems = new ArrayList<NutritionItem>();
        nutritionLVAdapter = new NutritionListViewAdapter(NutritionValueActivity.this, R.layout.custom_nutrition_list, nutritionItems);
        lvNutritionItems.setAdapter(nutritionLVAdapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foodName = etFood.getText().toString();
                String qty = etQty.getText().toString();


                if(foodName.length() == 0 || NutritionValueActivity.isParsable(qty) == false){
                    AlertDialog alertDialog = new AlertDialog.Builder(NutritionValueActivity.this).create();
                    alertDialog.setTitle("Invalid input");
                    alertDialog.setMessage("Please try again.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                else {
                    NutritionItem item = new NutritionItem(foodName, Integer.parseInt(qty));
                    nutritionItems.add(item);
                    nutritionLVAdapter.notifyDataSetChanged();
                }
            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(NutritionValueActivity.this, NutritionDataActivity.class);
                startActivity(myIntent);
            }
        });
    }
    public static boolean isParsable(String input){
        try{
            Integer.parseInt(input);
            return true;
        }catch(ParseException e){
            return false;
        }
    }
}
