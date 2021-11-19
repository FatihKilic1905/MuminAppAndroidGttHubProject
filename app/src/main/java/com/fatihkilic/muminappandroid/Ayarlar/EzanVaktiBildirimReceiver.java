package com.fatihkilic.muminappandroid.Ayarlar;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.fatihkilic.muminappandroid.MainActivity;
import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityAyarlarBinding;

public class EzanVaktiBildirimReceiver extends BroadcastReceiver {

    SharedPreferences sharedPreferences;








    @Override
    public void onReceive(Context context, Intent intent) {

        String title = intent.getStringExtra("NotTitle");
        String description = intent.getStringExtra("NotDescription");
        String sound = intent.getStringExtra("NotSound");
        int notifyNum = intent.getIntExtra("NotNotifNum",0);

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
