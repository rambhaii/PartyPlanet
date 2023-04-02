package com.app.partyplanets.DashBoad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.partyplanets.Adpters.FrequentAskQuestionAdapter;
import com.app.partyplanets.Data.FrequentQuestionModel;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.UtilsMethod;

import java.util.ArrayList;
import java.util.List;

public class frequentlyAQ extends AppCompatActivity implements View.OnClickListener
{
    ImageView back;
    List<FrequentQuestionModel> frequentQuestionModels;
    TextView others,send,message;
    RecyclerView recyclerView;
    LinearLayout list;
    TextView hotel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequently_aq);
        GetId();
    }
    private void GetId() {
        back=findViewById(R.id.back);
        recyclerView=findViewById(R.id.recyclerview1);
        others=findViewById(R.id.others);
        list=findViewById(R.id.list);
        hotel=findViewById(R.id.hotel);
        hotel.setText("Frequently Ask Questions ");
        send=findViewById(R.id.send);
        message=findViewById(R.id.message);

        others.setOnClickListener(this);
        send.setOnClickListener(this);

        back.setOnClickListener(this);
        frequentQuestionModels=new ArrayList<>();
        frequentQuestionModels.add(new FrequentQuestionModel("What's the difference between a Double room and a Twin room?"));
        frequentQuestionModels.add(new FrequentQuestionModel("What's the difference between a Double room and a Twin room?"));
        frequentQuestionModels.add(new FrequentQuestionModel("What's the difference between a Double room and a Twin room?"));
        frequentQuestionModels.add(new FrequentQuestionModel("What's the difference between a Double room and a Twin room?"));
        frequentQuestionModels.add(new FrequentQuestionModel("What's the difference between a Double room and a Twin room?"));
        FrequentAskQuestionAdapter frequentAskQuestionAdapter=new FrequentAskQuestionAdapter(this,frequentQuestionModels);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(frequentAskQuestionAdapter);
        recyclerView.setAdapter(frequentAskQuestionAdapter);


    }

    @Override
    public void onClick(View v)
    {
        if (v==back)
        {
            finish();
        }
        if (v==others)
        {
            list.setVisibility(View.VISIBLE);
        }
        if (v==send)
        {
           if (message.getText().toString().isEmpty())
           {
               message.setError("Write some Message ");
               message.requestFocus();
           }
           else if (UtilsMethod.INSTANCE.isNetworkAvialable(this) == false)
           {
               Toast.makeText(this, "Please Check Your Network Connectivity", Toast.LENGTH_SHORT).show();
           }
           else
           {


           }
        }
    }


}