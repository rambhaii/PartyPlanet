package com.app.partyplanets.Trending;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.partyplanets.Adpters.SearchAdapter;
import com.app.partyplanets.Data.DasboadModel;
import com.app.partyplanets.Data.Dataum;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.FragmentActivityMessage;
import com.app.partyplanets.Utils.GlobalBus;
import com.app.partyplanets.Utils.UtilsMethod;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;


public class HotelFragment extends Fragment {

    DasboadModel dasboadModel;
    ArrayList<Dataum> banerData;

    RecyclerView recyclerview;
    String secureloginResponse="";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState)
    {

        View v= inflater.inflate(R.layout.fragment_hotel, container, false);
        GetId(v);
        return v;
    }
    public void GetId(View v)
    {   UtilsMethod.INSTANCE.trendingSearch(getActivity(),"1","1");
        recyclerview=v.findViewById(R.id.recyclerview);

    }

    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage)
    {
        if (activityFragmentMessage.getMessage().equalsIgnoreCase("hotelsdata"))
        {

            dataParse(activityFragmentMessage.getFrom());


        }

    }


    private void dataParse(String from)
    {
        Log.d("kdghkhvcxvbdgieevvugjd",""+from);
        Gson gson = new Gson();
        dasboadModel = gson.fromJson(from, DasboadModel.class);
        banerData = dasboadModel.getBanerData();
        SearchAdapter adapter=new SearchAdapter(getActivity(),banerData);
        recyclerview.setHasFixedSize(false);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(llm);
        recyclerview.setAdapter(adapter);


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