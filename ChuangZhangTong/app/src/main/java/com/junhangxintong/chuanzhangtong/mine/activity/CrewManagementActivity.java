package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.common.MyApplication;
import com.junhangxintong.chuanzhangtong.mine.adapter.CrewListsAdapter;
import com.junhangxintong.chuanzhangtong.mine.adapter.MyCrewAdapter;
import com.junhangxintong.chuanzhangtong.mine.bean.CrewBean;
import com.junhangxintong.chuanzhangtong.mine.bean.CrewServeBean;
import com.junhangxintong.chuanzhangtong.net.HttpUtils;
import com.junhangxintong.chuanzhangtong.net.ICrewManagementService;
import com.junhangxintong.chuanzhangtong.net.MyCallback;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Response;

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
    @BindView(R.id.iv_nothing)
    ImageView ivNothing;
    @BindView(R.id.tv_nothing)
    TextView tvNothing;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.et_search_crew_name)
    EditText etSearchCrewName;
    @BindView(R.id.ll_search_crew_name)
    LinearLayout llSearchCrewName;
    private MyCrewAdapter myFleetAdapter;
    private boolean isChoose = true;
    private boolean isChooseAll = true;

    private List<String> savelist = new ArrayList();
    private Map<String, Boolean> map = new HashMap<>();
    private List<CrewServeBean.DataBean.ArrayBean> choosedLists;
    private List<CrewServeBean.DataBean.ArrayBean> crewLists;
    private CrewListsAdapter crewListsAdapter;
    private ArrayList<String> choosedCrewIdLists;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListener();
    }

    private void initListener() {
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
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.chuanyuanguanli));
        ivShare.setVisibility(View.VISIBLE);
        tvSetting.setVisibility(View.VISIBLE);
        tvSetting.setText(getResources().getString(R.string.add_ships));
        tvShare.setText(getResources().getString(R.string.delete));
        tvNothing.setText(getResources().getString(R.string.add_first_crew));
        ivNothing.setImageResource(R.drawable.iv_no_crew);
    }

    @Override
    protected void initData() {

        userId = CacheUtils.getString(this, Constants.ID);

        if (StringUtils.isNotEmpty(MyApplication.token)) {
            netGetCrewsLists("");
        }

    }

    private void netGetCrewsLists(String crewName) {
       /* NetUtils.postWithHeader(this, ConstantsUrls.CREW_LISTS)
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.PAGE_SIZE, "50")
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.PERSON_NAME, crewName)
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        CrewServeBean crewServerBean = new Gson().fromJson(response, CrewServeBean.class);
                        crewLists = crewServerBean.getData().getArray();

                        updateCrewsList();
                        crewListsAdapter = new CrewListsAdapter(CrewManagementActivity.this, crewLists);
                        lvMyCrew.setAdapter(crewListsAdapter);
                    }

                    @Override
                    public void onDataEmpty(String message) {
                        showDataEmptyUI();
                    }
                });
*/
        retrofit2.Call<CrewServeBean> call = HttpUtils.getDefault().getService(ICrewManagementService.class).getCrewList("1", "100", userId, crewName);
        call.enqueue(new MyCallback<CrewServeBean>() {
            @Override
            public void onSuccess(Response<CrewServeBean> response) {
                crewLists = response.body().getData().getArray();
                updateCrewsList();
                crewListsAdapter = new CrewListsAdapter(CrewManagementActivity.this, crewLists);
                lvMyCrew.setAdapter(crewListsAdapter);
            }

            @Override
            public void onDataEmpty(String message) {
                showDataEmptyUI();
            }
        });
    }

    private void updateCrewsList() {
        if (crewLists.size() > 0) {
            lvMyCrew.setVisibility(View.VISIBLE);
            llNoCrew.setVisibility(View.GONE);
            tvShare.setVisibility(View.VISIBLE);
        } else {
            showDataEmptyUI();
        }
    }

    private void showDataEmptyUI() {
        lvMyCrew.setVisibility(View.GONE);
        llNoCrew.setVisibility(View.VISIBLE);
        rlChooseAllDelete.setVisibility(View.GONE);
        tvShare.setVisibility(View.GONE);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_crew_management;
    }

    @OnClick({R.id.iv_back, R.id.tv_add_ship, R.id.ll_no_crew, R.id.tv_share, R.id.tv_my_crew_list_choose_all, R.id.tv_my_crew_list_delete, R.id.iv_share, R.id.tv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add_ship:
                gotoCrewInfoActivity();
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
            case R.id.iv_share:
                gotoCrewInfoActivity();
                break;
            case R.id.tv_setting:
                gotoCrewInfoActivity();
                break;
        }
    }

    private void gotoCrewInfoActivity() {
//        startActivityForResult(new Intent(CrewManagementActivity.this, CrewInfoInputActivity.class), Constants.REQUEST_CODE0);
        startActivity(new Intent(CrewManagementActivity.this, CrewInfoInputActivity.class));
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
        updateCrewsList();
        crewListsAdapter.notifyDataSetChanged();

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
        for (int i = 0; i < choosedCrewIdLists.size(); i++) {
            sb.append(choosedCrewIdLists.get(i) + ",");
        }
        String ids = sb.toString().substring(0, sb.length() - 1);
        NetUtils.postWithHeader(this, ConstantsUrls.DELETE_CREW)
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.IDS, ids)
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        crewLists.removeAll(choosedLists);
                        updateCrewsList();
                        crewListsAdapter.notifyDataSetChanged();
                        Toast.makeText(CrewManagementActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (StringUtils.isNotEmpty(MyApplication.token)) {
            netGetCrewsLists("");
        }
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
}
