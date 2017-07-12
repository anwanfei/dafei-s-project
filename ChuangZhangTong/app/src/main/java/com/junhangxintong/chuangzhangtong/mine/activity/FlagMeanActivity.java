package com.junhangxintong.chuangzhangtong.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class FlagMeanActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_flag_mean)
    TextView tvFlagMean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.flag_mean));
    }

    @Override
    protected void initData() {
        tvFlagMean.setText("App中地图上的图形代表的含义如下：\n" + "\n" + "三角形代表航行的船，方形代表停在原地的船只。\n" + "三角形：实线表示有明确船首向的船；虚线表示未知船首向的船。\n" +
                "            灰色：代表未做任何标记的船；\n" +
                "            橘黄色：代表我关注的船；\n" +
                "            蓝色：代表我的船队的船。\n" +
                "\n" +
                "正方形：黄色：代表停在原地的船。");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_flag_mean;
    }

    @OnClick({R.id.iv_back, R.id.tv_flag_mean})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_flag_mean:
                break;
        }
    }
}
