package com.example.easy.base;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.easy.utils.ToastUtil;

/**
 * @ClassName: BaseMVPActivity
 * @Description: java类作用描述
 * @Author: mcl
 * @Date: 2019/12/22 17:37
 */
public abstract class BaseMVPActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        //初始化mPresenter
        initPresenter();
        //绑定view
        if(mPresenter != null){
            mPresenter.attachView(this);
        }
        //初始化
        initView();
        initData();
    }

    /**
     * 初始化视图控件
     */
    protected abstract void initView();

    /**
     * 逻辑
     */
    protected abstract void initData();

    /**
     * 初始化  Presenter
     */
    protected abstract void initPresenter();

    /**
     * 获取布局
     * @return
     */
    protected abstract int getLayout();

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void toast(String msg) {
        ToastUtil.showShort(msg);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
