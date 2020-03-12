package com.example.pdthird;

import android.graphics.Color;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

public class DietListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<List<DietNestedItem>> secondLevel;
    private List<DietTitleItem> expandableListTitle;
    private List<LinkedHashMap<String, List<String>>> expandableListDetail;

    public DietListAdapter(Context context, List<DietTitleItem> dietTitleItems, List<List<DietNestedItem>> secondLevel, List<LinkedHashMap<String, List<String>>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = dietTitleItems;
        this.secondLevel = secondLevel;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(expandedListPosition).get(this.expandableListTitle.get(listPosition).getTitle()).get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final DietListSecondLevel secondLevelELV = new DietListSecondLevel(context);

        List<DietNestedItem> subHeaders = secondLevel.get(listPosition);


        List<List<String>> childData = new ArrayList<>();


        HashMap<String, List<String>> secondLevelData = expandableListDetail.get(listPosition);

        for (String key : secondLevelData.keySet()) {

            childData.add(secondLevelData.get(key));

        }


        secondLevelELV.setAdapter(new DietListInnerItemAdapter(context, subHeaders, childData));

        secondLevelELV.setGroupIndicator(null);


        secondLevelELV.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    secondLevelELV.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });


        return secondLevelELV;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return 1;
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
        final DietTitleItem expandedListItem = (DietTitleItem) getGroup(listPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.diet_list_group, null);
        }
        TextView expandedListTextView = (TextView) convertView.findViewById(R.id.tvDietLabel);
        CardView cardView = (CardView) convertView.findViewById(R.id.dietItemCard);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewDietPlan);

        cardView.setBackgroundColor(Color.parseColor(expandedListItem.getColorCode()));
        imageView.setImageResource(expandedListItem.getImageID());
        expandedListTextView.setText(expandedListItem.getTitle());

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