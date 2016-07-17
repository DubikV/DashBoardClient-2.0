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
import ua.com.avatlantik.dubyk.i.dashboardclient.dto.Money.MoneyTableDTO;

/**
 * Created by i.dubyk on 24.06.2016.
 */
public class MoneyFragment  extends Fragment{
    private static  final int LAYOUT = R.layout.fragment_money;
    private View view;
    private DataDTO dataDTO;
    private ArrayList<MoneyTableDTO> moneyTableDTO;

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

        moneyTableDTO = dataDTO.getMoneyTableDTO();

        if (moneyTableDTO == null){
            Toast.makeText(getActivity(),getString(R.string.error_no_data),Toast.LENGTH_SHORT).show();
            return view;
        }

        setData();

        return view;
    }

    private void setData() {

        for (MoneyTableDTO money: moneyTableDTO){
            if (money.getTypeData().equalsIgnoreCase(getString(R.string.plane_12q_name))){
                setDataToView(R.id.textView1_1, money.getSumMonth());
                setDataToView(R.id.textView1_2, money.getSumDay());
                setDataToView(R.id.textView1_3, money.getDelta12());
                setDataToView(R.id.textView1_4, money.getDelta3());
                setDataToView(R.id.textView1_5, money.getDelta1());
            }
            if (money.getTypeData().equalsIgnoreCase(getString(R.string.plane_3q_name))){
                setDataToView(R.id.textView2_1, money.getSumMonth());
                setDataToView(R.id.textView2_2, money.getSumDay());
                setDataToView(R.id.textView2_3, money.getDelta12());
                setDataToView(R.id.textView2_4, money.getDelta3());
                setDataToView(R.id.textView2_5, money.getDelta1());
            }
            if (money.getTypeData().equalsIgnoreCase(getString(R.string.plane_1q_name))){
                setDataToView(R.id.textView3_1, money.getSumMonth());
                setDataToView(R.id.textView3_2, money.getSumDay());
                setDataToView(R.id.textView3_3, money.getDelta12());
                setDataToView(R.id.textView3_4, money.getDelta3());
                setDataToView(R.id.textView3_5, money.getDelta1());
            }
            if (money.getTypeData().equalsIgnoreCase(getString(R.string.norm_name))){
                setDataToView(R.id.textView4_1, money.getSumMonth());
                setDataToView(R.id.textView4_2, money.getSumDay());
                setDataToView(R.id.textView4_3, money.getDelta12());
                setDataToView(R.id.textView4_4, money.getDelta3());
                setDataToView(R.id.textView4_5, money.getDelta1());
            }
            if (money.getTypeData().equalsIgnoreCase(getString(R.string.fact_name))){
                setDataToView(R.id.textView5_1, money.getSumMonth());
                setDataToView(R.id.textView5_2, money.getSumDay());
                setDataToView(R.id.textView5_3, money.getDelta12());
                setDataToView(R.id.textView5_4, money.getDelta3());
                setDataToView(R.id.textView5_5, money.getDelta1());
            }
            if (money.getTypeData().equalsIgnoreCase(getString(R.string.quantityClients_name))){
                setDataToView(R.id.textView6_1, money.getSumMonth());
                setDataToView(R.id.textView6_2, money.getSumDay());
                setDataToView(R.id.textView6_3, money.getDelta12());
                setDataToView(R.id.textView6_4, money.getDelta3());
                setDataToView(R.id.textView6_5, money.getDelta1());
            }
            if (money.getTypeData().equalsIgnoreCase(getString(R.string.averageClients_name))){
                setDataToView(R.id.textView7_1, money.getSumMonth());
                setDataToView(R.id.textView7_2, money.getSumDay());
                setDataToView(R.id.textView7_3, money.getDelta12());
                setDataToView(R.id.textView7_4, money.getDelta3());
                setDataToView(R.id.textView7_5, money.getDelta1());
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
