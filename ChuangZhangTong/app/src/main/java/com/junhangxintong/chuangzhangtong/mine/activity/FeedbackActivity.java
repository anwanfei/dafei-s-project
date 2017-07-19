package com.junhangxintong.chuangzhangtong.mine.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseActivity;
import com.junhangxintong.chuangzhangtong.mine.adapter.PhotoAdapter;
import com.junhangxintong.chuangzhangtong.utils.GlideImageLoader;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FeedbackActivity extends BaseActivity implements View.OnClickListener {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 8;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_input_feedback)
    EditText etInputFeedback;
    @BindView(R.id.et_mail_box)
    EditText etMailBox;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.iv_add_photo_feedback)
    ImageView ivAddPhotoFeedback;
    @BindView(R.id.ll_add_photo_feedback)
    LinearLayout llAddPhotoFeedback;
    @BindView(R.id.rvResultPhoto)
    RecyclerView rvResultPhoto;
    private PopupWindow popupWindow;
    private IHandlerCallBack iHandlerCallBack;
    private String TAG = "junhang";

    //存储获得图片路径的集合
    private List<String> path = new ArrayList<>();
    private GalleryConfig gallrtyConfig;
    private PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initGallery();

        init();
    }

    private void init() {
        gallrtyConfig = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader())
                .iHandlerCallBack(iHandlerCallBack)
                .provider("com.junhangxintong.chuangzhangtong.fileprovider")
                .pathList(path)
                .multiSelect(true)
                .multiSelect(true, 3)
                .maxSize(3)
                .isShowCamera(true)
                .filePath("/Gallery/Pictures")
                .build();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvResultPhoto.setLayoutManager(gridLayoutManager);

        photoAdapter = new PhotoAdapter(this, path);
        rvResultPhoto.setAdapter(photoAdapter);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.feed_back));
    }

    @Override
    protected void initData() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @OnClick({R.id.iv_back, R.id.tv_save, R.id.iv_add_photo_feedback, R.id.ll_add_photo_feedback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_save:
                break;
            case R.id.iv_add_photo_feedback:
                showChoosePhotesPop();
                break;
            case R.id.ll_add_photo_feedback:
                break;
        }
    }

    private void showChoosePhotesPop() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.choose_gender_picker_popupwindow_layut, null);

        popupWindow = new PopupWindow(inflate, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(inflate);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        backgroundAlpha(0.5f);
        TextView tv_man = (TextView) inflate.findViewById(R.id.tv_man);
        tv_man.setText(getResources().getString(R.string.camera));
        TextView tv_woman = (TextView) inflate.findViewById(R.id.tv_woman);
        tv_woman.setText(getResources().getString(R.string.album));
        TextView tv_cancel_choose_gender = (TextView) inflate.findViewById(R.id.tv_cancel_choose_gender);

        tv_man.setOnClickListener(this);
        tv_woman.setOnClickListener(this);
        tv_cancel_choose_gender.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(FeedbackActivity.this).inflate(R.layout.activity_personal_info, null);
        popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel_choose_gender:
                popupWindow.dismiss();
                break;
            case R.id.tv_man:
                GalleryPick.getInstance().setGalleryConfig(gallrtyConfig).openCamera(FeedbackActivity.this);
                popupWindow.dismiss();
                break;
            case R.id.tv_woman:
                gallrtyConfig.getBuilder().isOpenCamera(false).build();
                initPermissions();
                popupWindow.dismiss();
                break;
        }
    }

    // 授权管理
    private void initPermissions() {
        if (ContextCompat.checkSelfPermission(FeedbackActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请在 设置-应用管理 中开启此应用的储存授权。", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        } else {
            GalleryPick.getInstance().setGalleryConfig(gallrtyConfig).open(this);
        }
    }

    private void initGallery() {
        iHandlerCallBack = new IHandlerCallBack() {
            @Override
            public void onStart() {
                Log.i(TAG, "onStart: 开启");
            }

            @Override
            public void onSuccess(List<String> photoList) {
                Log.i(TAG, "onSuccess: 返回数据");
                path.clear();
                for (String s : photoList) {
                    Log.i(TAG, s);
                    path.add(s);
                }
                photoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancel() {
                Log.i(TAG, "onCancel: 取消");
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "onFinish: 结束");
            }

            @Override
            public void onError() {
                Log.i(TAG, "onError: 出错");
            }
        };
    }
}
