package com.app.partyplanets.Adpters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.partyplanets.DashBoad.BookingConditions;
import com.app.partyplanets.Data.RoomPreViewModel;
import com.app.partyplanets.R;

import java.util.ArrayList;
import java.util.List;

public class RoomsPreviewAdapter extends  RecyclerView.Adapter<RoomsPreviewAdapter.ViewHolder>
{

    private Context context;
    private List<RoomPreViewModel> msubslider = new ArrayList<>();
    String userName,Noadults,Nochild;

    public RoomsPreviewAdapter(Context context, List<RoomPreViewModel> msubslider,String userName,String Noadults ,String Nochild)
    {
        this.context = context;
        this.msubslider = msubslider;
        this.userName=userName;
        this.Noadults=Noadults;
        this.Nochild=Nochild;
    }

    @NonNull
    @Override
    public RoomsPreviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.roompreview, parent, false);
        return  new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomsPreviewAdapter.ViewHolder holder, int position)
    {
        RoomPreViewModel sliderItem = msubslider.get(position);
        holder.roomName.setText("Total "+sliderItem.getRoom_type()+" :");

        holder.adult.setText(Noadults+" adult");
        if (Nochild.equalsIgnoreCase("0"))
        {
            holder.child.setVisibility(View.GONE);
        }
        else
        {
            holder.child.setText(Nochild+" child");
        }

        holder.bed.setText(sliderItem.getBed_type());
        holder.booking_userName.setText(userName);
        holder.count.setText(sliderItem.getBookingQty());
        holder.information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


            }
        });

        holder.booking_conditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, BookingConditions.class));
            }
        });

    }

    @Override
    public int getItemCount()
    {

        return msubslider.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView roomName,adult,child,bed,booking_userName,booking_conditions,count;
        ImageView information;
        public ViewHolder(@NonNull View itemView)
         {super(itemView);
        this.roomName=itemView.findViewById(R.id.roomName);
        this.adult=itemView.findViewById(R.id.adult);
        this.child=itemView.findViewById(R.id.child);
        this.bed=itemView.findViewById(R.id.bed);
        this.booking_userName=itemView.findViewById(R.id.booking_userName);
        this.booking_conditions=itemView.findViewById(R.id.booking_conditions);
        this.information=itemView.findViewById(R.id.information);
        this.count=itemView.findViewById(R.id.count);

        }
    }
}
