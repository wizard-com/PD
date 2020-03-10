package com.example.pdthird;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class QuotesGridAdapter extends ArrayAdapter {

    private Context parent_context;
    private int layout_id;
    private ArrayList<GridItem> imageIDs;

    public QuotesGridAdapter(Context context, int id, ArrayList<GridItem> objects) {
        super(context, id, objects);
        this.parent_context = context;
        this.layout_id = id;
        this.imageIDs = objects;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.textViewTitle);
        ImageView ivItem = rowView.findViewById(R.id.imageViewCategory);
        CardView cardView = rowView.findViewById(R.id.categoryCard);

        // Obtain the Android Version information based on the position
        GridItem item = imageIDs.get(position);

        // Set values to the TextView to display the corresponding information
        tvTitle.setBackgroundColor(Color.parseColor(item.getColorCode()));
        tvTitle.setText(item.getTitle());
        cardView.setBackgroundColor(Color.parseColor(item.getColorCode()));
        ivItem.setImageResource(item.getImageID());

        return rowView;
    }



}
