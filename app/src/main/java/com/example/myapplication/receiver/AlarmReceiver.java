package com.example.myapplication.receiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.TaskStackBuilder;

import com.example.myapplication.Activity.AlarmActivity;
import com.example.myapplication.Activity.DailyWordActivity;
import com.example.myapplication.R;

import static android.app.NotificationManager.IMPORTANCE_DEFAULT;

public class AlarmReceiver extends BroadcastReceiver {
    private String TAG = "RECEIVER -- ALARMRECEIVER";
    private static final String CHANNEL_ID = "com.singhajit.notificationDemo.channelId";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notificationIntent = new Intent(context, AlarmActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(AlarmActivity.class);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(context);
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification = builder.setContentTitle("Từ vựng tiếng anh")
                    .setContentText("Đã đến giờ học tiếng Anh của bạn!")
                    .setTicker("New Message Alert!")
                    .setSmallIcon(R.drawable.alarm_icon)
                    .setContentIntent(pendingIntent).build();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(CHANNEL_ID);
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Notification",
                    IMPORTANCE_DEFAULT
            );
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }
        assert notificationManager != null;
        notificationManager.notify(0, notification);
        String batTat = intent.getStringExtra("BatTat");
        Intent myIntent = new Intent(context, DailyWordActivity.class);
        myIntent.putExtra("BatTat", batTat);
        context.startService(myIntent);
    }
}
