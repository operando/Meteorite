package com.os.operando.meteoroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;

public class Meteoroid {

    public static final String Screenshot_key = "screenshot_key";

    @Nullable
    public static Bitmap getScreenshotBitmap(Intent intent) {
        String key = intent.getStringExtra(Screenshot_key);
        if (key == null) {
            return null;
        }
        return ScreenshotBitmapCache.getBitmap(key);
    }
}
