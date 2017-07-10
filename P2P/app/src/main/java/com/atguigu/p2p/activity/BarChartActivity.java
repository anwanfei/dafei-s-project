package com.atguigu.p2p.activity;

import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.common.BaseActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class BarChartActivity extends BaseActivity {
    private Typeface mTf;

    @Bind(R.id.iv_common_back)
    ImageView ivCommonBack;
    @Bind(R.id.tv_common_title)
    TextView tvCommonTitle;
    @Bind(R.id.iv_common_setting)
    ImageView ivCommonSetting;
    @Bind(R.id.chart)
    BarChart chart;

    @Override
    protected void initTitle() {
        ivCommonBack.setVisibility(View.VISIBLE);
        ivCommonSetting.setVisibility(View.INVISIBLE);
        tvCommonTitle.setText("折线图示例");
    }

    @OnClick(R.id.iv_common_back)
    public void back(View view) {
        closeCurrentActivity();
    }

    @Override
    protected void initData() {
        //初始化字体
        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        //设置具体的描述信息
        chart.setDescription("王宝强离婚事件关注度");
        //是否绘制网格背景
        chart.setDrawGridBackground(false);
        //是否绘制柱状图阴影
        chart.setDrawBarShadow(false);

        //获取x轴
        XAxis xAxis = chart.getXAxis();
        //设置x轴显示位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置字体集
        xAxis.setTypeface(mTf);
        //是否绘制x轴网格线
        xAxis.setDrawGridLines(false);
        //是否绘制x轴线
        xAxis.setDrawAxisLine(true);

        //获取左边的y轴
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(mTf);
        leftAxis.setLabelCount(5, false);
        //最高的柱状图距离顶端的距离
        leftAxis.setSpaceTop(20f);


        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);//设置右边的y轴不显示
//        rightAxis.setTypeface(mTf);
//        rightAxis.setLabelCount(5, false);
//        rightAxis.setSpaceTop(20f);

        BarData barData = generateDataBar();
        barData.setValueTypeface(mTf);

        // set data
        chart.setData(barData);

        // do not forget to refresh the chart
//        chart.invalidate();
        chart.animateY(700);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bar_chart;
    }

    private BarData generateDataBar() {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int i = 0; i < 12; i++) {
            entries.add(new BarEntry((int) (Math.random() * 70) + 30, i));
        }

        BarDataSet d = new BarDataSet(entries, "New DataSet 1");
        //设置柱状图间的距离
        d.setBarSpacePercent(20f);
        //设置柱状图的颜色
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        //设置高亮的透明度
        d.setHighLightAlpha(100);

        BarData cd = new BarData(getMonths(), d);
        return cd;
    }
    private ArrayList<String> getMonths() {

        ArrayList<String> m = new ArrayList<String>();
        m.add("Jan");
        m.add("Feb");
        m.add("Mar");
        m.add("Apr");
        m.add("May");
        m.add("Jun");
        m.add("Jul");
        m.add("Aug");
        m.add("Sep");
        m.add("Okt");
        m.add("Nov");
        m.add("Dec");

        return m;
    }
}
