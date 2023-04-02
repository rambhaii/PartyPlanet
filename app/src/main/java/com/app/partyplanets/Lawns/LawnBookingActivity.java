package com.app.partyplanets.Lawns;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.app.partyplanets.Data.Data;
import com.app.partyplanets.Data.Dataum;
import com.app.partyplanets.LawnModel.Foodcategoryitem;
import com.app.partyplanets.LawnModel.Fooditem;
import com.app.partyplanets.LawnModel.LawnData;
import com.app.partyplanets.LawnModel.LawnResponse;
import com.app.partyplanets.LawnModel.LawnsAdaptar;
import com.app.partyplanets.R;
import com.app.partyplanets.Restaurants.RestauMenuBooking;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.app.partyplanets.Utils.Loader;
import com.app.partyplanets.Utils.UtilsMethod;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class LawnBookingActivity extends AppCompatActivity implements View.OnClickListener {
    DatePickerDialog datePicker;
    private int mYear, mMonth, mDay, mHour, mMinute;
    ImageView back;
    RadioButton radia_id1,radia_id2;
    String data,date;
    Dataum dataum;
    Button reserve;
    LawnsAdaptar adapter;
    TextView slectdate,enddate,hotel;
    EditText function,guest;
    RecyclerView recyclerview;
    LawnData selectedLawn;
    String responselist,list_id;
    String startDate="",endDa="";
    Double lawnprice;
    Loader loader;
    String userId;
    ArrayList<Fooditem> fooditems=new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lawn_booking2);
        loader = new Loader(this, android.R.style.Theme_Translucent_NoTitleBar);
        radia_id1=findViewById(R.id.radia_id1);
        radia_id2=findViewById(R.id.radia_id2);
        recyclerview=findViewById(R.id.recyclerview);

        enddate=findViewById(R.id.enddate);
        hotel=findViewById(R.id.hotel);
        reserve=findViewById(R.id.reserve);
        function=findViewById(R.id.function);
        guest=findViewById(R.id.guest);
        back=findViewById(R.id.back);
        back.setOnClickListener(this);
        reserve.setOnClickListener(this);

        slectdate=findViewById(R.id.slectdate);
        data=getIntent().getStringExtra("data");
        list_id=getIntent().getStringExtra("list_id");
        lawnprice= Double.valueOf(getIntent().getStringExtra("lawnprice"));
        responselist=getIntent().getStringExtra("responselist");

        SharedPreferences myPreferences = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String secureloginResponse = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, null);
        Data securelogincheckResponse = new Gson().fromJson(secureloginResponse, Data.class);
        userId = securelogincheckResponse.getId();


        foodMenulisted(responselist);
        dataum = new Gson().fromJson(data, Dataum.class);
        hotel.setText(" Reserved lawns");

        final Calendar c = Calendar.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            datePicker = new DatePickerDialog(LawnBookingActivity.this);
        }
        slectdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(LawnBookingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        // showdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        //date=(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        date = (year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date time1 = null;
                        try {
                            time1 = sdf.parse(date);
                            SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
                            String sd = newFormat.format(time1);
                            startDate=date;
                            slectdate.setText(sd);
                           /* dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                            dated = dateFormat.format(c.getTime());
                            slectdate.setText(date);*/


                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                    }
                }, mYear, mMonth, mDay);

                /* datePickerDialog.show();*/

                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                datePickerDialog.show();
            }
        });



      /*  reserve.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //  showSortingDialog();
//                selectedLawn.foodcategoryitems.forEach();
                for (Foodcategoryitem e : selectedLawn.foodcategoryitems)
                {
                    for (Fooditem fooditem :e.getFooditems() )
                    {
                         Log.d("dvshbivsdgiugvds: ",fooditem.image+"  "+fooditem.isSelected);
                    }
                }
                //showSortingDialog();
            }
        });*/

        enddate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(LawnBookingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // showdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        //date=(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        date = (year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date time1 = null;
                        try {
                            time1 = sdf.parse(date);
                            SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
                            String sd = newFormat.format(time1);
                            enddate.setText(sd);
                            endDa=date;
                           /* dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                            dated = dateFormat.format(c.getTime());
                            slectdate.setText(date);*/


                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                    }
                }, mYear, mMonth, mDay);

                /* datePickerDialog.show();*/

                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                datePickerDialog.show();
            }
        });










    }

    private void foodMenulisted(String from)
    {
        Gson gson = new Gson();
        LawnResponse  response= gson.fromJson(from, LawnResponse.class);
        ArrayList<LawnData> lawnData = response.getData();
        recyclerview.setLayoutManager(new LinearLayoutManager(LawnBookingActivity.this));
        adapter=new LawnsAdaptar(LawnBookingActivity.this,lawnData);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(llm);
        recyclerview.setAdapter(adapter);



    }

    public void selectedFoodMenu(LawnData lawnData)
    {
        selectedLawn=lawnData;
        Log.d("selectedFoodMenu: ",lawnData.foodcategoryitems.toString());
       /* if (guest.getText().toString().isEmpty())
        {
            guest.setError("Please Enter number of guest ");
            guest.requestFocus();
        }
        else {
            int person = Integer.parseInt(guest.getText().toString());
            int totalamount = Integer.parseInt(selectedLawn.getAmount_type());
            double price = totalamount * person + lawnprice;
            Toast.makeText(this, "" + price, Toast.LENGTH_SHORT).show();
        }*/

    }





    @Override
    public void onClick(View v)
    {
        if (v==back)
        {
            finish();
        }
        if (v==reserve)
        {

            try {
                String catId;

                ArrayList<String> menuId=new ArrayList<>();
                ArrayList<HashMap<String ,String>> finalArray=new ArrayList<>();

                int totalamount = Integer.parseInt(selectedLawn.getAmount_type());
                String plateType=selectedLawn.getId();

                for (Foodcategoryitem e : selectedLawn.foodcategoryitems)
                {
                    HashMap<String ,String> data=new HashMap<>();
                    catId=e.getId();
                    menuId.clear();

                    for (Fooditem fooditem : e.getFooditems())
                    {
                        Log.d( "gfytfyfy: ",new Gson().toJson(fooditem));

                        if (fooditem.isSelected)
                        {

                            menuId.add(fooditem.getId());
                        }

                    }
                    data.put("cat_id", catId);
                    data.put("menu_ids", String.valueOf(menuId));
                    finalArray.add(data);
                }



                if (startDate.isEmpty())
                {
                    Toast.makeText(LawnBookingActivity.this, "Please Select from date  ", Toast.LENGTH_SHORT).show();

                } else if (endDa.isEmpty()) {
                    Toast.makeText(LawnBookingActivity.this, "Please Select to date  ", Toast.LENGTH_SHORT).show();

                } else if (guest.getText().toString().isEmpty()) {
                    guest.setError("Please Enter number of guest ");
                    guest.requestFocus();
                } else if (function.getText().toString().isEmpty()) {
                    function.setError("Please Enter function Name ");
                    function.requestFocus();
                } else if (UtilsMethod.INSTANCE.isNetworkAvialable(this) == false)
                {
                    Toast.makeText(this, "Please Check Your Network Connectivity", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int person = Integer.parseInt(guest.getText().toString());
                    double price= totalamount * person + lawnprice;
                    termsAndPolicies(v,startDate,endDa,guest.getText().toString(),function.getText().toString(),list_id,userId,plateType,price,finalArray,"1"  );

                }
            }catch (Exception e)
            {
                e.printStackTrace();
                Log.d("sdnm",e.getMessage());
            }
        }

    }
 /*   void showSortingDialog() {
        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(this);

        View sheetView = getLayoutInflater().inflate(R.layout.dialog_sorting, null);

        TextView text_action_apply=sheetView.findViewById(R.id.text_action_apply);
        TextView text_action_cancel=sheetView.findViewById(R.id.text_action_cancel);
        mBottomSheetDialog.setContentView(sheetView);

        mBottomSheetDialog.show();

    }*/

 public void termsAndPolicies(View v1, String from, String to, String guest, String function_name, String listing_id, String user_id, String plate_type, double offer_price, ArrayList<HashMap<String ,String>>   food_cat_type, String catering_type)
 {
     BlurView topBlurView;
     float radius = 10f;

     View decorView = this.getWindow().getDecorView();
     ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);
     Drawable windowBackground = decorView.getBackground();

     LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
     View popupView = inflater.inflate(R.layout.terms_and_polices, null);
     CheckBox checkBox=popupView.findViewById(R.id.checkBox);
     @SuppressLint({"MissingInflatedId", "LocalSuppress"})
     TextView polices=popupView.findViewById(R.id.polices);
     Button okButton=popupView.findViewById(R.id.okButton);
     TextView back=popupView.findViewById(R.id.back);
     TextView permision=popupView.findViewById(R.id.permision);
     polices.setText(""+ApplicationConstant.INSTANCE.partyPolicy);
     topBlurView = popupView.findViewById(R.id.blurView);
     topBlurView.setupWith(rootView, new RenderScriptBlur((this)))
             .setFrameClearDrawable(windowBackground) // Optional
             .setBlurRadius(radius);


     int width = LinearLayout.LayoutParams.MATCH_PARENT;
     int height = LinearLayout.LayoutParams.WRAP_CONTENT;
     boolean focusable = false;
     final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
     popupWindow.showAtLocation(v1, Gravity.BOTTOM, 0, 0);
     popupWindow.setOutsideTouchable(false);
     popupWindow.setFocusable(true);


     checkBox.setOnClickListener(new View.OnClickListener()
     {
         @SuppressLint("ResourceAsColor")
         @Override
         public void onClick(View v)
         {
             boolean isChecked = checkBox.isChecked();
             if (isChecked)
             {okButton.setVisibility(View.VISIBLE);
             }
             else
             {
                 okButton.setVisibility(View.GONE);
             }
         }
     });
     okButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v)
         {
             loader.show();
             loader.setCancelable(false);
             loader.setCanceledOnTouchOutside(false);
             UtilsMethod.INSTANCE.lawnBookings(LawnBookingActivity.this,loader,from,to,guest,function_name,listing_id,user_id,plate_type,offer_price,food_cat_type,"1");


             popupWindow.dismiss();
         }
     });
     back.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             popupWindow.dismiss();
         }
     });






 }


}