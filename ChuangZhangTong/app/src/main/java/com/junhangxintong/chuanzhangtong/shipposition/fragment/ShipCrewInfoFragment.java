package com.junhangxintong.chuanzhangtong.shipposition.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;
import com.junhangxintong.chuanzhangtong.common.MyApplication;
import com.junhangxintong.chuanzhangtong.common.NetServiceErrortBean;
import com.junhangxintong.chuanzhangtong.mine.activity.CrewInfoInputActivity;
import com.junhangxintong.chuanzhangtong.mine.activity.LoginRegisterActivity;
import com.junhangxintong.chuanzhangtong.mine.adapter.CrewListsAdapter;
import com.junhangxintong.chuanzhangtong.mine.bean.CrewServeBean;
import com.junhangxintong.chuanzhangtong.mine.bean.SendVerifyCodeBean;
import com.junhangxintong.chuanzhangtong.shipposition.activity.AddCrewActivity;
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
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

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
    @BindView(R.id.et_search_crew_name)
    EditText etSearchCrewName;
    @BindView(R.id.ll_search_crew_name)
    LinearLayout llSearchCrewName;
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
    private String roleId;
    private String shipId;

    private boolean isChoose = true;
    private boolean isChooseAll = true;
    private List<String> savelist = new ArrayList();
    private Map<String, Boolean> map = new HashMap<>();
    private List<CrewServeBean.DataBean.ArrayBean> choosedLists;
    private String userId;
    private List<CrewServeBean.DataBean.ArrayBean> crewLists;
    private CrewListsAdapter crewListsAdapter;
    private ArrayList<String> choosedCrewIdLists;

    @Override
    protected View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_crew_management, null);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        roleId = CacheUtils.getString(getActivity(), Constants.ROLEID);
        ivBack.setVisibility(View.GONE);
        tvTitle.setText(getResources().getString(R.string.crew_list));
        ivShare.setVisibility(View.GONE);

        tvSetting.setText(getResources().getString(R.string.add));
        tvShare.setText(getResources().getString(R.string.delete));
        tvNothing.setText(getResources().getString(R.string.add_first_crew));
        ivNothing.setImageResource(R.drawable.iv_no_crew);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected void initListener() {
        super.initListener();
        etSearchCrewName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String crewName = etSearchCrewName.getText().toString();
                netGetCrewsLists(crewName);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        userId = CacheUtils.getString(getActivity(), Constants.ID);

        Intent intent = getActivity().getIntent();
        shipId = intent.getStringExtra(Constants.ID);

        if (StringUtils.isNotEmpty(MyApplication.token)) {
            netGetCrewsLists("");
        }
    }

    private void netGetCrewsLists(String crweName) {
        NetUtils.postWithHeader(getActivity(), ConstantsUrls.SHIP_CREW_LISTS)
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.PAGE_SIZE, "100")
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.SHIP_ID, shipId)
                .addParams(Constants.PERSON_NAME, crweName)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(), Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(getActivity(), Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            NetServiceErrortBean netServiceErrort = new Gson().fromJson(response, NetServiceErrortBean.class);
                            String message = netServiceErrort.getMessage();
                            String code = netServiceErrort.getCode();
                            if (code.equals("200")) {
                                CrewServeBean crewServerBean = new Gson().fromJson(response, CrewServeBean.class);
                                crewLists = crewServerBean.getData().getArray();

                                updateCrewsList();
                                crewListsAdapter = new CrewListsAdapter(getActivity(), crewLists);
                                lvMyCrew.setAdapter(crewListsAdapter);

                            } else if (code.equals("601")) {
                                //清除了sp存储
                                getActivity().getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(getActivity(), Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(getActivity(), LoginRegisterActivity.class));
                            } else if (code.equals("404")) {
                                crewLists = new ArrayList<CrewServeBean.DataBean.ArrayBean>();
                                updateCrewsList();
                            } else {
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (StringUtils.isNotEmpty(MyApplication.token)) {
            netGetCrewsLists("");
        }
    }

    private void updateCrewsList() {
        if (crewLists.size() > 0) {
            lvMyCrew.setVisibility(View.VISIBLE);
            llNoCrew.setVisibility(View.GONE);
            if (!roleId.equals("3")) {
                tvShare.setVisibility(View.VISIBLE);
                tvSetting.setVisibility(View.VISIBLE);
            }
        } else {
            lvMyCrew.setVisibility(View.GONE);
            llNoCrew.setVisibility(View.VISIBLE);
            tvShare.setVisibility(View.GONE);
            rlChooseAllDelete.setVisibility(View.GONE);
        }
    }

    private void deleteChoosedItems() {

        //清除集合
        savelist.clear();
        map.clear();

        //选中的船员作为一个集合，集中处理
        choosedLists = new ArrayList<>();
        //选中的船员id作为一个集合，集中处理
        choosedCrewIdLists = new ArrayList<>();

        //找到选中的位置，并保存在map
        for (int i = 0; i < crewLists.size(); i++) {
            boolean checkbox = crewLists.get(i).isCheckbox();
            if (checkbox) {
                map.put(i + "", true);
                choosedCrewIdLists.add(String.valueOf(crewLists.get(i).getId()));
                choosedLists.add(crewLists.get(i));
            } else {
                map.put(i + "", false);
            }
        }
        netDeleteChoosedCrews();

        //遍历map
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            String key = entry.getKey();
            Boolean value = entry.getValue();
            if (value) {
                savelist.add(key);
            }
        }
    }

    private void netDeleteChoosedCrews() {

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < choosedCrewIdLists.size(); i++) {
            sb.append(choosedCrewIdLists.get(i) + ",");
        }

        String ids = sb.toString().substring(0, sb.length() - 1);

        if (StringUtils.isEmpty(ids)) {
            Toast.makeText(getActivity(), getResources().getString(R.string.please_choos_crew), Toast.LENGTH_SHORT).show();
            return;
        }
        NetUtils.postWithHeader(getActivity(), ConstantsUrls.SHIP_DELETE_CREW)
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.IDS, ids)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(), Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(getActivity(), Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            SendVerifyCodeBean sendVerifyCode = new Gson().fromJson(response, SendVerifyCodeBean.class);
                            String message = sendVerifyCode.getMessage();
                            String code = sendVerifyCode.getCode();
                            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            if (code.equals("601")) {
                                //清除了sp存储
                                getActivity().getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(getActivity(), Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(getActivity(), LoginRegisterActivity.class));
                            } else if (code.equals("200")) {
                                updateCrewsList();
                                crewListsAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }


    public void chooseAllOrNot() {
        if (isChooseAll) {
            isChooseAll = false;
            for (int i = 0; i < crewLists.size(); i++) {
                crewLists.get(i).setCheckbox(true);
            }
            tvMyCrewListChooseAll.setText(getResources().getString(R.string.cancel_choose_all));
            crewListsAdapter.notifyDataSetChanged();
        } else {
            isChooseAll = true;
            for (int i = 0; i < crewLists.size(); i++) {
                crewLists.get(i).setCheckbox(false);
            }
            tvMyCrewListChooseAll.setText(getResources().getString(R.string.choose_all));
            crewListsAdapter.notifyDataSetChanged();
        }
    }

    private void addCrew() {
        startActivity(new Intent(getActivity(), CrewInfoInputActivity.class));
    }

    private void editCrews() {
        if (isChoose) {
            rlChooseAllDelete.setVisibility(View.VISIBLE);
            tvShare.setText(getResources().getString(R.string.cancel));
            crewListsAdapter.controlCheckboxShow(isChoose);
            isChoose = false;
            crewListsAdapter.notifyDataSetChanged();
        } else {
            rlChooseAllDelete.setVisibility(View.GONE);
            tvShare.setText(getResources().getString(R.string.delete));
            crewListsAdapter.controlCheckboxShow(isChoose);
            isChoose = true;
            crewListsAdapter.notifyDataSetChanged();
        }
    }

    @OnClick({R.id.iv_back, R.id.iv_share, R.id.tv_setting, R.id.tv_share, R.id.tv_add_ship, R.id.tv_my_crew_list_choose_all, R.id.tv_my_crew_list_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().finish();
                break;
            case R.id.iv_share:
                gotoAddCrewActivity();
                break;
            case R.id.tv_setting:
                gotoAddCrewActivity();
                break;
            case R.id.tv_share:
                editCrews();
                break;
            case R.id.tv_add_ship:
                gotoAddCrewActivity();
                break;
            case R.id.tv_my_crew_list_choose_all:
                chooseAllOrNot();
                break;
            case R.id.tv_my_crew_list_delete:
                deleteChoosedItems();
                break;
        }
    }

    private void gotoAddCrewActivity() {
        Intent intent = new Intent(getActivity(), AddCrewActivity.class);
        intent.putExtra(Constants.SHIP_ID, shipId);
        startActivity(intent);
    }
}
