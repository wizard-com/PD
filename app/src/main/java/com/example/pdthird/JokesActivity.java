package com.example.pdthird;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JokesActivity extends AppCompatActivity {

    ProgressBar progressBar;
    FrameLayout layout;
    ViewPager viewPager;
    ImageView prevArrow, nextArrow;
    JokesPageAdapter jokesPageAdapter;
    ArrayList<JokeItem> jokeItems;
    RequestQueue requestQueue;
    int current_position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);

        requestQueue = Volley.newRequestQueue(JokesActivity.this);
        layout = findViewById(R.id.frameLayout);
        viewPager = findViewById(R.id.viewPagerJokes);
        prevArrow = findViewById(R.id.imageViewPrev);
        nextArrow = findViewById(R.id.imageViewNext);
        jokeItems = new ArrayList<JokeItem>();
        jokesPageAdapter = new JokesPageAdapter(JokesActivity.this, jokeItems);
        progressBar = new ProgressBar(JokesActivity.this, null, android.R.attr.progressBarStyleLarge);

        FrameLayout.LayoutParams pLayoutParams =
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT);
        progressBar.setLayoutParams(pLayoutParams);
        pLayoutParams.gravity = Gravity.CENTER;
        layout.addView(progressBar);


        viewPager.setAdapter(jokesPageAdapter);
        jsonParse();

        prevArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current_position > 0) {
                    current_position--;
                    viewPager.setCurrentItem(current_position);
                }
            }
        });

        nextArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current_position < jokeItems.size()){
                    current_position++;
                    viewPager.setCurrentItem(current_position);
                }
            }
        });
    }
    private void jsonParse() {
        String url = "https://official-joke-api.appspot.com/random_ten";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    progressBar.setVisibility(View.VISIBLE);
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject object = response.getJSONObject(i);
                        String content = object.getString("setup");
                        String punchline = object.getString("punchline");
                        JokeItem jokeItem = new JokeItem(content, punchline);
                        jokesPageAdapter.add(jokeItem);
                    }
                    jokesPageAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }
}
