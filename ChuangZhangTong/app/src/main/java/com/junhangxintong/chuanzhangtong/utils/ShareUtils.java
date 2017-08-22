package com.junhangxintong.chuanzhangtong.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

/**
 * Created by anwanfei on 2017/8/22.
 */

public class ShareUtils {
    public static void share(final Context mContext, UMWeb web) {
        new ShareAction((Activity) mContext).withMedia(web)
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN_CIRCLE)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        //分享开始的回调
                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        Toast.makeText(mContext, share_media + " 分享成功啦", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                        Toast.makeText(mContext, share_media + " 分享失败啦", Toast.LENGTH_SHORT).show();
                        if (throwable != null) {
                            Log.d("throw", "throw:" + throwable.getMessage());
                        }
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                        Toast.makeText(mContext, share_media + " 分享取消了", Toast.LENGTH_SHORT).show();
                    }
                }).open();
    }
}
