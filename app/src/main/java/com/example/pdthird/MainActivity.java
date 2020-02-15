package com.example.pdthird;

import android.content.Context;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
//some change
public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
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

       FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu(view);
            }
        });
        btnReminder = findViewById(R.id.buttonReminder);

        reminderItems = new ArrayList<ReminderItem>();
        reminderLVAdapter = new ReminderListViewAdapter(MainActivity.this, R.layout.custom_list_view, reminderItems);
        reminderItems.add(new ReminderItem("10 glasses of water", R.drawable.glass2));
        reminderItems.add(new ReminderItem("5 minutes of meditation", R.drawable.meditation2));
        reminderItems.add(new ReminderItem("9000 steps", R.drawable.steps));
        reminderItems.add(new ReminderItem("7-9 Hours of sleep", R.drawable.sleep));
        reminderItems.add(new ReminderItem("1 physical activity", R.drawable.physical_activity));



        btnReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View row = inflater.inflate(R.layout.dialog, null);
                lv = (ListView)row.findViewById(R.id.lvReminderItems);
                lv.setAdapter(reminderLVAdapter);
                reminderLVAdapter.notifyDataSetChanged();

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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
