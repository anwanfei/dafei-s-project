package com.junhangxintong.chuanzhangtong.shipposition.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.shipposition.bean.AddLeaveReportBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.DateUtil;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class WriteLeaveMessageActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_input_loading_unloading_cargo_port)
    EditText etInputLoadingUnloadingCargoPort;
    @BindView(R.id.et_input_cartgo_type)
    EditText etInputCartgoType;
    @BindView(R.id.et_input_cartgo_num)
    EditText etInputCartgoNum;
    @BindView(R.id.tv_input_loading_unloading_cargo_start_time)
    TextView tvInputLoadingUnloadingCargoStartTime;
    @BindView(R.id.tv_input_loading_unloading_cargo_complete_time)
    TextView tvInputLoadingUnloadingCargoCompleteTime;
    @BindView(R.id.tv_input_leave_time)
    TextView tvInputLeaveTime;
    @BindView(R.id.tv_input_re_time_arrive_port)
    TextView tvInputReTimeArrivePort;
    @BindView(R.id.et_input_tug_use_num)
    EditText etInputTugUseNum;
    @BindView(R.id.et_input_ship_draft)
    EditText etInputShipDraft;
    @BindView(R.id.rb_yes)
    RadioButton rbYes;
    @BindView(R.id.rb_no)
    RadioButton rbNo;
    @BindView(R.id.rg_choose_is_no)
    RadioGroup rgChooseIsNo;
    @BindView(R.id.ll_commit)
    LinearLayout llCommit;
    private AlertDialog show;
    private String id;
    private String userId;
    private String isPilotage = "1";
    private String loadingUnloadingCargoStartTime = "";
    private String leavaTime = "";
    private String reArrivePortTime = "";
    private String loadingUnloadingCargoCompleteTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListener();
    }

    private void initListener() {
        rgChooseIsNo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == rbNo.getId()) {
                    isPilotage = "2";
                } else if (i == rbYes.getId()) {
                    isPilotage = "1";
                }
            }
        });
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.leave_message));
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra(Constants.ID);
        userId = CacheUtils.getString(this, Constants.ID);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_write_leave_message;
    }

    @OnClick({R.id.iv_back, R.id.tv_input_loading_unloading_cargo_start_time, R.id.tv_input_loading_unloading_cargo_complete_time, R.id.tv_input_leave_time, R.id.tv_input_re_time_arrive_port, R.id.ll_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_input_loading_unloading_cargo_start_time:
                DateUtil.getDateTime(this, tvInputLoadingUnloadingCargoStartTime);
                break;
            case R.id.tv_input_loading_unloading_cargo_complete_time:
                DateUtil.getDateTime(this, tvInputLoadingUnloadingCargoCompleteTime);
                break;
            case R.id.tv_input_leave_time:
                DateUtil.getDateTime(this, tvInputLeaveTime);
                break;
            case R.id.tv_input_re_time_arrive_port:
                DateUtil.getDateTime(this, tvInputReTimeArrivePort);
                break;
            case R.id.ll_commit:
                showCommitDialog();
                break;
        }
    }

    private void showCommitDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_clear_butter, null);
        TextView tv_cancel_clear_buffer = (TextView) view.findViewById(R.id.tv_cancel_clear_buffer);
        TextView tv_ok_clear_butter = (TextView) view.findViewById(R.id.tv_ok_clear_butter);
        TextView tv_dialog_title = (TextView) view.findViewById(R.id.tv_dialog_title);
        TextView tv_dialog_message = (TextView) view.findViewById(R.id.tv_dialog_message);

        tv_dialog_title.setText(getResources().getString(R.string.publish_message_title));
        tv_dialog_message.setText(getResources().getString(R.string.publish_message_message));

        tv_cancel_clear_buffer.setOnClickListener(this);
        tv_ok_clear_butter.setOnClickListener(this);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.style_dialog);
        dialog.setView(view);
        show = dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel_clear_buffer:
                show.dismiss();
                break;
            case R.id.tv_ok_clear_butter:
                netCommitReport();
                break;
        }
    }

    private void netCommitReport() {

        String loadingUnloadingCargoPort = etInputLoadingUnloadingCargoPort.getText().toString();
        String cartgoType = etInputCartgoType.getText().toString();
        String cartgoNum = etInputCartgoNum.getText().toString();
        loadingUnloadingCargoStartTime = tvInputLoadingUnloadingCargoStartTime.getText().toString();
        loadingUnloadingCargoCompleteTime = tvInputLoadingUnloadingCargoCompleteTime.getText().toString();
        leavaTime = tvInputLeaveTime.getText().toString();
        reArrivePortTime = tvInputReTimeArrivePort.getText().toString();
        String tugUseNum = etInputTugUseNum.getText().toString();
        String shipDraft = etInputShipDraft.getText().toString();

        AddLeaveReportBean.ReportJsonDataBean reportJsonData = new AddLeaveReportBean.ReportJsonDataBean();
        reportJsonData.setLoadPort(loadingUnloadingCargoPort);
        reportJsonData.setGoodsType(cartgoType);
        reportJsonData.setGoodsNum(cartgoNum);
        reportJsonData.setLoadBeginDate(loadingUnloadingCargoStartTime);
        reportJsonData.setLoadDndDate(loadingUnloadingCargoCompleteTime);
        reportJsonData.setLeavaBerthDate(leavaTime);
        reportJsonData.setExpectArriveBearthDate(reArrivePortTime);
        reportJsonData.setTugUseNum(tugUseNum);
        reportJsonData.setShipForwardDraft(shipDraft);
        reportJsonData.setIsPilotage(isPilotage);

        String json = new Gson().toJson(reportJsonData);

        if (loadingUnloadingCargoPort.equals("")) {
            Toast.makeText(WriteLeaveMessageActivity.this, getResources().getString(R.string.loadPort), Toast.LENGTH_SHORT).show();
            return;
        }

        NetUtils.postWithHeader(this, ConstantsUrls.ADD_REPORT)
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.TYPE, "4")
                .addParams(Constants.REPORT_JSON, json)
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        show.dismiss();
                        finish();
                    }
                });
    }
}
