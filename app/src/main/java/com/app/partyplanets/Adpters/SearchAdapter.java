package com.app.partyplanets.Adpters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.partyplanets.DashBoad.ShowDetails;
import com.app.partyplanets.Data.Dataum;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private List<Dataum> msubslider = new ArrayList<>();

    public SearchAdapter(Context context, List<Dataum> msubslider)
    {
        this.context = context;
        this.msubslider = msubslider;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.searchinglist, parent, false);
        return new SearchAdapter.ViewHolder(listItem);

    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder viewHolder, int position) {
        Dataum sliderItem = msubslider.get(position);

        Log.d("kdfjgfhg",sliderItem.getName()+"   "+sliderItem.getId());
        viewHolder.layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                context.startActivity(new Intent(context, ShowDetails.class).putExtra("data", new Gson().toJson(sliderItem)).putExtra("status","2"));
            }
        });
        viewHolder.current_price.setText("₹ "+sliderItem.getCurrent_price());
        viewHolder.offer_price.setText("₹ "+sliderItem.getOffer_price());
        viewHolder.name.setText(sliderItem.getName());
        viewHolder.location.setText(sliderItem.getCity()+" "+sliderItem.getState());
        Glide.with(viewHolder.itemView)
                .load(ApplicationConstant.INSTANCE.baseUrl +"/"+ sliderItem.getBanner_img())
                .fitCenter()
                .error(R.drawable.imm)
                .into(viewHolder.image);
    }

    @Override
    public int getItemCount()
    {
        return msubslider.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, tv_excelent,location, review, current_price, offer_price;
        RatingBar ratingBar;
        LinearLayout layout;

        public ViewHolder(@NonNull View v) {
            super(v);
            this.image = v.findViewById(R.id.image);
            this.name = v.findViewById(R.id.name);
            this.tv_excelent = v.findViewById(R.id.tv_excelent);
            this.review = v.findViewById(R.id.review);
            this.current_price = v.findViewById(R.id.current_price);
            this.offer_price = v.findViewById(R.id.offer_price);
            this.ratingBar = v.findViewById(R.id.ratingBar);
            this.layout = v.findViewById(R.id.layout);
            this.location = v.findViewById(R.id.location);
        }
    }
}
