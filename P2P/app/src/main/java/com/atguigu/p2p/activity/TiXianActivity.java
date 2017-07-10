package com.atguigu.p2p.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.p2p.R;
import com.atguigu.p2p.common.BaseActivity;
import com.atguigu.p2p.util.UIUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class TiXianActivity extends BaseActivity {


    @Bind(R.id.iv_common_back)
    ImageView ivCommonBack;
    @Bind(R.id.tv_common_title)
    TextView tvCommonTitle;
    @Bind(R.id.iv_common_setting)
    ImageView ivCommonSetting;
    @Bind(R.id.account_zhifubao)
    TextView accountZhifubao;
    @Bind(R.id.select_bank)
    RelativeLayout selectBank;
    @Bind(R.id.chongzhi_text)
    TextView chongzhiText;
    @Bind(R.id.view)
    View view;
    @Bind(R.id.input_money)
    EditText inputMoney;
    @Bind(R.id.chongzhi_text2)
    TextView chongzhiText2;
    @Bind(R.id.textView5)
    TextView textView5;
    @Bind(R.id.btn_tixian)
    Button btnTixian;

    @Override
    protected void initTitle() {
        ivCommonBack.setVisibility(View.VISIBLE);
        ivCommonSetting.setVisibility(View.INVISIBLE);
        tvCommonTitle.setText("提现");
    }

    @OnClick(R.id.iv_common_back)
    public void back(View view) {
        closeCurrentActivity();
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ti_xian;
    }

    @OnClick(R.id.btn_tixian)
    public void tiXian(View view) {
        String money = inputMoney.getText().toString();
        Toast.makeText(this, "你的提现额度为 " + money + ",此请求已被成功受理...审核通过后，24小时内，你的钱自然会到账...", Toast.LENGTH_SHORT).show();

        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                closeCurrentActivity();
            }
        }, 2000);

    }


}
