package com.app.partyplanets.Adpters;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.app.partyplanets.DashBoad.WriteReview;
import com.app.partyplanets.Data.Data;
import com.app.partyplanets.Data.SelectedRoomModel;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.app.partyplanets.Utils.Loader;
import com.app.partyplanets.Utils.UtilsMethod;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class BookingHistoryAdapter extends   RecyclerView.Adapter<BookingHistoryAdapter.ViewHolder>
{   private Context context;
    private List<SelectedRoomModel> msubslider = new ArrayList<>();
    Loader loader;
    Location gps_loc = null;
    Location network_loc = null;
    Location final_loc;
    double longitude;
    double latitude;
    String userCountry, userAddress;

    public BookingHistoryAdapter(Context context, List<SelectedRoomModel> msubslider)
    {
        super();
        this.context = context;
        this.msubslider = msubslider;
        Log.d("sdkjfhhjfd","djafhjhgf");
    }

    @NonNull
    @Override
    public BookingHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.booking_history_layout, parent, false);
        return  new ViewHolder(listItem);
    }
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull BookingHistoryAdapter.ViewHolder viewHolder, int position)
    {   SelectedRoomModel sliderItem = msubslider.get(position);
        viewHolder.title.setText(sliderItem.getName());
        viewHolder.amount.setText("₹ "+sliderItem.getTotal_price());
        viewHolder.amount.setTextColor(Color.BLUE);
        viewHolder.tv_address.setText(sliderItem.getAddress());
        loader = new Loader(context, android.R.style.Theme_Translucent_NoTitleBar);



          viewHolder.tracker.setOnClickListener(new View.OnClickListener()
            {
            @Override
            public void onClick(View v)
            {

                LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
                    return ;
                }
                try
                {
                    gps_loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    network_loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (gps_loc != null)
                {
                    final_loc = gps_loc;
                    latitude = final_loc.getLatitude();
                    longitude = final_loc.getLongitude();
                }
                else if (network_loc != null) {
                    final_loc = network_loc;
                    latitude = final_loc.getLatitude();
                    longitude = final_loc.getLongitude();
                }
                else
                {

                }
                ActivityCompat.requestPermissions((Activity) context, new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE}, 1);
                try {
                    Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                    List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                    if (addresses != null && addresses.size() > 0)
                    {   userCountry = addresses.get(0).getLocality();
                        userAddress = addresses.get(0).getAddressLine(0);
                          }
                    else
                    {   userCountry = "Unknown";

                    }
                if (!sliderItem.getLatitude().isEmpty() && !sliderItem.getLongitude().isEmpty())
                  {

                      String uri = "http://maps.google.com/maps?saddr=" + latitude+ "," +longitude + "&daddr=" +   Double.parseDouble(sliderItem.getLatitude()) + "," + Double.parseDouble(sliderItem.getLongitude()) ;
                     // String uri = "http://maps.google.com/maps?saddr=" + latitude+ "," +longitude + "&daddr=" +12.120000 + "," + 76.680000;
                      Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                      context.startActivity(intent);
                  }
                else
                {
                    String uri = "http://maps.google.com/maps?saddr=" + latitude+ "," +longitude + "&daddr=" +26.8467 + "," + 80.9462;
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    context.startActivity(intent);
                }




                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


            }
        });

        if (sliderItem.getPayment_type().equalsIgnoreCase("online"))
       {
           viewHolder.payment.setTextColor(Color.RED);
           viewHolder.payment.setText("Paid");

       }else
        {
            viewHolder.payment.setText("Cash ");
            viewHolder.payment.setTextColor(Color.BLUE);

        }



        if (sliderItem.getPayment_type().equalsIgnoreCase("Cash"))
        {
            viewHolder.linearLayout_cancle.setVisibility(View.VISIBLE);
            viewHolder.view_line.setVisibility(View.VISIBLE);
        }

        if (sliderItem.getStatus().equalsIgnoreCase("cancelled"))
        {
            viewHolder.cancelledMessage.setText("Your booking is cancelled ");
            viewHolder.cancelledMessage.setTextColor(Color.BLACK);
            viewHolder.cancelled.setVisibility(View.GONE);
            viewHolder.view_line.setVisibility(View.VISIBLE);
        }


        SharedPreferences myPreferences = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String secureloginResponse = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, null);
        Data securelogincheckResponse = new Gson().fromJson(secureloginResponse, Data.class);
        String  userId = securelogincheckResponse.getId();
        viewHolder.cancelled.setOnClickListener(new View.OnClickListener()
        {
          @Override
          public void onClick(View v)
          {

             UtilsMethod.INSTANCE.canceledkpop(viewHolder.itemView

                     ,(Activity) context
                     ,sliderItem.getBooking_id(),
                     userId,
                     viewHolder.cancelledMessage,
                     viewHolder.cancelled);



          }
      });

        viewHolder.timeslot.setVisibility(View.GONE);
        viewHolder.time.setVisibility(View.GONE);
        if (sliderItem.getTime_slot()!=null)
        {
            Log.d("djhtyeru",sliderItem.getName()+"  "+sliderItem.getTime_slot());
            viewHolder.timeslot.setVisibility(View.VISIBLE);
            viewHolder.time.setVisibility(View.VISIBLE);

            viewHolder.timeslot.setText(sliderItem.getTime_slot());
        }

           if (sliderItem.getCheckoutdate()!=null)
           {  try{
               String dd=sliderItem.getCheckoutdate();
               SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
               SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
               String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
               Date time1= null;

               time1 = fmt.parse(dd);
               String sd = newFormat.format(time1);
               viewHolder.date.setText(""+sd+"");
               Log.d("sjfkhhf",""+sd+"  "+currentDate);
               if (sd.equalsIgnoreCase(currentDate))
               {
                   feedback(viewHolder.itemView, (Activity) context,sliderItem.getId(),userId,sliderItem.getName());
               }


           } catch (ParseException e)
           {
           e.printStackTrace();
            }
           }

        String checkinDate=sliderItem.getCheckindate();
        String checkOutDate=sliderItem.getCheckoutdate();
        try {

        SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        Date time1= null;


            time1 = fmt.parse(checkinDate);
            String sd = newFormat.format(time1);
            viewHolder.date.setText(""+sd+"");
        } catch (ParseException e) {
            e.printStackTrace();
        }


       if (sliderItem.getStatus().equalsIgnoreCase("checkout"))
       {   Log.d("dfkjgnvb",sliderItem.getStatus());
           viewHolder.feedback.setVisibility(View.VISIBLE);
           viewHolder.feedback_line.setVisibility(View.VISIBLE);
       }
       viewHolder.feedback.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View v) {
               context.startActivity(new Intent(context, WriteReview.class).putExtra("hotelId",sliderItem.getId()).putExtra("userId",userId).putExtra("name",sliderItem.getName()));

           }
       });


        viewHolder.layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                UtilsMethod.INSTANCE.getHistrory(context,sliderItem.getModule_id()
                        ,sliderItem.booking_id,
                        sliderItem.getModule_name(),
                        checkinDate,checkOutDate,
                        new Gson().toJson(sliderItem),sliderItem.getPlate_type());

            }
        });

        Glide.with(viewHolder.itemView)
                .load(ApplicationConstant.INSTANCE.baseUrl+"/"+ sliderItem.getBanner_img())
                .fitCenter()
                .error(R.drawable.imm)
                .into(viewHolder.image);

    }

    @Override
    public int getItemCount()
    {
        return msubslider.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {  TextView title,tv_address,date,timeslot,time,cancelledMessage,payment,amount;
        ImageView image;
        CardView layout;
        LinearLayout linearLayout,linearLayout_cancle;
        View view_line,feedback_line;
        TextView cancelled;
        ImageView tracker;
        LinearLayout feedback;

        public ViewHolder(@NonNull View itemView)
        {    super(itemView);
            this.title=itemView.findViewById(R.id.title);
            this.image=itemView.findViewById(R.id.image);
            this.tv_address=itemView.findViewById(R.id.tv_address);
            this.layout=itemView.findViewById(R.id.layout);
            this.date=itemView.findViewById(R.id.date);
            this.timeslot=itemView.findViewById(R.id.timeslot);
            this.time=itemView.findViewById(R.id.time);
            this.cancelled=itemView.findViewById(R.id.cancelled);
            this.cancelledMessage=itemView.findViewById(R.id.cancelledMessage);
            this.linearLayout_cancle=itemView.findViewById(R.id.cancle);
            this.payment=itemView.findViewById(R.id.payment);
            this.view_line=itemView.findViewById(R.id.view_line);
            this.tracker=itemView.findViewById(R.id.tracker);
            this.feedback=itemView.findViewById(R.id.feedback);
            this.feedback_line=itemView.findViewById(R.id.feedbakline);
            this.amount=itemView.findViewById(R.id.amount);

        }
    }







        public void feedback(View view,Activity activity,String hotel_id,String userId,String name)
    {

        Log.d("jhfjh",userId);
        float radius = 10f;
        BlurView topBlurView;
        View decorView =activity.getWindow().getDecorView();
        ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);
        Drawable windowBackground = decorView.getBackground();

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.feedback, null);
        Button ok = popupView.findViewById(R.id.okButton);
        Button cancel= popupView.findViewById(R.id.Cancel);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView title= popupView.findViewById(R.id.title);
        title.setText(name);



        topBlurView = popupView.findViewById(R.id.blurView);
        topBlurView.setupWith(rootView, new RenderScriptBlur(activity))
                .setFrameClearDrawable(windowBackground) // Optional
                .setBlurRadius(radius);


        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(view, Gravity.TOP, 0, 0);
        popupWindow.setOutsideTouchable(false);
        final RadioGroup radioGroup;

        ok.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                context.startActivity(new Intent(context, WriteReview.class).putExtra("hotelId",hotel_id).putExtra("userId",userId).putExtra("name",name));
                popupWindow.dismiss();

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                popupWindow.dismiss();
            }
        });
    }




}
