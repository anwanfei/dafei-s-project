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

public class MyCertificateActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.iv_nothing)
    ImageView ivNothing;
    @BindView(R.id.tv_nothing)
    TextView tvNothing;
    @BindView(R.id.tv_add_ship)
    TextView tvAddShip;
    @BindView(R.id.ll_no_fleet)
    LinearLayout llNoFleet;
    @BindView(R.id.gv_certificate)
    GridView gvCertificate;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_my_crew_list_choose_all)
    TextView tvMyCrewListChooseAll;
    @BindView(R.id.tv_my_crew_list_delete)
    TextView tvMyCrewListDelete;
    @BindView(R.id.rl_choose_all_delete)
    RelativeLayout rlChooseAllDelete;
    private List<CrewCeretificateRemindBean.DataBean.ArrayBean> myCertificatesLists;
    private CrewCertificateAdapter crewCertificateAdapter;
    private String userId;
    private boolean isChoose = true;
    private boolean isChooseAll = true;
    private ArrayList<CrewCeretificateRemindBean.DataBean.ArrayBean> choosedLists;
    private ArrayList<String> choosedCrewIdLists;
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
        tvTitle.setText(getResources().getString(R.string.mycertificate));
        tvSetting.setText(getResources().getString(R.string.add));
        ivNothing.setImageResource(R.drawable.iv_no_certificate);
        tvNothing.setText(getResources().getString(R.string.add_first_certificate));
        tvShare.setText(getResources().getString(R.string.delete));
        tvShare.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {

        userId = CacheUtils.getString(this, Constants.ID);
        netGetcertificate(userId);
    }

    private void netGetcertificate(String userId) {
        NetUtils.postWithHeader(this, ConstantsUrls.CREW_CERTIFICATE_LISTS)
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.PAGE_SIZE, "100")
                .addParams(Constants.ID, userId)
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        CrewCeretificateRemindBean crewCeretificateBean = new Gson().fromJson(response, CrewCeretificateRemindBean.class);

                        myCertificatesLists = crewCeretificateBean.getData().getArray();

                        updataGridview();
                        crewCertificateAdapter = new CrewCertificateAdapter(MyCertificateActivity.this, myCertificatesLists);
                        gvCertificate.setAdapter(crewCertificateAdapter);

                        gvCertificate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(MyCertificateActivity.this, CrewCertificateDetailsActivity.class);
                                int id = myCertificatesLists.get(i).getId();
                                intent.putExtra(Constants.ID, String.valueOf(id));
                                intent.putExtra(Constants.CERTIFICATE_TYPE, 1);
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    protected void onDataEmpty(String message) {
                        super.onDataEmpty(message);
                        myCertificatesLists = new ArrayList<CrewCeretificateRemindBean.DataBean.ArrayBean>();
                        updataGridview();
                    }
                });
    }

    private void updataGridview() {
        if (myCertificatesLists.size() > 0) {
            gvCertificate.setVisibility(View.VISIBLE);
            llNoFleet.setVisibility(View.GONE);
            tvShare.setVisibility(View.VISIBLE);
        } else {
            gvCertificate.setVisibility(View.GONE);
            llNoFleet.setVisibility(View.VISIBLE);
            tvShare.setVisibility(View.GONE);
            rlChooseAllDelete.setVisibility(View.GONE);
            tvShare.setText(getResources().getString(R.string.delete));
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_certificate;
    }

    @OnClick({R.id.iv_back, R.id.tv_add_ship, R.id.tv_setting, R.id.tv_share, R.id.tv_my_crew_list_choose_all, R.id.tv_my_crew_list_delete, R.id.rl_choose_all_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add_ship:
                gotoAddCertificateActivity();
                break;
            case R.id.tv_setting:
                gotoAddCertificateActivity();
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
            case R.id.rl_choose_all_delete:
                break;
        }
    }

    public void chooseAllOrNot() {
        if (isChooseAll) {
            isChooseAll = false;
            for (int i = 0; i < myCertificatesLists.size(); i++) {
                myCertificatesLists.get(i).setCheckbox(true);
            }
            tvMyCrewListChooseAll.setText(getResources().getString(R.string.cancel_choose_all));
            crewCertificateAdapter.notifyDataSetChanged();
        } else {
            isChooseAll = true;
            for (int i = 0; i < myCertificatesLists.size(); i++) {
                myCertificatesLists.get(i).setCheckbox(false);
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
        for (int i = 0; i < myCertificatesLists.size(); i++) {
            boolean checkbox = myCertificatesLists.get(i).isCheckbox();
            if (checkbox) {
                map.put(i + "", true);
                choosedCrewIdLists.add(String.valueOf(myCertificatesLists.get(i).getId()));
                choosedLists.add(myCertificatesLists.get(i));
            } else {
                map.put(i + "", false);
            }
        }

        netDeleteChoosedCrews();
        updataGridview();
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
            Toast.makeText(MyCertificateActivity.this, getResources().getString(R.string.choose_one), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(MyCertificateActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(MyCertificateActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            SendVerifyCodeBean sendVerifyCode = new Gson().fromJson(response, SendVerifyCodeBean.class);
                            String message = sendVerifyCode.getMessage();
                            String code = sendVerifyCode.getCode();
                            Toast.makeText(MyCertificateActivity.this, message, Toast.LENGTH_SHORT).show();
                            if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(MyCertificateActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(MyCertificateActivity.this, LoginRegisterActivity.class));
                                finish();
                            } else if (code.equals("200")) {
                                myCertificatesLists.removeAll(choosedLists);
                                updataGridview();
                                crewCertificateAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }


    private void gotoAddCertificateActivity() {
        Intent intent = new Intent(MyCertificateActivity.this, AddCrewCertificateActivity.class);
        intent.putExtra(Constants.CREW_CERTIFICATE, 1);
        intent.putExtra(Constants.ID, userId);
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

    @Override
    protected void onResume() {
        super.onResume();
        netGetcertificate(userId);
    }
}
