package com.app.partyplanets.DashBoad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.app.partyplanets.Filter.FilterListActivity;
import com.app.partyplanets.Adpters.SearchAdapter;
import com.app.partyplanets.Data.DasboadModel;
import com.app.partyplanets.Data.Dataum;
import com.app.partyplanets.Filter.LownFilterListActivity;
import com.app.partyplanets.Filter.RestaurentFilterActivity;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.FragmentActivityMessage;
import com.app.partyplanets.Utils.GlobalBus;
import com.app.partyplanets.Utils.UtilsMethod;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Collections;

public class SearchingShowData extends AppCompatActivity implements View.OnClickListener {
private RecyclerView recyclerview;
private LottieAnimationView lottieAnimationView;
     ImageView back;
    DasboadModel dasboadModel;
    ArrayList<Dataum> banerData;
    Button cart,filter,reverse;
    String data;
    String moduleId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching_show_data);
        GetId();
    }

    private void GetId()
    {

        lottieAnimationView=findViewById(R.id.lottie_main);
        lottieAnimationView.setOnClickListener(this);
        recyclerview=findViewById(R.id.recyclerview);
        filter=findViewById(R.id.filter);
        reverse=findViewById(R.id.reverse);
        cart=findViewById(R.id.cart);
        cart.setOnClickListener(this);
        filter.setOnClickListener(this);
        reverse.setOnClickListener(this);
        back=findViewById(R.id.back);
        back.setOnClickListener(this);
        data=getIntent().getStringExtra("data");
        moduleId=getIntent().getStringExtra("moduleId");


        if (moduleId.equalsIgnoreCase("3"))
        {
            filter.setVisibility(View.VISIBLE);
            reverse.setVisibility(View.VISIBLE);
        }
        if (moduleId.equalsIgnoreCase("1"))
        {
            filter.setVisibility(View.VISIBLE);
            reverse.setVisibility(View.VISIBLE);
        }
        if (moduleId.equalsIgnoreCase("2"))
        {
            filter.setVisibility(View.VISIBLE);
            reverse.setVisibility(View.VISIBLE);
        }
        if (moduleId.equalsIgnoreCase("4"))
        {
            filter.setVisibility(View.VISIBLE);
            reverse.setVisibility(View.VISIBLE);
        }
        dataParse(data);
    }

    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage)
    {
        if (activityFragmentMessage.getMessage().equalsIgnoreCase(""))
        {
           // dataParse(activityFragmentMessage.getFrom());
            //Log.d("dfkghhsg",activityFragmentMessage.getFrom());
        }

    }

    private void dataParse(String from)
    {
        Log.d("kdghkhvcxvbdgivvugjd",""+from+""+moduleId);
        Gson gson = new Gson();
        dasboadModel = gson.fromJson(from, DasboadModel.class);
        banerData = dasboadModel.getBanerData();
        SearchAdapter adapter=new SearchAdapter(SearchingShowData.this,banerData);
        recyclerview.setHasFixedSize(false);
        recyclerview.setLayoutManager(new LinearLayoutManager(SearchingShowData.this));
        LinearLayoutManager llm = new LinearLayoutManager(SearchingShowData.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(llm);
        recyclerview.setAdapter(adapter);

    }


    @Override
    public void onDestroy() {
        // Unregister the registered event.
        GlobalBus.getBus().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }

    @Override
    public void onClick(View v)
    {
        if (v==back || v== lottieAnimationView)
        {
            finish();
        }
        if (v==cart)
        {
            startActivity(new Intent(SearchingShowData.this,CartViewActivity.class));

        }
        if (v==filter)
        {
            if (moduleId.equalsIgnoreCase("1"))
            {
                startActivity(new Intent(SearchingShowData.this, FilterListActivity.class).putExtra("moduleId",moduleId));
               //  finish();
            }
            else if (moduleId.equalsIgnoreCase("2"))
            {
                UtilsMethod.INSTANCE.timeSlot(SearchingShowData.this);
                startActivity(new Intent(SearchingShowData.this, RestaurentFilterActivity.class).putExtra("moduleId",moduleId));
                //finish();
            }
            else if (moduleId.equalsIgnoreCase("3"))
            {

                startActivity(new Intent(SearchingShowData.this, LownFilterListActivity.class).putExtra("moduleId",moduleId));
                // finish();
            }
            else if (moduleId.equalsIgnoreCase("4"))
            {

                startActivity(new Intent(SearchingShowData.this, LownFilterListActivity.class).putExtra("moduleId",moduleId));
               // finish();
            }
        }
        if (v==reverse)
        {
            Gson gson = new Gson();
            dasboadModel = gson.fromJson(data, DasboadModel.class);
            banerData = dasboadModel.getBanerData();
            Collections.reverse(banerData);
            SearchAdapter adapter=new SearchAdapter(SearchingShowData.this,banerData);
            recyclerview.setHasFixedSize(false);
            recyclerview.setLayoutManager(new LinearLayoutManager(SearchingShowData.this));
            LinearLayoutManager llm = new LinearLayoutManager(SearchingShowData.this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerview.setLayoutManager(llm);
            recyclerview.setAdapter(adapter);

        }
    }
}