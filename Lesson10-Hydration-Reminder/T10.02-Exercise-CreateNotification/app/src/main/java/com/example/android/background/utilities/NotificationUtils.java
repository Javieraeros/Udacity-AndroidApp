package com.example.android.background.utilities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.example.android.background.MainActivity;
import com.example.android.background.R;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.app.PendingIntent.getActivity;

/**
 * Utility class for creating hydration notifications
 */
public class NotificationUtils {
    private static final int PENDING_ITEM_ID =240209;
    private static final int NOTIFICATION_MANAGER_ID=191192;
    public void remindUserBecauseCharging(Context context){
        Resources resources=context.getResources();
        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(context)
                .setColor(ContextCompat.getColor(context,R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_drink_notification)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(resources.getString(R.string.charging_reminder_notification_title))
                .setContentText(resources.getString(R.string.charging_reminder_notification_body))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(resources.getString(R.string.charging_reminder_notification_body)))
                .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .setAutoCancel(true);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        Notification notification=notificationBuilder.build();
        NotificationManager notificationManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_MANAGER_ID,notification);

    }

    public PendingIntent contentIntent(Context context){
        Intent mainIntent=new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = getActivity(context, PENDING_ITEM_ID,mainIntent,FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }
    public Bitmap largeIcon(Context context){
        Resources resources=context.getResources();
        return BitmapFactory.decodeResource(resources, R.drawable.ic_local_drink_black_24px);

    }

}
