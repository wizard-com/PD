package com.example.pdthird;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.charts.Cartesian;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class NutritionDataActivity extends AppCompatActivity {

    AnyChartView anyChartView;
    double[] storedData;
    SharedPreferences sharedPreferences, year;
    String[] months;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_data);

        anyChartView = findViewById(R.id.any_chart_view_nutrition);

        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat sdfSG = new SimpleDateFormat("dd-MMM-yyyy");
        TimeZone tzInSG = TimeZone.getTimeZone("Asia/Singapore");

        sdfSG.setTimeZone(tzInSG);
        sdfSG.format(calendar.getTime());
        calendar.setTime(date);
        calendar.setTimeZone(tzInSG);

        String time = sdfSG.format(calendar.getTime());
        String month =  time.substring(3,6);
        int current_year = Integer.parseInt(time.substring(7,11));

        Cartesian cartesian = AnyChart.line();

        cartesian.animation(true);

        months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        Intent intent = getIntent();
        double[] data = intent.getDoubleArrayExtra("nutrient_value");

        List<DataEntry> seriesData = new ArrayList<>();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        year = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor1 = year.edit();

        editor.remove("year").apply();

        if(year.getInt("year", 0) == 0) {
            editor1.putInt("year", current_year);
            editor1.apply();
        }

        if(year.getInt("year", 0) < current_year){
            editor1.clear();
            editor1.putInt("year", current_year);
            editor1.apply();
        }

        if (sharedPreferences.getString(month, null) != null) {

            Gson gson = new Gson();


            String json = sharedPreferences.getString(month, null);
            Type type = new TypeToken<double[]>() {
            }.getType();
            storedData = gson.fromJson(json, type);


            storedData[0] += data[0];
            storedData[1] += data[1];
            storedData[2] += data[2];
            storedData[3] += data[3];

            data[0] = storedData[0];
            data[1] = storedData[1];
            data[2] = storedData[2];
            data[3] = storedData[3];

        }

        Gson gson = new Gson();
        String json = gson.toJson(data);



        editor.putString(month, json);


        editor.apply();

        if(sharedPreferences.getAll() != null) {

            Map<String, ?> keys = sharedPreferences.getAll();
            Gson gson2 = new Gson();

            for (Map.Entry<String, ?> key : keys.entrySet()) {

                if (Arrays.asList(months).contains(key.getKey())) {
                    String json2 = sharedPreferences.getString(key.getKey(), null);
                    Type type = new TypeToken<double[]>() {
                    }.getType();
                    double[] array = gson2.fromJson(json2, type);
                    seriesData.add(new CustomDataEntry(key.getKey(), array[0], array[1], array[2], array[3]));
                }
            }


        }
        cartesian.title("Nutrition data over months");

        cartesian.yAxis(0).title("Macro nutrients in grams(g)");

        seriesData.add(new CustomDataEntry("Apr", 10, 10 , 15, 20));

        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");
        Mapping series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }");
        Mapping series4Mapping = set.mapAs("{ x: 'x', value: 'value4' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name("Protein");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series2 = cartesian.line(series2Mapping);
        series2.name("Fat");
        series2.hovered().markers().enabled(true);
        series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series2.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series3 = cartesian.line(series3Mapping);
        series3.name("Carbohydrates");
        series3.hovered().markers().enabled(true);
        series3.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series3.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series4 = cartesian.line(series4Mapping);
        series4.name("Fibres");
        series4.hovered().markers().enabled(true);
        series4.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series4.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        anyChartView.setChart(cartesian);
    }
    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value, Number value2, Number value3, Number value4) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
            setValue("value4", value4);
        }

    }

}
