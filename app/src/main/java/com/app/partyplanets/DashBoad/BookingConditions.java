package com.app.partyplanets.DashBoad;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;

public class BookingConditions extends AppCompatActivity implements View.OnClickListener {
  TextView hotel,polices;
  ImageView back;
    @SuppressLint({"MissingInflatedId", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_conditions);
        hotel=findViewById(R.id.hotel);
        back=findViewById(R.id.back);
        polices=findViewById(R.id.polices);
        hotel.setText("Terms and Policies");
        hotel.setTextColor(R.color.textcolor);
        polices.setText(ApplicationConstant.INSTANCE.polices);
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