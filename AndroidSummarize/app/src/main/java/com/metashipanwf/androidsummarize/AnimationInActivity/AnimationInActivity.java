package com.metashipanwf.androidsummarize.AnimationInActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.metashipanwf.androidsummarize.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimationInActivity extends Activity implements View.OnClickListener {


    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.animation_sp)
    Spinner animationSp;
    @BindView(R.id.other_button)
    Button otherButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_animation_in);
        ButterKnife.bind(this);

        initData();
        initListener();

    }

    private void initListener() {
        tvBack.setOnClickListener(this);
        otherButton.setOnClickListener(this);
    }

    private void initData() {
        tvBack.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(getString(R.string.AnimationInActivity));

        String[] ls = getResources().getStringArray(R.array.anim_type);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < ls.length; i++) {
            list.add(ls[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        animationSp.setAdapter(adapter);
        animationSp.setSelection(0);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.other_button:
                showAnimation();
                break;
        }
    }

    private void showAnimation() {
        startActivity(new Intent(AnimationInActivity.this, AnimationOutActivity.class));

        switch (animationSp.getSelectedItemPosition()) {
            case 0:
                //淡入淡出效果
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                //Android内置的
                //overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                break;
            case 1:
                //放大淡出效果
                overridePendingTransition(R.anim.scale_in, R.anim.alpha_out);
                break;
            case 2:
                //转动淡出效果1
                overridePendingTransition(R.anim.scale_rotate_in, R.anim.alpha_out);
                break;
            case 3:
                //转动淡出效果2
                overridePendingTransition(R.anim.scale_translate_rotate, R.anim.alpha_out);
                break;
            case 4:
                //左上角展开淡出效果
                overridePendingTransition(R.anim.scale_translate, R.anim.alpha_out);
                break;
            case 5:
                //压缩变小淡出效果
                overridePendingTransition(R.anim.hyperspace_in, R.anim.hyperspace_out);
                break;
            case 6:
                //右往左推出效果
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                //Android内置的
                //overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                break;
            case 7:
                //下往上推出效果
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                break;
            case 8:
                //左右交错效果
                overridePendingTransition(R.anim.slide_left,
                        R.anim.slide_right);
                break;
            case 9:
                //放大淡出效果
                overridePendingTransition(R.anim.wave_scale, R.anim.alpha_out);
                break;
            case 10:
                //缩小效果
                overridePendingTransition(R.anim.zoom_enter,
                        R.anim.zoom_exit);
                break;
            case 11:
                //上下交错效果
                overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
                break;
        }
    }
}
