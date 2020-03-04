package com.example.pdthird;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HealthNewsActivity extends AppCompatActivity {
private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_news);
        Toolbar toolbar = findViewById(R.id.toolbarNews);
        setSupportActionBar(toolbar);
        webView = findViewById(R.id.web_view_health_news);
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
        getMenuInflater().inflate(R.menu.news_channels_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String url = "";
        int id = item.getItemId();

        if (id == R.id.bbc) {
            url = "https://www.bbc.com/news/health";
        }
        else if (id == R.id.cnn){
            url = "https://edition.cnn.com/health";
        }
        else if (id == R.id.who){
            url = "https://www.who.int/health-topics/";
        }
        else {
            url = "https://www.sciencedaily.com/news/top/health/";
        }
        webView.loadUrl(url);
        return super.onOptionsItemSelected(item);
    }

}
