package com.example.pdthird;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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
    ArrayList<ImageView> dots;
    LinearLayout dotsContainer;

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
        dotsContainer = findViewById(R.id.dotsContainer);

        pageItems = new ArrayList<PageItem>();
        dots = new ArrayList<ImageView>();

        customPageAdapter = new CustomPageAdapter(NutritionValueActivity.this, pageItems);
        viewPager.setAdapter(customPageAdapter);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                current_position = position;
                for (int i = 0; i < customPageAdapter.getCount(); i++){
                    dots.get(i).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.inactive_dots));
                }
                dots.get(position).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dots));
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

                                customPageAdapter.removeView(viewPager, current_position);
                                dots.remove(current_position);
                                dotsContainer.removeViewAt(current_position);
                                customPageAdapter.notifyDataSetChanged();

                                if(current_position >= 1 && dots.size() > 0){
                                    dots.get(current_position-1).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dots));
                                }
                                else if(current_position == 0 && dots.size() > 0 || dots.size() == 1){
                                    dots.get(0).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dots));
                                }
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
                    customPageAdapter.addView(new PageItem(new AnyChart(), entries, foodName), customPageAdapter.getCount());
                    customPageAdapter.notifyDataSetChanged();
                    dots.add(new ImageView(NutritionValueActivity.this));
                    dots.get(dots.size()-1).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.inactive_dots));
                    dotsContainer.addView(dots.get(dots.size()-1));
                    dots.get(0).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dots));
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
