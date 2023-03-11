package com.example.asm_mob201.WebView1234;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.asm_mob201.R;

public class WebViewGiaoDuc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_giao_duc);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        WebView webView = findViewById(R.id.webView);
        Intent intent = getIntent();
        String link = intent.getStringExtra("linkNews");
        webView.loadUrl(link);
        //
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}