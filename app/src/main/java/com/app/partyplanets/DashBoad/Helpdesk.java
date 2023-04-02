package com.app.partyplanets.DashBoad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.partyplanets.R;

public class Helpdesk extends AppCompatActivity implements View.OnClickListener {
    ImageView back;
  TextView hotel;
  TextView tv_openWebSite,contact,tv_email;
  Button booking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpdesk);
        GetId();
    }

    private void GetId() {
        back=findViewById(R.id.back);
        back.setOnClickListener(this);
        hotel=findViewById(R.id.hotel);
        tv_openWebSite=findViewById(R.id.tv_openWebSite);
        tv_openWebSite.setOnClickListener(this);
        hotel.setText("Contact Us");
        contact=findViewById(R.id.tv_contact);
        tv_email=findViewById(R.id.email);
        booking=findViewById(R.id.booking);
        contact.setOnClickListener(this);
        tv_email.setOnClickListener(this);
        booking.setOnClickListener(this);



    }

    @Override
    public void onClick(View v)
    {

        if (v==booking)
        {   String contact = "+91 7307939891"; // use country code with your phone number
            String url = "https://api.whatsapp.com/send?phone=" + contact;
            try {
                PackageManager pm = getPackageManager();
                pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            } catch (PackageManager.NameNotFoundException e) {
                Toast.makeText(Helpdesk.this, "Whatsapp app not installed in your phone", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        }
        if (v==back)
        {
            finish();
        }
        if (v==tv_openWebSite)
        {
            startActivity(new Intent(this,OpenWebsiteActivity.class));

        }
        if (v==contact)
        {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:7307939891"));
            startActivity(intent);
        }
  if (v==tv_email)
  {
      Intent intent = new Intent(Intent.ACTION_SEND);
      intent.setType("plain/text");
      intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"partyplanet42@gmail.com"});
      intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
      intent.putExtra(Intent.EXTRA_TEXT, "mail body");
      startActivity(Intent.createChooser(intent, ""));
  }
    }
}