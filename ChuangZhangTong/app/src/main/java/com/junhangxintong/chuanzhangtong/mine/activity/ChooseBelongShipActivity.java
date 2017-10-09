package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.common.NetServiceCodeBean;
import com.junhangxintong.chuanzhangtong.mine.bean.ShipListBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

public class ChooseBelongShipActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.listview_choose_certificate_type)
    ListView listviewChooseCertificateType;
    @BindView(R.id.iv_nothing)
    ImageView ivNothing;
    @BindView(R.id.tv_nothing)
    TextView tvNothing;
    @BindView(R.id.ll_no_fleet)
    LinearLayout llNoFleet;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.tv_add_ship)
    TextView tvAddShip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.choose_belong_ship));
        tvNothing.setText("亲，您尚没有船舶");
    }

    @Override
    protected void initData() {

        String userId = CacheUtils.getString(this, Constants.ID);
        NetUtils.postWithHeader(this, ConstantsUrls.MY_SHIP_LISTS)
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.PAGE_SIZE, "100")
                .addParams(Constants.USER_ID, userId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(ChooseBelongShipActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(ChooseBelongShipActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            NetServiceCodeBean netServiceErrort = new Gson().fromJson(response, NetServiceCodeBean.class);
                            String message = netServiceErrort.getMessage();
                            String code = netServiceErrort.getCode();
                            if (code.equals("200")) {
                                ShipListBean shipListBean = new Gson().fromJson(response, ShipListBean.class);
                                final List<ShipListBean.DataBean.ArrayBean> shipLists = shipListBean.getData().getArray();

                                final List<String> shipsName = new ArrayList<String>();

                                for (int i = 0; i < shipLists.size(); i++) {
                                    shipsName.add(shipLists.get(i).getShipName());
                                }
                                listviewChooseCertificateType.setAdapter(new ArrayAdapter(ChooseBelongShipActivity.this, R.layout.item_choose_duty, R.id.tv_duty_name, shipsName));

                                listviewChooseCertificateType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Intent intent = getIntent();
                                        intent.putExtra(Constants.SHIP_NAME, shipsName.get(i));
                                        intent.putExtra(Constants.SHIP_ID, String.valueOf(shipLists.get(i).getId()));
                                        setResult(Constants.REQUEST_CODE6, intent);
                                        finish();
                                    }
                                });
                            } else if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(ChooseBelongShipActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(ChooseBelongShipActivity.this, LoginRegisterActivity.class));
                                finish();
                            } else if (code.equals("404")) {
                                listviewChooseCertificateType.setVisibility(View.GONE);
                                llNoFleet.setVisibility(View.VISIBLE);
                                tvSetting.setVisibility(View.GONE);
                                ivShare.setVisibility(View.GONE);
                                tvAddShip.setVisibility(View.GONE);
                            } else {
                                Toast.makeText(ChooseBelongShipActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_choose_belong_ship;
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
