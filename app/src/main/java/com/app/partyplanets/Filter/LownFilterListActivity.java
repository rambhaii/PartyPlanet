package com.app.partyplanets.Filter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.partyplanets.Data.City;
import com.app.partyplanets.Data.CityModel;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.APIClient;
import com.app.partyplanets.Utils.AllAPIs;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.app.partyplanets.Utils.Loader;
import com.app.partyplanets.Utils.UtilsMethod;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LownFilterListActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner spinner;
    SeekBar seekBar;

    RatingBar  rating1;
    TextView hotel;
    Loader loader;
    Button btn_submit;
    int price;
    TextView tv_setprice;
    String moduleId;
    ArrayList<City> dataums;
    ImageView back;
    LinearLayout lv_1;
    EditText restaurent_name,capacity;
    String[] plate= new String[]{"Lucknow","Kanpur","Ayodhya","Gorakhpur","Ghaziabad","Noida","Saharanpur","Agra"};
    int rating=0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listlayout);
        seekBar=findViewById(R.id.seekBar);
        restaurent_name=findViewById(R.id.restaurent_name);
        lv_1=findViewById(R.id.lv_1);
        spinner=findViewById(R.id.spinner);
        tv_setprice=findViewById(R.id.tv_setprice);
        btn_submit=findViewById(R.id.btn_submit);
        hotel=findViewById(R.id.hotel);
        back=findViewById(R.id.back);
        rating1=findViewById(R.id.ratingBar1);
        capacity=findViewById(R.id.capacity);
        moduleId=getIntent().getStringExtra("moduleId");
        Log.d("jhhgh",moduleId);
        btn_submit.setOnClickListener(this);
        back.setOnClickListener(this);
        lv_1.setOnClickListener(this);
        loader = new Loader(this, android.R.style.Theme_Translucent_NoTitleBar);
        hotel.setText("Filter ");
        rating1.setOnClickListener(this);
        ArrayList<String> arrayList = new ArrayList<String>();
        String header= ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api= APIClient.getClient().create(AllAPIs.class);
        Call<CityModel> call=api.getCity(header,moduleId);
        arrayList.add("Select City ");
        call.enqueue(new Callback<CityModel>() {
            @Override
            public void onResponse(Call<CityModel> call, Response<CityModel> response)
            {

                if (response.body().getStatus()==1)
                {

                    dataums=response.body().getBanerData();

                    for (City d:dataums)
                    {
                        arrayList.add(""+d.getCity());
                    }}
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter(LownFilterListActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayList);
                spinner.setAdapter(arrayAdapter);


            }
            @Override
            public void onFailure(Call<CityModel> call, Throwable t)
            {

            }
        });











        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                tv_setprice.setText("₹ "+progress);
                price=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v)
    {
          if (v==lv_1)
          {
              LayerDrawable stars = (LayerDrawable) rating1.getProgressDrawable();
              stars.getDrawable(2).setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
              rating=1;
          }


        if (v==btn_submit)
        {


             if (UtilsMethod.INSTANCE.isNetworkAvialable(this) == false)
            {
                Toast.makeText(this, "Please Check Your Network Connectivity", Toast.LENGTH_SHORT).show();
            }
             else if(spinner.getSelectedItem().toString().equalsIgnoreCase("Select City "))
             {
                 Toast.makeText(this, "Please select city", Toast.LENGTH_SHORT).show();
             }
            else
            {

                loader.show();
                loader.setCancelable(false);
                loader.setCanceledOnTouchOutside(false);

             //UtilsMethod.INSTANCE.filterHotel(LownFilterListActivity.this, loader, "1", price, String.valueOf(spinner.getSelectedItem()), "", "", "0", "10");
            String pri="";
                if(price==0)
                 {  pri="";

                    UtilsMethod.INSTANCE.lawnsFilter(LownFilterListActivity.this,loader,moduleId,pri,
                            String.valueOf(spinner.getSelectedItem()),restaurent_name.getText().toString(),"","",capacity.getText().toString());

                }
                else
                {
                    if (price>=1000)
                    {
                        pri= String.valueOf(price);
                        UtilsMethod.INSTANCE.lawnsFilter(LownFilterListActivity.this,loader,moduleId,pri,
                                String.valueOf(spinner.getSelectedItem()),restaurent_name.getText().toString(),"","",capacity.getText().toString());

                    }
                    else
                    {

                        loader.dismiss();
                        Toast.makeText(this, "Filter price", Toast.LENGTH_SHORT).show();
                    }
                }




            }}


        if(v==back)
        {
            finish();

        }

    }
}