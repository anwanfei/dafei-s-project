package com.lingling.anwanfei.android.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.lingling.anwanfei.android.R;
import com.lingling.anwanfei.android.activity.RegisterActivity;

/**
 * 作者：安万飞
 * 时间： 2016/11/2
 * 邮箱：anwanfei_sp@163.com
 * QQ:546513287
 */
public class MyFragment extends BaseFragment {

    private TextView tv_register;
    @Override
    protected View initView() {
        View myView = View.inflate(getActivity(), R.layout.myfragment,null);
        tv_register = (TextView) myView.findViewById(R.id.tv_register);
        return myView;
    }

    @Override
    protected void initData() {
        super.initData();

        initListener();
    }

    private void initListener() {
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


}
