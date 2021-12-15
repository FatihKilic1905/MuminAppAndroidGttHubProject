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
import android.widget.TextView;
import android.widget.Toast;

import com.fatihkilic.muminappandroid.Ayarlar.Receiver.EzanVaktiBildirimReceiver;
import com.fatihkilic.muminappandroid.Ayarlar.Receiver.ImsakOncesiBildirimReceiver;
import com.fatihkilic.muminappandroid.Ayarlar.Receiver.ImsakVaktiBildirimReceiver;
import com.fatihkilic.muminappandroid.DayInfo.DayInfoActivity;
import com.fatihkilic.muminappandroid.MainActivity;
import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.Ulkeler.KonumActivity;
import com.fatihkilic.muminappandroid.databinding.FragmentMuminBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.SQLOutput;
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

    String todayStr;



    String babyName;
    String mealOfTheDay;
    String todayInHistory;

    String vaktinCikmasinaString;

    NotificationManager notificationManager;

    SharedPreferences sharedPreferences;

    EzanVaktiBildirimReceiver EzanVaktiReceivers;

    String vaktinAyetiStr;
    String vaktinHadisiStr;

    private AdView mAdView;
    private AdView mAdView2;
    private AdView mAdView3;

    private FirebaseFirestore firebaseFirestore;

    long  longdateImsak;
    long  longdateGunes;
    long  longdateOgle;
    long  longdateIkindi;
    long  longDateAksam;
    long  longDateYatsi;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMuminBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedPreferences = requireActivity().getSharedPreferences("com.fatihkilic.muminappandroid", Context.MODE_PRIVATE);

        notificationManager = (NotificationManager) requireActivity().getSystemService(Context.NOTIFICATION_SERVICE);


        binding.VaktinAyetiLabel.setText(sharedPreferences.getString("VaktinAyeti","Sabrederek ve namaz kılarak (Allah’tan) yardım dileyin.  Şüphesiz namaz, Allah’a derinden saygı duyanlardan başkasına ağır gelir.(Bakara Sûresi 45)"));
        binding.VaktinHadisiLabel.setText(sharedPreferences.getString("VaktinHadisi","Bir müslüman, farz namazın vakti geldiğinde güzelce abdest alır, huşû içinde ve rükûunu da tam yaparak namazını kılarsa, büyük günah işlemedikçe, bu namaz önceki günahlarına keffâret olur. Bu her zaman böyledir. (Müslim, Tahâret 7)"));


        firebaseFirestore = FirebaseFirestore.getInstance();


        binding.konumtitle.setText(sharedPreferences.getString("storedKonum","Konum"));

        sistemSaatiVoid();
        getBildirimSound();
        DayInfoGet();
        sistemTarihiVoid();


        try {
            getEzanVakti();
        } catch (Exception e) {


        }


        String konumControl = binding.konumtitle.getText().toString();
        if (konumControl.equals("Konum")) {

            Intent ilkGirisIntent = new Intent(getActivity(),KonumActivity.class);
            sharedPreferences.edit().putString("IlkGiris", "1").apply();
            startActivity(ilkGirisIntent);

        } else {

            String control = binding.imsakTime.getText().toString();
            sharedPreferences.edit().putString("IlkGiris", "2").apply();


            if (control.equals("00:00")){

            } else {


                try {
                    vakitGeldi();
                    VaktinCikmasinaTimer();
                } catch (Exception e) {

                }




            }


        }









        // sil bunu yayınlarken
       binding.buttonnotification.setVisibility(View.VISIBLE);



        Button notBut = binding.buttonnotification;
        notBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {




                } catch (Exception ed) {
                    System.out.println("olmuyor");

                }


            }
        });

        TextView mealTextButton = binding.mealofDayText;
        mealTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toMealIntent = new Intent(getActivity(), DayInfoActivity.class);
                toMealIntent.putExtra("DayInfoPageInfo", "Meal" );
                startActivity(toMealIntent);

            }
        });

        TextView tihTextButton = binding.todayHistoryText;
        tihTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toMealIntent = new Intent(getActivity(), DayInfoActivity.class);
                toMealIntent.putExtra("DayInfoPageInfo", "Tih" );
                startActivity(toMealIntent);

            }
        });

        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = binding.adView;
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mAdView3 = binding.adView3;
        AdRequest adRequest3 = new AdRequest.Builder().build();
        mAdView3.loadAd(adRequest3);





        return root;


    }




    @Override
    public void onStart() {
        super.onStart();



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

        vakitDatabase = getActivity().openOrCreateDatabase("EZANVAKITLERIDATA", Context.MODE_PRIVATE,null);

        try {

            Cursor cursor = vakitDatabase.rawQuery("SELECT * FROM ezanvakitleridatabase WHERE miladiKisa = ?", new String[]{sistemTarihiStr});
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

                System.out.println("Vakitler   " + imsakVakti + gunesVakti + ogleVakti + ikindiVakti + aksamVakti + yatsiVakti);
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


                StringBuilder timeToday = new StringBuilder();
                timeToday.append(cursor.getString(miladiKisaIx));
                todayStr = timeToday.toString();




            }

            cursor.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        bildirimGonder();


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

    public void vaktinHAdisi () {

        sharedPreferences.edit().remove("VaktinHadisi").apply();

        Random random = new Random();
        int a = random.nextInt(5);

        String[] vaktinHadisiArray = {"Bir müslüman, farz namazın vakti geldiğinde güzelce abdest alır, huşû içinde ve rükûunu da tam yaparak namazını kılarsa, büyük günah işlemedikçe, bu namaz önceki günahlarına keffâret olur. Bu her zaman böyledir. (Müslim, Tahâret 7)"
                , "İki serinlik namazını, sabah ve ikindiyi kılan kimse cennete girer. (Buhârî, Mevâkît 26"
                ,  "Sabah namazını kılan kimse Allah’ın himayesindedir. Dikkat et, ey Ademoğlu! Allah, bizzat himayesinde olan bir konuda seni sorguya çekmesin. (Müslim, Mesâcid 261-262)"
                , "İkindi namazını terkeden kimsenin işlediği amelleri boşa gider. (Buhârî, Mevâkît 15)"
                , "Cemaatle kılınan namaz, tek başına kılınan namazdan yirmi yedi derece daha faziletlidir. (Buhârî, Ezân 30)"
                , "İnsanlar yatsı namazı ile sabah namazındaki fazilet ve sevabı bilselerdi, emekleyerek bile olsa mutlaka camiye, cemaate gelirlerdi. (Buhârî, Ezân 9)"};

        vaktinHadisiStr = vaktinHadisiArray[a];

        sharedPreferences.edit().putString("VaktinHadisi", vaktinHadisiStr).apply();

    }

    public void vaktinAyeti () {

        sharedPreferences.edit().remove("VaktinAyeti").apply();

        Random random = new Random();
        int a = random.nextInt(6);

        String[] vaktinAyetiArray = {"Onlar gaybe  inanırlar, namazı dosdoğru kılarlar, kendilerine rızık olarak verdiğimizden de Allah yolunda harcarlar. (Bakara Sûresi 3)"
                , "Namazı kılın, zekâtı verin. Rükû edenlerle birlikte siz de rükû edin. (Bakara Sûresi 43)"
                , "Sabrederek ve namaz kılarak (Allah’tan) yardım dileyin.  Şüphesiz namaz, Allah’a derinden saygı duyanlardan başkasına ağır gelir.(Bakara Sûresi 45)"
                , " Sizin dostunuz ancak Allah’tır, Resûlüdür ve Allah’ın emirlerine boyun eğerek namazı kılan, zekâtı veren mü’minlerdir.(Mâide Sûresi 55)"
                , "Siz namaza çağırdığınız vakit onu alaya alıp eğlence yerine koyuyorlar. Bu, şüphesiz onların akılları ermeyen bir toplum olmalarındandır.(Mâide Sûresi 58)"
                , "Şeytan, içki ve kumarla, ancak aranıza düşmanlık ve kin sokmak; sizi Allah’ı anmaktan ve namazdan alıkoymak ister. Artık vazgeçiyor musunuz? (Mâide Sûresi 91)"
                , "Kur’an, namazı dosdoğru kılan, zekâtı veren ve ahirete de kesin olarak inanan mü’minler için bir hidayet rehberi ve bir müjdedir. (Neml Sûresi 2-3)"};

        vaktinAyetiStr = vaktinAyetiArray[a];

        sharedPreferences.edit().putString("VaktinAyeti", vaktinAyetiStr).apply();

    }

    public void getBildirimSound() {

        vOImsakSureInt = sharedPreferences.getInt("vOImsakSureInt", 45);
        vOImsakSesStr = sharedPreferences.getString("vOImsakSesStr", "ahmadalnafes");
        vImsakSesStr = sharedPreferences.getString("vImsakSesStr", "ahmadalnafes");

        vOGunesSureInt = sharedPreferences.getInt("vOGunesSureInt", 45);
        vOGunesSesStr = sharedPreferences.getString("vOGunesSesStr", "ahmadalnafes");
        vGunesSesStr = sharedPreferences.getString("vGunesSesStr", "ahmadalnafes");

        vOOgleSureInt = sharedPreferences.getInt("vOOgleSureInt", 45);
        vOOgleSesStr = sharedPreferences.getString("vOOgleSesStr", "ahmadalnafes");
        vOgleSesStr = sharedPreferences.getString("vOgleSesStr", "ahmadalnafes");

        vOIkindiSureInt = sharedPreferences.getInt("vOIkindiSureInt", 45);
        vOIkindiSesStr = sharedPreferences.getString("vOIkindiSesStr", "ahmadalnafes");
        vIkindiSesStr = sharedPreferences.getString("vIkindiSesStr", "ahmadalnafes");

        vOAksamSureInt = sharedPreferences.getInt("vOAksamSureInt", 45);
        vOAksamSesStr = sharedPreferences.getString("vOAksamSesStr", "ahmadalnafes");
        vAksamSesStr = sharedPreferences.getString("vAksamSesStr", "ahmadalnafes");

        vOYatsiSureInt = sharedPreferences.getInt("vOYatsiSureInt", 45);
        vOYatsiSesStr = sharedPreferences.getString("vOYatsiSesStr", "ahmadalnafes");
        vYatsiSesStr = sharedPreferences.getString("vYatsiSesStr", "ahmadalnafes");






    }

    private void DayInfoGet() {



        DocumentReference usdRef = firebaseFirestore.collection("DayInfo").document("Info");
        usdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        babyName = (String) document.get("babyName");
                        binding.babyNameText.setText(babyName);
                        mealOfTheDay = (String) document.get("mealOfTheDay");
                        binding.mealofDayText.setText(mealOfTheDay);
                        todayInHistory = (String) document.get("todayInHistory");
                        binding.todayHistoryText.setText(todayInHistory);



                    } else {
                        System.out.println("Olumsuz");
                    }

                } else {

                    System.out.println("cekme başarısız");
                }

            }
        });





    }

    public void bildirimGonderVaktinde(String titles, String sounds ,int notifyNum, long longdate ){


        long currentLong = System.currentTimeMillis();

        if (longdate > currentLong || sounds.equals("Kapalı")) {

            vaktinHAdisi();
            vaktinAyeti();

            // Create Sounds Link
            StringBuilder SoundUrl = new StringBuilder();
            SoundUrl.append("/raw/");
            SoundUrl.append(sounds);


            Intent intent = new Intent(getActivity(), ImsakVaktiBildirimReceiver.class);

            intent.putExtra("vImsakTitle", titles);
            intent.putExtra("vImsakDescription", vaktinHadisiStr);
            intent.putExtra("vImsakSound", sounds);
            intent.putExtra("vImsakNotifyNum", notifyNum);


            PendingIntent PendingEzan = PendingIntent.getBroadcast(getActivity(), notifyNum, intent, 0);

            AlarmManager ezanAlarmManager = (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);

            long along = System.currentTimeMillis();
            long timesss = 1000 * 5;

            ezanAlarmManager.set(AlarmManager.RTC_WAKEUP, longdate, PendingEzan);


            System.out.println(titles + "Ayarlandı");

        }


    }

    public void bildirimGonderVaktindenOnce(String titles, String sounds ,int notifyNum, long longdate, int beforeTime ) {



        long currentLong = System.currentTimeMillis();

        if (longdate > currentLong || sounds.equals("Kapalı")) {

            // Create Sounds Link
            StringBuilder SoundUrl = new StringBuilder();
            SoundUrl.append("/raw/");
            SoundUrl.append(sounds);

            Intent intent = new Intent(getActivity(), ImsakOncesiBildirimReceiver.class);

            intent.putExtra("vOImsakTitle", titles);
            intent.putExtra("vOImsakDescription", "Vaktin çıkmasına " + beforeTime + " dakika kaldı.");
            intent.putExtra("vOImsakSound", sounds);
            intent.putExtra("vOImsakNotifyNum", notifyNum);

            PendingIntent PendingEzan = PendingIntent.getBroadcast(getActivity(), notifyNum,intent, 0);

            AlarmManager ezanAlarmManager =  (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);

            long beforeLong = 60000 * beforeTime;


            long beforetimelong = longdate - beforeLong;

            System.out.println("before" + beforetimelong);
            ezanAlarmManager.set(AlarmManager.RTC_WAKEUP, beforetimelong ,PendingEzan);

            System.out.println(titles + "Öncesi Ayarlandı");

        }



    }


    public void bildirimGonder () {

        //ImakVaktiBildirim

        String[] splitImsak = todayStr.split("[.]");

        StringBuilder imsakTimeBuild = new StringBuilder();
        imsakTimeBuild.append(splitImsak[2]);
        imsakTimeBuild.append("-");
        imsakTimeBuild.append(splitImsak[1]);
        imsakTimeBuild.append("-");
        imsakTimeBuild.append(splitImsak[0]);
        imsakTimeBuild.append(" ");
        imsakTimeBuild.append(imsakVakti);
        imsakTimeBuild.append(":00");
        String imsakTime = imsakTimeBuild.toString();


        try {
            DateFormat formatterImsak = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateImsak = formatterImsak.parse(imsakTime);
            longdateImsak = dateImsak.getTime();


        } catch (Exception e) {

            System.out.println("imsak long alınamadı");

        }

        bildirimGonderVaktinde("İmsak Vakti", vImsakSesStr,1,longdateImsak);
        bildirimGonderVaktindenOnce("İmsak Vakti",vOImsakSesStr,7,longdateImsak,vOImsakSureInt);


        // Gunes Vakti

        String[] splitGunes = todayStr.split("[.]");

        StringBuilder gunesTimeBuild = new StringBuilder();
        gunesTimeBuild.append(splitGunes[2]);
        gunesTimeBuild.append("-");
        gunesTimeBuild.append(splitGunes[1]);
        gunesTimeBuild.append("-");
        gunesTimeBuild.append(splitGunes[0]);
        gunesTimeBuild.append(" ");
        gunesTimeBuild.append(gunesVakti);
        gunesTimeBuild.append(":00");
        String gunesTime = gunesTimeBuild.toString();

        try {

            DateFormat formattergunes = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dategunes = formattergunes.parse(gunesTime);
            longdateGunes = dategunes.getTime();

        } catch (Exception e) {

            System.out.println("gunes long alınamadı");

        }


        bildirimGonderVaktinde("Güneş Doğdu", vGunesSesStr,2,longdateGunes);
        bildirimGonderVaktindenOnce("Güneş",vOImsakSesStr,8,longdateGunes,vOGunesSureInt);


        // Ogle Vakti Bildirim

        String[] splitOgle = todayStr.split("[.]");

        StringBuilder ogleTimeBuild = new StringBuilder();
        ogleTimeBuild.append(splitOgle[2]);
        ogleTimeBuild.append("-");
        ogleTimeBuild.append(splitOgle[1]);
        ogleTimeBuild.append("-");
        ogleTimeBuild.append(splitOgle[0]);
        ogleTimeBuild.append(" ");
        ogleTimeBuild.append(ogleVakti);
        ogleTimeBuild.append(":00");
        String ogleTime = ogleTimeBuild.toString();

        try {

            DateFormat formatterogle = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateogle = formatterogle.parse(ogleTime);
            longdateOgle = dateogle.getTime();

        } catch (Exception e) {

            System.out.println("ogle long alınamadı");

        }


        bildirimGonderVaktinde("Öğle Vakti", vOgleSesStr,3,longdateOgle);
        bildirimGonderVaktindenOnce("Öğle Vakti",vOOgleSesStr,9,longdateOgle,vOOgleSureInt);

        // IkindiVaktiBildirim

        String[] splitIkindi = todayStr.split("[.]");

        StringBuilder ikindiTimeBuild = new StringBuilder();
        ikindiTimeBuild.append(splitIkindi[2]);
        ikindiTimeBuild.append("-");
        ikindiTimeBuild.append(splitIkindi[1]);
        ikindiTimeBuild.append("-");
        ikindiTimeBuild.append(splitIkindi[0]);
        ikindiTimeBuild.append(" ");
        ikindiTimeBuild.append(ikindiVakti);
        ikindiTimeBuild.append(":00");
        String ikindiTime = ikindiTimeBuild.toString();

        try {

            DateFormat formatterikindi = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateikindi= formatterikindi.parse(ikindiTime);
            longdateIkindi = dateikindi.getTime();

        } catch (Exception e) {

            System.out.println("ogle long alınamadı");

        }

        bildirimGonderVaktinde("İkindi Vakti", vIkindiSesStr,4,longdateIkindi);
        bildirimGonderVaktindenOnce("İkindi Vakti",vOIkindiSesStr,10,longdateIkindi,vOIkindiSureInt);

        // Aksam Vakti Bildirim

        String[] splitAksam = todayStr.split("[.]");

        StringBuilder aksamTimeBuild = new StringBuilder();
        aksamTimeBuild.append(splitAksam[2]);
        aksamTimeBuild.append("-");
        aksamTimeBuild.append(splitAksam[1]);
        aksamTimeBuild.append("-");
        aksamTimeBuild.append(splitAksam[0]);
        aksamTimeBuild.append(" ");
        aksamTimeBuild.append(aksamVakti);
        aksamTimeBuild.append(":00");
        String aksamTime = aksamTimeBuild.toString();


        try {

            DateFormat formatterAksam = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateAksam = formatterAksam.parse(aksamTime);
            longDateAksam = dateAksam.getTime();

        } catch (Exception e) {

            System.out.println("ogle long alınamadı");

        }

        bildirimGonderVaktinde("Akşam Vakti", vAksamSesStr,5,longDateAksam);
        bildirimGonderVaktindenOnce("Akşam Vakti",vOAksamSesStr,11,longDateAksam,vOAksamSureInt);


        // yatsiBildirim

        String[] splitYatsi = todayStr.split("[.]");

        StringBuilder yatsiTimeBuild = new StringBuilder();
        yatsiTimeBuild.append(splitYatsi[2]);
        yatsiTimeBuild.append("-");
        yatsiTimeBuild.append(splitYatsi[1]);
        yatsiTimeBuild.append("-");
        yatsiTimeBuild.append(splitYatsi[0]);
        yatsiTimeBuild.append(" ");
        yatsiTimeBuild.append(yatsiVakti);
        yatsiTimeBuild.append(":00");
        String yatsiTime = yatsiTimeBuild.toString();

        try {

            DateFormat formatterYatsi = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateYatsi = formatterYatsi.parse(yatsiTime);
            longDateYatsi = dateYatsi.getTime();

        } catch (Exception e) {

            System.out.println("ogle long alınamadı");

        }


        bildirimGonderVaktinde("Yatsı Vakti", vYatsiSesStr,6,longDateYatsi);
        bildirimGonderVaktindenOnce("Yatsı Vakti",vOYatsiSesStr,12,longDateYatsi,vOYatsiSureInt);


    }



    /*
    public void bildirimGonderGunes(String titles, String sounds ,int notifyNum, String time ) throws ParseException {

        vaktinHAdisi();
        vaktinAyeti();

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


        Uri customSoundUri = Uri.parse(ContentResolver. SCHEME_ANDROID_RESOURCE + "://" + requireActivity().getPackageName() + SoundUrl);


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

        intent.putExtra("vGunesTitle", titles);
        intent.putExtra("vGunesDescription", vaktinHadisiStr);
        intent.putExtra("vGunesSound", sounds);
        intent.putExtra("vGunesNotifyNum", notifyNum);
        intent.putExtra("vaktinAyeti", vaktinAyetiStr);
        intent.putExtra("vaktinHadisi", vaktinHadisiStr);


        PendingIntent PendingEzan = PendingIntent.getBroadcast(getActivity(), 0,intent, 0);

        AlarmManager ezanAlarmManager =  (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);

        long along = System.currentTimeMillis();
        long timesss = 1000 * 5;

        ezanAlarmManager.set(AlarmManager.RTC_WAKEUP, longdate,PendingEzan);

    }

    public void bildirimGonderOgle(String titles, String sounds ,int notifyNum, String time ) throws ParseException {



        vaktinHAdisi();
        vaktinAyeti();

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


        Uri customSoundUri = Uri.parse(ContentResolver. SCHEME_ANDROID_RESOURCE + "://" + requireActivity().getPackageName() + SoundUrl);


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

        intent.putExtra("vOgleTitle", titles);
        intent.putExtra("vOgleDescription", vaktinHadisiStr);
        intent.putExtra("vOgleSound", sounds);
        intent.putExtra("vOgleNotifyNum", notifyNum);
        intent.putExtra("vaktinAyeti", vaktinAyetiStr);
        intent.putExtra("vaktinHadisi", vaktinHadisiStr);


        PendingIntent PendingEzan = PendingIntent.getBroadcast(getActivity(), 0,intent, 0);

        AlarmManager ezanAlarmManager =  (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);

        long along = System.currentTimeMillis();
        long timesss = 1000 * 5;

        ezanAlarmManager.set(AlarmManager.RTC_WAKEUP, longdate,PendingEzan);

    }

    public void bildirimGonderIkindi(String titles, String sounds ,int notifyNum, String time ) throws ParseException {



        vaktinHAdisi();
        vaktinAyeti();

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


        Uri customSoundUri = Uri.parse(ContentResolver. SCHEME_ANDROID_RESOURCE + "://" + requireActivity().getPackageName() + SoundUrl);


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

        intent.putExtra("vIkindiTitle", titles);
        intent.putExtra("vIkindiDescription", vaktinHadisiStr);
        intent.putExtra("vIkindiSound", sounds);
        intent.putExtra("vIkindiNotifyNum", notifyNum);
        intent.putExtra("vaktinAyeti", vaktinAyetiStr);
        intent.putExtra("vaktinHadisi", vaktinHadisiStr);


        PendingIntent PendingEzan = PendingIntent.getBroadcast(getActivity(), 0,intent, 0);

        AlarmManager ezanAlarmManager =  (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);

        long along = System.currentTimeMillis();
        long timesss = 1000 * 5;

        ezanAlarmManager.set(AlarmManager.RTC_WAKEUP, longdate,PendingEzan);

    }

    public void bildirimGonderAksam(String titles, String sounds ,int notifyNum, String time ) throws ParseException {



        vaktinHAdisi();
        vaktinAyeti();

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


        Uri customSoundUri = Uri.parse(ContentResolver. SCHEME_ANDROID_RESOURCE + "://" + requireActivity().getPackageName() + SoundUrl);


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

        intent.putExtra("vAksamTitle", titles);
        intent.putExtra("vAksamDescription", vaktinHadisiStr);
        intent.putExtra("vAksamSound", sounds);
        intent.putExtra("vAksamNotifyNum", notifyNum);
        intent.putExtra("vaktinAyeti", vaktinAyetiStr);
        intent.putExtra("vaktinHadisi", vaktinHadisiStr);


        PendingIntent PendingEzan = PendingIntent.getBroadcast(getActivity(), 0,intent, 0);

        AlarmManager ezanAlarmManager =  (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);

        long along = System.currentTimeMillis();
        long timesss = 1000 * 5;

        ezanAlarmManager.set(AlarmManager.RTC_WAKEUP, longdate,PendingEzan);

    }

    public void bildirimGonderYatsi(String titles, String sounds ,int notifyNum, String time ) throws ParseException {



        vaktinHAdisi();
        vaktinAyeti();

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


        Uri customSoundUri = Uri.parse(ContentResolver. SCHEME_ANDROID_RESOURCE + "://" + requireActivity().getPackageName() + SoundUrl);


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

        intent.putExtra("vYatsiTitle", titles);
        intent.putExtra("vYatsiDescription", vaktinHadisiStr);
        intent.putExtra("vYatsiSound", sounds);
        intent.putExtra("vYatsiNotifyNum", notifyNum);
        intent.putExtra("vaktinAyeti", vaktinAyetiStr);
        intent.putExtra("vaktinHadisi", vaktinHadisiStr);


        PendingIntent PendingEzan = PendingIntent.getBroadcast(getActivity(), 0,intent, 0);

        AlarmManager ezanAlarmManager =  (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);

        long along = System.currentTimeMillis();
        long timesss = 1000 * 5;

        ezanAlarmManager.set(AlarmManager.RTC_WAKEUP, longdate,PendingEzan);

    }



    public void bildirimGonderGunesOncesi(String titles, String sounds ,int notifyNum, String time, int beforeTime ) throws ParseException {


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


        Uri customSoundUri = Uri.parse(ContentResolver. SCHEME_ANDROID_RESOURCE + "://" + requireActivity().getPackageName() + SoundUrl);


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

        intent.putExtra("vOGunesTitle", titles);
        intent.putExtra("vOGunesDescription", "Vaktin çıkmasına " + beforeTime + " dakika kaldı.");
        intent.putExtra("vOGunesSound", sounds);
        intent.putExtra("vOGunesNotifyNum", notifyNum);



        PendingIntent PendingEzan = PendingIntent.getBroadcast(getActivity(), 0,intent, 0);

        AlarmManager ezanAlarmManager =  (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);

        long along = System.currentTimeMillis();
        long beforeLong = 60000 * beforeTime;

        ezanAlarmManager.set(AlarmManager.RTC_WAKEUP, longdate - beforeLong,PendingEzan);

    }

    public void bildirimGonderOgleOncesi(String titles, String sounds ,int notifyNum, String time, int beforeTime ) throws ParseException {


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


        Uri customSoundUri = Uri.parse(ContentResolver. SCHEME_ANDROID_RESOURCE + "://" + requireActivity().getPackageName() + SoundUrl);


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

        intent.putExtra("vOOgleTitle", titles);
        intent.putExtra("vOOgleDescription", "Vaktin çıkmasına " + beforeTime + " dakika kaldı.");
        intent.putExtra("vOOgleSound", sounds);
        intent.putExtra("vOOgleNotifyNum", notifyNum);



        PendingIntent PendingEzan = PendingIntent.getBroadcast(getActivity(), 0,intent, 0);

        AlarmManager ezanAlarmManager =  (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);

        long along = System.currentTimeMillis();
        long beforeLong = 60000 * beforeTime;

        ezanAlarmManager.set(AlarmManager.RTC_WAKEUP, longdate - beforeLong,PendingEzan);

    }

    public void bildirimGonderIkindiOncesi(String titles, String sounds ,int notifyNum, String time, int beforeTime ) throws ParseException {


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


        Uri customSoundUri = Uri.parse(ContentResolver. SCHEME_ANDROID_RESOURCE + "://" + requireActivity().getPackageName() + SoundUrl);


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

        intent.putExtra("vOIkindiTitle", titles);
        intent.putExtra("vOIkindiDescription", "Vaktin çıkmasına " + beforeTime + " dakika kaldı.");
        intent.putExtra("vOIkindiSound", sounds);
        intent.putExtra("vOIkindiNotifyNum", notifyNum);



        PendingIntent PendingEzan = PendingIntent.getBroadcast(getActivity(), 0,intent, 0);

        AlarmManager ezanAlarmManager =  (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);

        long along = System.currentTimeMillis();
        long beforeLong = 60000 * beforeTime;

        ezanAlarmManager.set(AlarmManager.RTC_WAKEUP, longdate - beforeLong,PendingEzan);

    }

    public void bildirimGonderAksamOncesi(String titles, String sounds ,int notifyNum, String time, int beforeTime ) throws ParseException {


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


        Uri customSoundUri = Uri.parse(ContentResolver. SCHEME_ANDROID_RESOURCE + "://" + requireActivity().getPackageName() + SoundUrl);


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

        intent.putExtra("vOAksamTitle", titles);
        intent.putExtra("vOAksamDescription", "Vaktin çıkmasına " + beforeTime + " dakika kaldı.");
        intent.putExtra("vOAksamSound", sounds);
        intent.putExtra("vOAksamNotifyNum", notifyNum);



        PendingIntent PendingEzan = PendingIntent.getBroadcast(getActivity(), 0,intent, 0);

        AlarmManager ezanAlarmManager =  (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);

        long along = System.currentTimeMillis();
        long beforeLong = 60000 * beforeTime;

        ezanAlarmManager.set(AlarmManager.RTC_WAKEUP, longdate - beforeLong,PendingEzan);

    }

    public void bildirimGonderYatsiOncesi(String titles, String sounds ,int notifyNum, String time, int beforeTime ) throws ParseException {


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


        Uri customSoundUri = Uri.parse(ContentResolver. SCHEME_ANDROID_RESOURCE + "://" + requireActivity().getPackageName() + SoundUrl);


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

        intent.putExtra("vOYatsiTitle", titles);
        intent.putExtra("vOYatsiDescription", "Vaktin çıkmasına " + beforeTime + " dakika kaldı.");
        intent.putExtra("vOYatsiSound", sounds);
        intent.putExtra("vOYatsiNotifyNum", notifyNum);



        PendingIntent PendingEzan = PendingIntent.getBroadcast(getActivity(), 0,intent, 0);

        AlarmManager ezanAlarmManager =  (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);

        long along = System.currentTimeMillis();
        long beforeLong = 60000 * beforeTime;

        ezanAlarmManager.set(AlarmManager.RTC_WAKEUP, longdate - beforeLong,PendingEzan);

    }


    */


}