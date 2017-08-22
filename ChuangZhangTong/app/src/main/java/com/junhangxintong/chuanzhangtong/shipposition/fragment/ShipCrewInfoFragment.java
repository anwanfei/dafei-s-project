package com.junhangxintong.chuanzhangtong.shipposition.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;
import com.junhangxintong.chuanzhangtong.mine.adapter.MyCrewAdapter;
import com.junhangxintong.chuanzhangtong.mine.bean.CrewBean;
import com.junhangxintong.chuanzhangtong.shipposition.activity.AddCrewActivity;
import com.junhangxintong.chuanzhangtong.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by anwanfei on 2017/8/8.
 */

public class ShipCrewInfoFragment extends BaseFragment {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.iv_nothing)
    ImageView ivNothing;
    @BindView(R.id.tv_nothing)
    TextView tvNothing;
    @BindView(R.id.tv_add_ship)
    TextView tvAddShip;
    @BindView(R.id.ll_no_crew)
    LinearLayout llNoCrew;
    @BindView(R.id.lv_my_crew)
    ListView lvMyCrew;
    @BindView(R.id.tv_my_crew_list_choose_all)
    TextView tvMyCrewListChooseAll;
    @BindView(R.id.tv_my_crew_list_delete)
    TextView tvMyCrewListDelete;
    @BindView(R.id.rl_choose_all_delete)
    RelativeLayout rlChooseAllDelete;
    Unbinder unbinder;
    private ArrayList<CrewBean> crews;
    private MyCrewAdapter myCrewAdapter;
    private ArrayList<CrewBean> choosedCrewsLists;
    private boolean isChoose = true;
    private boolean isChooseAll = true;
    private List<String> savelist = new ArrayList();
    private Map<String, Boolean> map = new HashMap<>();
    private List<CrewBean> choosedLists;


    public ShipCrewInfoFragment() {
    }

    @Override
    protected View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_crew_management, null);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.chuanyuanguanli));
        ivShare.setVisibility(View.GONE);
        tvSetting.setText(getResources().getString(R.string.add_crews));
        tvShare.setText(getResources().getString(R.string.edit));
        tvNothing.setText(getResources().getString(R.string.add_first_crew));
        ivNothing.setImageResource(R.drawable.iv_no_crew);
        return rootView;
    }

    @Override
    protected void initData() {
        super.initData();
        crews = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CrewBean crewBean = new CrewBean();
            crewBean.setCrewName("船员" + i + "号");
            crewBean.setDuty("大副" + i + "号");
            crewBean.setJobNum("00" + i + "号");
            crewBean.setNationality("中国");
            crews.add(crewBean);
        }

        updateCrewsList();
        myCrewAdapter = new MyCrewAdapter(getActivity(), crews);
        lvMyCrew.setAdapter(myCrewAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void updateCrewsList() {
        if (crews.size() > 0) {
            lvMyCrew.setVisibility(View.VISIBLE);
            llNoCrew.setVisibility(View.GONE);
            tvShare.setVisibility(View.VISIBLE);
        } else {
            lvMyCrew.setVisibility(View.GONE);
            llNoCrew.setVisibility(View.VISIBLE);
            tvShare.setVisibility(View.GONE);
            rlChooseAllDelete.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.iv_back, R.id.iv_share, R.id.tv_setting, R.id.tv_share, R.id.tv_add_ship, R.id.ll_no_crew, R.id.tv_my_crew_list_choose_all, R.id.tv_my_crew_list_delete, R.id.rl_choose_all_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().finish();
                break;
            case R.id.iv_share:
                break;
            case R.id.tv_setting:
                if (isChoose) {
                    addCrew();
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.can_not_add_when_edit), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_share:
                editCrews();
                break;
            case R.id.tv_add_ship:
                addCrew();
                break;
            case R.id.ll_no_crew:
                break;
            case R.id.tv_my_crew_list_choose_all:
                chooseAllOrNot();
                break;
            case R.id.tv_my_crew_list_delete:
                deleteChoosedItems();
                break;
            case R.id.rl_choose_all_delete:
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
        updateCrewsList();
        myCrewAdapter.notifyDataSetChanged();

        //遍历map
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            String key = entry.getKey();
            Boolean value = entry.getValue();
            if (value) {
                savelist.add(key);
            }
        }
    }


    public void chooseAllOrNot() {
        if (isChooseAll) {
            isChooseAll = false;
            for (int i = 0; i < crews.size(); i++) {
                crews.get(i).setCheckbox(true);
            }
            tvMyCrewListChooseAll.setText(getResources().getString(R.string.cancel_choose_all));
            myCrewAdapter.notifyDataSetChanged();
        } else {
            isChooseAll = true;
            for (int i = 0; i < crews.size(); i++) {
                crews.get(i).setCheckbox(false);
            }
            tvMyCrewListChooseAll.setText(getResources().getString(R.string.choose_all));
            myCrewAdapter.notifyDataSetChanged();
        }
    }

    private void addCrew() {
        startActivityForResult(new Intent(getActivity(), AddCrewActivity.class), Constants.REQUEST_CODE0);
    }

    private void editCrews() {
        if (isChoose) {
            rlChooseAllDelete.setVisibility(View.VISIBLE);
            tvShare.setText(getResources().getString(R.string.cancel));
            myCrewAdapter.controlCheckboxShow(isChoose);
            isChoose = false;
            myCrewAdapter.notifyDataSetChanged();
        } else {
            rlChooseAllDelete.setVisibility(View.GONE);
            tvShare.setText(getResources().getString(R.string.edit));
            myCrewAdapter.controlCheckboxShow(isChoose);
            isChoose = true;
            myCrewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case Constants.REQUEST_CODE0:
                    choosedCrewsLists = (ArrayList<CrewBean>) data.getSerializableExtra(Constants.ADD_CREW);
                    crews.addAll(choosedCrewsLists);
                    updateCrewsList();
                    myCrewAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }
}
