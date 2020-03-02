package com.example.pdthird;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;

import com.anychart.charts.Waterfall;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;


public class BmiDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_data_layout);

        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat sdfSG = new SimpleDateFormat("dd-M-yyyy");
        TimeZone tzInSG = TimeZone.getTimeZone("Asia/Singapore");
        sdfSG.setTimeZone(tzInSG);
        sdfSG.format(calendar.getTime());
        calendar.setTime(date);
        calendar.setTimeZone(tzInSG);

        String time = sdfSG.format(calendar.getTime());
        String month = time.substring(0,3);
        Intent intent = getIntent();
        double bmi = intent.getDoubleExtra("bmiResult", 0.0);
        double rounded = Math.round(bmi * 10)/10.0;

        AlertDialog alertDialog = new AlertDialog.Builder(BmiDataActivity.this).create();
        alertDialog.setTitle("Date");
        alertDialog.setMessage(time+" "+rounded);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
        Waterfall waterfall = AnyChart.waterfall();

        waterfall.title("BMI data over months");

        waterfall.yScale().minimum(0d);

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
        data.add(new ValueDataEntry("Jan", 25));
        data.add(new ValueDataEntry("Feb", -46));
        data.add(new ValueDataEntry("Mar", 10));
        data.add(new ValueDataEntry("Apr", 37));
        data.add(new ValueDataEntry("May", -21));
        data.add(new ValueDataEntry("Jun", 53));
        data.add(new ValueDataEntry("Jul", 31));
        data.add(new ValueDataEntry("Aug", -15));
        data.add(new ValueDataEntry("Sep", 42));
        data.add(new ValueDataEntry("Oct", 53));
        data.add(new ValueDataEntry("Nov", -15));
        data.add(new ValueDataEntry("Dec", 51));
        DataEntry end = new DataEntry();
        end.setValue("x", "End");
        end.setValue("isTotal", true);
        data.add(end);

        waterfall.data(data);


        AnyChartView anyChartView = (AnyChartView) findViewById(R.id.any_chart_view);
        anyChartView.setChart(waterfall);
    }


}
