package com.example.yanglh6.myapplication4;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;


/**
 * Implementation of App Widget functionality.
 */
public class MyAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_app_widget);
        views.setTextViewText(R.id.WidgetName, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Intent clickInt = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, clickInt, 0);
        RemoteViews view = new RemoteViews(context.getPackageName(),R.layout.my_app_widget);
        view.setOnClickPendingIntent(R.id.WidgetImage, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, view);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("debug", intent.toString());
        super.onReceive(context, intent);
        RemoteViews view = new RemoteViews(context.getPackageName(),R.layout.my_app_widget);
        Bundle bundle = intent.getExtras();
        String widgetName = bundle.getString("name");
        int widgetImage = bundle.getInt("ItemImage");
        if (intent.getAction().equals("com.example.yanglh6.myapplication4.staticreceiver")) {
            view.setTextViewText(R.id.WidgetName, widgetName);
            view.setImageViewResource(R.id.WidgetImage, widgetImage);
            AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(context);
            appWidgetManager.updateAppWidget(new ComponentName(context, MyAppWidget.class), view);

            Bitmap bitmap= BitmapFactory.decodeResource(context.getResources(),bundle.getInt("ItemImage"));
            int imageId = (int) bundle.get("ItemImage");
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.Builder builder = new Notification.Builder(context);
            builder.setContentTitle("静态广播")
                    .setContentText(bundle.getString("name"))
                    .setLargeIcon(bitmap)
                    .setSmallIcon(imageId)
                    .setTicker("您有一条新消息")
                    .setAutoCancel(true);
            Intent Intent1 = new Intent(context, MainActivity.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, Intent1, 0);
            builder.setContentIntent(pendingIntent);
            Notification notify = builder.build();
            notificationManager.notify(0, notify);
        }
    }
}

