package com.anwanfei.anfly.summary.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;

import com.anwanfei.anfly.R;
import com.anwanfei.anfly.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class CountDownTimerActivity extends BaseActivity {

    @BindView(R.id.btn_countdown_timer)
    Button btnCountdownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_count_down_timer;
    }

    @OnClick(R.id.btn_countdown_timer)
    public void onViewClicked() {
        /**
         * 参数一：总共时间
         * 参数二：设置计时的速度
         *
         * onTick：显示剩余的时间
         * onfinish：倒计时结束
         */
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                btnCountdownTimer.setEnabled(false);
                btnCountdownTimer.setText("已发送(" + l / 1000 + ")");
            }

            @Override
            public void onFinish() {
                btnCountdownTimer.setEnabled(true);
                btnCountdownTimer.setText("重新获取验证码");
            }
        }.start();
    }
}
