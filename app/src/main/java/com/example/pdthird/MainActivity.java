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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

        btnReminder = findViewById(R.id.buttonReminder);

        reminderItems = new ArrayList<ReminderItem>();
        reminderItems.add(new ReminderItem("10 glasses of water", R.drawable.glass2));
        reminderItems.add(new ReminderItem("5 minutes of meditation", R.drawable.meditation2));
        reminderItems.add(new ReminderItem("9000 steps", R.drawable.steps));
        reminderItems.add(new ReminderItem("7-9 Hours of sleep", R.drawable.sleep));
        reminderItems.add(new ReminderItem("1 physical activity", R.drawable.physical_activity));
        reminderLVAdapter = new ReminderListViewAdapter(MainActivity.this, R.layout.custom_list_view, reminderItems);


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

        btnReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View row = inflater.inflate(R.layout.dialog, null);
                lv = (ListView)row.findViewById(R.id.lvReminderItems);
                lv.setAdapter(reminderLVAdapter);
                reminderLVAdapter.notifyDataSetChanged();
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setNeutralButton("Ok", null);
                alert.setView(row);
                AlertDialog dialog = alert.create();
                dialog.show();
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
        if (item.getItemId() == R.id.mental) {
            Toast.makeText(this, "Mental", Toast.LENGTH_LONG).show();
            if (item.getItemId() == R.id.quotes){
                Toast.makeText(this, "Quotes", Toast.LENGTH_LONG).show();
            }
            return true;
        }
       else {
            Toast.makeText(this, "Physical", Toast.LENGTH_LONG).show();
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
