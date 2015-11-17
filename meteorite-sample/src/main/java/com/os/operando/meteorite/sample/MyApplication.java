package com.os.operando.meteorite.sample;

import android.app.Application;

import com.os.operando.meteorite.MeteoriteActivity;
import com.os.operando.meteoroid.MeteoroidActivityLifecycleCallbacks;
import com.os.operando.meteoroid.MeteoroidNotification;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MeteoroidNotification.show(this, MeteoriteActivity.createComponentName(this));
        registerActivityLifecycleCallbacks(new MeteoroidActivityLifecycleCallbacks());
    }
}
