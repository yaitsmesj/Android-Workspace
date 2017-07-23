package com.example.suraj.firebaseexample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Suraj on 7/16/2017.
 */

public class MyMessagingService extends FirebaseMessagingService {

    public static final String TAG = "FCM";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "onMessageReceived: "+ remoteMessage.getMessageId());
        Log.d(TAG, "onMessageReceived: "+ remoteMessage.getMessageType());
        Log.d(TAG, "onMessageReceived: "+ remoteMessage.getData());
        Log.d(TAG, "onMessageReceived: "+ remoteMessage.getNotification().getTitle());
        Log.d(TAG, "onMessageReceived: "+ remoteMessage.getNotification().getBody());

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("FROM_NOTIFICATION",true);

        PendingIntent pi = PendingIntent.getActivity(this,111,intent,PendingIntent.FLAG_ONE_SHOT);

        Notification.Action openActivityAction = new Notification.Action(
                R.mipmap.ic_launcher,
                "Open",
                pi
        );


        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification.Builder notificationBuilder = new Notification.Builder(this)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(remoteMessage.getNotification().getBody());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            notificationBuilder.addAction(openActivityAction);
        }

        Notification myNotification = notificationBuilder.build();

        nm.notify(321,myNotification);
    }
}
