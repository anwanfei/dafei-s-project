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
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicRemindBerthingReportBean;
import com.junhangxintong.chuanzhangtong.shipposition.bean.AddBerthingReportBean;
import com.junhangxintong.chuanzhangtong.shipposition.bean.BerthingReportInfoBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.DateUtil;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ModifyBerthingReportActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.et_input_loading_unloading_cargo_port)
    EditText etInputLoadingUnloadingCargoPort;
    @BindView(R.id.tv_input_anchor_arrival_time)
    TextView tvInputAnchorArrivalTime;
    @BindView(R.id.tv_input_system_stop_time)
    TextView tvInputSystemStopTime;
    @BindView(R.id.tv_input_test_cabin_start_time)
    TextView tvInputTestCabinStartTime;
    @BindView(R.id.tv_input_test_cabin_end_time)
    TextView tvInputTestCabinEndTime;
    @BindView(R.id.et_input_port_position)
    EditText etInputPortPosition;
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
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.ll_commit)
    LinearLayout llCommit;
    private String userId;
    private BerthingReportInfoBean.DataBean.ObjectBean berthingReportInfoFromShip;
    private int id;
    private String anchorArrivalTime = "";
    private String systemStopTime = "";
    private String testCabinStartTime = "";
    private String testCabinEndTime = "";
    private String isPilotage = "1";
    private AlertDialog show;

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
        tvTitle.setText(getResources().getString(R.string.modify_berthing_message));
    }

    @Override
    protected void initData() {
        userId = CacheUtils.getString(this, Constants.ID);

        Intent intent = getIntent();
        boolean fromShip = intent.getBooleanExtra(Constants.FROM_SHIP, false);
        if (fromShip) {

            berthingReportInfoFromShip = (BerthingReportInfoBean.DataBean.ObjectBean) intent.getSerializableExtra(Constants.BERTHING_REPORT_INFO);

            etInputLoadingUnloadingCargoPort.setText(berthingReportInfoFromShip.getLoadPort());
            etInputPortPosition.setText(berthingReportInfoFromShip.getPortBearth());
            etInputTugUseNum.setText(berthingReportInfoFromShip.getTugUseNum());
            etInputShipDraft.setText(berthingReportInfoFromShip.getShipForwardDraft());
            tvInputAnchorArrivalTime.setText(berthingReportInfoFromShip.getAnchorAweighDate());
            tvInputSystemStopTime.setText(berthingReportInfoFromShip.getXtBerthDate());
            tvInputTestCabinEndTime.setText(berthingReportInfoFromShip.getHoldInspectionBeginDate());
            tvInputTestCabinEndTime.setText(berthingReportInfoFromShip.getHoldInspectionEndDate());

            String isPilotage = berthingReportInfoFromShip.getIsPilotage();
            if (isPilotage.equals("1")) {
                rbYes.setChecked(true);
            } else {
                rbNo.setChecked(true);
            }
            id = berthingReportInfoFromShip.getId();
        } else {
            DynamicRemindBerthingReportBean.DataBean.ObjectBean dynamicRemindBerthingReportBean = (DynamicRemindBerthingReportBean.DataBean.ObjectBean) intent.getSerializableExtra(Constants.BERTHING_REPORT_INFO);
            etInputLoadingUnloadingCargoPort.setText(dynamicRemindBerthingReportBean.getLoadPort());
            etInputPortPosition.setText(dynamicRemindBerthingReportBean.getPortBearth());
            etInputTugUseNum.setText(dynamicRemindBerthingReportBean.getTugUseNum());
            etInputShipDraft.setText(dynamicRemindBerthingReportBean.getShipForwardDraft());
            tvInputAnchorArrivalTime.setText(dynamicRemindBerthingReportBean.getAnchorAweighDate());
            tvInputSystemStopTime.setText(dynamicRemindBerthingReportBean.getXtBerthDate());
            tvInputTestCabinEndTime.setText(dynamicRemindBerthingReportBean.getHoldInspectionBeginDate());
            tvInputTestCabinEndTime.setText(dynamicRemindBerthingReportBean.getHoldInspectionEndDate());

            String isPilotage = dynamicRemindBerthingReportBean.getIsPilotage();
            if (isPilotage.equals("1")) {
                rbYes.setChecked(true);
            } else {
                rbNo.setChecked(true);
            }
            id = dynamicRemindBerthingReportBean.getId();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_berthing_report;
    }

    @OnClick({R.id.iv_back, R.id.tv_input_anchor_arrival_time, R.id.tv_input_system_stop_time, R.id.tv_input_test_cabin_start_time, R.id.tv_input_test_cabin_end_time, R.id.ll_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_input_anchor_arrival_time:
                DateUtil.getDateTime(this, tvInputAnchorArrivalTime);
                break;
            case R.id.tv_input_system_stop_time:
                DateUtil.getDateTime(this, tvInputAnchorArrivalTime);
                break;
            case R.id.tv_input_test_cabin_start_time:
                DateUtil.getDateTime(this, tvInputAnchorArrivalTime);
                break;
            case R.id.tv_input_test_cabin_end_time:
                DateUtil.getDateTime(this, tvInputAnchorArrivalTime);
                break;
            case R.id.ll_commit:
                showCommitDialog();
                break;
        }
    }

    private void netCommitReport() {

        String loadingUnloadingCargoPort = etInputLoadingUnloadingCargoPort.getText().toString();
        anchorArrivalTime = tvInputAnchorArrivalTime.getText().toString();
        systemStopTime = tvInputSystemStopTime.getText().toString();
        testCabinStartTime = tvInputTestCabinStartTime.getText().toString();
        testCabinEndTime = tvInputTestCabinEndTime.getText().toString();
        String portPosition = etInputPortPosition.getText().toString();
        String tugUseNum = etInputTugUseNum.getText().toString();
        String shipDraft = etInputShipDraft.getText().toString();

        AddBerthingReportBean.ReportJsonDataBean reportJsonData = new AddBerthingReportBean.ReportJsonDataBean();
        reportJsonData.setLoadPort(loadingUnloadingCargoPort);
        reportJsonData.setAnchorAweighDate(anchorArrivalTime);
        reportJsonData.setXtBerthDate(systemStopTime);
        reportJsonData.setHoldInspectionBeginDate(testCabinStartTime);
        reportJsonData.setHoldInspectionEndDate(testCabinEndTime);
        reportJsonData.setPortBearth(portPosition);
        reportJsonData.setTugUseNum(tugUseNum);
        reportJsonData.setShipForwardDraft(shipDraft);
        reportJsonData.setIsPilotage(isPilotage);
        reportJsonData.setId(id);

        String json = new Gson().toJson(reportJsonData);

        if (loadingUnloadingCargoPort.equals("")) {
            Toast.makeText(ModifyBerthingReportActivity.this, getResources().getString(R.string.loadPort), Toast.LENGTH_SHORT).show();
            return;
        }

        NetUtils.postWithHeader(this, ConstantsUrls.MODIFY_REPORT)
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.TYPE, "2")
                .addParams(Constants.REPORT_JSON, json)
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        Toast.makeText(ModifyBerthingReportActivity.this, message, Toast.LENGTH_SHORT).show();
                        show.dismiss();
                        finish();
                    }
                });
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
}
