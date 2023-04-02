package com.app.partyplanets.Restaurants;

import static com.app.partyplanets.R.color.red;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.app.partyplanets.Data.Data;
import com.app.partyplanets.R;
import com.app.partyplanets.RestaurantData.CartViewData;
import com.app.partyplanets.RestaurantData.CartViewModel;
import com.app.partyplanets.RestaurantData.Food;
import com.app.partyplanets.RestaurantData.TimeSlotData;
import com.app.partyplanets.RestaurantData.TimeSlotModel;
import com.app.partyplanets.ResttaurantAdapter.FoodMenuViewAdpter;
import com.app.partyplanets.ResttaurantAdapter.TimeSlotAdapter;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.app.partyplanets.Utils.FragmentActivityMessage;
import com.app.partyplanets.Utils.GlobalBus;
import com.app.partyplanets.Utils.Loader;
import com.app.partyplanets.Utils.UtilsMethod;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class RestauMenuBooking extends AppCompatActivity implements View.OnClickListener
{
    DatePickerDialog datePicker;
    private int mYear, mMonth, mDay, mHour, mMinute;
    TextView slectdate,amt,datepicker;
    TimeSlotModel timeSlotModel;
    TimePickerDialog timepickerdialog;
    ArrayList<TimeSlotData> timeSlotData;
    String selectedDate;
    String date="";
    String dated;
    String format;
    private TextView dateTimeDisplay;
    Spinner spinner;
    String[] persion = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30","31", "32", "33", "34", "35", "36", "37", "38", "39", "40","41", "42", "43", "44", "45", "46", "47", "48", "49", "50","51", "52", "53", "54", "55", "56", "57", "58", "59",
            "60","61", "62", "63", "64", "65", "66", "67", "68", "69", "70","71", "72", "73", "74", "75", "76", "77", "78", "79", "80",
            "81", "82", "83", "84", "85", "86", "87", "88", "89", "90","91", "92", "93", "94", "95", "96", "97", "98", "99", "100",};
    private SimpleDateFormat dateFormat;
    RecyclerView timeslot,recyclerview;
    Button bookNow;
    String restaurantId;
      int id;
    CartViewModel cartViewModel;
    int totalamount=0;
    int amount;
    TextView hotel;
    ImageView back;
    Loader loader;
    Calendar calendar;
    private int CalendarHour, CalendarMinute;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restau_menu_booking);

        loader = new Loader(this, android.R.style.Theme_Translucent_NoTitleBar);
        slectdate = findViewById(R.id.slectdate);
        spinner = findViewById(R.id.persion_spinner);
        bookNow = findViewById(R.id.bookNow);
        datepicker = findViewById(R.id.datepicker);
        amt = findViewById(R.id.amt);
        bookNow.setOnClickListener(this);

        hotel=findViewById(R.id.hotel);
        hotel.setText("Reservation of Guest ");
        back=findViewById(R.id.back);
        back.setOnClickListener(this);
        timeslot = findViewById(R.id.timeslot);
        recyclerview = findViewById(R.id.recyclerview);
        restaurantId = getIntent().getStringExtra("restaurantId");
        final Calendar c = Calendar.getInstance();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            datePicker = new DatePickerDialog(RestauMenuBooking.this);
        }
        slectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(RestauMenuBooking.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        date = (year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date time1 = null;
                        try {
                            time1 = sdf.parse(date);
                            SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
                            String sd = newFormat.format(time1);
                            slectdate.setText(sd);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                    }
                }, mYear, mMonth, mDay);


                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                datePickerDialog.show();
            }
        });

        datepicker.setOnClickListener(this);



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, persion);
        spinner.setAdapter(adapter);

    }

    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {
        if (activityFragmentMessage.getMessage().equalsIgnoreCase("TimeSlot"))
        {
            dataParse(activityFragmentMessage.getFrom());
        }
        else if (activityFragmentMessage.getMessage().equalsIgnoreCase("ShowFoodMenu"))
        {
            foodmenuList(activityFragmentMessage.getFrom());
        }

    }

    private void foodmenuList(String from)
    {
        Gson gson = new Gson();
        cartViewModel = gson.fromJson(from, CartViewModel.class);
        CartViewData datum=cartViewModel.getData();
        amount= Integer.parseInt(datum.getSub_total());
        amt.setText("Total amount:  ₹ "+amount);
        ArrayList<Food> data = datum.getData();
        Collections.reverse(datum.getData());
        FoodMenuViewAdpter adapter=new FoodMenuViewAdpter(RestauMenuBooking.this,data,restaurantId);
        adapter.notifyDataSetChanged();
        recyclerview.setHasFixedSize(false);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(llm);
        recyclerview.setAdapter(adapter);
    }



    private void dataParse(String from)
    {
        Gson gson = new Gson();
        timeSlotModel = gson.fromJson(from, TimeSlotModel.class);
        timeSlotData = timeSlotModel.getData();
        TimeSlotAdapter adapter=new TimeSlotAdapter(RestauMenuBooking.this,timeSlotData);
        timeslot.setHasFixedSize(false);
        timeslot.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        timeslot.setLayoutManager(llm);
        timeslot.setAdapter(adapter);

    }


    public void termsAndPolicies(View v1,String   userId, String date, String person,  String selectedDate, String restaurantId, String amount)
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
                Log.d("dkfjgdfhg", selectedDate);
                UtilsMethod.INSTANCE.checkSlotAvailability(RestauMenuBooking.this,loader, userId, date, person, selectedDate, restaurantId, String.valueOf(amount));


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




  public  void getDataAdapter(int  carId)
  { id=carId;
  }
