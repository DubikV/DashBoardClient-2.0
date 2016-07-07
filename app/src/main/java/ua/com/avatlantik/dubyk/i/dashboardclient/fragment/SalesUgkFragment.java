package ua.com.avatlantik.dubyk.i.dashboardclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ua.com.avatlantik.dubyk.i.dashboardclient.R;
import ua.com.avatlantik.dubyk.i.dashboardclient.dto.SalesUGKTableDTO;

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

        setData();

        return view;
    }

    private void setData() {

        ArrayList<SalesUGKTableDTO> salesUGKTableDTOs = new ArrayList<>();
        salesUGKTableDTOs.add(new SalesUGKTableDTO("12Q", 234, 3423, 34,98, 0));
        salesUGKTableDTOs.add(new SalesUGKTableDTO("3Q", 0, 22,0 ,98, 0));
        salesUGKTableDTOs.add(new SalesUGKTableDTO("1Q", 0, 11, 34,0, 0));
        salesUGKTableDTOs.add(new SalesUGKTableDTO(getString(R.string.norm_name), 0, 11, 34,0, 0));
        salesUGKTableDTOs.add(new SalesUGKTableDTO(getString(R.string.fact_name), 0, 11, 34,0, 0));
        salesUGKTableDTOs.add(new SalesUGKTableDTO(getString(R.string.quantityClients_name), 0, 11, 34,0, 0));
        salesUGKTableDTOs.add(new SalesUGKTableDTO(getString(R.string.averageClients_name), 0, 11, 34,0, 0));


        for (SalesUGKTableDTO sales: salesUGKTableDTOs){
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


    private void setDataToView(int tviewid, int data) {
        TextView tview = (TextView) view.findViewById(tviewid);
        if(data == 0){
            tview.setText("");
        }else{
            tview.setText(""+data);
        }


    }


}
