package com.example.pdthird;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NutritionListViewAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<NutritionItem> nutritionItemsList;

    public NutritionListViewAdapter(Context context, int id, ArrayList<NutritionItem> objects) {
        super(context, id, objects);
        parent_context = context;
        layout_id = id;
        nutritionItemsList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvName = rowView.findViewById(R.id.textViewFood);
        TextView tvQty = rowView.findViewById(R.id.textViewQty);


        // Obtain the Android Version information based on the position
        NutritionItem item = nutritionItemsList.get(position);

        // Set values to the TextView to display the corresponding information
        tvName.setText(item.getName());
        tvQty.setText(item.getQty()+"");
        return rowView;
    }
}
