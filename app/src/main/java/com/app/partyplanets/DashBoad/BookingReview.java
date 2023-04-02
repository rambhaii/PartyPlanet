package com.app.partyplanets.DashBoad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.partyplanets.Adpters.RoomsPreviewAdapter;
import com.app.partyplanets.Data.RoomGetByCarId;
import com.app.partyplanets.Data.RoomPreViewModel;
import com.app.partyplanets.Data.SelectedRoomModel;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.app.partyplanets.Utils.UtilsMethod;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class BookingReview extends AppCompatActivity implements PaymentResultListener
{
    RecyclerView recyclerView;
    TextView name,address,offer_price,start_date,end_date,price;
    ImageView imageView;
    String data;
    String userName,Noadults,Nochild;
    ArrayList<RoomPreViewModel> rooms;
    SelectedRoomModel selectedRoomModel;
    RoomGetByCarId roomGetByCarId;
    Button confirms;
    LinearLayout confirms1;
    Context context;
    String cartId;
    private BlurView topBlurView;
    private EditText amountEdt;
    private Button payBtn;


    Double actualprice;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_review);
        Checkout.preload(getApplicationContext());
        recyclerView = findViewById(R.id.recyclerView);
        name=findViewById(R.id.name);
        address=findViewById(R.id.tv_address);
        imageView=findViewById(R.id.image);
        offer_price=findViewById(R.id.offer_price);
        start_date=findViewById(R.id.start_date);
        end_date=findViewById(R.id.end_date);
        price=findViewById(R.id.oldprice);
        confirms = findViewById(R.id.successfull);
        cartId=getIntent().getStringExtra("cartId");
        data=getIntent().getStringExtra("data");
        userName=getIntent().getStringExtra("userName");
        Gson gson=new Gson();
        RoomGetByCarId roomGetByCarId = gson.fromJson(data, RoomGetByCarId.class);
        selectedRoomModel=roomGetByCarId.getData();
        name.setText(selectedRoomModel.getName());
        address.setText(selectedRoomModel.getAddress());
        Glide.with(this)
                .load(ApplicationConstant.INSTANCE.baseUrl+"/"+selectedRoomModel.getBanner_img())
                .fitCenter()
                .error(R.drawable.nature)
                .into(imageView);
           SharedPreferences myPreferences = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         Date time1= null;
        Date time2= null;
        try {
            time1 = sdf.parse(myPreferences.getString("checkindate",""));
             time2=sdf.parse(myPreferences.getString("checkOutDate",""));
            SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
            String sd = newFormat.format(time1);
            String ed = newFormat.format(time2);
            start_date.setText(sd);
            end_date.setText(ed);

        } catch (ParseException e) {
            e.printStackTrace();
        }
           offer_price.setText("Rs."+myPreferences.getString("offerprece",""));
           price.setText("Rs."+myPreferences.getString("actualprice",""));
           actualprice= Double.valueOf(myPreferences.getString("offerprece",""))*100;
           rooms=selectedRoomModel.getRoomData();
          Noadults=selectedRoomModel.getBookingAdultNo();
          Nochild=selectedRoomModel.getBookingChildNo();
           dataParse(selectedRoomModel,userName,Noadults,Nochild);

        confirms.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
             //   onButtonShowPopupWindowClick(v);
                userSuggestPermission(v,confirms);
            }
        });}

    public void userSuggestPermission(View v1,TextView confirms)
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
        polices.setText(""+ApplicationConstant.INSTANCE.polices);
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
              public void onClick(View v) {
                  onButtonShowPopupWindowClick(v1,confirms);
                  confirms.setVisibility(View.GONE);
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

    private void dataParse( SelectedRoomModel from,String userName,String Noadults,String Nochild)
    {   rooms = from.getRoomData();
        RoomsPreviewAdapter adapter = new RoomsPreviewAdapter(this, rooms,userName,Noadults,Nochild);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }

    public void onButtonShowPopupWindowClick(View view,TextView confirms)
    {
        float radius = 10f;
        View decorView =getWindow().getDecorView();
        ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);
        Drawable windowBackground = decorView.getBackground();

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.paymentbox, null);
        //////////////////////////////////////////////////
        TextView back=popupView.findViewById(R.id.back);

        topBlurView=popupView.findViewById(R.id.blurView);
        topBlurView.setupWith(rootView, new RenderScriptBlur(this))
                .setFrameClearDrawable(windowBackground) // Optional
                .setBlurRadius(radius);

        Button   search=popupView.findViewById(R.id.search);
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = false;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupWindow.setOutsideTouchable(false);
    //    popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);

        final RadioGroup radioGroup;

        radioGroup = popupView.findViewById(R.id.groupradio);
        search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {   int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1)
                {
                    RadioButton selectedRadioButton;
                    selectedRadioButton = popupView.findViewById(selectedRadioButtonId);
                    String paymentType = selectedRadioButton.getText().toString();
                    if (paymentType.equals("Online"))
                    {
                        startPayment();
                        popupWindow.dismiss();
                    }
                    else
                    {  UtilsMethod.INSTANCE.payment(BookingReview.this,cartId,"cash","ascdsadf","cash", String.valueOf(actualprice),"asdfds","sdvsasd");
                        popupWindow.dismiss();
                    }
                }
                else
                {
                    Toast.makeText(BookingReview.this, "Please select  Payment Type", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                confirms.setVisibility(View.VISIBLE);
                popupWindow.dismiss();
            }
        });


    }

    public void startPayment()
    {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_Ujd8385mQjXqFH");

        checkout.setImage(R.drawable.abc);
        final Activity activity = this;
        try {
            JSONObject options = new JSONObject();

            options.put("name", "Merchant Name");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
            // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", actualprice);//pass amount in currency subunits
            options.put("prefill.email", "gaurav.kumar@example.com");
            options.put("prefill.contact","9988776655");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);
            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }


    @Override
    public void onPaymentSuccess(String s)
    {
        UtilsMethod.INSTANCE.payment(BookingReview.this,cartId,"online",s,"successful", String.valueOf(actualprice),"njdsfjj",s);
    }

    @Override
    public void onPaymentError(int i, String s)
    {
        UtilsMethod.INSTANCE.alertBox("","Payment Failed due to error"+s,BookingReview.this);
    }






}