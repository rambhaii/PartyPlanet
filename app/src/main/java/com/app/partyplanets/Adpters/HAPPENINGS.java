package com.app.partyplanets.Adpters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.partyplanets.Model.HappeningsModel;
import com.app.partyplanets.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class HAPPENINGS extends  RecyclerView.Adapter<HAPPENINGS.ViewHolder>
{
    private Context context;
    private List<HappeningsModel> msubslider = new ArrayList<>();

    public HAPPENINGS(Context context, List<HappeningsModel> msubslider) {
        this.context = context;
        this.msubslider = msubslider;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.happenings, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        HappeningsModel sliderItem = msubslider.get(position);

        viewHolder.cityname.setText(sliderItem.getDescription());
        viewHolder.location.setText(sliderItem.getTitle());
        Glide.with(viewHolder.itemView)
                .load(sliderItem.getImage())
                .fitCenter()
                .into(viewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return msubslider.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public RelativeLayout layout;
        public TextView cityname;
        public TextView location;

        public ViewHolder(@NonNull View v)
        {
            super(v);
            this.imageView=v.findViewById(R.id.image);
            this.layout=v.findViewById(R.id.layout);
            this.cityname=v.findViewById(R.id.cityname);
            this.location=v.findViewById(R.id.location);
        }
    }
}
