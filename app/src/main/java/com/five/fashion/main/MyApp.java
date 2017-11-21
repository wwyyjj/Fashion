package com.five.fashion.main;

import android.app.Application;
import android.content.Context;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by wangyajie on 2017/11/9.
 */

public class MyApp extends Application {
    private static Context context;
    public static MyApp mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
        context = getApplicationContext();
        mInstance = this;
    }
    public static MyApp getInstance() {
        return mInstance;
    }
    public static Context getContext() {
        return context;
    }
}
