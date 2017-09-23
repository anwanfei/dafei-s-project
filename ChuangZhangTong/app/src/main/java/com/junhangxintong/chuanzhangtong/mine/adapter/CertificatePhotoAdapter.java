package com.junhangxintong.chuanzhangtong.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.mine.bean.UrlBean;

import java.util.ArrayList;

import butterknife.BindView;

public class CertificatePhotoAdapter extends RecyclerView.Adapter<CertificatePhotoAdapter.ViewHolder> {

    @BindView(R.id.ivPhoto)
    ImageView ivPhoto;
    private Context context;
    private LayoutInflater mLayoutInflater;
    private ArrayList<UrlBean> urlLists;
    private String domain;
    private final static String TAG = "PhotoAdapter";

    public CertificatePhotoAdapter(Context context, ArrayList<UrlBean> urlLists, String domain) {
        this.context = context;
        this.urlLists = urlLists;
        this.domain = domain;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_photo, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String url = domain + urlLists.get(position);
        Glide.with(context)
                .load(url)
                .centerCrop()
                .into(holder.ivPhoto);
       /* holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ScanPhotoActivity.class);
                intent.putStringArrayListExtra(Constants.PHONE_PATHS, result);
                intent.putExtra(Constants.POSITION, position);
                context.startActivity(intent);
                result.clear();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return urlLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPhoto = (ImageView) itemView.findViewById(R.id.ivPhoto);
        }

    }
}