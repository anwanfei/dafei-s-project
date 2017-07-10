package com.metashipanwf.androidsummarize.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by edz on 2017/7/10.
 */

public class FontUtil {
    static public void changeFonts(Context context, ViewGroup root, String font, int size, int color) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(), font);
        for (int i = 0; i < root.getChildCount(); i++) {
            View v = root.getChildAt(i);
            if (v instanceof TextView) {
                ((TextView) v).setTypeface(tf);
//                ((TextView)v).setTextSize((int)(size*metrics.density));
                ((TextView) v).setTextColor(color);
            } else if (v instanceof Button) {
            } else if (v instanceof EditText) {
                ((TextView) v).setTypeface(tf);
                ((TextView) v).setTextColor(color);
            } else if (v instanceof ViewGroup) {
                changeFonts(context, (ViewGroup) v, font, size, color);
            }
        }
    }

    static public void changeFont(Context context, View element, String font, int size, int color) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(), font);
        if (element instanceof TextView) {
            ((TextView) element).setTypeface(tf);
            ((TextView) element).setTextSize(size);
            ((TextView) element).setTextColor(color);
        } else if (element instanceof EditText) {
            ((EditText) element).setTypeface(tf);
        }
    }

}
