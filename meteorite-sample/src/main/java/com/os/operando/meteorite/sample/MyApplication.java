package com.os.operando.meteorite.sample;

import android.app.Application;

import com.os.operando.meteor.MeteorActivityLifecycleCallbacks;
import com.os.operando.meteor.MeteorNotification;
import com.os.operando.meteorite.MeteoriteActivity;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MeteorNotification.show(this, MeteoriteActivity.createComponentName(this));
        registerActivityLifecycleCallbacks(new MeteorActivityLifecycleCallbacks());
    }
}
