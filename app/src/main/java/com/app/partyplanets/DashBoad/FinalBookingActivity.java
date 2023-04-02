package com.app.partyplanets.DashBoad;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.partyplanets.Data.Data;
import com.app.partyplanets.Data.secureLoginResponse;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.app.partyplanets.Utils.UtilsMethod;
import com.google.gson.Gson;

public class FinalBookingActivity extends AppCompatActivity implements View.OnClickListener {
    String secureloginResponse;
    EditText fname,lname,e_mail,contact,adharnumber,country;
    TextView actualprice,price,hotel;
    Button reserve;
    String old_price,offer_price,cartId,selectRoom,userId,roomsNo;
    Data data;
    ImageView back;
    secureLoginResponse secureLoginResponse;
    @SuppressLint({"MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_booking);
        contact=findViewById(R.id.contact);
        hotel=findViewById(R.id.hotel);
        back=findViewById(R.id.back);
        hotel.setText("Fill Your Details ");
        back.setOnClickListener(this);
        fname=findViewById(R.id.fname);
        lname=findViewById(R.id.lname);
        e_mail=findViewById(R.id.email);
        adharnumber=findViewById(R.id.adharnumber);
        country=findViewById(R.id.country);
        reserve=findViewById(R.id.reserve);
        actualprice=findViewById(R.id.actualprice);
        price=findViewById(R.id.price);
        reserve.setOnClickListener(this);
       /* i.putExtra("data",new Gson().toJson(response.body()).toString());
        i.putExtra("old_price",old_price);
        i.putExtra("offer_price",oldoffer_price);*/
        old_price=getIntent().getStringExtra("old_price");
        offer_price=getIntent().getStringExtra("offer_price");
        cartId=getIntent().getStringExtra("data");
        actualprice.setText("₹."+old_price);
        price.setText("₹."+offer_price);
        Gson gson=new Gson();
        secureLoginResponse = gson.fromJson(cartId, secureLoginResponse.class);
        data=secureLoginResponse.getData();
        roomsNo=""+data.getCart_id();
        SharedPreferences myPreferences = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        secureloginResponse = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, null);
        Data securelogincheckResponse = new Gson().fromJson(secureloginResponse, Data.class);
        contact.setText(securelogincheckResponse.getPhoneNo());
        fname.setText(securelogincheckResponse.getFirstName());
        lname.setText(securelogincheckResponse.getLastName());
        e_mail.setText(securelogincheckResponse.getEmail());
        country.setText(securelogincheckResponse.getCity());
        userId=securelogincheckResponse.getId();

    }

    @Override
    public void onClick(View v) {
         if (v==back)
         {
             finish();
         }


        if (v==reserve)
        {
             if (contact.getText().toString().isEmpty())
             {
                 contact.setError("Enter Contact");
                 contact.requestFocus();
             }
            else if (fname.getText().toString().isEmpty())
            {
                fname.setError("Enter first Name");
                fname.requestFocus();
            }
             else if (lname.getText().toString().isEmpty())
             {
                 lname.setError("Enter last Name");
                 lname.requestFocus();
             }
             else if (e_mail.getText().toString().isEmpty())
             {
                 e_mail.setError("Enter E-mail");
                 e_mail.requestFocus();
             }
             else if (country.getText().toString().isEmpty())
             {
                 country.setError("Enter Country/region");
                 country.requestFocus();
             }

             else if (UtilsMethod.INSTANCE.isNetworkAvialable(this) == false)
             {
                 Toast.makeText(this, "Please Check Your Network Connectivity", Toast.LENGTH_SHORT).show();
             }
             else
             {
                 if (!roomsNo.isEmpty() && !userId.isEmpty())
                 {
                     UtilsMethod.INSTANCE.RoomDeatailsByCartid(FinalBookingActivity.this, roomsNo,userId,fname.getText().toString()+" "+lname.getText().toString());

                 }
                 else
                 {
                     UtilsMethod.INSTANCE.alertBox("","Please choose  room ? ",this);
                 }
             }

        }

    }
}