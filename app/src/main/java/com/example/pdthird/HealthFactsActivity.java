package com.example.pdthird;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

import java.util.Timer;
import java.util.TimerTask;


public class HealthFactsActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    WebView webView;
    ProgressBar progressBar;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_healthfacts);

        progressBar = new ProgressBar(HealthFactsActivity.this, null, android.R.attr.progressBarStyleLarge);
        webView = findViewById(R.id.web_view_health_facts);
        Toolbar toolbar = findViewById(R.id.toolbarFacts);
        setSupportActionBar(toolbar);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
            }
        });
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

    private void renderProgessBar(){
        progressBar = new ProgressBar(HealthFactsActivity.this, null, android.R.attr.progressBarStyleLarge);
        frameLayout = findViewById(R.id.healthFactsLayout);

        FrameLayout.LayoutParams pLayoutParams =
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT);
        progressBar.setLayoutParams(pLayoutParams);
        pLayoutParams.gravity = Gravity.CENTER;
        frameLayout.addView(progressBar);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void showMenu(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.facts_sources_menu);
        popup.show();
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
    public boolean onMenuItemClick(MenuItem item) {

        renderProgessBar();
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
        return true;
    }


}
