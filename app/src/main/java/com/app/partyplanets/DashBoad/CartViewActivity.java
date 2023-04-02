package com.app.partyplanets.DashBoad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.partyplanets.Adpters.CartViewAdapter;
import com.app.partyplanets.Data.CartData;
import com.app.partyplanets.Data.Data;
import com.app.partyplanets.Data.secureLoginResponse;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.app.partyplanets.Utils.FragmentActivityMessage;
import com.app.partyplanets.Utils.GlobalBus;
import com.app.partyplanets.Utils.Loader;
import com.app.partyplanets.Utils.UtilsMethod;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Collections;

public class CartViewActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerview;
    String secureloginResponse;
    String userId;
    secureLoginResponse  secureLoginResponse;
    /* List<Data> data;*/
    ArrayList<CartData> dataArrayList;
    TextView amount;
    Loader loader;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);
        recyclerview=findViewById(R.id.recyclerview);
        amount=findViewById(R.id.amount);
        loader = new Loader(this, android.R.style.Theme_Translucent_NoTitleBar);
        back=findViewById(R.id.back);
        back.setOnClickListener(this);


        SharedPreferences myPreferences = this.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        secureloginResponse = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, null);
        Data securelogincheckResponse = new Gson().fromJson(secureloginResponse, Data.class);
        userId = securelogincheckResponse.getId();
        hitApi();

    }

    public void hitApi()
    {
        if (UtilsMethod.INSTANCE.isNetworkAvialable(this) == false)
        {
            Toast.makeText(this, "Please Check Your Network Connectivity", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loader.show();
            loader.setCancelable(false);
            loader.setCanceledOnTouchOutside(false);
            UtilsMethod.INSTANCE.viewCartDetails(CartViewActivity.this,userId,loader);
        }
    }




    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage)
    {
        if (activityFragmentMessage.getMessage().equalsIgnoreCase("viewcart"))
        {
            dataParse(activityFragmentMessage.getFrom());
        }
    }

    private void dataParse(String from)
    {
        Log.d("kdghkhdfgrcxhghdgiugjd",""+from);

        Gson gson = new Gson();
        secureLoginResponse = gson.fromJson(from, secureLoginResponse.class);
        amount.setText( "₹ "+secureLoginResponse.getData().getTotalAmount());
        dataArrayList = secureLoginResponse.getData().getCartData();
        Collections.reverse( secureLoginResponse.getData().getCartData());
        CartViewAdapter adapter=new CartViewAdapter(CartViewActivity.this,dataArrayList);
        adapter.notifyDataSetChanged();
        recyclerview.setHasFixedSize(false);
        recyclerview.setLayoutManager(new LinearLayoutManager(CartViewActivity.this));
        LinearLayoutManager llm = new LinearLayoutManager(CartViewActivity.this);
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
    public void onClick(View v) {
        if (v==back)
        {
            finish();
        }

    }
    @Override public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }
}