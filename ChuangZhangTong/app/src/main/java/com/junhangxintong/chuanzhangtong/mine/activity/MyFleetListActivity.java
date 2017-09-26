package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.common.NetServiceCodeBean;
import com.junhangxintong.chuanzhangtong.utils.RoleEnum;
import com.junhangxintong.chuanzhangtong.mine.adapter.MyFleetAdapter;
import com.junhangxintong.chuanzhangtong.mine.adapter.MyShipAdapter;
import com.junhangxintong.chuanzhangtong.mine.bean.MyFleetBean;
import com.junhangxintong.chuanzhangtong.mine.bean.ShipListBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

public class MyFleetListActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_add_ship)
    TextView tvAddShip;
    @BindView(R.id.ll_no_fleet)
    LinearLayout llNoFleet;
    @BindView(R.id.lv_my_fleet)
    ListView lvMyFleet;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_my_fleet_list_choose_all)
    TextView tvMyFleetListChooseAll;
    @BindView(R.id.tv_my_fleet_list_delete)
    TextView tvMyFleetListDelete;
    @BindView(R.id.rl_choose_all_delete)
    RelativeLayout rlChooseAllDelete;
    @BindView(R.id.et_search_ship_name)
    EditText etSearchShipName;
    @BindView(R.id.ll_search_ship_name)
    LinearLayout llSearchShipName;
    @BindView(R.id.iv_nothing)
    ImageView ivNothing;
    @BindView(R.id.tv_nothing)
    TextView tvNothing;

    private List<MyFleetBean> myFleetLists;
    private MyFleetAdapter myFleetAdapter;
    private boolean isChoose = true;
    private boolean isChooseAll = true;
    private List<String> savelist = new ArrayList();
    private Map<String, Boolean> map = new HashMap<>();
    private List<MyFleetBean> choosedLists;
    private List<ShipListBean.DataBean.ArrayBean> shipLists;
    private String userId;
    private MyShipAdapter myShipAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListener();
    }

    private void initListener() {
        etSearchShipName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String shipName = etSearchShipName.getText().toString();
                netGetShipLists(shipName);
            }
        });
    }

    @Override
    protected void initView() {

        String roleID = CacheUtils.getString(this, Constants.ROLEID);

        if (roleID.equals(String.valueOf(RoleEnum.SHIPMASTER.getCode())) || roleID.equals(String.valueOf(RoleEnum.SHIPMEMBER.getCode()))) {
            tvTitle.setText(getResources().getString(R.string.my_ship));
        } else {
            tvTitle.setText(getResources().getString(R.string.my_fleet));
        }

        ivBack.setVisibility(View.VISIBLE);

        tvSetting.setVisibility(View.GONE);
        ivShare.setVisibility(View.GONE);
        tvAddShip.setVisibility(View.GONE);

//        tvSetting.setVisibility(View.GONE);
//        tvShare.setVisibility(View.GONE);
//        ivShare.setVisibility(View.GONE);
//        ivShare.setBackgroundResource(R.drawable.iv_add_fleet);
//        tvSetting.setText(getResources().getString(R.string.add_ships));
//        tvShare.setText(getResources().getString(R.string.edit));
    }

    @Override
    protected void initData() {

        userId = CacheUtils.getString(this, Constants.ID);

        netGetShipLists("");
    }

    private void netGetShipLists(final String shipName) {
        final String roleId = CacheUtils.getString(this, Constants.ROLEID);
        NetUtils.postWithHeader(this, ConstantsUrls.MY_SHIP_LISTS)
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.PAGE_SIZE, "100")
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.SHIP_NAME, shipName)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(MyFleetListActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(MyFleetListActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            NetServiceCodeBean netServiceErrort = new Gson().fromJson(response, NetServiceCodeBean.class);
                            String message = netServiceErrort.getMessage();
                            String code = netServiceErrort.getCode();
                            if (code.equals("200")) {

                                ShipListBean shipListBean = new Gson().fromJson(response, ShipListBean.class);
                                shipLists = shipListBean.getData().getArray();

                                updataListview();

                                if (roleId.equals(String.valueOf(RoleEnum.SHIPMEMBER.getCode()))) {
                                    myShipAdapter = new MyShipAdapter(MyFleetListActivity.this, shipLists);
                                    lvMyFleet.setAdapter(myShipAdapter);
                                    myShipAdapter.notifyDataSetChanged();
                                } else {
                                    myFleetAdapter = new MyFleetAdapter(MyFleetListActivity.this, shipLists);
                                    lvMyFleet.setAdapter(myFleetAdapter);
                                    myFleetAdapter.notifyDataSetChanged();
                                }
                            } else if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(MyFleetListActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(MyFleetListActivity.this, LoginRegisterActivity.class));
                                finish();
                            } else if (code.equals("404")) {
                                if (StringUtils.isNotBlank(shipName)) {
                                    tvNothing.setText(getResources().getString(R.string.search_no_ship));
                                } else {
                                    tvNothing.setText(getResources().getString(R.string.no_ship));
                                }
                                shipLists = new ArrayList<ShipListBean.DataBean.ArrayBean>();
                                updataListview();
                            } else {
                                Toast.makeText(MyFleetListActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void updataListview() {
        if (shipLists.size() > 0) {
            lvMyFleet.setVisibility(View.VISIBLE);
            llNoFleet.setVisibility(View.GONE);
            tvShare.setVisibility(View.VISIBLE);
            llSearchShipName.setVisibility(View.VISIBLE);
        } else {
            lvMyFleet.setVisibility(View.GONE);
            llNoFleet.setVisibility(View.VISIBLE);
            tvShare.setVisibility(View.GONE);
            rlChooseAllDelete.setVisibility(View.GONE);

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_fleet_list;
    }

    @OnClick({R.id.iv_back, R.id.tv_add_ship, R.id.ll_no_fleet, R.id.iv_share, R.id.tv_setting, R.id.tv_share, R.id.tv_my_fleet_list_choose_all, R.id.tv_my_fleet_list_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add_ship:
                gotoAddShipActivity();
                break;
            case R.id.ll_no_fleet:
                break;
            case R.id.iv_share:
                gotoAddShipActivity();
                break;
            case R.id.tv_setting:
                gotoAddShipActivity();
                break;
            case R.id.tv_share:
                editFleet();
                break;
            case R.id.tv_my_fleet_list_choose_all:
                chooseAllOrNot();
                break;
            case R.id.tv_my_fleet_list_delete:
                deleteChoosedItems();
                break;
        }
    }

    private void gotoAddShipActivity() {
        Intent intent = new Intent(MyFleetListActivity.this, AddShipActivity.class);

        startActivityForResult(intent, Constants.REQUEST_CODE1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case Constants.REQUEST_CODE1:
                    String shipName = data.getStringExtra(Constants.SHIP_NAME);
                    MyFleetBean myFleetBean = new MyFleetBean();
                    myFleetBean.setShipName(shipName);
                    myFleetLists.add(myFleetBean);
                    myFleetAdapter.notifyDataSetChanged();
                    break;
            }
        }

    }

    private void deleteChoosedItems() {
        //清除集合
        savelist.clear();
        map.clear();

        //选中的船作为一个集合，集中处理
        choosedLists = new ArrayList<>();

        //找到选中的位置，并保存在map
        for (int i = 0; i < myFleetLists.size(); i++) {
            boolean checkbox = myFleetLists.get(i).isCheckbox();
            if (checkbox) {
                map.put(i + "", true);
                choosedLists.add(myFleetLists.get(i));
            } else {
                map.put(i + "", false);
            }
        }
        myFleetLists.removeAll(choosedLists);
        updataListview();
        myFleetAdapter.notifyDataSetChanged();

        //遍历map
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            String key = entry.getKey();
            Boolean value = entry.getValue();
            if (value) {
                savelist.add(key);
            }
        }

        Log.e("asdasdas", "选择位置的 ：" + savelist.toString());
    }

    private void chooseAllOrNot() {
        if (isChooseAll) {
            isChooseAll = false;
            for (int i = 0; i < myFleetLists.size(); i++) {
                myFleetLists.get(i).setCheckbox(true);
            }
            tvMyFleetListChooseAll.setText(getResources().getString(R.string.cancel_choose_all));
            myFleetAdapter.notifyDataSetChanged();
        } else {
            isChooseAll = true;
            for (int i = 0; i < myFleetLists.size(); i++) {
                myFleetLists.get(i).setCheckbox(false);
            }
            tvMyFleetListChooseAll.setText(getResources().getString(R.string.choose_all));
            myFleetAdapter.notifyDataSetChanged();
        }
    }

    private void editFleet() {
        if (isChoose) {
            rlChooseAllDelete.setVisibility(View.VISIBLE);
            tvShare.setText(getResources().getString(R.string.cancel));
            myFleetAdapter.controlCheckboxShow(isChoose);
            isChoose = false;
            myFleetAdapter.notifyDataSetChanged();
        } else {
            rlChooseAllDelete.setVisibility(View.GONE);
            tvShare.setText(getResources().getString(R.string.edit));
            myFleetAdapter.controlCheckboxShow(isChoose);
            isChoose = true;
            myFleetAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
