package com.app.partyplanets.Adpters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.app.partyplanets.Trending.HotelFragment;
import com.app.partyplanets.Trending.ResortsFragment;
import com.app.partyplanets.Trending.ResturantFragment;
import com.app.partyplanets.Utils.UtilsMethod;

public class MyAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }


    @Override
    public Fragment getItem(int position)
    {
        switch (position) {
            case 0:
                HotelFragment homeFragment = new HotelFragment();
                UtilsMethod.INSTANCE.trendingSearch(myContext,"1","1");
                return homeFragment;
            case 1:
                ResturantFragment resturantFragment = new ResturantFragment();
                UtilsMethod.INSTANCE.trendingSearch(myContext,"2","1");
                return resturantFragment;
            case 2:
                ResortsFragment sportFragment = new ResortsFragment();
                UtilsMethod.INSTANCE.trendingSearch(myContext,"3","1");
                return sportFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return totalTabs;
    }
}