package com.metashipanwf.androidsummarize.fragment;

import android.content.Intent;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.metashipanwf.androidsummarize.AnimationInActivity.AnimationInActivity;
import com.metashipanwf.androidsummarize.R;
import com.metashipanwf.androidsummarize.activity.VibratorActivity;
import com.metashipanwf.androidsummarize.badge.BadgeViewActivity;
import com.metashipanwf.androidsummarize.basetest.BasicTestActivity;
import com.metashipanwf.androidsummarize.feedback.FeedbackActivity;
import com.metashipanwf.androidsummarize.guide.GuideActivity;
import com.metashipanwf.androidsummarize.qrCode.QRCodeActivity;
import com.metashipanwf.androidsummarize.slidemenu.DrawerLayoutActivity;

/**
 * author:AnWanfei
 * time:2016/11/4.
 * Email:anwanfei_sp@163.com
 * function：总结常用代码
 */
public class SummarizeFragment extends BaseFragment {

    private ListView listview;
    private TextView tv_title;
    private String[] data = {"引导界面", "意见反馈", "tablayout显示消息提醒", "常用基本操作", "侧滑菜单", "Activity切换过场动画", "启用相机拍照并保存图片", "震动测试",
            "打包签名", "代码混淆", "反编译", "渠道打包", "二维码", "机械学习", "", "", "", "", "", ""};


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

        tv_title.setText("总结");


        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), R.layout.item_summarize, data);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String string = data[i];
                switch (string) {
                    case "引导界面":
                        startActivity(new Intent(getActivity(), GuideActivity.class));
                        break;
                    case "意见反馈":
                        startActivity(new Intent(getActivity(), FeedbackActivity.class));
                        break;
                    case "常用基本操作":
                        startActivity(new Intent(getActivity(), BasicTestActivity.class));
                        break;
                    case "侧滑菜单":
                        startActivity(new Intent(getActivity(), DrawerLayoutActivity.class));
                        break;
                    case "Activity切换过场动画":
                        startActivity(new Intent(getActivity(), AnimationInActivity.class));
                        break;
                    case "启用相机拍照并保存图片":
                        startActivity(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
                        break;
                    case "震动测试":
                        startActivity(new Intent(getActivity(), VibratorActivity.class));
                        break;
                    case "二维码":
                        startActivity(new Intent(getActivity(), QRCodeActivity.class));
                        break;
                    case "机械学习":
                        startActivity(new Intent(getActivity(), QRCodeActivity.class));
                        break;
                    case "tablayout显示消息提醒":
                        startActivity(new Intent(getActivity(), BadgeViewActivity.class));
                        break;

                }
            }
        });

    }


}
