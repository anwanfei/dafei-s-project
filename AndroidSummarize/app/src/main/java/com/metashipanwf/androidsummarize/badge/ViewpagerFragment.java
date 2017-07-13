package com.metashipanwf.androidsummarize.badge;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.metashipanwf.androidsummarize.R;

/**
 * Created by edz on 2017/7/13.
 */

public class ViewpagerFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fargment_viewpager, null);
        return view;
    }
}
