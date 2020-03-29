package com.example.easy;

import android.content.Context;

import com.example.easy.utils.AppUtils;
import com.example.easy.utils.ToastUtil;

/**
 * @ClassName: AppConfig
 * @Description: App全局配置Context
 * @Author: mcl
 * @Date: 2019/12/22 18:00
 */
public class AppConfig {
    private static Context mContext;


    public static void init(Context context){
        mContext = context;
        ToastUtil.init(mContext);
        AppUtils.init(mContext);
    }
}
