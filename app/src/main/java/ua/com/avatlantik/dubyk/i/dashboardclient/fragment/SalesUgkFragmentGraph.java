package ua.com.avatlantik.dubyk.i.dashboardclient.fragment;

import android.animation.ValueAnimator;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.AxisValueFormatter;

import java.util.ArrayList;

import ua.com.avatlantik.dubyk.i.dashboardclient.Constants.ConstantsGlobal;
import ua.com.avatlantik.dubyk.i.dashboardclient.R;
import ua.com.avatlantik.dubyk.i.dashboardclient.dto.DataDTO;
import ua.com.avatlantik.dubyk.i.dashboardclient.dto.SalesUGKDTO;

/**
 * Created by i.dubyk on 24.06.2016.
 */
public class SalesUgkFragmentGraph extends Fragment{
    private static  final int LAYOUT = R.layout.fragment_sales_graph;
    private View view;
    private CombinedChart mChart;
    private final int itemcount = 12;
    private ArrayList<SalesUGKDTO> salesUGK;
    private ArrayList<String> xAxisList;
    private int plane_12Q, plane_3Q, plane_1Q;

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

        DataDTO dataDTO = DataDTO.getInstance();

        salesUGK = dataDTO.getSalesUGKDTO();

        if (salesUGK == null){
            Toast.makeText(getActivity(),getString(R.string.error_no_data),Toast.LENGTH_SHORT).show();
            return view;
        }

        xAxisList = new ArrayList<>();
        for (SalesUGKDTO salesUGKDTO: salesUGK) {
            if (salesUGKDTO.getTypeData().equals(ConstantsGlobal.PLANE_12Q)) {
                xAxisList.add(String.valueOf(salesUGKDTO.getNumberDay()));
                plane_12Q = salesUGKDTO.getValye();
            }
            if (salesUGKDTO.getTypeData().equals(ConstantsGlobal.PLANE_3Q)) {
                plane_3Q = salesUGKDTO.getValye();
            }
            if (salesUGKDTO.getTypeData().equals(ConstantsGlobal.PLANE_1Q)) {
                plane_1Q = salesUGKDTO.getValye();
            }
        }

        startCountAnimation();

        setCombineGraphIntroTheView(orientationView);

