package com.fatihkilic.muminappandroid.Ayarlar;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.fatihkilic.muminappandroid.R;

public class EzanVaktiBildirimReceiver extends BroadcastReceiver {

    SharedPreferences sharedPreferences;


    @Override
    public void onReceive(Context context, Intent intent) {


        sharedPreferences = context.getSharedPreferences("com.fatihkilic.muminappandroid", Context.MODE_PRIVATE);

        String titles = sharedPreferences.getString("VakitBildirimTitle", "dddd");




        NotificationCompat.Builder EzanNotifyBuilder = new NotificationCompat.Builder(context, "notifyEzan")
                .setSmallIcon(R.drawable.ic_mumin_toolbar_logo)
                .setContentTitle(titles)
                .setContentText("descriptions")
                .setSound(Uri.parse(""))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat ezanNotificationManager = NotificationManagerCompat.from(context);

        ezanNotificationManager.notify(1, EzanNotifyBuilder.build());



    }
}
