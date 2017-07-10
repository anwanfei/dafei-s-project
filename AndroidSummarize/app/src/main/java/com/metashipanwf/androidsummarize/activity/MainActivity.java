package com.metashipanwf.androidsummarize.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.metashipanwf.androidsummarize.R;
import com.metashipanwf.androidsummarize.fragment.BaseFragment;
import com.metashipanwf.androidsummarize.fragment.DisanfangFragment;
import com.metashipanwf.androidsummarize.fragment.HomeFragment;
import com.metashipanwf.androidsummarize.fragment.MyFragment;
import com.metashipanwf.androidsummarize.fragment.SummarizeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private RadioGroup rg_bottom_tag;
    private int position;
    private List<BaseFragment> fragments;
    private Fragment fromFragment;//切换前的fragment
    private boolean isExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //透明状态栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_main);

        //初始化布局
        initView();

        //初始化fragment
        initFragment();

        //对radiogroup设置监听
        initListener();
    }


    private void initListener() {
        rg_bottom_tag.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置默认主页面
        rg_bottom_tag.check(R.id.rb_home);
    }

    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                case R.id.rb_home:
                    position = 0;
                    break;
                case R.id.rb_psychology:
                    position = 1;
                    break;
                case R.id.rb_music:
                    position = 2;
                    break;
                case R.id.rb_my:
                    position = 3;
                    break;
                default:
                    position = 0;
                    break;
            }

            /*
            * 根据位置获得对应的fragment
            */
            Fragment toFragment = getFragment();

            /*
            *切换到相应的fragment
             */
            switchFrament(fromFragment, toFragment);
        }
    }

    private Fragment getFragment() {
        if (fragments != null) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    /**
     * @param from 刚显示的Fragment,马上就要被隐藏了
     * @param to   马上要切换到的Fragment，一会要显示
     */
    private void switchFrament(Fragment from, Fragment to) {
        if (from != to) {
            fromFragment = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //才切换
            //判断有没有被添加
            if (!to.isAdded()) {
                //to没有被添加
                //from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //添加to
                if (to != null) {
                    ft.add(R.id.fl_content, to).commit();
                }
            } else {
                //to已经被添加
                // from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //显示to
                if (to != null) {
                    ft.show(to).commit();
                }
            }
        }

    }


    private void initFragment() {
        fragments = new ArrayList<>();

        fragments.add(new HomeFragment());
        fragments.add(new SummarizeFragment());
        fragments.add(new DisanfangFragment());
        fragments.add(new MyFragment());
    }

    private void initView() {
        rg_bottom_tag = (RadioGroup) findViewById(R.id.rg_bottom_tag);
    }


    //退出功能
    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && !isExit) {
            isExit = true;
            Toast.makeText(MainActivity.this, "再一次退出", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/

    //软件的退出功能
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (position != 0) {//不是第一个页面
                position = 0;
                rg_bottom_tag.check(R.id.rb_home);
                return true;
            } else if (!isExit) {
                isExit = true;
                Toast.makeText(MainActivity.this, "2秒内再点击一次退出", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                }, 2000);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
