package com.os.operando.meteoroid;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;

public class ViewUtils {

    @Nullable
    public static Bitmap getDecorViewBitmap(Window window) {
        View decorView = window.getDecorView();
        return getViewBitmap(decorView);
    }

    private static Bitmap getViewBitmap(View view) {
        try {
            view.setDrawingCacheEnabled(true);
            Bitmap cache = view.getDrawingCache();
            if (cache == null) {
                return null;
            }
            Bitmap bitmap = Bitmap.createBitmap(cache);
            return bitmap;
        } finally {
            view.setDrawingCacheEnabled(false);
        }
    }
}
