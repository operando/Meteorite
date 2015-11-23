package com.os.operando.meteorite.sample;

import android.app.Application;

import com.os.operando.meteorite.Meteorite;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Meteorite.init(this);
    }
}
