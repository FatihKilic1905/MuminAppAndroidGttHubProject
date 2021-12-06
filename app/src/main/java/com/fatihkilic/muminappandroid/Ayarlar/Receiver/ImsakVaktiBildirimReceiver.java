package com.fatihkilic.muminappandroid.Ayarlar.Receiver;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.app.PendingIntent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;

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
        String vaktinAyetiStr = intentImsak.getStringExtra("vaktinAyeti");
        String vaktinHadisiStr = intentImsak.getStringExtra("vaktinHadisi");

        StringBuilder SoundUrl = new StringBuilder();
        SoundUrl.append("/raw/");
        SoundUrl.append(sound);




        Uri customSoundUri = Uri.parse(ContentResolver. SCHEME_ANDROID_RESOURCE + "://" + context.getPackageName() + SoundUrl);

        NotificationManager ezanVaktinotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build();

            CharSequence name = "Ezan Vakitleri Kanalı";
            String descriptions = "Ezan Vakitleri için hatırlatma";
            int importance = NotificationManager.IMPORTANCE_HIGH;


            NotificationChannel ezanChannel = new NotificationChannel("notifyEzan", name, importance);
            ezanChannel.setDescription(descriptions);
            ezanChannel.setSound(customSoundUri, audioAttributes);


            ezanVaktinotificationManager.createNotificationChannel(ezanChannel);

        }

        NotificationCompat.Builder EzanNotifyBuilder = new NotificationCompat.Builder(context, "notifyEzan")
                .setSmallIcon(R.drawable.ic_mumin_toolbar_logo)
                .setContentTitle(title)
                .setContentText(description)
                .setSound(customSoundUri)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setColor(Color.RED);

        Intent intent = new Intent(context, BildirimActivity.class);
        intent.putExtra("InfoNot",title);
        intent.putExtra("DescNot", description);
        intent.putExtra("VANot",vaktinAyetiStr);
        intent.putExtra("VHNot",vaktinHadisiStr);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,notifyNum,intent,0);

        EzanNotifyBuilder.setContentIntent(pendingIntent);

        ezanVaktinotificationManager.notify(notifyNum, EzanNotifyBuilder.build());

    }
}
