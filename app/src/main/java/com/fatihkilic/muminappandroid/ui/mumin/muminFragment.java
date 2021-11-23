package com.fatihkilic.muminappandroid.ui.mumin;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.fatihkilic.muminappandroid.Ayarlar.Receiver.EzanVaktiBildirimReceiver;
import com.fatihkilic.muminappandroid.Ayarlar.Receiver.ImsakVaktiBildirimReceiver;
import com.fatihkilic.muminappandroid.MainActivity;
import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.FragmentMuminBinding;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class muminFragment extends Fragment {

    private FragmentMuminBinding binding;
    SQLiteDatabase vakitDatabase;

    String imsakVakti;
    String gunesVakti;
    String ogleVakti;
    String ikindiVakti;
    String aksamVakti;
    String yatsiVakti;

    Integer vOImsakSureInt;
    String vOImsakSesStr;
    String vImsakSesStr;

    Integer vOGunesSureInt;
    String vOGunesSesStr;
    String vGunesSesStr;

    Integer vOOgleSureInt;
    String vOOgleSesStr;
    String vOgleSesStr;

    Integer vOIkindiSureInt;
    String vOIkindiSesStr;
    String vIkindiSesStr;

    Integer vOAksamSureInt;
    String vOAksamSesStr;
    String vAksamSesStr;

    Integer vOYatsiSureInt;
    String vOYatsiSesStr;
    String vYatsiSesStr;

    String sistemTarihiStr;
    String sistemSaatiStr;

    Integer vakitFarkSaat;
    Integer vakitFarkDakika;
    Integer vakitFarkSaniye;

    String vaktinCikmasinaString;

    NotificationManager notificationManager;

    SharedPreferences sharedPreferences;

    EzanVaktiBildirimReceiver EzanVaktiReceivers;

    String vaktinAyetiStr;
    String vaktinHadisiStr;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMuminBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedPreferences = requireActivity().getSharedPreferences("com.fatihkilic.muminappandroid", Context.MODE_PRIVATE);

        notificationManager = (NotificationManager) requireActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        vakitDatabase = requireActivity().openOrCreateDatabase("EZANVAKITLERIDATA", Context.MODE_PRIVATE,null);

        sistemTarihiVoid();
        sistemSaatiVoid();
        getBildirimSound();



        Button notBut = binding.buttonnotification;
        notBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    bildirimGonderImsak("Şükürler",vImsakSesStr,1,"2021-11-23 18:11:00");
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //imsakbildirimgonder("Sana","Olsun","",2, 0);

            }
        });


        return root;



    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



    public void getEzanVakti() {


        try {



            Cursor cursor = vakitDatabase.rawQuery("SELECT * FROM ezanvakitleridatabase WHERE miladiKisa = ?", new String[] {sistemTarihiStr});
            int imsakVaktiIx = cursor.getColumnIndex("imsakVakti");
            int gunesVaktiIx = cursor.getColumnIndex("gunesVakti");
            int ogleVaktiIx = cursor.getColumnIndex("ogleVakti");
            int ikindiVaktiIx = cursor.getColumnIndex("ikindiVakti");
            int aksamVaktiIx = cursor.getColumnIndex("aksamVakti");
            int yatsiVaktiIx = cursor.getColumnIndex("yatsiVakti");
            int hicriUzunIx = cursor.getColumnIndex("hicriUzun");
            int miladiUzunIx = cursor.getColumnIndex("miladiUzun");
            int miladiKisaIx = cursor.getColumnIndex("miladiKisa");


            while (cursor.moveToNext()){

                imsakVakti = cursor.getString(imsakVaktiIx);
                gunesVakti = cursor.getString(gunesVaktiIx);
                ogleVakti = cursor.getString(ogleVaktiIx);
                ikindiVakti = cursor.getString(ikindiVaktiIx);
                aksamVakti = cursor.getString(aksamVaktiIx);
                yatsiVakti = cursor.getString(yatsiVaktiIx);

                binding.imsakTime.setText(cursor.getString(imsakVaktiIx));
                binding.gunesTime.setText(cursor.getString(gunesVaktiIx));
                binding.ogleTime.setText(cursor.getString(ogleVaktiIx));
                binding.ikindiTime.setText(cursor.getString(ikindiVaktiIx));
                binding.aksamTime.setText(cursor.getString(aksamVaktiIx));
                binding.yatsiTime.setText(cursor.getString(yatsiVaktiIx));
                binding.hicrititle.setText(cursor.getString(hicriUzunIx));

                String miladiStr = cursor.getString(miladiUzunIx);
                String[] miladiSplit = miladiStr.split("\\s");

                binding.guntitle.setText(miladiSplit[0]);
                binding.aytitle.setText(miladiSplit[1]);
                binding.gunlertitle.setText(miladiSplit[3]);

            }

            StringBuilder timeToday = new StringBuilder();
            timeToday.append(miladiKisaIx);
            

            cursor.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        vakitGeldi();


    }

    public void vakitGeldi() {


        // stringi Integera cevirirken try and catch gerekebilir

        String[] splitSistemSaati = sistemSaatiStr.split(":");

            Integer sistemSaatInt = Integer.parseInt(splitSistemSaati[0]);
            Integer sistemDakikaInt = Integer.parseInt(splitSistemSaati[1]);
            Integer sistemSaniyeInt = Integer.parseInt(splitSistemSaati[2]);


            //Imsak

        StringBuilder ekleImsak = new StringBuilder();
        ekleImsak.append(imsakVakti);
        ekleImsak.append(":00");
        String imsakNew = ekleImsak.toString();

        String[] splitImsak = imsakNew.split(":");

        Integer imsakSaatInt = Integer.parseInt(splitImsak[0]);
        Integer imsakDakikaInt = Integer.parseInt(splitImsak[1]);
        Integer imsakSaniyeInt = Integer.parseInt(splitImsak[2]);


        //Gunes

        StringBuilder ekleGunes = new StringBuilder();
        ekleGunes.append(gunesVakti);
        ekleGunes.append(":00");
        String gunesNew = ekleImsak.toString();

        String[] splitGunes = gunesNew.split(":");

        Integer gunesSaatInt = Integer.parseInt(splitGunes[0]);
        Integer gunesDakikaInt = Integer.parseInt(splitGunes[1]);
        Integer gunesSaniyeInt = Integer.parseInt(splitGunes[2]);

        //ogle

        StringBuilder ekleOgle = new StringBuilder();
        ekleOgle.append(ogleVakti);
        ekleOgle.append(":00");
        String ogleNew = ekleOgle.toString();

        String[] splitOgle = ogleNew.split(":");

        Integer ogleSaatInt = Integer.parseInt(splitOgle[0]);
        Integer ogleDakikaInt = Integer.parseInt(splitOgle[1]);
        Integer ogleSaniyeInt = Integer.parseInt(splitOgle[2]);

        //ikindi

        StringBuilder ekleIkindi = new StringBuilder();
        ekleIkindi.append(ikindiVakti);
        ekleIkindi.append(":00");
        String ikindiNew = ekleIkindi.toString();

        String[] splitIkindi = ikindiNew.split(":");

        Integer ikindiSaatInt = Integer.parseInt(splitIkindi[0]);
        Integer ikindiDakikaInt = Integer.parseInt(splitIkindi[1]);
        Integer ikindiSaniyeInt = Integer.parseInt(splitIkindi[2]);


        //aksam

        StringBuilder ekleaksam = new StringBuilder();
        ekleaksam.append(aksamVakti);
        ekleaksam.append(":00");
        String aksamNew = ekleaksam.toString();

        String[] splitAksam = aksamNew.split(":");

        Integer aksamSaatInt = Integer.parseInt(splitAksam[0]);
        Integer aksamDakikaInt = Integer.parseInt(splitAksam[1]);
        Integer aksamSaniyeInt = Integer.parseInt(splitAksam[2]);


        //yatsi

        StringBuilder ekleYatsi = new StringBuilder();
        ekleYatsi.append(yatsiVakti);
        ekleYatsi.append(":00");
        String yatsiNew = ekleYatsi.toString();

        String[] splitYatsi = yatsiNew.split(":");

        Integer yatsiSaatInt = Integer.parseInt(splitYatsi[0]);
        Integer yatsiDakikaInt = Integer.parseInt(splitYatsi[1]);
        Integer yatsiSaniyeInt = Integer.parseInt(splitYatsi[2]);


        String geceYarisi = "23:59:59";

        String[] splitgeceYarisi = geceYarisi.split(":");

        Integer GeceYarisiSaatInt = Integer.parseInt(splitYatsi[0]);
        Integer GeceYarisiDakikaInt = Integer.parseInt(splitYatsi[1]);
        Integer GeceYarisiSaniyeInt = Integer.parseInt(splitYatsi[2]);


        if (sistemSaatInt > imsakSaatInt && sistemSaatInt < gunesSaatInt || sistemSaatInt == imsakSaatInt && imsakDakikaInt < sistemDakikaInt || sistemSaatInt == gunesSaatInt && sistemDakikaInt < gunesDakikaInt) {

            SaatFarkıHesap(sistemSaatiStr, gunesVakti);

            binding.imsakTitle.setTextColor(Color.RED);
            binding.imsakTime.setTextColor(Color.RED);

        } else if (sistemSaatInt > gunesSaatInt && sistemSaatInt < ogleSaatInt || sistemSaatInt == gunesSaatInt && gunesDakikaInt < sistemDakikaInt || sistemSaatInt == ogleSaatInt && sistemDakikaInt < ogleDakikaInt) {

            SaatFarkıHesap(sistemSaatiStr, ogleVakti);

            binding.gunesTitle.setTextColor(Color.RED);
            binding.gunesTime.setTextColor(Color.RED);

        } else if (sistemSaatInt > ogleSaatInt && sistemSaatInt < ikindiSaatInt || sistemSaatInt == ogleSaatInt && ogleDakikaInt < sistemDakikaInt || sistemSaatInt == ikindiSaatInt && sistemDakikaInt < ikindiDakikaInt) {

            SaatFarkıHesap(sistemSaatiStr, ikindiVakti);

            binding.ogleTitle.setTextColor(Color.RED);
            binding.ogleTime.setTextColor(Color.RED);


        } else if (sistemSaatInt > ikindiSaatInt && sistemSaatInt < aksamSaatInt || sistemSaatInt == ikindiSaatInt && ikindiDakikaInt < sistemDakikaInt || sistemSaatInt == aksamSaatInt && sistemDakikaInt < aksamDakikaInt) {

            SaatFarkıHesap(sistemSaatiStr, aksamVakti);

            binding.ikindiTime.setTextColor(Color.RED);
            binding.aikindiTitle.setTextColor(Color.RED);

        } else if (sistemSaatInt > aksamSaatInt && sistemSaatInt < yatsiSaatInt || sistemSaatInt == aksamSaatInt && aksamDakikaInt < sistemDakikaInt || sistemSaatInt == yatsiSaatInt && sistemDakikaInt < yatsiDakikaInt) {

            SaatFarkıHesap(sistemSaatiStr, yatsiVakti);

            binding.aksamTitle.setTextColor(Color.RED);
            binding.aksamTime.setTextColor(Color.RED);


        } else if (sistemSaatInt > yatsiSaatInt && sistemSaatInt < GeceYarisiSaatInt ||  sistemSaatInt < imsakSaatInt || sistemSaatInt == yatsiSaatInt && yatsiDakikaInt < sistemDakikaInt || sistemSaatInt == imsakSaatInt && sistemDakikaInt < imsakDakikaInt || sistemSaatInt == GeceYarisiSaatInt && sistemDakikaInt < GeceYarisiDakikaInt) {

            SaatFarkıHesap(sistemSaatiStr, imsakVakti);

            binding.yatsiTime.setTextColor(Color.RED);
            binding.yatsiTitle.setTextColor(Color.RED);

        }


        if (gunesSaatInt - sistemSaatInt == 1 && gunesDakikaInt < 45 && sistemDakikaInt - gunesDakikaInt >= 15 || gunesSaatInt == sistemSaatInt && gunesDakikaInt >= 45 && gunesDakikaInt - sistemDakikaInt <= 45) {

            binding.vaktincikmasinatitle.setTextColor(Color.RED);
            binding.vaktincikmasinaTimerTitle.setTextColor(Color.RED);
            binding.vaktincikmasinatitle.setText("Kerahat Vakti");

        }

        if (ogleSaatInt - sistemSaatInt == 1 && ogleDakikaInt < 45 && sistemDakikaInt - ogleDakikaInt >= 15 || ogleSaatInt == sistemSaatInt && ogleDakikaInt >= 45 && ogleDakikaInt - sistemDakikaInt <= 45) {

            binding.vaktincikmasinatitle.setTextColor(Color.RED);
            binding.vaktincikmasinaTimerTitle.setTextColor(Color.RED);
            binding.vaktincikmasinatitle.setText("Kerahat Vakti");

        }

        if (aksamSaatInt - sistemSaatInt == 1 && aksamDakikaInt < 45 && sistemDakikaInt - aksamDakikaInt >= 15 || aksamSaatInt == sistemSaatInt && aksamDakikaInt >= 45 && aksamDakikaInt - sistemDakikaInt <= 45) {

            binding.vaktincikmasinatitle.setTextColor(Color.RED);
            binding.vaktincikmasinaTimerTitle.setTextColor(Color.RED);
            binding.vaktincikmasinatitle.setText("Kerahat Vakti");

        }


    }

    public void SaatFarkıHesap(String SistemSaatiFark, String GelecekVakitFark) {


        String[] splitSistem = SistemSaatiFark.split(":");

        Integer sistemSaatInt = Integer.parseInt(splitSistem[0]);
        Integer sistemDakikaInt = Integer.parseInt(splitSistem[1]);
        Integer sistemSaniyeInt = Integer.parseInt(splitSistem[2]);


        StringBuilder ekleGelecekVakit = new StringBuilder();
        ekleGelecekVakit.append(GelecekVakitFark);
        ekleGelecekVakit.append(":00");
        String GelecekVakit = ekleGelecekVakit.toString();

        String[] splitGelecek = GelecekVakit.split(":");

        Integer gelecekSaatInt = Integer.parseInt(splitGelecek[0]);
        Integer gelecekDakikaInt = Integer.parseInt(splitGelecek[1]);
        Integer gelecekSaniyeInt = Integer.parseInt(splitGelecek[2]);

        Integer saniyeFarki = sistemSaniyeInt - gelecekSaniyeInt;

        if(gelecekSaatInt >= sistemSaatInt && gelecekDakikaInt > sistemDakikaInt){

            Integer saatFarki = gelecekSaatInt - sistemSaatInt;
            Integer dakikaFarki = gelecekDakikaInt - sistemDakikaInt;

            vakitFarkSaat = saatFarki;
            vakitFarkDakika = dakikaFarki;

            if (saniyeFarki < 0) {

                vakitFarkSaniye = saniyeFarki - saniyeFarki*2;

            } else if (saniyeFarki >= 0) {

                vakitFarkSaniye = saniyeFarki;
            }

            StringBuilder vaktincikmasina = new StringBuilder();
            vaktincikmasina.append(vakitFarkSaat);
            vaktincikmasina.append(":");
            vaktincikmasina.append(vakitFarkDakika);
            vaktincikmasina.append(":");
            vaktincikmasina.append(vakitFarkSaniye);
            vaktinCikmasinaString = vaktincikmasina.toString();
            System.out.println("vaktin cıkmasına" + vaktinCikmasinaString);




        } else if (gelecekSaatInt > sistemSaatInt && gelecekDakikaInt < sistemDakikaInt) {

            Integer saatFarki = gelecekSaatInt - sistemSaatInt - 1;
            Integer dakikaFarki = 60 + gelecekDakikaInt - sistemDakikaInt;

            vakitFarkSaat = saatFarki;
            vakitFarkDakika = dakikaFarki;

            if (saniyeFarki < 0) {

                vakitFarkSaniye = saniyeFarki - saniyeFarki*2;

            } else if (saniyeFarki >= 0) {

                vakitFarkSaniye = saniyeFarki;
            }

            StringBuilder vaktincikmasina = new StringBuilder();
            vaktincikmasina.append(vakitFarkSaat);
            vaktincikmasina.append(":");
            vaktincikmasina.append(vakitFarkDakika);
            vaktincikmasina.append(":");
            vaktincikmasina.append(vakitFarkSaniye);
            vaktinCikmasinaString = vaktincikmasina.toString();

            System.out.println("vaktin cıkmasına" + vaktinCikmasinaString);

        } else if (gelecekSaatInt < sistemSaatInt && gelecekDakikaInt > sistemDakikaInt) {


            Integer saatFarki = 24 - sistemSaatInt + gelecekSaatInt;
            Integer dakikaFarki = gelecekDakikaInt - sistemDakikaInt;

            vakitFarkSaat = saatFarki;
            vakitFarkDakika = dakikaFarki;

            if (saniyeFarki < 0) {

                vakitFarkSaniye = saniyeFarki - saniyeFarki*2;

            } else if (saniyeFarki >= 0) {

                vakitFarkSaniye = saniyeFarki;
            }

            StringBuilder vaktincikmasina = new StringBuilder();
            vaktincikmasina.append(vakitFarkSaat);
            vaktincikmasina.append(":");
            vaktincikmasina.append(vakitFarkDakika);
            vaktincikmasina.append(":");
            vaktincikmasina.append(vakitFarkSaniye);
            vaktinCikmasinaString = vaktincikmasina.toString();

            System.out.println("vaktin cıkmasına" + vaktinCikmasinaString);



        } else if (gelecekSaatInt < sistemSaatInt && gelecekDakikaInt < sistemDakikaInt) {

            Integer saatFarki = 23 - sistemSaatInt + gelecekSaatInt;
            Integer dakikaFarki = 60 + gelecekDakikaInt - sistemDakikaInt;

            vakitFarkSaat = saatFarki;
            vakitFarkDakika = dakikaFarki;

            if (saniyeFarki < 0) {

                vakitFarkSaniye = saniyeFarki - saniyeFarki*2;

            } else if (saniyeFarki >= 0) {

                vakitFarkSaniye = saniyeFarki;
            }

            StringBuilder vaktincikmasina = new StringBuilder();
            vaktincikmasina.append(vakitFarkSaat);
            vaktincikmasina.append(":");
            vaktincikmasina.append(vakitFarkDakika);
            vaktincikmasina.append(":");
            vaktincikmasina.append(vakitFarkSaniye);
            vaktinCikmasinaString = vaktincikmasina.toString();

            System.out.println("vaktin cıkmasına" + vaktinCikmasinaString);


        }


    }

    public void VaktinCikmasinaTimer () {

        String[] splitSistem = vaktinCikmasinaString.split(":");

        Integer Saat = Integer.parseInt(splitSistem[0]);
        Integer Dakika = Integer.parseInt(splitSistem[1]);
        Integer Saniye = Integer.parseInt(splitSistem[2]);

        String hour = String.valueOf(Saat);
        String Minute = String.valueOf(Dakika);
        String Second = String.valueOf(Saniye);

        String Hours;
        String Minutes;
        String Seconds;

        if (hour.length() == 1) {

            StringBuilder Hr = new StringBuilder();
            Hr.append("0");
            Hr.append(hour);

            Hours = String.valueOf(Hr);


        } else {

            Hours = hour;

        }

        if (Minute.length() == 1) {

            StringBuilder Mnt = new StringBuilder();
            Mnt.append("0");
            Mnt.append(Minute);

            Minutes = String.valueOf(Mnt);

        } else {

            Minutes = Minute;

        }

        if (Second.length() == 1) {

            StringBuilder Sn = new StringBuilder();
            Sn.append("0");
            Sn.append(Minute);

            Seconds = String.valueOf(Sn);

        } else {

            Seconds = Second;

        }

        if (Hours == "00") {

            StringBuilder vaktincikmasina = new StringBuilder();
            vaktincikmasina.append(Minutes);
            vaktincikmasina.append(" ");
            vaktincikmasina.append("Dakika");

            binding.vaktincikmasinaTimerTitle.setText(vaktincikmasina);

        } else {

            StringBuilder vaktincikmasina = new StringBuilder();
            vaktincikmasina.append(Hours);
            vaktincikmasina.append(" ");
            vaktincikmasina.append("Saat");
            vaktincikmasina.append(" ");
            vaktincikmasina.append(Minutes);
            vaktincikmasina.append(" ");
            vaktincikmasina.append("Dakika");

            binding.vaktincikmasinaTimerTitle.setText(vaktincikmasina);


        }


    }

    public void sistemTarihiVoid() {

        SimpleDateFormat sistemtarih = new SimpleDateFormat("dd.M.yyyy");
        Date sistemtarihi = new Date();
        sistemTarihiStr = sistemtarih.format(sistemtarihi);

    }

    public void sistemSaatiVoid() {

        SimpleDateFormat sistemSaat = new SimpleDateFormat("HH:mm:ss");

        Date sistemSaati = Calendar.getInstance().getTime();


        sistemSaatiStr = sistemSaat.format(sistemSaati);

        System.out.println("Saaat" + sistemSaatiStr);


    }

    public void bildirimGonderImsak(String titles, String sounds ,int notifyNum, String time ) throws ParseException {

        vaktinHAdisi();

        // Create Sounds Link
        StringBuilder SoundUrl = new StringBuilder();
        SoundUrl.append("/raw/");
        SoundUrl.append(sounds);


        // Convert LongTime
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = formatter.parse(time);
        long  longdate = date.getTime();
        System.out.println(date);
        System.out.println(longdate);

        Uri customSoundUri = Uri.parse("android.resource://" + requireActivity().getPackageName() + "/" + R.raw.kussesi1);
       // Uri customSoundUri = Uri.parse("android.resource://" + requireActivity().getPackageName()+ SoundUrl);
       // Uri customSoundUri = Uri.parse(ContentResolver. SCHEME_ANDROID_RESOURCE + "://" + requireActivity().getPackageName() + SoundUrl);
       // System.out.println(customSoundUri);

        NotificationManager ezanVaktinotificationManager = (NotificationManager) requireActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build();

            CharSequence name = "Ezan Vakitleri Kanalı";
            String description = "Ezan Vakitleri için hatırlatma";
            int importance = NotificationManager.IMPORTANCE_HIGH;


            NotificationChannel ezanChannel = new NotificationChannel("notifyEzan", name, importance);
            ezanChannel.setDescription(description);
            ezanChannel.setSound(customSoundUri, audioAttributes);


            ezanVaktinotificationManager.createNotificationChannel(ezanChannel);

        }

        Intent intent = new Intent(getActivity(), ImsakVaktiBildirimReceiver.class);

        intent.putExtra("vImsakTitle", titles);
        intent.putExtra("vImsakDescription", vaktinHadisiStr);
        intent.putExtra("vImsakSound", sounds);
        intent.putExtra("vImsakNotifyNum", notifyNum);

        PendingIntent PendingEzan = PendingIntent.getBroadcast(getActivity(), 0,intent, 0);

        AlarmManager ezanAlarmManager =  (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);

        long along = System.currentTimeMillis();
        long timesss = 1000 * 5;

        ezanAlarmManager.set(AlarmManager.RTC_WAKEUP, along + timesss,PendingEzan);

    }

    public void vaktinHAdisi () {

        Random random = new Random();
        int a = random.nextInt(10);

        String[] vaktinHadisiArray = {"1","2","3","4","5","6","7","8","9","10"};

        vaktinHadisiStr = vaktinHadisiArray[a];

    }

    public void vaktinAyeti () {

        Random random = new Random();
        int a = random.nextInt(10);

        String[] vaktinAyetiArray = {"1","2","3","4","5","6","7","8","9","10"};

        vaktinAyetiStr = vaktinAyetiArray[a];

    }

    public void getBildirimSound() {

        vOImsakSureInt = sharedPreferences.getInt("vOImsakSureInt", 0);
        vOImsakSesStr = sharedPreferences.getString("vOImsakSesStr", "");
        vImsakSesStr = sharedPreferences.getString("vImsakSesStr", "");

        vOGunesSureInt = sharedPreferences.getInt("vOGunesSureInt", 0);
        vOGunesSesStr = sharedPreferences.getString("vOGunesSesStr", "");
        vGunesSesStr = sharedPreferences.getString("vGunesSesStr", "");

        vOOgleSureInt = sharedPreferences.getInt("vOOgleSureInt", 0);
        vOOgleSesStr = sharedPreferences.getString("vOOgleSesStr", "");
        vOgleSesStr = sharedPreferences.getString("vOgleSesStr", "");

        vOIkindiSureInt = sharedPreferences.getInt("vOIkindiSureInt", 0);
        vOIkindiSesStr = sharedPreferences.getString("vOIkindiSesStr", "");
        vIkindiSesStr = sharedPreferences.getString("vIkindiSesStr", "");

        vOAksamSureInt = sharedPreferences.getInt("vOAksamSureInt", 0);
        vOAksamSesStr = sharedPreferences.getString("vOAksamSesStr", "");
        vAksamSesStr = sharedPreferences.getString("vAksamSesStr", "");

        vOYatsiSureInt = sharedPreferences.getInt("vOYatsiSureInt", 0);
        vOYatsiSesStr = sharedPreferences.getString("vOYatsiSesStr", "");
        vYatsiSesStr = sharedPreferences.getString("vYatsiSesStr", "");






    }



}