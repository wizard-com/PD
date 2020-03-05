package com.example.pdthird;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import java.util.HashMap;
import java.util.Map;

public class QuotesActivity extends AppCompatActivity {

    TextView tvQuote;
    RequestQueue requestQueue;
    Button btnParse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
        tvQuote = findViewById(R.id.textViewQuote);
        btnParse = findViewById(R.id.btnParse);

        requestQueue = Volley.newRequestQueue(QuotesActivity.this);
        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
    }
    private void jsonParse(){
        String url = "https://favqs.com/api/quotes/";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("quotes");

                    for (int i = 0; i < array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        String body = object.getString("body");
                        tvQuote.append(body+"\n\n");
                    }
                }
                catch (JSONException e){
                    AlertDialog alertDialog = new AlertDialog.Builder(QuotesActivity.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("Loading fail");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog alertDialog = new AlertDialog.Builder(QuotesActivity.this).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Loading fail");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
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


