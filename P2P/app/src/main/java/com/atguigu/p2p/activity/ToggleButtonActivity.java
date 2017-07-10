package com.atguigu.p2p.activity;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.atguigu.p2p.R;
import com.atguigu.p2p.common.BaseActivity;
import com.atguigu.p2p.util.UIUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class ToggleButtonActivity extends BaseActivity {


    @Bind(R.id.iv_common_back)
    ImageView ivCommonBack;
    @Bind(R.id.tv_common_title)
    TextView tvCommonTitle;
    @Bind(R.id.iv_common_setting)
    ImageView ivCommonSetting;
    @Bind(R.id.toggle_btn)
    ToggleButton toggleBtn;

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
        //设置选中状态改变的监听
        toggleBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    UIUtils.toast("开启手势密码功能",false);
                }else{
                    UIUtils.toast("关闭手势密码功能",false);
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_toggle_button;
    }


}
