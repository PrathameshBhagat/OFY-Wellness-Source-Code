package com.ofywellness;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class AddMealWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.add_meal_widget);

        // 2. define intent --> action which will be performed
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("https://insideandroid.in"));

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        // 3. set pending intent on view
        remoteViews.setOnClickPendingIntent(R.id.add_meal_button_text_view, pendingIntent);

   /*     *//*Intent i = new Intent(context,LoginActivity.class);
        i.addCategory("android.intent.category.LAUNCHER");
        i.setAction("android.intent.action.MAIN");*//*
        Intent  intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("https://insideandroid.in"));



        // Construct the RemoteViews object
  RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.add_meal_widget);
        *//*views.setOnClickPendingIntent(R.id.appwidget_text,PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE));
*//*
 *//*       Intent configIntent = new Intent(context, AddMealActivity.class);

      *//**//*  configIntent.addCategory("android.intent.category.LAUNCHER");
        configIntent.setAction("android.intent.action.MAIN");
*//**//*

        configIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);*//*Intent mailClient = new Intent(Intent.ACTION_VIEW);
        mailClient.setClassName("com.google.android.gm", "com.google.android.gm.ConversationListActivity");
        context.startActivity(mailClient);

        PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, mailClient, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        views.setOnClickPendingIntent(R.id.appwidget_text, configPendingIntent);
        *//*
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        //3. set pending intent on view
        views.setOnClickPendingIntent(R.id.appwidget_text, pi);*/
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }


}

/**
 * Implementation of App Widget functionality.
 */


