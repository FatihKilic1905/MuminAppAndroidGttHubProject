package com.fatihkilic.muminappandroid.Ayarlar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.fatihkilic.muminappandroid.R;

public class ImsakVaktiBildirimReceiver extends BroadcastReceiver {

    SharedPreferences sharedPreferences;


    @Override
    public void onReceive(Context context, Intent intent1) {

        String title = intent1.getStringExtra("NotTitle1");
        String description = intent1.getStringExtra("NotDescription1");
        String sound = intent1.getStringExtra("NotSound1");
        int notifyNum = intent1.getIntExtra("NotNotifNum1",0);

        System.out.println("notify"  + notifyNum);





        NotificationCompat.Builder EzanNotifyBuilder = new NotificationCompat.Builder(context, "notifyEzan")
                .setSmallIcon(R.drawable.ic_mumin_toolbar_logo)
                .setContentTitle(title)
                .setContentText(description)
                .setSound(Uri.parse(sound))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat ezanNotificationManager = NotificationManagerCompat.from(context);

        ezanNotificationManager.notify(notifyNum, EzanNotifyBuilder.build());



    }
}
