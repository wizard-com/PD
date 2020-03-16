package com.example.pdthird;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class WorkOutProgramAdapter extends ArrayAdapter{

    private Context parent_context;
    private int layout_id;
    private ArrayList<WorkOutItem> itemsList;

    public WorkOutProgramAdapter(Context context, int id, ArrayList<WorkOutItem> itemsList) {
        super(context, id, itemsList);
        this.parent_context = context;
        this.layout_id = id;
        this.itemsList = itemsList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
       return renderView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return renderView(position, convertView, parent);
    }

    private View renderView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvLabel = rowView.findViewById(R.id.tvWorkoutType);
        ImageView ivItem = rowView.findViewById(R.id.ivWorkoutType);

        // Obtain the Android Version information based on the position
        WorkOutItem item = itemsList.get(position);

        // Set values to the TextView to display the corresponding information
        tvLabel.setText(item.getType());
        ivItem.setImageResource(item.getImageID());
        tvLabel.setBackgroundColor(Color.parseColor(item.getColorCode()));

        return rowView;
    }

}
