package com.app.partyplanets.Filter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.partyplanets.Data.City;
import com.app.partyplanets.Data.CityModel;
import com.app.partyplanets.R;
import com.app.partyplanets.RestaurantData.TimeSlotData;
import com.app.partyplanets.RestaurantData.TimeSlotModel;
import com.app.partyplanets.Utils.APIClient;
import com.app.partyplanets.Utils.AllAPIs;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.app.partyplanets.Utils.FragmentActivityMessage;
import com.app.partyplanets.Utils.GlobalBus;
import com.app.partyplanets.Utils.Loader;
import com.app.partyplanets.Utils.UtilsMethod;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurentFilterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText restaurent_name;
    Button btn_submit;
    Spinner spinner;
    Loader loader;
    ImageView back;
    String moduleId;
    ArrayList<City> dataums;

    TextView hotel;
    int id;

    RecyclerView timeslot;
    TimeSlotModel timeSlotModel;
    ArrayList<TimeSlotData> timeSlotData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurent_filter);
        restaurent_name=findViewById(R.id.restaurent_name);
        btn_submit=findViewById(R.id.btn_submit);
        timeslot=findViewById(R.id.timeslot);
        hotel=findViewById(R.id.hotel);
        spinner=findViewById(R.id.spinner);
        moduleId=getIntent().getStringExtra("moduleId");
        back=findViewById(R.id.back);
        back.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        loader = new Loader(this, android.R.style.Theme_Translucent_NoTitleBar);

        hotel.setText("Filter");
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
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter(RestaurentFilterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayList);
                spinner.setAdapter(arrayAdapter);


            }
            @Override
            public void onFailure(Call<CityModel> call, Throwable t)
            {

            }
        });
    }

    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {
        if (activityFragmentMessage.getMessage().equalsIgnoreCase("TimeSlot"))
        {
            dataParse(activityFragmentMessage.getFrom());
        }

    }

    private void dataParse(String from)
    {

        Gson gson = new Gson();
        timeSlotModel = gson.fromJson(from, TimeSlotModel.class);
        timeSlotData = timeSlotModel.getData();
        FilterTimeSlotAdapter adapter=new FilterTimeSlotAdapter(RestaurentFilterActivity.this,timeSlotData);
        timeslot.setHasFixedSize(false);
        timeslot.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        timeslot.setLayoutManager(llm);
        timeslot.setAdapter(adapter);

    }

    public  void getDataAdapter(int  carId)
    { id=carId;
    }


    @Override
    public void onDestroy() {
        GlobalBus.getBus().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onStart()
    {  super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }
    @Override
    public void onClick(View v)
    {  if (v==btn_submit){
           if(spinner.getSelectedItem().toString().equalsIgnoreCase("Select City "))
          {
            Toast.makeText(this, "Please select city", Toast.LENGTH_SHORT).show();
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
                UtilsMethod.INSTANCE.filterRestaurant(RestaurentFilterActivity.this,loader,"2",""+spinner.getSelectedItem(),restaurent_name.getText().toString(),"","","");

            }}
        if (v==back)
        {
            finish();
        }



    }

    }