        return view;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setOrientationView(newConfig);
    }
    public void setOrientationView(Configuration newConfig){

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

    private void startCountAnimation() {

        final TextView textView_header_graph = (TextView) view.findViewById(R.id.textView_header_graph);

        final ValueAnimator animatorHeaderGraph4 = new ValueAnimator();
        animatorHeaderGraph4.setObjectValues(0,87);
        animatorHeaderGraph4.setDuration(ConstantsGlobal.SMALL_TIME);
        animatorHeaderGraph4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView_header_graph.setText("" + getString(R.string.nav_salesUgk_ua) + "   "+"91"+"%/"+"115"+"%"+"   "+"69"+"/"+(int) animation.getAnimatedValue());

            }
        });

        final ValueAnimator animatorHeaderGraph3 = new ValueAnimator();
        animatorHeaderGraph3.setObjectValues(0,69);
        animatorHeaderGraph3.setDuration(ConstantsGlobal.SMALL_TIME);
        animatorHeaderGraph3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView_header_graph.setText("" + getString(R.string.nav_salesUgk_ua) + "   "+"91"+"%/"+"115"+"%"+"   "+(int) animation.getAnimatedValue()+"/0");
                if (animation.getAnimatedValue().toString().equals("69")) {
                    animatorHeaderGraph4.start();
                }
            }
        });

        final ValueAnimator animatorHeaderGraph2 = new ValueAnimator();
        animatorHeaderGraph2.setObjectValues(0,115);
        animatorHeaderGraph2.setDuration(ConstantsGlobal.SMALL_TIME);
        animatorHeaderGraph2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView_header_graph.setText("" + getString(R.string.nav_salesUgk_ua) + "   "+"91"+"%/"+(int) animation.getAnimatedValue()+"%"+"   "+"0/0");
                if (animation.getAnimatedValue().toString().equals("115")) {
                    animatorHeaderGraph3.start();
                }
            }
        });

        ValueAnimator animatorHeaderGraph1 = new ValueAnimator();
        animatorHeaderGraph1.setObjectValues(0,91);
        animatorHeaderGraph1.setDuration(ConstantsGlobal.SMALL_TIME);
        animatorHeaderGraph1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView_header_graph.setText("" + getString(R.string.nav_salesUgk_ua) + "   "+(int) animation.getAnimatedValue()+"%/"+"0%"+"   "+"0/0");
                if (animation.getAnimatedValue().toString().equals("91")) {
                    animatorHeaderGraph2.start();
                }
            }
        });

        animatorHeaderGraph1.start();

    }

    private void setCombineGraphIntroTheView(int orientation){

        mChart = (CombinedChart) view.findViewById(R.id.chart);
        mChart.setDescription("");
        mChart.setBackgroundColor(getResources().getColor(R.color.colorBlueWhite));
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(false);

        mChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.LINE,
                CombinedChart.DrawOrder.BAR
        });

        mChart.animateY(ConstantsGlobal.MAX_TIME);

        Legend l = mChart.getLegend();
        l.setWordWrapEnabled(true);
        l.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        l.setTextSize(getResources().getDimension(R.dimen.graph_legend_textsize));

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawAxisLine(true);
        rightAxis.setAxisMinValue(getResources().getDimension(R.dimen.zero_size)); // this replaces setStartAtZero(true)
        rightAxis.setEnabled(false);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(true);
        leftAxis.setAxisMinValue(getResources().getDimension(R.dimen.zero_size)); // this replaces setStartAtZero(true)
        leftAxis.setTextSize(getResources().getDimension(R.dimen.axis_textSize));


        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinValue(getResources().getDimension(R.dimen.zero_size));
        //xAxis.setAxisMaxValue(xAxisList.size());
        //xAxis.setAxisMaxValue(12f); // Max size
        //xAxis.setGranularity(5f);   // Division of the scale
        xAxis.setTextSize(getResources().getDimension(R.dimen.axis_textSize));
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(xAxisList.size());
        xAxis.setValueFormatter(new AxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xAxisList.get((int) value % xAxisList.size());
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }

        });

        CombinedData data = new CombinedData();


        data.setData(generateBarData());
        data.setData(generateLineData());

        xAxis.setAxisMaxValue(data.getXMax() + getResources().getDimension(R.dimen.additions_to_max_sizeLine));

        mChart.setData(data);
        mChart.invalidate();
    }

    private LineData generateLineData() {

        LineData d = new LineData();

        d.addDataSet(generateLineData12Q());
        d.addDataSet(generateLineData3Q());
        d.addDataSet(generateLineData1Q());
        d.addDataSet(generateLineDataNorm());

        return d;
    }

    private LineDataSet generateLineDataNorm() {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(0, 0));
        for (SalesUGKDTO salesUGKDTO: salesUGK)
            if(salesUGKDTO.getTypeData().equals(ConstantsGlobal.NORM)) {
                entries.add(new Entry(salesUGKDTO.getNumberDay(), salesUGKDTO.getValye()));
            }

        LineDataSet set = new LineDataSet(entries, getString(R.string.norm_name));
        set.setColor(Color.BLUE);
        set.setLineWidth(getResources().getDimension(R.dimen.graph_lineWidth));
        set.setCircleColor(Color.BLUE);
        set.setCircleRadius(getResources().getDimension(R.dimen.graph_lineCircleRadius));
        set.setFillColor(Color.BLUE);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(getResources().getDimension(R.dimen.dot_valueTextSize));
        set.setValueTextColor(Color.BLUE);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);

        return set;
    }

    private LineDataSet generateLineData12Q() {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(0, plane_12Q));
        for (SalesUGKDTO salesUGKDTO: salesUGK) {
            if (salesUGKDTO.getTypeData().equals(ConstantsGlobal.PLANE_12Q)) {
                entries.add(new Entry(salesUGKDTO.getNumberDay(), salesUGKDTO.getValye()));
            }
        }


        LineDataSet set = new LineDataSet(entries, getString(R.string.plane_12q_name));
        set.setColor(getResources().getColor(R.color.color_graph_blue));
        set.setDrawValues(false);
        set.setDrawCircles(false);
        set.setLineWidth(getResources().getDimension(R.dimen.graph_minlineWidth));
        set.setDrawFilled(true);
        set.setFillColor(getResources().getColor(R.color.color_graph_blue));
        set.setFillAlpha(100);

        return set;
    }

    private LineDataSet generateLineData3Q() {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(0, plane_3Q));
        for (SalesUGKDTO salesUGKDTO: salesUGK)
            if(salesUGKDTO.getTypeData().equals(ConstantsGlobal.PLANE_3Q)) {
                entries.add(new Entry(salesUGKDTO.getNumberDay()-1, salesUGKDTO.getValye()));
            }

        LineDataSet set = new LineDataSet(entries, getString(R.string.plane_3q_name));
        set.setColor(getResources().getColor(R.color.color_graph_yellow));
        set.setDrawValues(false);
        set.setDrawCircles(false);
        set.setLineWidth(getResources().getDimension(R.dimen.graph_minlineWidth));
        set.setDrawFilled(true);
        set.setFillColor(getResources().getColor(R.color.color_graph_yellow));
        set.setFillAlpha(100);

        return set;
    }

    private LineDataSet generateLineData1Q() {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(0, plane_1Q));
        for (SalesUGKDTO salesUGKDTO: salesUGK)
            if(salesUGKDTO.getTypeData().equals(ConstantsGlobal.PLANE_1Q)) {
                entries.add(new Entry(salesUGKDTO.getNumberDay()-1, salesUGKDTO.getValye()));
            }

        LineDataSet set = new LineDataSet(entries, getString(R.string.plane_1q_name));
        set.setColor(getResources().getColor(R.color.color_graph_pink));
        set.setDrawValues(false);
        set.setDrawCircles(false);
        set.setLineWidth(getResources().getDimension(R.dimen.graph_minlineWidth));
        set.setDrawFilled(true);
        set.setFillColor(getResources().getColor(R.color.color_graph_pink));
        set.setFillAlpha(100);

        return set;
    }



    private BarData generateBarData() {

        ArrayList<BarEntry> entries1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> entries2 = new ArrayList<BarEntry>();

        for (SalesUGKDTO salesUGKDTO: salesUGK)
            if(salesUGKDTO.getTypeData().equals(ConstantsGlobal.FACT)) {
                entries1.add(new BarEntry(1, salesUGKDTO.getValye()));
            }

        BarDataSet set1 = new BarDataSet(entries1, getString(R.string.fact_name));
        set1.setColor(Color.RED);
        set1.setValueTextColor(Color.RED);
        set1.setValueTextSize(getResources().getDimension(R.dimen.dot_valueTextSize));
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);

        BarDataSet set2 = new BarDataSet(entries2, "");

        float groupSpace = getResources().getDimension(R.dimen.zero_size);
        float barSpace = getResources().getDimension(R.dimen.zero_size); // x2 dataset
        float barWidth = getResources().getDimension(R.dimen.barWidth_middle); // x2 dataset
        // (0.45 + 0.02) * 2 + 0.06 = 1.00 -> interval per "group"

        BarData d = new BarData(set1, set2);
        d.setBarWidth(barWidth);

        // make this BarData object grouped
        d.groupBars(0, groupSpace, barSpace); // start at x = 0

        return d;
    }



    protected float getRandom(float range, float startsfrom) {
        return (float) (Math.random() * range) + startsfrom;
    }
}
