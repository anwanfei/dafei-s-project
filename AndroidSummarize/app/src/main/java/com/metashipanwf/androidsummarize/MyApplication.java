package com.metashipanwf.androidsummarize;

import android.app.Application;
import android.content.Context;

/**
 * author:AnWanfei
 * time:2017/2/23.
 * Email:anwanfei_sp@163.com
 * function:
 */

public class MyApplication extends Application {

    /**对外提供整个应用生命周期的Context**/
    private static Context instance;
    /**
     * 对外提供Application Context
     * @return
     */
    public static Context gainContext() {
        return instance;
    }
}
