package com.os.operando.meteoroid;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Window;

public class MeteoroidReceiver extends BroadcastReceiver {

    private static final String component_name = "component_name";

    public static Intent createIntent(Context context) {
        return new Intent(context, MeteoroidReceiver.class);
    }

    public static PendingIntent createPendingIntent(Context context, int requestCode, int flags, @NonNull ComponentName componentName) {
        Intent intent = createIntent(context);
        intent.putExtra(component_name, componentName);
        return PendingIntent.getBroadcast(context, requestCode, intent, flags);
    }

    public MeteoroidReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(Tag.Slack, "onReceive");
        Window w = WindowStack.peek();
        if (w == null) {
            // TODO Error callback
            return;
        }
        Bitmap b = ViewUtils.getDecorViewBitmap(w);
        String uuid = ScreenshotBitmapCache.putBitmap(b);
        ComponentName componentName = intent.getParcelableExtra(component_name);
        Intent i = new Intent();
        i.putExtra(Meteoroid.Screenshot_key, uuid);
        i.setComponent(componentName);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
