package com.atguigu.p2p.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.activity.BarChartActivity;
import com.atguigu.p2p.activity.ChongZhiActivity;
import com.atguigu.p2p.activity.LineChartActivity;
import com.atguigu.p2p.activity.LoginActivity;
import com.atguigu.p2p.activity.PieChartActivity;
import com.atguigu.p2p.activity.TiXianActivity;
import com.atguigu.p2p.activity.ToggleButtonActivity;
import com.atguigu.p2p.activity.UserInfoActivity;
import com.atguigu.p2p.bean.Login;
import com.atguigu.p2p.common.BaseActivity;
import com.atguigu.p2p.common.BaseFragment;
import com.atguigu.p2p.util.BitmapUtils;
import com.atguigu.p2p.util.UIUtils;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by shkstart on 2016/8/12 0012.
 */
public class MeFragment extends BaseFragment {


    @Bind(R.id.iv_common_back)
    ImageView ivCommonBack;
    @Bind(R.id.tv_common_title)
    TextView tvCommonTitle;
    @Bind(R.id.iv_common_setting)
    ImageView ivCommonSetting;
    @Bind(R.id.imageView1)
    ImageView imageView1;
    @Bind(R.id.icon_time)
    RelativeLayout iconTime;
    @Bind(R.id.textView11)
    TextView textView11;
    @Bind(R.id.relativeLayout1)
    RelativeLayout relativeLayout1;
    @Bind(R.id.recharge)
    ImageView recharge;
    @Bind(R.id.withdraw)
    ImageView withdraw;
    @Bind(R.id.ll_touzi)
    TextView llTouzi;
    @Bind(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @Bind(R.id.ll_zichang)
    TextView llZichang;
    @Bind(R.id.ll_zhanquan)
    TextView llZhanquan;

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return null;
    }

    //加载界面显示的过程中的回调方法
    @Override
    protected void initData(String content) {
        isLogin();
    }

    private void isLogin() {
        SharedPreferences sp = this.getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String username = sp.getString("UF_ACC", "");
        if (TextUtils.isEmpty(username)) {//第1种情况：在没有登录过的情况下，提示登录的dialog
            login();
        } else {
            //第2种情况：加载已经登录的用户信息
            doUser();
        }


    }

    //如果用户已经登录，显示登录信息
    private void doUser() {
        Login login = ((BaseActivity) this.getActivity()).getLogin();
        textView11.setText(login.UF_ACC);

        File filesDir = this.getActivity().getExternalFilesDir(null);
        File file = new File(filesDir, "icon.jpg");
        Log.e("TAG", file.getAbsolutePath());
        if (file.exists()) {//如果本地存储中存在
            //将存储中的图片还原为内存中的Bitmap对象
            Log.e("TAG", "file.exist()");
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            //图片的压缩
            Bitmap target = BitmapUtils.zoom(bitmap, UIUtils.dp2px(62), UIUtils.dp2px(62));
            //图片的圆形处理
            target = BitmapUtils.circleBitmap(target);
            imageView1.setImageBitmap(target);
            return;
        }

        //显示用户头像
        Picasso.with(getActivity()).load(login.UF_AVATAR_URL).transform(new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {//source:得到的内存层面的头像对象
                //压缩处理
                Bitmap target = BitmapUtils.zoom(source, UIUtils.dp2px(62), UIUtils.dp2px(62));
                //圆形处理
                target = BitmapUtils.circleBitmap(target);
                source.recycle();//回收source资源
                return target;
            }

            @Override
            public String key() {
                return "";//返回值不要为null
            }
        }).into(imageView1);

    }

    //提示用户登录
    private void login() {
        new AlertDialog.Builder(this.getActivity())
                .setTitle("提示")
                .setMessage("必须先登录...come on..")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(MeFragment.this.getActivity(), "要登录哦！", Toast.LENGTH_SHORT).show();
                        //启动登录的界面
                        ((BaseActivity) MeFragment.this.getActivity()).goToActivity(LoginActivity.class, null);
                    }
                })
                .setCancelable(false)
                .show();
    }

    public void initTitle() {
        ivCommonBack.setVisibility(View.INVISIBLE);
        ivCommonSetting.setVisibility(View.VISIBLE);

        tvCommonTitle.setText("我的资产");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_me;
    }

    //设置“设置”按钮的点击事件
    @OnClick(R.id.iv_common_setting)
    public void changeIcon(View view) {
        ((BaseActivity) this.getActivity()).goToActivity(UserInfoActivity.class, null);
    }

    @OnClick(R.id.recharge)
    public void recharge(View view) {
        ((BaseActivity) this.getActivity()).goToActivity(ChongZhiActivity.class, null);
    }

    @OnClick(R.id.withdraw)
    public void withdraw(View view) {
        ((BaseActivity) this.getActivity()).goToActivity(TiXianActivity.class, null);
    }

    @OnClick(R.id.ll_touzi)
    public void startLineChart(View view) {
        ((BaseActivity) this.getActivity()).goToActivity(LineChartActivity.class, null);
    }

    @OnClick(R.id.ll_touzi_zhiguan)
    public void startBarChart(View view) {
        ((BaseActivity) this.getActivity()).goToActivity(BarChartActivity.class, null);
    }

    @OnClick(R.id.ll_zichang)
    public void startPieChart(View view) {
        ((BaseActivity) this.getActivity()).goToActivity(PieChartActivity.class, null);
    }

    @OnClick(R.id.ll_zhanquan)
    public void startToggle(View view){
        ((BaseActivity) this.getActivity()).goToActivity(ToggleButtonActivity.class, null);

    }

}
