package com.metashipanwf.androidsummarize.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.metashipanwf.androidsummarize.R;

import java.util.Calendar;

public class DialogActivity extends Activity implements View.OnClickListener {


    private Button btnOkCancel;
    private Button btnSingleSelect;
    private Button btnMultiSelect;
    private Button btnThreeSelect;
    private Button btnList;
    private Button btnBarProggressbar;
    private Button btnCircularProgressbar;
    private Button btnInput;
    private Button btnCustom;
    private Button btnTime;
    private Button btnDate;
    private TextView tv_title;
    private TextView tv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dialog);

        initView();
        initData();
        initListener();
    }

    private void initData() {
        tv_title.setVisibility(View.VISIBLE);
        tv_back.setVisibility(View.VISIBLE);
        tv_title.setText("对话框大全");
    }

    private void initListener() {
        btnOkCancel.setOnClickListener(this);
        btnSingleSelect.setOnClickListener(this);
        btnMultiSelect.setOnClickListener(this);
        btnThreeSelect.setOnClickListener(this);
        btnList.setOnClickListener(this);
        btnBarProggressbar.setOnClickListener(this);
        btnCircularProgressbar.setOnClickListener(this);
        btnCustom.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        btnDate.setOnClickListener(this);
        btnInput.setOnClickListener(this);
        tv_back.setOnClickListener(this);
    }

    private void initView() {
        btnOkCancel = (Button) findViewById(R.id.btn_ok_cancel);
        btnSingleSelect = (Button) findViewById(R.id.btn_single_select);
        btnMultiSelect = (Button) findViewById(R.id.btn_multi_select);
        btnThreeSelect = (Button) findViewById(R.id.btn_three_select);
        btnList = (Button) findViewById(R.id.btn_list);
        btnBarProggressbar = (Button) findViewById(R.id.btn_bar_proggressbar);
        btnCircularProgressbar = (Button) findViewById(R.id.btn_circular_progressbar);
        btnCustom = (Button) findViewById(R.id.btn_custom);
        btnTime = (Button) findViewById(R.id.btn_time);
        btnDate = (Button) findViewById(R.id.btn_date);
        btnInput = (Button) findViewById(R.id.btn_input);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_back = (TextView) findViewById(R.id.tv_back);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
            case R.id.tv_back:
                finish();
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
                .setIcon(R.drawable.ic_tab_netaudio_press)
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        new AlertDialog.Builder(this)
                .setTitle("详细做法")
                .setMessage("1.基本都是AlertDialog.Builder()\n" +
                        "2.进度条对话框ProgressDialog()\n" +
                        "3.时间对话框TimePickerDialog()\n" +
                        "4.日期对话框DatePickerDialog()\n")
                .setPositiveButton("学会了", null)
                .show();
        return super.onOptionsItemSelected(item);
    }

}
