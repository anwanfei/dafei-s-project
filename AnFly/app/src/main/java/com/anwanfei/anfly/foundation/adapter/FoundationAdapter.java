package com.anwanfei.anfly.foundation.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anwanfei.anfly.R;
import com.anwanfei.anfly.foundation.activity.AndroidComponentActivity;
import com.anwanfei.anfly.foundation.activity.AndroidEventActivity;
import com.anwanfei.anfly.foundation.activity.AndroidFoundationActivity;
import com.anwanfei.anfly.foundation.activity.AndroidInternetActivity;
import com.anwanfei.anfly.foundation.activity.AndroidInterviewActivity;
import com.anwanfei.anfly.foundation.activity.AndroidAdvanceActivity;
import com.anwanfei.anfly.foundation.activity.AndroidPhotoAnimationActivity;
import com.anwanfei.anfly.foundation.activity.AndroidStorageActivity;
import com.anwanfei.anfly.foundation.activity.AndroidUIActivity;
import com.anwanfei.anfly.foundation.bean.FoundationBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anwanfei on 2017/7/28.
 */

public class FoundationAdapter extends RecyclerView.Adapter<FoundationAdapter.ViewHolder> {


    private Context mContext;
    private List<FoundationBean> mList = new ArrayList<>();
    private List<Integer> mHeights;

    Class[] arrClasses = {AndroidFoundationActivity.class, AndroidComponentActivity.class, AndroidUIActivity.class, AndroidStorageActivity.class, AndroidInternetActivity.class
    , AndroidPhotoAnimationActivity.class, AndroidEventActivity.class, AndroidAdvanceActivity.class, AndroidInterviewActivity.class};


    public FoundationAdapter(Context mContext, List<FoundationBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
        getRandomHeight(mList);
    }

    public void getRandomHeight(List<FoundationBean> mList) {
        mHeights = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            //随机的获取一个范围为200-600直接的高度
            mHeights.add((int) (300 + Math.random() * 400));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_foundation_fagment, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = mHeights.get(position);
        holder.itemView.setLayoutParams(layoutParams);
        FoundationBean bean = mList.get(position);
        holder.textView.setText(bean.getName());
        holder.imageview.setImageResource(bean.getImageview());
        holder.rlFoundation.setOnClickListener(new MyClickListener(position));
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class MyClickListener implements View.OnClickListener {
        int position;

        public MyClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            mContext.startActivity(new Intent(mContext, arrClasses[position]));
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imageview)
        ImageView imageview;
        @BindView(R.id.text_view)
        TextView textView;
        @BindView(R.id.rl_foundation)
        RelativeLayout rlFoundation;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
