package com.junhangxintong.chuanzhangtong.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by anwanfei on 2017/8/24.
 */

public class MultiVerify {
    /**
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p;
        Matcher m;
        boolean b;
        p = Pattern.compile("^((13[0-9])|(14[5,7,9])|(15[^4,\\D])|(17[0,1,3,5-8])|(18[0-9]))\\d{8}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 邮箱验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isMail(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
       /* p = Pattern.compile("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-z]{2,}$"); // 验证邮箱
        m = p.matcher(str);
        b = m.matches();*/
        if (str.matches("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$")) {
            return true;
        }
        return false;
    }

    /**
     * 校验身份证-18位
     *
     * @param id
     * @return
     */
    public static boolean isIdCard(String id) {
        if (id.matches("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$")) {
            return true;
        }
        return false;
    }
}
