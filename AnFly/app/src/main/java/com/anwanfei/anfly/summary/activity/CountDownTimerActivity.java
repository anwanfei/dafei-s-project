package com.anwanfei.anfly.summary.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anwanfei.anfly.R;
import com.anwanfei.anfly.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class CountDownTimerActivity extends BaseActivity {


    @BindView(R.id.tv_countdown_timer)
    TextView tvCountdownTimer;
    @BindView(R.id.tv_toast)
    TextView tvToast;

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

    public void onViewClicked() {

    }

    @OnClick({R.id.tv_countdown_timer, R.id.tv_toast})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_countdown_timer:
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
                        tvCountdownTimer.setEnabled(false);
                        tvCountdownTimer.setText("已发送(" + l / 1000 + ")");
                    }

                    @Override
                    public void onFinish() {
                        tvCountdownTimer.setEnabled(true);
                        tvCountdownTimer.setText("重新获取验证码");
                    }
                }.start();
                break;
            case R.id.tv_toast:
                Toast toast = new Toast(this);
                View inflate = LinearLayout.inflate(this, R.layout.layout_toast, null);
                TextView tv_tosat = (TextView) inflate.findViewById(R.id.tv_tosat);
                tv_tosat.setText("自定义Toast");
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(inflate);
                toast.show();
                break;
        }
    }
}
