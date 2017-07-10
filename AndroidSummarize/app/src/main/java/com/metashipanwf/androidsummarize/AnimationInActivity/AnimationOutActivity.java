package com.metashipanwf.androidsummarize.AnimationInActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.metashipanwf.androidsummarize.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimationOutActivity extends Activity implements View.OnClickListener {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.activity_animation_out)
    RelativeLayout activityAnimationOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_animation_out);
        ButterKnife.bind(this);

        initData();
        initListener();
    }

    private void initListener() {
        tvBack.setOnClickListener(this);
    }

    private void initData() {
        tvBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getString(R.string.AnimationInActivity));
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
