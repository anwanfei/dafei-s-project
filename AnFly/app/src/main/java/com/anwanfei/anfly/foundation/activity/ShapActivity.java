package com.anwanfei.anfly.foundation.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.anwanfei.anfly.R;
import com.anwanfei.anfly.common.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShapActivity extends BaseActivity {

    @BindView(R.id.gv_shap)
    GridView gvShap;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    int[] arrContents = {R.drawable.rectangle_normal,R.drawable.rectangle_gradient,R.drawable.rectangle_frame,R.drawable.rectangle_empty_frame_radius,R.drawable.rectangle_radius
    ,R.drawable.rectangle_empty_frame,R.drawable.rectangle_center_gradient,R.drawable.rectangle_frame_radius,R.drawable.rectangle_center_gradient_sweep,R.drawable.rectangle_empty_frame_radius_half
    ,R.drawable.ovel_normal,R.drawable.ovel_gradient,R.drawable.ovel_frame,R.drawable.ovel_center_gtadient_sweep,R.drawable.ovel_empty_frame_radius,R.drawable.ring_normal
    ,R.drawable.ring_gradient,R.drawable.ring_gradient_with_bg,R.drawable.ring_gradient_no_bg,R.drawable.ring_thickness_gradient,R.drawable.line_empty,R.drawable.line_normal};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.SHAP));
    }

    @Override
    protected void initData() {
        gvShap.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return arrContents.length;
            }

            @Override
            public Object getItem(int i) {
                return arrContents[i];
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View convertView, ViewGroup viewGroup) {
                //1、创建或获取viewHolder
                ViewHolder holder = null;
                if (convertView == null) {
                    //创建holder对象
                    holder = new ViewHolder();
                    //加载条目布局
                    convertView = View.inflate(ShapActivity.this, R.layout.item_shap, null);

                    //找控件
                    holder.tv_shap = (TextView) convertView.findViewById(R.id.tv_shap);

                    //保存holder
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                //2、获取当前的item数据
                holder.tv_shap.setBackgroundResource(arrContents[i]);

                //返回view
                return convertView;
            }
        });
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    static class ViewHolder {
        @BindView(R.id.tv_shap)
        TextView tv_shap;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        ViewHolder() {
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shap;
    }
}
