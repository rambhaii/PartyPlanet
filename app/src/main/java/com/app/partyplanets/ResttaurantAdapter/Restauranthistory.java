package com.app.partyplanets.ResttaurantAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.partyplanets.Data.RoomHistory;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Restauranthistory  extends   RecyclerView.Adapter<Restauranthistory.ViewHolder>
{  Context context;
    String dated;
    ArrayList<RoomHistory> roomHistories;
    public Restauranthistory(Context  context, ArrayList<RoomHistory> selectedRoomModels ,String date)
    {
        this.context=context;
        this.dated=date;
        this.roomHistories=selectedRoomModels;
    }

    @NonNull
    @Override
    public Restauranthistory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.foodmenu_history, parent, false);
        return  new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull Restauranthistory.ViewHolder holder, int position)
    {
        RoomHistory sliderItem = roomHistories.get(position);
        if (Integer.parseInt(sliderItem.getType())==1)
        {
            holder.plate.setText("Full Plate ");
        }
        else if (Integer.parseInt(sliderItem.getType())==2)
        {
            holder. plate.setText("Half Plate ");
        }
        holder.name.setText(sliderItem.getTitle());
        holder.price.setText("₹ "+sliderItem.getTotal_amt());
        holder.quantity.setText(sliderItem.getQty());
        holder.subtitle.setText(sliderItem.getSub_title());

        String checkoutDate=sliderItem.getCreated_at();
        SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date time1= null;
        try {
            time1 = fmt.parse(dated);
            //   time2=newFormat.parse(checkoutDate);
            String sd = newFormat.format(time1);
            // String ed = newFormat.format(time2);
            holder.date.setText("Booking Date: "+sd+"");

        } catch (ParseException e) {
            e.printStackTrace();
        }// date.setText(d.getd);
        Glide.with(holder.itemView)
                .load(ApplicationConstant.INSTANCE.baseUrl + sliderItem.getImage())
                .placeholder(R.drawable.imm)
                .fitCenter()
                .into(holder.image);

    }

    @Override
    public int getItemCount()
    {
        return roomHistories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {  TextView name,price,plate,quantity,date,subtitle;
        ImageView image;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.title);
             price = itemView.findViewById(R.id.price);
             plate=itemView.findViewById(R.id.plate);
             quantity = itemView.findViewById(R.id.quantity);
             date = itemView.findViewById(R.id.date);
             subtitle = itemView.findViewById(R.id.subtitle);
             image = itemView.findViewById(R.id.image);

        }
    }
}
