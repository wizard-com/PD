package com.example.pdthird;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class JokesPageAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<JokeItem> jokeItems;


    public JokesPageAdapter(Context context, ArrayList<JokeItem> jokeItems) {
        this.context = context;
        this.jokeItems = jokeItems;
    }

    @Override
    public int getCount() {
        return jokeItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        JokeItem joke = jokeItems.get(position);
        View view = layoutInflater.inflate(R.layout.custom_slider_jokes, container, false);

        TextView tvHeader = view.findViewById(R.id.textViewHeader);
        TextView tvBody = view.findViewById(R.id.textViewContent);

        tvHeader.setText("JOKE "+(position+1));
        tvBody.setText(joke.getContent()+"\n\n"+joke.getPunchline());

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View)object);

    }
}
