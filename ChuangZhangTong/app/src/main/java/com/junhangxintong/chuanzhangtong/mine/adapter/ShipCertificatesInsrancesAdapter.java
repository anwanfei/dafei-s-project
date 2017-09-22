package com.junhangxintong.chuanzhangtong.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.mine.bean.CustomCertificateBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anwanfei on 2017/9/15.
 */

public class ShipCertificatesInsrancesAdapter extends BaseAdapter {

    private Context mContext;
    private List<CustomCertificateBean> certificates;

    public ShipCertificatesInsrancesAdapter(Context mContext, List<CustomCertificateBean> myFleetLists) {
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

        ViewHolder holder = null;
        if (convertView == null) {

            //加载条目布局
            convertView = View.inflate(mContext, R.layout.item_certificate, null);

            //创建holder对象
            holder = new ViewHolder(convertView);

            //保存holder
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //2、获取当前的item数据
        String certificateName = certificates.get(position).getName();
        String certifNo = certificates.get(position).getBinahao();
        String issueOrganization = certificates.get(position).getIssueArgument();
        String validDate = certificates.get(position).getValidDate();

        holder.tvCertificateName.setText(certificateName);
        holder.tvCertificateIssuingAuthority.setText(issueOrganization);
        holder.tvCertificateNumber.setText("证书编号:" + certifNo);
        if (validDate.equals("")) {
            holder.tvCertificateType.setText("有效日期:永久有效");
        } else {
            holder.tvCertificateType.setText("有效日期:" + validDate);
        }
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
    }
}
