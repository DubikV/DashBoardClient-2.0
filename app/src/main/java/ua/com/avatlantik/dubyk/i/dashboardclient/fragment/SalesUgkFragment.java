package ua.com.avatlantik.dubyk.i.dashboardclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ua.com.avatlantik.dubyk.i.dashboardclient.R;
import ua.com.avatlantik.dubyk.i.dashboardclient.dto.DataDTO;
import ua.com.avatlantik.dubyk.i.dashboardclient.dto.SalesUGK.SalesUGKTableDTO;

/**
 * Created by i.dubyk on 24.06.2016.
 */
public class SalesUgkFragment  extends Fragment{
    private static  final int LAYOUT = R.layout.fragment_sales_ugk;
    private View view;
    private DataDTO dataDTO;
    private ArrayList<SalesUGKTableDTO> salesUGKTableDTO;

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

        dataDTO = DataDTO.getInstance();

        salesUGKTableDTO = dataDTO.getSalesUGKTableDTO();

        if (salesUGKTableDTO == null){
            Toast.makeText(getActivity(),getString(R.string.error_no_data),Toast.LENGTH_SHORT).show();
            return view;
        }

        setData();

        return view;
    }

    private void setData() {

        for (SalesUGKTableDTO sales: salesUGKTableDTO){
            if (sales.getTypeData().equalsIgnoreCase(getString(R.string.plane_12q_name))){
                setDataToView(R.id.textView1_1, sales.getSumMonth());
                setDataToView(R.id.textView1_2, sales.getSumDay());
                setDataToView(R.id.textView1_3, sales.getDelta12());
                setDataToView(R.id.textView1_4, sales.getDelta3());
                setDataToView(R.id.textView1_5, sales.getDelta1());
            }
            if (sales.getTypeData().equalsIgnoreCase(getString(R.string.plane_3q_name))){
                setDataToView(R.id.textView2_1, sales.getSumMonth());
                setDataToView(R.id.textView2_2, sales.getSumDay());
                setDataToView(R.id.textView2_3, sales.getDelta12());
                setDataToView(R.id.textView2_4, sales.getDelta3());
                setDataToView(R.id.textView2_5, sales.getDelta1());
            }
            if (sales.getTypeData().equalsIgnoreCase(getString(R.string.plane_1q_name))){
                setDataToView(R.id.textView3_1, sales.getSumMonth());
                setDataToView(R.id.textView3_2, sales.getSumDay());
                setDataToView(R.id.textView3_3, sales.getDelta12());
                setDataToView(R.id.textView3_4, sales.getDelta3());
                setDataToView(R.id.textView3_5, sales.getDelta1());
            }
            if (sales.getTypeData().equalsIgnoreCase(getString(R.string.norm_name))){
                setDataToView(R.id.textView4_1, sales.getSumMonth());
                setDataToView(R.id.textView4_2, sales.getSumDay());
                setDataToView(R.id.textView4_3, sales.getDelta12());
                setDataToView(R.id.textView4_4, sales.getDelta3());
                setDataToView(R.id.textView4_5, sales.getDelta1());
            }
            if (sales.getTypeData().equalsIgnoreCase(getString(R.string.fact_name))){
                setDataToView(R.id.textView5_1, sales.getSumMonth());
                setDataToView(R.id.textView5_2, sales.getSumDay());
                setDataToView(R.id.textView5_3, sales.getDelta12());
                setDataToView(R.id.textView5_4, sales.getDelta3());
                setDataToView(R.id.textView5_5, sales.getDelta1());
            }
            if (sales.getTypeData().equalsIgnoreCase(getString(R.string.quantityClients_name))){
                setDataToView(R.id.textView6_1, sales.getSumMonth());
                setDataToView(R.id.textView6_2, sales.getSumDay());
                setDataToView(R.id.textView6_3, sales.getDelta12());
                setDataToView(R.id.textView6_4, sales.getDelta3());
                setDataToView(R.id.textView6_5, sales.getDelta1());
            }
            if (sales.getTypeData().equalsIgnoreCase(getString(R.string.averageClients_name))){
                setDataToView(R.id.textView7_1, sales.getSumMonth());
                setDataToView(R.id.textView7_2, sales.getSumDay());
                setDataToView(R.id.textView7_3, sales.getDelta12());
                setDataToView(R.id.textView7_4, sales.getDelta3());
                setDataToView(R.id.textView7_5, sales.getDelta1());
            }
        }

    }


    private void setDataToView(int tviewid, double data) {
        TextView tview = (TextView) view.findViewById(tviewid);
        if(data == 0){
            tview.setText("");
        }else{
            tview.setText(""+data);
        }


    }


}
