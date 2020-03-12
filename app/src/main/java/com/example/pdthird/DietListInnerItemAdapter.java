package com.example.pdthird;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.HashMap;
import java.util.List;

public class DietListInnerItemAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<DietNestedItem> expandableListTitle;
    private List<List<String>> expandableListDetail;

    public DietListInnerItemAdapter(Context context, List<DietNestedItem> dietTitleItems, List<List<String>>   expandableListDetail) {
        this.context = context;
        this.expandableListTitle = dietTitleItems;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, int expandedListPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.diet_list_innermost_item, null);

        List<String> childArray = expandableListDetail.get(listPosition);

        String text = childArray.get(expandedListPosition);

        TextView textView = convertView.findViewById(R.id.tvInnerMost);

        textView.setText(text);

        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        DietNestedItem listTitle = (DietNestedItem) getGroup(listPosition);

        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.diet_list_item, null);
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.tvLayer2);
        CardView cardView = (CardView) convertView.findViewById(R.id.dayCard);

        cardView.setBackgroundColor(Color.parseColor(listTitle.getColorCode()));
        listTitleTextView.setText(listTitle.getLabel());

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

    public void add(DietNestedItem dietNestedItem){
        this.expandableListTitle.add(dietNestedItem);
    }
}
