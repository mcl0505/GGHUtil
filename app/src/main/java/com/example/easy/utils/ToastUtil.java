package com.example.easy.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easy.AppApplication;

/**
 * @ClassName: ToastUtil
 * @Description: Toast 吐司工具类
 * @Author: mcl
 * @Date: 2019/12/22 17:45
 */
public class ToastUtil {
    private static Toast mToast;
    private static Context context;

    public static void init(Context mContext){
        context = mContext;
    }

    private static void showToast(Context context, String msg, int duration) {
        if(context!=null){
            context = context.getApplicationContext();
        }else{
            return;
        }
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, duration);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    public static void showShort(String msg) {// , int duration
        context = AppApplication.context;
        showToast(context, msg,Toast.LENGTH_SHORT);
    }

    public static void showLong(String msg) {// , int duration
        showToast(context, msg,Toast.LENGTH_LONG);
    }
}
