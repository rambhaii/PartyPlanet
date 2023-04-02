package com.app.partyplanets.ResttaurantAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.partyplanets.R;
import com.app.partyplanets.RestaurantData.TimeSlotData;
import com.app.partyplanets.Restaurants.RestauMenuBooking;

import java.util.ArrayList;
import java.util.List;

public class TimeSlotAdapter extends RecyclerView.Adapter<TimeSlotAdapter.ViewHolder>
{
    private Context context;
    private List<TimeSlotData> msubslider = new ArrayList<>();
    private int index=-1;

    public TimeSlotAdapter(Context context, List<TimeSlotData> msubslider)
    {
        this.context = context;
        this.msubslider = msubslider;
    }

    @NonNull
    @Override
    public TimeSlotAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.tim_eslot_menu, parent, false);
        return  new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeSlotAdapter.ViewHolder holder, int position)
    {
        TimeSlotData slider=msubslider.get(position);

        holder.times.setText(slider.getTime_slot());

        holder.cardId.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                index=position;
                int id = Integer.parseInt(slider.getId());
                ((RestauMenuBooking) context).getDataAdapter(id);
                notifyDataSetChanged();

            }
        });
      if (index==position)
      {
          holder.cardId.setCardBackgroundColor(Color.BLACK);
          holder.times.setTextColor(Color.WHITE);
      }
     else {
            holder.cardId.setCardBackgroundColor(Color.WHITE);
          holder.times.setTextColor(Color.GRAY);
        }


    }

    @Override
    public int getItemCount()
    {
        return msubslider.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
          TextView times;
         CardView cardId;



        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
           this.times = itemView.findViewById(R.id.timeslot);
           this.cardId = itemView.findViewById(R.id.cardId);


        }
    }
}
