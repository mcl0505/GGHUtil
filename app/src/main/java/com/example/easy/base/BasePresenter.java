package com.example.easy.base;

import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * @ClassName: BasePresenter
 * @Description: java类作用描述
 * @Author: mcl
 * @Date: 2019/12/22 17:31
 */
public abstract class BasePresenter<M extends IBaseModel, V extends IBaseView> {

    private WeakReference<V> mvpView;
    private M mvpModel;

    /**
     * 绑定View
     */
    @SuppressWarnings("unchecked")
    public void attachView(V view) {
        mvpView = new WeakReference<>(view);
        if (mvpModel == null) {
            mvpModel = createModule();
        }
    }

    /**
     * 解绑View
     */
    public void detachView() {
        if (null != mvpView) {
            mvpView.clear();
            mvpView = null;
        }
        this.mvpModel = null;
    }

    /**
     * 是否与View建立连接
     */
    protected boolean isViewAttached() {
        return null != mvpView && null != mvpView.get();
    }

    protected V getView() {
        return isViewAttached() ? mvpView.get() : null;
    }

    protected M getModule() {
        return mvpModel;
    }

    protected Context getContext() {
        return getView().getContext();
    }

    protected void showLoading() {
        getView().showLoading();
    }

    protected void dismissLoading() {
        getView().dismissLoading();
    }

    protected void toast(String s) {
        getView().toast(s);
    }


    /**
     * 通过该方法创建Module
     */
    protected abstract M createModule();

    /**
     * 初始化方法
     */
    public abstract void start();
}