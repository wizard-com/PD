package com.example.pdthird;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NutritionValueActivity extends AppCompatActivity {

    ViewPager viewPager;
    NutritionPageAdapter nutritionPageAdapter;
    ArrayList<PageItem> pageItems;
    EditText etFood, etQty;
    Button btnViewData, btnAdd, btnDelete;
    int current_position;
    ArrayList<ImageView> dots;
    RequestQueue requestQueue;
    LinearLayout dotsContainer, linearLayout;
    ProgressBar progressBar;
    String foodName, qty;
    HashMap<String, HashMap<String, Double>> nutrientMappings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_value);


        requestQueue = Volley.newRequestQueue(NutritionValueActivity.this);
        nutrientMappings = new HashMap<String, HashMap<String, Double>>();

        viewPager = findViewById(R.id.chartViewPager);
        btnViewData = findViewById(R.id.viewLineGraph);
        btnAdd = findViewById(R.id.add);
        btnDelete = findViewById(R.id.delete);
        etFood = findViewById(R.id.editTextFood);
        etQty = findViewById(R.id.editTextQty);
        dotsContainer = findViewById(R.id.dotsContainer);

        pageItems = new ArrayList<PageItem>();
        dots = new ArrayList<ImageView>();

        nutritionPageAdapter = new NutritionPageAdapter(NutritionValueActivity.this, pageItems);
        viewPager.setAdapter(nutritionPageAdapter);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                current_position = position;
                for (int i = 0; i < nutritionPageAdapter.getCount(); i++){
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


                if(nutritionPageAdapter.getCount() < 1){
                    alertDialog.setTitle("Warning");
                    alertDialog.setMessage("No more items to be deleted.");

                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                }
                else {

                    alertDialog.setTitle("Confirmation");
                    alertDialog.setMessage("Are you sure you want to delete?");

                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    if(nutritionPageAdapter.getCount() == 1){
                                        current_position = 0;
                                    }
                                    nutrientMappings.remove(nutritionPageAdapter.getPageItem(current_position).getTitle());
                                    nutritionPageAdapter.removeView(viewPager, current_position);
                                    dots.remove(current_position);
                                    dotsContainer.removeViewAt(current_position);
                                    nutritionPageAdapter.notifyDataSetChanged();
                                    viewPager.setCurrentItem(nutritionPageAdapter.getCount()-1);


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
                }


                alertDialog.show();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpProgressBar();
                foodName = etFood.getText().toString();
                foodName = foodName.toLowerCase();
                qty = etQty.getText().toString();


                if(foodName.length() == 0 || qty.length() == 0){
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
                    makeAPICall();
                }
            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog alertDialog = new AlertDialog.Builder(NutritionValueActivity.this).create();

                    alertDialog.setTitle("Warning");
                    alertDialog.setMessage("Are you sure you want to proceed and see the line graph? Once done the data in line graph cannot be deleted.");

                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent myIntent = new Intent(NutritionValueActivity.this, NutritionDataActivity.class);

                            double[] dataArr = new double[4];

                            double netProtein = 0.0;
                            double netCarbs = 0.0;
                            double netFibre = 0.0;
                            double netFat = 0.0;

                            for (String key : nutrientMappings.keySet()) {

                                HashMap<String, Double> data = nutrientMappings.get(key);

                                if(data.get("Protein") != null) {
                                    netProtein += data.get("Protein");
                                }
                                if (data.get("Carbs") != null) {
                                    netCarbs += data.get("Carbs");
                                }
                                if (data.get("Fat") != null) {
                                    netFat += data.get("Fat");
                                }
                                if (data.get("Fibre") != null) {
                                    netFibre += data.get("Fibre");
                                }
                            }
                            dataArr[0] = netProtein;
                            dataArr[1] = netFat;
                            dataArr[2] = netCarbs;
                            dataArr[3] = netFibre;

                            myIntent.putExtra("nutrient_value", dataArr);
                            startActivity(myIntent);
                        }
                    });

                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                alertDialog.show();
                }

        });
    }

    private void makeAPICall(){
        String url = "https://api.edamam.com/api/food-database/parser?ingr="+foodName+"&app_id=54e01c26&app_key=f8ec4fdaa335cd26821dcce5b00311fa";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("parsed");

                    JSONObject object = array.getJSONObject(0).getJSONObject("food").getJSONObject("nutrients");
                    ArrayList<DataEntry> entries = new ArrayList<DataEntry>();

                    int multiplier = Integer.parseInt(qty)/100;

                    HashMap<String, Double> mappings = new HashMap<String, Double>();

                    if(object.has("PROCNT")){
                        entries.add(new ValueDataEntry("Protein", object.getDouble("PROCNT")*multiplier));
                        mappings.put("Protein", object.getDouble("PROCNT")*multiplier);
                    }
                    if(object.has("FAT")){
                        entries.add(new ValueDataEntry("Fat", object.getDouble("FAT")*multiplier));
                        mappings.put("Fat", object.getDouble("FAT")*multiplier);
                    }
                    if(object.has("CHOCDF")){
                        entries.add(new ValueDataEntry("Carbs", object.getDouble("CHOCDF")*multiplier));
                        mappings.put("Carbs", object.getDouble("CHOCDF")*multiplier);
                    }
                    if(object.has("FIBTG")){
                        entries.add(new ValueDataEntry("Fibre", object.getDouble("FIBTG")*multiplier));
                        mappings.put("Fibre", object.getDouble("FIBTG")*multiplier);
                    }
                    nutrientMappings.put(foodName, mappings);

                    nutritionPageAdapter.addView(new PageItem(new AnyChart(), entries, foodName), nutritionPageAdapter.getCount());
                    nutritionPageAdapter.notifyDataSetChanged();
                    dots.add(new ImageView(NutritionValueActivity.this));
                    dots.get(dots.size()-1).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.inactive_dots));
                    dotsContainer.addView(dots.get(dots.size()-1));
                    dots.get(current_position).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dots));
                    progressBar.setVisibility(View.GONE);
                }
                catch (JSONException e){
                    e.printStackTrace();
                    AlertDialog alertDialog = new AlertDialog.Builder(NutritionValueActivity.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("The item you just added probably doesn't exists or there's a network connection time out. Please try again.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);

    }

    public static boolean isParsable(String input){
        try{
            Integer.parseInt(input);
            return true;
        }catch(ParseException e){
            return false;
        }
    }

    private void setUpProgressBar(){
        progressBar = new ProgressBar(NutritionValueActivity.this, null, android.R.attr.progressBarStyleLarge);
        linearLayout = findViewById(R.id.ntLayout);

        LinearLayout.LayoutParams pLayoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        progressBar.setLayoutParams(pLayoutParams);
        pLayoutParams.gravity = Gravity.CENTER;
        linearLayout.addView(progressBar);
    }
}
