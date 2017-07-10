package com.atguigu.p2p.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.p2p.R;
import com.atguigu.p2p.ui.randomLayout.StellarMap;
import com.atguigu.p2p.util.UIUtils;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shkstart on 2016/8/15 0015.
 */
public class ProductRecommondFragment extends Fragment {

    @Bind(R.id.sm_product_recommond)
    StellarMap smProductRecommond;

    private String[] datas = new String[]{"超级新手计划", "乐享活系列90天计划", "钱包计划", "30天理财计划(加息2%)", "90天理财计划(加息5%)", "180天理财计划(加息10%)",
            "林业局投资商业经营", "中学老师购买车辆", "屌丝下海经商计划", "新西游影视拍摄投资", "Java培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "阿里巴巴洗钱计划", "铁路局回款计划", "高级白领赢取白富美投资计划"
    };
    private Random random;

    private String[] ones = new String[datas.length / 2];
    private String[] twos = new String[datas.length / 2];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = UIUtils.getXmlView(R.layout.fragment_product_recommond);
        ButterKnife.bind(this, view);

        initData();
        return view;

    }

    MyAdapter adapter;

    private void initData() {
        for(int i = 0; i < datas.length; i++) {
            if(i < datas.length / 2){
                ones[i] = datas[i];
            }else{
                twos[i - datas.length / 2] = datas[i];
            }
        }


        random = new Random();
        adapter = new MyAdapter();
        int padding = UIUtils.dp2px(5);
        smProductRecommond.setInnerPadding(padding,padding,padding,padding);

        smProductRecommond.setAdapter(adapter);

        //必须调用如下的两个方法方可显示数据
        smProductRecommond.setRegularity(8,8);
        smProductRecommond.setGroup(0,true);//显示group= 0，显示动画

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    class MyAdapter implements StellarMap.Adapter {

        //显示分组的个数
        @Override
        public int getGroupCount() {
            return 2;
        }

        //每组显示的个数
        @Override
        public int getCount(int group) {
            return 8;
        }

        @Override
        public View getView(int group, int position, View convertView) {
            Log.e("TAG", "group = " + group);
            final TextView tv = new TextView(getActivity());

            if(group == 0){
                tv.setText(ones[position]);
            }else{
                tv.setText(twos[position]);
            }

            tv.setTextSize(UIUtils.dp2px(8) + random.nextInt(8));//设置字体大小
            int red = random.nextInt(211);
            int green = random.nextInt(211);
            int blue = random.nextInt(211);
            tv.setTextColor(Color.rgb(red,green,blue));

            //设置点击事件
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), tv.getText(), Toast.LENGTH_SHORT).show();
                }
            });


            return tv;
        }

        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }
        //接下来显示的组别
        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            if(group == 1){
                group = 0;
            }else{
                group = 1;
            }
            return group;

        }
    }
}
