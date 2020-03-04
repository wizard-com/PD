package com.example.pdthird;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class HealthFactsActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_healthfacts);
        webView = findViewById(R.id.web_view_health_facts);
        Toolbar toolbar = findViewById(R.id.toolbarFacts);
        setSupportActionBar(toolbar);
        webView.setWebViewClient(new WebViewClient());
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setDomStorageEnabled(true);


    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.facts_sources_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String url = "";
        int id = item.getItemId();

        if (id == R.id.best_life) {
            url = "https://bestlifeonline.com/shocking-health-facts/";
        }
        else if (id == R.id.the_good_body){
            url = "https://www.thegoodbody.com/health-facts/";
        }
        else if (id == R.id.amazing_health_facts){
            url = "https://www.amazinghealthfacts.org/";
        }
        else {
            url = "https://www.amazinghealthfacts.org/";
        }
        webView.loadUrl(url);
        return super.onOptionsItemSelected(item);
    }




}
