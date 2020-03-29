package com.example.easy.callback;

/**
 * @ClassName: ResponseCallback
 * @Description: java类作用描述
 * @Author: mcl
 * @Date: 2019/12/22 17:19
 */
public interface ResponseCallback<T> {
    void success(T response);

    void fail(String s);

    void error(String error);
}
