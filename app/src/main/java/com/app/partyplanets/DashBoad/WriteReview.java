package com.app.partyplanets.DashBoad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.partyplanets.R;
import com.app.partyplanets.Utils.Loader;
import com.app.partyplanets.Utils.UtilsMethod;

public class WriteReview extends AppCompatActivity implements View.OnClickListener {
  SeekBar seekBar;
  String hotel_id,secureloginResponse,userId;
  TextView value;
  ImageView back;
  String r1,r2,r3,r4,r5;
  Button submit;
  TextView comments,hotel;
  Loader loader;
  double cle,staf,amen,eco,fac;


  RatingBar ratingBar,ratingBar_staffandeservice,ratingBar_amenties,ratingBar_eco_friendness,ratingBar_facilities;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);
        seekBar=findViewById(R.id.seekBar);
        ratingBar=findViewById(R.id.ratingBar);
        submit=findViewById(R.id.submit);
        hotel=findViewById(R.id.hotel);

        ratingBar_staffandeservice=findViewById(R.id.ratingBar_staffandeservice);
        ratingBar_amenties=findViewById(R.id.ratingBar_amenties);
        ratingBar_eco_friendness=findViewById(R.id.ratingBar_eco_friendness);
        ratingBar_facilities=findViewById(R.id.ratingBar_facilities);
        back=findViewById(R.id.back);
        comments=findViewById(R.id.comments);
        back.setOnClickListener(this);
        submit.setOnClickListener(this);
        loader = new Loader(this, android.R.style.Theme_Translucent_NoTitleBar);



       /* SharedPreferences myPreferences = this.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        secureloginResponse = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, null);
        Data securelogincheckResponse = new Gson().fromJson(secureloginResponse, Data.class);
        userId = securelogincheckResponse.getId();*/

      /*  ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser)
            {
                value2= String.valueOf(rating);
                Toast.makeText(WriteReview.this, ""+rating, Toast.LENGTH_SHORT).show();
            }
        });*/
        hotel_id=getIntent().getStringExtra("hotelId");
        userId=getIntent().getStringExtra("userId");
        hotel.setText(getIntent().getStringExtra("name"));

       /* seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                //value.setText(""+progress);
                ratingBar.setVisibility(View.VISIBLE);
                ratingBar.setNumStars(progress);
                ratingBar.setRating( progress);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });*/
      //UtilsMethod.INSTANCE.review(WriteReview.this,hotel_id,userId,"3","4","4","5","4","dfjkhjkjv");
      //  Log.d("value2",value2);



    }

    @Override
    public void onClick(View v)
    {
        if (v==back)
        {
            finish();
        }
        if (v==submit)
        {
            cle=ratingBar.getRating();
            staf= ratingBar_staffandeservice.getRating();
            amen= ratingBar_amenties.getRating();
            eco= ratingBar_eco_friendness.getRating();
            fac=ratingBar_facilities.getRating();
            if(cle<=0.0 )
          {
              Toast.makeText(this, "Select Cleanliness rating", Toast.LENGTH_SHORT).show();
          }
          else   if(staf<=0.0)
            {
                Toast.makeText(this, "Select Staff and services rating", Toast.LENGTH_SHORT).show();
            }  else   if(eco<=0.0)
            {
                Toast.makeText(this, "Select Eco-friendly rating", Toast.LENGTH_SHORT).show();
            } else   if(fac<=0.0)
            {
                Toast.makeText(this, "Select facilities rating", Toast.LENGTH_SHORT).show();
            }else   if(amen<=0.0 )
            {
                Toast.makeText(this, "Select Amenities rating", Toast.LENGTH_SHORT).show();
            }
           else if (UtilsMethod.INSTANCE.isNetworkAvialable(this) == false)
           {
            Toast.makeText(this, "Please Check Your Network Connectivity", Toast.LENGTH_SHORT).show();

           }
           else
           {
               loader.show();
               loader.setCancelable(false);
               loader.setCanceledOnTouchOutside(false);
               Log.d("whduy", String.valueOf(ratingBar.getRating()));
             //  UtilsMethod.INSTANCE.review(loader,WriteReview.this, hotel_id, userId, String.valueOf(ratingBar.getRating()), String.valueOf(ratingBar_staffandeservice.getRating()), String.valueOf(ratingBar_amenties.getRating()), String.valueOf(ratingBar_eco_friendness.getRating()), String.valueOf(ratingBar_facilities.getRating()), comments.getText().toString());
               UtilsMethod.INSTANCE.review(loader,WriteReview.this, hotel_id, userId,
                       String.valueOf(cle),
                       String.valueOf(staf),
                       String.valueOf(amen),
                       String.valueOf(eco),
                       String.valueOf(fac),
                       comments.getText().toString());

           }
        }

    }
}