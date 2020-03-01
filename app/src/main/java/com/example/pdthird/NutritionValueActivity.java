package com.example.pdthird;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.annotations.Line;

import java.util.ArrayList;
import java.util.List;

public class NutritionValueActivity extends AppCompatActivity {

    ViewPager viewPager;
    CustomPageAdapter customPageAdapter;
    ArrayList<Integer> pageItems;
    EditText etFood, etQty;
    Button btnViewData, btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_calculator_form);

        viewPager = findViewById(R.id.chartViewPager);
        btnViewData = findViewById(R.id.viewLineGraph);
        btnAdd = findViewById(R.id.add);
        etFood = findViewById(R.id.editTextFood);
        etQty = findViewById(R.id.editTextQty);

        pageItems = new ArrayList<Integer>();



        pageItems.add(R.drawable.bmi);
        pageItems.add(R.drawable.healthfacts);
        pageItems.add(R.drawable.steps);
        pageItems.add(R.drawable.glass2);

        customPageAdapter = new CustomPageAdapter(NutritionValueActivity.this, pageItems);
        viewPager.setAdapter(customPageAdapter);
        customPageAdapter.notifyDataSetChanged();

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
