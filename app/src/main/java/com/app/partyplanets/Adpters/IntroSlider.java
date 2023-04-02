package com.app.partyplanets.Adpters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.partyplanets.Data.Dataum;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.app.partyplanets.Utils.Loader;
import com.bumptech.glide.Glide;

import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class IntroSlider extends SliderViewAdapter<IntroSlider.SliderAdapterVH> {

    private Context context;
    Loader loader;
    private List<Dataum> msubslider = new ArrayList<>();


    public IntroSlider(Context context, List<Dataum> SliderItems)
    {
        this.context = context;
        msubslider=SliderItems;
    }



    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.imgslider, null);
        return new SliderAdapterVH(inflate);

    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
       Dataum sliderItem = msubslider.get(position);

       /*   viewHolder.gtstart.setText(sliderItem.getDescription());
           viewHolder.gtstart.setTextSize(20);
           viewHolder.gtstart.setTextColor(Color.BLACK);
            //viewHolder.gtstart.setText("skip");
        viewHolder.header.setText(sliderItem.getHeader());
        viewHolder.header.setTextSize(30);
        viewHolder.header.setTextColor(Color.BLACK);
*/

        Log.d("dsfjhgdfuyuyr",ApplicationConstant.INSTANCE.baseUrl+sliderItem.getBanner_img());
        Log.d("dsfjhgdfuyuyr",sliderItem.getBanner_img());

        Glide.with(viewHolder.itemView)
                .load(ApplicationConstant.INSTANCE.baseUrl+sliderItem.getBanner_img())
                .fitCenter()
                .error(R.drawable.imm)
                .into(viewHolder.imageViewBackground);

      /* Glide.with(viewHolder.itemView)
                .load(sliderItem.getImage())
                .fitCenter()
                .into(viewHolder.imageGifContainer);

*/
       /* viewHolder.gtstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences mypreference = context.getSharedPreferences("guestdata", Context.MODE_PRIVATE);



                String type = mypreference.getString("type", "");
                String url = mypreference.getString("url", "");


            }
        });
*/
    /*   viewHolder.imageGifContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               *//* Intent i = new Intent(context, Guest.class);
                context.startActivity(i);*//*
            }
        });*/



    }




    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return msubslider.size();
    }

    class SliderAdapterVH extends ViewHolder {


        View itemView;
        ImageView imageViewBackground;
      //  ImageView imageGifContainer;
       // ImageView hoverarrow;
       // TextView textViewDescription;
       // TextView gtstart;
      //  TextView header;
       // TextView tvmain;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
           // imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            //textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
          //  gtstart=itemView.findViewById(R.id.getstarted);
          //  hoverarrow=itemView.findViewById(R.id.hoverback);
         //   header=itemView.findViewById(R.id.tv_header);
            this.itemView = itemView;
        }
    }

}