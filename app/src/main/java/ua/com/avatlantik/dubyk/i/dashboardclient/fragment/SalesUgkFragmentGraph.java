package ua.com.avatlantik.dubyk.i.dashboardclient.fragment;

import android.animation.ValueAnimator;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.XLabels;
import com.github.mikephil.charting.utils.YLabels;

import java.util.ArrayList;

import ua.com.avatlantik.dubyk.i.dashboardclient.Constants.ConstantsGlobal;
import ua.com.avatlantik.dubyk.i.dashboardclient.R;

/**
 * Created by i.dubyk on 24.06.2016.
 */
public class SalesUgkFragmentGraph extends Fragment{
    private static  final int LAYOUT = R.layout.fragment_sales_ugk_graph;
    private View view;
    private LineChart mChart;

    public static SalesUgkFragmentGraph getInstance() {

        Bundle args = new Bundle();
        SalesUgkFragmentGraph fragment = new SalesUgkFragmentGraph();
        fragment.setArguments(args);
        return  fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        int orientationView = getResources().getConfiguration().orientation;

        setGraphIntroTheView(orientationView);

        setPozitionsLinearLayoutForOrientation(orientationView);

        setSizeForTextViews(orientationView);

        startCountAnimation();

        return view;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setOrientationView(newConfig);
    }
    public void setOrientationView(Configuration newConfig){

        setPozitionsLinearLayoutForOrientation(newConfig.orientation);

    }

    private void setSizeForTextViews(int orientation){


//        int heightDisplay = getDisplayHeight();
//
//        //Build numbers--------------------------------
//        Module_Form_Settings modSet = new Module_Form_Settings(this);

//        TextView textView_down1 = (TextView) view.findViewById(R.id.textView_numbers_down1);
//        TextView textView_up1 = (TextView) view.findViewById(R.id.textView_numbers_up1);
//        textView_down1.setTextSize(modSet.getSizeHeightView(ConstantsForms.MIDDLE_SIZE_NAME, heightDisplay, orientation));
//        textView_up1.setTextSize(modSet.getSizeHeightView(ConstantsForms.MIDDLE_SIZE_NAME, heightDisplay, orientation));
//
//        TextView textView_down2_1 = (TextView) view.findViewById(R.id.textView_numbers_down2_1);
//        TextView textView_up2_1 = (TextView) view.findViewById(R.id.textView_numbers_up2_1);
//        textView_down2_1.setTextSize(modSet.getSizeHeightView(ConstantsForms.BIG_SIZE_NAME, heightDisplay, orientation));
//        textView_up2_1.setTextSize(modSet.getSizeHeightView(ConstantsForms.BIG_SIZE_NAME, heightDisplay, orientation));
//
//        TextView textView_down2_2_2 = (TextView) view.findViewById(R.id.textView_numbers_down2_2_2);
//        TextView textView_up2_2_2 = (TextView) view.findViewById(R.id.textView_numbers_up2_2_2);
//        textView_down2_2_2.setTextSize(modSet.getSizeHeightView(ConstantsForms.MIDDLE_SIZE_NAME, heightDisplay, orientation));
//        textView_up2_2_2.setTextSize(modSet.getSizeHeightView(ConstantsForms.MIDDLE_SIZE_NAME, heightDisplay, orientation));


    }

    public int getDisplayHeight() {

        Display display = this.getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    public int getDisplayWeght() {

        Display display = this.getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }


    private void setPozitionsLinearLayoutForOrientation(int orientation){

        LinearLayout llglobal = (LinearLayout) view.findViewById(R.id.linerlayout_global);
        LinearLayout llnumbers = (LinearLayout) view.findViewById(R.id.linerlayout_numbers);

//        LinearLayout llnumbers_up = (LinearLayout) view.findViewById(R.id.linerlayout_numbers_up);
//        LinearLayout llnumbers_down = (LinearLayout) view.findViewById(R.id.linerlayout_numbers_down);
//
//        LinearLayout.LayoutParams params_up = new LinearLayout.LayoutParams(
//                               LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//
//        LinearLayout.LayoutParams params_down = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);


        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            llglobal.setOrientation(LinearLayout.HORIZONTAL);
            llnumbers.setOrientation(LinearLayout.VERTICAL);

//            params_up.setMargins(0,10,0,0);
//            params_up.weight = 1;
//            llnumbers_up.setLayoutParams(params_up);
//            params_down.setMargins(0,0,0,10);
//            params_down.weight = 1;
//            llnumbers_down.setLayoutParams(params_down);



        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            llglobal.setOrientation(LinearLayout.VERTICAL);
            llnumbers.setOrientation(LinearLayout.HORIZONTAL);

//            params_up.setMargins(0,0,0,0);
//            params_up.weight = 1;
//            llnumbers_up.setLayoutParams(params_up);
//            params_down.setMargins(0,0,0,0);
//            params_down.weight = 1;
//            llnumbers_down.setLayoutParams(params_down);

       }
//
//        setSizeForTextViews(orientation);

    }


