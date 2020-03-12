package com.example.pdthird;

import android.graphics.Color;
import android.widget.BaseExpandableListAdapter;

import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

public class DietListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<DietTitleItem> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;

    public DietListAdapter(Context context, List<DietTitleItem> dietTitleItems, HashMap<String, List<String>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = dietTitleItems;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition).getTitle()).get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.diet_list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView.findViewById(R.id.dietListItem);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition).getTitle()).size();
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
        DietTitleItem listTitle = (DietTitleItem) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.diet_list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.tvDietLabel);
        CardView cardView = (CardView) convertView.findViewById(R.id.dietItemCard);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.imageViewDietPlan);

        cardView.setBackgroundColor(Color.parseColor(listTitle.getColorCode()));
        listTitleTextView.setText(listTitle.getTitle());
        imageView.setImageResource(listTitle.getImageID());

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
    public void add(DietTitleItem dietTitleItem){
        this.expandableListTitle.add(dietTitleItem);
    }
}