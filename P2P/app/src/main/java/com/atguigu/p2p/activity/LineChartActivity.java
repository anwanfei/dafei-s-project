package com.atguigu.p2p.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.common.BaseActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class LineChartActivity extends BaseActivity {

    private Typeface mTf;
    @Bind(R.id.iv_common_back)
    ImageView ivCommonBack;
    @Bind(R.id.tv_common_title)
    TextView tvCommonTitle;
    @Bind(R.id.iv_common_setting)
    ImageView ivCommonSetting;
    @Bind(R.id.chart)
    LineChart chart;

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
        //设置描述信息
        chart.setDescription("尚硅谷股票走势");
        //是否绘制网格的背景
        chart.setDrawGridBackground(false);

        //设置x轴
        //获取x轴
        XAxis xAxis = chart.getXAxis();
        //设置x轴显示位置
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        //设置字体集
        xAxis.setTypeface(mTf);
        //是否绘制x轴的网格线
        xAxis.setDrawGridLines(false);
        //是否绘制x轴线
        xAxis.setDrawAxisLine(false);

        //获取左边的y轴
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(mTf);
        //参数1：设置y轴显示的节点的个数
        //参数2：是否要均匀分布
        leftAxis.setLabelCount(5, false);

        //获取右边的y轴
        YAxis rightAxis = chart.getAxisRight();
        //设置y轴是否显示
        rightAxis.setEnabled(false);
//        rightAxis.setTypeface(mTf);
//        rightAxis.setLabelCount(5, false);
//        rightAxis.setDrawGridLines(false);

        // set data
        chart.setData(generateDataLine());
        // do not forget to refresh the chart
        // chart.invalidate();
        chart.animateX(750);


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_line_chart;
    }

    //返回具体的折线数据
    private LineData generateDataLine() {

        ArrayList<Entry> e1 = new ArrayList<Entry>();

        for (int i = 0; i < 12; i++) {
            e1.add(new Entry((int) (Math.random() * 65) + 40, i));
        }

        //创建折线一
        LineDataSet d1 = new LineDataSet(e1, "New DataSet 1");
        //设置折线的宽度
        d1.setLineWidth(2.5f);
        //设置小圆点的尺寸
        d1.setCircleSize(4.5f);
        //设置高亮的颜色：选中折线时显示的颜色
        d1.setHighLightColor(Color.rgb(244, 117, 117));
        //是否设置小圆点对应的数值
        d1.setDrawValues(true);



        ArrayList<LineDataSet> sets = new ArrayList<LineDataSet>();
        sets.add(d1);

        LineData cd = new LineData(getMonths(), sets);
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
