package com.app.partyplanets.ResttaurantAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.partyplanets.R;
import com.app.partyplanets.RestaurantData.FoodManue;
import com.app.partyplanets.RestaurantData.ResturantData;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdeptar extends  RecyclerView.Adapter<RestaurantAdeptar.ViewHolder>
{
    private Context context;
    private List<ResturantData> msubslider = new ArrayList<>();
    private List<FoodManue> nestedlist ;
    public interface onListClickedRowListner
    {
        void onListSelected(int status);


    }
   onListClickedRowListner listner;


    public RestaurantAdeptar(Context context, List<ResturantData> msubslider,  onListClickedRowListner listner)
    {
        this.context = context;
        this.msubslider = msubslider;
        this.listner =  listner;
    }

    @NonNull
    @Override
    public RestaurantAdeptar.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.foodmenucategories, parent, false);
        return  new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantAdeptar.ViewHolder holder, int position)
    {
        ResturantData sliderItem = msubslider.get(position);
        holder.tv_title.setText(sliderItem.getName());
        Log.d("dkfjghjheryy",sliderItem.getName());
        boolean isExpendable=sliderItem.expendable;
        holder.isExpendable.setVisibility(isExpendable ? View.VISIBLE :View.GONE);
        if (isExpendable)
        { holder.imageView.setImageResource(R.drawable.ic_up_arrow);
        }
        else
        {
            holder.imageView.setImageResource(R.drawable.ic_arrow__1_);
        }
        Log.d("yuu", String.valueOf(sliderItem.getData()));

        if (sliderItem.getData().isEmpty())
        {
            holder.layout.setVisibility(View.GONE);
        }else
        {
            FoodAdapter foodAdapter = new FoodAdapter(sliderItem.getData(), context);
            holder.recyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
            holder.recyclerView.setHasFixedSize(true);
            holder.recyclerView.setAdapter(foodAdapter);
        }

        holder.linearLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listner.onListSelected(1);
                sliderItem.setExpendable(!sliderItem.isExpendable());
                nestedlist=sliderItem.getData();
                notifyItemChanged(holder.getAdapterPosition());
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
     TextView tv_title;
     private RelativeLayout isExpendable;
     private RecyclerView recyclerView;
     private ImageView imageView,image;
     LinearLayout linearLayout,layout;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tv_title=itemView.findViewById(R.id.tv_title);
            isExpendable=itemView.findViewById(R.id.isExpendable);
            recyclerView=itemView.findViewById(R.id.tv_child);
            imageView=itemView.findViewById(R.id.arrow);
            linearLayout=itemView.findViewById(R.id.linearlayout);
            image=itemView.findViewById(R.id.image);
            layout=itemView.findViewById(R.id.parent_linear);
        }
    }


}
