package com.fatihkilic.muminappandroid.Ayarlar.Receiver;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.fatihkilic.muminappandroid.MainActivity;
import com.fatihkilic.muminappandroid.R;

public class IkindiOncesiBildirimReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intentIkindiOncesi) {

        String title = intentIkindiOncesi.getStringExtra("vOIkindiTitle");
        String description = intentIkindiOncesi.getStringExtra("vOIkindiDescription");
        String sound = intentIkindiOncesi.getStringExtra("vOIkindiSound");
        int notifyNum = intentIkindiOncesi.getIntExtra("vOIkindiNotifyNum",0);


        StringBuilder SoundUrl = new StringBuilder();
        SoundUrl.append("/raw/");
        SoundUrl.append(sound);


        Uri customSoundUri = Uri.parse(ContentResolver. SCHEME_ANDROID_RESOURCE + "://" + context.getPackageName() + SoundUrl);



        NotificationCompat.Builder EzanNotifyBuilder = new NotificationCompat.Builder(context, "notifyEzan")
                .setSmallIcon(R.drawable.ic_mumin_toolbar_logo)
                .setContentTitle(title)
                .setContentText(description)
                .setSound(customSoundUri)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,notifyNum,intent,0);
        EzanNotifyBuilder.setContentIntent(pendingIntent);

        NotificationManagerCompat ezanNotificationManager = NotificationManagerCompat.from(context);

        ezanNotificationManager.notify(notifyNum, EzanNotifyBuilder.build());

    }
}
