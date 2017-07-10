package com.metashipanwf.androidsummarize.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.metashipanwf.androidsummarize.DialogActivity.DialogActivity;
import com.metashipanwf.androidsummarize.R;
import com.metashipanwf.androidsummarize.activity.ChangeFontActivity;
import com.metashipanwf.androidsummarize.activity.MainActivity;
import com.metashipanwf.androidsummarize.event.EventBusActivity;
import com.metashipanwf.androidsummarize.retrofit.CallActivity;
import com.metashipanwf.androidsummarize.tablayout.TablayoutActivity;

/**
 * author:AnWanfei
 * time:2016/11/4.
 * Email:anwanfei_sp@163.com
 * function：第三方框架的使用：
 */
public class DisanfangFragment extends BaseFragment {

    private ListView listview;
    private TextView tv_title;
    private String[] data = {"Eventbus", "TabLayout","Banner","发送手机验证码","练习界面","属性动画",
            "Behavioir","Opency","状态栏沉浸式","Otto","Dagger2","ReactNative","线程池","新特性","下拉刷新",
            "PopupWindow","Retrofit","RxJava","CountdownView","OpenDanmaku弹幕","GreenDao","字体改变","","","",""};

    @Override
    protected View initView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.summarize_layout, null);
        listview = (ListView) view.findViewById(R.id.listview);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();

        tv_title.setText("第三方");

        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), R.layout.item_summarize, data);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String string = data[i];
                switch (string) {
                    case "Eventbus":
                        startActivity(new Intent(getActivity(), EventBusActivity.class));
                        break;
                    case "TabLayout":
                        startActivity(new Intent(getActivity(), TablayoutActivity.class));
                        break;
                    case "Banner":
                        startActivity(new Intent(getActivity(), EventBusActivity.class));
                        break;
                    case "发送手机验证码":
                        startActivity(new Intent(getActivity(), MainActivity.class));
                        break;
                    case "练习界面":
                        startActivity(new Intent(getActivity(), DialogActivity.class));
                        break;
                    case "Retrofit":
                        startActivity(new Intent(getActivity(), CallActivity.class));
                        break;
                    case "字体改变":
                        startActivity(new Intent(getActivity(), ChangeFontActivity.class));
                        break;


                }
            }
        });
   }
}
