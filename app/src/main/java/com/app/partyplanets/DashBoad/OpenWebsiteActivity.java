package com.app.partyplanets.DashBoad;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.partyplanets.R;

public class OpenWebsiteActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView back;
    TextView hotel;
    WebView webView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_website);
        back = findViewById(R.id.back);
        hotel = findViewById(R.id.hotel);
        back.setOnClickListener(this);
        hotel.setText("PARTY PLANET ");
        webView = findViewById(R.id.web);
        webView.loadUrl("https://www.thepartyplanets.com/");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
    }
    @Override
    public void onClick(View v) {
        if (v == back) {
            finish();
        }
    }
}