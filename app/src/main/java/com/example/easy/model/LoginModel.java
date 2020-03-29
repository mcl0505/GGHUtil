package com.example.easy.model;

import android.text.TextUtils;

import com.example.easy.bean.LoginBean;
import com.example.easy.callback.ResponseCallback;

/**
 * @ClassName: LoginModel
 * @Description: 登录网络请求
 * @Author: mcl
 * @Date: 2019/12/22 17:22
 */
public class LoginModel implements LC {
    @Override
    public void login(String userName, String password, ResponseCallback<LoginBean> callback) {

        if (TextUtils.equals("admin",userName)){
            if (TextUtils.equals("123456",password)){
                LoginBean loginBean = new LoginBean();
                loginBean.setToken("1234567890");
                callback.success(loginBean);
            }else {
                callback.error("密码错误");
            }

        }else {
            callback.error("账号错误");
        }

    }
}
