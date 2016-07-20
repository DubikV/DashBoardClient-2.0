package ua.com.avatlantik.dubyk.i.dashboardclient.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.com.avatlantik.dubyk.i.dashboardclient.R;

public class TabFragmentStocks extends Fragment {

    private static TabLayout tabLayout;
    private static ViewPager viewPager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View x =  inflater.inflate(R.layout.app_bar_main,null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) x.findViewById(R.id.vievPager);

        TabPageStocksFragmentAdapter adapter = new TabPageStocksFragmentAdapter((getChildFragmentManager()));
        viewPager.setAdapter(adapter);

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return x;

    }

}
