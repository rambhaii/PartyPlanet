package com.app.partyplanets.DashBoad;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.partyplanets.Data.Dataum;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.app.partyplanets.Utils.UtilsMethod;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.widget.DatePicker;
import android.widget.Toast;


public class BookingHotel extends AppCompatActivity implements View.OnClickListener
{

    TextView ditailspric, showdate,showenddate,name,hotel;
    ImageView back;
    /* Button btnDatePicker, btnTimePicker;
     EditText txtDate, txtTime;*/
    private int mYear, mMonth, mDay, mHour, mMinute;
    LinearLayout enddate,datepic;
    String date="",dateend="";
    RelativeLayout  search;
    String data;
    Dataum dataum;
    ImageView image;
    String[] arraySpinner = new String[]{
            "1", "2", "3", "4", "5"};
    String[] arraySpinnerchild= new String[]{
            "0","1", "2", "3", "4", "5"};

    Spinner child_spinner,adults_spinner;
    String adults,child;
    DatePickerDialog datePicker;
    private SimpleDateFormat dateFormat;
    String conSd,coned;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_hotel);
        back = findViewById(R.id.back);
        hotel = findViewById(R.id.hotel);
        hotel.setText("Bookings Hotel ");
        back.setOnClickListener(this);

        showdate = findViewById(R.id.showdate);
        name = findViewById(R.id.name);
        image = findViewById(R.id.image);
        datepic = findViewById(R.id.datepic);
        enddate=findViewById(R.id.enddate);
        showenddate=findViewById(R.id.showenddate);
        search=findViewById(R.id.search);
        search.setOnClickListener(this);
        final Calendar c = Calendar.getInstance();



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            datePicker = new DatePickerDialog(BookingHotel.this);
        }

        datepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {   mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(BookingHotel.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth)
                            {

                               // showdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                //date=(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                date=(year+"-"+ (monthOfYear + 1)+ "-" +dayOfMonth);

                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                Date time1= null;

                                try {
                                    time1 = sdf.parse(date);
                                    SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
                                    conSd = newFormat.format(time1);
                                    showdate.setText(conSd);

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }



                            }
                        }, mYear, mMonth, mDay);

               /* datePickerDialog.show();*/

                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                datePickerDialog.show();
            }
        }
        );





        enddate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(BookingHotel.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                            {
                                dateend=(year+"-"+ (monthOfYear + 1)+ "-" +dayOfMonth);
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                Date time1= null;

                                try {
                                    time1 = sdf.parse(dateend);
                                    SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
                                    coned = newFormat.format(time1);

                               if (compareTime(conSd,coned)==false)
                                {
                                    Toast.makeText(BookingHotel.this, "Please Select Future Check-Out Date  ", Toast.LENGTH_SHORT).show();
                                }
                               else
                               {


                                       showenddate.setText(coned);
                                   }
                               } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, mYear, mMonth, mDay);
               // datePickerDialog.show();
                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());

                // show the dialog
                datePickerDialog.show();

            }
        });
        data = getIntent().getStringExtra("data");

        dataum = new Gson().fromJson(data, Dataum.class);
        name.setText(dataum.getName());
        Log.d("fuhfdghkfj", "" + dataum.getModule_id());
        Glide.with(this)
                .load(ApplicationConstant.INSTANCE.baseUrl+"/"+dataum.getBanner_img())
                .fitCenter()
                .error(R.drawable.imm)
                .into(image);

        child_spinner=findViewById(R.id.child_spinner);
        adults_spinner=findViewById(R.id.adults_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adults_spinner.setAdapter(adapter);

        ArrayAdapter<String> adapterchild = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinnerchild);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        child_spinner.setAdapter(adapterchild);




        adults_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Log.d("jdshgushudgvuv",""+adults_spinner.getSelectedItem());

                adults= String.valueOf(adults_spinner.getSelectedItem());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
        child_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Log.d("jdshgufmgndjfkgshudgvuv",""+adults_spinner.getSelectedItem());
                child= String.valueOf(child_spinner.getSelectedItem());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


    }

    @Override
    public void onClick(View v)
    {
        if (v==back)
        {
            finish();
        }

        if (v==search)
        {
            Log.d("yuyrytthjgvj",date+"               "+dateend +"    "+child+"  "+adults);
            if (date.isEmpty())
            {
                Toast.makeText(this, "Please Select Check-in Date", Toast.LENGTH_SHORT).show();
            }
            else  if(dateend.isEmpty())
            {
                Toast.makeText(this, "Please Select check-out Date", Toast.LENGTH_SHORT).show();
            }

            else if (UtilsMethod.INSTANCE.isNetworkAvialable(this) == false)
            {
                Toast.makeText(this, "Please Check Your Network Connectivity", Toast.LENGTH_SHORT).show();
            } else {
                UtilsMethod.INSTANCE.searchRooms(BookingHotel.this, dataum.getId(), date, dateend, adults, child);
                //startActivity(new Intent(BookingHotel.this,Rooms.class));
            }

        }}

    boolean  compareTime(String currentDate,String end_date)
    {
        try {
        Log.d("iurvhgv",currentDate+"   "+end_date);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");

            Date startdate=sdf.parse(currentDate);
            Date endDate=sdf.parse(end_date);
            if(startdate.compareTo(endDate) < 0)
            {
                return true;
            }
            else
            {
                return  false;
            }

        } catch (ParseException e)
        {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
