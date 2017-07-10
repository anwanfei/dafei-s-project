package com.atguigu.p2p.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

/**
 * Created by shkstart on 2016/8/18 0018.
 * 图片处理的工具类
 */
public class BitmapUtils {

    public static Bitmap circleBitmap(Bitmap source){//source:就是头像图片
        //得到图片的宽度
        int width = source.getWidth();
        //创建一个内存层面的Bitmap对象
        Bitmap target = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        //创建对应的画布
        Canvas canvas = new Canvas(target);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        //绘制下层的圆
        canvas.drawCircle(width/2,width/2,width/2,paint);
        //设置图片相交情况下的处理方式
        //setXfermode：设置当绘制的图像出现相交情况时候的处理方式的,它包含的常用模式有哪几种
        //PorterDuff.Mode.SRC_IN 取两层图像交集部分,只显示上层图像,注意这里是指取相交叉的部分,然后显示上层图像
        //PorterDuff.Mode.DST_IN 取两层图像交集部分,只显示下层图像
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //绘制上层的bitmap
        canvas.drawBitmap(source,0,0,paint);

        return target;
    }

    public static Bitmap zoom(Bitmap source, float w, float h) {//参数2,3必须声明为float类型，不能为int
        Matrix matrix = new Matrix();

        float width = w / source.getWidth();
        float height = h / source.getHeight();
        matrix.postScale(width,height);//进行缩放处理

        Bitmap bitmap = Bitmap.createBitmap(source,0,0,source.getWidth(),source.getHeight(),matrix,true);
        return bitmap;
    }
}
