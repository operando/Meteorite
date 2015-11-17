package com.os.operando.meteorite;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.squareup.okhttp.Callback;

import java.io.File;
import java.io.IOException;

public class Meteorite {

    private static final String SLACK_TOKEN_META_DATA = "com.os.operando.meteorite.slackToken";

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

    public static void post(Context context, Bitmap bitmap, Callback callback) throws IOException {
        ApplicationInfo ai;
        try {
            ai = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            return;
        }
        if (ai == null || ai.metaData == null) {
            return;
        }
        String token = ai.metaData.getString("slack_token");
        if (TextUtils.isEmpty(token)) {
            return;
        }
        post(context, token, bitmap, callback);
    }

    public static void post(Context context, String token, Bitmap bitmap, Callback callback) throws IOException {
        File directory = IOUtils.getCacheDirectory(context);
        String cacheDirectoryPath = directory.getAbsolutePath();
        File bitmapFile = IOUtils.newUniqueTempFile(cacheDirectoryPath, ".jpg");
        IOUtils.saveBitmap(bitmap, bitmapFile);
    }
}
