package com.example.pdthird;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

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

    GridView gridView;
    GridAdapter gridAdapter;
    ArrayList<GridItem> gridItems;
    //RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);

        gridView = findViewById(R.id.gridViewCategories);
        gridItems = new ArrayList<GridItem>();
        gridItems.add(new GridItem("Life", android.R.drawable.ic_menu_gallery));
        gridItems.add(new GridItem("Health", android.R.drawable.ic_menu_gallery));
        gridItems.add(new GridItem("Happiness", android.R.drawable.ic_menu_gallery));
        gridItems.add(new GridItem("Motivational", android.R.drawable.ic_menu_gallery));
        gridItems.add(new GridItem("Knowledge", android.R.drawable.ic_menu_gallery));
        gridItems.add(new GridItem("Art", android.R.drawable.ic_menu_gallery));

        gridAdapter = new GridAdapter(QuotesActivity.this, R.layout.custom_grid_item, gridItems);
        gridAdapter.notifyDataSetChanged();
        gridView.setAdapter(gridAdapter);
//        requestQueue = Volley.newRequestQueue(QuotesActivity.this);
    }
//    private void jsonParse(){
//        String url = "https://favqs.com/api/quotes/?filter=life&type=tag";
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    JSONArray array = response.getJSONArray("quotes");
//
//                    for (int i = 0; i < array.length(); i++){
//                        JSONObject object = array.getJSONObject(i);
//                        String body = object.getString("body");
//
//                    }
//                }
//                catch (JSONException e){
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        })
//        {
//            /** Passing some request headers* */
//            @Override
//            public Map getHeaders() throws AuthFailureError {
//                HashMap headers = new HashMap();
//                headers.put("Content-Type", "application/json");
//                headers.put("Authorization", "Token token=a6a555c1ba09d5babc1019879244f90b");
//                return headers;
//            }
//        };
//        requestQueue.add(request);
//    }

}


