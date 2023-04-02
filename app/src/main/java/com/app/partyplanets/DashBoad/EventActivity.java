package com.app.partyplanets.DashBoad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.partyplanets.Data.Data;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.app.partyplanets.Utils.Loader;
import com.app.partyplanets.Utils.UtilsMethod;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EventActivity extends AppCompatActivity implements View.OnClickListener
{
    DatePickerDialog datePicker;
    private int mYear, mMonth, mDay, mHour, mMinute;
    LinearLayout slectDate;
    TextView showdate,hotel;
    String date="";
    EditText fname,lname,email,city,contact,budget,address,message,guest;
    Button submit;
    Loader loader;
    String event_type,userId;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        slectDate=findViewById(R.id.slectdate);
        showdate=findViewById(R.id.date);
        back=findViewById(R.id.back);
        hotel=findViewById(R.id.hotel);
        guest=findViewById(R.id.guest);

        fname=findViewById(R.id.fname);
        lname=findViewById(R.id.lname);
        email=findViewById(R.id.email);
        city=findViewById(R.id.city);
        contact=findViewById(R.id.contact);
        budget=findViewById(R.id.budget);
        address=findViewById(R.id.address);
        message=findViewById(R.id.message);
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(this);
        back.setOnClickListener(this);
        loader = new Loader(this, android.R.style.Theme_Translucent_NoTitleBar);
        event_type=getIntent().getStringExtra("event_type");
        SharedPreferences myPreferences = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String secureloginResponse = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, null);
        Data securelogincheckResponse = new Gson().fromJson(secureloginResponse, Data.class);
        userId = securelogincheckResponse.getId();
        hotel.setText(event_type);



        final Calendar c = Calendar.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            datePicker = new DatePickerDialog(EventActivity.this);
        }
        slectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(EventActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                            showdate.setText(sd);
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

    @Override
    public void onClick(View v)
    {
        if (v==submit)
        {
            if (fname.getText().toString().isEmpty())
            {
                fname.setError("Enter Name");
                fname.requestFocus();
            }
            else if (lname.getText().toString().isEmpty())
            {
                lname.setError("Enter Name");
                lname.requestFocus();
            }
            else if (email.getText().toString().isEmpty())
            {
                email.setError("Enter Email");
                email.requestFocus();
            }
            else if (city.getText().toString().isEmpty())
            {
                city.setError("Enter city");
                city.requestFocus();
            } else if (contact.getText().toString().isEmpty())
            {
                contact.setError("Enter Phone number ");
                contact.requestFocus();
            }
            else if (budget.getText().toString().isEmpty())
            {
                budget.setError("Enter Amount ");
                budget.requestFocus();
            }
            else if (guest.getText().toString().isEmpty())
            {
                guest.setError("Enter Number of guest");
                guest.requestFocus();
            }
            else if (address.getText().toString().isEmpty())
            {
                address.setError("Enter address ");
                address.requestFocus();
            }
            else if (UtilsMethod.INSTANCE.isValidEmail(email.getText().toString()) == false)
            {
                //Toast.makeText(this, "Please Enter valid E-mail Id", Toast.LENGTH_SHORT).show();
                email.setError("Please Enter valid E-mail Id");
                email.requestFocus();
            }
            else if (UtilsMethod.INSTANCE.isNetworkAvialable(this) == false)
            {
                Toast.makeText(this, "Please Check Your Network Connectivity", Toast.LENGTH_SHORT).show();
            }
            else if (date.isEmpty())
            {
                Toast.makeText(this, "Please select date", Toast.LENGTH_SHORT).show();
            }
            else
            {   loader.show();
                loader.setCancelable(false);
                loader.setCanceledOnTouchOutside(false);
                UtilsMethod.INSTANCE.addEvent(EventActivity.this,loader,fname.getText().toString()+" "+lname.getText().toString(),email.getText().toString()
                        ,contact.getText().toString(),event_type
                        ,date,budget.getText().toString()
                ,address.getText().toString(),message.getText().toString(),userId,guest.getText().toString());
            }



        }
        if (v==back)
        {
            finish();
        }

    }
}