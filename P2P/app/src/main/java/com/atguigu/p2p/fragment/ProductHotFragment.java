package com.atguigu.p2p.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.p2p.R;
import com.atguigu.p2p.ui.FlowLayout;
import com.atguigu.p2p.util.DrawableUtil;
import com.atguigu.p2p.util.UIUtils;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shkstart on 2016/8/15 0015.
 */
public class ProductHotFragment extends Fragment {

    @Bind(R.id.fl_product_hot)
    FlowLayout flProductHot;

    private String[] datas = new String[]{"新手计划", "乐享活系列90天计划", "钱包", "30天理财计划(加息2%)",
            "林业局投资商业经营与大捞一笔", "中学老师购买车辆", "屌丝下海经商计划", "新西游影视拍", "Java培训老师自己周转", "HelloWorld", "C++-C-ObjectC-java", "Android vs ios", "算法与数据结构", "JNI与NDK", "team working"};
    private Random random;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        View view = UIUtils.getXmlView(R.layout.fragment_product_hot);
        View view = View.inflate(getActivity(), R.layout.fragment_product_hot, null);
        ButterKnife.bind(this, view);

        initData();
        return view;

    }

    private void initData() {
        random = new Random();
        for(String data : datas){
            final TextView tv = new TextView(getActivity());

            tv.setText(data);
            ViewGroup.MarginLayoutParams mp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mp.leftMargin = UIUtils.dp2px(10);
            mp.rightMargin = UIUtils.dp2px(10);
            mp.topMargin = UIUtils.dp2px(10);
            mp.bottomMargin = UIUtils.dp2px(10);

            tv.setLayoutParams(mp);

            int red = random.nextInt(211);//[0,211)   Math.random() --> [0,1)  -->[a,b]
            int green = random.nextInt(211);
            int blue = random.nextInt(211);

            //设置背景颜色
//            tv.setBackground(DrawableUtil.getDrawable(Color.rgb(red,green,blue),UIUtils.dp2px(4)));
            tv.setBackground(DrawableUtil.getSelector(DrawableUtil.getDrawable(Color.rgb(red,green,blue),UIUtils.dp2px(4)),DrawableUtil.getDrawable(Color.WHITE,UIUtils.dp2px(4))));

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ProductHotFragment.this.getActivity(),tv.getText(), Toast.LENGTH_SHORT).show();
                }
            });

            //内边距
            int padding = UIUtils.dp2px(4);
            tv.setPadding(padding,padding,padding,padding);
            flProductHot.addView(tv);
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
