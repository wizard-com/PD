package com.example.pdthird;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import androidx.preference.PreferenceManager;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class BmiValueActivity extends AppCompatActivity {

    Button btnCalcBMI, btnViewWaterfallChart;
    TextView tvBmiResult;
    EditText etWeight, etHeight;
    SharedPreferences sharedPreferences;
    double bmi;
    String colorCode;
    boolean isSame;

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


        final AlertDialog alertDialog = new AlertDialog.Builder(BmiValueActivity.this).create();

        isSame = false;

        btnCalcBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String weight = etWeight.getText().toString();
                String height = etHeight.getText().toString();


                if(weight.length() == 0 || height.length() == 0){

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
                else if (sharedPreferences.getString("weight", null) != null && sharedPreferences.getString("height", null) != null){

                    double prevWeight = Double.parseDouble(sharedPreferences.getString("weight", "0.0"));

                    double prevHeight = Double.parseDouble(sharedPreferences.getString("height", "0.0"));

                    double weightToFloat = Double.parseDouble(weight);
                    double heightToFloat = Double.parseDouble(height);

                    if (prevHeight == heightToFloat && prevWeight == weightToFloat){
                        isSame = true;
                        alertDialog.setTitle("Reminder");
                        alertDialog.setMessage("It looks like the weight and height you have entered now is exactly the same as the ones you entered previously. Please try again.");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }

                    else {

                        bmi = weightToFloat / (heightToFloat * heightToFloat);
                        double rounded = Math.round(bmi * 10) / 10.0;
                        String result = "";
                        colorCode = "";
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

                        editor.putString("weight", weight);
                        editor.putString("height", height);
                        editor.apply();

                        if (colorCode.equals("#F8BB23") || colorCode.equals("#F85A23")) {
                            alertDialog.setTitle("Reminder");
                            alertDialog.setMessage("It seems like you are either overweight or obese. Why don't you try some workout programmes or try low-fat diet for weight loss and read some jokes for stress relief?");

                            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Try Workout programs", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(BmiValueActivity.this, WorkOutProgramsActivity.class);
                                    startActivity(intent);
                                }
                            });
                            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Read jokes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(BmiValueActivity.this, JokesActivity.class);
                                    startActivity(intent);
                                }
                            });
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Try low-fat diet", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(BmiValueActivity.this, DietPlansActivity.class);
                                    startActivity(intent);
                                }
                            });
                            alertDialog.show();
                        }
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


}
