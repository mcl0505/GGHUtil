package com.example.easy.base;

import android.content.Context;

/**
 * @ClassName: IBaseView
 * @Description: java类作用描述
 * @Author: mcl
 * @Date: 2019/12/22 17:32
 */
public interface IBaseView {
    /**
     * 显示加载框
     */
    void showLoading();

    /**
     * 隐藏加载框
     */
    void dismissLoading();

    void toast(String msg);

    /**
     * 上下文
     *
     * @return context
     */
    Context getContext();
}
