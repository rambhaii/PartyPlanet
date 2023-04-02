package com.app.partyplanets.DashBoad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.app.partyplanets.R;

public class CovidAdvice extends AppCompatActivity implements View.OnClickListener {
 ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState)
     {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_covid_advice);
         GetId();



     }

    private void GetId() {
        back=findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if (v==back)
        {
            finish();
        }

    }
}