package com.app.partyplanets.DashBoad;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.app.partyplanets.Adpters.BookingHistoryAdapter;
import com.app.partyplanets.Data.BookingHistoryModel;
import com.app.partyplanets.Data.Data;
import com.app.partyplanets.Data.SelectedRoomModel;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.app.partyplanets.Utils.FragmentActivityMessage;
import com.app.partyplanets.Utils.GlobalBus;
import com.app.partyplanets.Utils.UtilsMethod;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Collections;


public class BookingHistory extends Fragment
{

    RecyclerView recyclerview;
    String secureloginResponse="";
    BookingHistoryModel bookingHistoryModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

  /*  @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true *//* enabled by default *//*) {
            @Override
            public void handleOnBackPressed() {
                // getActivity().onBackPressed();
                startActivity(new Intent(getActivity(),DashBoad.class));
                getActivity().finish();
               *//* Fragment dasboadfragment=new Dasboadfragment();
                Fragment fragment = new Dasboadfragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
               // fragmentTransaction.replace(R.id.main_container, fragment);
                fragmentTransaction.add(dasboadfragment, "detail") // Add this transaction to the back stack (name is an optional name for this back stack state, or null).
                        .addToBackStack(null)
                        .commit();
             //   fragmentTransaction.commit();
*//*
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);


    }*/





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View v= inflater.inflate(R.layout.fragment_booking_history, container, false);
        WindowManager windowManager = (WindowManager)getActivity().getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();
        v.setLayoutParams(new RecyclerView.LayoutParams(width, RecyclerView.LayoutParams.MATCH_PARENT));

        GetId(v);
        return v;
    }
    public void  GetId(View v)
    {
        SharedPreferences myPreferences = getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
         secureloginResponse = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, null);
        Data securelogincheckResponse = new Gson().fromJson(secureloginResponse, Data.class);
        String  userId = securelogincheckResponse.getId();
        UtilsMethod.INSTANCE.bookingHistory(getActivity(),userId);
        recyclerview=v.findViewById(R.id.recyclerview);
        secureloginResponse = myPreferences.getString(ApplicationConstant.INSTANCE.History, null);
        Log.d("dsjfhjfbjhfbgjhjreuiyre",new Gson().toJson(secureloginResponse));

    if (secureloginResponse==null)
    {
        Toast.makeText(getActivity(), "Con not have any booking ?", Toast.LENGTH_SHORT).show();
    }
    else
    {
        BookingHisList(secureloginResponse);
    }


    }
    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage)
    {
        if (activityFragmentMessage.getMessage().equalsIgnoreCase("datahistory"))
        {
      //      BookingHisList(activityFragmentMessage.getFrom());
        }
    }

    private void BookingHisList(String from)
    {

        Gson gson = new Gson();
        bookingHistoryModel = gson.fromJson(from, BookingHistoryModel.class);
        ArrayList<SelectedRoomModel> selectedRoomModels = bookingHistoryModel.getBanerData();
        Collections.reverse(bookingHistoryModel.getBanerData());
        BookingHistoryAdapter servicesAdapter=new BookingHistoryAdapter(getActivity(),selectedRoomModels);
        servicesAdapter.notifyDataSetChanged();
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(llm);
        recyclerview.setAdapter(servicesAdapter);


    }





    @Override
    public void onDestroy()
    {
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