package com.app.partyplanets.Adpters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.partyplanets.Data.Amenities;
import com.app.partyplanets.Data.ReviewList;
import com.app.partyplanets.Data.RoomHistory;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Amenties extends  RecyclerView.Adapter<Amenties.ViewHolder>
{

    private Context context;
    private ArrayList<Amenities> msubslider = new ArrayList<>();

    public Amenties(Context context, ArrayList<Amenities> msubslider) {
        this.context = context;
        this.msubslider = msubslider;
    }

    @NonNull
    @Override
    public Amenties.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.amenties, parent, false);
        return  new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull Amenties.ViewHolder holder, int position)
    {
        Amenities sliderItem = msubslider.get(position);
     //   Log.d("djsfcbvjncvc",ApplicationConstant.INSTANCE.baseUrl+sliderItem.getIcon());
        holder.name.setText(sliderItem.getName());
        Glide.with(holder.itemView)
                .load(ApplicationConstant.INSTANCE.baseUrl + sliderItem.getIcon())
                .fitCenter()
                .error(R.drawable.ic_logo)
                .into(holder.image);


    }

    @Override
    public int getItemCount() {
        return msubslider.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView name;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            image=itemView.findViewById(R.id.logo);
        }
    }
}
