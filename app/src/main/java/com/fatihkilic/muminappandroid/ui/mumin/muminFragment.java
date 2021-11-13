package com.fatihkilic.muminappandroid.ui.mumin;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fatihkilic.muminappandroid.MainActivity;
import com.fatihkilic.muminappandroid.databinding.FragmentMuminBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class muminFragment extends Fragment {

    private FragmentMuminBinding binding;
    SQLiteDatabase vakitDatabase;

    String imsakVakti;
    String gunesVakti;
    String ogleVakti;
    String ikindiVakti;
    String aksamVakti;
    String yatsiVakti;

    String vOImsakSureStr;
    String vOImsakSesStr;
    String vImsakSesStr;

    String vOGunesSureStr;
    String vOGunesSesStr;
    String vGunesSesStr;

    String vOOgleSureStr;
    String vOOgleSesStr;
    String vOgleSesStr;

    String vOIkindiSureStr;
    String vOIkindiSesStr;
    String vIkindiSesStr;

    String vOAksamSureStr;
    String vOAksamSesStr;
    String vAksamSesStr;

    String vOYatsiSureStr;
    String vOYatsiSesStr;
    String vYatsiSesStr;

    String sistemTarihiStr;
    String sistemSaatiStr;

    Integer vakitFarkSaat;
    Integer vakitFarkDakika;
    Integer vakitFarkSaniye;

    String vaktinCikmasinaString;

    Notification ezanVaktiNotification;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {





        binding = FragmentMuminBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        vakitDatabase = requireActivity().openOrCreateDatabase("EZANVAKITLERIDATA", Context.MODE_PRIVATE,null);


        sistemTarihiVoid();
        sistemSaatiVoid();

        System.out.println("tarih" + System.currentTimeMillis());

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

    public void bildirimGonderVoid(String ContentTitle, String ContentText, String Time ) throws ParseException {

        SimpleDateFormat convertVakittoMillis = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = convertVakittoMillis.parse(Time);
        long vakitMillis = date.getTime();

        ezanVaktiNotification = new Notification.Builder(getActivity())
                .setContentTitle(ContentTitle)
                .setContentText(ContentText)
                .setWhen(vakitMillis)
                .build();

    }

}