package com.app.partyplanets.BookingHistory;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.app.partyplanets.DashBoad.BookingHistory;

public class HistoryAdapter  extends FragmentPagerAdapter
{
    private Context myContext;
    int totalTabs;

    public HistoryAdapter(Context context, FragmentManager fm, int totalTabs)
    {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }


    @Override
    public Fragment getItem(int position)
    {

        switch (position) {

            case 0:
                BookingHistory homeFragment = new BookingHistory();
                return homeFragment;
            case 1:
                BookingHistory homeFragment1 = new BookingHistory();
               return homeFragment1;
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
