package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.common.NetServiceCodeBean;
import com.junhangxintong.chuanzhangtong.mine.adapter.CrewCertificateAdapter;
import com.junhangxintong.chuanzhangtong.mine.bean.CrewCeretificateRemindBean;
import com.junhangxintong.chuanzhangtong.mine.bean.SendVerifyCodeBean;
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

public class CrewCertificateListsActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.gv_certificate)
    GridView gvCertificate;
    @BindView(R.id.iv_nothing)
    ImageView ivNothing;
    @BindView(R.id.tv_nothing)
    TextView tvNothing;
    @BindView(R.id.tv_add_ship)
    TextView tvAddShip;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.ll_no_fleet)
    LinearLayout llNoFleet;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_my_crew_list_choose_all)
    TextView tvMyCrewListChooseAll;
    @BindView(R.id.tv_my_crew_list_delete)
    TextView tvMyCrewListDelete;
    @BindView(R.id.rl_choose_all_delete)
    RelativeLayout rlChooseAllDelete;
    private boolean isChoose = true;
    private boolean isChooseAll = true;

    private String id;
    private List<CrewCeretificateRemindBean.DataBean.ArrayBean> crewCertificatesLists;
    private CrewCertificateAdapter crewCertificateAdapter;
    private ArrayList<CrewCeretificateRemindBean.DataBean.ArrayBean> choosedLists;
    private ArrayList<String> choosedCrewIdLists;
    private String userId;
    private List<String> savelist = new ArrayList();
    private Map<String, Boolean> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvSetting.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.crew_certificate));
        ivShare.setBackgroundResource(R.drawable.add_certificate);
        tvSetting.setText(getResources().getString(R.string.add));
        tvShare.setText(getResources().getString(R.string.delete));
        tvShare.setVisibility(View.VISIBLE);
        ivNothing.setImageResource(R.drawable.iv_no_certificate);
        tvNothing.setText(getResources().getString(R.string.add_first_certificate));
        tvType.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra(Constants.ID);
        userId = CacheUtils.getString(this, Constants.ID);

        netGetCrewCertificateLists(id);
    }

    private void netGetCrewCertificateLists(String id) {
        NetUtils.postWithHeader(this, ConstantsUrls.CREW_CERTIFICATE_LISTS)
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.PAGE_SIZE, "100")
                .addParams(Constants.ID, id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(CrewCertificateListsActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(CrewCertificateListsActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            NetServiceCodeBean netServiceErrort = new Gson().fromJson(response, NetServiceCodeBean.class);
                            String message = netServiceErrort.getMessage();
                            String code = netServiceErrort.getCode();
                            if (code.equals("200")) {
                                CrewCeretificateRemindBean crewCeretificateBean = new Gson().fromJson(response, CrewCeretificateRemindBean.class);

                                crewCertificatesLists = crewCeretificateBean.getData().getArray();

                                updataGridview();
                                crewCertificateAdapter = new CrewCertificateAdapter(CrewCertificateListsActivity.this, crewCertificatesLists);
                                gvCertificate.setAdapter(crewCertificateAdapter);

                                gvCertificate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Intent intent = new Intent(CrewCertificateListsActivity.this, CrewCertificateDetailsActivity.class);
                                        int id = crewCertificatesLists.get(i).getId();
                                        intent.putExtra(Constants.ID, String.valueOf(id));
                                        intent.putExtra(Constants.CERTIFICATE_TYPE, 1);
                                        startActivity(intent);
                                    }
                                });

                            } else if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(CrewCertificateListsActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(CrewCertificateListsActivity.this, LoginRegisterActivity.class));
                            } else if (code.equals("404")) {
                                crewCertificatesLists = new ArrayList<CrewCeretificateRemindBean.DataBean.ArrayBean>();
                                updataGridview();
                            } else {
                                Toast.makeText(CrewCertificateListsActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void updataGridview() {
        if (crewCertificatesLists.size() > 0) {
            gvCertificate.setVisibility(View.VISIBLE);
            llNoFleet.setVisibility(View.GONE);
            tvType.setVisibility(View.VISIBLE);
            tvShare.setVisibility(View.VISIBLE);
        } else {
            gvCertificate.setVisibility(View.GONE);
            llNoFleet.setVisibility(View.VISIBLE);
            tvType.setVisibility(View.GONE);
            tvShare.setVisibility(View.GONE);
            rlChooseAllDelete.setVisibility(View.GONE);
            tvShare.setText(getResources().getString(R.string.delete));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        netGetCrewCertificateLists(id);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_certificate_lists;
    }

    @OnClick({R.id.iv_back, R.id.iv_share, R.id.tv_setting, R.id.tv_add_ship, R.id.tv_my_crew_list_choose_all, R.id.tv_my_crew_list_delete, R.id.tv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_share:
                editCrews();
                break;
            case R.id.tv_setting:
                gotoAddCertificateActivity();
                break;
            case R.id.tv_add_ship:
                gotoAddCertificateActivity();
                break;
            case R.id.tv_my_crew_list_choose_all:
                chooseAllOrNot();
                break;
            case R.id.tv_my_crew_list_delete:
                deleteChoosedItems();
                break;
        }
    }

    public void chooseAllOrNot() {
        if (isChooseAll) {
            isChooseAll = false;
            for (int i = 0; i < crewCertificatesLists.size(); i++) {
                crewCertificatesLists.get(i).setCheckbox(true);
            }
            tvMyCrewListChooseAll.setText(getResources().getString(R.string.cancel_choose_all));
            crewCertificateAdapter.notifyDataSetChanged();
        } else {
            isChooseAll = true;
            for (int i = 0; i < crewCertificatesLists.size(); i++) {
                crewCertificatesLists.get(i).setCheckbox(false);
            }
            tvMyCrewListChooseAll.setText(getResources().getString(R.string.choose_all));
            crewCertificateAdapter.notifyDataSetChanged();
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
        for (int i = 0; i < crewCertificatesLists.size(); i++) {
            boolean checkbox = crewCertificatesLists.get(i).isCheckbox();
            if (checkbox) {
                map.put(i + "", true);
                choosedCrewIdLists.add(String.valueOf(crewCertificatesLists.get(i).getId()));
                choosedLists.add(crewCertificatesLists.get(i));
            } else {
                map.put(i + "", false);
            }
        }

        netDeleteChoosedCrews();
        updateCrewsList();
        crewCertificateAdapter.notifyDataSetChanged();

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

        if (StringUtils.isBlank(sb.toString())) {
            Toast.makeText(CrewCertificateListsActivity.this, getResources().getString(R.string.choose_one), Toast.LENGTH_SHORT).show();
            return;
        }
        String ids = sb.toString().substring(0, sb.length() - 1);
        NetUtils.postWithHeader(this, ConstantsUrls.DELETE_CREW_CERTIFICATE)
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.IDS, ids)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(CrewCertificateListsActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(CrewCertificateListsActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            SendVerifyCodeBean sendVerifyCode = new Gson().fromJson(response, SendVerifyCodeBean.class);
                            String message = sendVerifyCode.getMessage();
                            String code = sendVerifyCode.getCode();
                            Toast.makeText(CrewCertificateListsActivity.this, message, Toast.LENGTH_SHORT).show();
                            if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(CrewCertificateListsActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(CrewCertificateListsActivity.this, LoginRegisterActivity.class));
                                finish();
                            } else if (code.equals("200")) {
                                crewCertificatesLists.removeAll(choosedLists);
                                updataGridview();
                                crewCertificateAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }

    private void updateCrewsList() {
        if (crewCertificatesLists.size() > 0) {
            gvCertificate.setVisibility(View.VISIBLE);
            llNoFleet.setVisibility(View.GONE);
            tvShare.setVisibility(View.VISIBLE);
            tvSetting.setVisibility(View.VISIBLE);
        } else {
            gvCertificate.setVisibility(View.GONE);
            llNoFleet.setVisibility(View.VISIBLE);
            tvShare.setVisibility(View.GONE);
            tvSetting.setVisibility(View.GONE);
            rlChooseAllDelete.setVisibility(View.GONE);
        }
    }

    private void gotoAddCertificateActivity() {
        Intent intent = new Intent(CrewCertificateListsActivity.this, AddCrewCertificateActivity.class);
        intent.putExtra(Constants.CREW_CERTIFICATE, 1);
        intent.putExtra(Constants.ID, id);
        startActivity(intent);
    }

    private void editCrews() {
        if (isChoose) {
            rlChooseAllDelete.setVisibility(View.VISIBLE);
            tvShare.setText(getResources().getString(R.string.cancel));
            crewCertificateAdapter.controlCheckboxShow(isChoose);
            isChoose = false;
            crewCertificateAdapter.notifyDataSetChanged();
        } else {
            rlChooseAllDelete.setVisibility(View.GONE);
            tvShare.setText(getResources().getString(R.string.delete));
            crewCertificateAdapter.controlCheckboxShow(isChoose);
            isChoose = true;
            crewCertificateAdapter.notifyDataSetChanged();
        }
    }

}
