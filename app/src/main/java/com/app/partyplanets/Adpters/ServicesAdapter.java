package com.app.partyplanets.Adpters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.partyplanets.DashBoad.EventActivity;
import com.app.partyplanets.Data.Dataum;
import com.app.partyplanets.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ServicesAdapter extends  RecyclerView.Adapter<ServicesAdapter.ViewHolder>
{
    private Context context;
    private List<Dataum> msubslider = new ArrayList<>();

    public ServicesAdapter(Context context, List<Dataum> msubslider)
    {
        this.context = context;
        this.msubslider = msubslider;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.services, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        Dataum sliderItem = msubslider.get(position);

        viewHolder.cityname.setText(sliderItem.getName());
        viewHolder.card_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            context.startActivity(new Intent(context, EventActivity.class).putExtra("event_type",sliderItem.getName()));
            }
        });


        if (sliderItem.getId().equalsIgnoreCase("3"))
        {
         Glide.with(viewHolder.itemView)
                    .load(R.drawable.birthdayparty)
                    .fitCenter()
                    .into(viewHolder.imageView);
        }
        if (sliderItem.getId().equalsIgnoreCase("2"))
        {
            Glide.with(viewHolder.itemView)
                    .load(R.drawable.travel_image)
                    .fitCenter()
                    .into(viewHolder.imageView);
        }
        if (sliderItem.getId().equalsIgnoreCase("1"))
        {
            Glide.with(viewHolder.itemView)
                    .load(R.drawable.ho)
                    .fitCenter()
                    .into(viewHolder.imageView);
        }


    }

    @Override
    public int getItemCount() {
        return msubslider.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imageView;
        public RelativeLayout layout;
        public TextView cityname;
        public CardView card_services;
        public ViewHolder(@NonNull View v)
        {
            super(v);
            this.imageView=v.findViewById(R.id.image);
            this.layout=v.findViewById(R.id.layout);
            this.cityname=v.findViewById(R.id.cityname);
            this.card_services=v.findViewById(R.id.card_services);
        }
    }
}
