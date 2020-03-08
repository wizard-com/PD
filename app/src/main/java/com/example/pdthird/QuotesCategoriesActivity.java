package com.example.pdthird;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class QuotesCategoriesActivity extends AppCompatActivity {

    GridView gridView;
    QuotesGridAdapter quotesGridAdapter;
    ArrayList<GridItem> gridItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_categories);

        gridView = findViewById(R.id.gridViewCategories);
        gridItems = new ArrayList<GridItem>();
        gridItems.add(new GridItem("Life", R.drawable.life, "#167DC2"));
        gridItems.add(new GridItem("Health", R.drawable.health, "#D3234B"));
        gridItems.add(new GridItem("Happiness", R.drawable.happiness, "#F8981D"));
        gridItems.add(new GridItem("Motivational", R.drawable.motivation, "#FFCC33"));
        gridItems.add(new GridItem("Knowledge", R.drawable.knowledge, "#4AA84A"));
        gridItems.add(new GridItem("Art", R.drawable.art, "#D93E8F"));

        quotesGridAdapter = new QuotesGridAdapter(QuotesCategoriesActivity.this, R.layout.custom_grid_item, gridItems);
        quotesGridAdapter.notifyDataSetChanged();
        gridView.setAdapter(quotesGridAdapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String[] dataArr = new String[2];
                String url_path = "";
                String color_code = "";

                if(position == 0){
                    url_path = "?filter=life&type=tag";
                    color_code = "#167DC2";
                }
                else if (position == 1){
                    url_path = "?filter=health&type=tag";
                    color_code = "#D3234B";
                }
                else if (position == 2){
                    url_path = "?filter=happiness&type=tag";
                    color_code = "#F8981D";
                }
                else if (position == 3){
                    url_path = "?filter=motivational&type=tag";
                    color_code = "#FFCC33";
                }
                else if (position == 4){
                    url_path = "?filter=knowledge&type=tag";
                    color_code = "#4AA84A";
                }
                else {
                    url_path = "?filter=health&type=tag";
                    color_code = "#D93E8F";
                }
                dataArr[0] = url_path;
                dataArr[1] = color_code;
                Intent myIntent = new Intent(QuotesCategoriesActivity.this, QuotesActivity.class);
                myIntent.putExtra("path_and_color", dataArr);
                startActivity(myIntent);
            }
        });
    }
}


