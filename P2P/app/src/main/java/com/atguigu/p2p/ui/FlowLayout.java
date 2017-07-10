package com.atguigu.p2p.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shkstart on 2016/8/16 0016.
 */
public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
      protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //获取当前布局的宽度、高度的设置模式以及值
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //在AT_MOST模式下，计算布局的宽度、高度
        int width = 0;
        int height = 0;

        //定义每一行显示的宽度和高度
        int lineWidth = 0;
        int lineHeight = 0;
        //获取子视图的个数
        int childCount = getChildCount();
        for(int i = 0; i < childCount; i++) {

            //获取每一个子视图
            View childView = getChildAt(i);
            //保证如下方法调用以后，可以测量子视图的宽度、高度
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            //获取每一个子View的margin
            MarginLayoutParams mp = (MarginLayoutParams) childView.getLayoutParams();
            //摆放在同一行上
            if(lineWidth + childWidth + mp.leftMargin + mp.rightMargin <= widthSize){
                lineWidth += childWidth + mp.leftMargin + mp.rightMargin;
                lineHeight = Math.max(lineHeight,childHeight + mp.topMargin + mp.bottomMargin);
            }else{//换行
                width = Math.max(width,lineWidth);
                height += lineHeight;
                //重置
                lineWidth = childWidth + mp.leftMargin + mp.rightMargin;
                lineHeight = childHeight + mp.topMargin + mp.bottomMargin;
            }
            //单独需要考虑最后一个元素
            if(i == childCount -1){
                width = Math.max(width,lineWidth);
                height += lineHeight;
            }

        }

        Log.e("TAG", "width = " + width + ",height = " + height);
        Log.e("TAG", "widthSize = " + widthSize + ",heightSize = " + heightSize);
        //调用此方法，给布局设置宽度、高度
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : width,heightMode == MeasureSpec.EXACTLY ? heightSize : height);
    }


    private List<Integer> allHeights = new ArrayList<>();//保存每一行的高度
    private List<List<View>> allViews = new ArrayList<>();//保存每一行的所有的view
    //作用：调用所有的子View的layout()，指定每一个子view的布局位置
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();

        int lineWidth = 0;
        int lineHeight = 0;
        int childCount = getChildCount();
        List<View> views = new ArrayList<>();//保存一行中出现的所有的view
        for(int i = 0; i < childCount; i++) {

            View childView = getChildAt(i);
            //保证如下方法调用以后，可以测量子视图的宽度、高度
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            //获取每一个子View的margin
            MarginLayoutParams mp = (MarginLayoutParams) childView.getLayoutParams();

            if(lineWidth + childWidth + mp.leftMargin + mp.rightMargin <= width){//还在同一行
                views.add(childView);
                lineWidth += childWidth + mp.leftMargin + mp.rightMargin;
                lineHeight = Math.max(lineHeight,childHeight + mp.topMargin + mp.bottomMargin);
            }else{//换行
                allViews.add(views);
                allHeights.add(lineHeight);

                //重置
                views = new ArrayList<>();
                views.add(childView);
                lineWidth = childWidth + mp.leftMargin + mp.rightMargin;
                lineHeight = childHeight + mp.topMargin + mp.bottomMargin;
            }

            if(i == childCount - 1){
                allViews.add(views);
                allHeights.add(lineHeight);
            }

        }

        Log.e("TAG", "allViews.size():" + allViews.size() + ",allHeight.size():" + allHeights.size());

        int x = 0;
        int y = 0;
        for(int i = 0;i < allViews.size();i++){
            List<View> list = allViews.get(i);
            int lineHeight1 = allHeights.get(i);

            for(View view : list){//遍历每一行中的每一个view

                int viewWidth = view.getMeasuredWidth();
                int viewHeight = view.getMeasuredHeight();
                MarginLayoutParams mp = (MarginLayoutParams) view.getLayoutParams();

                int viewLeft = x + mp.leftMargin;
                int viewTop = y + mp.topMargin;
                int viewRight = viewLeft + viewWidth;
                int viewBottom = viewTop + viewHeight;

                view.layout(viewLeft,viewTop,viewRight,viewBottom);

                x += viewWidth + mp.leftMargin + mp.rightMargin;//将现有的view占据的宽度添加到x上

            }
            x = 0;
            y += lineHeight1;//每次要换行的时候，需要调整y
        }


    }

    //为了上面可以调用子视图的getLayoutParams()得到一个MarginLayoutParams，需要提供如下的方法
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        MarginLayoutParams mp = new MarginLayoutParams(getContext(), attrs);
        return mp;
    }
}
