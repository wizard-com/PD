package com.example.pdthird;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    CardView bmiCard, healthNewsCard, nutritionCard, healthFactsCard, moreOptionsCard;
    ListView lv;
    ArrayList<ReminderItem> reminderItems;
    ReminderListViewAdapter reminderLVAdapter;
    Button btnReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        bmiCard = findViewById(R.id.bmiCard);
        healthNewsCard = findViewById(R.id.healthNewsCard);
        nutritionCard = findViewById(R.id.nutritionCard);
        healthFactsCard = findViewById(R.id.healthFactsCard);
        moreOptionsCard = findViewById(R.id.moreOptionsCard);

        healthFactsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, HealthFactsActivity.class);
                startActivity(myIntent);
            }
        });

        nutritionCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, NutritionValueActivity.class);
                startActivity(myIntent);
            }
        });

        healthNewsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, HealthNewsActivity.class);
                startActivity(myIntent);
            }
        });

        bmiCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, BmiValueActivity.class);
                startActivity(myIntent);
            }
        });


        moreOptionsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v);
            }
        });

    }
    public void showMenu(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.pop_up_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        Intent myIntent = null;

        if (item.getItemId() == R.id.quotes) {
            myIntent = new Intent(MainActivity.this, QuotesCategoriesActivity.class);
            startActivity(myIntent);
        }
        else if (item.getItemId() == R.id.jokes) {
            myIntent = new Intent(MainActivity.this, JokesActivity.class);
            startActivity(myIntent);
        }
        else if (item.getItemId() == R.id.workouts){
            myIntent = new Intent(MainActivity.this, WorkOutProgramsActivity.class);
            startActivity(myIntent);
        }
        else if (item.getItemId() == R.id.diet){
            myIntent = new Intent(MainActivity.this, DietPlansActivity.class);
            startActivity(myIntent);
        }
        return true;
    }

}
