package com.app.partyplanets.DashBoad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.partyplanets.R;

public class TermsAndConditions extends AppCompatActivity implements View.OnClickListener {
    ImageView back;
TextView hotel;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);
        GetId();
    }

    private void GetId() {
        back = findViewById(R.id.back);
        hotel = findViewById(R.id.hotel);
        back.setOnClickListener(this);
        hotel.setText("Terms  & Policy ");
        webView = findViewById(R.id.web);
        webView.loadUrl("https://www.thepartyplanets.com/Front/PrivacyPolicy");
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

