package com.junhangxintong.chuanzhangtong.mine.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.CircleImageView;
import com.junhangxintong.chuanzhangtong.utils.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalInfoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_photo)
    CircleImageView ivPhoto;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.rl_user_name)
    RelativeLayout rlUserName;
    @BindView(R.id.tv_label)
    TextView tvLabel;
    @BindView(R.id.rl_label)
    RelativeLayout rlLabel;
    @BindView(R.id.tv_duty)
    TextView tvDuty;
    @BindView(R.id.rl_duty)
    RelativeLayout rlDuty;
    @BindView(R.id.tv_gender)
    TextView tvGender;
    @BindView(R.id.rl_gender)
    RelativeLayout rlGender;
    @BindView(R.id.tv_contact_number)
    TextView tvContactNumber;
    @BindView(R.id.rl_contact_number)
    RelativeLayout rlContactNumber;
    @BindView(R.id.tv_emergency_contactor)
    TextView tvEmergencyContactor;
    @BindView(R.id.rl_emergency_contactor)
    RelativeLayout rlEmergencyContactor;
    @BindView(R.id.tv_emergency_contactor_phone)
    TextView tvEmergencyContactorPhone;
    @BindView(R.id.rl_emergency_contactor_phone)
    RelativeLayout rlEmergencyContactorPhone;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private PopupWindow genderPopWindow;
    private PopupWindow photoPopWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.personal_info));

        String userName = CacheUtils.getString(this, Constants.USER_NAME);
        String gender = CacheUtils.getString(this, Constants.GENDER);
        String contact_phone = CacheUtils.getString(this, Constants.CONTACT_PHONE);
        String emergency_contactor = CacheUtils.getString(this, Constants.EMERGENCY_CONTACTOR);
        String emergency_contactor_phone = CacheUtils.getString(this, Constants.EMERGENCY_CONTACTOR_PHONE);
        String duty = CacheUtils.getString(this, Constants.DUTY);
        //获取拍的照片
        String path = Environment.getExternalStorageDirectory() + Constants.PHONE_PATH;
        File file = new File(path);
        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            ivPhoto.setImageBitmap(bitmap);
        }
        if (!userName.isEmpty()) {
            tvUserName.setText(userName);
        }
        if (!gender.isEmpty()) {
            tvGender.setText(gender);
        }
        if (!contact_phone.isEmpty()) {
            tvContactNumber.setText(contact_phone);
        }
        if (!emergency_contactor.isEmpty()) {
            tvEmergencyContactor.setText(emergency_contactor);
        }
        if (!emergency_contactor_phone.isEmpty()) {
            tvEmergencyContactorPhone.setText(emergency_contactor_phone);
        }
        if (!duty.isEmpty()) {
            tvDuty.setText(duty);
        }


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_info;
    }

    @OnClick({R.id.iv_back, R.id.iv_photo, R.id.rl_user_name, R.id.rl_label, R.id.rl_duty, R.id.rl_gender, R.id.rl_contact_number, R.id.rl_emergency_contactor, R.id.rl_emergency_contactor_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_photo:
                showPhotoPickerPopupWindow();
                break;
            case R.id.rl_user_name:
                startActivityForResult(new Intent(PersonalInfoActivity.this, ModifyNameActivity.class), Constants.REQUEST_CODE0);
                break;
            case R.id.rl_label:
                break;
            case R.id.rl_duty:
                startActivityForResult(new Intent(PersonalInfoActivity.this, ChooseDutyActivity.class), Constants.REQUEST_CODE4);
                break;
            case R.id.rl_gender:
                showChooseGenderPopupWindow();
                break;
            case R.id.rl_contact_number:
                startActivityForResult(new Intent(PersonalInfoActivity.this, ContactPhoneActivity.class), Constants.REQUEST_CODE1);
                break;
            case R.id.rl_emergency_contactor:
                startActivityForResult(new Intent(PersonalInfoActivity.this, EmergencyContactorActivity.class), Constants.REQUEST_CODE2);
                break;
            case R.id.rl_emergency_contactor_phone:
                startActivityForResult(new Intent(PersonalInfoActivity.this, EmergencyContactorPhoneActivity.class), Constants.REQUEST_CODE3);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String path = getCacheDir() + "/tx.png";
        if (data != null) {
            switch (requestCode) {
                case Constants.REQUEST_CODE0:
                    String userName = data.getStringExtra(Constants.USER_NAME);
                    CacheUtils.putString(this, Constants.USER_NAME, userName);
                    tvUserName.setText(userName);
                    break;
                case Constants.REQUEST_CODE1:
                    String contact_phone = data.getStringExtra(Constants.CONTACT_PHONE);
                    CacheUtils.putString(this, Constants.CONTACT_PHONE, contact_phone);
                    tvContactNumber.setText(contact_phone);
                    break;
                case Constants.REQUEST_CODE2:
                    String emergency_contactor = data.getStringExtra(Constants.EMERGENCY_CONTACTOR);
                    CacheUtils.putString(this, Constants.EMERGENCY_CONTACTOR, emergency_contactor);
                    tvEmergencyContactor.setText(emergency_contactor);
                    break;
                case Constants.REQUEST_CODE3:
                    String emergency_contactor_phone = data.getStringExtra(Constants.EMERGENCY_CONTACTOR_PHONE);
                    CacheUtils.putString(this, Constants.EMERGENCY_CONTACTOR_PHONE, emergency_contactor_phone);
                    tvEmergencyContactorPhone.setText(emergency_contactor_phone);
                    break;
                case Constants.REQUEST_CODE4:
                    String dutyName = data.getStringExtra(Constants.DUTY);
                    CacheUtils.putString(this, Constants.DUTY, dutyName);
                    tvDuty.setText(dutyName);

                    break;
                case Constants.REQUEST_CODE_CAMERA:
                    //拍照
                    Bundle bundle = data.getExtras();
                    // 获取相机返回的数据，并转换为图片格式
                    Bitmap bitmap = (Bitmap) bundle.get(Constants.DATA);
                    try {
                        FileOutputStream fos = new FileOutputStream(path);
                        //bitmap压缩(压缩格式、质量、压缩文件保存的位置)
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                        ivPhoto.setImageBitmap(bitmap);

                        //将图片保存在本地
                        saveImage(bitmap);

                        //上传服务器
//                        netSendPhoneToService();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case Constants.REQUEST_CODE_ALBUM:
                    Uri imageUri = data.getData();
                    String[] filePathColumns = {MediaStore.Images.Media.DATA};
                    Cursor c = getContentResolver().query(imageUri, filePathColumns, null, null, null);
                    c.moveToFirst();
                    int columnIndex = c.getColumnIndex(filePathColumns[0]);
                    String imagePath = c.getString(columnIndex);
                    c.close();

                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true; // 只获取图片的大小信息，而不是将整张图片载入在内存中，避免内存溢出
                    BitmapFactory.decodeFile(imagePath, options);
                    int height = options.outHeight;
                    int width = options.outWidth;
                    int inSampleSize = 2; // 默认像素压缩比例，压缩为原图的1/2
                    int minLen = Math.min(height, width); // 原图的最小边长
                    if (minLen > 100) { // 如果原始图像的最小边长大于100dp（此处单位我认为是dp，而非px）
                        float ratio = (float) minLen / 100.0f; // 计算像素压缩比例
                        inSampleSize = (int) ratio;
                    }
                    options.inJustDecodeBounds = false; // 计算好压缩比例后，这次可以去加载原图了
                    options.inSampleSize = inSampleSize; // 设置为刚才计算的压缩比例
                    Bitmap bm = BitmapFactory.decodeFile(imagePath, options); // 解码文件
                    Log.w("TAG", "size: " + bm.getByteCount() + " width: " + bm.getWidth() + " heigth:" + bm.getHeight()); // 输出图像数据
                    ivPhoto.setImageBitmap(bm);
                    c.close();
                    //本地保存
                    saveImage(bm);
                    //图片上传
//                    netSendPhoneToService();
                    break;
            }
        }
    }

    private void saveImage(Bitmap image) {
        //判断sd卡是否处于挂载状态
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File filesDir = this.getExternalFilesDir(null);//sdcard/Android/data/应用包名/file/...jpg
            File file = new File(filesDir, "icon.jpg");

            try {
                image.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void showChooseDutyDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_choose_duty, null);
        final TextView tv_title = (TextView) dialogView.findViewById(R.id.tv_title);
        ImageView iv_back = (ImageView) dialogView.findViewById(R.id.iv_back);
        iv_back.setVisibility(View.VISIBLE);
        final ListView listview_province = (ListView) dialogView.findViewById(R.id.listview_province);
        tv_title.setText(getResources().getString(R.string.choose_duty));


        final AlertDialog.Builder provinceDialog = new AlertDialog.Builder(PersonalInfoActivity.this, R.style.Dialog_Fullscreen_not_transparent);
        provinceDialog.setView(dialogView);
        provinceDialog.show();
    }

    private void showChooseGenderPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(PersonalInfoActivity.this).inflate(R.layout.choose_gender_picker_popupwindow_layut, null);
        genderPopWindow = new PopupWindow(contentView,
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT, true);
        genderPopWindow.setContentView(contentView);
        genderPopWindow.setBackgroundDrawable(new BitmapDrawable());
        genderPopWindow.setFocusable(true);
        backgroundAlpha(0.5f);
        TextView tv_man = (TextView) contentView.findViewById(R.id.tv_man);
        TextView tv_woman = (TextView) contentView.findViewById(R.id.tv_woman);
        TextView tv_cancel_choose_gender = (TextView) contentView.findViewById(R.id.tv_cancel_choose_gender);

        tv_man.setOnClickListener(this);
        tv_woman.setOnClickListener(this);
        tv_cancel_choose_gender.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(PersonalInfoActivity.this).inflate(R.layout.activity_personal_info, null);
        genderPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
        genderPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
    }

    private void showPhotoPickerPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(PersonalInfoActivity.this).inflate(R.layout.photo_picker_popupwindow_layut, null);
        photoPopWindow = new PopupWindow(contentView,
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT, true);
        photoPopWindow.setContentView(contentView);
        photoPopWindow.setBackgroundDrawable(new BitmapDrawable());
        photoPopWindow.setFocusable(true);
        backgroundAlpha(0.5f);
        TextView tv_camera = (TextView) contentView.findViewById(R.id.tv_camera);
        TextView tv_album = (TextView) contentView.findViewById(R.id.tv_album);
        TextView tv_cancel = (TextView) contentView.findViewById(R.id.tv_cancel);
        tv_cancel.setTextColor(getResources().getColor(R.color.red));
        tv_camera.setOnClickListener(this);
        tv_album.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(PersonalInfoActivity.this).inflate(R.layout.activity_personal_info, null);
        photoPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
        photoPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                photoPopWindow.dismiss();
                break;
            case R.id.tv_camera:
                startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), Constants.REQUEST_CODE_CAMERA);
                photoPopWindow.dismiss();
                break;
            case R.id.tv_album:
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), Constants.REQUEST_CODE_ALBUM);
                photoPopWindow.dismiss();
                break;
            case R.id.tv_cancel_choose_gender:
                genderPopWindow.dismiss();
                break;
            case R.id.tv_man:
                tvGender.setText(getResources().getString(R.string.man));
                CacheUtils.putString(this, Constants.GENDER, getResources().getString(R.string.man));
                genderPopWindow.dismiss();
                break;
            case R.id.tv_woman:
                tvGender.setText(getResources().getString(R.string.woman));
                CacheUtils.putString(this, Constants.GENDER, getResources().getString(R.string.woman));
                genderPopWindow.dismiss();
                break;
        }
    }
}
