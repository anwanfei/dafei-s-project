package com.anwanfei.anfly.foundation.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.anwanfei.anfly.R;
import com.anwanfei.anfly.common.BaseActivity;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

public class DialogActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_ok_cancel)
    Button btnOkCancel;
    @BindView(R.id.btn_single_select)
    Button btnSingleSelect;
    @BindView(R.id.btn_multi_select)
    Button btnMultiSelect;
    @BindView(R.id.btn_three_select)
    Button btnThreeSelect;
    @BindView(R.id.btn_list)
    Button btnList;
    @BindView(R.id.btn_bar_proggressbar)
    Button btnBarProggressbar;
    @BindView(R.id.btn_circular_progressbar)
    Button btnCircularProgressbar;
    @BindView(R.id.btn_input)
    Button btnInput;
    @BindView(R.id.btn_time)
    Button btnTime;
    @BindView(R.id.btn_date)
    Button btnDate;
    @BindView(R.id.btn_custom)
    Button btnCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.dialog_all));
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dialog;
    }

    @OnClick({R.id.iv_back, R.id.btn_ok_cancel, R.id.btn_single_select, R.id.btn_multi_select, R.id.btn_three_select, R.id.btn_list, R.id.btn_bar_proggressbar, R.id.btn_circular_progressbar, R.id.btn_input, R.id.btn_time, R.id.btn_date, R.id.btn_custom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_ok_cancel:
                okCancelDialog();
                break;
            case R.id.btn_single_select:
                singleSelectDialog();
                break;
            case R.id.btn_multi_select:
                multiSelectDialog();
                break;
            case R.id.btn_three_select:
                threeSelectDialog();
                break;
            case R.id.btn_list:
                listDialog();
                break;
            case R.id.btn_bar_proggressbar:
                barProgressDialog();
                break;
            case R.id.btn_circular_progressbar:
                circularProgressDialog();
                break;
            case R.id.btn_input:
                inputDialog();
                break;
            case R.id.btn_time:
                timeDialog();
                break;
            case R.id.btn_date:
                dateDialog();
                break;
            case R.id.btn_custom:
                customDialog();
                break;
        }
    }

    //自定义对话框
    private void customDialog() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_custom, null);
        Button btn1 = (Button) view.findViewById(R.id.btn1);
        Button btn2 = (Button) view.findViewById(R.id.btn2);

        final AlertDialog dialog = new AlertDialog.Builder(this).setTitle("自定义对话框").setView(view)
                .setMessage("自定义").show();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    //日期对话框
    private void dateDialog() {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                Toast.makeText(DialogActivity.this, "你选择的日期是" + year + "年" + monthOfYear + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT).show();
            }
        }, year, monthOfYear - 1, dayOfMonth);
        datePickerDialog.show();
//        Toast.makeText(DialogActivity.this, year + "/" + monthOfYear + "/" + dayOfMonth, Toast.LENGTH_SHORT).show();
    }


    /*
    时间对话框
     */
    private void timeDialog() {
        Calendar instance = Calendar.getInstance();
        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                Toast.makeText(DialogActivity.this, "您选择的时间是" + hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();
            }
        }, instance.get(Calendar.HOUR_OF_DAY), instance.get(Calendar.MINUTE), false);

        dialog.show();

    }

    /*
    输入对话框
     */
    private void inputDialog() {
        final EditText editText = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请输入");
        builder.setView(editText);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(DialogActivity.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    /*
    圆形进度对话框
     */
    private void circularProgressDialog() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("提示");
        dialog.setMessage("正在处理...");
        dialog.setIndeterminate(false);//设置进度条是否为不明确
        dialog.setCancelable(false);//设置能否点击取消
        dialog.setButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /*
    条形进度对话框
     */
    private void barProgressDialog() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setTitle("提示");
        dialog.setMessage("正在处理...");
        dialog.show();
        new Thread() {
            public void run() {
                dialog.setMax(100);
                for (int i = 0; i < 100; i++) {
                    dialog.setProgress(i);
                    SystemClock.sleep(80);

                }
                dialog.dismiss();
            }
        }.start();


    }

    /*
    列表对话框
     */
    private void listDialog() {
        new AlertDialog.Builder(this).setTitle("列表对话框")
                .setItems(new String[]{"条目一", "条目二", "条目三"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DialogActivity.this, "选择了条目" + i, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    /*
    三选对话框
     */
    private void threeSelectDialog() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.iv_my_pressed)
                .setTitle("智商考验")
                .setMessage("你是猪吗？")
                .setPositiveButton("是的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DialogActivity.this, "这智商。。。", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("好像是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DialogActivity.this, "这智商。。。", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("我不是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DialogActivity.this, "这智商。。。", Toast.LENGTH_SHORT).show();
                    }
                }).create().show();
    }

    /*
    多选对话框
     */
    private void multiSelectDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择你喜欢的颜色");
        final String[] items = {"黄", "绿", "红", "蓝"};
        final boolean[] checkedItems = {false, false, false, false};
        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checkedItems[which] = isChecked;
                Toast.makeText(DialogActivity.this, items[which], Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("取消", null);

        builder.setPositiveButton("提交", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < checkedItems.length; i++) {
                    if (checkedItems[i]) {
                        sb.append(items[i] + ",");
                    }
                }
                Toast.makeText(DialogActivity.this, "你的选择是" + sb.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        builder.show();
    }

    /*
    单选对话框
     */
    private void singleSelectDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择性别");
        final String[] items = {"男", "女", "未知"};
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(DialogActivity.this, items[which], Toast.LENGTH_SHORT).show();

            }
        });
        builder.show();
    }

    /*
    确定取消对话框
     */
    private void okCancelDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
        builder.setTitle("友情提示");
        builder.setMessage("若练此功,必先自宫,是否继续");
        builder.setPositiveButton("是的,想好了", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogActivity.this, "啊...", Toast.LENGTH_SHORT).show();
                Toast.makeText(DialogActivity.this, "即使自宫,未必成功...", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("想想在说", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogActivity.this, "若不自宫,一定不成功", Toast.LENGTH_SHORT).show();

            }
        });
        builder.show();
    }


}
