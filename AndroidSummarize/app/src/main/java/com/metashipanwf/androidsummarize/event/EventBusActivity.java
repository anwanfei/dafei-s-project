package com.metashipanwf.androidsummarize.event;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.metashipanwf.androidsummarize.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventBusActivity extends Activity implements View.OnClickListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.bt_eventbus_send)
    Button btEventbusSend;
    @BindView(R.id.bt_eventbus_sticky)
    Button btEventbusSticky;
    @BindView(R.id.tv_eventbus_result)
    TextView tvEventbusResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        setContentView(R.layout.activity_event_bus);
        ButterKnife.bind(this);


        initData();

        initListener();
    }

    private void initData() {
        //注册eventbus
        EventBus.getDefault().register(this);
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText("EventBus页面");

    }

    private void initListener() {
        btEventbusSend.setOnClickListener(this);
        btEventbusSticky.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_eventbus_send:
                Intent intent = new Intent(EventBusActivity.this, EventBusSendActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_eventbus_sticky:
                EventBus.getDefault().postSticky(new StickyEvent("我是粘性事件"));
                break;
        }
    }

    // 5接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MesssageEventBus(MessageEvent event){

        // 显示接收的消息
        tvEventbusResult.setText(event.name+"从"+event.password);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
