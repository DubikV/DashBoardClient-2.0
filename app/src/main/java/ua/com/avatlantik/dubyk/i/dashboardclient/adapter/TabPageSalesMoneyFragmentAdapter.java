package ua.com.avatlantik.dubyk.i.dashboardclient.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ua.com.avatlantik.dubyk.i.dashboardclient.fragment.MoneyFragment;
import ua.com.avatlantik.dubyk.i.dashboardclient.fragment.MoneyFragmentGraph;

/**
 * Created by i.dubyk on 24.06.2016.
 */
public class TabPageSalesMoneyFragmentAdapter extends FragmentPagerAdapter {

    private String[] tabs;

    public TabPageSalesMoneyFragmentAdapter(FragmentManager fm) {
        super(fm);
        tabs = new String[] {
            "Графік",
            "Дані"
        };
    }

    @Override
        public CharSequence getPageTitle(int position) {
           return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MoneyFragmentGraph.getInstance();
            case 1:
                return MoneyFragment.getInstance();
            }
            return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }



}


