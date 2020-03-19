package com.example.pdthird;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;

import com.anychart.charts.Waterfall;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;


public class BmiDataActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    String[] months;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_data_layout);

        Waterfall waterfall = AnyChart.waterfall();

        waterfall.title("BMI data over months");

        waterfall.yScale().minimum(0d);

        months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat sdfSG = new SimpleDateFormat("dd-MMM-yyyy");
        TimeZone tzInSG = TimeZone.getTimeZone("Asia/Singapore");
        sdfSG.setTimeZone(tzInSG);
        sdfSG.format(calendar.getTime());
        calendar.setTime(date);
        calendar.setTimeZone(tzInSG);

        String time = sdfSG.format(calendar.getTime());
        String month = time.substring(3,6);
        Intent intent = getIntent();
        double bmi = intent.getDoubleExtra("bmiResult", 0.0);
        double rounded = Math.round(bmi * 10)/10.0;


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        if(bmi != 0.0){

            Gson gson = new Gson();
            String json = gson.toJson(bmi);
            editor.putString(month, json);
            editor.apply();

        }

//        waterfall.yAxis(0).labels().format("${%Value}{scale:(1000000)(1)|(mln)}");
//        waterfall.labels().enabled(true);
//        waterfall.labels().format(
//                "function() {\n" +
//                        "      if (this['isTotal']) {\n" +
//                        "        return anychart.format.number(this.absolute, {\n" +
//                        "          scale: true\n" +
//                        "        })\n" +
//                        "      }\n" +
//                        "\n" +
//                        "      return anychart.format.number(this.value, {\n" +
//                        "        scale: true\n" +
//                        "      })\n" +
//                        "    }");

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Start", 23));

        if(sharedPreferences.getAll() != null) {

            Map<String, ?> keys = sharedPreferences.getAll();
            Gson gson2 = new Gson();

            for (Map.Entry<String, ?> key : keys.entrySet()) {

                if (Arrays.asList(months).contains(key.getKey())) {
                    String json2 = sharedPreferences.getString(key.getKey(), null);
                    Type type = new TypeToken<Double>() {
                    }.getType();
                    Double bmiValue = gson2.fromJson(json2, type);
                    double difference = 0.0;

                    if (bmiValue > 23) {
                        difference = bmiValue - 23;
                    } else {
                        difference = 23 - bmiValue;
                    }
                    data.add(new ValueDataEntry(key.getKey(), difference));

                }
            }


        }


        DataEntry end = new DataEntry();
        end.setValue("x", "End");
        end.setValue("isTotal", true);
        data.add(end);

        waterfall.data(data);


        AnyChartView anyChartView = (AnyChartView) findViewById(R.id.any_chart_view);
        anyChartView.setChart(waterfall);
    }


}
