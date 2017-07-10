package com.metashipanwf.androidsummarize.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.metashipanwf.androidsummarize.R;
import com.metashipanwf.androidsummarize.activity.AndriodOfBaseActivity;
import com.metashipanwf.androidsummarize.activity.UIActivity;

/**
 * author:AnWanfei
 * time:2016/11/4.
 * Email:anwanfei_sp@163.com
 * function：Android基础知识
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private Button btn_ui;
    private Button btn_zujian;
    private Button btn_base;
    private TextView tv_title;

    @Override
    protected View initView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_home, null);
        btn_ui = (Button) view.findViewById(R.id.btn_ui);
        btn_zujian = (Button) view.findViewById(R.id.btn_assembly);
        btn_base = (Button) view.findViewById(R.id.btn_base);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();

        tv_title.setText("安卓基础");

        btn_ui.setOnClickListener(this);
        btn_base.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ui:
                startActivity(new Intent(getActivity(), UIActivity.class));
                break;
            case R.id.btn_base:
                startActivity(new Intent(getActivity(), AndriodOfBaseActivity.class));
                break;
        }
    }
}
