package com.example.pdthird;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ReminderListViewAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<ReminderItem> itemsList;

    public ReminderListViewAdapter(Context context, int id, ArrayList<ReminderItem> objects) {
        super(context, id, objects);
        parent_context = context;
        layout_id = id;
        itemsList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvLabel = rowView.findViewById(R.id.itemLabel);
        ImageView ivItem = rowView.findViewById(R.id.itemImage);

        // Obtain the Android Version information based on the position
        ReminderItem item = itemsList.get(position);

        // Set values to the TextView to display the corresponding information
        tvLabel.setText(item.getLabel());
        ivItem.setImageResource(item.getImageID());
        return rowView;
    }
}
