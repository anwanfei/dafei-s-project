package com.junhangxintong.chuanzhangtong.shipposition.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.junhangxintong.chuanzhangtong.mine.activity.CrewInfoInputActivity;
import com.junhangxintong.chuanzhangtong.mine.activity.LoginRegisterActivity;
import com.junhangxintong.chuanzhangtong.mine.bean.CrewBean;
import com.junhangxintong.chuanzhangtong.mine.bean.CrewServeBean;
import com.junhangxintong.chuanzhangtong.mine.bean.SendVerifyCodeBean;
import com.junhangxintong.chuanzhangtong.shipposition.adapter.AdditiveCrewAdapter;
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

public class AddCrewActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lv_additive_crew)
    ListView lvAdditiveCrew;
    @BindView(R.id.et_additive_crew_search)
    EditText etAdditiveCrewSearch;
    @BindView(R.id.ll_additive_crew_search)
    LinearLayout llAdditiveCrewSearch;
    @BindView(R.id.tv_choosed_crew_num)
    TextView tvChoosedCrewNum;
    @BindView(R.id.tv_add_ok)
    TextView tvAddOk;
    @BindView(R.id.rl_add_ok)
    RelativeLayout rlAddOk;
    @BindView(R.id.iv_nothing)
    ImageView ivNothing;
    @BindView(R.id.tv_nothing)
    TextView tvNothing;
    @BindView(R.id.tv_add_ship)
    TextView tvAddShip;
    @BindView(R.id.ll_no_crew)
    LinearLayout llNoCrew;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    private ArrayList<CrewBean> crews;
    private ArrayList<CrewBean> choosedCrewLists;
    private Map<String, Boolean> map = new HashMap<>();
    private AdditiveCrewAdapter additiveCrewAdapter;
    private List<String> savelist = new ArrayList();
    private ArrayList<CrewServeBean.DataBean.ArrayBean> choosedLists;
    private String userId;
    private List<CrewServeBean.DataBean.ArrayBean> crewLists;
    private String shipId;
    private ArrayList<String> choosedCrewIdLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.choose_additive_crews));
        tvNothing.setText(getResources().getString(R.string.add_first_crew));
        tvSetting.setText(getResources().getString(R.string.add));
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        shipId = intent.getStringExtra(Constants.SHIP_ID);

        userId = CacheUtils.getString(this, Constants.ID);

        netGetAddtiveCrewsLists("");

        crews = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            CrewBean crewBean = new CrewBean();
            crewBean.setCrewName("船员" + i + "号");
            crewBean.setDuty("大副" + i + "号");
            crewBean.setJobNum("00" + i + "号");
            crewBean.setNationality("中国");
            crews.add(crewBean);
        }

        // TODO: 2017/8/9  搜索要添加的船员---数据库query
        // TODO: 2017/8/26 有结果和没有结果
    }

    private void netGetAddtiveCrewsLists(String crweName) {
        NetUtils.postWithHeader(this, ConstantsUrls.SHIP_ADDTIVE_CRWE_LIST)
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.PAGE_SIZE, "100")
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.PERSON_NAME, crweName)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(AddCrewActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(AddCrewActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            NetServiceCodeBean netServiceErrort = new Gson().fromJson(response, NetServiceCodeBean.class);
                            String message = netServiceErrort.getMessage();
                            String code = netServiceErrort.getCode();
                            if (code.equals("200")) {
                                CrewServeBean crewServerBean = new Gson().fromJson(response, CrewServeBean.class);
                                crewLists = crewServerBean.getData().getArray();

                                updateCrewsList();
                                additiveCrewAdapter = new AdditiveCrewAdapter(AddCrewActivity.this, (ArrayList<CrewServeBean.DataBean.ArrayBean>) crewLists, tvChoosedCrewNum);
                                lvAdditiveCrew.setAdapter(additiveCrewAdapter);

                            } else if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(AddCrewActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(AddCrewActivity.this, LoginRegisterActivity.class));
                            } else if (code.equals("404")) {
                                crewLists = new ArrayList<CrewServeBean.DataBean.ArrayBean>();
                                updateCrewsList();
                            } else {
                                Toast.makeText(AddCrewActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void updateCrewsList() {
        if (crewLists.size() > 0) {
            lvAdditiveCrew.setVisibility(View.VISIBLE);
            llNoCrew.setVisibility(View.GONE);
            rlAddOk.setVisibility(View.VISIBLE);
            tvSetting.setVisibility(View.GONE);
        } else {
            lvAdditiveCrew.setVisibility(View.GONE);
            llNoCrew.setVisibility(View.VISIBLE);
            rlAddOk.setVisibility(View.GONE);
            tvSetting.setVisibility(View.VISIBLE);
            tvNothing.setText(getResources().getString(R.string.input_crew_info));
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_crew;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void determineAddCrews() {

        //清除集合
        savelist.clear();
        map.clear();

        //选中的船作为一个集合，集中处理
        choosedLists = new ArrayList<>();

        choosedCrewIdLists = new ArrayList<>();

        //找到选中的位置，并保存在map
        for (int i = 0; i < crewLists.size(); i++) {
            boolean checkbox = crewLists.get(i).isCheckbox();
            if (checkbox) {
                map.put(i + "", true);
                crewLists.get(i).setCheckbox(false);
                choosedLists.add(crewLists.get(i));
                choosedCrewIdLists.add(String.valueOf(crewLists.get(i).getId()));
            } else {
                map.put(i + "", false);
            }
        }

        //遍历map
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            String key = entry.getKey();
            Boolean value = entry.getValue();
            if (value) {
                savelist.add(key);
            }
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < choosedCrewIdLists.size(); i++) {
            sb.append(choosedCrewIdLists.get(i) + ",");
        }

        String ids = sb.toString().substring(0, sb.length() - 1);
        if (StringUtils.isNotBlank(ids)) {
            netConfirmAddCrew(ids);
        }
    }

    private void netConfirmAddCrew(String ids) {
        NetUtils.postWithHeader(this, ConstantsUrls.SHIP_ADD_CREW)
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.SHIP_ID, shipId)
                .addParams(Constants.IDS, ids)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(AddCrewActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(AddCrewActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            SendVerifyCodeBean sendVerifyCode = new Gson().fromJson(response, SendVerifyCodeBean.class);
                            String message = sendVerifyCode.getMessage();
                            String code = sendVerifyCode.getCode();
                            Toast.makeText(AddCrewActivity.this, message, Toast.LENGTH_SHORT).show();
                            if (code.equals("601")) {
                                //清除了sp存储
                                AddCrewActivity.this.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(AddCrewActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(AddCrewActivity.this, LoginRegisterActivity.class));
                            } else if (code.equals("200")) {
                                finish();
                            }
                        }
                    }
                });
    }

    @OnClick({R.id.iv_back, R.id.tv_setting, R.id.tv_add_ship, R.id.tv_add_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_setting:
                addCrew();
                break;
            case R.id.tv_add_ship:
                addCrew();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add_ok:
                determineAddCrews();
                break;
        }
    }

    private void addCrew() {
        Intent intent = new Intent(this, CrewInfoInputActivity.class);
        intent.putExtra(Constants.FROM_SHIP,true);
        intent.putExtra(Constants.SHIP_ID,shipId);
        startActivity(intent);
        finish();
    }
}
