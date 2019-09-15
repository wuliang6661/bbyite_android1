package com.baibeiyun.bbyiot.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.baibeiyun.bbyiot.application.BaseApplication;
import com.baibeiyun.bbyiot.common.IConstant;

/**
 * Created by liugang on 2019/5/8.
 */
public class SpUtils {

    private static final int sp_mode = Context.MODE_PRIVATE;

    public static boolean put(String key, Object value) {
        SharedPreferences preferences = BaseApplication.getContext().getSharedPreferences(IConstant.APP_NAME, sp_mode);
        SharedPreferences.Editor edit = preferences.edit();

        if (value instanceof String) {
            if (!TextUtils.isEmpty((CharSequence) value)) {
                edit.putString(key, (String) value);
            }
        } else if (value instanceof Boolean) {
            edit.putBoolean(key, (Boolean) value);

        } else if (value instanceof Float) {
            edit.putFloat(key, (Float) value);

        } else if (value instanceof Integer) {
            edit.putInt(key, (Integer) value);
        } else {
            edit.putLong(key, (Long) value);
        }

        boolean commit = edit.commit();
        return commit;
    }


    public static String getString(String key, String defualt) {
        SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences(IConstant.APP_NAME, sp_mode);
        return sharedPreferences.getString(key, defualt);
    }

    public static int getInt(String key, int defualt) {
        SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences(IConstant.APP_NAME, sp_mode);
        return sharedPreferences.getInt(key, defualt);
    }

    public static boolean getBoolean(String key, boolean defualt) {
        SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences(IConstant.APP_NAME, sp_mode);
        return sharedPreferences.getBoolean(key, defualt);
    }

    //销毁
    public static void remove(String key) {
        SharedPreferences preferences = BaseApplication.getContext().getSharedPreferences(IConstant.APP_NAME, sp_mode);
        SharedPreferences.Editor edit = preferences.edit();
        edit.remove(key);
        edit.commit();

    }
}
