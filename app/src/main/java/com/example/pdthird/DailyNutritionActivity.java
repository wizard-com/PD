package com.example.pdthird;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class DailyNutritionActivity extends AppCompatActivity {

    AnyChartView anyChartView;
    double[] storedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_nutrition);

        anyChartView = (AnyChartView) findViewById(R.id.any_chart_view_daily);
        Intent intent = getIntent();
        double[] dataArray = intent.getDoubleArrayExtra("nutrient_value");

        Date date = new Date();
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Singapore");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.setTimeZone(timeZone);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        df.setTimeZone(timeZone);
        String formattedDate = df.format(calendar.getTime());

        String day = formattedDate.substring(0, 6);
        String today = formattedDate.substring(0,2);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(DailyNutritionActivity.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        AlertDialog alertDialog = new AlertDialog.Builder(DailyNutritionActivity.this).create();

        if (sharedPreferences.getString(day, null) != null) {

            Gson gson = new Gson();

            String json = sharedPreferences.getString(day, null);

            Type type = new TypeToken<double[]>() {
            }.getType();
            storedData = gson.fromJson(json, type);


            storedData[0] += dataArray[0];
            storedData[1] += dataArray[1];
            storedData[2] += dataArray[2];
            storedData[3] += dataArray[3];

            dataArray[0] = storedData[0];
            dataArray[1] = storedData[1];
            dataArray[2] = storedData[2];
            dataArray[3] = storedData[3];


        }

        Gson gson = new Gson();
        String json = gson.toJson(dataArray);
        editor.putString(day, json);
        editor.putString("today", today);

        editor.apply();

        if(sharedPreferences.getString("today", null) != null && !today.equals(sharedPreferences.getString("today", null))){
            editor.remove(day).apply();
            Log.d("Status","SharedPreference removed.");
        }

        Pie pie = AnyChart.pie();

        pie.title("Nutrition data for "+day);

        List<DataEntry> data = new ArrayList<>();

        if (sharedPreferences.getString(day, null) != null){

            Gson gson2 = new Gson();
            String json2 = sharedPreferences.getString(day, null);

            Type type = new TypeToken<double[]>() {}.getType();
            double[] array = gson2.fromJson(json2, type);

            Log.d("Protein", array[0]+"");
            Log.d("Fat", array[1]+"");
            Log.d("Carb", array[2]+"");
            Log.d("Fibre", array[3]+"");

            data.add(new ValueDataEntry("Protein", array[0]));
            data.add(new ValueDataEntry("Fat", array[1]));
            data.add(new ValueDataEntry("Carbohydrate", array[2]));
            data.add(new ValueDataEntry("Fibre", array[3]));

            String message = "It appears that ";

            if (array[0] <= 80){
                message += "your daily protein intake is a bit low. Why don't you check out and try some high-protein diet plans?";
            }

            if (array[1] >= 77){
                message += "your daily fat intake is a bit high. Why don't you check out and try some low-fat diet plans?";
            }

            if (array[2] >= 325){
                message += "your daily carbohydrate intake is a bit high. Why don't you check out and try some low-carb diet plans?";
            }

            if (array[3] <= 30){
                message += "your daily fibre intake is a bit low. Why don't you check out and try some high-fibre diet plans?";
            }

            alertDialog.setTitle("Reminder");
            alertDialog.setMessage(message);

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Check diet plans", new DialogInterface.OnClickListener() {
                public void onClick (DialogInterface dialog,int which){
                dialog.dismiss();

                Intent myIntent = new Intent(DailyNutritionActivity.this, DietPlansActivity.class);
                startActivity(myIntent);
            }
            });

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Not now", new DialogInterface.OnClickListener() {
                public void onClick (DialogInterface dialog,int which){
                    dialog.dismiss();
                }
            });

            alertDialog.show();
        }
        pie.data(data);
        anyChartView.setChart(pie);
    }

}
