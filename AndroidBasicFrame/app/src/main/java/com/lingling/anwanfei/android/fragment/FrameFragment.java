package com.lingling.anwanfei.android.fragment;

import android.view.View;
import android.widget.TextView;

import com.lingling.anwanfei.android.R;

/**
 * 作者：安万飞
 * 时间： 2016/11/2
 * 邮箱：anwanfei_sp@163.com
 * QQ:546513287
 */
public class FrameFragment extends BaseFragment {

    private TextView tv_foundation;

    @Override
    protected View initView() {
        View foundationView = View.inflate(getActivity(), R.layout.foundation_fragment, null);
        tv_foundation = (TextView) foundationView.findViewById(R.id.tv_foundation);
        return foundationView;
    }

    @Override
    protected void initData() {
        super.initData();
        tv_foundation.setText(getResources().getString(R.string.frame));
    }
}
