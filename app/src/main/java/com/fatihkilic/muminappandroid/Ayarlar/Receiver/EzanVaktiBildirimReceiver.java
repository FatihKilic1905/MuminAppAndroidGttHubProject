package com.fatihkilic.muminappandroid.Ayarlar.Receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.fatihkilic.muminappandroid.MainActivity;
import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityAyarlarBinding;

public class EzanVaktiBildirimReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {



        String title = intent.getStringExtra("NotTitle");
        String description = intent.getStringExtra("NotDescription");
        String sound = intent.getStringExtra("NotSound");
        int notifyNum = intent.getIntExtra("NotNotifNum",0);

        System.out.println("notify"  + notifyNum);

        StringBuilder SoundUrl = new StringBuilder();
        SoundUrl.append("/raw/");
        SoundUrl.append(sound);

        Uri custumSoundUri = Uri.parse("android.resource://" + intent.getComponent().getPackageName() + "/" + R.raw.rrh);
        Uri customSoundUri1 = Uri.parse(ContentResolver. SCHEME_ANDROID_RESOURCE + "://" + intent.getComponent().getPackageName() + "/raw/rrh.mp3" );
       // Uri customSoundUri = Uri.parse("android.resource://" + intentImsak.getComponent().getPackageName()+ SoundUrl);
        System.out.println("URII " + custumSoundUri);


        NotificationCompat.Builder EzanNotifyBuilder = new NotificationCompat.Builder(context, "notifyEzan")
                .setSmallIcon(R.drawable.ic_mumin_toolbar_logo)
                .setContentTitle(title)
                .setContentText(description)
                .setSound(custumSoundUri)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat ezanNotificationManager = NotificationManagerCompat.from(context);

        ezanNotificationManager.notify(notifyNum, EzanNotifyBuilder.build());



    }
}
