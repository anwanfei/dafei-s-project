package com.metashipanwf.androidsummarize.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.metashipanwf.androidsummarize.R;
import com.metashipanwf.androidsummarize.utils.FontUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeFontActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_font);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        FontUtil.changeFont(this, button, "lxk.ttf", 20, Color.BLACK);

    }
}
