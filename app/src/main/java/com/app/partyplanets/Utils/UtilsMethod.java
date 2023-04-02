package com.app.partyplanets.Utils;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.partyplanets.Activity.GetLocation;
import com.app.partyplanets.Activity.Login;
import com.app.partyplanets.Activity.OtpVerification;
import com.app.partyplanets.Activity.SignInEmail;
import com.app.partyplanets.Activity.SignUp;
import com.app.partyplanets.Activity.SplashActivity;
import com.app.partyplanets.DashBoad.BookingReview;
import com.app.partyplanets.DashBoad.DashBoad;
import com.app.partyplanets.DashBoad.FinalBookingActivity;
import com.app.partyplanets.DashBoad.RoomActivity;
import com.app.partyplanets.DashBoad.RoomsHistry;
import com.app.partyplanets.DashBoad.SearchingShowData;
import com.app.partyplanets.DashBoad.SuccessFull;
import com.app.partyplanets.DashBoad.WriteReview;
import com.app.partyplanets.Data.BookingHistoryModel;
import com.app.partyplanets.Data.DasboadModel;
import com.app.partyplanets.Data.Data;
import com.app.partyplanets.Data.DataRespone;
import com.app.partyplanets.Data.Event;
import com.app.partyplanets.Data.ReviewDataModel;
import com.app.partyplanets.Data.RoomDataModel;
import com.app.partyplanets.Data.RoomGetByCarId;
import com.app.partyplanets.Data.RoomHistoryModel;
import com.app.partyplanets.Data.Rooms;
import com.app.partyplanets.Data.secureLoginResponse;
import com.app.partyplanets.LawnModel.Lawn;
import com.app.partyplanets.LawnModel.LawnResponse;
import com.app.partyplanets.Lawns.LawnBookingActivity;
import com.app.partyplanets.Lawns.OutSideLawnBooking;
import com.app.partyplanets.Lawns.PaymentLawnActivity;
import com.app.partyplanets.Lounges.LoungeBooking;
import com.app.partyplanets.Lounges.LoungeHistory;
import com.app.partyplanets.R;
import com.app.partyplanets.RestaurantData.CartViewModel;
import com.app.partyplanets.RestaurantData.Cartmenu;
import com.app.partyplanets.RestaurantData.ResturantModel;
import com.app.partyplanets.RestaurantData.TimeSlotModel;
import com.app.partyplanets.Restaurants.BookMenuActivity;
import com.app.partyplanets.Restaurants.FoodMenuHistory;
import com.app.partyplanets.Restaurants.Restaurantbooking;
import com.google.gson.Gson;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import cn.pedant.SweetAlert.SweetAlertDialog;
import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public enum UtilsMethod {
    INSTANCE;

    public void setLoginrespose(Context context, String Loginrespose, String one)
    {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.Loginrespose, Loginrespose);
        editor.putString(ApplicationConstant.INSTANCE.one, one);
        editor.commit();

    }
    public void setLoginrespos(Context context, String Loginrespose, String one) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
       /* editor.putString(ApplicationConstant.INSTANCE.Loginrespose, Loginrespose);
        editor.putString(ApplicationConstant.INSTANCE.one, one);*/
        editor.commit();

    }
    public void BookingHistory(Context context, String bookinghistry, String one)
    {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.History, bookinghistry);
        editor.commit();

    }
    public void saveAddress(Context context, String address, String city,String state)
    {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.address, address);
        editor.putString(ApplicationConstant.INSTANCE.city, city);
        editor.putString(ApplicationConstant.INSTANCE.state, state);
        editor.commit();

    }



    public void hotelsdata(Context context, String hoteldata)
    {
        Log.d("dkjfhjdf",hoteldata);
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.hoteldata, hoteldata);
        editor.commit();

    }

    public void storelist(Context context, String storelist) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.storelist, storelist);
        editor.commit();
    }

    public void services(Context context, String service) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.service, service);
        editor.commit();
    }
    public void bannerlist(Context context, String bannerlist)
    {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.bannerlist, bannerlist);
        editor.commit();
    }




    public  void lawnsFilter(Activity context,Loader loader,String moduleId, String price,String city,String name,String start,String next,String guest )
    {

        Log.d("dfd",moduleId+"   p"+price+"   c"+city+" g"+guest+"  n"+name+"  ");
        String header=ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api=APIClient.getClient().create(AllAPIs.class);
        //Call<DasboadModel> call=api.lawnsFilter(header,moduleId,Double.parseDouble(price+""),city,name,"0","20",guest);
        Call<DasboadModel> call=api.lawnsFilter(header,moduleId,price,city,name,"0","20",guest);
        call.enqueue(new Callback<DasboadModel>() {
            @Override
            public void onResponse(Call<DasboadModel> call, Response<DasboadModel> response)
            {
                Log.d("jgfsyreiuyieur", new Gson().toJson(response.body()).toString());

                if (response != null) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try
                    {
                        if (response.body().getStatus()==1)
                        {

                            context.startActivity(new Intent(context, SearchingShowData.class)
                                    .putExtra("data", new Gson().toJson(response.body()).toString())
                                    .putExtra("moduleId",moduleId));
                            context.finish();


                        }
                        else
                        {
                          //  Toast.makeText(context, ApplicationConstant.INSTANCE.message, Toast.LENGTH_SHORT).show();
                            alertBox("",ApplicationConstant.INSTANCE.message,context);

                        }


                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }


                }

            }

            @Override
            public void onFailure(Call<DasboadModel> call, Throwable t) {

            }
        });
    }



  public void filterRestaurant(Activity context,Loader loader,String module_id,String city,String name,String slot_id,String start,String next)
  {
      Log.d("fkdjhdfg",module_id+""+city+""+name+""+slot_id);
    String header=ApplicationConstant.INSTANCE.Headertoken;
    AllAPIs api=APIClient.getClient().create(AllAPIs.class);
    Call<DasboadModel> call=api.restaurentFilter(header,module_id,city,name,slot_id,"0","50");
    call.enqueue(new Callback<DasboadModel>()
    {
        @Override
        public void onResponse(Call<DasboadModel> call, Response<DasboadModel> response)
        {
            if (response != null)
            {
                if (loader != null)
                {
                    if (loader.isShowing())
                        loader.dismiss();
                }

                try {
                    if (response.body().getStatus() == 1)
                    {
                        context.startActivity(new Intent(context, SearchingShowData.class)
                                .putExtra("data", new Gson().toJson(response.body()).toString())
                                .putExtra("moduleId",module_id));
                        context.finish();

                    }
                    else
                    {
                        Toast.makeText(context, "Restaurant con not available. This name or place ", Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        @Override
        public void onFailure(Call<DasboadModel> call, Throwable t) {

        }
    });

  }


    public void cancellledBooking(Context context, Loader loader, String booking_id, String user_id, TextView cancelledMessage,TextView cancelled)
    {
        try {
            String header = ApplicationConstant.INSTANCE.Headertoken;
            AllAPIs api = APIClient.getClient().create(AllAPIs.class);
            Call<Event> call = api.orderCancelled(header, booking_id, user_id);
            call.enqueue(new Callback<Event>() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onResponse(Call<Event> call, Response<Event> response)
                {
                    Log.d("cancelled",""+response.body().getStatus());


                    if (response != null)
                    {
                        if (loader != null)
                        {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.body().getStatus()==1)
                        {
                            cancelledMessage.setText("Your booking is cancelled ");
                            cancelledMessage.setTextColor(Color.BLUE);

                            cancelled.setVisibility(View.GONE);
                        }
                        else
                        {
                            alertBox("",response.body().getMessage(),context);
                        }

                    }
                }


                @Override
                public void onFailure(Call<Event> call, Throwable t) {

                }
            });
        }catch (Exception e)
        {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


    public void addEvent(Context context,Loader loader, String full_name,String email_id, String phone_no,String event_type, String event_date, String budget,String address,String message, String user_id ,String no_guest)
    {
        try {
            String header = ApplicationConstant.INSTANCE.Headertoken;
            AllAPIs api = APIClient.getClient().create(AllAPIs.class);
            Call<Event> call = api.addEvent(header, full_name, email_id, phone_no, event_type, event_date, budget, address, message, user_id,no_guest);
            call.enqueue(new Callback<Event>() {
                @Override
                public void onResponse(Call<Event> call, Response<Event> response) {
                    if (response != null) {
                        if (loader != null)
                        {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.body().getStatus()==1)
                        {

                            sweetAlertBox(context,"Successfully added","DashBoad");
                        }
                        else
                        {
                            alertBox("",response.body().getMessage(),context);
                        }

                    }
                }


                @Override
                public void onFailure(Call<Event> call, Throwable t) {

                }
            });
        }catch (Exception e)
        {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }









    public void lawnPayment(Context context,String cartid,String payment_type,String txn_id,String status,String amount,String payment_note,String response )
    {
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        Call<RoomGetByCarId> call = api.lawanpayment(header,cartid,payment_type,txn_id,amount,status,payment_note,response);
        call.enqueue(new Callback<RoomGetByCarId>() {
            @Override
            public void onResponse(Call<RoomGetByCarId> call, Response<RoomGetByCarId> response)
            {

                if(response.body().getStatus()==1)
                {

                    context.startActivity(new Intent(context,SuccessFull.class));
                }
                else
                {
                    alertBox("","Payment Failed",context);
                }
            }

            @Override
            public void onFailure(Call<RoomGetByCarId> call, Throwable t)
            {
                Log.d("jdkhfjh",t.getMessage());
            }
        });
    }

    public void loungeBookings(Context context, Loader loader, String from, String to, String guest, String function_name, String listing_id, String user_id, String plate_type,String module_id,  ArrayList<HashMap<String ,String>>  food_cat_type) {


        Log.d("loungbooking",from+""+to+"  "+guest+"   "+function_name+"  "+listing_id+"  "+user_id+"  "+plate_type+" "+module_id );
        String header = ApplicationConstant.INSTANCE.Headertoken;
            AllAPIs api = APIClient.getClient().create(AllAPIs.class);
            Call<Lawn> call = api.loungebooking(header, from,to , guest, function_name,listing_id, user_id,plate_type ,module_id,new Gson().toJson(food_cat_type));
            call.enqueue(new Callback<Lawn>()
            {
                @Override
                public void onResponse(Call<Lawn> call, Response<Lawn> response)
                {
                    if (response != null)
                    {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }


                        try {

                            if (response.body().getStatus() == 1) {


                                context.startActivity(new Intent(context, PaymentLawnActivity.class).putExtra("data", new Gson().toJson(response.body()).toString()));

                            } else {
                                Toast.makeText(context, "This Menu is not available at this Date ! ", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                    }


                }

                @Override
                public void onFailure(Call<Lawn> call, Throwable t)
                {
                    Log.d("dsjfddmndsbf",t.getMessage());
                }
            });





    }





    public void lawnBookings(Context context, Loader loader, String from, String to, String guest, String function_name, String listing_id, String user_id, String plate_type, double offer_price,  ArrayList<HashMap<String ,String>>   food_cat_type, String catering_type) {

        Log.d("fdhgkjhdfkjhg",new Gson().toJson(food_cat_type));
        Log.d("sdfjh",""+plate_type+"    "+plate_type);
        Log.d("lawnbookingee",from+" "+to+"  "+guest+"   "+function_name+"  "+listing_id+"  "+user_id+"  "+plate_type+" "+offer_price+" "+catering_type);


            String header = ApplicationConstant.INSTANCE.Headertoken;
            AllAPIs api = APIClient.getClient().create(AllAPIs.class);
            Call<Lawn> call = api.lawanbooking(header, from,to , guest, function_name, listing_id, user_id, plate_type,offer_price,new Gson().toJson(food_cat_type),catering_type);
             call.enqueue(new Callback<Lawn>()
             {
                 @Override
                 public void onResponse(Call<Lawn> call, Response<Lawn> response)
                 {
                     Log.d("lawnBookings",""+response.body().getStatus());
                     if (response != null)
                     {
                         if (loader != null)
                         {
                             if (loader.isShowing())
                                 loader.dismiss();
                         }

                         if (response.body().getStatus() == 1)
                         {
                             context.startActivity(new Intent(context, PaymentLawnActivity.class).putExtra("data", new Gson().toJson(response.body()).toString()));
                         }
                         else
                         {
                             Toast.makeText(context, "This slot is not available for booking ", Toast.LENGTH_SHORT).show();
                         }

                     }



                 }

                 @Override
                 public void onFailure(Call<Lawn> call, Throwable t)
                 {
                     Log.d("dsjfddmndsbf",t.getMessage());
                 }
             });





    }




    public void filterHotel(Context context,Loader loader,String moduleId, int price,String city,String room_type,String room_size,String start,String next) {
        try {
                 Log.d("dhvnmxcv",moduleId+" "+city);
                    String header = ApplicationConstant.INSTANCE.Headertoken;
                    AllAPIs api = APIClient.getClient().create(AllAPIs.class);
                    Call<DasboadModel> call = api.HotelFilter(header, moduleId,Double.parseDouble(price+"") , city, room_type, room_size, start, next);
                    call.enqueue(new Callback<DasboadModel>()
                    {
                        @Override
                        public void onResponse(Call<DasboadModel> call, Response<DasboadModel> response)
                        {
                            if (response != null) {
                                if (loader != null) {
                                    if (loader.isShowing())
                                        loader.dismiss();
                                }
                                try {
                                    if (response.body().getStatus() == 1)
                                    {

                                        context.startActivity(new Intent(context, SearchingShowData.class)
                                                .putExtra("data", new Gson().toJson(response.body()).toString())
                                                .putExtra("moduleId",moduleId));

                                    }
                                    else {
                                        Toast.makeText(context, "Con not be available rooms", Toast.LENGTH_SHORT).show();

                                    }
                                } catch (Exception e) {
                                  e.printStackTrace();
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<DasboadModel> call, Throwable t)
                        {
                            Log.d("dsjfmndsbf",t.getMessage());

                        }
                    });

                } catch (Exception e)
                {
                    e.printStackTrace();
                    Log.d("dsjfddmndsbf",e.getMessage());
                }



        }

    public void loungFoodList(Context context,String list_id,String data,Loader loader,String moduleId)
    {
        try {


            String header = ApplicationConstant.INSTANCE.Headertoken;
            AllAPIs api = APIClient.getClient().create(AllAPIs.class);
            Call<LawnResponse> call = api.listLawnFoodMenu(header, list_id);
            call.enqueue(new Callback<LawnResponse>() {
                @Override
                public void onResponse(Call<LawnResponse> call, Response<LawnResponse> response)
                {

                      if (response != null) {
                          if (loader != null) {
                              if (loader.isShowing())
                                  loader.dismiss();
                          }
                          try {

                          if (response.body().getStatus() == 1)
                          {
                              Intent i = new Intent(context, LoungeBooking.class);
                              i.putExtra("data", data);
                              i.putExtra("list_id", list_id);
                              i.putExtra("moduleId", moduleId);
                              i.putExtra("responselist", new Gson().toJson(response.body()));
                              context.startActivity(i);
                          } else
                          {
                              Toast.makeText(context, " Not available " + " ", Toast.LENGTH_SHORT).show();
                          }
                          }catch (Exception e)
                          {

                              Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                          }
                      }

                }

                @Override
                public void onFailure(Call<LawnResponse> call, Throwable t)
                {
                    Toast.makeText(context, t.getMessage() + " error", Toast.LENGTH_SHORT).show();

                }
            });
        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }


        public void lawnFoodList(Context context,String list_id,String data,Loader loader,String lawnprice,int status)
       {
        try {
            Log.d("jdfhjf", list_id);
            String header = ApplicationConstant.INSTANCE.Headertoken;
            AllAPIs api = APIClient.getClient().create(AllAPIs.class);
            Call<LawnResponse> call = api.listLawnFoodMenu(header, list_id);
            call.enqueue(new Callback<LawnResponse>() {
                @Override
                public void onResponse(Call<LawnResponse> call, Response<LawnResponse> response)
                {
                    Log.d("rrrr",response.body().getMessage());
                    if (response != null)
                    {
                        if (loader != null)
                        {
                             if (loader.isShowing())
                                loader.dismiss();
                       }


                        if (response.body().getStatus() == 1)
                        {  if(status==1)
                        {
                            Intent i = new Intent(context, LawnBookingActivity.class);
                            i.putExtra("data", data);
                            i.putExtra("list_id", list_id);
                            i.putExtra("lawnprice", lawnprice);
                            i.putExtra("responselist", new Gson().toJson(response.body()));
                            context.startActivity(i);
                          //  Toast.makeText(context, response.body().getMessage() + " " + list_id, Toast.LENGTH_SHORT).show();
                        }
                        else if (status==2)
                        {
                            Intent i = new Intent(context, OutSideLawnBooking.class);
                            i.putExtra("data", data);
                            i.putExtra("list_id", list_id);
                            i.putExtra("lawnprice", lawnprice);
                            i.putExtra("responselist", new Gson().toJson(response.body()));
                            context.startActivity(i);

                        }


                        }
                        else
                        {
                            Log.d("fdjghdfjk",response.body().getMessage());
                            Toast.makeText(context, response.body().getMessage() + "  ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<LawnResponse> call, Throwable t)
                {
                    Toast.makeText(context, t.getMessage() + " error", Toast.LENGTH_SHORT).show();

                }
            });
        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }














    public void getHistrory(Context context,String module_id,String booking_id,String moduleType,String date,String checkOutDate,String alldata,String plate)
    {  Log.d("djkfhdfjfgkjdfg",module_id+"    "+booking_id+"   "+moduleType+"  "+date+"  "+checkOutDate);
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        Call<RoomHistoryModel> call = api.getRoosOrFoodMenu(header,module_id,booking_id);
        call.enqueue(new Callback<RoomHistoryModel>()
        {
            @Override
            public void onResponse(Call<RoomHistoryModel> call, Response<RoomHistoryModel> response)
            {
                Log.d("dsgdfgdfgh",""+response.body().getStatus());


                if (response.body().getStatus()==1)
                {
                    if (moduleType.equalsIgnoreCase("Hotels"))
                    {
                        Log.d("dhfhf",new Gson().toJson(response.body().getBanerData()));
                        context.startActivity(new Intent(context, RoomsHistry.class).putExtra("data", new Gson().toJson(response.body()).toString()).putExtra("date",date).putExtra("checkOutDate",checkOutDate));
                    }
                    else if(moduleType.equalsIgnoreCase("Restaurants"))
                    {
                        Log.d("dhfhf",new Gson().toJson(response.body().getBanerData()));

                        context.startActivity(new Intent(context, FoodMenuHistory.class).putExtra("data", new Gson().toJson(response.body()).toString()).putExtra("date",date));
                    }
                    else if (moduleType.equalsIgnoreCase("Lounges / Banquet Halls"))
                    {

                    context.startActivity(new Intent(context, LoungeHistory.class).putExtra("data", new Gson().toJson(response.body()).toString()).putExtra("date",date).putExtra("alldata",alldata).putExtra("status",0));

                    }
                    else if (moduleType.equalsIgnoreCase("Lawns"))
                    {


                            context.startActivity(new Intent(context, LoungeHistory.class).putExtra("data", new Gson().toJson(response.body()).toString()).putExtra("date", date).putExtra("alldata", alldata));

                    }
                }
            }

            @Override
            public void onFailure(Call<RoomHistoryModel> call, Throwable t)
            {
                Log.d("ddgdfg",""+t.getMessage());
            }
        });
    }
    public void restaurantPayment(Context context,Loader loader,String cartid,String payment_type,String txn_id,String status,String amount,String payment_note,String response )
    {
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        Call<RoomGetByCarId> call = api.restaurantPayment(header,cartid,payment_type,txn_id,amount,status,payment_note,response);
        call.enqueue(new Callback<RoomGetByCarId>() {
            @Override
            public void onResponse(Call<RoomGetByCarId> call, Response<RoomGetByCarId> response)
            {
                if (response != null) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
               try {

                   if (response.body().getStatus() == 1) {
                       context.startActivity(new Intent(context, SuccessFull.class));
                   } else {
                       alertBox("", "Payment Failed", context);
                   }
               }catch (Exception e)
               {
                   e.printStackTrace();
               }
                }
            }

            @Override
            public void onFailure(Call<RoomGetByCarId> call, Throwable t)
            {
                Log.d("jdkhfjh",t.getMessage());
            }
        });
    }






    public void showFoodMenuList(Context context,String listingId,String userId)
    {
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        Call<CartViewModel> call=api.showViewFoodMenuList(header,listingId,userId);
        call.enqueue(new Callback<CartViewModel>()
        {
            @Override
            public void onResponse(Call<CartViewModel> call, Response<CartViewModel> response)
            {
                  if (response.body().getStatus()==1)
                  {
                      FragmentActivityMessage fragmentActivityMessage = new FragmentActivityMessage("ShowFoodMenu",
                              "" + new Gson().toJson(response.body()).toString());
                      GlobalBus.getBus().post(fragmentActivityMessage);
                  }
                  else
                  {
                      alertBox("","please add food menu  in menu list ",context);
                  }


            }
            @Override
            public void onFailure(Call<CartViewModel> call, Throwable t)
            {

            }
        });
    }

  public void timeSlot(Context context)
  {
      String header = ApplicationConstant.INSTANCE.Headertoken;
      AllAPIs api = APIClient.getClient().create(AllAPIs.class);
      Call<TimeSlotModel> call=api.bookTimeSlot(header);
      call.enqueue(new Callback<TimeSlotModel>() {
          @Override
          public void onResponse(Call<TimeSlotModel> call, Response<TimeSlotModel> response)
          {
              if (response.body().getStatus()==1)
              {
                  FragmentActivityMessage fragmentActivityMessage = new FragmentActivityMessage("TimeSlot",
                          "" + new Gson().toJson(response.body()).toString());
                  GlobalBus.getBus().post(fragmentActivityMessage);
              }
              else
              {

              }

          }

          @Override
          public void onFailure(Call<TimeSlotModel> call, Throwable t) {

          }
      });

  }



    public void checkSlotAvailability(Context context,Loader loader,String userId,String date,String guest,String time_slot_id,String listing_id,String total_price)
    {
        Log.d("djf",userId+""+date+" "+guest+" "+time_slot_id+"  "+listing_id);
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        Call<Cartmenu> call=api.checkSlot(header,userId,date,guest,time_slot_id,listing_id,total_price);
        call.enqueue(new Callback<Cartmenu>() {
            @Override
            public void onResponse(Call<Cartmenu> call, Response<Cartmenu> response)
            {
                if (response != null)
                {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {


                        if (response.body().getStatus() == 1)
                        {
                            Log.d("kdjfghkjfgh",new Gson().toJson(response.body()).toString());
                            context.startActivity(new Intent(context, BookMenuActivity.class).putExtra("data", new Gson().toJson(response.body()).toString()).putExtra("total_price", total_price));
                        } else {
                            alertBox("", "This time Slot is not Available please select another time slot", context);
                        }
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<Cartmenu> call, Throwable t)
            {
                Log.d("isufvknv",""+t.getMessage());

            }
        });
    }






    public void deleteMenu(Context context,String userId,String menuId)
    {
        Log.d("fbmcxjjsuerrr", "UserId  " + userId + "  Menu Id " + menuId);
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        Call<Cartmenu> call=api.delete_cart_menu(header,userId,menuId);
        call.enqueue(new Callback<Cartmenu>() {
            @Override
            public void onResponse(Call<Cartmenu> call, Response<Cartmenu> response)
            {
                Log.d("dkfsjhdhfgnddjhh",""+response.body().getStatus());
                if (response.body().getStatus()==1)
                {
                   // alertBox("Menu List","This menu is delete from menu list",context);

                    Toast.makeText(context, "This menu is delete from menu list", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    alertBox("Menu List",response.body().getMessage(),context);

                }
            }

            @Override
            public void onFailure(Call<Cartmenu> call, Throwable t) {

            }
        });
    }

    public void deletefoodMenu(Context context,String userId,String menuId,String restaurantId)
    {
        Log.d("fbmcxjjsuerrr", "UserId  " + userId + "  Menu Id " + menuId);
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        Call<Cartmenu> call=api.delete_cart_menu(header,userId,menuId);
        call.enqueue(new Callback<Cartmenu>() {
            @Override
            public void onResponse(Call<Cartmenu> call, Response<Cartmenu> response)
            {
                Log.d("dkfsjhdhfgnddjhh",""+response.body().getStatus());
                if (response.body().getStatus()==1)
                {
                    // alertBox("Menu List","This menu is delete from menu list",context);
                    UtilsMethod.INSTANCE.showFoodMenuList(context,restaurantId,userId);
                    Toast.makeText(context, "This menu is delete from menu list", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    alertBox("Menu List",response.body().getMessage(),context);

                }
            }

            @Override
            public void onFailure(Call<Cartmenu> call, Throwable t) {

            }
        });
    }

    public void addMenu(Context context,String id,String price,String userId,String quantity,String listingId)
    {    Log.d("fbmcxjjsuerrr","id  "+id+"  price "+price+"  user "+userId+"   quantity  "+quantity+" listingId      "+listingId );
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        Call<Cartmenu> call=api.add_menu(header,id,price,userId,quantity,listingId);
        call.enqueue(new Callback<Cartmenu>() {
            @Override
            public void onResponse(Call<Cartmenu> call, Response<Cartmenu> response)
            {
                Log.d("fbmcdfgfgxjjsuerrr",""+response.body().getStatus());
                if (response.body().getStatus()==1)
                {
                  //  alertBox("Menu List", "This Menu  is add for menu list", context);

                }
                else
                {
                    alertBox("Menu List", response.body().getMessage(), context);
                }
            }

            @Override
            public void onFailure(Call<Cartmenu> call, Throwable t) {
                Log.d("fbmcdfgfgxjjsuerrr",""+t.getMessage());
            }
        });
    }

    public void restaurantList(Context context,String listId,String data,String listingId,Loader loader)
{      Log.d("csdfsdf",""+listId);
    String header = ApplicationConstant.INSTANCE.Headertoken;
    AllAPIs api = APIClient.getClient().create(AllAPIs.class);
    Call<ResturantModel> call=api.restaurantList(header,listId);
    call.enqueue(new Callback<ResturantModel>()
    {
        @Override
        public void onResponse(Call<ResturantModel> call, Response<ResturantModel> response)
        {

            if (response != null)
            {
                if (loader != null)
                {
                    if (loader.isShowing())
                        loader.dismiss();
                }
                try {
                    if (response.body().getStatus() == 1)
                    {
                        context.startActivity(new Intent(context, Restaurantbooking.class).putExtra("data", new Gson().toJson(response.body()).toString()).putExtra("datum", data));
                    } else {
                        alertBox("", "data  not available", context);
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onFailure(Call<ResturantModel> call, Throwable t)
        {
            Log.d("stautsvalue",""+t.getMessage());
        }
    });

}

    public void bookingHistory(Context context,String userId)
    {
        Log.d("sdfkjhdsvbvv",""+userId);
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        Call<BookingHistoryModel> call=api.bookingHistory(header,userId);
        call.enqueue(new Callback<BookingHistoryModel>()
        {
            @Override
            public void onResponse(Call<BookingHistoryModel> call, Response<BookingHistoryModel> response)
            {
           try {
               if (response.body().getStatus() == 1)
               {
                   Log.d("revdgiewjf", "" + new Gson().toJson(response.body()).toString());
                   FragmentActivityMessage fragmentActivityMessage = new FragmentActivityMessage("datahistory",
                           "" + new Gson().toJson(response.body()));
                   GlobalBus.getBus().post(fragmentActivityMessage);
                   UtilsMethod.INSTANCE.BookingHistory(context, "" + new Gson().toJson(response.body()), null);


               }
               else {

               }
           }
           catch (Exception e)
           {
               e.printStackTrace();
           }
            }

            @Override
            public void onFailure(Call<BookingHistoryModel> call, Throwable t)
            {
                Log.d("jdkfbbbhfjh",t.getMessage());
            }
        });


    }


    public void payment(Context context,String cart_id,String payment_type,String txn_id,String status,String amount,String note,String response )
    {
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        Call<RoomGetByCarId> call = api.payment(header,cart_id,payment_type,txn_id,amount,status,note,response);
        call.enqueue(new Callback<RoomGetByCarId>() {
            @Override
            public void onResponse(Call<RoomGetByCarId> call, Response<RoomGetByCarId> response)
            {
                 if(response.body().getStatus()==1)
                 {
                    context.startActivity(new Intent(context,SuccessFull.class));
                 }
                 else
                 {
                     alertBox("","Payment Failed",context);
                 }
            }

            @Override
            public void onFailure(Call<RoomGetByCarId> call, Throwable t)
            {
            }
        });
    }


    public void RoomDeatailsByCartid(Context context,String cartId,String userId,String userName)
    {
        Log.d("dkjvnbjhfg",cartId+" "+userId);
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        Call<RoomGetByCarId> call = api.getroomDeatailsByCartid(header,cartId,userId);
        call.enqueue(new Callback<RoomGetByCarId>()
        {
            @Override
            public void onResponse(Call<RoomGetByCarId> call, Response<RoomGetByCarId> response)
            {
               try {
                   if (response.body().getStatus() == 1)
                   {
                      Log.d("jksgdsfgh",new Gson().toJson(response.body()));
                       Intent i = new Intent(context, BookingReview.class);
                       i.putExtra("data", new Gson().toJson(response.body()).toString());
                       i.putExtra("userName", userName);
                       i.putExtra("cartId", cartId);
                       context.startActivity(i);
                   } else {
                       alertBox("", "data not available", context);
                   }
               }catch (Exception e)
               {
                   e.printStackTrace();
               }
            }

            @Override
            public void onFailure(Call<RoomGetByCarId> call, Throwable t)
            {
            Log.d("uyubdsfuh",t.getMessage());
            }
        });

    }



    public void reserveRoom(Context context,
                            String list_id,String checkindate
                 ,String checkoutdate,String maxadult,String maxchild,
                            String oldoffer_price,String old_price,
                            String user_id,ArrayList<String> roomid,ArrayList<String> selectroom )
    {
          Log.d("sfkgjvnb",list_id+"  "+checkindate+"  "+checkoutdate+"  "+maxadult+"  "+oldoffer_price+"  "+roomid+" "+selectroom+""+maxadult+"  "+maxchild);
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        Call<secureLoginResponse> call = api.reserveRoom(header,list_id,checkindate,checkoutdate,maxadult,maxchild,oldoffer_price,old_price,user_id,roomid,selectroom);
        call.enqueue(new Callback<secureLoginResponse>() {
           @Override
           public void onResponse(Call<secureLoginResponse> call, Response<secureLoginResponse> response)
           {

               if (response.body().getStatus()==1)
               {

                   Log.d("sdjhdfghffhghhg",new Gson().toJson(response.body()).toString());
                     Intent i = new Intent(context, FinalBookingActivity.class);
                     i.putExtra("data",new Gson().toJson(response.body()).toString());
                     i.putExtra("old_price",old_price);
                     i.putExtra("offer_price",oldoffer_price);
                     i.putExtra("roomcount",oldoffer_price);
                     context.startActivity(i);

               }
               else
               {
                   Log.d("hcjgcjxzhgcvj", "" + response.body().getStatus());
               }
           }

           @Override
           public void onFailure(Call<secureLoginResponse> call, Throwable t)
           {
               Log.d("hcjgcjxzhgcvdddj",""+t.getMessage());

           }
       });

    }









     public void roomslist(Context context,String roomId)
     {
         String header = ApplicationConstant.INSTANCE.Headertoken;
         AllAPIs api = APIClient.getClient().create(AllAPIs.class);
         Call<Rooms> call = api.roomNumber(header,"","",roomId);
         call.enqueue(new Callback<Rooms>() {
             @Override
             public void onResponse(Call<Rooms> call, Response<Rooms> response)
             {
                 Log.d("roomlist",""+response.body().getStatus());

             }

             @Override
             public void onFailure(Call<Rooms> call, Throwable t) {

             }
         });

     }

      public void searchRooms(Context context,String hotelId,String checkIn,String checkOut,String adults,String child)
      {
          Log.d("hbvnvbn",adults+"   "+child+"  "+hotelId+"  "+checkOut+"  "+checkIn );
          String header = ApplicationConstant.INSTANCE.Headertoken;
          AllAPIs api = APIClient.getClient().create(AllAPIs.class);
          Call<RoomDataModel> call = api.searchRooms(header, hotelId,checkIn,checkOut,adults,child);
          call.enqueue(new Callback<RoomDataModel>()
          {
              @Override
              public void onResponse(Call<RoomDataModel> call, Response<RoomDataModel> response)
              {

                  try {

                      if (response.body().getStatus() == 1)
                      {
                          Log.d("weuiry73467", new Gson().toJson(response.body()).toString());
                          Intent i = new Intent(context, RoomActivity.class);
                          i.putExtra("data", new Gson().toJson(response.body()).toString());
                          i.putExtra("hotelId", hotelId);
                          i.putExtra("checkIn", checkIn);
                          i.putExtra("checkOut", checkOut);
                          i.putExtra("adults", adults);
                          i.putExtra("child", child);
                          context.startActivity(i);

                      }
                      else
                      {
                          alertBox("", "Con't be available rooms please select other's date", context);
                      }
                  }catch (Exception e)
                  {
                      e.printStackTrace();
                  }

              }

              @Override
              public void onFailure(Call<RoomDataModel> call, Throwable t) {
                  Log.d("rsgsdfgdbdgf3dffew", "" +t.getMessage());
              }
          });

      }

    public void getRoomsList(Context context ,String hotelId)
    {
         Log.d("valuesjdhfuygf", hotelId);
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        Call<RoomDataModel> call = api.getAllRoomList(header, hotelId);
        call.enqueue(new Callback<RoomDataModel>()
        {  @Override
            public void onResponse(Call<RoomDataModel> call, Response<RoomDataModel> response)
            {
             //   Log.d("cbcvbbvcbb",""+response.body().getStatus());
                //  Log.d("reviewjf",""+response.body().getStatus());
              /*  FragmentActivityMessage fragmentActivityMessage = new FragmentActivityMessage("Roomlist",
                        "" + new Gson().toJson(response.body()).toString());
                GlobalBus.getBus().post(fragmentActivityMessage);*/

            }
            @Override
            public void onFailure(Call<RoomDataModel> call, Throwable t)
            {
                Log.d("dsjw",""+t.getMessage());

            }
        });
    }


    public void showReview(Context context, String hotelId) {
        Log.d("valuesjdhfuygf", hotelId);
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        Call<secureLoginResponse> call = api.showReview(header, hotelId);
        call.enqueue(new Callback<secureLoginResponse>()
        {
            @Override
            public void onResponse(Call<secureLoginResponse> call, Response<secureLoginResponse> response)
            {

                 if (response.body().getStatus()==1)
                 {
                     Log.d("reviewjf",""+response.body().getStatus());
                     FragmentActivityMessage fragmentActivityMessage = new FragmentActivityMessage("showReview",
                             "" + new Gson().toJson(response.body()).toString());
                     GlobalBus.getBus().post(fragmentActivityMessage);
                 }
                 else
                {
                    alertBox("","Data not found ",context);
                }
            }

            @Override
            public void onFailure(Call<secureLoginResponse> call, Throwable t) {
                Log.d("ruyure87rfuf", t.getMessage());

            }
        });
    }


    public void logoutfromServer(Context context) {
        try {
            SharedPreferences myPreferences = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
            String secureloginResponse = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, null);
            Data securelogincheckResponse = new Gson().fromJson(secureloginResponse, Data.class);
            String userId = securelogincheckResponse.getId();
            UtilsMethod.INSTANCE.setLoginrespos(context, "", "");
            String header = ApplicationConstant.INSTANCE.Headertoken;
            AllAPIs api = APIClient.getClient().create(AllAPIs.class);
            Call<secureLoginResponse> call = api.userLogOut(header, userId);
            call.enqueue(new Callback<secureLoginResponse>() {
                @Override
                public void onResponse(Call<secureLoginResponse> call, Response<secureLoginResponse> response) {
                    Log.d("mzbchusgfdguyyuyye8", response.body().getMessage().toString());
                    if (response != null)
                    {
                        if (response.body().getStatus() == 1)
                        {
                            Toast.makeText(context, "Logout Successful", Toast.LENGTH_SHORT).show();
                            UtilsMethod.INSTANCE.setLoginrespos(context, "", "");
                            Intent startIntent = new Intent(context, SplashActivity.class);
                            startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(startIntent);
                        } else
                        {
                            sweetAlertBoxFailed(context, response.body().getMessage(), "");
                        }
                    }


                }

                @Override
                public void onFailure(Call<secureLoginResponse> call, Throwable t) {
                    sweetAlertBoxFailed(context, t.getMessage(), "");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void review(Loader loader, WriteReview review, String hotelId, String userId, String cleanliness, String staff_and_services, String amenities, String eco_friendliness, String facilities, String comments) {
        Log.d("uycfgfdbhc", "" + userId + "   jhgczv h   " + cleanliness+""+staff_and_services);
        try{
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        Call<ReviewDataModel> call = api.writeReview(header, hotelId, userId, cleanliness, staff_and_services, amenities, eco_friendliness, facilities, comments);
        call.enqueue(new Callback<ReviewDataModel>() {
            @Override
            public void onResponse(Call<ReviewDataModel> call, Response<ReviewDataModel> response) {
                Log.d("vsd4wreg", "" + response.body().getMessage());
                Toast.makeText(review, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                if (response != null) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        if (response.body().getStatus() == 1)
                        {
                            //sweetAlertBox(review,"Thank You! Your review help us to serve you better","Showdeatails");
                            new SweetAlertDialog(review, SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("")
                                    .setContentText("" + "Thank You! Your review help us to serve you better")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            sweetAlertDialog.dismiss();
                                            review.finish();
                                        }
                                    }).show();
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ReviewDataModel> call, Throwable t) {
                if (loader != null) {
                    if (loader.isShowing())
                        loader.dismiss();
                }

            }
        });
    }catch (Exception e)
    {
        e.printStackTrace();
    }
    }


    public void viewMore(Context context, String moduleId ) {
    try{
        Log.d("moduleSlug", "" + moduleId);
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        Call<DasboadModel> call = api.viewMore(header, moduleId, "0", "100");
        call.enqueue(new Callback<DasboadModel>() {
            @Override
            public void onResponse(Call<DasboadModel> call, Response<DasboadModel> response) {
                Log.d("vjkhjxzvkx", "" + response.body().getStatus());
                FragmentActivityMessage fragmentActivityMessage = new FragmentActivityMessage("searchingmoduls",
                        "" + new Gson().toJson(response.body()).toString());
                GlobalBus.getBus().post(fragmentActivityMessage);

                context.startActivity(new Intent(context, SearchingShowData.class)
                        .putExtra("data", new Gson().toJson(response.body()).toString()).putExtra("moduleId",moduleId));

            }

            @Override
            public void onFailure(Call<DasboadModel> call, Throwable t)
            {

            }
        });
    }catch (Exception e)
    {
        e.printStackTrace();
    }
    }
    public void trendingSearch(Context context, String moduleId,String trending) {
     try{
        Log.d("moduleId", "" + moduleId);
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        Call<DasboadModel> call = api.trending(header, moduleId, "0", "20",trending);
        call.enqueue(new Callback<DasboadModel>() {
            @Override
            public void onResponse(Call<DasboadModel> call, Response<DasboadModel> response)
            {
                if (response.body().getStatus()==1) {

                    if (moduleId.equalsIgnoreCase("1")) {
                        FragmentActivityMessage fragmentActivityMessage = new FragmentActivityMessage("hotelsdata",
                                "" + new Gson().toJson(response.body()).toString());
                        GlobalBus.getBus().post(fragmentActivityMessage);


                    } else if (moduleId.equalsIgnoreCase("2")) {
                        FragmentActivityMessage fragmentActivityMessage = new FragmentActivityMessage("restaurantsdata",
                                "" + new Gson().toJson(response.body()).toString());
                        GlobalBus.getBus().post(fragmentActivityMessage);

                    } else if (moduleId.equalsIgnoreCase("3")) {
                        FragmentActivityMessage fragmentActivityMessage = new FragmentActivityMessage("diningdata",
                                "" + new Gson().toJson(response.body()).toString());
                        GlobalBus.getBus().post(fragmentActivityMessage);

                    }
                }else
                {
                    Toast.makeText(context, "Server error.....", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DasboadModel> call, Throwable t)
            {

            }
        });
    }catch (Exception e)
    {
        e.printStackTrace();
    }
    }


    public void trendingExploring(Context context, String moduleId,String trending) {
        try{
            Log.d("moduleId", "" + moduleId);
            String header = ApplicationConstant.INSTANCE.Headertoken;
            AllAPIs api = APIClient.getClient().create(AllAPIs.class);
            Call<DasboadModel> call = api.getExpliringList(header);
            call.enqueue(new Callback<DasboadModel>()
            {
                @Override
                public void onResponse(Call<DasboadModel> call, Response<DasboadModel> response)
                {
                  try {

                      if (response.body().getStatus() == 1) {
                          FragmentActivityMessage fragmentActivityMessage = new FragmentActivityMessage("trendingExploring",
                                  "" + new Gson().toJson(response.body()).toString());
                          GlobalBus.getBus().post(fragmentActivityMessage);
                      } else {

                      }
                  }catch (Exception e)
                  {
                      e.printStackTrace();
                  }


                }

                @Override
                public void onFailure(Call<DasboadModel> call, Throwable t)
                {

                }
            });
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }




    public void deleteCart(Context context, String userId, String listId)
    {
        try {
            Log.d("jsfdge8rryfg", userId + "     " + listId);
            String header = ApplicationConstant.INSTANCE.Headertoken;
            AllAPIs api = APIClient.getClient().create(AllAPIs.class);
            Call<secureLoginResponse> call = api.deleteCart(header, userId, listId);
            call.enqueue(new Callback<secureLoginResponse>()
            {
                @Override
                public void onResponse(Call<secureLoginResponse> call, Response<secureLoginResponse> response)
                {
                    Log.d("jsfdge8rryfgyyuysdv", "" + response.body().getStatus());
                    try {
                        if (response.body().getStatus() == 1)
                        {
                            //sweetAlertBox(context,"Successfully Deleted","");
                            UtilsMethod.INSTANCE.viewCartDetails(context, userId, null);
                            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            sweetAlertBoxFailed(context, response.body().getMessage(), "");
                        }
                    } catch (Exception e) {
                        sweetAlertBoxFailed(context, "" + e, "");
                    }
                }

                @Override
                public void onFailure(Call<secureLoginResponse> call, Throwable t) {
                    sweetAlertBoxFailed(context, "" + t.getMessage(), "");

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewCartDetails(Context context, String userId, Loader loader) {
        try {
            String header = ApplicationConstant.INSTANCE.Headertoken;
            AllAPIs api = APIClient.getClient().create(AllAPIs.class);
            Call<secureLoginResponse> call = api.viewCart(header, userId);
            call.enqueue(new Callback<secureLoginResponse>() {
                @Override
                public void onResponse(Call<secureLoginResponse> call, Response<secureLoginResponse> response) {
                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        try {
                            if (response.body().getStatus() == 1)
                            {
                                Log.d("sdfhjsddezcxcvdvbh", userId + "  dbvjdbv  " + response.body().getMessage());
                                FragmentActivityMessage fragmentActivityMessage = new FragmentActivityMessage("viewcart",
                                        "" + new Gson().toJson(response.body()).toString());
                                GlobalBus.getBus().post(fragmentActivityMessage);
                            } else {
                                sweetAlertBoxFailed(context, response.body().getMessage(), "");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<secureLoginResponse> call, Throwable t) {
                    Log.d("sdfhjsddedvbh", userId + "  dbvjdbv  " + t.getMessage());
                }
            });
        } catch (Exception e) {
            alertBox("", "" + e, context);
        }
    }

    public void addToCart(Context context, String userId, String listId, String name) {
  try{
        Log.d("sdfhjsdvbh", userId + "  dbvjdbv  " + listId);
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        Call<secureLoginResponse> call = api.addTocart(header, userId, listId);
        call.enqueue(new Callback<secureLoginResponse>() {
            @Override
            public void onResponse(Call<secureLoginResponse> call, Response<secureLoginResponse> response) {
                try {
                    if (response.body().getStatus() == 1)
                    {
                        Log.d("dsf2gdt",new Gson().toJson(response.body().getData()));

                        sweetAlertBox(context, " " + name + " is added your cart ", "onlyshow");
                    } else {
                        sweetAlertBoxFailed(context, " " + name + " is already added in your cart", "onlyshow");
                    }
                } catch (Exception e) {
                    Log.e("error: ", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<secureLoginResponse> call, Throwable t)
            {

                Log.d("dfdhnnb", "" + t);
                alertBox(" ", t.getMessage(), context);

            }
        });
    }catch (Exception e)
    {
        e.printStackTrace();
    }
    }

    public void searching(Context context, String keyword)
    {
        try {
            String header = ApplicationConstant.INSTANCE.Headertoken;
            AllAPIs api = APIClient.getClient().create(AllAPIs.class);
            Log.d("ssvhccddff", "" + keyword);
            Call<DasboadModel> call = api.searchingAllmoduls(header, keyword);
            call.enqueue(new Callback<DasboadModel>() {
                @Override
                public void onResponse(Call<DasboadModel> call, Response<DasboadModel> response) {
                    Log.d("dser",response.body().getMessage());

                    try {
                        if (response.body().getStatus() == 1)
                        {

                            FragmentActivityMessage fragmentActivityMessage = new FragmentActivityMessage("searchingmoduls",
                                    "" + new Gson().toJson(response.body()).toString());
                            GlobalBus.getBus().post(fragmentActivityMessage);
                            context.startActivity(new Intent(context, SearchingShowData.class).putExtra("data", new Gson().toJson(response.body()).toString()).putExtra("moduleId",""));

                        } else {
                            sweetAlertBoxFailed(context, response.body().getMessage(), "");

                        }
                    } catch (Exception e) {
                        sweetAlertBoxFailed(context, "" + e, "");
                    }
                }

                @Override
                public void onFailure(Call<DasboadModel> call, Throwable t) {
                    Log.d("xds", t + "");
                    alertBox(" ", t.getMessage(), context);
                }
            });
        } catch (Exception e) {
            alertBox(" ", "" + e, context);
        }



    }

    public void userServices(Context context) {

        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        Log.d("ssvhdffhgh", "" + header);
        Call<DasboadModel> call = api.userServices(header);
        call.enqueue(new Callback<DasboadModel>()
        {
            @Override
            public void onResponse(Call<DasboadModel> call, Response<DasboadModel> response)
            {
                try
                {
                Log.d("weoriehhf", response.body().getStatus() + "");
                FragmentActivityMessage fragmentActivityMessage = new FragmentActivityMessage("userServices",
                        "" + new Gson().toJson(response.body()).toString());
                GlobalBus.getBus().post(fragmentActivityMessage);
                UtilsMethod.INSTANCE.services(context, "" +  new Gson().toJson(response.body()).toString());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.d("sjdfhfgrytt",e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<DasboadModel> call, Throwable t)
            {


            }
        });


    }

    public void listingAllModule(Context context) {
        try{
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        Log.d("ssvhdff", "" + header);
        Call<DasboadModel> call = api.listingallModule(header, "0", "4");
        call.enqueue(new Callback<DasboadModel>() {
            @Override
            public void onResponse(Call<DasboadModel> call, Response<DasboadModel> response)
            {
                try {
                    if (response.body().getStatus() == 1) {
                        if (response.body().getStatus() == 1) {
                            FragmentActivityMessage fragmentActivityMessage = new FragmentActivityMessage("allmodulelisted",
                                    "" + new Gson().toJson(response.body()).toString());
                            GlobalBus.getBus().post(fragmentActivityMessage);
                            UtilsMethod.INSTANCE.storelist(context, "" + new Gson().toJson(response.body()).toString());
                        } else {
                            Toast.makeText(context, "Connection problrem", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(context, "Internal server error ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<DasboadModel> call, Throwable t) {
                Log.d("wqesdfr34", "" + t);

            }
        });
    }catch (Exception e)
    {
        e.printStackTrace();
    }


    }

    public void bannerSliderImage(Context context)
    {
        try{
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        Log.d("sdff", "hdfsddfeader" + header);
        Call<DasboadModel> call = api.bannerSlider(header, "0", "2");
        call.enqueue(new Callback<DasboadModel>() {
            @Override
            public void onResponse(Call<DasboadModel> call, Response<DasboadModel> response)
            {
                try {
                    if (response.body().getStatus() == 1) {
                        FragmentActivityMessage fragmentActivityMessage = new FragmentActivityMessage("bannerList", "" + new Gson().toJson(response.body()).toString());
                        GlobalBus.getBus().post(fragmentActivityMessage);
                        UtilsMethod.INSTANCE.bannerlist(context, "" + new Gson().toJson(response.body()).toString());

                    } else {
                        Toast.makeText(context, "please try again ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<DasboadModel> call, Throwable t) {
                Log.d("wqer34", "" + t);

            }
        });
        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }


    public void createAccount(Context context, Loader loader, String fname, String lname, String email, String password, String cpassword,String contact) {
      try{
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);


        Call<secureLoginResponse> call = api.createAccount(header, fname, lname, email,contact, password, cpassword);
        call.enqueue(new Callback<secureLoginResponse>() {
            @Override
            public void onResponse(Call<secureLoginResponse> call, Response<secureLoginResponse> response) {
                if (response != null)
                {
                    if (loader != null)
                    {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        if (response.body().getStatus() == 1)
                        {
                            Toast.makeText(context, "Login Successfully! ", Toast.LENGTH_SHORT).show();
                            UtilsMethod.INSTANCE.setLoginrespose(context, "" + new Gson().toJson(response.body().getData()), "1");
                          // context.startActivity(new Intent(context,DashBoad.class));
                            Intent i = new Intent(context, GetLocation.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(i);
                           //sweetAlertBox(context, response.body().getMessage(), "Login");

                        } else
                        {
                            alertBox(" ", "This E-mail Id already login Please sign In", context);
                            Intent i = new Intent(context, Login.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(i);
                        }
                    } catch (Exception e) {
                        Log.e("error: ", e.getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call<secureLoginResponse> call, Throwable t) {
                Log.e("onFailure: ", t.getMessage());
                sweetAlertBoxFailed(context, t.getMessage(), "");
                if (loader != null) {
                    if (loader.isShowing())
                        loader.dismiss();
                }

            }
        });
    }catch (Exception e)
    {
        e.printStackTrace();
    }

    }


    public void signIn(Context context, Loader loader, String email, String password,int flag)
    {
        try
        {
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        Call<secureLoginResponse> call = api.signIn(header, email, password, null, null,flag);
        call.enqueue(new Callback<secureLoginResponse>() {
            @Override
            public void onResponse(Call<secureLoginResponse> call, Response<secureLoginResponse> response) {
                Log.d("status", String.valueOf(response.body().getStatus()));
                if (response != null)
                {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        if (response.body().getStatus() == 1)
                        {
                            UtilsMethod.INSTANCE.setLoginrespose(context, "" + new Gson().toJson(response.body().getData()), "1");
                            //sweetAlertBox(context, response.body().getMessage(), "GetLocation");
                            Toast.makeText(context, "Login Successfully !", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(context, GetLocation.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(i);


                        } else {
                            sweetAlertBoxFailed(context, "Please Enter valid e-mail or password", flag+"");
                        }
                    } catch (Exception e) {
                        Log.e("error: ", e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<secureLoginResponse> call, Throwable t) {
                Log.e("onFailure: ", t.getMessage());
                sweetAlertBoxFailed(context, t.getMessage(), "");
                if (loader != null) {
                    if (loader.isShowing())
                        loader.dismiss();
                }
            }
        });
      }catch (Exception e)
      {
        e.printStackTrace();
      }

    }

    public void UpdateProfile(Context context, Loader loader, String id, String fname, String lname, String phone_no, String city, String state, String address_1, String address_2, String zipcode, String latitude, String longitude, String firebase_token, String image) {
  try{
        File file = new File(image);
        Log.e("profileimage", "" + file);
        MultipartBody.Part fileToUpload1;
        if (image.equalsIgnoreCase("1")) {
            fileToUpload1 = MultipartBody.Part.createFormData("image", "");

        }
        else
        {
            RequestBody requestBody1 = RequestBody.create(MediaType.parse("*image/*"), file);
            fileToUpload1 = MultipartBody.Part.createFormData("image", file.getName(), requestBody1);
        }

        RequestBody user_id = RequestBody.create(MediaType.parse("text/plain"), id);
        RequestBody first_name = RequestBody.create(MediaType.parse("text/plain"), fname);
        RequestBody last_name = RequestBody.create(MediaType.parse("text/plain"), lname);
        RequestBody contact = RequestBody.create(MediaType.parse("text/plain"), phone_no);
        RequestBody city_name = RequestBody.create(MediaType.parse("text/plain"), city);
        RequestBody state_name = RequestBody.create(MediaType.parse("text/plain"), state);
        RequestBody address1_name = RequestBody.create(MediaType.parse("text/plain"), address_1);
        RequestBody address2_name = RequestBody.create(MediaType.parse("text/plain"), address_2);
        RequestBody z_zipcode = RequestBody.create(MediaType.parse("text/plain"), zipcode);
        RequestBody la_latitude = RequestBody.create(MediaType.parse("text/plain"), latitude);
        RequestBody lo_longitude = RequestBody.create(MediaType.parse("text/plain"), longitude);
        RequestBody token_firebase = RequestBody.create(MediaType.parse("text/plain"), firebase_token);
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        //Call<DataRespone> call=api.upDateProfile(header,id+"",fname+"",lname+"",phone_no+"",city+"",state+"",address_1+"",address_2+"",zipcode+"", latitude+"",longitude+"",firebase_token+"",image+"");
        Call<DataRespone> call = api.upDateProfile(header, user_id, first_name, last_name, contact, city_name, state_name, address1_name, address2_name, z_zipcode, la_latitude, lo_longitude, token_firebase, fileToUpload1);
        Log.e("UserProfileuploadres", "is : " + call.toString());
        call.enqueue(new Callback<DataRespone>() {
            @Override
            public void onResponse(Call<DataRespone> call, Response<DataRespone> response)
            {
                Toast.makeText(context, "" + response.body().getData1().getFirstName(), Toast.LENGTH_SHORT).show();
                Log.d("sdfjhhghsdf", new Gson().toJson(response.body()));

                if (response != null)
                {
                    if (loader != null)
                    {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        if (response.body().getStatus() == 1)
                        {
                            sweetAlertBox(context, response.body().getMessage(), "DashBoad");
                            UtilsMethod.INSTANCE.setLoginrespose(context, "" + new Gson().toJson(response.body().getData1()), "1");

                        }
                        else
                        {
                            sweetAlertBoxFailed(context, response.body().getMessage(), "");
                        }

                    } catch (Exception e) {
                        Log.d("Exception", "" + e);
                    }
                }
            }

            @Override
            public void onFailure(Call<DataRespone> call, Throwable t)
            {
                Log.e("onFailure: ", t.getMessage());
                sweetAlertBoxFailed(context, "Please select image", "");
                if (loader != null) {
                    if (loader.isShowing())
                        loader.dismiss();
                }
            }
        });
    }catch (Exception e)
    {
        e.printStackTrace();
    }

    }

    public void forgetpasswor(Context context, Loader loader, String email) {
        try{
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        Call<secureLoginResponse> call = api.forgetpasswor(header, email);
        call.enqueue(new Callback<secureLoginResponse>() {
            @Override
            public void onResponse(Call<secureLoginResponse> call, Response<secureLoginResponse> response) {
                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                if (response != null) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        if (response.body().getStatus() == 1)
                        {
                            sweetAlertBox(context, response.body().getMessage(), "OtpVerification");
                        } else {
                            sweetAlertBoxFailed(context, "This E-mail is Not a registered", "");
                        }
                    } catch (Exception e)
                    {
                        Log.d("error", "" + e);
                    }
                }
            }

            @Override
            public void onFailure(Call<secureLoginResponse> call, Throwable t)
            {
                if (loader != null) {
                    if (loader.isShowing())
                        loader.dismiss();
                }

            }
        });
    }catch (Exception e)
    {
        e.printStackTrace();
    }

    }

    public void changePassword(Context context, Loader loader, String userId, String currentpassword, String newpassword, String cnewpassword) {
        try {
            String header = ApplicationConstant.INSTANCE.Headertoken;
            AllAPIs api = APIClient.getClient().create(AllAPIs.class);
            Call<secureLoginResponse> call = api.changePassword(header, userId, currentpassword, newpassword, cnewpassword);
            call.enqueue(new Callback<secureLoginResponse>() {
                @Override
                public void onResponse(Call<secureLoginResponse> call, Response<secureLoginResponse> response) {

                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        try {
                            if (response.body().getStatus() == 1) {
                                sweetAlertBox(context, response.body().getMessage(), "DashBoad");
                            } else {
                                sweetAlertBoxFailed(context, response.body().getMessage(), "");
                            }
                        } catch (Exception e) {
                            Log.d("error", "" + e);
                        }
                    }
                }

                @Override
                public void onFailure(Call<secureLoginResponse> call, Throwable t) {
                    Log.e("onFailure: ", t.getMessage());
                    sweetAlertBoxFailed(context, t.getMessage(), "");
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }


                }
            });
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void otpVerify(Context context, Loader loader, String email, String otp, String newpassword, String cnewpassword) {
        String header = ApplicationConstant.INSTANCE.Headertoken;
        AllAPIs api = APIClient.getClient().create(AllAPIs.class);
        try {
            Call<secureLoginResponse> call = api.otpVerify(header, email, otp, newpassword, cnewpassword);
            call.enqueue(new Callback<secureLoginResponse>() {
                @Override
                public void onResponse(Call<secureLoginResponse> call, Response<secureLoginResponse> response) {
                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        try {
                            if (response.body().getStatus() == 1) {
                                sweetAlertBox(context, response.body().getMessage(), "SignInEmail");
                            } else {
                                sweetAlertBoxFailed(context, response.body().getMessage(), "");
                            }
                        } catch (Exception e) {
                            Log.d("error", "" + e);
                        }
                    }
                }

                @Override
                public void onFailure(Call<secureLoginResponse> call, Throwable t) {
                    Log.e("onFailure: ", t.getMessage());
                    sweetAlertBoxFailed(context, t.getMessage(), "");
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                }
            });
        }catch (Exception e)
        {
          e.printStackTrace();
        }

    }


    public void alertBox(String title, String Message, Context context) {
        try {
            final AlertDialog dialog1 = new AlertDialog.Builder(context).create();
            dialog1.setTitle(title);
            dialog1.setMessage(Message);
            dialog1.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog1.dismiss();
                    //System.exit(1);
                }
            });
            dialog1.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sweetAlertBox(Context context, String message, String openspage)
    {
        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("")
                .setContentText("" + "" + message)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        if (openspage.equalsIgnoreCase("DashBoad")) {
                            Intent i = new Intent(context, DashBoad.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(i);
                        } else if (openspage.equalsIgnoreCase("Login"))
                        {
                            Intent i = new Intent(context, Login.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(i);

                        } else if (openspage.equalsIgnoreCase("OtpVerification")) {
                            Intent i = new Intent(context, OtpVerification.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(i);

                        } else if (openspage.equalsIgnoreCase("GetLocation")) {
                            Intent i = new Intent(context, GetLocation.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(i);

                        } else if (openspage.equalsIgnoreCase("SignInEmail")) {
                            Intent i = new Intent(context, SignInEmail.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(i);

                        } else if (openspage.equalsIgnoreCase("Showdeatails")) {
                          /*  Intent i = new Intent(context, DashBoad.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(i);*/
                            ((WriteReview) context.getApplicationContext()).finish();
                        }
                        else if (openspage.equalsIgnoreCase("successful")) {
                            Intent i = new Intent(context, SuccessFull.class);

                            context.startActivity(i);

                        }


                    }
                }).show();
    }

    public void sweetAlertBoxFailed(Context context, String message, String openspage) {
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("")
                .setContentText("" + "" + message)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        if(openspage.equals("1")){
                            Log.e("openspage",openspage);
                            context.startActivity(new Intent(context, SignUp.class).putExtra("type","2"));
                        }
                        sweetAlertDialog.dismiss();




                    }
                }).show();
    }


    public boolean isValidEmail(String email) {

        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public boolean isNetworkAvialable(Context context) {
        ConnectivityManager cm =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public boolean isValidPassword(String password) {

        Pattern pattern;
        Matcher matcher;

        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }

    public String dateForamterer(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateandTime = sdf.format(date);
        return currentDateandTime;
    }

    public void bookingpop(View view, Activity activity,String hotel_id,String data,Loader loader,String lawnprice)
    {
        float radius = 10f;
         BlurView topBlurView;
        View decorView =activity.getWindow().getDecorView();
        ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);
        Drawable windowBackground = decorView.getBackground();

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.catrings, null);




        topBlurView = popupView.findViewById(R.id.blurView);
        topBlurView.setupWith(rootView, new RenderScriptBlur(activity))
                .setFrameClearDrawable(windowBackground) // Optional
                .setBlurRadius(radius);

        Button search = popupView.findViewById(R.id.search);
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        popupWindow.setOutsideTouchable(false);
        final RadioGroup radioGroup;
        radioGroup = popupView.findViewById(R.id.groupradio);
        search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1)
                {
                    RadioButton selectedRadioButton;
                    selectedRadioButton = popupView.findViewById(selectedRadioButtonId);
                    String paymentType = selectedRadioButton.getText().toString();
                    if (paymentType.equals("Inside catering "))
                    {  loader.show();
                        loader.setCancelable(false);
                        loader.setCanceledOnTouchOutside(false);
                        UtilsMethod.INSTANCE.lawnFoodList(activity,hotel_id,data,loader,lawnprice,1);
                        popupWindow.dismiss();
                    }
                    else if (paymentType.equals("Outside catering"))
                    {
                      /*  loader.show();
                        loader.setCancelable(false);
                        loader.setCanceledOnTouchOutside(false);
                        UtilsMethod.INSTANCE.lawnFoodList(activity,hotel_id,data,loader,lawnprice,2);
                */
                        Intent i = new Intent(activity, OutSideLawnBooking.class);
                        i.putExtra("data", data);
                        i.putExtra("list_id", hotel_id);
                        i.putExtra("lawnprice", lawnprice);
                    //    i.putExtra("responselist", new Gson().toJson(response.body()));
                        activity.startActivity(i);
                        popupWindow.dismiss();
                    }




                }


            }
        });
    }


    @SuppressLint("MissingInflatedId")
    public void canceledkpop(View view, Activity activity, String bookingId, String userId,TextView cancelledMessage, TextView cancelled)
    {

        Loader loader;


        float radius = 10f;
        BlurView topBlurView;
        View decorView =activity.getWindow().getDecorView();
        ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);
        Drawable windowBackground = decorView.getBackground();

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.bookingcancle, null);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button okButton = popupView.findViewById(R.id.okButton);
       Button Cancel = popupView.findViewById(R.id.Cancel);
       TextView msg = popupView.findViewById(R.id.msg);

        loader = new Loader(activity, android.R.style.Theme_Translucent_NoTitleBar);
        topBlurView = popupView.findViewById(R.id.blurView);
        topBlurView.setupWith(rootView, new RenderScriptBlur(activity))
                .setFrameClearDrawable(windowBackground)
                .setBlurRadius(radius);
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupWindow.setOutsideTouchable(false);
        msg.setText("If you want to cancelled booking ! ");

        Cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        okButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loader.show();
                loader.setCancelable(false);
                loader.setCanceledOnTouchOutside(false);
                UtilsMethod.INSTANCE.cancellledBooking(activity,loader,bookingId,userId,cancelledMessage,cancelled);
                popupWindow.dismiss();
            }
        });
    }


}
