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
import com.junhangxintong.chuanzhangtong.mine.bean.CustomCertificateBean;
import com.junhangxintong.chuanzhangtong.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anwanfei on 2017/8/24.
 */

public class ShipCertificateInsuranceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;

    private List<CustomCertificateBean> shipCertificates;
    private List<CustomCertificateBean> shipInsurances;

    public ShipCertificateInsuranceAdapter(Context mContext, List<CustomCertificateBean> shipCertificates, List<CustomCertificateBean> shipInsurances) {
        this.mContext = mContext;
        this.shipCertificates = shipCertificates;
        this.shipInsurances = shipInsurances;
    }

    //枚举两种类型
    public static enum ITEM_TYPE {
        ITEM_TYPE_HEAD,
        ITEM_TYPE_CONTENT
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE.ITEM_TYPE_HEAD.ordinal();
        } else if (position > 0 && position <= (int) (Math.ceil(shipCertificates.size() / 2))) {
            return ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal();
        } else if (position == ((int) (Math.ceil(shipCertificates.size() / 2)) + 1)) {
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeaderViewHolder) {
            if (position == 0) {
                ((HeaderViewHolder) holder).tvType.setText(mContext.getResources().getString(R.string.certificate));
            } else if (position == Math.ceil(shipCertificates.size() / 2) + 1) {
                ((HeaderViewHolder) holder).tvType.setText(mContext.getResources().getString(R.string.insurance));
            }
        } else if (holder instanceof ContentViewHolder) {
            if (position > 0 && position <= Math.ceil(shipCertificates.size() / 2)) {
                ((ContentViewHolder) holder).tvCertificateName1.setText(shipCertificates.get(2 * (position - 1)).getName());
                ((ContentViewHolder) holder).tvCertificateName2.setText(shipCertificates.get(2 * position - 1).getName());
                ((ContentViewHolder) holder).tvCertificateNumber1.setText(mContext.getResources().getString(R.string.certificate_number_colon) + shipCertificates.get(2 * (position - 1)).getBinahao());
                ((ContentViewHolder) holder).tvCertificateNumber2.setText(mContext.getResources().getString(R.string.certificate_number_colon) + shipCertificates.get(2 * position - 1).getBinahao());
                ((ContentViewHolder) holder).tvCertificateIssuingAuthority1.setText(mContext.getResources().getString(R.string.issuing_authority_colon) + shipCertificates.get(2 * (position - 1)).getIssueArgument());
                ((ContentViewHolder) holder).tvCertificateIssuingAuthority2.setText(mContext.getResources().getString(R.string.issuing_authority_colon) + shipCertificates.get(2 * position - 1).getIssueArgument());
                ((ContentViewHolder) holder).tvCertificateType1.setText(mContext.getResources().getString(R.string.effective_date_colon) + shipCertificates.get(2 * (position - 1)).getValidDate());
                ((ContentViewHolder) holder).tvCertificateName2.setText(mContext.getResources().getString(R.string.effective_date_colon) + shipCertificates.get(2 * position - 1).getName());

                //点击详情跳转
                ((ContentViewHolder) holder).llItemLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gotoShipCertificateDetailsActivity(2 * (position - 1));
                    }
                });
                ((ContentViewHolder) holder).llItemRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gotoShipCertificateDetailsActivity(2 * position - 1);
                    }
                });
            } else {
                ((ContentViewHolder) holder).tvCertificateName1.setText(shipInsurances.get(2 * (position - ((int) (Math.ceil(shipCertificates.size() / 2)) + 2))).getName());
                ((ContentViewHolder) holder).tvCertificateName2.setText(shipInsurances.get(1 + 2 * (position - ((int) (Math.ceil(shipCertificates.size() / 2)) + 2))).getName());
                ((ContentViewHolder) holder).tvCertificateNumber1.setText(mContext.getResources().getString(R.string.certificate_number_colon) + shipInsurances.get(2 * (position - ((int) (Math.ceil(shipCertificates.size() / 2)) + 2))).getBinahao());
                ((ContentViewHolder) holder).tvCertificateNumber2.setText(mContext.getResources().getString(R.string.certificate_number_colon) + shipInsurances.get(1 + 2 * (position - ((int) (Math.ceil(shipCertificates.size() / 2)) + 2))).getBinahao());
                ((ContentViewHolder) holder).tvCertificateIssuingAuthority1.setText(mContext.getResources().getString(R.string.issuing_authority_colon) + shipInsurances.get(2 * (position - ((int) (Math.ceil(shipCertificates.size() / 2)) + 2))).getIssueArgument());
                ((ContentViewHolder) holder).tvCertificateIssuingAuthority2.setText(mContext.getResources().getString(R.string.issuing_authority_colon) + shipInsurances.get(1 + 2 * (position - ((int) (Math.ceil(shipCertificates.size() / 2)) + 2))).getIssueArgument());
                ((ContentViewHolder) holder).tvCertificateType1.setText(mContext.getResources().getString(R.string.effective_date_colon) + shipInsurances.get(2 * (position - ((int) (Math.ceil(shipCertificates.size() / 2)) + 2))).getValidDate());
                ((ContentViewHolder) holder).tvCertificateType2.setText(mContext.getResources().getString(R.string.effective_date_colon) + shipInsurances.get(1 + 2 * (position - ((int) (Math.ceil(shipCertificates.size() / 2)) + 2))).getValidDate());

                ((ContentViewHolder) holder).llItemLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gotoShipInsuranceDetailsActivity(2 * (position - ((int) (Math.ceil(shipCertificates.size() / 2)) + 2)));
                    }
                });
                ((ContentViewHolder) holder).llItemRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gotoShipInsuranceDetailsActivity(1 + 2 * (position - ((int) (Math.ceil(shipCertificates.size() / 2)) + 2)));
                    }
                });
            }

        }
    }

    private void gotoShipInsuranceDetailsActivity(int position) {
        Intent intent = new Intent(mContext, InstranceDetailsActivity.class);
        intent.putExtra(Constants.ID, shipInsurances.get(position).getId());
        mContext.startActivity(intent);
    }

    private void gotoShipCertificateDetailsActivity(int position) {
        Intent intent = new Intent(mContext, CertificateIndetailsActivity.class);
        intent.putExtra(Constants.ID, shipCertificates.get(position).getId());
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        int num = (int) (Math.ceil(shipCertificates.size() / 2)) + (int) (Math.ceil(shipInsurances.size() / 2));
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
}
