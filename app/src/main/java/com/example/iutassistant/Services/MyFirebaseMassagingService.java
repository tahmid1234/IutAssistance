package com.example.iutassistant.Services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.iutassistant.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Objects;
import java.util.Random;

public class MyFirebaseMassagingService extends FirebaseMessagingService{
        @Override
public void onNewToken(@NonNull String s) {
    System.out.println( "Refreshed token: " + s);
}

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onMessageReceived(RemoteMessage remoteMessage) {
            super.onMessageReceived(remoteMessage);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int notificationId= new Random().nextInt(3000);

            System.out.println("KI khobor friends");
          /*  NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"admin_channel")
                    .setContentTitle(Objects.requireNonNull(remoteMessage.getData().get("title"))//getNotification()).getTitle())
                    .setContentText(remoteMessage.getNotification().getBody())
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.assignment_icon)
                    .setAutoCancel(true);*/
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"admin_channel")
                    .setSmallIcon(R.drawable.assignment_icon)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentTitle(remoteMessage.getData().get("title"))
                    .setContentText(remoteMessage.getData().get("message"))
                    .setAutoCancel(true);

            System.out.println("KI khobor friends");


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String channelId = "1";
                NotificationChannel channel = new NotificationChannel(
                        channelId,
                        "for daily",
                        NotificationManager.IMPORTANCE_HIGH);
                assert notificationManager != null;
                notificationManager.createNotificationChannel(channel);
                builder.setChannelId(channelId);
            }

            if (notificationManager != null) {
                notificationManager.notify(notificationId, builder.build());
            }
        }

}
