package com.atguigu.p2p.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.common.BaseFragment;
import com.loopj.android.http.RequestParams;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shkstart on 2016/8/12 0012.
 */
public class MoreFragment extends BaseFragment {


    @Bind(R.id.iv_common_back)
    ImageView ivCommonBack;
    @Bind(R.id.tv_common_title)
    TextView tvCommonTitle;
    @Bind(R.id.iv_common_setting)
    ImageView ivCommonSetting;
    @Bind(R.id.tv)
    TextView tv;

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected void initData(String content) {
        //实现跑马灯效果的方式一
//        tv.setFocusable(true);
//        tv.setFocusableInTouchMode(true);
//        tv.requestFocus();

    }

    public void initTitle() {
        ivCommonBack.setVisibility(View.GONE);
        ivCommonSetting.setVisibility(View.GONE);

        tvCommonTitle.setText("更多");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_more;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
