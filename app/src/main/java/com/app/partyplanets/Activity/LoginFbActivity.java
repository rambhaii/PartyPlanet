package com.app.partyplanets.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.app.partyplanets.R;
import com.facebook.CallbackManager;

public class LoginFbActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fb);


    }

}