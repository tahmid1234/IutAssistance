package com.example.iutassistant;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public abstract class OldNotiFication {

    public static final String CHANNEL_ID = "notsch";

    public static void createNotificationChannel(Context activity) {



        // Create the NotificationChannel, but only on API 26+ because
        //the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name = (CharSequence)"abcd";//(Integer.parseInt(a));
            String description = (String)"popo";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = activity.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

            // Log.d(Constants.NOTIFICATION_LOG, "createNotificationChannel: notification channel created");
        }
    }

    public static void showNotification(Context currentActivity, Intent intent, int notification_id, String title, String content){

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //check
        PendingIntent pendingIntent = PendingIntent.getActivity(currentActivity, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = buildNotification(currentActivity, title, content, pendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(currentActivity);

        // notificationId is a unique int for each notification that you must define
      
        notificationManager.notify(notification_id, builder.build());


        //   Log.d(Constants.NOTIFICATION_LOG, "showNotification: notification showed");intent


    }

    private static NotificationCompat.Builder buildNotification(Context activity, String title, String content, PendingIntent pendingIntent){
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(activity, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.ic_announcement)
                .setSound(defaultSoundUri)
                .setLargeIcon(BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_announcement))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        // Log.d(Constants.NOTIFICATION_LOG, "buildNotification: notification builder created");

        return builder;

    }


}
