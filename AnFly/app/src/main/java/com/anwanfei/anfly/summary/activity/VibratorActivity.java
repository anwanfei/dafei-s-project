package com.anwanfei.anfly.summary.activity;

import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.anwanfei.anfly.R;
import com.anwanfei.anfly.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class VibratorActivity extends BaseActivity {

    @BindView(R.id.btn_short)
    Button btnShort;
    @BindView(R.id.btn_long)
    Button btnLong;
    @BindView(R.id.btn_rhythm)
    Button btnRhythm;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private Vibrator vibrator;
    private boolean isChecked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.test_vibrator));
    }

    @Override
    protected void initData() {
        vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_vibrator;
    }

    @OnClick({R.id.btn_short, R.id.btn_long, R.id.btn_rhythm, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_short:
                shortVibrator();
                break;
            case R.id.btn_long:
                longVibrator();
                break;
            case R.id.btn_rhythm:
                rtythmVibrator();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void rtythmVibrator() {
        if (isChecked) {
            // 设置震动周期
            vibrator.vibrate(new long[]{1000, 10, 1000, 10, 1000}, 0);
            isChecked = false;
            btnRhythm.setText(getResources().getString(R.string.short_ibrator) + "(停)");
        } else {
            // 取消震动
            vibrator.cancel();
            isChecked = true;
            btnRhythm.setText(getResources().getString(R.string.short_ibrator) + "(启)");
        }
    }

    //第一个参数为设置震动的效果的数组，第一个参数为等待指定时间后开始震动，震动时间为第二个参数。后边的参数依次为等待震动时间和震动的时间
    // 第二个参数为 -1表示只震动一次，为0则震动会一直持续。
    private void longVibrator() {
        if (isChecked) {
            // 设置震动周期
            vibrator.vibrate(new long[]{1, 1000, 1, 1000}, 0);
            isChecked = false;
            btnLong.setText(getResources().getString(R.string.short_ibrator) + "(停)");
        } else {
            // 取消震动
            vibrator.cancel();
            isChecked = true;
            btnLong.setText(getResources().getString(R.string.short_ibrator) + "(启)");
        }
    }

    private void shortVibrator() {
        if (isChecked) {
            // 设置震动周期
            vibrator.vibrate(new long[]{1000, 10, 100, 1000}, -1);
            isChecked = false;
            btnShort.setText(getResources().getString(R.string.short_ibrator) + "(停)");
        } else {
            // 取消震动
            vibrator.cancel();
            isChecked = true;
            btnShort.setText(getResources().getString(R.string.short_ibrator) + "(启)");
        }
    }
}
