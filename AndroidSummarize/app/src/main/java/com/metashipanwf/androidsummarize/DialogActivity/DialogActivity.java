package com.metashipanwf.androidsummarize.DialogActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.metashipanwf.androidsummarize.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogActivity extends Activity {


    @BindView(R.id.vp_dialog)
    ViewPager vpDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise);
        ButterKnife.bind(this);
    }
}
