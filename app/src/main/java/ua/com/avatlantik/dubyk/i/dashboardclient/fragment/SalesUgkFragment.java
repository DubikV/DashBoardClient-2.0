package ua.com.avatlantik.dubyk.i.dashboardclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.com.avatlantik.dubyk.i.dashboardclient.R;

/**
 * Created by i.dubyk on 24.06.2016.
 */
public class SalesUgkFragment  extends Fragment{
    private static  final int LAYOUT = R.layout.fragment_sales_ugk;
    private View view;

    public static SalesUgkFragment getInstance() {

        Bundle args = new Bundle();
        SalesUgkFragment fragment = new SalesUgkFragment();
        fragment.setArguments(args);
        return  fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        return view;
    }
}
