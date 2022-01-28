package com.fatihkilic.muminappandroid.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityMyAccountBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityMyAccountEditBinding;
import com.fatihkilic.muminappandroid.databinding.ActivitySignInBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.onesignal.OneSignal;

import java.util.ArrayList;
import java.util.Date;

public class MyAccountEditActivity extends AppCompatActivity {

    private ActivityMyAccountEditBinding binding;

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private static final String ONESIGNAL_APP_ID = "1966721c-a30c-4299-9d7a-38e084b98072";

    String userName;
    String name;
    String surName;
    String birthdayString;
    String email;
    String userDescription;
    String country;
    String province;
    String image;

    Date bithday;

    ArrayList<String> genderArray;
    ArrayList<String> countryArray;
    ArrayList<String> stateArray;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_edit);

        binding = ActivityMyAccountEditBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);



        genderArray = new ArrayList<String>();
        genderArray.add("Kadın");
        genderArray.add("Erkek");

        countryArray = new ArrayList<String>();
        countryArray.add("Türkiye");

        stateArray = new ArrayList<String>();
        stateArray.add("Adana");
        stateArray.add("Adıyaman");
        stateArray.add("Afyonkarahisar");
        stateArray.add("Ağrı");
        stateArray.add("Ağrı");
        stateArray.add("Amasya");
        stateArray.add("Ankara");
        stateArray.add("Antalya");
        stateArray.add("Aydın");
        stateArray.add("Balıkesir");
        stateArray.add("Bilecik");
        stateArray.add("Bingöl");
        stateArray.add("Bitlis");
        stateArray.add("Bolu");
        stateArray.add("Burdur");
        stateArray.add("Bursa");
        stateArray.add("Çanakkale");
        stateArray.add("Çankırı");
        stateArray.add("Çorum");
        stateArray.add("Denizli");
        stateArray.add("Diyarbakır");
        stateArray.add("Erzincan");
        stateArray.add("Erzurum");
        stateArray.add("Eskişehir");
        stateArray.add("Gaziantep");
        stateArray.add("Giresun");
        stateArray.add("Gümüşhane");
        stateArray.add("Hakkari");
        stateArray.add("Hatay");
        stateArray.add("Isparta");
        stateArray.add("Mersin");
        stateArray.add("İstanbul");
        stateArray.add("İzmir");
        stateArray.add("Kars");
        stateArray.add("Kayseri");
        stateArray.add("Kırklareli");
        stateArray.add("Kırşehir");
        stateArray.add("Kocaeli");
        stateArray.add("Konya");
        stateArray.add("Kütahya");
        stateArray.add("Malatya");
        stateArray.add("Manisa");
        stateArray.add("Kahramanmaraş");
        stateArray.add("Mardin");
        stateArray.add("Muğla");
        stateArray.add("Muş");
        stateArray.add("Nevşehir");
        stateArray.add("Niğde");
        stateArray.add("Rize");
        stateArray.add("Sakarya");
        stateArray.add("Sakarya");
        stateArray.add("Samsun");
        stateArray.add("Siirt");
        stateArray.add("Sinop");
        stateArray.add("Sivas");
        stateArray.add("Tekirdağ");
        stateArray.add("Tokat");
        stateArray.add("Trabzon");
        stateArray.add("Tunceli");
        stateArray.add("Uşak");
        stateArray.add("Van");
        stateArray.add("Yozgat");
        stateArray.add("Zonguldak");
        stateArray.add("Aksaray");
        stateArray.add("Bayburt");
        stateArray.add("Karaman");
        stateArray.add("Kırıkkale");
        stateArray.add("Batman");
        stateArray.add("Şırnak");
        stateArray.add("Bartın");
        stateArray.add("Ardahan");
        stateArray.add("Iğdır");
        stateArray.add("Yalova");
        stateArray.add("Karabük");
        stateArray.add("Kilis");
        stateArray.add("Osmaniye");
        stateArray.add("Düzce");

    }
}