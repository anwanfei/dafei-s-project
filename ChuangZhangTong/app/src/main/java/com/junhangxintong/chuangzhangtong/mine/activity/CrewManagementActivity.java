package com.junhangxintong.chuangzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseActivity;
import com.junhangxintong.chuangzhangtong.mine.adapter.MyCrewAdapter;
import com.junhangxintong.chuangzhangtong.mine.bean.CrewBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class CrewManagementActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_add_ship)
    TextView tvAddCrew;
    @BindView(R.id.ll_no_crew)
    LinearLayout llNoCrew;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.lv_my_crew)
    ListView lvMyCrew;
    @BindView(R.id.tv_my_crew_list_choose_all)
    TextView tvMyCrewListChooseAll;
    @BindView(R.id.tv_my_crew_list_delete)
    TextView tvMyCrewListDelete;
    @BindView(R.id.rl_choose_all_delete)
    RelativeLayout rlChooseAllDelete;

    List<CrewBean> crews;
    private MyCrewAdapter myFleetAdapter;
    private boolean isChoose = true;
    private boolean isChooseAll = true;

    private List<String> savelist = new ArrayList();
    private Map<String, Boolean> map = new HashMap<>();
    private List<CrewBean> choosedLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.chuanyuanguanli));
        tvShare.setText(getResources().getString(R.string.edit));
    }

    @Override
    protected void initData() {
        crews = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            CrewBean crewBean = new CrewBean();
            crewBean.setCrewName("船员" + i + "号");
            crewBean.setDuty("大副" + i + "号");
            crewBean.setJobNum("00" + i + "号");
            crewBean.setNationality("中国");
            crews.add(crewBean);
        }

        if (crews.size() > 0) {
            lvMyCrew.setVisibility(View.VISIBLE);
            llNoCrew.setVisibility(View.GONE);
            tvShare.setVisibility(View.VISIBLE);
        } else {
            lvMyCrew.setVisibility(View.GONE);
            llNoCrew.setVisibility(View.VISIBLE);
            tvShare.setVisibility(View.GONE);
        }

        myFleetAdapter = new MyCrewAdapter(this, crews);
        lvMyCrew.setAdapter(myFleetAdapter);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_crew_management;
    }

    @OnClick({R.id.iv_back, R.id.tv_add_ship, R.id.ll_no_crew, R.id.tv_share, R.id.tv_my_crew_list_choose_all, R.id.tv_my_crew_list_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add_ship:
                startActivity(new Intent(CrewManagementActivity.this, CrewInfoInputActivity.class));
                break;
            case R.id.ll_no_crew:
                break;
            case R.id.tv_share:
                editCrews();
                break;
            case R.id.tv_my_crew_list_choose_all:
                chooseAllOrNot();
                break;
            case R.id.tv_my_crew_list_delete:
                deleteChoosedItems();
                break;
        }
    }

    private void deleteChoosedItems() {
        //清除集合
        savelist.clear();
        map.clear();

        //选中的船作为一个集合，集中处理
        choosedLists = new ArrayList<>();

        //找到选中的位置，并保存在map
        for (int i = 0; i < crews.size(); i++) {
            boolean checkbox = crews.get(i).isCheckbox();
            if (checkbox) {
                map.put(i + "", true);
                choosedLists.add(crews.get(i));
            } else {
                map.put(i + "", false);
            }
        }
        crews.removeAll(choosedLists);
        myFleetAdapter.notifyDataSetChanged();

        //遍历map
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            String key = entry.getKey();
            Boolean value = entry.getValue();
            if (value) {
                savelist.add(key);
            }
        }
    }

    private void chooseAllOrNot() {
        if (isChooseAll) {
            isChooseAll = false;
            for (int i = 0; i < crews.size(); i++) {
                crews.get(i).setCheckbox(true);
            }
            tvMyCrewListChooseAll.setText(getResources().getString(R.string.cancel_choose_all));
            myFleetAdapter.notifyDataSetChanged();
        } else {
            isChooseAll = true;
            for (int i = 0; i < crews.size(); i++) {
                crews.get(i).setCheckbox(false);
            }
            tvMyCrewListChooseAll.setText(getResources().getString(R.string.choose_all));
            myFleetAdapter.notifyDataSetChanged();
        }
    }

    private void editCrews() {
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
}
