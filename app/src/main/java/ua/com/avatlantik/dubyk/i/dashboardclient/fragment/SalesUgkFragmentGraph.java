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

/**
 * Created by i.dubyk on 24.06.2016.
 */
public class SalesUgkFragmentGraph extends Fragment{
    private static  final int LAYOUT = R.layout.fragment_sales_graph;
    private View view;
    private CombinedChart mChart;
    private final int itemcount = 12;
    private ArrayList<String> mMonths;

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

        final ArrayList<String> mMonths = new ArrayList<>();
        mMonths.add("JAN");
        mMonths.add("FEB");
        mMonths.add("MAR");
        mMonths.add("APR");
        mMonths.add("MAY");
        mMonths.add("JUN");
        mMonths.add("JUL");
        mMonths.add("AUG");
        mMonths.add("SEP");
        mMonths.add("OKT");
        mMonths.add("NOV");
        mMonths.add("DEC");

        mChart = (CombinedChart) view.findViewById(R.id.chart);
        mChart.setDescription("");
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(false);

        mChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE
        });

        mChart.animateY(ConstantsGlobal.MAX_TIME);

        Legend l = mChart.getLegend();
        l.setWordWrapEnabled(true);
        l.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        l.setTextSize(15f);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(true);
        rightAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)
        rightAxis.setTextSize(20f);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawAxisLine(true);
        leftAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)
        leftAxis.setEnabled(false);


        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinValue(0f);
        xAxis.setGranularity(1f);
        xAxis.setTextSize(20f);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(mMonths.size());
        xAxis.setValueFormatter(new AxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mMonths.get((int) value % mMonths.size());
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }

        });

        CombinedData data = new CombinedData();

        data.setData(generateLineData());
        data.setData(generateBarData());

        xAxis.setAxisMaxValue(data.getXMax() + 0.25f);

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
        for (int index = 1; index < itemcount; index++)
            entries.add(new Entry(index, getRandom(15, 5)));

        LineDataSet set = new LineDataSet(entries, getString(R.string.norm_name));
        set.setColor(Color.BLUE);
        set.setLineWidth(4f);
        set.setCircleColor(Color.BLUE);
        set.setCircleRadius(4f);
        set.setFillColor(Color.BLUE);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(15f);
        set.setValueTextColor(Color.BLUE);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);

        return set;
    }

    private LineDataSet generateLineData12Q() {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(0, 55));
        for (int index = 0; index < itemcount; index++)
            entries.add(new Entry(index + 0.5f, 55));


        LineDataSet set = new LineDataSet(entries, getString(R.string.plane_12q_name));
        set.setColor(Color.BLUE);
        set.setDrawValues(false);
        set.setDrawCircles(false);
        set.setLineWidth(1f);
        set.setDrawFilled(true);
        set.setFillColor(Color.BLUE);

        return set;
    }

    private LineDataSet generateLineData3Q() {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(0, 42));
        for (int index = 0; index < itemcount; index++)
            entries.add(new Entry(index + 0.5f, 42));

        LineDataSet set = new LineDataSet(entries, getString(R.string.plane_3q_name));
        set.setColor(Color.GREEN);
        set.setDrawValues(false);
        set.setDrawCircles(false);
        set.setLineWidth(1f);
        set.setDrawFilled(true);
        set.setFillColor(Color.GREEN);
        set.setFillAlpha(100);

        return set;
    }

    private LineDataSet generateLineData1Q() {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(0, 36));
        for (int index = 0; index < itemcount; index++)
            entries.add(new Entry(index + 0.5f, 36));

        LineDataSet set = new LineDataSet(entries, getString(R.string.plane_1q_name));
        set.setColor(Color.YELLOW);
        set.setDrawValues(false);
        set.setDrawCircles(false);
        set.setLineWidth(1f);
        set.setDrawFilled(true);
        set.setFillColor(Color.YELLOW);
        set.setFillAlpha(100);

        return set;
    }



    private BarData generateBarData() {


        ArrayList<BarEntry> entries1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> entries2 = new ArrayList<BarEntry>();

        for (int index = 0; index < itemcount; index++) {
            entries1.add(new BarEntry(0, getRandom(25, 25)));
        }

        BarDataSet set1 = new BarDataSet(entries1, "Bar 1");
        set1.setColor(Color.RED);
        set1.setValueTextColor(Color.RED);
        set1.setValueTextSize(15f);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);

        BarDataSet set2 = new BarDataSet(entries2, "");

        float groupSpace = 0.06f;
        float barSpace = 0.02f; // x2 dataset
        float barWidth = 0.45f; // x2 dataset
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
