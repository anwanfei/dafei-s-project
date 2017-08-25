package com.anwanfei.anfly;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.anwanfei.anfly.common.BaseActivity;
import com.anwanfei.anfly.common.BaseFragment;
import com.anwanfei.anfly.foundation.fragment.FoundationFragment;
import com.anwanfei.anfly.my.fragment.MyFragment;
import com.anwanfei.anfly.summary.fragment.SummaryFragment;
import com.anwanfei.anfly.third.fragment.ThirdFragment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity {


    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.rb_foundation)
    RadioButton rbFoundation;
    @BindView(R.id.rb_summary)
    RadioButton rbSummary;
    @BindView(R.id.rb_third_party)
    RadioButton rbThirdParty;
    @BindView(R.id.rb_my)
    RadioButton rbMy;
    @BindView(R.id.rg_bottom_tag)
    RadioGroup rgBottomTag;
    private ArrayList<BaseFragment> fragments;
    private int position;
    private Fragment fromFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListener();
    }

    private void initListener() {
        rgBottomTag.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置默认主页面
        rgBottomTag.check(R.id.rb_foundation);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();

        fragments.add(new FoundationFragment());
        fragments.add(new SummaryFragment());
        fragments.add(new ThirdFragment());
        fragments.add(new MyFragment());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
            switch (checkedId) {
                case R.id.rb_foundation:
                    position = 0;
                    break;
                case R.id.rb_summary:
                    position = 1;
                    break;
                case R.id.rb_third_party:
                    position = 2;
                    break;
                case R.id.rb_my:
                    position = 3;
                    break;
                default:
                    position = 0;
                    break;
            }

            BaseFragment toFragment = getFragment();
            switchFragment(fromFragment, toFragment);
        }
    }

    private void switchFragment(Fragment from, Fragment to) {
        if (from != to) {
            fromFragment = to;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            if (!to.isAdded()) {
                //判断是否被add过
                if (from != null) {
                    transaction.hide(from);
                }
                if (to != null) {
                    transaction.add(R.id.fl_content, to).commit();
                }
            } else {
                if (from != null) {
                    transaction.hide(from);
                }
                if (to != null) {
                    transaction.show(to).commit();
                }
            }

        }
    }

    public void test() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };
        Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("Hello");
                e.onNext("Hi");
                e.onNext("Aloha");
                e.onComplete();

            }
        });

        observable.subscribe(observer);

    }


    private BaseFragment getFragment() {
        if (fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }
}
