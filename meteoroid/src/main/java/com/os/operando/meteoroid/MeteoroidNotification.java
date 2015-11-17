package com.os.operando.meteoroid;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v7.app.NotificationCompat;

public class MeteoroidNotification {

    private static final String NOTIFICATION_TAG = MeteoroidNotification.class.getName();
    private static final int NOTIFICATION_ID = 100;

    public static void show(Context context, ComponentName componentName) {
        PendingIntent pendingIntent = MeteoroidReceiver.createPendingIntent(context, 0, PendingIntent.FLAG_CANCEL_CURRENT, componentName);
        Notification notification = buildNotification(context, pendingIntent);
        NotificationManager notificationManager = getNotificationManager(context);
        notificationManager.notify(NOTIFICATION_TAG, NOTIFICATION_ID, notification);
    }

    public static void cancel(Context context) {
        NotificationManager notificationManager = getNotificationManager(context);
        notificationManager.cancel(NOTIFICATION_TAG, NOTIFICATION_ID);
    }

    private static Notification buildNotification(Context context, PendingIntent pendingIntent) {
        PackageManager packageManager = context.getPackageManager();
        String applicationName = context.getApplicationInfo().loadLabel(packageManager).toString();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker("Start SlackPost");
        builder.setContentTitle(applicationName);
        builder.setContentText("タップすると表示中のスクリーンショットを撮ります");
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(false);
        return builder.build();
    }

    private static NotificationManager getNotificationManager(Context context) {
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }
}
