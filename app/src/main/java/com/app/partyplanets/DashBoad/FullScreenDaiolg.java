package com.app.partyplanets.DashBoad;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.DialogFragment;

import com.app.partyplanets.Data.Galleryimages;
import com.app.partyplanets.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class FullScreenDaiolg extends DialogFragment
{
    SliderView sliderView;
    ImageView back_btn;
    String url="";
    ArrayList<Galleryimages> gallery;
    Galleryimages Galleryimages;
    private boolean backPressToExit = false;


    /** The system calls this to get the DialogFragment's layout, regardless
     of whether it's being displayed as a dialog or an embedded fragment. */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // getActivity().getSupportActionBar().hide();
        // Inflate the layout to use as dialog or embedded fragment
        View view=inflater.inflate(R.layout.purchase_items, container, false);
        sliderView=view.findViewById(R.id.fullImage);
        back_btn=view.findViewById(R.id.back_btn);
        url= getArguments().getString("imageUrl")+"";
        dataParse(url);

       /*
        Gson gson = new Gson();*/
       // galleryimages = gson.fromJson(url, Galleryimages.class);

     //   Log.d("kfdnkjdfnvjk",galleryimages.getImage());

       /* Glide.with(this)
                .load(ApplicationConstant.INSTANCE.baseUrl+galleryimages.getImage())
                .into(fullImage);

        */
        back_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                FullScreenDaiolg.this.dismiss();
            }
        });

        return view;
    }




    private void dataParse(String  list)
    {
        Log.d("skjdfjdgy",""+list);
        ArrayList<Galleryimages>  banerData=new ArrayList<Galleryimages>() ;
        banerData=new Gson().fromJson(list,new TypeToken<ArrayList<Galleryimages> >() {}.getType());
        sliderView.setSliderAdapter(new GallareySlider(getActivity(),banerData));
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.ZOOMOUTTRANSFORMATION);
       // sliderView.startAutoCycle();
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LOCALE);
        sliderView.setIndicatorSelectedColor(Color.BLACK);
        sliderView.setIndicatorUnselectedColor(Color.BLACK);
        sliderView.setScrollTimeInSec(2);
    }

}