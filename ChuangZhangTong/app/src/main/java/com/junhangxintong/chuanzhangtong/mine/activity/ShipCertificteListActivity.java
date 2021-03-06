package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.mine.adapter.ShipCertificatesAndInsrancesAdapter;
import com.junhangxintong.chuanzhangtong.mine.bean.SendVerifyCodeBean;
import com.junhangxintong.chuanzhangtong.mine.bean.ShipCertificateInsuranceListsBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.DensityUtil;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.junhangxintong.chuanzhangtong.view.MyGridview;
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


public class ShipCertificteListActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.iv_nothing)
    ImageView ivNothing;
    @BindView(R.id.tv_nothing)
    TextView tvNothing;
    @BindView(R.id.tv_add_ship)
    TextView tvAddShip;
    @BindView(R.id.ll_no_fleet)
    LinearLayout llNoFleet;
    @BindView(R.id.rv_certificate)
    RecyclerView rvCertificate;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.gv_certificate)
    GridView gvCertificate;
    @BindView(R.id.gv_insurance)
    GridView gvInsurance;
    @BindView(R.id.ll_certificate)
    LinearLayout llCertificate;
    @BindView(R.id.ll_insurance)
    LinearLayout llInsurance;
    @BindView(R.id.gv_certificate_insurance)
    MyGridview gvCertificateInsurance;
    @BindView(R.id.tv_my_crew_list_choose_all)
    TextView tvMyCrewListChooseAll;
    @BindView(R.id.tv_my_crew_list_delete)
    TextView tvMyCrewListDelete;
    @BindView(R.id.rl_choose_all_delete)
    RelativeLayout rlChooseAllDelete;
    private PopupWindow popupWindow;
    private boolean isShowPop;
    private String id;
    private List<ShipCertificateInsuranceListsBean.DataBean.ArrayBean> shipCertificateInsuranceLists;
    private String userId;
    private boolean isChoose = true;
    private ShipCertificatesAndInsrancesAdapter shipCertificatesAndInsrancesAdapter;
    private boolean isChooseAll = true;
    private List<String> savelist = new ArrayList();
    private Map<String, Boolean> map = new HashMap<>();
    private ArrayList<ShipCertificateInsuranceListsBean.DataBean.ArrayBean> choosedLists;
    private ArrayList<String> choosedCrewIdLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.ship_certificate));
        tvSetting.setVisibility(View.VISIBLE);
        ivShare.setVisibility(View.GONE);
        ivShare.setBackgroundResource(R.drawable.add_certificate);
        tvSetting.setText(getResources().getString(R.string.add));
        tvShare.setVisibility(View.VISIBLE);
        tvShare.setText(getResources().getString(R.string.delete));
        ivNothing.setImageResource(R.drawable.iv_no_certificate);
        tvNothing.setText(getResources().getString(R.string.add_first_certificate));
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra(Constants.ID);
        userId = CacheUtils.getString(this, Constants.ID);
        netGetCertificateOrInsurances();
    }

    private void netGetCertificateOrInsurances() {
        NetUtils.postWithHeader(this, ConstantsUrls.SHIP_CERTIFICATE_LIST)
                .addParams(Constants.PAGE_SIZE, "100")
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.SHIP_ID, id)
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onDataEmpty(String message) {
                        super.onDataEmpty(message);
                        shipCertificateInsuranceLists = new ArrayList<ShipCertificateInsuranceListsBean.DataBean.ArrayBean>();
                        updata();
                    }

                    @Override
                    protected void onSuccess(String response, String message) {
                        /*final List<CustomCertificateBean> shipCertificates = new ArrayList<CustomCertificateBean>();
                                final List<CustomCertificateBean> shipInsurances = new ArrayList<CustomCertificateBean>();*/
                        ShipCertificateInsuranceListsBean shipCertificateInsuranceListsBean = new Gson().fromJson(response, ShipCertificateInsuranceListsBean.class);
                        shipCertificateInsuranceLists = shipCertificateInsuranceListsBean.getData().getArray();
                        shipCertificatesAndInsrancesAdapter = new ShipCertificatesAndInsrancesAdapter(ShipCertificteListActivity.this, shipCertificateInsuranceLists);
                        updata();
                        gvCertificateInsurance.setAdapter(shipCertificatesAndInsrancesAdapter);
                        gvCertificateInsurance.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                int certifType = shipCertificateInsuranceLists.get(i).getCertifType();
                                int id = shipCertificateInsuranceLists.get(i).getId();
                                if (certifType == 1) {
                                    Intent intent = new Intent(ShipCertificteListActivity.this, ShipCertificateDetailsActivity.class);
                                    intent.putExtra(Constants.ID, String.valueOf(id));
                                    startActivity(intent);

                                } else {
                                    Intent intent = new Intent(ShipCertificteListActivity.this, InsuranceDetailsActivity.class);
                                    intent.putExtra(Constants.ID, String.valueOf(id));
                                    startActivity(intent);
                                }
                            }
                        });
                                /*CustomCertificateBean customCertificateBean = null;
                                for (int i = 0; i < shipCertificateInsuranceLists.size(); i++) {
                                    customCertificateBean = new CustomCertificateBean();
                                    int certifType = shipCertificateInsuranceLists.get(i).getCertifType();

                                    String name = shipCertificateInsuranceLists.get(i).getName();
                                    String certifNo = shipCertificateInsuranceLists.get(i).getCertifNo();
                                    String issueOrganization = shipCertificateInsuranceLists.get(i).getIssueOrganization();
                                    String validDate = shipCertificateInsuranceLists.get(i).getValidDate();
                                    int certifId = shipCertificateInsuranceLists.get(i).getId();
                                    String shipName = shipCertificateInsuranceLists.get(i).getShipName();

                                    customCertificateBean.setBinahao(certifNo);
                                    customCertificateBean.setId(String.valueOf(certifId));
                                    customCertificateBean.setIssueArgument(issueOrganization);
                                    customCertificateBean.setName(name);
                                    customCertificateBean.setValidDate(validDate);
                                    customCertificateBean.setShipName(shipName);

                                    if (certifType == 1) {
                                        shipCertificates.add(customCertificateBean);
                                    } else if (certifType == 2) {
                                        shipInsurances.add(customCertificateBean);
                                    }
                                }

                                if (shipCertificates.size() > 0) {
                                    llCertificate.setVisibility(View.VISIBLE);

                                } else {
                                    llCertificate.setVisibility(View.GONE);
                                }

                                if (shipInsurances.size() > 0) {
                                    llInsurance.setVisibility(View.VISIBLE);
                                } else {
                                    llInsurance.setVisibility(View.GONE);
                                }

                                updata();

                                ShipCertificatesInsrancesAdapter shipCertificatesAdapter = new ShipCertificatesInsrancesAdapter(ShipCertificteListActivity.this, shipCertificates);
                                ShipInsrancesAdapter shipInsrancesAdapter = new ShipInsrancesAdapter(ShipCertificteListActivity.this, shipInsurances);
                                gvCertificate.setAdapter(shipCertificatesAdapter);
                                gvInsurance.setAdapter(shipInsrancesAdapter);

                                gvInsurance.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Intent intent = new Intent(ShipCertificteListActivity.this, InsuranceDetailsActivity.class);
                                        intent.putExtra(Constants.ID, shipInsurances.get(i).getId());
                                        startActivity(intent);
                                    }
                                });

                                gvCertificate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Intent intent = new Intent(ShipCertificteListActivity.this, CrewCertificateDetailsActivity.class);
                                        intent.putExtra(Constants.ID, shipCertificates.get(i).getId());
                                        startActivity(intent);
                                    }
                                });*/
                    }
                });
    }

    private void gotoShipCertificateDetailsActivity(int position) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        netGetCertificateOrInsurances();
