package com.junhangxintong.chuanzhangtong.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.mine.bean.CrewCeretificateRemindBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anwanfei on 2017/7/14.
 */

public class CrewCertificateAdapter extends BaseAdapter {

    private Context mContext;
    private List<CrewCeretificateRemindBean.DataBean.ArrayBean> certificates;

    public CrewCertificateAdapter(Context mContext, List<CrewCeretificateRemindBean.DataBean.ArrayBean> myFleetLists) {
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

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
            holder.llCetificate = (LinearLayout) convertView.findViewById(R.id.ll_cetificate);

            //保存holder
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //2、获取当前的item数据
        String certificateName = certificates.get(position).getName();
        String certifNo = certificates.get(position).getCertifNo();
        String issueOrganization = certificates.get(position).getIssueOrganization();
        String validDate = certificates.get(position).getValidDate();

        holder.tvCertificateName.setText(certificateName);
        holder.tvCertificateIssuingAuthority.setText("签发机构：" + issueOrganization);
        holder.tvCertificateNumber.setText("证书编号：" + certifNo);
        if (validDate.equals("")) {
            holder.tvCertificateType.setText("有效日期：永久有效");
        } else {
            holder.tvCertificateType.setText("有效日期：" + validDate);
        }
/*
        holder.llCetificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CertificateIndetailsActivity.class);
                int id = certificates.get(position).getId();
                intent.putExtra(Constants.ID, String.valueOf(id));
                mContext.startActivity(intent);
            }
        });*/
        //返回view
        return convertView;
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
        @BindView(R.id.ll_cetificate)
        LinearLayout llCetificate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        ViewHolder() {
        }
    }
}
