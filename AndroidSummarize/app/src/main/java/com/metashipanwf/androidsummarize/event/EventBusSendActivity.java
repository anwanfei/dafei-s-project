package com.metashipanwf.androidsummarize.event;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.metashipanwf.androidsummarize.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventBusSendActivity extends Activity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.bt_eventbus_send_main)
    Button btEventbusSendMain;
    @BindView(R.id.bt_eventbus_send_sticky)
    Button btEventbusSendSticky;
    @BindView(R.id.tv_eventbus_send_result)
    TextView tvEventbusSendResult;
    private boolean isFirstFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_send);
        ButterKnife.bind(this);

        initData();

        initListener();
    }

    private void initListener() {

        btEventbusSendMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 4 发送消息
                EventBus.getDefault().post(new MessageEvent("安万飞", "主线程发送过来的数据"));
                // 结束当前页面
                finish();
            }
        });
        btEventbusSendSticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFirstFlag) {

                    isFirstFlag = false;

                    // 4 注册
                    EventBus.getDefault().register(EventBusSendActivity.this);
                }
            }
        });
    }

    private void initData() {
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText("EventBus发送数据页面");
    }

    //注意,和之前的方法一样,只是多了一个 sticky = true 的属性.
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(StickyEvent event) {
        tvEventbusSendResult.setText(event.msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 5 解注册
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(EventBusSendActivity.this);
    }
}
