package com.app.partyplanets.DashBoad;

import static android.content.Context.MODE_PRIVATE;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.partyplanets.Adpters.IntroSlider;
import com.app.partyplanets.Adpters.DasboadAdapter;
import com.app.partyplanets.Adpters.ServicesAdapter;
import com.app.partyplanets.Data.Dataum;
import com.app.partyplanets.Data.DasboadModel;
import com.app.partyplanets.Model.HappeningsModel;
import com.app.partyplanets.Model.PopularDestinationModel;
import com.app.partyplanets.Model.Services;
import com.app.partyplanets.Model.offersandpramotion;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.app.partyplanets.Utils.FragmentActivityMessage;
import com.app.partyplanets.Utils.GlobalBus;
import com.app.partyplanets.Utils.UtilsMethod;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class Dasboadfragment extends Fragment implements View.OnClickListener {
    RelativeLayout searchView;
    String img;
    DasboadModel dasboadModel;
    ArrayList<Dataum> banerData;
    private SliderView sliderView;
    List<Dataum> subSliderModelList;
    RecyclerView recyclerView,POPULARDESTINATION,happeningslist,SERVICES;
    List<offersandpramotion> list;
    DasboadAdapter adapter;
    List<PopularDestinationModel> popularDestinationModels;
    List<HappeningsModel> hlist;
    List<Services> services;
    String storelist="",bannerlist="",service;
    TextView termsandPolicies,helpdesk,frequentlyAQ,name;
    ImageView middleTop,img_crc_right,topleft,topbottom,bottomright,instagram,twitter,facebook,youtube;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View v= inflater.inflate(R.layout.fragment_dasboadfragment, container, false);
        GetId( v);
        return v;
    }
   public void GetId(View v)
    {
          searchView=v.findViewById(R.id.search);
          searchView.setOnClickListener(this);
          list=new ArrayList<>();
          subSliderModelList = new ArrayList<>();
          sliderView =v.findViewById(R.id.imageSlider);
           SERVICES=v.findViewById(R.id.SERVICES);
           termsandPolicies=v.findViewById(R.id.termsandPolicies);
           helpdesk=v.findViewById(R.id.helpdesk);
           frequentlyAQ=v.findViewById(R.id.askquestion);
           middleTop=v.findViewById(R.id.middle);
           instagram=v.findViewById(R.id.instagram);
           twitter=v.findViewById(R.id.twitter);
           facebook=v.findViewById(R.id.facebook);
           youtube=v.findViewById(R.id.youtube);
           img_crc_right=v.findViewById(R.id.img_crc_right);
           topleft=v.findViewById(R.id.topleft);
           topbottom=v.findViewById(R.id.top);
           bottomright=v.findViewById(R.id.bottomright);
           name=v.findViewById(R.id.name);
           UtilsMethod.INSTANCE.trendingExploring(getActivity(),"1","1");
           termsandPolicies.setOnClickListener(this);
           helpdesk.setOnClickListener(this);
           frequentlyAQ.setOnClickListener(this);
           instagram.setOnClickListener(this);
           twitter.setOnClickListener(this);
           facebook.setOnClickListener(this);
          youtube.setOnClickListener(this);


          /* subSliderModelList.add(new SubSliderModel(R.drawable.nature,0,"skip",getString(R.string.slider1text)));
          subSliderModelList.add(new SubSliderModel(R.drawable.pics,0,"skip",getString(R.string.slider1text)));
          subSliderModelList.add(new SubSliderModel(R.drawable.nature,0,"skip",getString(R.string.slider1text)));

          */

        /*  subSliderModelList.add(new BanerData("","","", ApplicationConstant.INSTANCE.baseUrl+img,""));
          sliderView.setSliderAdapter(new IntroSlider(getActivity(),subSliderModelList));
         sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.ZOOMOUTTRANSFORMATION);
        sliderView.startAutoCycle();
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.BLACK);
        sliderView.setIndicatorUnselectedColor(Color.WHITE);
        sliderView.setScrollTimeInSec(2);*/

        list.add(new offersandpramotion(R.drawable.nature));
        list.add(new offersandpramotion(R.drawable.nature));
        list.add(new offersandpramotion(R.drawable.nature));
        recyclerView=v.findViewById(R.id.recyclerview);


       /* DasboadAdapter adapter=new DasboadAdapter(getActivity(),list);
        recyclerView=v.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);*/

        //POPULARDESTINATION
   /* POPULARDESTINATION=v.findViewById(R.id.POPULARDESTINATION);
        popularDestinationModels=new ArrayList<>();
        popularDestinationModels.add(new PopularDestinationModel(R.drawable.nature,"GOA"));
        popularDestinationModels.add(new PopularDestinationModel(R.drawable.pics,"GOA"));
        popularDestinationModels.add(new PopularDestinationModel(R.drawable.nature,"GOA"));
        PopularDestination pdAdapter=new PopularDestination(getActivity(),popularDestinationModels);


        POPULARDESTINATION.setHasFixedSize(false);
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        layout.setOrientation(LinearLayoutManager.HORIZONTAL);
        POPULARDESTINATION.setLayoutManager(layout);
        POPULARDESTINATION.setAdapter(pdAdapter);


        happeningslist=v.findViewById(R.id.happenings);
        hlist=new ArrayList<>();
        hlist.add(new HappeningsModel(R.drawable.hotel,"MUMBAI","Tell Us Your Location"));
        hlist.add(new HappeningsModel(R.drawable.hotel,"MUMBAI","Tell Us Your Location"));
        hlist.add(new HappeningsModel(R.drawable.hotel,"MUMBAI","Tell Us Your Location"));
        hlist.add(new HappeningsModel(R.drawable.hotel,"MUMBAI","Tell Us Your Location"));
        HAPPENINGS happeningsAdapter=new HAPPENINGS(getActivity(),hlist);
        happeningslist.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        happeningslist.setLayoutManager(layoutManager);
        happeningslist.setAdapter(happeningsAdapter);

        SERVICES=v.findViewById(R.id.SERVICES);
        services=new ArrayList<>();
        services.add(new Services(R.drawable.weddings,"TIMES WEDDINGS"));
        services.add(new Services(R.drawable.hotel,"TIMES WEDDINGS"));
        services.add(new Services(R.drawable.weddings,"TIMES WEDDINGS"));
        services.add(new Services(R.drawable.weddings,"TIMES WEDDINGS"));
        services.add(new Services(R.drawable.hotel,"TIMES WEDDINGS"));
        servicesAdapter=new ServicesAdapter(getActivity(),services);
        SERVICES.setHasFixedSize(false);
        LinearLayoutManager SERVICESLayout = new LinearLayoutManager(getActivity());
        SERVICESLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
        SERVICES.setLayoutManager(SERVICESLayout);
        SERVICES.setAdapter(servicesAdapter);*/
        SharedPreferences myPreferences = getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        storelist = myPreferences.getString(ApplicationConstant.INSTANCE.storelist, "")+"";
        bannerlist = myPreferences.getString(ApplicationConstant.INSTANCE.bannerlist, "");
        service = myPreferences.getString(ApplicationConstant.INSTANCE.service, "");

        if(!bannerlist.equalsIgnoreCase(""))
        {
            //listedView(storelist);
            dataParse(bannerlist);
           // userServices(service);
        }
       if (!storelist.equalsIgnoreCase(""))
        {
            listedView(storelist);
        }
        if (!service.equalsIgnoreCase(""))
        {
            userServices(service);
        }


        Log.d("weiurytyre",storelist);


    }

    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage)
    {
        if (activityFragmentMessage.getMessage().equalsIgnoreCase("bannerList"))
        {
            if(storelist.equalsIgnoreCase(""))
            {
                dataParse(activityFragmentMessage.getFrom());
            }

        }
        else if(activityFragmentMessage.getMessage().equalsIgnoreCase("allmodulelisted"))
        {
            if(bannerlist.equalsIgnoreCase(""))
            {
                listedView(activityFragmentMessage.getFrom());
            }

        }
        else if(activityFragmentMessage.getMessage().equalsIgnoreCase("userServices"))
        {
            if(service.equalsIgnoreCase(""))
            {
                userServices(activityFragmentMessage.getFrom());
            }
        }
        else if(activityFragmentMessage.getMessage().equalsIgnoreCase("trendingExploring"))
        {

            explorTrending(activityFragmentMessage.getFrom());

        }
    }

    private void explorTrending(String from)
    {
        try {
            Gson gson = new Gson();
            DasboadModel dasboadModel = gson.fromJson(from, DasboadModel.class);
            ArrayList<Dataum> banerData = dasboadModel.getBanerData();
            Glide.with(this)
                    .load(ApplicationConstant.INSTANCE.baseUrl +"/"+ banerData.get(0).getBanner_img())
                    .placeholder(R.drawable.imm)
                    .fitCenter()
                    .into(middleTop);

            middleTop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), ShowDetails.class).putExtra("data", new Gson().toJson(banerData.get(0))).putExtra("status", "2"));
                }
            });


            Glide.with(this)
                    .load(ApplicationConstant.INSTANCE.baseUrl +"/"+ banerData.get(1).getBanner_img())
                    .placeholder(R.drawable.imm)
                    .fitCenter()
                    .into(topleft);
            topleft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), ShowDetails.class).putExtra("data", new Gson().toJson(banerData.get(1))).putExtra("status", "2"));
                }
            });

            Glide.with(this)
                    .load(ApplicationConstant.INSTANCE.baseUrl +"/"+ banerData.get(2).getBanner_img())
                    .placeholder(R.drawable.imm)
                    .fitCenter()
                    .into(topbottom);
            topbottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), ShowDetails.class).putExtra("data", new Gson().toJson(banerData.get(2))).putExtra("status", "2"));
                }
            });

            Glide.with(this)
                    .load(ApplicationConstant.INSTANCE.baseUrl +"/"+ banerData.get(3).getBanner_img())
                    .placeholder(R.drawable.imm)
                    .fitCenter()
                    .into(img_crc_right);
            img_crc_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), ShowDetails.class).putExtra("data", new Gson().toJson(banerData.get(3))).putExtra("status", "2"));
                }
            });

            Glide.with(this)
                    .load(ApplicationConstant.INSTANCE.baseUrl +"/"+ banerData.get(4).getBanner_img())
                    .placeholder(R.drawable.imm)
                    .fitCenter()
                    .into(bottomright);
            bottomright.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), ShowDetails.class).putExtra("data", new Gson().toJson(banerData.get(4))).putExtra("status", "2"));
                }
            });


            // galleryimages=sliderItem.getGalleryimages().get(0);

        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }




    private void userServices(String from)
    {

        Gson gson = new Gson();
        dasboadModel = gson.fromJson(from, DasboadModel.class);
        banerData = dasboadModel.getBanerData();
        ServicesAdapter servicesAdapter=new ServicesAdapter(getActivity(),banerData);
        SERVICES.setHasFixedSize(false);
        SERVICES.setLayoutManager(new LinearLayoutManager(getActivity()));
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        SERVICES.setLayoutManager(llm);
        SERVICES.setAdapter(servicesAdapter);

    }

    private void listedView(String from)
    {    Gson gson = new Gson();
        dasboadModel = gson.fromJson(from, DasboadModel.class);
        banerData = dasboadModel.getBanerData();
        DasboadAdapter adapter=new DasboadAdapter(getActivity(),banerData);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }

    private void dataParse(String from)
    {
        Gson gson = new Gson();
        dasboadModel = gson.fromJson(from, DasboadModel.class);
        banerData = dasboadModel.getBanerData();
        sliderView.setSliderAdapter(new IntroSlider(getActivity(),banerData));
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.ZOOMOUTTRANSFORMATION);
        sliderView.startAutoCycle();
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.BLACK);
        sliderView.setIndicatorUnselectedColor(Color.WHITE);
        sliderView.setScrollTimeInSec(2);



    }




    @Override
    public void onClick(View v)
    {
        if (v==searchView)
        {
           Fragment fragment = new SearchForHotelDesRestorentFm();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_container, fragment);
            fragmentTransaction.commit();

        /*  Fragment fragmentone=new Dasboadfragment();
            Fragment fragment = new SearchForHotelDesRestorentFm();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_container, fragment);
            fragmentTransaction.add(R.id.cantainer, fragmentone, "fragmentone");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();*/

        }
        if (v==termsandPolicies)
        {
            startActivity(new Intent(getActivity(),TermsAndConditions.class));
        }
        if (v==helpdesk)
        {
            startActivity(new Intent(getActivity(),Helpdesk.class));
        }
        if (v==frequentlyAQ)
        {
            startActivity(new Intent(getActivity(),frequentlyAQ.class));
        }
        if (v==instagram)
        {
            Uri uri = Uri.parse("https://www.instagram.com/the__party__planet/?igshid=Yzg5MTU1MDY%3D");
            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

            likeIng.setPackage("com.instagram.android");

            try {
                startActivity(likeIng);
            } catch (ActivityNotFoundException e)
            {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/the__party__planet/?igshid=Yzg5MTU1MDY%3D")));
            }
        }
        if (v==facebook)
        {try
        {
            Intent followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=100068203274702"));
            startActivity(followIntent);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable()
            {
                @Override
                public void run() {
                    Intent followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=100068203274702"));
                    startActivity(followIntent);
                }
            }, 1000 * 2);

        }
        catch (Exception e)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/<user_name>")));
            String errorMessage = (e.getMessage()==null)?"Message is empty":e.getMessage();
            Log.d("sdfdf" ,errorMessage);
        }




        } if (v==twitter)
        {
           /* try {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("twitter://user?screen_name=@Ramprak58780886"));
                startActivity(intent);
            } catch (Exception e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://twitter.com/#!/[user_name]")));
            }*/
        }
        if (v==youtube)
        {
           /* try {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + id));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            } catch (ActivityNotFoundException e) {

                // youtube is not installed.Will be opened in other available apps

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/watch?v=" + id));
                startActivity(i);*/
          //  }
        }

    }


    @Override
    public void onDestroy() {
        // Unregister the registered event.
        GlobalBus.getBus().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }
}