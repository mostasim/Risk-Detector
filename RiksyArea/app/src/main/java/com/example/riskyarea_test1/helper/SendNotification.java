package com.example.riskyarea_test1.helper;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.riskyarea_test1.R;

import java.util.concurrent.atomic.AtomicInteger;


public class SendNotification extends AsyncTask<String, String, String> {
    private static final String CHANNEL_ID = "CHANNEL_ID";
    private static final String TAG = "main";

    private Context ctx;
    private AtomicInteger notificationId = new AtomicInteger(0);

    public SendNotification(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        for (String s : params) {
            Log.e(TAG, s);

            publishProgress(s);

            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
            }
        }
        return "Executed";
    }

    @Override
    protected void onProgressUpdate(String... values) {
        for (String title: values) {
            sendNotification(title, notificationId.incrementAndGet());
        }
    }

    void sendNotification(String title, int notificationId) {
        String message = "You are at Risky Area";
        if (title.equals("Crime Ara"))
            message = "You are at most Criminal Area! Be Careful";
        else if (title.equals("Accidental Area"))
            message = "You are at most Accidental Area! Be Careful";
        else if (title.equals("Over-Bridge"))
            message = "You are near to a over bridge! Use Over-Bridge";


        NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx, CHANNEL_ID)
                .setSmallIcon(R.drawable.spider)
                .setContentTitle(String.format("%s ", title))
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
                // Add the action button

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(ctx);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId, builder.build());
    }
}
