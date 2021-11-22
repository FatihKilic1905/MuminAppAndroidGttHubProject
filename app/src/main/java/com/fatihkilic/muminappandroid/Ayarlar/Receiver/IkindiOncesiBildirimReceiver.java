package com.fatihkilic.muminappandroid.Ayarlar.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.fatihkilic.muminappandroid.R;

public class IkindiOncesiBildirimReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intentIkindiOncesi) {

        String title = intentIkindiOncesi.getStringExtra("IkindiOncesiNotTitle");
        String description = intentIkindiOncesi.getStringExtra("IkindiOncesiNotDescription");
        String sound = intentIkindiOncesi.getStringExtra("IkindiOncesiNotSound");
        int notifyNum = intentIkindiOncesi.getIntExtra("IkindiOncesiNotNotifyNum",0);


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
