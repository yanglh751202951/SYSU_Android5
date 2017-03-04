package com.example.yanglh6.myapplication4;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.RemoteViews;

public class DynamicReceiver extends BroadcastReceiver {
    public DynamicReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.example.yanglh6.myapplication4.dynamicreceiver")) {
            Bundle bundle = intent.getExtras();
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), bundle.getInt("ItemImage"));
            int imageId = bundle.getInt("ItemImage");
            RemoteViews view = new RemoteViews(context.getPackageName(),R.layout.my_app_widget);
            String widgetName = bundle.getString("name");

            view.setTextViewText(R.id.WidgetName, widgetName);
            view.setImageViewResource(R.id.WidgetImage, imageId);
            AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(context);
            appWidgetManager.updateAppWidget(new ComponentName(context, MyAppWidget.class), view);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.Builder builder = new Notification.Builder(context);
            builder.setContentTitle("动态广播")
                    .setContentText(widgetName)
                    .setLargeIcon(bitmap)
                    .setSmallIcon(imageId)
                    .setTicker("您有一条新消息")
                    .setAutoCancel(true);
            Intent mIntent = new Intent(context, MainActivity.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mIntent, 0);
            builder.setContentIntent(pendingIntent);
            Notification notify = builder.build();
            notificationManager.notify(0, notify);
        }
    }
}
