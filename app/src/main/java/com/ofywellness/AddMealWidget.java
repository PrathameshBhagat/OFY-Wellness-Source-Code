package com.ofywellness;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class AddMealWidget extends AppWidgetProvider {
    @Override
    public void onReceive(Context context, Intent intent) {
        startActivity(context,new Intent(context, AddMealActivity.class), Bundle.EMPTY);

    }
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.add_meal_widget);

        // 2. define intent --> action which will be performed
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("https://insideandroid.in"));

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,new Intent(context, AddMealActivity.class), PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // 3. set pending intent on view
        remoteViews.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);

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

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}