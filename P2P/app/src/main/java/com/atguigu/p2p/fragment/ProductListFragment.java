package com.atguigu.p2p.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.p2p.R;
import com.atguigu.p2p.bean.Product;
import com.atguigu.p2p.common.AppNetConfig;
import com.atguigu.p2p.common.BaseHolder;
import com.atguigu.p2p.common.MyBaseAdapter1;
import com.atguigu.p2p.common.MyBaseAdapter2;
import com.atguigu.p2p.common.MyBaseAdapter3;
import com.atguigu.p2p.ui.RoundProgress;
import com.atguigu.p2p.util.UIUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shkstart on 2016/8/15 0015.
 * 1.联网下载数据 - json解析 -- 集合数据
 * 2.ListView加载数据的显示：ListView + Adapter + 集合数据 + item Layout
 */
public class ProductListFragment extends Fragment {

    @Bind(R.id.lv_product_list)
    ListView lvProductList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = UIUtils.getXmlView(R.layout.fragment_product_list);
        ButterKnife.bind(this, view);

        initData();

        return view;

    }

    private AsyncHttpClient client = new AsyncHttpClient();

    private void initData() {
        //1.联网下载数据 - json解析 -- 集合数据
        client.post(AppNetConfig.PRODUCT, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                //解析json数据
                JSONObject jsonObject = JSON.parseObject(content);
                if (jsonObject.getBoolean("success")) {
                    String data = jsonObject.getString("data");
                    List<Product> products = JSON.parseArray(data, Product.class);


                    //2.ListView加载数据的显示：ListView + Adapter + 集合数据 + item Layout
                    //方式一：
//                    MyAdapter adapter = new MyAdapter(products);
//                    lvProductList.setAdapter(adapter);
                    //方式二：
//                    MyAdapter1 adapter = new MyAdapter1(products);
//                    lvProductList.setAdapter(adapter);
                    //方式三：
//                    MyAdapter2 adapter = new MyAdapter2(products);
//                    lvProductList.setAdapter(adapter);
                    //方式四：
                    MyAdapter3 adapter = new MyAdapter3(products);
                    lvProductList.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Throwable error, String content) {
                Toast.makeText(ProductListFragment.this.getActivity(), "数据下载失败", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    //方式一：
    class MyAdapter extends BaseAdapter {
        List<Product> data;

        public MyAdapter(List<Product> data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            return data == null ? 0 : data.size() + 1;
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            int itemViewType = getItemViewType(position);
            if (itemViewType == 0) {//对应的是position == 4
                //生成一个特殊的view，作为返回值
                TextView tv = new TextView(parent.getContext());
                tv.setText("不一样的烟火");
                tv.setTextColor(UIUtils.getColor(R.color.colorAccent));
                tv.setTextSize(UIUtils.dp2px(15));
                return tv;
            } else {
                if (position > 4) {
                    position = position - 1;
                }

                ViewHolder holder = null;
                if (convertView == null) {
                    convertView = View.inflate(parent.getContext(), R.layout.item_product_list, null);
                    holder = new ViewHolder(convertView);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                //装配数据
                Product product = data.get(position);
                holder.pMoney.setText(product.money);
                holder.pMinzouzi.setText(product.minTouMoney);
                holder.pProgresss.setProgress(Integer.parseInt(product.progress));
                holder.pMinnum.setText(product.memberNum);
                holder.pName.setText(product.name);
                holder.pYearlv.setText(product.yearRate);
                holder.pSuodingdays.setText(product.suodingDays);

                return convertView;
            }


        }

        //获取item类型的个数
        @Override
        public int getViewTypeCount() {
            return 2;
        }

        //获取不同position位置对应的item类型
        @Override
        public int getItemViewType(int position) {
            if (position == 4) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    static class ViewHolder {
        @Bind(R.id.p_name)
        TextView pName;
        @Bind(R.id.p_money)
        TextView pMoney;
        @Bind(R.id.p_yearlv)
        TextView pYearlv;
        @Bind(R.id.p_suodingdays)
        TextView pSuodingdays;
        @Bind(R.id.p_minzouzi)
        TextView pMinzouzi;
        @Bind(R.id.p_minnum)
        TextView pMinnum;
        @Bind(R.id.p_progresss)
        RoundProgress pProgresss;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    //方式二：
    class MyAdapter1 extends MyBaseAdapter1<Product> {

        public MyAdapter1(List<Product> data) {
            super(data);
        }

        @Override
        public View myGetView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(parent.getContext(), R.layout.item_product_list, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            //装配数据
            Product product = data.get(position);
            holder.pMoney.setText(product.money);
            holder.pMinzouzi.setText(product.minTouMoney);
            holder.pProgresss.setProgress(Integer.parseInt(product.progress));
            holder.pMinnum.setText(product.memberNum);
            holder.pName.setText(product.name);
            holder.pYearlv.setText(product.yearRate);
            holder.pSuodingdays.setText(product.suodingDays);

            return convertView;
        }


    }

    //方式三：没有使用ViewHolder,导致执行过多次的findViewById()，导致效率低。
    class MyAdapter2 extends MyBaseAdapter2<Product> {

        @Bind(R.id.p_name)
        TextView pName;
        @Bind(R.id.p_money)
        TextView pMoney;
        @Bind(R.id.p_yearlv)
        TextView pYearlv;
        @Bind(R.id.p_suodingdays)
        TextView pSuodingdays;
        @Bind(R.id.p_minzouzi)
        TextView pMinzouzi;
        @Bind(R.id.p_minnum)
        TextView pMinnum;
        @Bind(R.id.p_progresss)
        RoundProgress pProgresss;

        public MyAdapter2(List<Product> data) {
            super(data);
        }

        //装配数据
        @Override
        protected void setData(View convertView, Product product) {
            ((TextView) convertView.findViewById(R.id.p_name)).setText(product.name);
            //....还有7个findViewById()
            Log.e("TAG", "setData()");
        }

        //返回加载item layout对应的convertView
        @Override
        protected View initView() {
            return View.inflate(getActivity(), R.layout.item_product_list, null);
        }
    }

    //方式四：
    class MyAdapter3 extends MyBaseAdapter3<Product> {


        public MyAdapter3(List<Product> data) {
            super(data);
        }

        @Override
        protected BaseHolder getHolder() {
            return new MyViewHolder();
        }
    }

    class MyViewHolder extends BaseHolder<Product> {

        @Bind(R.id.p_name)
        TextView pName;
        @Bind(R.id.p_money)
        TextView pMoney;
        @Bind(R.id.p_yearlv)
        TextView pYearlv;
        @Bind(R.id.p_suodingdays)
        TextView pSuodingdays;
        @Bind(R.id.p_minzouzi)
        TextView pMinzouzi;
        @Bind(R.id.p_minnum)
        TextView pMinnum;
        @Bind(R.id.p_progresss)
        RoundProgress pProgresss;

        @Override
        public View initView() {
            return View.inflate(getActivity(), R.layout.item_product_list, null);
        }

        @Override
        public void setData(Product product) {
            pMoney.setText(product.money);
            pMinzouzi.setText(product.minTouMoney);
            pProgresss.setProgress(Integer.parseInt(product.progress));
            pMinnum.setText(product.memberNum);
            pName.setText(product.name);
            pYearlv.setText(product.yearRate);
            pSuodingdays.setText(product.suodingDays);
        }
    }
}
