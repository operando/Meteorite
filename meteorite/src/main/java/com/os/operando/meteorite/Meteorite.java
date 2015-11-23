package com.os.operando.meteorite;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.os.operando.meteor.MeteorActivityLifecycleCallbacks;
import com.os.operando.meteor.MeteorNotification;

public class Meteorite {

    private static final String SLACK_TOKEN_META_DATA = "com.os.operando.meteorite.slackToken";

    public static void init(Application application) {
        MeteorNotification.show(application, MeteoriteActivity.createComponentName(application));
        application.registerActivityLifecycleCallbacks(new MeteorActivityLifecycleCallbacks());
    }

    @Nullable
    public static String getToken(Context context) {
        ApplicationInfo ai;
        try {
            ai = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
        if (ai == null || ai.metaData == null) {
            return null;
        }
        String token = ai.metaData.getString(SLACK_TOKEN_META_DATA);
        if (TextUtils.isEmpty(token)) {
            return null;
        }
        return token;
    }
}
