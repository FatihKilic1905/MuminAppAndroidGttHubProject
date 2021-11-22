package com.fatihkilic.muminappandroid.Ayarlar.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.fatihkilic.muminappandroid.R;

public class ImsakOncesiBildirimReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intentImsakOncesi) {

        String title = intentImsakOncesi.getStringExtra("ImsakOncesiNotTitle");
        String description = intentImsakOncesi.getStringExtra("ImsakOncesiNotDescription");
        String sound = intentImsakOncesi.getStringExtra("ImsakOncesiNotSound");
        int notifyNum = intentImsakOncesi.getIntExtra("ImsakOncesiNotNotifyNum",0);


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
