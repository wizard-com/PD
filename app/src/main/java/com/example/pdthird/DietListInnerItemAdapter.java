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
    private HashMap<String, List<String>> expandableListDetail;

    public DietListInnerItemAdapter(Context context, List<DietNestedItem> dietTitleItems, HashMap<String, List<String>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = dietTitleItems;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition).getLabel()).get(expandedListPosition);
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
            convertView = layoutInflater.inflate(R.layout.diet_list_item_layer2, null);
        }

        TextView expandedListTextView = (TextView) convertView.findViewById(R.id.tvLayer2);
        CardView cardView = (CardView) convertView.findViewById(R.id.dayCard);

        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition).getLabel()).size();
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
            convertView = layoutInflater.inflate(R.layout.diet_list_item_layer2, null);
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.tvLayer2);
        CardView cardView = (CardView) convertView.findViewById(R.id.dayCard);

        cardView.setBackgroundColor(Color.parseColor(listTitle.getColorCode()));
        listTitleTextView.setText(listTitle.getTitle());

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
