package com.example.pdthird;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

public class CustomPageAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<AnyChart> chartViews;


    public CustomPageAdapter(Context context, ArrayList<AnyChart> chartViews) {
        this.context = context;
        this.chartViews = chartViews;
    }

    @Override
    public int getCount() {
        return chartViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        AnyChart chart = chartViews.get(position);
        View view = layoutInflater.inflate(R.layout.slider_item, container, false);
        AnyChartView chartView = view.findViewById(R.id.any_chart_item);

        chartView.setChart(chart.pie());
        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.refreshDrawableState();

    }
}
