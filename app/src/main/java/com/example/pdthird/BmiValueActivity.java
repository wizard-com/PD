package com.example.pdthird;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BmiValueActivity extends AppCompatActivity {

    Button btnCalcBMI, btnViewWaterfallChart;
    TextView tvBmiResult;
    EditText etWeight, etHeight;
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
                    double weightToFloat = Double.parseDouble(weight);
                    double heightToFloat = Double.parseDouble(height);

                    bmi = weightToFloat / (heightToFloat * heightToFloat);
                    double rounded = Math.round(bmi * 10)/10.0;
                    String result = "";
                    String colorCode = "";
                    BMIResult bmiResult = new BMIResult(bmi, colorCode, result);


                    if (rounded < 18.5){
                        result = "Your BMI is "+rounded+"\n You are underweight";
                        colorCode = "#8cc6e0";
                    }
                    else if(rounded >= 18.5 && rounded <= 25){
                        result = "Your BMI is "+rounded+"\n You are in healthy range";
                        colorCode = "#34F42E";
                    }
                    else if(rounded > 25 && rounded <= 30){
                        result = "Your BMI is "+rounded+"\n You are overweight";
                        colorCode = "#F8BB23";
                    }
                    else {
                        result = "Your BMI is "+rounded+"\n You are obese";
                        colorCode = "#F85A23";
                    }

                    bmiResult.setColor(colorCode);
                    bmiResult.setText(result);
                    tvBmiResult.setTextColor(Color.parseColor(bmiResult.getColorCode()));
                    tvBmiResult.setText(bmiResult.getText());

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
