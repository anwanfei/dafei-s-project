package com.junhangxintong.chuanzhangtong.shipposition.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.mine.bean.CrewBean;
import com.junhangxintong.chuanzhangtong.shipposition.adapter.AdditiveCrewAdapter;
import com.junhangxintong.chuanzhangtong.utils.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

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
    private ArrayList<CrewBean> crews;
    private ArrayList<CrewBean> choosedCrewLists;
    private Map<String, Boolean> map = new HashMap<>();
    private AdditiveCrewAdapter additiveCrewAdapter;
    private List<String> savelist = new ArrayList();
    private ArrayList<CrewBean> choosedLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.choose_additive_crews));
    }

    @Override
    protected void initData() {
        crews = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            CrewBean crewBean = new CrewBean();
            crewBean.setCrewName("船员" + i + "号");
            crewBean.setDuty("大副" + i + "号");
            crewBean.setJobNum("00" + i + "号");
            crewBean.setNationality("中国");
            crews.add(crewBean);
        }

        additiveCrewAdapter = new AdditiveCrewAdapter(this, crews, tvChoosedCrewNum);
        lvAdditiveCrew.setAdapter(additiveCrewAdapter);

        // TODO: 2017/8/9  搜索要添加的船员---数据库query
        // TODO: 2017/8/26 有结果和没有结果
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_crew;
    }

    @OnClick({R.id.iv_back, R.id.tv_add_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add_ok:
                determineAddCrews();
                break;
        }
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

        //找到选中的位置，并保存在map
        for (int i = 0; i < crews.size(); i++) {
            boolean checkbox = crews.get(i).isCheckbox();
            if (checkbox) {
                map.put(i + "", true);
                crews.get(i).setCheckbox(false);
                choosedLists.add(crews.get(i));
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

        Intent intent = getIntent();
        intent.putExtra(Constants.ADD_CREW, (Serializable) choosedLists);
        setResult(Constants.REQUEST_CODE0, intent);
        finish();

    }
}
