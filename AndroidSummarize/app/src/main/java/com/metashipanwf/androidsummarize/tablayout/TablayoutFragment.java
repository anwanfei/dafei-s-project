package com.metashipanwf.androidsummarize.tablayout;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.metashipanwf.androidsummarize.fragment.BaseFragment;

/**
 * author:AnWanfei
 * time:2017/2/28.
 * Email:anwanfei_sp@163.com
 * function:
 */

public class TablayoutFragment extends BaseFragment {

    private String title;
    private String content;
    private TextView textView;

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }


    @Override
    protected View initView() {
        textView = new TextView(getActivity());
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);

        return textView;
    }
    public TablayoutFragment(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    protected void initData() {
        super.initData();
        //设置内容
        textView.setText(content);
    }
}