    private void startCountAnimation() {
        final TextView textView_down2_1 = (TextView) view.findViewById(R.id.textView_numbers_down2_1);
        final TextView textView_up2_1 = (TextView) view.findViewById(R.id.textView_numbers_up2_1);

        final TextView textView_down2_2_2 = (TextView) view.findViewById(R.id.textView_numbers_down2_2_2);
        final TextView textView_up2_2_2 = (TextView) view.findViewById(R.id.textView_numbers_up2_2_2);


        ValueAnimator animatorBigProcent = new ValueAnimator();
        animatorBigProcent.setObjectValues(0, 100);
        animatorBigProcent.setDuration(ConstantsGlobal.MAX_TIME);
        animatorBigProcent.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView_down2_1.setText("" + (int) animation.getAnimatedValue()+"%");
                textView_up2_1.setText("" + (int) animation.getAnimatedValue()+"%");
            }
        });

        ValueAnimator animatorSmallProcent = new ValueAnimator();
        animatorSmallProcent.setObjectValues(0, 88);
        animatorSmallProcent.setDuration(ConstantsGlobal.MAX_TIME);
        animatorSmallProcent.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView_down2_2_2.setText("" + (int) animation.getAnimatedValue()+"%");
                textView_up2_2_2.setText("" + (int) animation.getAnimatedValue()+"%");
            }
        });

        animatorBigProcent.start();
        animatorSmallProcent.start();
    }

    private void setGraphIntroTheView(int orientation){
        // Build graph-------------------------
        mChart = (LineChart) view.findViewById(R.id.chart);

        ArrayList<Entry> entriesPlane = new ArrayList<>();
        entriesPlane.add(new Entry(4f, 0));
        entriesPlane.add(new Entry(8f, 1));
        entriesPlane.add(new Entry(6f, 2));
        entriesPlane.add(new Entry(2f, 3));
        entriesPlane.add(new Entry(18f, 4));
        entriesPlane.add(new Entry(9f, 5));
        entriesPlane.add(new Entry(4f, 6));
        entriesPlane.add(new Entry(8f, 7));
        entriesPlane.add(new Entry(6f, 8));
        entriesPlane.add(new Entry(2f, 9));
        entriesPlane.add(new Entry(18f, 10));
        entriesPlane.add(new Entry(9f, 11));

        ArrayList<Entry> entriesSalec = new ArrayList<>();
        entriesSalec.add(new Entry(14f, 0));
        entriesSalec.add(new Entry(18f, 1));
        entriesSalec.add(new Entry(3f, 2));
        entriesSalec.add(new Entry(12f, 3));
        entriesSalec.add(new Entry(8f, 4));
        entriesSalec.add(new Entry(16f, 5));
        entriesSalec.add(new Entry(42f, 6));
        entriesSalec.add(new Entry(8f, 7));
        entriesSalec.add(new Entry(6f, 8));
        entriesSalec.add(new Entry(2f, 9));
        entriesSalec.add(new Entry(28f, 10));
        entriesSalec.add(new Entry(9f, 11));

        ArrayList<LineDataSet> lines = new ArrayList<LineDataSet> ();

        LineDataSet datasetPlane = new LineDataSet(entriesPlane, "План УГК");
        datasetPlane.setDrawCubic(true); // Плавность
        //datasetPlane.setDrawFilled(true); // Заполнение
        //datasetPlane.setColors(ColorTemplate.COLORFUL_COLORS); // Цвет между точками
        datasetPlane.setColor(Color.RED);
        datasetPlane.setLineWidth(4f);
        datasetPlane.setCircleSize(5f);
        datasetPlane.setCircleColor(Color.RED);
        datasetPlane.setFillAlpha(10);
        datasetPlane.setFillColor(Color.BLACK);
        datasetPlane.setDrawCubic(true);
        datasetPlane.setDrawCircles(true);
        lines.add(datasetPlane);

        LineDataSet datasetSalec = new LineDataSet(entriesSalec, "Продажи УГК");
        datasetSalec.setDrawCubic(true); // Плавность
        //datasetSalec.setDrawFilled(true); // Заполнение
        //datasetSalec.setColors(ColorTemplate.COLORFUL_COLORS); // Цвет между точками
        datasetSalec.setColor(Color.BLUE);
        datasetSalec.setLineWidth(4f);
        datasetSalec.setCircleSize(5f);
        datasetSalec.setCircleColor(Color.BLUE);
        datasetSalec.setFillAlpha(10);
        datasetSalec.setFillColor(Color.BLACK);
        datasetSalec.setDrawCubic(true);
        datasetSalec.setDrawCircles(true); //Использовать точки
        lines.add(datasetSalec);

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Cіч.");
        labels.add("Лют.");
        labels.add("Бер.");
        labels.add("Квіт.");
        labels.add("Трав.");
        labels.add("Черв.");
        labels.add("Лип.");
        labels.add("Серп.");
        labels.add("Вер.");
        labels.add("Жовт.");
        labels.add("Лист.");
        labels.add("Груд.");

        LineData data = new LineData(labels, lines);

        mChart.setDescription("");

        mChart.setDrawGridBackground(false);

        mChart.setData(data);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setPinchZoom(true);

        mChart.animateXY(ConstantsGlobal.MAX_TIME, ConstantsGlobal.MAX_TIME);

        // axis y---------------------------
        YLabels yLabels = mChart.getYLabels();
        yLabels.setTextSize(13f);
        mChart.setDrawXLabels(true);

        // axis x---------------------------
        XLabels xLabels = mChart.getXLabels();
        xLabels.setPosition(XLabels.XLabelPosition.BOTTOM);
        xLabels.setAvoidFirstLastClipping(true);
        xLabels.setTextSize(12);

        mChart.setDrawYLabels(true);

        // Legend----------------------------
        Legend l = mChart.getLegend();

        l.setFormSize(10f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.CIRCLE);
        l.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        l.setTextSize(15f);
        l.setTextColor(Color.BLACK);
        l.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        l.setYEntrySpace(5f); // set the space between the legend entries on the y-axis

        Paint p = mChart.getPaint(Chart.PAINT_VALUES);
        p.setTextSize(13f);
        p.setColor(Color.BLACK);

        mChart.setPaint(p, Chart.PAINT_VALUES);

    }
}
