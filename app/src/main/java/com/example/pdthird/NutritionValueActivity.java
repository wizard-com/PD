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

import java.util.ArrayList;

public class NutritionValueActivity extends AppCompatActivity {

    ViewPager viewPager;
    CustomPageAdapter customPageAdapter;
    ArrayList<PageItem> pageItems;
    EditText etFood, etQty;
    Button btnViewData, btnAdd, btnDelete;
    int current_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_calculator_form);

        viewPager = findViewById(R.id.chartViewPager);
        btnViewData = findViewById(R.id.viewLineGraph);
        btnAdd = findViewById(R.id.add);
        btnDelete = findViewById(R.id.delete);
        etFood = findViewById(R.id.editTextFood);
        etQty = findViewById(R.id.editTextQty);

        pageItems = new ArrayList<PageItem>();






        customPageAdapter = new CustomPageAdapter(NutritionValueActivity.this, pageItems);
        viewPager.setAdapter(customPageAdapter);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                current_position = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog alertDialog = new AlertDialog.Builder(NutritionValueActivity.this).create();
                alertDialog.setTitle("Confirmation");
                alertDialog.setMessage("Are you sure you want to delete?"+current_position+" "+pageItems.size());

                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                pageItems.remove(current_position);
                                customPageAdapter.notifyDataSetChanged();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
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
                    ArrayList<DataEntry> entries = new ArrayList<DataEntry>();
                    entries.add(new ValueDataEntry("First", Integer.parseInt(qty)));
                    entries.add(new ValueDataEntry("Second", Integer.parseInt(qty)+20));
                    entries.add(new ValueDataEntry("Third", Integer.parseInt(qty)+50));
                    pageItems.add(new PageItem(new AnyChart(), entries));
                    customPageAdapter.notifyDataSetChanged();
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
