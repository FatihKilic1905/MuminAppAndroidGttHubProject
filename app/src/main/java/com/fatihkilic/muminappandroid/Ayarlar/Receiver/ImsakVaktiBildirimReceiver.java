package com.fatihkilic.muminappandroid.Ayarlar.Receiver;


import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.app.PendingIntent;
import android.net.Uri;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.fatihkilic.muminappandroid.BildirimActivity;
import com.fatihkilic.muminappandroid.MainActivity;
import com.fatihkilic.muminappandroid.R;

import java.net.URI;

public class ImsakVaktiBildirimReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intentImsak) {

        String title = intentImsak.getStringExtra("vImsakTitle");
        String description = intentImsak.getStringExtra("vImsakDescription");
        String sound = intentImsak.getStringExtra("vImsakSound");
        int notifyNum = intentImsak.getIntExtra("vImsakNotifyNum",0);

        StringBuilder SoundUrl = new StringBuilder();
        SoundUrl.append("/raw/");
        SoundUrl.append(sound);





       Uri custumSoundUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.kussesi1);
       Uri customSoundUri = Uri.parse("android.resource://" + intentImsak.getComponent().getPackageName()+ SoundUrl);
        System.out.println(custumSoundUri);


        NotificationCompat.Builder EzanNotifyBuilder = new NotificationCompat.Builder(context, "notifyEzan")
                .setSmallIcon(R.drawable.ic_mumin_toolbar_logo)
                .setContentTitle(title)
                .setContentText(description)
                .setSound(custumSoundUri)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        Intent intent = new Intent(context, BildirimActivity.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,notifyNum,intent,0);
        EzanNotifyBuilder.setContentIntent(pendingIntent);


        NotificationManagerCompat ezanNotificationManager = NotificationManagerCompat.from(context);

        ezanNotificationManager.notify(notifyNum, EzanNotifyBuilder.build());

    }
}
