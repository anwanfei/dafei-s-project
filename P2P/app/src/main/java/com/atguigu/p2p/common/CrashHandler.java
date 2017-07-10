package com.atguigu.p2p.common;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by shkstart on 2016/8/13 0013.
 * 程序中的未捕获的全局异常的捕获（单例）
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;
    private Context mContext;

    //懒汉式的单例模式（不用考虑同步）
    private CrashHandler(){}

    private static CrashHandler crashHandler = null;

    public static CrashHandler getInstance(){
        if(crashHandler == null){
            crashHandler = new CrashHandler();
        }
        return crashHandler;
    }

    public void init(Context context){
        this.mContext = context;
        //获取系统默认的处理未捕获的异常的实现类
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        //将当前自定义UncaughtExceptionHandler的实现类作为出现未捕获异常时的处理类
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当程序执行时，出现未捕获的异常时，自动执行如下的uncaughtException()
     * @param thread
     * @param ex
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if(ex == null){
            defaultUncaughtExceptionHandler.uncaughtException(thread,ex);
        }else{
//            Log.e("TAG", "亲，出现异常了！");
            handleException(ex);
        }
    }

    private void handleException(Throwable ex) {
        new Thread(){
            @Override
            public void run() {

                Looper.prepare();
                //执行在主线程中的代码
                Toast.makeText(mContext, "亲，出现了一个小故障！", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        //收集用户的异常信息，并发送给服务器端
        collectionException(ex);

        //执行后续的关闭资源的一系列操作
        try {
            Thread.sleep(2000);

            //将所有的activity移除
            AppManager.getInstance().removeAll();
            //关闭当前的进程
            android.os.Process.killProcess(android.os.Process.myPid());
            //关闭虚拟机
            System.exit(0);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void collectionException(Throwable ex) {
        //获取异常的相关信息
        final String message = ex.getMessage();
        //获取手机的信息
        final String proInfo = Build.DEVICE + "--" + Build.PRODUCT + "---" + Build.MODEL + "--" + Build.VERSION.SDK_INT;

         //将异常信息发送给服务器端
        //这里就简单处理了
        new Thread(){
            @Override
            public void run() {
                Log.e("TAG", "message = " + message + ",proInfo = " + proInfo);
            }
        }.start();



    }
}
