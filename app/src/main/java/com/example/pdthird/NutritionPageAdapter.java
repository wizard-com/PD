package com.example.pdthird;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.anychart.AnyChartView;

import com.anychart.charts.Pie;

import java.util.ArrayList;

public class NutritionPageAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<PageItem> chartViews;


    public NutritionPageAdapter(Context context, ArrayList<PageItem> chartViews) {
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
        PageItem chart = chartViews.get(position);
        View view = layoutInflater.inflate(R.layout.custom_nutrition_chart, container, false);
        AnyChartView chartView = view.findViewById(R.id.any_chart_item);

        Pie pie = chart.getAnyChart().pie();
        pie.title(chart.getTitle());

        pie.data(chart.getDataEntries());
        chartView.setChart(pie);
        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View)object);

    }
    public int addView(PageItem pageItem, int position){
        chartViews.add(position, pageItem);
        return position;
    }
    public int removeView(ViewPager pager, int position){
        pager.setAdapter(null);
        chartViews.remove(position);
        pager.setAdapter(this);
        return position;
    }
    public PageItem getPageItem(int position){
        return chartViews.get(position);
    }
}
