package com.metashipanwf.androidsummarize.countdown;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.metashipanwf.androidsummarize.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CountDownTimerActivity extends AppCompatActivity {

    @BindView(R.id.btn_count_down_timer)
    Button btnCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_timer);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_count_down_timer)
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
                btnCountDownTimer.setEnabled(false);
                btnCountDownTimer.setText("已发送(" + l / 1000 + ")");
            }

            @Override
            public void onFinish() {
                btnCountDownTimer.setEnabled(true);
                btnCountDownTimer.setText("重新获取验证码");
            }
        }.start();
    }
}
