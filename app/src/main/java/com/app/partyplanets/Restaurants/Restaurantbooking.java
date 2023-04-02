package com.app.partyplanets.Restaurants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.partyplanets.Data.Data;
import com.app.partyplanets.Data.Dataum;
import com.app.partyplanets.R;
import com.app.partyplanets.RestaurantData.ResturantData;
import com.app.partyplanets.RestaurantData.ResturantModel;
import com.app.partyplanets.ResttaurantAdapter.RestaurantAdeptar;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.app.partyplanets.Utils.UtilsMethod;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Restaurantbooking extends AppCompatActivity implements View.OnClickListener ,RestaurantAdeptar.onListClickedRowListner{

    String listvalue,data;
    Dataum dataum;
    ImageView image,back;
    TextView name,hotel;
    RecyclerView recyclerview;
    ResturantModel resturantModel;
    ArrayList<ResturantData> resturantData;
    Button reserve;
    String restaurantId;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantbooking);
        image=findViewById(R.id.image);
        name=findViewById(R.id.name);
        back=findViewById(R.id.back);
        recyclerview=findViewById(R.id.recyclerview);
        reserve=findViewById(R.id.reserve);
        hotel=findViewById(R.id.hotel);
        reserve.setOnClickListener(this);
        back.setOnClickListener(this);
        reserve.setVisibility(View.GONE);

        listvalue=getIntent().getStringExtra("datum");
        data=getIntent().getStringExtra("data");
        dataum = new Gson().fromJson(listvalue, Dataum.class);
        hotel.setText(dataum.getName());
        Glide.with(this)
                .load(ApplicationConstant.INSTANCE.baseUrl+"/"+dataum.getBanner_img())
                .fitCenter()
                .error(R.drawable.imm)
                .into(image);
        name.setText(dataum.getName());
        parshData(data);
        restaurantId=dataum.getId();
        SharedPreferences myPreferences = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String secureloginResponse = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, null);
        Data securelogincheckResponse = new Gson().fromJson(secureloginResponse, Data.class);
       userId = securelogincheckResponse.getId();






    }

    public void parshData(String datalist)
    {

        Gson gson = new Gson();
        resturantModel = gson.fromJson(datalist, ResturantModel.class);
        resturantData = resturantModel.getData();
        RestaurantAdeptar adapter=new RestaurantAdeptar(Restaurantbooking.this,resturantData,this);
        recyclerview.setHasFixedSize(false);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(llm);
        recyclerview.setAdapter(adapter);
    }


    @Override
    public void onClick(View v)
    {
        if (v==reserve)
        {
            startActivity(new Intent(Restaurantbooking.this,RestauMenuBooking.class).putExtra("restaurantId",restaurantId));
            UtilsMethod.INSTANCE.timeSlot(Restaurantbooking.this);
            UtilsMethod.INSTANCE.showFoodMenuList(Restaurantbooking.this,restaurantId,userId);
        }
        if (v==back)
        {
            finish();
        }

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onListSelected(int status)
    {
        if (status==1)
        {
            reserve.setVisibility(View.VISIBLE);
        }
    }
}