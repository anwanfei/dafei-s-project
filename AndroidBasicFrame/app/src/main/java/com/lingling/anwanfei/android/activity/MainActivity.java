package com.lingling.anwanfei.android.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lingling.anwanfei.android.R;
import com.lingling.anwanfei.android.fragment.BaseFragment;
import com.lingling.anwanfei.android.fragment.FoundationFragment;
import com.lingling.anwanfei.android.fragment.FrameFragment;
import com.lingling.anwanfei.android.fragment.InterviewFragment;
import com.lingling.anwanfei.android.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private RadioGroup rg_bottom_tag;
    private List<BaseFragment> fragments;
    private boolean isExit;
    private TextView tv_title;
    /*
    选中的位置
     */
    private int position;

    /**
     * 上次切换的Fragment
     */
    private Fragment fromFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initFragment();
        initListener();

    }

    /*
    设置RadioGroup的事件监听
     */
    private void initListener() {

        rg_bottom_tag.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置默认主页面
        rg_bottom_tag.check(R.id.rb_foundation);
    }

    /*
    初始化fragment
     */
    private void initFragment() {

        fragments = new ArrayList<>();
        fragments.add(new FoundationFragment());//基础知识
        fragments.add(new InterviewFragment());//面试
        fragments.add(new FrameFragment());//框架
        fragments.add(new MyFragment());//我的

    }

    /**
     * 初始化控件
     */
    private void initView() {
        setContentView(R.layout.activity_main);
        rg_bottom_tag = (RadioGroup) findViewById(R.id.rg_bottom_tag);
        tv_title = (TextView)findViewById(R.id.tv_title);
    }

    /*
    设置radiogroup的监听
     */
    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_foundation:
                    position = 0;
                    tv_title.setText("基础");
                    break;
                case R.id.rb_interview:
                    position = 1;
                    tv_title.setText("面试");
                    break;
                case R.id.rb_frame:
                    position = 2;
                    tv_title.setText("框架");
                    break;
                case R.id.rb_my:
                    position = 3;
                    tv_title.setText("我的");
                    break;
                default:
                    position = 0;
                    tv_title.setText("基础");
                    break;
            }

            //根据位置获得对应的fragment
            Fragment toFragment = getFragment();
            //切换到相应的fragment
            switchFrament(fromFragment,toFragment);
        }

    }

    /**
     * 得到Fragment
     */
    private BaseFragment getFragment() {
        if (fragments != null) {
            BaseFragment baseFragment =  fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    /**
     *
     * @param from 刚显示的Fragment,马上就要被隐藏了
     * @param to 马上要切换到的Fragment，一会要显示
     */
    private void switchFrament(Fragment from,Fragment to) {
        if(from != to){
            fromFragment = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //才切换
            //判断有没有被添加
            if(!to.isAdded()){
                //to没有被添加
                //from隐藏
                if(from != null){
                    ft.hide(from);
                }
                //添加to
                if(to != null){
                    ft.add(R.id.fl_content,to).commit();
                }
            }else{
                //to已经被添加
                // from隐藏
                if(from != null){
                    ft.hide(from);
                }
                //显示to
                if(to != null){
                    ft.show(to).commit();
                }
            }
        }
    }
    //软件的退出功能
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (position != 0) {//不是第一个页面
                position = 0;
                rg_bottom_tag.check(R.id.rb_foundation);
                return true;
            }else if(!isExit) {
                isExit = true;
                Toast.makeText(MainActivity.this, "再点击一次退出", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                },2000);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
