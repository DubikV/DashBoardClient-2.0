package ua.com.avatlantik.dubyk.i.dashboardclient.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ua.com.avatlantik.dubyk.i.dashboardclient.fragment.SalesUgkFragment;
import ua.com.avatlantik.dubyk.i.dashboardclient.fragment.SalesUgkFragmentGraph;

/**
 * Created by i.dubyk on 24.06.2016.
 */
public class TabPageSalesUGKFragmentAdapter extends FragmentPagerAdapter {

    private String[] tabs;

    public TabPageSalesUGKFragmentAdapter(FragmentManager fm) {
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
                return SalesUgkFragmentGraph.getInstance();
            case 1:
                return SalesUgkFragment.getInstance();
            }
            return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }



}


