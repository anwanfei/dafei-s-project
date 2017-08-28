package com.junhangxintong.chuanzhangtong.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.mine.activity.CrewCertificateListsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anwanfei on 2017/7/14.
 */

public class CertificateAdapter extends BaseAdapter implements View.OnClickListener {

    private Context mContext;
    private List<String> certificates;

    public CertificateAdapter(Context mContext, List<String> myFleetLists) {
        this.mContext = mContext;
        this.certificates = myFleetLists;
    }

    @Override
    public int getCount() {
        return certificates.size();
    }

    @Override
    public Object getItem(int i) {
        return certificates.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        //1、创建或获取viewHolder
        ViewHolder holder = null;
        if (convertView == null) {
            //创建holder对象
            holder = new ViewHolder();

            //加载条目布局
            convertView = View.inflate(mContext, R.layout.item_certificate, null);

            //找控件
            holder.tvCertificateName = (TextView) convertView.findViewById(R.id.tv_certificate_name);
            holder.tvCertificateNumber = (TextView) convertView.findViewById(R.id.tv_certificate_number);
            holder.tvCertificateType = (TextView) convertView.findViewById(R.id.tv_certificate_type);
            holder.tvCertificateIssuingAuthority = (TextView) convertView.findViewById(R.id.tv_certificate_issuing_authority);

            //保存holder
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //2、获取当前的item数据
        String certificateName = certificates.get(position);
        holder.tvCertificateName.setText(certificateName);

        //返回view
        return convertView;
    }

    @Override
    public void onClick(View view) {
        mContext.startActivity(new Intent(mContext, CrewCertificateListsActivity.class));
    }

    static class ViewHolder {
        @BindView(R.id.tv_certificate_name)
        TextView tvCertificateName;
        @BindView(R.id.tv_certificate_number)
        TextView tvCertificateNumber;
        @BindView(R.id.tv_certificate_type)
        TextView tvCertificateType;
        @BindView(R.id.tv_certificate_issuing_authority)
        TextView tvCertificateIssuingAuthority;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        ViewHolder() {
        }
    }
}
