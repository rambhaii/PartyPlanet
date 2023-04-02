package com.app.partyplanets.DashBoad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.app.partyplanets.R;

import java.util.Timer;
import java.util.TimerTask;

public class SuccessFull extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_full);

        Timer t = new Timer(false);
        t.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        startActivity(new Intent(SuccessFull.this,DashBoad.class));
                        finish();
                    }
                });
            }
        }, 1000);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
     startActivity(new Intent(SuccessFull.this,DashBoad.class));
    }
}