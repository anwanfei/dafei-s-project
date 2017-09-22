package com.junhangxintong.chuanzhangtong.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.mine.bean.ShipCertificateInsuranceListsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anwanfei on 2017/9/20.
 */

public class ShipCertificatesAndInsrancesAdapter extends BaseAdapter {

    private Context mContext;
    private List<ShipCertificateInsuranceListsBean.DataBean.ArrayBean> shipCertificateInsuranceLists;
    private boolean isShowCheckBox;

    public ShipCertificatesAndInsrancesAdapter(Context mContext, List<ShipCertificateInsuranceListsBean.DataBean.ArrayBean> shipCertificateInsuranceLists) {
        this.mContext = mContext;
        this.shipCertificateInsuranceLists = shipCertificateInsuranceLists;
    }

    @Override
    public int getCount() {
        return shipCertificateInsuranceLists.size();
    }

    @Override
    public Object getItem(int i) {
        return shipCertificateInsuranceLists.get(i);
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
        int certifType = shipCertificateInsuranceLists.get(position).getCertifType();
        if (certifType == 1) {
            holder.tvLabelCertificate.setVisibility(View.VISIBLE);
        } else {
            holder.tvLabelInsurance.setVisibility(View.VISIBLE);
        }
        String certificateName = shipCertificateInsuranceLists.get(position).getName();
        String certifNo = shipCertificateInsuranceLists.get(position).getCertifNo();
        String issueOrganization = shipCertificateInsuranceLists.get(position).getIssueOrganization();
        String validDate = shipCertificateInsuranceLists.get(position).getValidDate();
        final boolean checkbox = shipCertificateInsuranceLists.get(position).isCheckbox();

        holder.tvCertificateName.setText(certificateName);
        holder.tvCertificateIssuingAuthority.setText(issueOrganization);
        holder.tvCertificateNumber.setText("证书编号:" + certifNo);
        if (validDate.equals("")) {
            holder.tvCertificateType.setText("有效日期:永久有效");
        } else {
            holder.tvCertificateType.setText("有效日期:" + validDate);
        }

        if (checkbox) {
            holder.cbShip.setChecked(true);
        } else {
            holder.cbShip.setChecked(false);
        }

        holder.cbShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCb = shipCertificateInsuranceLists.get(position).isCheckbox();
                if (isCb) {
                    shipCertificateInsuranceLists.get(position).setCheckbox(false);
                } else {
                    shipCertificateInsuranceLists.get(position).setCheckbox(true);
                }
            }
        });

        if (isShowCheckBox) {
            holder.cbShip.setVisibility(View.VISIBLE);
        } else {
            holder.cbShip.setVisibility(View.INVISIBLE);
        }
        //返回view
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_label_certificate)
        TextView tvLabelCertificate;
        @BindView(R.id.tv_label_insurance)
        TextView tvLabelInsurance;
        @BindView(R.id.cb_ship)
        CheckBox cbShip;
        @BindView(R.id.tv_certificate_name)
        TextView tvCertificateName;
        @BindView(R.id.tv_certificate_number)
        TextView tvCertificateNumber;
        @BindView(R.id.tv_certificate_issuing_authority)
        TextView tvCertificateIssuingAuthority;
        @BindView(R.id.tv_certificate_type)
        TextView tvCertificateType;
        @BindView(R.id.ll_cetificate)
        LinearLayout llCetificate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void controlCheckboxShow(boolean b) {
        isShowCheckBox = b;
        notifyDataSetChanged();
    }
}
