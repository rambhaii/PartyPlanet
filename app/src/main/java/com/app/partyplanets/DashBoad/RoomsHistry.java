package com.app.partyplanets.DashBoad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.partyplanets.Adpters.Amenties;
import com.app.partyplanets.Adpters.DasboadAdapter;
import com.app.partyplanets.Data.Amenities;
import com.app.partyplanets.Data.Galleryimages;
import com.app.partyplanets.Data.RoomHistory;
import com.app.partyplanets.Data.RoomHistoryModel;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RoomsHistry extends AppCompatActivity {
RecyclerView recyclerview;
String data,dated,checkOutDate;
ImageView back;
LinearLayout layout_list;
  Galleryimages galleryimages;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms_histry);
        layout_list=findViewById(R.id.layout_list);
        back=findViewById(R.id.back);
        data=getIntent().getStringExtra("data");
        dated=getIntent().getStringExtra("date");
        checkOutDate=getIntent().getStringExtra("checkOutDate");
        ShowRooms(data);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

    }
    Amenties adapter;
    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    RecyclerView  recyclerView;
    private void ShowRooms(String data)
    {
        Log.d("dsjkffdgrrhreer",""+data);
        RoomHistoryModel selectedRoomModels;

        Gson gson = new Gson();
        selectedRoomModels=gson.fromJson(data, RoomHistoryModel.class);

        for(RoomHistory d:selectedRoomModels.getBanerData())
        {
            LayoutInflater inflater2 = null;
            inflater2 = (LayoutInflater) getSystemService(this.LAYOUT_INFLATER_SERVICE);
            final View mLinearView = inflater2.inflate(R.layout.bookinghistory, null);
            TextView name=mLinearView.findViewById(R.id.title);
            TextView bedtype=mLinearView.findViewById(R.id.bedtype);
            TextView room_size=mLinearView.findViewById(R.id.room_size);

            recyclerView=mLinearView.findViewById(R.id.recyclerview4);
            TextView type=mLinearView.findViewById(R.id.type);
            TextView date=mLinearView.findViewById(R.id.date);
            TextView child=mLinearView.findViewById(R.id.child);
          //  TextView wifi=mLinearView.findViewById(R.id.wifi);
            TextView totalroom=mLinearView.findViewById(R.id.totalroom);
            Log.d("dhfddfjhgjhge",d.getAmenities().get(0).getName());
             adapter=new Amenties(RoomsHistry.this,d.getAmenities());
            GridLayoutManager layoutManager=new GridLayoutManager(this,2);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();




            ImageView image=mLinearView.findViewById(R.id.image);
            //  TextView wifi=mLinearView.findViewById(R.id.wifi);
            name.setText(d.getRoom_type());
            bedtype.setText(d.getBed_type());
            room_size.setText("Room Size: "+d.getRoom_size()+" ft²");
            type.setText("Room Type: "+d.getAc_type());
            totalroom.setText("Total Room :  "+d.getQty());
         //   total_amount.setText("₹."+d.getPrice());
            child.setText("Adults :  "+ d.getAdult_no()+"     Child :  "+d.getChild_no());
           // date.setText(d.getd);
            galleryimages=d.getGalleryimages().get(0);
            String img=galleryimages.getImage();
            String checkoutDate=d.getCreated_at();

            SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date time1= null;
            Date time2= null;

            try {
                time1 = fmt.parse(dated);
                time2= fmt.parse(checkOutDate);
                //   time2=newFormat.parse(checkoutDate);
                String sd = newFormat.format(time1);
                String sd1 = newFormat.format(time2);
                // String ed = newFormat.format(time2);
                date.setText("Booking Date:  "+sd+"  To  "+sd1);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            Glide.with(mLinearView)
                    .load(ApplicationConstant.INSTANCE.baseUrl +"/"+ img)
                    .placeholder(R.drawable.imm)
                    .fitCenter()
                    .into(image);
            layout_list.addView(mLinearView);
        }

    }

}