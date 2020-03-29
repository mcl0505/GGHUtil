package com.example.easy.contract;

import com.example.easy.base.IBaseModel;
import com.example.easy.base.IBaseView;
import com.example.easy.bean.LoginBean;
import com.example.easy.callback.ResponseCallback;

/**
 * @ClassName: LoginContract
 * @Description: 登录约束
 * @Author: mcl
 * @Date: 2019/12/22 17:15
 * Contract是Interface，每一组mvp行为对应一个Contract
 */
public interface LoginContract {
    interface Model extends IBaseModel {
        /**
         * 统一登录接口
         * @param userName  用户名
         * @param password  密码
         * @param callback  回调
         */
        void login(String userName, String password, ResponseCallback<LoginBean> callback);
    }

    interface View extends IBaseView {
        void loginSuccess(String token);
    }

    interface Presenter {
        void login(String userName,String password);
    }

}
