package com.metashipanwf.androidsummarize.DialogActivity;

import android.view.View;

import com.metashipanwf.androidsummarize.fragment.BaseFragment;

/**
 * author:AnWanfei
 * time:2017/2/28.
 * Email:anwanfei_sp@163.com
 * function:
 */

public class DialogFragment extends BaseFragment{

    private String title;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    protected View initView() {
        return null;
    }

    @Override
    protected void initData() {
        super.initData();
    }
}
