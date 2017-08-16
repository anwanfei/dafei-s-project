package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.common.MainActivity;
import com.junhangxintong.chuanzhangtong.mine.adapter.MyFollowFleetAdapter;
import com.junhangxintong.chuanzhangtong.mine.bean.MyFollowFleetBean;

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

    private List<MyFollowFleetBean> myFollowFleetLists;
    private MyFollowFleetAdapter myFollowFleetAdapter;
    private boolean isChoose = true;
    private List<String> savelist = new ArrayList();
    private Map<String, Boolean> map = new HashMap<>();
    private List<MyFollowFleetBean> choosedLists;
    private boolean isChooseAll = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.follow_fleet));
        tvShare.setVisibility(View.VISIBLE);
        tvShare.setText(getResources().getString(R.string.edit));
        tvNothing.setText(getResources().getString(R.string.follow_fisrt_ship));
        tvAddShip.setText(getResources().getString(R.string.immediately_follow));
    }

    @Override
    protected void initData() {
        myFollowFleetLists = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            MyFollowFleetBean myFollowFleetBean = new MyFollowFleetBean();
            myFollowFleetBean.setShipName("华海" + i + "号");
            myFollowFleetLists.add(myFollowFleetBean);
        }

        updaFollowFLeetList();

        myFollowFleetAdapter = new MyFollowFleetAdapter(this, myFollowFleetLists);
        lvMyFolllowFleet.setAdapter(myFollowFleetAdapter);
    }

    private void updaFollowFLeetList() {
        if (myFollowFleetLists.size() > 0) {
            lvMyFolllowFleet.setVisibility(View.VISIBLE);
            llNoFollowFleet.setVisibility(View.GONE);
            tvShare.setVisibility(View.VISIBLE);
        } else {
            lvMyFolllowFleet.setVisibility(View.GONE);
            llNoFollowFleet.setVisibility(View.VISIBLE);
            tvShare.setVisibility(View.GONE);
            rlChooseAllDelete.setVisibility(View.GONE);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_follow_fleet;
    }

    @OnClick({R.id.iv_back, R.id.tv_add_ship, R.id.tv_my_fleet_list_choose_all, R.id.tv_my_fleet_list_delete, R.id.rl_choose_all_delete, R.id.tv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add_ship:
                startActivity(new Intent(MyFollowFleetActivity.this, MainActivity.class));
                finish();
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

    private void editFollowFleet() {
        if (isChoose) {
            rlChooseAllDelete.setVisibility(View.VISIBLE);
            tvShare.setText(getResources().getString(R.string.cancel));
            myFollowFleetAdapter.controlCheckboxShow(isChoose);
            isChoose = false;
            myFollowFleetAdapter.notifyDataSetChanged();
        } else {
            rlChooseAllDelete.setVisibility(View.GONE);
            tvShare.setText(getResources().getString(R.string.edit));
            myFollowFleetAdapter.controlCheckboxShow(isChoose);
            isChoose = true;
            myFollowFleetAdapter.notifyDataSetChanged();
        }
    }


    private void deleteChoosedItems() {
        //清除集合
        savelist.clear();
        map.clear();

        //选中的船作为一个集合，集中处理
        choosedLists = new ArrayList<>();

        //找到选中的位置，并保存在map
        for (int i = 0; i < myFollowFleetLists.size(); i++) {
            boolean checkbox = myFollowFleetLists.get(i).isCheckbox();
            if (checkbox) {
                map.put(i + "", true);
                choosedLists.add(myFollowFleetLists.get(i));
            } else {
                map.put(i + "", false);
            }
        }
        myFollowFleetLists.removeAll(choosedLists);
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

    private void chooseAllOrNot() {
        if (isChooseAll) {
            isChooseAll = false;
            for (int i = 0; i < myFollowFleetLists.size(); i++) {
                myFollowFleetLists.get(i).setCheckbox(true);
            }
            tvMyFleetListChooseAll.setText(getResources().getString(R.string.cancel_choose_all));
            myFollowFleetAdapter.notifyDataSetChanged();
        } else {
            isChooseAll = true;
            for (int i = 0; i < myFollowFleetLists.size(); i++) {
                myFollowFleetLists.get(i).setCheckbox(false);
            }
            tvMyFleetListChooseAll.setText(getResources().getString(R.string.choose_all));
            myFollowFleetAdapter.notifyDataSetChanged();
        }
    }

}