package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.common.MainActivity;
import com.junhangxintong.chuanzhangtong.mine.adapter.MyFollowFleetAdapter;
import com.junhangxintong.chuanzhangtong.mine.bean.FollowShipListBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MyFollowFleetActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_add_ship)
    TextView tvAddShip;
    @BindView(R.id.ll_no_follow_fleet)
    LinearLayout llNoFollowFleet;
    @BindView(R.id.lv_my_folllow_fleet)
    ListView lvMyFolllowFleet;
    @BindView(R.id.tv_my_fleet_list_choose_all)
    TextView tvMyFleetListChooseAll;
    @BindView(R.id.tv_my_fleet_list_delete)
    TextView tvMyFleetListDelete;
    @BindView(R.id.rl_choose_all_delete)
    RelativeLayout rlChooseAllDelete;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.iv_nothing)
    ImageView ivNothing;
    @BindView(R.id.tv_nothing)
    TextView tvNothing;
    @BindView(R.id.et_search_ship_name)
    EditText etSearchShipName;
    @BindView(R.id.ll_search_ship_name)
    LinearLayout llSearchShipName;
    @BindView(R.id.tv_search_ship)
    TextView tvSearchShip;

    private List<FollowShipListBean.DataBean.ArrayBean> myFollowFleetLists;
    private MyFollowFleetAdapter myFollowFleetAdapter;
    private boolean isChoose = true;
    private List<String> savelist = new ArrayList();
    private Map<String, Boolean> map = new HashMap<>();
    private List<FollowShipListBean.DataBean.ArrayBean> choosedLists;
    private boolean isChooseAll = true;
    private List<FollowShipListBean.DataBean.ArrayBean> followShipLists;
    private ArrayList<String> choosedShipIdLists;
    private String userId;
    private List<String> followShipIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initlistener();
    }

    private void initlistener() {
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
                netGetFollowFleetList(shipName);
            }
        });
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.follow_fleets));

        tvShare.setText(getResources().getString(R.string.delete));
        tvNothing.setText(getResources().getString(R.string.follow_fisrt_ship));
        tvAddShip.setText(getResources().getString(R.string.add));
    }

    @Override
    protected void initData() {

        userId = CacheUtils.getString(this, Constants.ID);
        netGetFollowFleetList("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        netGetFollowFleetList("");
    }

    private void netGetFollowFleetList(String shipName) {
        NetUtils.postWithHeader(this, ConstantsUrls.FOLLOW_SHIP_LIST)
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.PAGE_SIZE, "100")
                .addParams(Constants.SHIP_NAME, shipName)
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onDataEmpty(String message) {
                        super.onDataEmpty(message);
                        followShipLists = new ArrayList<FollowShipListBean.DataBean.ArrayBean>();
                        updaFollowFLeetList();
                    }

                    @Override
                    protected void onSuccess(String response, String message) {
                        FollowShipListBean followShipListBean = new Gson().fromJson(response, FollowShipListBean.class);
                        followShipLists = followShipListBean.getData().getArray();

                        followShipIds = new ArrayList<String>();
                        for (int i = 0; i < followShipLists.size(); i++) {
                            followShipIds.add(String.valueOf(followShipLists.get(i).getId()));
                        }
                        updaFollowFLeetList();

                        myFollowFleetAdapter = new MyFollowFleetAdapter(MyFollowFleetActivity.this, followShipLists);
                        lvMyFolllowFleet.setAdapter(myFollowFleetAdapter);
                        myFollowFleetAdapter.notifyDataSetChanged();
                    }
                });
    }

    private void updaFollowFLeetList() {
        if (followShipLists.size() > 0) {
            lvMyFolllowFleet.setVisibility(View.VISIBLE);
            llNoFollowFleet.setVisibility(View.GONE);
            tvShare.setVisibility(View.VISIBLE);
            tvSearchShip.setVisibility(View.VISIBLE);
            tvAddShip.setVisibility(View.VISIBLE);
        } else {
            lvMyFolllowFleet.setVisibility(View.GONE);
            llNoFollowFleet.setVisibility(View.VISIBLE);
            tvShare.setVisibility(View.GONE);
            rlChooseAllDelete.setVisibility(View.GONE);
            tvSearchShip.setVisibility(View.GONE);
            tvNothing.setVisibility(View.GONE);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_follow_fleet;
    }

    @OnClick({R.id.iv_back, R.id.tv_add_ship, R.id.tv_search_ship, R.id.tv_my_fleet_list_choose_all, R.id.tv_my_fleet_list_delete, R.id.rl_choose_all_delete, R.id.tv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add_ship:
                searchShipNameToMainActivity();
                break;
            case R.id.tv_search_ship:
                searchShipNameToMainActivity();
                break;
            case R.id.tv_my_fleet_list_choose_all:
                chooseAllOrNot();
                break;
            case R.id.tv_my_fleet_list_delete:
                deleteChoosedItems();
                break;
            case R.id.rl_choose_all_delete:

                break;
            case R.id.tv_share:
                editFollowFleet();
                break;
        }
    }

    private void searchShipNameToMainActivity() {
        String searchShipName = etSearchShipName.getText().toString();

       /*if (StringUtils.isEmpty(searchShipName)) {
            Toast.makeText(MyFollowFleetActivity.this, getResources().getString(R.string.input_ship_name), Toast.LENGTH_SHORT).show();
            return;
        }*/

       /* if (followShipLists.size() > 0) {
            Toast.makeText(MyFollowFleetActivity.this, getResources().getString(R.string.followed_ship), Toast.LENGTH_SHORT).show();
            return;
        }
*/
        Intent intent = new Intent(MyFollowFleetActivity.this, MainActivity.class);
//        intent.putExtra(Constants.SEARCHSHIPNAME, searchShipName);
        intent.putExtra(Constants.SEARCHSHIPNAME, searchShipName);
        startActivity(intent);
        CacheUtils.putBoolean(MyFollowFleetActivity.this, Constants.SEARCHSHIPNAME, true);
        finish();
    }

    private void editFollowFleet() {
        if (isChoose) {
            rlChooseAllDelete.setVisibility(View.VISIBLE);
            tvShare.setText(getResources().getString(R.string.cancel));
            myFollowFleetAdapter.controlCheckboxShow(isChoose);
            isChoose = false;
            myFollowFleetAdapter.notifyDataSetChanged();
            tvSearchShip.setVisibility(View.GONE);
        } else {
            rlChooseAllDelete.setVisibility(View.GONE);
            tvShare.setText(getResources().getString(R.string.edit));
            myFollowFleetAdapter.controlCheckboxShow(isChoose);
            isChoose = true;
            myFollowFleetAdapter.notifyDataSetChanged();
            tvSearchShip.setVisibility(View.VISIBLE
            );
        }
    }


    private void deleteChoosedItems() {
        //清除集合
        savelist.clear();
        map.clear();

        //选中的船作为一个集合，集中处理
        choosedLists = new ArrayList<>();

        //选中的船的id作为一个集合，集中处理
        choosedShipIdLists = new ArrayList<>();

        //找到选中的位置，并保存在map
        for (int i = 0; i < followShipLists.size(); i++) {
            boolean checkbox = followShipLists.get(i).isCheckbox();
            if (checkbox) {
                map.put(i + "", true);
                choosedShipIdLists.add(String.valueOf(followShipLists.get(i).getId()));
                choosedLists.add(followShipLists.get(i));
            } else {
                map.put(i + "", false);
            }
        }
        followShipLists.removeAll(choosedLists);
        netDeleteChoosedCrews();
        updaFollowFLeetList();
        myFollowFleetAdapter.notifyDataSetChanged();

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

    private void netDeleteChoosedCrews() {

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < choosedShipIdLists.size(); i++) {
            sb.append(choosedShipIdLists.get(i) + ",");
        }

        String ids = sb.toString().substring(0, sb.length() - 1);
        NetUtils.postWithHeader(MyFollowFleetActivity.this, ConstantsUrls.DELETE_FOLLOW_SHIP)
                .addParams(Constants.IDS, ids)
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {

                    }
                });
    }

    private void chooseAllOrNot() {
        if (isChooseAll) {
            isChooseAll = false;
            for (int i = 0; i < followShipLists.size(); i++) {
                followShipLists.get(i).setCheckbox(true);
            }
            tvMyFleetListChooseAll.setText(getResources().getString(R.string.cancel_choose_all));
            myFollowFleetAdapter.notifyDataSetChanged();
        } else {
            isChooseAll = true;
            for (int i = 0; i < followShipLists.size(); i++) {
                followShipLists.get(i).setCheckbox(false);
            }
            tvMyFleetListChooseAll.setText(getResources().getString(R.string.choose_all));
            myFollowFleetAdapter.notifyDataSetChanged();
        }
    }

}
