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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

public class NutritionDataActivity extends AppCompatActivity {

    AnyChartView anyChartView;
    HashMap<String, double[]> hashMapNutrition = new HashMap<String, double[]>();
    SharedPreferences sharedpreferences;

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
        String month = time.substring(3,6);
        int current_year = Integer.parseInt(time.substring(7,11));

        Cartesian cartesian = AnyChart.line();

        cartesian.animation(true);

        Intent intent = getIntent();
        double[] data = intent.getDoubleArrayExtra("nutrient_value");

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        if (sharedPreferences.getString("Protein", null) != null && sharedPreferences.getString("Fat", null) != null
            && sharedPreferences.getString("Carbs", null) != null && sharedPreferences.getString("Fibres", null) != null){

            double[] childArray = hashMapNutrition.get(month);

            double proteinValue = Double.parseDouble(sharedPreferences.getString("Protein", null));
            double fatValue = Double.parseDouble(sharedPreferences.getString("Fat", null));
            double carbValue = Double.parseDouble(sharedPreferences.getString("Carbs", null));
            double fibreValue = Double.parseDouble(sharedPreferences.getString("Fibres", null));

            proteinValue += data[0];
            fatValue += data[1];
            carbValue += data[2];
            fibreValue += data[3];

            childArray[0] += proteinValue;
            childArray[1] += fatValue;
            childArray[2] += carbValue;
            childArray[3] += fibreValue;

            hashMapNutrition.put(month, childArray);

        }
        else {
            hashMapNutrition.put(month, data);
        }

        Set<String> stringList = new ArrayList<>();

        stringList.add(String.format("%.2f", hashMapNutrition.get(month)[0]));
        stringList.add(String.format("%.2f", hashMapNutrition.get(month)[1]));
        stringList.add(String.format("%.2f", hashMapNutrition.get(month)[2]));
        stringList.add(String.format("%.2f", hashMapNutrition.get(month)[3]));

        editor.putStringSet(month, stringList);
        editor.commit();

        cartesian.title("Nutrition data over months");

        cartesian.yAxis(0).title("Macro nutrients in grams(g)");
        List<DataEntry> seriesData = new ArrayList<>();

        seriesData.add(new CustomDataEntry(month, 10.0, 7.8, 15.5, 12.4));
        seriesData.add(new CustomDataEntry("April", 0.0, 0.0, 0.0, 0.0));
        seriesData.add(new CustomDataEntry("May", 0.0, 0.0, 0.0, 0.0));
        seriesData.add(new CustomDataEntry("June", 0.0, 0.0, 0.0, 0.0));


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
