package com.atguigu.p2p.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.common.BaseActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class PieChartActivity extends BaseActivity {
    private Typeface mTf;
    @Bind(R.id.iv_common_back)
    ImageView ivCommonBack;
    @Bind(R.id.tv_common_title)
    TextView tvCommonTitle;
    @Bind(R.id.iv_common_setting)
    ImageView ivCommonSetting;
    @Bind(R.id.chart)
    PieChart chart;

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

        chart.setDescription("8月份数据显示");
        //设置内部圆的半径
        chart.setHoleRadius(52f);
        //设置包裹内部圆的外部圆的半径
        chart.setTransparentCircleRadius(57f);
        chart.setCenterText("android\n厂商的占比");
        //设置中间显示的文本的字体集
        chart.setCenterTextTypeface(mTf);
        //设置中间显示的文本的字体大小
        chart.setCenterTextSize(18f);
        //设置是否为百分比的显示
        chart.setUsePercentValues(true);

        //获取产生的pie形式的数据
        PieData pieData = generateDataPie();
        //设置数据的显示格式
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTypeface(mTf);
        //设置具体pie上显示的数据的字体大小
        pieData.setValueTextSize(11f);
        //设置具体pie上显示的数据的字体颜色
        pieData.setValueTextColor(Color.RED);
        // set data
        chart.setData(pieData);

        //获取图示信息
        Legend l = chart.getLegend();
        //显示在右端
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        //显示的几个item在y轴上的间距
        l.setYEntrySpace(0f);
        //最上面的item距离顶端的距离
        l.setYOffset(0f);

        // do not forget to refresh the chart
        // chart.invalidate();
        chart.animateXY(900, 900);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_pie_chart;
    }

    private PieData generateDataPie() {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i < 4; i++) {
            entries.add(new Entry((int) (Math.random() * 70) + 30, i));
        }

        PieDataSet d = new PieDataSet(entries, "");

        //设置具体相邻两块pie之间的距离
        d.setSliceSpace(2f);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);

        PieData cd = new PieData(getQuarters(), d);
        return cd;
    }

    private ArrayList<String> getQuarters() {

        ArrayList<String> q = new ArrayList<String>();
        q.add("samsung");
        q.add("huawei");
        q.add("vivo");
        q.add("oppo");

        return q;
    }
}
