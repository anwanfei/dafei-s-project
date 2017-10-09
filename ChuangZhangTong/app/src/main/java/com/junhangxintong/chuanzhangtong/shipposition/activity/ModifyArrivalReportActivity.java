package com.junhangxintong.chuanzhangtong.shipposition.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicRemindArrivalReportBean;
import com.junhangxintong.chuanzhangtong.shipposition.bean.AddArrivalReportBean;
import com.junhangxintong.chuanzhangtong.shipposition.bean.ArrivalReportInfoBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.DateUtil;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ModifyArrivalReportActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_input_arrival_port)
    EditText etInputArrivalPort;
    @BindView(R.id.et_input_port_stop_position)
    EditText etInputPortStopPosition;
    @BindView(R.id.tv_choose_anchor_arrival_time)
    TextView tvChooseAnchorArrivalTime;
    @BindView(R.id.et_input_anchor_posotion)
    EditText etInputAnchorPosotion;
    @BindView(R.id.et_input_longitude)
    EditText etInputLongitude;
    @BindView(R.id.et_input_latitude)
    EditText etInputLatitude;
    @BindView(R.id.et_input_ship_direction)
    EditText etInputShipDirection;
    @BindView(R.id.et_input_ship_speed)
    EditText etInputShipSpeed;
    @BindView(R.id.et_input_the_ship_heavy_oil)
    EditText etInputTheShipHeavyOil;
    @BindView(R.id.et_input_certificate_name)
    EditText etInputCertificateName;
    @BindView(R.id.et_input_ship_draft)
    EditText etInputShipDraft;
    @BindView(R.id.et_input_the_ship_frashwater)
    EditText etInputTheShipFrashwater;
    @BindView(R.id.et_input_light_oil_consume)
    EditText etInputLightOilConsume;
    @BindView(R.id.et_input_frashwater_consume)
    EditText etInputFrashwaterConsume;
    @BindView(R.id.et_input_remark_with_colon_details)
    EditText etInputRemarkWithColonDetails;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.ll_commit)
    LinearLayout llCommit;
    private String userId;
    private AlertDialog show;
    private int reportId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.modify_arrival_message));
    }

    @Override
    protected void initData() {
        userId = CacheUtils.getString(this, Constants.ID);

        Intent intent = getIntent();
        boolean fromShip = intent.getBooleanExtra(Constants.FROM_SHIP, false);
        if (!fromShip) {

            DynamicRemindArrivalReportBean.DataBean.ObjectBean arrivalInfoFromDynamic = (DynamicRemindArrivalReportBean.DataBean.ObjectBean) intent.getSerializableExtra(Constants.ARRIVAL_REPORT_INFO);

            etInputArrivalPort.setText(arrivalInfoFromDynamic.getArrivePort());
            etInputPortStopPosition.setText(arrivalInfoFromDynamic.getPortRadsteadBerth());
            tvChooseAnchorArrivalTime.setText(arrivalInfoFromDynamic.getArriveAnchorDate());
            etInputAnchorPosotion.setText(arrivalInfoFromDynamic.getAnchorPosition());
            etInputLongitude.setText(arrivalInfoFromDynamic.getLongitude());
            etInputLatitude.setText(arrivalInfoFromDynamic.getLatitude());
            etInputShipDirection.setText(arrivalInfoFromDynamic.getCourse());
            etInputShipSpeed.setText(arrivalInfoFromDynamic.getCurrShipSpeed());
            etInputTheShipHeavyOil.setText(arrivalInfoFromDynamic.getShipHeavyOil());
            etInputCertificateName.setText(arrivalInfoFromDynamic.getShipLightOil());
            etInputShipDraft.setText(arrivalInfoFromDynamic.getShipForwardDraft());
            etInputTheShipFrashwater.setText(arrivalInfoFromDynamic.getShipFreshwater());
            etInputLightOilConsume.setText(arrivalInfoFromDynamic.getLightOilConsumption());
            etInputFrashwaterConsume.setText(arrivalInfoFromDynamic.getFreshwaterConsumption());
            etInputRemarkWithColonDetails.setText(arrivalInfoFromDynamic.getRemark());

            reportId = arrivalInfoFromDynamic.getId();

        } else {
            ArrivalReportInfoBean.DataBean.ObjectBean arrivalInfoFromNet = (ArrivalReportInfoBean.DataBean.ObjectBean) intent.getSerializableExtra(Constants.ARRIVAL_REPORT_INFO);
            etInputArrivalPort.setText(arrivalInfoFromNet.getArrivePort());
            etInputPortStopPosition.setText(arrivalInfoFromNet.getPortRadsteadBerth());
            tvChooseAnchorArrivalTime.setText(arrivalInfoFromNet.getArriveAnchorDate());
            etInputAnchorPosotion.setText(arrivalInfoFromNet.getAnchorPosition());
            etInputLongitude.setText(arrivalInfoFromNet.getLongitude());
            etInputLatitude.setText(arrivalInfoFromNet.getLatitude());
            etInputShipDirection.setText(arrivalInfoFromNet.getCourse());
            etInputShipSpeed.setText(arrivalInfoFromNet.getCurrShipSpeed());
            etInputTheShipHeavyOil.setText(arrivalInfoFromNet.getShipHeavyOil());
            etInputCertificateName.setText(arrivalInfoFromNet.getShipLightOil());
            etInputShipDraft.setText(arrivalInfoFromNet.getShipForwardDraft());
            etInputTheShipFrashwater.setText(arrivalInfoFromNet.getShipFreshwater());
            etInputLightOilConsume.setText(arrivalInfoFromNet.getLightOilConsumption());
            etInputFrashwaterConsume.setText(arrivalInfoFromNet.getFreshwaterConsumption());
            etInputRemarkWithColonDetails.setText(arrivalInfoFromNet.getRemark());

            reportId = arrivalInfoFromNet.getId();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_arrival_report;
    }

    @OnClick({R.id.iv_back, R.id.tv_choose_anchor_arrival_time, R.id.tv_save, R.id.ll_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_choose_anchor_arrival_time:
                DateUtil.getDateTime(this, tvChooseAnchorArrivalTime);
                break;
            case R.id.tv_save:
                showCommitDialog();
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

        String arrivalPort = etInputArrivalPort.getText().toString();
        String portStopPosition = etInputPortStopPosition.getText().toString();
        String anchorArrivalTime = tvChooseAnchorArrivalTime.getText().toString();
        String anchorPosotion = etInputAnchorPosotion.getText().toString();
        String longitude = etInputLongitude.getText().toString();
        String latitude = etInputLatitude.getText().toString();
        String shipDirection = etInputShipDirection.getText().toString();
        String shipSpeed = etInputShipSpeed.getText().toString();
        String heavyOil = etInputTheShipHeavyOil.getText().toString();
        String lightOil = etInputCertificateName.getText().toString();
        String shipDraft = etInputShipDraft.getText().toString();
        String freshWater = etInputTheShipFrashwater.getText().toString();
        String lightOilConsume = etInputLightOilConsume.getText().toString();
        String remark = etInputRemarkWithColonDetails.getText().toString();
        String freshWaterConsume = etInputFrashwaterConsume.getText().toString();

        AddArrivalReportBean.ReportJsonDataBean reportJsonData = new AddArrivalReportBean.ReportJsonDataBean();

        reportJsonData.setArrivePort(arrivalPort);
        reportJsonData.setPortRadsteadBerth(portStopPosition);
        reportJsonData.setArriveAnchorDate(anchorArrivalTime);
        reportJsonData.setAnchorPosition(anchorPosotion);
        reportJsonData.setLongitude(longitude);
        reportJsonData.setLatitude(latitude);
        reportJsonData.setCourse(shipDirection);
        reportJsonData.setCurrShipSpeed(shipSpeed);
        reportJsonData.setShipHeavyOil(heavyOil);
        reportJsonData.setShipLightOil(lightOil);
        reportJsonData.setShipForwardDraft(shipDraft);
        reportJsonData.setShipFreshwater(freshWater);
        reportJsonData.setLightOilConsumption(lightOilConsume);
        reportJsonData.setFreshwaterConsumption(freshWaterConsume);
        reportJsonData.setRemark(remark);
        reportJsonData.setId(reportId);

        String json = new Gson().toJson(reportJsonData);

        if (arrivalPort.equals("")) {
            Toast.makeText(ModifyArrivalReportActivity.this, getResources().getString(R.string.input_arrival_port), Toast.LENGTH_SHORT).show();
            return;
        }

        NetUtils.postWithHeader(this, ConstantsUrls.MODIFY_REPORT)
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.TYPE, "3")
                .addParams(Constants.REPORT_JSON, json)
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        Toast.makeText(ModifyArrivalReportActivity.this, message, Toast.LENGTH_SHORT).show();
                        show.dismiss();
                        finish();
                    }
                });
    }
}
