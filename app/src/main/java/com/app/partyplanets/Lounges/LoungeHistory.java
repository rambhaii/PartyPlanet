package com.app.partyplanets.Lounges;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.partyplanets.Data.SelectedRoomModel;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoungeHistory extends AppCompatActivity implements View.OnClickListener {
    String data ,date,alldata;
    RecyclerView recyclerview;
    ImageView image;
    SelectedRoomModel selectedRoomModel;
    TextView bookingDate,paymentstatus,hotel,guest,platetype,totalamount,lawnsprice,functionname,tv_address;
    LinearLayout lawns,plate;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lounge_history);
        image=findViewById(R.id.image);
        tv_address=findViewById(R.id.tv_address);
        bookingDate=findViewById(R.id.bookingDate);
        paymentstatus=findViewById(R.id.paymentstatus);
        lawns=findViewById(R.id.lawns);
        lawnsprice=findViewById(R.id.lawnsprice);
        functionname=findViewById(R.id.functionname);
        plate=findViewById(R.id.plate);
        hotel=findViewById(R.id.hotel);
        guest=findViewById(R.id.guest);
        back=findViewById(R.id.back);
        back.setOnClickListener(this);
        platetype=findViewById(R.id.platetype);
        totalamount=findViewById(R.id.totalamount);
        data = getIntent().getStringExtra("data");
        date = getIntent().getStringExtra("date");
        alldata = getIntent().getStringExtra("alldata");
        selectedRoomModel = new Gson().fromJson(alldata, SelectedRoomModel.class);
         if (selectedRoomModel.getModule_name().equalsIgnoreCase("Lawns"))
         {
             lawns.setVisibility(View.VISIBLE);
             lawnsprice.setText("₹. "+selectedRoomModel.getOffer_price());
         }
        if (selectedRoomModel.getPlate_type()==null)
        {
            plate.setVisibility(View.GONE);
        }
        Glide.with(this)
                .load(ApplicationConstant.INSTANCE.baseUrl +selectedRoomModel.getBanner_img())
                .fitCenter()
                .error(R.drawable.imm)
                .into(image);

        String checkoutDate=selectedRoomModel.getCheckindate();
        SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date time1= null;
        try {
            time1 = fmt.parse(checkoutDate);
            String sd = newFormat.format(time1);
            bookingDate.setText(""+sd+"");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // bookingDate.setText(date);
        hotel.setText(selectedRoomModel.getName());
        guest.setText(selectedRoomModel.getPerson_no()+" Guest ");
        platetype.setText("₹ "+selectedRoomModel.getPlate_type()+" / Plate");
        totalamount.setText("₹ "+selectedRoomModel.getTotal_price()+" ");
        functionname.setText(selectedRoomModel.getFunction_type());
        tv_address.setText(selectedRoomModel.getAddress());
        if (selectedRoomModel.getPayment_type().equalsIgnoreCase("online"))
        {   paymentstatus.setTextColor(Color.RED);
            paymentstatus.setText("₹ "+selectedRoomModel.getTotal_price()+" Paid");
        }else
        {
           paymentstatus.setText("Payment on visit ");
           paymentstatus.setTextColor(Color.BLUE);
        }}

    @Override
    public void onClick(View v) {
        if (v==back)
        {
            finish();
        }
    }
}