//        shipCertificateInsuranceAdapter.notifyDataSetChanged();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ship_certificte_list;
    }

    @OnClick({R.id.iv_back, R.id.tv_share, R.id.tv_setting, R.id.tv_add_ship, R.id.ll_no_fleet, R.id.tv_my_crew_list_choose_all, R.id.tv_my_crew_list_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_share:
                editCrews();
                break;
            case R.id.tv_setting:
                if (isShowPop) {
                    hidePop();
                    isShowPop = false;
                } else {
                    showPopOfAddType();
                    isShowPop = true;
                }
                break;
            case R.id.tv_add_ship:
                if (isShowPop) {
                    hidePop();
                    isShowPop = false;
                } else {
                    showPopOfAddType();
                    isShowPop = true;
                }
                break;
            case R.id.ll_no_fleet:
                break;
            case R.id.tv_my_crew_list_choose_all:
                chooseAllOrNot();
                break;
            case R.id.tv_my_crew_list_delete:
                deleteChoosedItems();
                break;
        }
    }

    private void editCrews() {
        if (isChoose) {
            rlChooseAllDelete.setVisibility(View.VISIBLE);
            tvShare.setText(getResources().getString(R.string.cancel));
            shipCertificatesAndInsrancesAdapter.controlCheckboxShow(isChoose);
            isChoose = false;
            shipCertificatesAndInsrancesAdapter.notifyDataSetChanged();
        } else {
            rlChooseAllDelete.setVisibility(View.GONE);
            tvShare.setText(getResources().getString(R.string.delete));
            shipCertificatesAndInsrancesAdapter.controlCheckboxShow(isChoose);
            isChoose = true;
            shipCertificatesAndInsrancesAdapter.notifyDataSetChanged();
        }
    }

    public void chooseAllOrNot() {
        if (isChooseAll) {
            isChooseAll = false;
            for (int i = 0; i < shipCertificateInsuranceLists.size(); i++) {
                shipCertificateInsuranceLists.get(i).setCheckbox(true);
            }
            tvMyCrewListChooseAll.setText(getResources().getString(R.string.cancel_choose_all));
            shipCertificatesAndInsrancesAdapter.notifyDataSetChanged();
        } else {
            isChooseAll = true;
            for (int i = 0; i < shipCertificateInsuranceLists.size(); i++) {
                shipCertificateInsuranceLists.get(i).setCheckbox(false);
            }
            tvMyCrewListChooseAll.setText(getResources().getString(R.string.choose_all));
            shipCertificatesAndInsrancesAdapter.notifyDataSetChanged();
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
        for (int i = 0; i < shipCertificateInsuranceLists.size(); i++) {
            boolean checkbox = shipCertificateInsuranceLists.get(i).isCheckbox();
            if (checkbox) {
                map.put(i + "", true);
                choosedCrewIdLists.add(String.valueOf(shipCertificateInsuranceLists.get(i).getId()));
                choosedLists.add(shipCertificateInsuranceLists.get(i));
            } else {
                map.put(i + "", false);
            }
        }

        netDeleteChoosedCrews();
        updata();
        shipCertificatesAndInsrancesAdapter.notifyDataSetChanged();

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
            Toast.makeText(ShipCertificteListActivity.this, getResources().getString(R.string.choose_one), Toast.LENGTH_SHORT).show();
            return;
        }
        String ids = sb.toString().substring(0, sb.length() - 1);
        NetUtils.postWithHeader(this, ConstantsUrls.DELETE_SHIP_CERTIFICATE)
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.IDS, ids)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(ShipCertificteListActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(ShipCertificteListActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            SendVerifyCodeBean sendVerifyCode = new Gson().fromJson(response, SendVerifyCodeBean.class);
                            String message = sendVerifyCode.getMessage();
                            String code = sendVerifyCode.getCode();
                            Toast.makeText(ShipCertificteListActivity.this, message, Toast.LENGTH_SHORT).show();
                            if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(ShipCertificteListActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(ShipCertificteListActivity.this, LoginRegisterActivity.class));
                                finish();
                            } else if (code.equals("200")) {
                                shipCertificateInsuranceLists.removeAll(choosedLists);
                                updata();
                                shipCertificatesAndInsrancesAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }

    private void updata() {
        if (shipCertificateInsuranceLists.size() > 0) {
            rvCertificate.setVisibility(View.VISIBLE);
            llNoFleet.setVisibility(View.GONE);
            tvShare.setVisibility(View.VISIBLE);
        } else {
            rvCertificate.setVisibility(View.GONE);
            llNoFleet.setVisibility(View.VISIBLE);
            tvShare.setVisibility(View.GONE);
            llInsurance.setVisibility(View.GONE);
            llCertificate.setVisibility(View.GONE);
            rlChooseAllDelete.setVisibility(View.GONE);
            tvShare.setText(getResources().getString(R.string.delete));
        }
    }

    private void showPopOfAddType() {
        View view = LinearLayout.inflate(this, R.layout.pop_ship_certificate_add_type, null);

        TextView tv_add_certificate = (TextView) view.findViewById(R.id.tv_add_certificate);
        TextView tv_add_insurance = (TextView) view.findViewById(R.id.tv_add_insurance);

        popupWindow = new PopupWindow(view, DensityUtil.dp2px(this, 100), LinearLayout.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setContentView(view);

        //点击pop以外的部分消失
        popupWindow.setOutsideTouchable(true);

        popupWindow.showAsDropDown(tvSetting, 0, 0);

        tv_add_certificate.setOnClickListener(this);
        tv_add_insurance.setOnClickListener(this);
    }

    private void hidePop() {
        popupWindow.dismiss();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add_insurance:
                goAddInsuranceActivity();
                hidePop();
                break;
            case R.id.tv_add_certificate:
                gotoAddCertificateActivity();
                hidePop();
                break;
        }
    }

    private void gotoAddCertificateActivity() {
        Intent intent = new Intent(ShipCertificteListActivity.this, AddShipCertificateActivity.class);
        intent.putExtra(Constants.ID, id);
        startActivity(intent);
    }

    private void goAddInsuranceActivity() {
        Intent intent = new Intent(ShipCertificteListActivity.this, AddInsuranceActivity.class);
        intent.putExtra(Constants.ID, id);
        startActivity(intent);
    }

}
