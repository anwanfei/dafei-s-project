package com.anwanfei.anfly.summary.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.anwanfei.anfly.R;
import com.anwanfei.anfly.common.BaseFragment;
import com.anwanfei.anfly.utils.ReverseBitmap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by anwanfei on 2017/8/1.
 */

public class TablayoutFragment extends BaseFragment {
    @BindView(R.id.imageview)
    ImageView imageview;
    Unbinder unbinder;

    @Override
    protected View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_tablayout, null);
        return view;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        Bitmap reverseBitmapById = ReverseBitmap.getReverseBitmapById(getActivity(), R.drawable.bg, (float) 0.5);
        imageview.setImageBitmap(reverseBitmapById);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
