package com.example.pdthird;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class QuoteListViewAdapter extends ArrayAdapter {

    private Context parent_context;
    private int layout_id;
    private ArrayList<QuoteItem> quoteItems;

    public QuoteListViewAdapter(Context context, int id, ArrayList<QuoteItem> objects) {
        super(context, id, objects);
        this.parent_context = context;
        this.layout_id = id;
        this.quoteItems = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tv = rowView.findViewById(R.id.textViewQuote);
        CardView  cv = rowView.findViewById(R.id.quoteCard);

        // Obtain the Android Version information based on the position
        QuoteItem item = quoteItems.get(position);

        // Set values to the TextView to display the corresponding information
        tv.setText(item.getQuoteBody());
        cv.setBackgroundColor(Color.parseColor(item.getColorCode()));

        return rowView;
    }
}
