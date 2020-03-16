package com.example.pdthird;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

import com.example.pdthird.WorkoutProgramsData;

public class WorkOutProgramsActivity extends AppCompatActivity {

private Spinner spinner;
private TextView textView;
private WorkOutProgramAdapter workOutProgramAdapter;
private ArrayList<WorkOutItem> workOutItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_programs);

        spinner = findViewById(R.id.spinnerWorkoutTypes);
        textView = findViewById(R.id.tvWorkoutPrograms);

        workOutItems = new ArrayList<WorkOutItem>();
        workOutItems.add(new WorkOutItem("LIIT", "#06FF45", R.drawable.liit));
        workOutItems.add(new WorkOutItem("MIIT", "#FF6D00", R.drawable.miit));
        workOutItems.add(new WorkOutItem("HIIT", "#ED1C24", R.drawable.hiit));

        workOutProgramAdapter = new WorkOutProgramAdapter(WorkOutProgramsActivity.this, R.layout.custom_spinner_item, workOutItems);

        spinner.setAdapter(workOutProgramAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                textView.setText("");
                WorkOutItem workOutItem = (WorkOutItem) parent.getSelectedItem();
                String type = workOutItem.getType();
                String[] data = WorkoutProgramsData.getData(type);

                for (int i = 0; i < data.length-1; i++){
                    textView.append(data[i]);
                }
                textView.setBackgroundColor(Color.parseColor(data[data.length-1]));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



}
