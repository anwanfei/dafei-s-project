package com.junhangxintong.chuanzhangtong.mine.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.mine.Utils.EventBusMessage;
import com.junhangxintong.chuanzhangtong.mine.adapter.PhotoAdapter;
import com.junhangxintong.chuanzhangtong.mine.bean.SendVerifyCodeBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.GlideImageLoader;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.junhangxintong.chuanzhangtong.utils.PictureUtils;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import org.apache.commons.lang.StringUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

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
    private ArrayList<String> path = new ArrayList<>();
    private GalleryConfig gallrtyConfig;
    private PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initGallery();

        init();

        EventBus.getDefault().register(this);
    }

    private void init() {
        chooseAndShowPhotos();
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.feed_back));
    }

    @Override
    protected void initData() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCallback(EventBusMessage eventBusMessage) {
        path = eventBusMessage.getPhotos();

        chooseAndShowPhotos();
    }

    private void chooseAndShowPhotos() {
        gallrtyConfig = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader())
                .iHandlerCallBack(iHandlerCallBack)
                .provider("com.junhangxintong.chuanzhangtong.fileprovider")
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
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
                netCommitFeedback();
                break;
            case R.id.iv_add_photo_feedback:
                showChoosePhotesPop();
                break;
        }
    }

    private void netCommitFeedback() {

        String content = etInputFeedback.getText().toString();
        String phoneNum = etPhone.getText().toString();
        String userId = CacheUtils.getString(this, Constants.ID);

        Map<String, File> stringFileHashMap = new HashMap<>();
        for (int i = 0; i < path.size(); i++) {
            try {
                String bitmapToPath = PictureUtils.bitmapToPath(path.get(i));
                String key = i + ".jpg";
                stringFileHashMap.put(key, new File(bitmapToPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (StringUtils.isEmpty(content)) {
            Toast.makeText(FeedbackActivity.this, getResources().getString(R.string.input_feedback), Toast.LENGTH_SHORT).show();
            return;
        }
        final ProgressDialog progressDialog = getProgressDialog();
        NetUtils.postWithHeader(this, ConstantsUrls.FEEDBACK)
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.FEEDBACK_CONTENT, content)
                .addParams(Constants.MOBILEPHONE, phoneNum)
                .files(Constants.PICTRUE, stringFileHashMap)
                .build()
                .writeTimeOut(3000)
                .connTimeOut(3000)
                .readTimeOut(3000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(FeedbackActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        progressDialog.setProgress((int) progress);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        progressDialog.dismiss();
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(FeedbackActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            SendVerifyCodeBean sendVerifyCode = new Gson().fromJson(response, SendVerifyCodeBean.class);
                            String message = sendVerifyCode.getMessage();
                            String code = sendVerifyCode.getCode();
                            Toast.makeText(FeedbackActivity.this, message, Toast.LENGTH_SHORT).show();
                            if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(FeedbackActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(FeedbackActivity.this, LoginRegisterActivity.class));
                                finish();
                            }
                            if (code.equals("200")) {

                                finish();
                            }
                        }
                    }
                });

    }

    private void showChoosePhotesPop() {

        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(FeedbackActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
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
                if (path.size() < 3) {
                    GalleryPick.getInstance().setGalleryConfig(gallrtyConfig).openCamera(FeedbackActivity.this);
                } else {
                    Toast.makeText(FeedbackActivity.this, getResources().getString(R.string.photo_num_inner_three), Toast.LENGTH_SHORT).show();
                }
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
