package com.anwanfei.anfly.foundation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anwanfei.anfly.R;
import com.anwanfei.anfly.common.BaseFragment;
import com.anwanfei.anfly.foundation.activity.AndroidAdvanceActivity;
import com.anwanfei.anfly.foundation.activity.AndroidComponentActivity;
import com.anwanfei.anfly.foundation.activity.AndroidEventActivity;
import com.anwanfei.anfly.foundation.activity.AndroidFoundationActivity;
import com.anwanfei.anfly.foundation.activity.AndroidInternetActivity;
import com.anwanfei.anfly.foundation.activity.AndroidInterviewActivity;
import com.anwanfei.anfly.foundation.activity.AndroidPhotoAnimationActivity;
import com.anwanfei.anfly.foundation.activity.AndroidStorageActivity;
import com.anwanfei.anfly.foundation.activity.AndroidUIActivity;
import com.anwanfei.anfly.foundation.adapter.FoundationAdapter;
import com.anwanfei.anfly.foundation.bean.FoundationBean;
import com.anwanfei.anfly.utils.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by anwanfei on 2017/7/28.
 */

public class FoundationFragment extends BaseFragment {
    //    @BindView(R.id.rv_foundation)
//    RecyclerView rvFoundation;
    Unbinder unbinder;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_introduction)
    TextView tvIntroduction;
    @BindView(R.id.tv_assembly)
    TextView tvAssembly;
    @BindView(R.id.tv_andriod_ui)
    TextView tvAndriodUi;
    @BindView(R.id.tv_photo_animation)
    TextView tvPhotoAnimation;
    @BindView(R.id.tv_storage)
    TextView tvStorage;
    @BindView(R.id.tv_internet)
    TextView tvInternet;
    @BindView(R.id.tv_event)
    TextView tvEvent;
    @BindView(R.id.tv_advance)
    TextView tvAdvance;
    @BindView(R.id.tv_foundation_interview)
    LinearLayout tvFoundationInterview;
    private FoundationAdapter foundationAdapter;

    public static List<?> images = new ArrayList<>();

    String[] arrNmaes = {"安卓入门", "安卓组件", "用户界面", "数据存储", "网络数据", "图像动画", "消息事件任务", "新特新", "安卓面试"};

    int[] arrImages = {R.drawable.bg_bar, R.drawable.bg_bar, R.drawable.bg_bar, R.drawable.bg_bar, R.drawable.bg_bar, R.drawable.bg_bar, R.drawable.bg_bar, R.drawable.bg_bar, R.drawable.bg_bar};
    private List<FoundationBean> mLists = new ArrayList<>();

    @Override
    protected View initView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_foundation, null);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initData() {
        super.initData();

        for (int i = 0; i < arrNmaes.length; i++) {
            FoundationBean foundationBean = new FoundationBean();
            foundationBean.setImageview(arrImages[i]);
            foundationBean.setName(arrNmaes[i]);

            mLists.add(foundationBean);
        }

        String[] urls = getResources().getStringArray(R.array.url4);
        List list = Arrays.asList(urls);
        images = new ArrayList(list);
        banner.setImages(images)
                .setImageLoader(new GlideImageLoader())
                .start();

//        rvFoundation.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        foundationAdapter = new FoundationAdapter(getActivity(), mLists);
//        rvFoundation.setLayoutManager(new LinearLayoutManager(getActivity()));
//        rvFoundation.setAdapter(foundationAdapter);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_introduction, R.id.tv_assembly, R.id.tv_andriod_ui, R.id.tv_photo_animation, R.id.tv_storage, R.id.tv_internet, R.id.tv_event, R.id.tv_advance, R.id.tv_foundation_interview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_introduction:
                startActivity(new Intent(getActivity(), AndroidFoundationActivity.class));
                break;
            case R.id.tv_assembly:
                startActivity(new Intent(getActivity(), AndroidComponentActivity.class));
                break;
            case R.id.tv_andriod_ui:
                startActivity(new Intent(getActivity(), AndroidUIActivity.class));
                break;
            case R.id.tv_photo_animation:
                startActivity(new Intent(getActivity(), AndroidPhotoAnimationActivity.class));
                break;
            case R.id.tv_storage:
                startActivity(new Intent(getActivity(), AndroidStorageActivity.class));
                break;
            case R.id.tv_internet:
                startActivity(new Intent(getActivity(), AndroidInternetActivity.class));
                break;
            case R.id.tv_event:
                startActivity(new Intent(getActivity(), AndroidEventActivity.class));
                break;
            case R.id.tv_advance:
                startActivity(new Intent(getActivity(), AndroidAdvanceActivity.class));
                break;
            case R.id.tv_foundation_interview:
                startActivity(new Intent(getActivity(), AndroidInterviewActivity.class));
                break;
        }
    }
}
