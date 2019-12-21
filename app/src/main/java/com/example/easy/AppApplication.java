package com.example.easy;

import android.app.Application;
import android.content.Context;

public class AppApplication extends Application {
    public static Application getInstance;
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        getInstance = this;
        context = this;
    }
}
