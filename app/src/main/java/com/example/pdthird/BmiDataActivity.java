package com.example.pdthird;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.PreferenceManager;

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

        months = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat sdfSG = new SimpleDateFormat("dd-MM-yyyy");
        TimeZone tzInSG = TimeZone.getTimeZone("Asia/Singapore");
        sdfSG.setTimeZone(tzInSG);
        sdfSG.format(calendar.getTime());
        calendar.setTime(date);
        calendar.setTimeZone(tzInSG);

        String time = sdfSG.format(calendar.getTime());
        String month = time.substring(3,5);
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
                    AlertDialog alertDialog = new AlertDialog.Builder(BmiDataActivity.this).create();
                    alertDialog.setTitle("Date");
                    alertDialog.setMessage(key.getKey()+" "+json2);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                    if (json2.contains("[") == false) {
                        Type type = new TypeToken<Double>() {
                        }.getType();
                        Double bmiValue = gson2.fromJson(json2, type);
                        double difference = 0.0;

                        difference = bmiValue - 23;

                        data.add(new ValueDataEntry(key.getKey(), difference));

                    }
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