package com.app.partyplanets.DashBoad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.partyplanets.Adpters.GalleryAdapter;
import com.app.partyplanets.Data.DasboadModel;
import com.app.partyplanets.Data.Data;
import com.app.partyplanets.Data.Dataum;
import com.app.partyplanets.Data.Galleryimages;
import com.app.partyplanets.Data.RatingData;
import com.app.partyplanets.Model.list;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.APIClient;
import com.app.partyplanets.Utils.AllAPIs;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.app.partyplanets.Utils.Loader;
import com.app.partyplanets.Utils.UtilsMethod;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.jsoup.Jsoup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowDetails extends AppCompatActivity implements View.OnClickListener, GalleryAdapter.onListClickedRowListner {
    String data;
    Dataum dataum;
    list list;
    TextView alcohalPolicy,decorPolicy,totalrating,friendly,facelities,amenities,services,Cleanliness,askquestion_tv,showreview,hotel,hotel_name,parking, renovation,tv_address, description,policies,contact,email,offer_price,current_price,language,health_standard;
    ImageView image;
    Button booking,addcart;
    String secureloginResponse;
    String id="";
    String listId="";
    Object language_obj;
    ImageView back;
    String status;
    Button write_review;
    String hotel_id;
    RecyclerView recyclerView_gall;
    DasboadModel dasboadModel;
    ArrayList<Dataum> banerData;
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;
    LinearLayout linearlayout,linearlayoutp;
    Loader loader;
    WebView webView,Alcohol_Policy;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {   super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        loader = new Loader(this, android.R.style.Theme_Translucent_NoTitleBar);
        image=findViewById(R.id.image);
        Alcohol_Policy=findViewById(R.id.Alcohol_Policy);
        hotel=findViewById(R.id.hotel);
        friendly=findViewById(R.id.friendly);
        facelities=findViewById(R.id.facelities);
        amenities=findViewById(R.id.amenities);
        services=findViewById(R.id.services);
        Cleanliness=findViewById(R.id.Cleanliness);
        totalrating=findViewById(R.id.total);
        webView=findViewById(R.id.descriptionWebView);
        askquestion_tv=findViewById(R.id.askquestion_tv);
        recyclerView_gall=findViewById(R.id.recyclerView_gall);
        back=findViewById(R.id.back);
        hotel_name = findViewById(R.id.name);
        linearlayout = findViewById(R.id.linearlayout);
        linearlayoutp = findViewById(R.id.linearlayoutp);
        showreview = findViewById(R.id.showreview);
        tv_address = findViewById(R.id.tv_address);
        description = findViewById(R.id.description);
        policies = findViewById(R.id.policies);
        contact = findViewById(R.id.contact);
        email = findViewById(R.id.email);
        booking = findViewById(R.id.booking);
        addcart = findViewById(R.id.addcart);
        offer_price=findViewById(R.id.offer_price);
        current_price=findViewById(R.id.current_price);
        language=findViewById(R.id.language);
        health_standard=findViewById(R.id.health_standard);

        parking=findViewById(R.id.parking);
        booking.setOnClickListener(this);
        addcart.setOnClickListener(this);
        back.setOnClickListener(this);
        askquestion_tv.setOnClickListener(this);
        write_review=findViewById(R.id.write_review);
        write_review.setOnClickListener(this);
        showreview.setOnClickListener(this);

        status=getIntent().getStringExtra("status");
        data = getIntent().getStringExtra("data");
        dataum = new Gson().fromJson(data, Dataum.class);
        list = new Gson().fromJson(data, list.class);
        parshData(list);
        hotel_name.setText(dataum.getName());
        tv_address.setText(dataum.getAddress());
        String plainText = Html.fromHtml(dataum.getLong_description()).toString();
        description.setText(plainText);
        contact.setText("Phone No.  (+91)-"+dataum.getPhone_no());
        email.setText("Email:  "+dataum.getEmail_id());
        health_standard.setText(""+dataum.getHealth_and_hygiene());

        hotel_id=dataum.getId();
        ratinglist(hotel_id);
        parking.setText(""+dataum.getParking_policy());
        language_obj=dataum.getLanguages();
        String value= String.valueOf(language_obj);
        language.setText(value.replaceAll("[^A-Za-z0-9,]",""));


        if (dataum.getModule_slug().equals("hotels"))
        {
            hotel.setText("Hotel Details");
            booking.setText("  BOOK A STAY  ");
        }
        else if(dataum.getModule_slug().equals("restaurants"))
        {
            hotel.setText("Restaurant Details");
            booking.setText("  BOOK NOW  ");
        }
        else if(dataum.getModule_slug().equals("lounges-banquet-halls"))
        {
            hotel.setText("Lounges Details");
            booking.setText("  BOOK NOW  ");
        }
        else if(dataum.getModule_slug().equals("lawns"))
        {
            linearlayoutp.setVisibility(View.VISIBLE);
            offer_price.setText("₹ "+dataum.getOffer_price());
            current_price.setText("₹ "+dataum.getCurrent_price());
            hotel.setText("Lawns Details");
            booking.setText("  BOOK NOW  ");
        }



        String p = Html.fromHtml(dataum.getCatering_policy()+"\n"+ dataum.getDj_policy()+"\n"+dataum.getDecor_policy()).toString();
        String textFromHtml = Jsoup.parse(dataum.getDecor_policy().toString()).text();
        Log.d("kjdfhkjdfgh",textFromHtml);
       if(dataum.getDecor_policy().isEmpty())
       {
           decorPolicy=findViewById(R.id.decorPolicy);
           decorPolicy.setVisibility(View.GONE);
           webView.setVisibility(View.GONE);
       }


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultFontSize(12);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        webView.setLongClickable(false);
        webView.setHapticFeedbackEnabled(false);
        webView.loadData(dataum.getDecor_policy(), "text/html", "UTF-8");

        if (dataum.getOutside_alcohol().isEmpty())
        {
            alcohalPolicy=findViewById(R.id.alcohalPolicy);
            alcohalPolicy.setVisibility(View.GONE);
            Alcohol_Policy.setVisibility(View.GONE);
        }

        WebSettings webSettings1 = Alcohol_Policy.getSettings();
        webSettings1.setJavaScriptEnabled(true);
        webSettings1.setDefaultFontSize(12);
        Alcohol_Policy.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        Alcohol_Policy.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        Alcohol_Policy.setLongClickable(false);
        Alcohol_Policy.setHapticFeedbackEnabled(false);
        Alcohol_Policy.loadData(dataum.getOutside_alcohol(), "text/html", "UTF-8");
  Log.d("sdgfg",dataum.getOutside_alcohol());


        policies.setText(p);
        Glide.with(this)
                .load(ApplicationConstant.INSTANCE.baseUrl+"/"+dataum.getBanner_img())
                .fitCenter()
                .error(R.drawable.imm)
                .into(image);
        listId=dataum.getId();
        SharedPreferences myPreferences = this.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        secureloginResponse = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, null);
        Data securelogincheckResponse = new Gson().fromJson(secureloginResponse, Data.class);
        id = securelogincheckResponse.getId();
        if (status.equalsIgnoreCase("1"))
        {
            addcart.setVisibility(View.GONE);
        }
        else if (status.equalsIgnoreCase("2"))
        {
            addcart.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v)
    {
        if (v==booking)
        {
            if (dataum.getModule_slug().equals("hotels"))
            {   startActivity(new Intent(this, BookingHotel.class).putExtra("data", data));
                UtilsMethod.INSTANCE.getRoomsList(this, hotel_id);
            }
            else if(dataum.getModule_slug().equals("restaurants"))
            {     SharedPreferences prefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
                  SharedPreferences.Editor editor = prefs.edit();
                  editor.putString("listingId", hotel_id);
                  editor.commit();
                loader.show();
                loader.setCancelable(false);
                loader.setCanceledOnTouchOutside(false);
                UtilsMethod.INSTANCE.restaurantList(ShowDetails.this,dataum.getId(),data ,hotel_id,  loader);
           }
        else if(dataum.getModule_slug().equals("lounges-banquet-halls"))
        {   SharedPreferences prefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("listingId", hotel_id);
            editor.commit();
            loader.show();
            loader.setCancelable(false);
            loader.setCanceledOnTouchOutside(false);
            UtilsMethod.INSTANCE.loungFoodList(ShowDetails.this,hotel_id,data,loader,dataum.getModule_id());
        }
        else if(dataum.getModule_slug().equals("lawns"))
        {
            UtilsMethod.INSTANCE.bookingpop(v,ShowDetails.this,hotel_id,data,loader,dataum.getOffer_price());
        }



        }
        if (v==addcart)
        {
            UtilsMethod.INSTANCE.addToCart(ShowDetails.this,id,listId,dataum.getName().toString());
        }
        if (v==back)
        {
            finish();
        }
        if (v==write_review)
        {
            Log.d("lotelId",hotel_id);
            startActivity(new Intent(this,WriteReview.class).putExtra("hotelId",hotel_id).putExtra("userId",id));
        }
        if (v==showreview)
        {
            startActivity(new Intent(this,ShowReview.class).putExtra("hotel_id",id));
            UtilsMethod.INSTANCE.showReview(ShowDetails.this,hotel_id);
             Log.d("hotel_id",hotel_id);
        }
        if (v==askquestion_tv)
        {
            startActivity(new Intent(this,frequentlyAQ.class));
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();}
    public void parshData(list  list)
    {    Gson gson = new Gson();
         ArrayList<Galleryimages>  banerData = list.getGalleryimages();
        GalleryAdapter adapter=new GalleryAdapter(this,banerData,this);
        recyclerView_gall.setHasFixedSize(false);
        recyclerView_gall.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_gall.setLayoutManager(llm);
        recyclerView_gall.setAdapter(adapter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent)
    {
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    @Override
    public void onListSelected(int position)
    {


    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener
    {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector)
        {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            recyclerView_gall.setScaleX(mScaleFactor);
            recyclerView_gall.setScaleY(mScaleFactor);
            return true;
        }
    }










    public void ratinglist(String listingId)
    {
        Log.d("listingId",listingId);

            String header = ApplicationConstant.INSTANCE.Headertoken;
            AllAPIs api = APIClient.getClient().create(AllAPIs.class);
            Call<RatingData> call = api.ratingalist(header,listingId);
            call.enqueue(new Callback<RatingData>()
            {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(Call<RatingData> call, Response<RatingData> response)
                {
                    try {


                        Log.d("rrrrrrr", response.body().getMessage() + "  " + listingId);
                        if (response.body().getStatus() == 1)
                        {

                            friendly.setText("" + response.body().getDatum().getEco_friendliness() + "/" + "5");
                            facelities.setText("" + response.body().getDatum().getFacilities() + "/" + "5");
                            amenities.setText("" + response.body().getDatum().getAmenities() + "/" + "5");
                            services.setText("" + response.body().getDatum().getStaff_and_services() + "/" + "5");
                            Cleanliness.setText("" + response.body().getDatum().getCleanliness() + "/" + "5");
                            double total = (double) (response.body().getDatum().getEco_friendliness()
                                    + response.body().getDatum().getFacilities()
                                    + response.body().getDatum().getAmenities()
                                    + response.body().getDatum().getStaff_and_services()
                                    + response.body().getDatum().getCleanliness());
                            total = total / 5;
                            totalrating.setText("" + total);

                        } else {

                        }
                    }catch (Exception e)
                    {
                        Log.d("kjfdgfdgkj",e.getMessage());
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(Call<RatingData> call, Throwable t)
                {
                  Log.d("kxjfhkjf",t.getMessage());
                }
            });

    }




}