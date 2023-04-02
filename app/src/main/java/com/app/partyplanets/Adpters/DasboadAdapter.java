package com.app.partyplanets.Adpters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.app.partyplanets.DashBoad.ShowDetails;
import com.app.partyplanets.Data.Dataum;
import com.app.partyplanets.Model.list;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.app.partyplanets.Utils.UtilsMethod;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DasboadAdapter extends  RecyclerView.Adapter<DasboadAdapter.ViewHolder>
{
    private Context context;
    private List<Dataum> msubslider = new ArrayList<>();
 int index=0;
    public DasboadAdapter(Context context, List<Dataum> msubslider)
    {
        this.context = context;
        this.msubslider = msubslider;
    }



    @NonNull
    @Override
    public DasboadAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.offers_and_population, parent, false);
        return  new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull DasboadAdapter.ViewHolder viewHolder, int position)
    {


        Dataum sliderItem = msubslider.get(position);

        index=position;
        if (index==0)
        {
            viewHolder.li_cat.setVisibility(View.VISIBLE);
            viewHolder.categoriges.setText("BOOK A STAY");
        }
        if (index==1)
        {
          viewHolder.li_cat.setVisibility(View.VISIBLE);
          viewHolder.categoriges.setText("BOOK A PARTY");
        }
        viewHolder.tv_title.setText(sliderItem.getName());
        viewHolder.tv_title.setAllCaps(true);
        viewHolder.viewmore.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                UtilsMethod.INSTANCE.viewMore(context,sliderItem.getId());


            }
        });



        for (list d : sliderItem.getList())
        {
            LayoutInflater inflater2 = null;
            inflater2 = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            final View mLinearView = inflater2.inflate(R.layout.populardestination, null);
            ImageView image = mLinearView.findViewById(R.id.image);
            TextView cityname = mLinearView.findViewById(R.id.cityname);
            TextView location = mLinearView.findViewById(R.id.location);
            LinearLayout select = mLinearView.findViewById(R.id.select);
            Glide.with(viewHolder.itemView)
                    .load(ApplicationConstant.INSTANCE.baseUrl +"/"+ d.getBanner_img())
                    .fitCenter()
                    .error(R.drawable.imm)
                    .into(image);
            Log.d("djsfjdfhg",d.getName()+""+ApplicationConstant.INSTANCE.baseUrl + d.getBanner_img());
            cityname.setText(d.getName());
            location.setText(d.getCity());
            select.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent i=new Intent(context,ShowDetails.class);
                    i.putExtra("data",  new Gson().toJson(d));
                    i.putExtra("status","2");
                    context.startActivity(i);

                }
            });
            viewHolder.layout_view.addView(mLinearView);
        }
    }

    @Override
    public int getItemCount() {
        return msubslider.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

       public TextView tv_title,categoriges;
       LinearLayout layout_view,li_cat;
        LottieAnimationView viewmore;

        public ViewHolder(@NonNull View v)
        {
            super(v);
            this.tv_title=v.findViewById(R.id.tv_title);
            this.layout_view=v.findViewById(R.id.layout_view);
            this.viewmore=v.findViewById(R.id.viewmore);
            this.categoriges=v.findViewById(R.id.categoriges);
            this.li_cat=v.findViewById(R.id.li_cat);


        }
    }
}
