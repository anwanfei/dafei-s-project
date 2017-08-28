package com.junhangxintong.chuanzhangtong.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.mine.activity.CertificateIndetailsActivity;
import com.junhangxintong.chuanzhangtong.mine.activity.InstranceDetailsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anwanfei on 2017/8/24.
 */

public class ShipCertificateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    String[] arrShipCertificates = {"船籍港证书", "船级证书", "载重线证书", "干舷证书", "保险单", "无疫证书", "检疫证书", "除鼠证书"};
    String[] arrShipInsuranceCertificates = {"全损险", "一切险"};

    //枚举两种类型
    public static enum ITEM_TYPE {
        ITEM_TYPE_HEAD,
        ITEM_TYPE_CONTENT
    }

    public ShipCertificateAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE.ITEM_TYPE_HEAD.ordinal();
        } else if (position > 0 && position <= 4) {
            return ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal();
        } else if (position == 5) {
            return ITEM_TYPE.ITEM_TYPE_HEAD.ordinal();
        } else {
            return ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal();
        }
/*
        if (position == 0) {
            return ITEM_TYPE.ITEM_TYPE_HEAD.ordinal();
        } else if (position > 0 && position <= Math.ceil(arrShipCertificates.length / 2)) {
            return ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal();
        } else if (position == Math.ceil(arrShipCertificates.length / 2) + 1) {
            return ITEM_TYPE.ITEM_TYPE_HEAD.ordinal();
        } else {
            return ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal();
        }
*/

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_HEAD.ordinal()) {
            View headerView = LayoutInflater.from(mContext).inflate(R.layout.item_ship_certificate_head, parent, false);
            HeaderViewHolder headerViewHolder = new HeaderViewHolder(headerView);
            return headerViewHolder;
        } else {
            View contentView = LayoutInflater.from(mContext).inflate(R.layout.item_ship_certificate_content, parent, false);
            ContentViewHolder contentViewHolder = new ContentViewHolder(contentView);
            return contentViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            if (position == 0) {
                ((HeaderViewHolder) holder).tvType.setText(mContext.getResources().getString(R.string.certificate));
            } else if (position == Math.ceil(arrShipCertificates.length / 2) + 1) {
                ((HeaderViewHolder) holder).tvType.setText(mContext.getResources().getString(R.string.insurance));
            }
        } else if (holder instanceof ContentViewHolder) {
            if (position > 0 && position <= Math.ceil(arrShipCertificates.length / 2)) {
                ((ContentViewHolder) holder).tvCertificateName1.setText(arrShipCertificates[2 * (position - 1)]);
                ((ContentViewHolder) holder).tvCertificateName2.setText(arrShipCertificates[2 * position - 1]);
                MyCertificateListener myCertificateListener = new MyCertificateListener();
                ((ContentViewHolder) holder).llItemLeft.setOnClickListener(myCertificateListener);
                ((ContentViewHolder) holder).llItemRight.setOnClickListener(myCertificateListener);
            } else {
                ((ContentViewHolder) holder).tvCertificateName1.setText(arrShipInsuranceCertificates[0]);
                ((ContentViewHolder) holder).tvCertificateName2.setText(arrShipInsuranceCertificates[1]);
                MyInsuranceListener myInsuranceListener = new MyInsuranceListener();
                ((ContentViewHolder) holder).llItemLeft.setOnClickListener(myInsuranceListener);
                ((ContentViewHolder) holder).llItemRight.setOnClickListener(myInsuranceListener);
            }

        }
    }

    @Override
    public int getItemCount() {
        int num = (int) (Math.ceil(arrShipCertificates.length / 2) + Math.ceil(arrShipInsuranceCertificates.length / 2));
        return num + 2;
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_type)
        TextView tvType;

        HeaderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ContentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_certificate_name1)
        TextView tvCertificateName1;
        @BindView(R.id.tv_certificate_number1)
        TextView tvCertificateNumber1;
        @BindView(R.id.tv_certificate_type1)
        TextView tvCertificateType1;
        @BindView(R.id.tv_certificate_issuing_authority1)
        TextView tvCertificateIssuingAuthority1;
        @BindView(R.id.ll_item_left)
        LinearLayout llItemLeft;
        @BindView(R.id.tv_certificate_name2)
        TextView tvCertificateName2;
        @BindView(R.id.tv_certificate_number2)
        TextView tvCertificateNumber2;
        @BindView(R.id.tv_certificate_type2)
        TextView tvCertificateType2;
        @BindView(R.id.tv_certificate_issuing_authority2)
        TextView tvCertificateIssuingAuthority2;
        @BindView(R.id.ll_item_right)
        LinearLayout llItemRight;
        @BindView(R.id.ll_certificate_insurance)
        LinearLayout llCertificateInsurance;

        ContentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private class MyCertificateListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            mContext.startActivity(new Intent(mContext, CertificateIndetailsActivity.class));
        }
    }

    private class MyInsuranceListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            mContext.startActivity(new Intent(mContext, InstranceDetailsActivity.class));
        }
    }
}
