package com.app.partyplanets.Adpters;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.partyplanets.DashBoad.ShowDetails;
import com.app.partyplanets.Data.CartData;
import com.app.partyplanets.Data.Data;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.app.partyplanets.Utils.UtilsMethod;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;

public class CartViewAdapter extends  RecyclerView.Adapter<CartViewAdapter.ViewHolder>
{
    private Context context;
    private ArrayList<CartData> msubslider = new ArrayList<>();


    public CartViewAdapter(Context context, ArrayList<CartData> msubslider) {
        this.context = context;
        this.msubslider = msubslider;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.cartview_layout, parent, false);
        return  new CartViewAdapter.ViewHolder(listItem);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position)
    {

        SharedPreferences myPreferences = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String secureloginResponse = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, null);
        Data securelogincheckResponse = new Gson().fromJson(secureloginResponse, Data.class);
        String  userId = securelogincheckResponse.getId();

        CartData sliderItem = msubslider.get(position);
        Glide.with(viewHolder.itemView)
                .load(ApplicationConstant.INSTANCE.baseUrl + sliderItem.getBanner_img())
                .fitCenter()
                .into(viewHolder.image);
        viewHolder.name.setText(sliderItem.getName());
        viewHolder.offer_price.setText("₹ "+sliderItem.getOffer_price());
        viewHolder.current_price.setText("₹ "+sliderItem.getCurrent_price());
        viewHolder.tv_address.setText(sliderItem.getCity()+" "+sliderItem.getState());
        Log.d("jdfhbvjfggryt",""+sliderItem.getList_id());

        viewHolder.booking.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
              /*  if (sliderItem.getModule_slug().equals("hotels"))
                {

                    context.startActivity(new Intent(context, BookingHotel.class).putExtra("data", new Gson().toJson(sliderItem)));
                    UtilsMethod.INSTANCE.getRoomsList(context, sliderItem.getId());
                }*/
                context.startActivity(new Intent(context, ShowDetails.class).putExtra("data", new Gson().toJson(sliderItem)).putExtra("status","1"));


            }
        });
        viewHolder.delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                UtilsMethod.INSTANCE.deleteCart(context,userId,sliderItem.getList_id());

              //  Log.d("sdhgdsfvxzcgy",sliderItem.getId());
            }
        });
        viewHolder.viewdetails.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                context.startActivity(new Intent(context, ShowDetails.class).putExtra("data", new Gson().toJson(sliderItem)).putExtra("status","1"));
                Toast.makeText(context, "coming soon Need to Payment gateway ", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount()
    {
        return msubslider.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {  LinearLayout layout_view;
         ImageView image,delete;
         TextView name,current_price,offer_price,tv_address,viewdetails;
         Button booking;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            this.layout_view=itemView.findViewById(R.id.layout_view);
            this.image=itemView.findViewById(R.id.image);
            this.name=itemView.findViewById(R.id.name);
            this.current_price=itemView.findViewById(R.id.current_price);
            this.offer_price=itemView.findViewById(R.id.offer_price);
            this.tv_address=itemView.findViewById(R.id.tv_address);
            this.viewdetails=itemView.findViewById(R.id.viewdetails);
            this.delete=itemView.findViewById(R.id.delete);
            this.booking=itemView.findViewById(R.id.booking);
        }
    }
}
