package com.example.pdthird;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class BmiValueActivity extends AppCompatActivity {

    Button btnCalcBMI, btnViewWaterfallChart;
    TextView tvBmiResult;
    EditText etWeight, etHeight;
    SharedPreferences sharedPreferences;
    int[] splittedDayMonth;
    String month;
    double bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_calculator_form);

        btnCalcBMI = findViewById(R.id.buttonCalcBMI);
        btnViewWaterfallChart = findViewById(R.id.viewWaterfallChart);
        tvBmiResult = findViewById(R.id.textViewBMIResult);
        etWeight = findViewById(R.id.editTextWeight);
        etHeight = findViewById(R.id.editTextHeight);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat sdfSG = new SimpleDateFormat("dd-MM-yyyy");
        TimeZone tzInSG = TimeZone.getTimeZone("Asia/Singapore");
        sdfSG.setTimeZone(tzInSG);
        sdfSG.format(calendar.getTime());
        calendar.setTime(date);
        calendar.setTimeZone(tzInSG);

        String time = sdfSG.format(calendar.getTime());
        month = time.substring(3,6);
        String dayAndMonth = time.substring(0,5);
        String[] day_month = dayAndMonth.split("-");

        splittedDayMonth = new int[2];

        splittedDayMonth[0] = Integer.parseInt(day_month[0]);
        splittedDayMonth[1] = Integer.parseInt(day_month[1]);

//
//        AlertDialog alertDialog = new AlertDialog.Builder(BmiValueActivity.this).create();
//        alertDialog.setTitle("Date");
//        alertDialog.setMessage(splittedDayMonth[0]+" "+splittedDayMonth[1]);
//        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//        alertDialog.show();

        btnCalcBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String weight = etWeight.getText().toString();
                String height = etHeight.getText().toString();


                if(weight.length() == 0 || height.length() == 0){

                    AlertDialog alertDialog = new AlertDialog.Builder(BmiValueActivity.this).create();
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

                    if (isValidDate()) {
                        double weightToFloat = Double.parseDouble(weight);
                        double heightToFloat = Double.parseDouble(height);

                        bmi = weightToFloat / (heightToFloat * heightToFloat);
                        double rounded = Math.round(bmi * 10) / 10.0;
                        String result = "";
                        String colorCode = "";
                        BMIResult bmiResult = new BMIResult(bmi, colorCode, result);

                        if (rounded < 18.5) {
                            result = "Your BMI is " + rounded + "\n You are underweight";
                            colorCode = "#8cc6e0";
                        } else if (rounded >= 18.5 && rounded <= 25) {
                            result = "Your BMI is " + rounded + "\n You are in healthy range";
                            colorCode = "#34F42E";
                        } else if (rounded > 25 && rounded <= 30) {
                            result = "Your BMI is " + rounded + "\n You are overweight";
                            colorCode = "#F8BB23";
                        } else {
                            result = "Your BMI is " + rounded + "\n You are obese";
                            colorCode = "#F85A23";
                        }

                        bmiResult.setColor(colorCode);
                        bmiResult.setText(result);
                        tvBmiResult.setTextColor(Color.parseColor(bmiResult.getColorCode()));
                        tvBmiResult.setText(bmiResult.getText());

                        editor.putInt(month, splittedDayMonth[1]);
                        editor.apply();
                    }
                    else {
                        AlertDialog alertDialog = new AlertDialog.Builder(BmiValueActivity.this).create();
                        alertDialog.setTitle("Reminder");
                        alertDialog.setMessage("It appears that you have calculated BMI this month. Please try again next month.");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                }
            }
        });

        btnViewWaterfallChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(BmiValueActivity.this, BmiDataActivity.class);
                myIntent.putExtra("bmiResult", bmi);
                startActivity(myIntent);
            }
        });
    }

    private boolean isValidDate(){

        if(sharedPreferences.getInt(month, 0) != 0){

            return splittedDayMonth[1] > sharedPreferences.getInt(month, 0);
        }
        return true;
    }
}
