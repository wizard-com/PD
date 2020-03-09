package com.example.pdthird;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuotesActivity extends AppCompatActivity {

    ListView listView;
    ProgressBar progressBar;
    LinearLayout linearLayout;
    QuoteListViewAdapter quoteListViewAdapter;
    ArrayList<QuoteItem> arrayList;
    private String color_code;
    private String url = "https://favqs.com/api/quotes/";
    private String path = "";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);

        listView = findViewById(R.id.lvQuotes);
        arrayList = new ArrayList<QuoteItem>();
        quoteListViewAdapter = new QuoteListViewAdapter(QuotesActivity.this, R.layout.custom_quote_list, arrayList);
        listView.setAdapter(quoteListViewAdapter);
        requestQueue = Volley.newRequestQueue(QuotesActivity.this);

        progressBar = new ProgressBar(QuotesActivity.this, null, android.R.attr.progressBarStyleLarge);
        linearLayout = findViewById(R.id.listViewLayout);

        LinearLayout.LayoutParams pLayoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        progressBar.setLayoutParams(pLayoutParams);
        pLayoutParams.gravity = Gravity.CENTER;
        linearLayout.addView(progressBar);

       Intent intent = getIntent();
       String[] data = intent.getStringArrayExtra("path_and_color");
       path = data[0];
       color_code = data[1];

       jsonParse();

    }
    private void jsonParse(){
        url += path;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    progressBar.setVisibility(View.VISIBLE);
                    JSONArray array = response.getJSONArray("quotes");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        String body = object.getString("body");
                        quoteListViewAdapter.add(new QuoteItem(color_code, body));
                    }
                    quoteListViewAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        })
        {
            /** Passing some request headers* */
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Token token=a6a555c1ba09d5babc1019879244f90b");
                return headers;
            }
        };
        requestQueue.add(request);
    }


    }
