package com.app.partyplanets.DashBoad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.partyplanets.Data.Galleryimages;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.app.partyplanets.Utils.Loader;
import com.bumptech.glide.Glide;
import com.jsibbold.zoomage.ZoomageView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class GallareySlider extends SliderViewAdapter<GallareySlider.SliderAdapterVH> {

    private Context context;
    Loader loader;
    private List<Galleryimages> msubslider = new ArrayList<>();


    public GallareySlider(Context context, List<Galleryimages> SliderItems)
    {
        this.context = context;
        msubslider=SliderItems;
    }



    @Override
    public GallareySlider.SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallary_slider, null);
        return new SliderAdapterVH(inflate);

    }

    @Override
    public void onBindViewHolder(GallareySlider.SliderAdapterVH viewHolder, int position) {
        Galleryimages sliderItem = msubslider.get(position);

        Glide.with(viewHolder.itemView)
                .load(ApplicationConstant.INSTANCE.baseUrl+"/"+sliderItem.getImage())
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
        ZoomageView imageViewBackground;
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
