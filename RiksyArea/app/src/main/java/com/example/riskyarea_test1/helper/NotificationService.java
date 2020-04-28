package com.example.riskyarea_test1.helper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.riskyarea_test1.R;
import com.example.riskyarea_test1.database.PreferenceUtil;
import com.example.riskyarea_test1.ui.activity.HomeActivity;

import static com.example.riskyarea_test1.MyApp.CHANNEL_ID;

public class NotificationService extends Service implements NearbyListener {


    @Override
    public void onCreate() {
        super.onCreate();
    }


    public void fireNotification(String title, String message) {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(this, HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .build();
        mNotificationManager.notify(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Intent notificationIntent = new Intent(this, HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);


        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Detecting person ...")
                .setContentText("")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .build();


        NearbyHelper nearbyHelper = new NearbyHelper(this);
        nearbyHelper.initBluetoothOnly();
        nearbyHelper.setNearbyListener(this);


        new Thread(() -> {
            nearbyHelper.publishMessage(new PreferenceUtil(this).getRiskPoint());
        }).start();


        startForeground(1, notification);

        //do heavy work on a background thread
        //stopSelf();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void getMessageByRiskValue(int value, boolean b) {

        String title = b ? "User Discovered Near You" : "User Lost Near You";
        String message = "";
        if (value <= 3) {

        }
        if (value <= 6 && value >= 4) {
            message = b ? "Moderate infected person found " : "Now you are safe";
            fireNotification(title, message);
        }
        if (value >= 7) {
            message = b ? "Highly infected person found" : "Now you are safe";
            fireNotification(title, message);
        }

    }

    @Override
    public void onUserDiscovered(int id) {
        getMessageByRiskValue(id, true);
    }

    @Override
    public void onUserLost(int id) {
        getMessageByRiskValue(id, false);
    }
}
