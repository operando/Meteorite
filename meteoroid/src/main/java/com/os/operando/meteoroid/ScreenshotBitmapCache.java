package com.os.operando.meteoroid;

import android.graphics.Bitmap;
import android.util.LruCache;

import java.util.UUID;

public class ScreenshotBitmapCache {

    private static LruCache<String, Bitmap> sMemoryCache;

    static {
        int cacheSize = 4 * 1024 * 1024;
        sMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public static String putBitmap(Bitmap bitmap) {
        String uuid = UUID.randomUUID().toString();
        sMemoryCache.put(uuid, bitmap);
        return uuid;
    }

    public static Bitmap getBitmap(String key) {
        return sMemoryCache.get(key);
    }

    public static Bitmap removeBitmap(String key) {
        return sMemoryCache.remove(key);
    }
}
