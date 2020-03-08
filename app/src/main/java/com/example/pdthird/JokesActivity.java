package com.example.pdthird;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
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

    ViewPager viewPager;
    JokesPageAdapter jokesPageAdapter;
    ArrayList<JokeItem> jokeItems;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);
        requestQueue = Volley.newRequestQueue(JokesActivity.this);
        viewPager = findViewById(R.id.viewPagerJokes);
        jokeItems = new ArrayList<JokeItem>();
        jokesPageAdapter = new JokesPageAdapter(JokesActivity.this, jokeItems);
        viewPager.setAdapter(jokesPageAdapter);
        jsonParse();
    }

    private void jsonParse() {
        String url = "https://official-joke-api.appspot.com/random_ten";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject object = response.getJSONObject(i);
                        String content = object.getString("setup");
                        String punchline = object.getString("punchline");
                        JokeItem jokeItem = new JokeItem(content, punchline);
                        jokeItems.add(jokeItem);
                    }
                    jokesPageAdapter.notifyDataSetChanged();

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