public void getTotalAmn(int amttotal)
{


}


    @Override
    public void onDestroy() {

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
          if (v==datepicker)
          {
              if(!slectdate.getText().toString().isEmpty())
              {
                  Log.d("dfjhfjvbdnfv",slectdate.getText().toString());
                  calendar = Calendar.getInstance();
                  CalendarHour = calendar.get(Calendar.HOUR_OF_DAY);
                  CalendarMinute = calendar.get(Calendar.MINUTE);

                  timepickerdialog = new TimePickerDialog(this,
                            new TimePickerDialog.OnTimeSetListener()
                          {
                              SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
                              String currentDateandTime = sdf.format(Calendar.getInstance().getTime());
                              SimpleDateFormat sdfdd = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                              String dv = sdfdd.format(Calendar.getInstance().getTime());
                              String OpenTime="9:0 AM";
                              String endTime="11:5 PM";

                              Date time1,time3;

                              {
                                  try
                                  {
                                      time1 = sdf.parse(OpenTime);
                                      time3 = sdf.parse(endTime);

                                  } catch (ParseException e) {
                                      e.printStackTrace();
                                  }
                              }

                              @Override
                              public void onTimeSet(TimePicker view, int hourOfDay, int minute)
                              {

                                  if (hourOfDay == 0)
                                  {

                                      hourOfDay += 12;
                                      format = " AM";
                                  } else if (hourOfDay == 12)
                                  {

                                      format = " PM";

                                  } else if (hourOfDay > 12)
                                  {

                                      hourOfDay -= 12;
                                      format = " PM";

                                  }
                                  else
                                  {

                                      format = " AM";
                                  }

                                  Date time2;

                                  {
                                      try {
                                          time2 = sdf.parse(hourOfDay + ":" + minute + format);


                                          if (time1.before(time2) && time3.after(time2))
                                          {
                                              if (compareTime(OpenTime, hourOfDay + ":" + minute + format))
                                              {
                                                  if (dv.equalsIgnoreCase(slectdate.getText().toString()))
                                                  {

                                                      if (compareTime(currentDateandTime, hourOfDay + ":" + minute + format)) {

                                                          datepicker.setText(hourOfDay + ":" + minute + format);
                                                          selectedDate=hourOfDay + ":" + minute + format;
                                                      }
                                                      else
                                                      {
                                                          datepicker.setText(" ");
                                                          selectedDate="";
                                                          //Toast.makeText(BookingActivityNew.this, "currentDate"+cudhbjhdv, Toast.LENGTH_SHORT).show();
                                                          Toast.makeText(RestauMenuBooking.this, "Please Select Future time", Toast.LENGTH_SHORT).show();
                                                      }
                                                  } else {
                                                      Log.d("dgdfdghgh", hourOfDay + ":" + minute + format);
                                                      datepicker.setText(hourOfDay + ":" + minute + format);
                                                      selectedDate=hourOfDay + ":" + minute + format;
                                                  }
                                              } else
                                              {
                                                  selectedDate="";


                                              }
                                          }
                                          else
                                          {
                                              datepicker.setText(" ");
                                              selectedDate="";
                                              Toast.makeText(RestauMenuBooking.this, "Please select time between 9 AM to 11 PM Only ", Toast.LENGTH_SHORT).show();
                                          }
                                         }
                                      catch(ParseException e)
                                          {
                                              e.printStackTrace();
                                          }
                                      }


                              }

                          }, CalendarHour, CalendarMinute, false);
                           timepickerdialog.show();
                  }
              else
              {
                  Toast.makeText(RestauMenuBooking.this, "First ,Please Select Date", Toast.LENGTH_SHORT).show();

              }

            }


        if (v==bookNow)
        {
            getSlot(v);


        }
        if (v==back)
        {
            finish();
        }
    }










    boolean  compareTime(String currenttime,String todate)
    {

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        try {
            Date time1=sdf.parse(currenttime);
            Date time2=sdf.parse(todate);
            if(time1.before(time2))
            {
                Log.d("sdff","dmv");
                return true;
            }
            else
            {
                Log.d("sdfsff","dmvsdf");
                datepicker.setText(" ");
                selectedDate="";
                return  false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

    }
    boolean  compareTime1(String currenttime,String todate)
    {
        Log.d("djhhdghv",currenttime);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        try {
            Date time1=sdf.parse(currenttime);
            Date time2=sdf.parse(todate);
            if(time1.after(time2))
            {
                Log.d("sdff","dmv");
                return true;
            }
            else
            {
                return  false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

    }

    private void getSlot(View v)
    {
        SharedPreferences myPreferences = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String secureloginResponse = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, null);
        Data securelogincheckResponse = new Gson().fromJson(secureloginResponse, Data.class);
        String userId = securelogincheckResponse.getId();
        String person = String.valueOf(spinner.getSelectedItem());
        if (date.isEmpty())
        {
            Toast.makeText(RestauMenuBooking.this, "Please Select  Date", Toast.LENGTH_SHORT).show();

        }
        else if (datepicker.getText().toString().isEmpty())
        {
            Toast.makeText(RestauMenuBooking.this, "Please select time ", Toast.LENGTH_SHORT).show();

        }
        else if (person.isEmpty())
        {
            Toast.makeText(RestauMenuBooking.this, "please select Guests ", Toast.LENGTH_SHORT).show();
        } else if (UtilsMethod.INSTANCE.isNetworkAvialable(RestauMenuBooking.this) == false) {
            Toast.makeText(RestauMenuBooking.this, "Please Check Your Network Connectivity", Toast.LENGTH_SHORT).show();
        }
        else
        {
            termsAndPolicies(v, userId, date, person, selectedDate, restaurantId, String.valueOf(amount));

        }

    }
}