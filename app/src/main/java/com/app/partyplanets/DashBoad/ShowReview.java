package com.app.partyplanets.DashBoad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.partyplanets.Adpters.AllReviewsAdapter;
import com.app.partyplanets.Data.ReviewList;
import com.app.partyplanets.Data.secureLoginResponse;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.FragmentActivityMessage;
import com.app.partyplanets.Utils.GlobalBus;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class ShowReview extends AppCompatActivity implements View.OnClickListener {
    String hotel_id;
    secureLoginResponse data;
    ArrayList<ReviewList> reviewLists;
    RecyclerView recyclerview;
    ImageView back;
    TextView hotel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_review);
        hotel_id=getIntent().getStringExtra("hotel_id");
        Log.d("efjguagfjchcc",hotel_id+"           bvjkbvjbxcv");

        recyclerview=findViewById(R.id.recyclerview);
        hotel=findViewById(R.id.hotel);
        hotel.setText("Review List");

        back=findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage)
    {
        if (activityFragmentMessage.getMessage().equalsIgnoreCase("showReview"))
        {
            dataParse(activityFragmentMessage.getFrom());
        }

    }

    private void dataParse(String from)
    {

        Log.d("dkjfhjfgh",from);
        Gson gson = new Gson();
        data = gson.fromJson(from, secureLoginResponse.class);
        if (data.getData().getTotalReviews()>0)
        {
            reviewLists = data.getData().getReviewLists();
            AllReviewsAdapter adapter = new AllReviewsAdapter(this, reviewLists);
            recyclerview.setHasFixedSize(false);
            recyclerview.setLayoutManager(new LinearLayoutManager(ShowReview.this));
            LinearLayoutManager llm = new LinearLayoutManager(ShowReview.this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerview.setLayoutManager(llm);
            recyclerview.setAdapter(adapter);
        }
        else
        {
            Toast.makeText(this, "No_Review ", Toast.LENGTH_SHORT).show();
        }


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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}