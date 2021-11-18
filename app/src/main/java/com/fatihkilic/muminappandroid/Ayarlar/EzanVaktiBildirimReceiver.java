package com.fatihkilic.muminappandroid.Ayarlar;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.fatihkilic.muminappandroid.R;

public class EzanVaktiBildirimReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder EzanNotifyBuilder = new NotificationCompat.Builder(context, "notifyEzan")
                .setSmallIcon(R.drawable.ic_mumin_toolbar_logo)
                .setContentTitle("Vatna Millet Sakarya")
                .setContentText("Olacak Ins")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat ezanNotificationManager = NotificationManagerCompat.from(context);

        ezanNotificationManager.notify(1, EzanNotifyBuilder.build());



    }
}
