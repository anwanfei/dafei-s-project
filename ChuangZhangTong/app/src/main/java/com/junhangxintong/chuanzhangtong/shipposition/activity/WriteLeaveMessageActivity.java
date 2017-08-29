package com.junhangxintong.chuanzhangtong.shipposition.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.utils.DateUtil;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.leave_message));
    }

    @Override
    protected void initData() {

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
                DateUtil.showChooseTimeDialog(this, tvInputLoadingUnloadingCargoStartTime);
                break;
            case R.id.tv_input_loading_unloading_cargo_complete_time:
                DateUtil.showChooseTimeDialog(this, tvInputLoadingUnloadingCargoCompleteTime);
                break;
            case R.id.tv_input_leave_time:
                DateUtil.showChooseTimeDialog(this, tvInputLeaveTime);
                break;
            case R.id.tv_input_re_time_arrive_port:
                DateUtil.showChooseTimeDialog(this, tvInputReTimeArrivePort);
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
                Toast.makeText(WriteLeaveMessageActivity.this, getResources().getString(R.string.commit_message_success), Toast.LENGTH_SHORT).show();
                show.dismiss();
                finish();
                break;
        }
    }
}
