package com.app.partyplanets.DashBoad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.partyplanets.Adpters.RoomsAdapter;
import com.app.partyplanets.Data.Data;
import com.app.partyplanets.Data.RoomData;
import com.app.partyplanets.Data.RoomDataModel;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.app.partyplanets.Utils.UtilsMethod;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RoomActivity extends AppCompatActivity implements RoomsAdapter.onListClickedRowListner
{   TextView date;
    RecyclerView recyclerView;
    RoomDataModel roomDataModel;
    ArrayList<RoomData> data;
    String value="";
    RelativeLayout layout;
    public   static TextView price;
    public   static TextView actualprice;
    public static String totalprice;
    public static TextView rooms;
    public static TextView totalroom;
    Button reserve;
    static int roomcount;
    ArrayList<String> roomid;
    String secureloginResponse;
    String UserId;
    String checkinDate,checkOutDate;
            //max_child,max_adult;
    String hotelId;
    String startDate,endDate,adults,child;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);
        recyclerView=findViewById(R.id.recyclerview);
        layout=findViewById(R.id.layout);
        date=findViewById(R.id.date);
        price=findViewById(R.id.price);
        totalroom=findViewById(R.id.rooms);
        actualprice=findViewById(R.id.actualprice);
        reserve=findViewById(R.id.reserve);
         value=getIntent().getStringExtra("data");
         hotelId=getIntent().getStringExtra("hotelId");
         startDate=getIntent().getStringExtra("checkIn");
         endDate=getIntent().getStringExtra("checkOut");
         adults=getIntent().getStringExtra("adults");
         child=getIntent().getStringExtra("child");

         dataParse(value);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date time1= null;
        Date time2= null;
        try {
            time1 = sdf.parse(startDate);
            time2=sdf.parse(endDate);
            SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
            String sd = newFormat.format(time1);
            String ed = newFormat.format(time2);
            date.setText("Availability: "+sd+" To "+ed);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SharedPreferences myPreferences = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        secureloginResponse = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, null);
        Data securelogincheckResponse = new Gson().fromJson(secureloginResponse, Data.class);
        UserId=securelogincheckResponse.getId();
    }

    public  static void getData(int prices, int room_id, int roomNo)
    {

        roomcount=roomcount+roomNo;
        price.setText(""+prices);
        rooms.setText(""+roomcount);
    }


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }

    /*@Override
    public void onClick(View v)
    {
        if (v==reserve)
        {
            startActivity(new Intent(RoomActivity.this,FinalBookingActivity.class));
            UtilsMethod.INSTANCE.reserveRoom(RoomActivity.this,"5",
                    "2022-12-12",
                    "2022-12-13","1","2","200","100","1",roomid,roomid);
}*/
    /* @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage)
    {
        if (activityFragmentMessage.getMessage().equalsIgnoreCase("Roomlist"))
        {
            dataParse(activityFragmentMessage.getFrom());
        }
    }*/
    private void dataParse(String from)
    {

            Log.d("djfhubfgdgbngxuy", from);
            Log.d("hjgdsdguyuwe", from);
            Gson gson = new Gson();
            roomDataModel = gson.fromJson(from, RoomDataModel.class);
            data = roomDataModel.getData();
            RoomsAdapter adapter = new RoomsAdapter(this, data,this);
            recyclerView.setHasFixedSize(false);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(llm);
            recyclerView.setAdapter(adapter);

    }

    @Override
    public void onListSelected(ArrayList<RoomData> data1,int offer_prece,int actual_price,ArrayList<String> roomId,ArrayList<String> roomCount)
    {



        for (RoomData roomdata: data)
        {
            Log.d("hellosff", roomdata.getMax_child());
            checkinDate=roomdata.getCheck_in();
            checkOutDate=roomdata.getCheck_out();
            //          max_child=  roomdata.getMax_child();
          //  max_adult=roomdata.getMax_adult();


        }

        reserve.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if (offer_prece != 0)
                {
                     UtilsMethod.INSTANCE.reserveRoom(RoomActivity.this, hotelId,
                            checkinDate,
                            checkOutDate, adults, child, String.valueOf(offer_prece), String.valueOf(actual_price), UserId, roomId, roomCount);
                    SharedPreferences prefs =getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("checkindate",checkinDate);
                    editor.putString("checkOutDate",checkOutDate);
                    editor.putString("max_adult",adults);
                    editor.putString("max_child",child);
                    editor.putString("offerprece", String.valueOf(offer_prece));
                    editor.putString("actualprice", String.valueOf(actual_price));
                    editor.commit();


                }
                else
                {
                    UtilsMethod.INSTANCE.alertBox("","Con't be available room  selected this data",RoomActivity.this);
                }

            }
        });

    }
   /* @Override
    public void onDestroy() {
        // Unregister the registered event.
        GlobalBus.getBus().unregister(this);
        super.onDestroy();}
    @Override
    public void onStart()
    {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }*/





}