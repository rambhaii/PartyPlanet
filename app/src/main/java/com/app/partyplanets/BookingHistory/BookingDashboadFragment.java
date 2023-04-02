package com.app.partyplanets.BookingHistory;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.partyplanets.DashBoad.BookingHistory;
import com.app.partyplanets.R;
import com.google.android.material.tabs.TabLayout;

public class BookingDashboadFragment extends Fragment {


    TabLayout tabLayout;
    ViewPager viewPager;
     HistoryAdapter adapter;
     LinearLayout container;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_booking_dashboad, container, false);

        GetId(v);
        return v;
    }
    private void GetId(View v)
    {
        tabLayout = v. findViewById(R.id.tab_layout);
        container = v. findViewById(R.id.fragment_container);



        //create tabs title
        tabLayout.addTab(tabLayout.newTab().setText("Applications"));
        tabLayout.addTab(tabLayout.newTab().setText("Books"));
        tabLayout.addTab(tabLayout.newTab().setText("Games"));

        //replace default fragment
        replaceFragment(new BookingHistory());

        //handling tab click event
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    replaceFragment(new BookingHistory());
                } else if (tab.getPosition() == 1) {
                    replaceFragment(new CancelledFragment());
                } else {
                    replaceFragment(new BookingHistory());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);

        transaction.commit();
    }





      /*  tabLayout=v.findViewById(R.id.tabLayout);
        viewPager=v.findViewById(R.id.viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("Active"));
        tabLayout.addTab(tabLayout.newTab().setText("Cancelled"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
         adapter = new HistoryAdapter( getActivity(),getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
         viewPager.setAdapter(adapter);
          adapter.notifyDataSetChanged();
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


*/


}