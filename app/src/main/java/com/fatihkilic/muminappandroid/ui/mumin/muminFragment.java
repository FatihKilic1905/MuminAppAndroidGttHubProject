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
import android.database.sqlite.SQLiteStatement;
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
import com.fatihkilic.muminappandroid.EzanVaktiCoplete.EzanVakitleriModel;
import com.fatihkilic.muminappandroid.EzanVaktiCoplete.EzanVaktiPost;
import com.fatihkilic.muminappandroid.EzanVaktiService;
import com.fatihkilic.muminappandroid.MainActivity;
import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.Ulkeler.KonumActivity;
import com.fatihkilic.muminappandroid.User.FriendsDetailActivity;
import com.fatihkilic.muminappandroid.User.MyAccountActivity;
import com.fatihkilic.muminappandroid.User.SignInActivity;
import com.fatihkilic.muminappandroid.ZikirMatik.ZikirMatikMainActivity;
import com.fatihkilic.muminappandroid.databinding.ActivityEzanVaktiBinding;
import com.fatihkilic.muminappandroid.databinding.FragmentMuminBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.onesignal.OneSignal;

import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class muminFragment extends Fragment {

    private FragmentMuminBinding binding;
    SQLiteDatabase vakitDatabase;

    ArrayList<EzanVakitleriModel> ezanVakitleriModels;
    ArrayList<EzanVaktiPost> ezanVaktiPostArrayList;

    private String BaseUrl = "https://ezanvakti.herokuapp.com/";

    Retrofit retrofitIlce;

    String imsakVakti;
    String gunesVakti;
    String ogleVakti;
    String ikindiVakti;
    String aksamVakti;
    String yatsiVakti;

    long  longdateImsak;
    long  longdateGunes;
    long  longdateOgle;
    long  longdateIkindi;
    long  longDateAksam;
    long  longDateYatsi;

    String imsakVaktiTomorrow;
    String gunesVaktiTomorrow;
    String ogleVaktiTomorrow;
    String ikindiVaktiTomorrow;
    String aksamVaktiTomorrow;
    String yatsiVaktiTomorrow;

    long  longdateImsakTomorrow;
    long  longdateGunesTomorrow;
    long  longdateOgleTomorrow;
    long  longdateIkindiTomorrow;
    long  longDateAksamTomorrow;
    long  longDateYatsiTomorrow;

    String imsakVaktiGunAsiri;
    String gunesVaktiGunAsiri;
    String ogleVaktiGunAsiri;
    String ikindiVaktiGunAsiri;
    String aksamVaktiGunAsiri;
    String yatsiVaktiGunAsiri;

    long  longdateImsakGunAsiri;
    long  longdateGunesGunAsiri;
    long  longdateOgleGunAsiri;
    long  longdateIkindiGunAsiri;
    long  longDateAksamGunAsiri;
    long  longDateYatsiGunAsiri;

    String todayStr;
    String tomorrowStr;
    String gunAsiriStr;


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
    String sistemTarihiTomorrowStr;
    String sistemTarihiGunAsiriStr;

    Integer vakitFarkSaat;
    Integer vakitFarkDakika;
    Integer vakitFarkSaniye;

    String babyName;
    String mealOfTheDay;
    String todayInHistory;

    String vaktinCikmasinaString;

    NotificationManager notificationManager;

    SharedPreferences sharedPreferences;

    EzanVaktiBildirimReceiver EzanVaktiReceivers;

    String vaktinAyetiStr;
    String vaktinHadisiStr;

    ArrayList<String> friendsRequestCount;

    private AdView mAdView;
    private AdView mAdView2;
    private AdView mAdView3;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth auth;

    private static final String ONESIGNAL_APP_ID = "1966721c-a30c-4299-9d7a-38e084b98072";



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(requireActivity());
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        binding = FragmentMuminBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Gson gsonIlce = new GsonBuilder().setLenient().create();

        retrofitIlce = new Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create(gsonIlce)).build();

        sharedPreferences = requireActivity().getSharedPreferences("com.fatihkilic.muminappandroid", Context.MODE_PRIVATE);

        notificationManager = (NotificationManager) requireActivity().getSystemService(Context.NOTIFICATION_SERVICE);


        binding.VaktinAyetiLabel.setText(sharedPreferences.getString("VaktinAyeti","Sabrederek ve namaz kılarak (Allah’tan) yardım dileyin.  Şüphesiz namaz, Allah’a derinden saygı duyanlardan başkasına ağır gelir.(Bakara Sûresi 45)"));
        binding.VaktinHadisiLabel.setText(sharedPreferences.getString("VaktinHadisi","Bir müslüman, farz namazın vakti geldiğinde güzelce abdest alır, huşû içinde ve rükûunu da tam yaparak namazını kılarsa, büyük günah işlemedikçe, bu namaz önceki günahlarına keffâret olur. Bu her zaman böyledir. (Müslim, Tahâret 7)"));


        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();


        binding.konumtitle.setText(sharedPreferences.getString("storedKonum","Konum"));




        if (auth.getCurrentUser() != null) {

            friendsRequestCount = new ArrayList<>();

            getProfil();

            getfriendsCount();


        }




        try {

            sistemTarihiVoid();
            sistemSaatiVoid();
            getEzanVakti();
            getBildirimSound();
            DayInfoGet();
            getTomorrowVakit();
            getGunAsiriVakit();
            loadEzanVakti();
        } catch (Exception e) {


        }




        binding.userNameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userText = binding.userNameText.getText().toString();


                    if (auth.getCurrentUser() != null) {

                        getProfil();

                        Intent zikirmatikIntent = new Intent(getActivity(), MyAccountActivity.class);
                        startActivity(zikirmatikIntent);

                    } else {


                        Intent zikirmatikIntent = new Intent(getActivity(), SignInActivity.class);
                        zikirmatikIntent.putExtra("ComePage", "MainPageUser");
                        startActivity(zikirmatikIntent);


                    }






            }
        });

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



        Button zikirMatikButton = binding.zikirMatikButton;
        zikirMatikButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if (auth.getCurrentUser() != null) {

                Intent zikirmatikIntent = new Intent(getActivity(), ZikirMatikMainActivity.class);
                startActivity(zikirmatikIntent);

            } else {

                Intent zikirmatikIntent = new Intent(getActivity(), SignInActivity.class);
                zikirmatikIntent.putExtra("ComePage", "MainPageZikir");
                startActivity(zikirmatikIntent);

            }

            }
        });

        if (auth.getCurrentUser() != null) {

            System.out.println("kullanıcı  " + auth.getCurrentUser().getEmail());

        }


        return root;


    }

    public void loadEzanVakti() {


        vakitDatabase = getActivity().openOrCreateDatabase("EZANVAKITLERIDATA", Context.MODE_PRIVATE, null);
        vakitDatabase.execSQL("CREATE TABLE IF NOT EXISTS ezanvakitleridatabase(id INTEGER PRIMARY KEY, imsakVakti VARCHAR, gunesVakti VARCHAR, ogleVakti VARCHAR, ikindiVakti VARCHAR, aksamVakti VARCHAR, yatsiVakti VARCHAR, miladiKisa VARCHAR, miladiUzun VARCHAR, hicriUzun VARCHAR)");


        try {

            Cursor cursor = vakitDatabase.rawQuery("SELECT * FROM ezanvakitleridatabase WHERE miladiKisa = ?", new String[]{sistemTarihiStr});

            int miladiKisaIx = cursor.getColumnIndex("miladiKisa");


            while (cursor.moveToNext()){


                StringBuilder timeToday = new StringBuilder();
                timeToday.append(cursor.getString(miladiKisaIx));
                todayStr = timeToday.toString();




            }

            cursor.close();

            System.out.println("buyuk degil" + todayStr);

            if (todayStr.equals(sistemTarihiStr)) {

                System.out.println("Buyuk degil");



            } else {

                System.out.println("Buyuk degil 222");

                String ilceIDShare = sharedPreferences.getString("ilceID","");

                System.out.println("Buyuk degil 111" + ilceIDShare);

                vakitDatabase.execSQL("DROP TABLE  ezanvakitleridatabase");

                EzanVaktiService ezanVaktiService = retrofitIlce.create(EzanVaktiService.class);
                Call<List<EzanVakitleriModel>> call = ezanVaktiService.getEzanData(ilceIDShare);

                call.enqueue(new Callback<List<EzanVakitleriModel>>() {
                    @Override
                    public void onResponse(Call<List<EzanVakitleriModel>> call, Response<List<EzanVakitleriModel>> response) {

                        if (response.isSuccessful()) {

                            List<EzanVakitleriModel> responseList = response.body();
                            ezanVakitleriModels = new ArrayList<>(responseList);

                            for (EzanVakitleriModel vakitleriModel : ezanVakitleriModels) {

                                try {


                                    vakitDatabase.execSQL("CREATE TABLE IF NOT EXISTS ezanvakitleridatabase(id INTEGER PRIMARY KEY, imsakVakti VARCHAR, gunesVakti VARCHAR, ogleVakti VARCHAR, ikindiVakti VARCHAR, aksamVakti VARCHAR, yatsiVakti VARCHAR, miladiKisa VARCHAR, miladiUzun VARCHAR, hicriUzun VARCHAR)");
                                    String EzanVakitleriDatabaseString = "INSERT INTO ezanvakitleridatabase(imsakVakti, gunesVakti, ogleVakti, ikindiVakti, aksamVakti, yatsiVakti, miladiKisa, miladiUzun, hicriUzun) VALUES(?,?,?,?,?,?,?,?,?)";
                                    SQLiteStatement sqLiteStatementEzanVakti = vakitDatabase.compileStatement(EzanVakitleriDatabaseString);
                                    sqLiteStatementEzanVakti.bindString(1, vakitleriModel.Imsak);
                                    sqLiteStatementEzanVakti.bindString(2, vakitleriModel.Gunes);
                                    sqLiteStatementEzanVakti.bindString(3, vakitleriModel.Ogle);
                                    sqLiteStatementEzanVakti.bindString(4, vakitleriModel.Ikindi);
                                    sqLiteStatementEzanVakti.bindString(5, vakitleriModel.Aksam);
                                    sqLiteStatementEzanVakti.bindString(6, vakitleriModel.Yatsi);
                                    sqLiteStatementEzanVakti.bindString(7, vakitleriModel.MiladiTarihKisa);
                                    sqLiteStatementEzanVakti.bindString(8, vakitleriModel.MiladiTarihUzun);
                                    sqLiteStatementEzanVakti.bindString(9, vakitleriModel.HicriTarihUzun);
                                    sqLiteStatementEzanVakti.execute();

                                    System.out.println("vakitler" + vakitleriModel.Yatsi);




                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<List<EzanVakitleriModel>> call, Throwable t) {

                        t.printStackTrace();

                    }
                });

                getEzanVakti();

            }

        } catch (Exception e) {

            e.printStackTrace();

        }


    }

    public void getProfil () {

        String email = auth.getCurrentUser().getEmail();

        DocumentReference usdRef = firebaseFirestore.collection("User").document(email);
        usdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        binding.userNameText.setText((String) document.get("userName"));


                    } else {
                        System.out.println("Olumsuz");
                    }

                } else {

                    System.out.println("cekme başarısız");
                }

            }


        });





    }




    @Override
    public void onPause() {
        super.onPause();


    }




    @Override
    public void onResume() {
        super.onResume();



        try {

            sistemTarihiVoid();
            sistemSaatiVoid();
            getEzanVakti();
            getBildirimSound();
            DayInfoGet();
            getTomorrowVakit();
            getGunAsiriVakit();
            getfriendsCount();

        } catch (Exception e) {


        }

        if (auth.getCurrentUser() != null) {

            getProfil();


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




    public void getfriendsCount () {



        firebaseFirestore.collection("User").document(auth.getCurrentUser().getEmail()).collection("FriendsRequest").addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    Toast.makeText(getActivity(), "İnternet bağlantısında bir problem var", Toast.LENGTH_LONG).show();

                }

                if (value != null) {

                    for (DocumentSnapshot snapshot : value.getDocuments()) {

                        Map<String, Object> data = snapshot.getData();

                        String emailList = (String) data.get("email");

                        friendsRequestCount.add(emailList);

                        if (friendsRequestCount.size() > 0) {

                            binding.userNameText.setTextColor(Color.RED);
                            binding.userNameText.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner_red));

                        }


                    }


                }


            }

        });




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

    public void getTomorrowVakit () {

        vakitDatabase = getActivity().openOrCreateDatabase("EZANVAKITLERIDATA", Context.MODE_PRIVATE,null);

        try {

            Cursor cursor = vakitDatabase.rawQuery("SELECT * FROM ezanvakitleridatabase WHERE miladiKisa = ?", new String[]{"sistemTarihiTomorrowStr"});
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

                imsakVaktiTomorrow = cursor.getString(imsakVaktiIx);
                gunesVaktiTomorrow = cursor.getString(gunesVaktiIx);
                ogleVaktiTomorrow = cursor.getString(ogleVaktiIx);
                ikindiVaktiTomorrow = cursor.getString(ikindiVaktiIx);
                aksamVaktiTomorrow = cursor.getString(aksamVaktiIx);
                yatsiVaktiTomorrow = cursor.getString(yatsiVaktiIx);

                StringBuilder timeToday = new StringBuilder();
                timeToday.append(cursor.getString(miladiKisaIx));
                tomorrowStr = timeToday.toString();

            }

            cursor.close();

        } catch (Exception e) {

            e.printStackTrace();

        }


    }

    public void getGunAsiriVakit () {

        vakitDatabase = getActivity().openOrCreateDatabase("EZANVAKITLERIDATA", Context.MODE_PRIVATE,null);

        try {

            Cursor cursor = vakitDatabase.rawQuery("SELECT * FROM ezanvakitleridatabase WHERE miladiKisa = ?", new String[]{sistemTarihiGunAsiriStr});
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

                imsakVaktiGunAsiri = cursor.getString(imsakVaktiIx);
                gunesVaktiGunAsiri = cursor.getString(gunesVaktiIx);
                ogleVaktiGunAsiri = cursor.getString(ogleVaktiIx);
                ikindiVaktiGunAsiri = cursor.getString(ikindiVaktiIx);
                aksamVaktiGunAsiri= cursor.getString(aksamVaktiIx);
                yatsiVaktiGunAsiri = cursor.getString(yatsiVaktiIx);

                StringBuilder timeToday = new StringBuilder();
                timeToday.append(cursor.getString(miladiKisaIx));
                gunAsiriStr = timeToday.toString();

            }

            cursor.close();

        } catch (Exception e) {

            e.printStackTrace();

        }


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

        Integer GeceYarisiSaatInt = Integer.parseInt(splitgeceYarisi[0]);
        Integer GeceYarisiDakikaInt = Integer.parseInt(splitgeceYarisi[1]);
        Integer GeceYarisiSaniyeInt = Integer.parseInt(splitgeceYarisi[2]);


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

        SimpleDateFormat sistemtarih = new SimpleDateFormat("dd.MM.yyyy");
        Date sistemtarihi = new Date();
        sistemTarihiStr = sistemtarih.format(sistemtarihi);

        long bugunLong = sistemtarihi.getTime();

        long yarinLong = bugunLong + 86400000;
        SimpleDateFormat tomorrowFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date tomorrowDate = new Date(yarinLong);
        sistemTarihiTomorrowStr = tomorrowFormat.format(tomorrowDate);

        long gunAssiriLong = bugunLong + 86400000 * 2;
        SimpleDateFormat gunasiriFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date gunasiriDate = new Date(gunAssiriLong);
        sistemTarihiGunAsiriStr = gunasiriFormat.format(gunasiriDate);

    }

    public void sistemSaatiVoid() {

        SimpleDateFormat sistemSaat = new SimpleDateFormat("HH:mm:ss");

        Date sistemSaati = Calendar.getInstance().getTime();


        sistemSaatiStr = sistemSaat.format(sistemSaati);

        System.out.println("Saaat" + sistemSaatiStr);


    }

    public void vaktinHAdisi () {

        Random random = new Random();
        int a = random.nextInt(5);

        String[] vaktinHadisiArray = {"Bir müslüman, farz namazın vakti geldiğinde güzelce abdest alır, huşû içinde ve rükûunu da tam yaparak namazını kılarsa, büyük günah işlemedikçe, bu namaz önceki günahlarına keffâret olur. Bu her zaman böyledir. (Müslim, Tahâret 7)"
                , "İki serinlik namazını, sabah ve ikindiyi kılan kimse cennete girer. (Buhârî, Mevâkît 26"
                ,  "Sabah namazını kılan kimse Allah’ın himayesindedir. Dikkat et, ey Ademoğlu! Allah, bizzat himayesinde olan bir konuda seni sorguya çekmesin. (Müslim, Mesâcid 261-262)"
                , "İkindi namazını terkeden kimsenin işlediği amelleri boşa gider. (Buhârî, Mevâkît 15)"
                , "Cemaatle kılınan namaz, tek başına kılınan namazdan yirmi yedi derece daha faziletlidir. (Buhârî, Ezân 30)"
                , "İnsanlar yatsı namazı ile sabah namazındaki fazilet ve sevabı bilselerdi, emekleyerek bile olsa mutlaka camiye, cemaate gelirlerdi. (Buhârî, Ezân 9)"};

        vaktinHadisiStr = vaktinHadisiArray[a];



    }

    public void vaktinAyeti () {



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



        DocumentReference usdRef = firebaseFirestore.collection("DayInfo").document(sistemTarihiStr);
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

    public void bildirimGonderVaktinde(String titles, String sounds ,int notifyNum, long longdate, String aythds ){



        long currentLong = System.currentTimeMillis();

        if (longdate > currentLong || sounds.equals("Kapalı")) {

            vaktinHAdisi();
            vaktinAyeti();

            // Create Sounds Link
            StringBuilder SoundUrl = new StringBuilder();
            SoundUrl.append("/raw/");
            SoundUrl.append(sounds);


            if (aythds.equals("hadis")) {

                Intent intent = new Intent(getActivity(), ImsakVaktiBildirimReceiver.class);

                intent.putExtra("vImsakTitle", titles);
                intent.putExtra("vImsakDescription", vaktinHadisiStr);
                intent.putExtra("vImsakSound", sounds);
                intent.putExtra("vImsakNotifyNum", notifyNum);
                intent.putExtra("SourceAyetHadis", "Vaktin Hadisi");
                intent.putExtra("vImsakDescription2",vaktinAyetiStr);

                PendingIntent PendingEzan = PendingIntent.getBroadcast(getActivity(), notifyNum, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager ezanAlarmManager = (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);

                long along = System.currentTimeMillis();
                long timesss = 1000 * 5;

                ezanAlarmManager.set(AlarmManager.RTC_WAKEUP, longdate, PendingEzan);

                System.out.println(titles + "Ayarlandı");

            } else if (aythds.equals("ayet")) {

                Intent intent = new Intent(getActivity(), ImsakVaktiBildirimReceiver.class);

                intent.putExtra("vImsakTitle", titles);
                intent.putExtra("vImsakDescription", vaktinAyetiStr);
                intent.putExtra("vImsakSound", sounds);
                intent.putExtra("vImsakNotifyNum", notifyNum);
                intent.putExtra("SourceAyetHadis", "Vaktin Ayeti");
                intent.putExtra("vImsakDescription2",vaktinHadisiStr);

                PendingIntent PendingEzan = PendingIntent.getBroadcast(getActivity(), notifyNum, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager ezanAlarmManager = (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);

                long along = System.currentTimeMillis();
                long timesss = 1000 * 5;

                ezanAlarmManager.set(AlarmManager.RTC_WAKEUP, longdate, PendingEzan);


                System.out.println(titles + "Ayarlandı");

            }




        }


    }

    public void bildirimGonderVaktindenOnce(String titles, String sounds ,int notifyNum, long longdate, int beforeTime ) {



        long currentLong = System.currentTimeMillis();
        long beforeLong = 60000 * beforeTime;

        if (longdate - beforeLong  > currentLong || sounds.equals("Kapalı")) {

            // Create Sounds Link
            StringBuilder SoundUrl = new StringBuilder();
            SoundUrl.append("/raw/");
            SoundUrl.append(sounds);

            Intent intent = new Intent(getActivity(), ImsakOncesiBildirimReceiver.class);

            intent.putExtra("vOImsakTitle", titles);
            intent.putExtra("vOImsakDescription", "Vaktin çıkmasına " + beforeTime + " dakika kaldı.");
            intent.putExtra("vOImsakSound", sounds);
            intent.putExtra("vOImsakNotifyNum", notifyNum);

            PendingIntent PendingEzan = PendingIntent.getBroadcast(getActivity(), notifyNum,intent, PendingIntent.FLAG_CANCEL_CURRENT);

            AlarmManager ezanAlarmManager =  (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);

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

            SimpleDateFormat formattercalImsak = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendarImsak = Calendar.getInstance();
            calendarImsak.setTime(formattercalImsak.parse(imsakTime));
            longdateImsak = calendarImsak.getTimeInMillis();
            System.out.println("Tarih " + calendarImsak);
            System.out.println("TarihLong " + longdateImsak);


        } catch (Exception e) {

            System.out.println("imsak long alınamadı");

        }

        bildirimGonderVaktinde("İmsak Vakti", vImsakSesStr,1,longdateImsak,"hadis");
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

            SimpleDateFormat formattercalGunes = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendarGunes = Calendar.getInstance();
            calendarGunes.setTime(formattercalGunes.parse(gunesTime));
            longdateGunes = calendarGunes.getTimeInMillis();
            System.out.println("Tarih " + calendarGunes);
            System.out.println("TarihLong " + longdateGunes);

        } catch (Exception e) {

            System.out.println("gunes long alınamadı");

        }


        bildirimGonderVaktinde("Güneş Doğdu", vGunesSesStr,2,longdateGunes,"ayet");
        bildirimGonderVaktindenOnce("Güneş Vakti",vOImsakSesStr,8,longdateGunes,vOGunesSureInt);


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


            SimpleDateFormat formattercalOgle = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendarOgle = Calendar.getInstance();
            calendarOgle.setTime(formattercalOgle.parse(ogleTime));
            longdateOgle = calendarOgle.getTimeInMillis();
            System.out.println("Tarih " + calendarOgle);
            System.out.println("TarihLong " + longdateOgle);



        } catch (Exception e) {

            System.out.println("ogle long alınamadı");

        }


        bildirimGonderVaktinde("Öğle Vakti", vOgleSesStr,3,longdateOgle,"hadis");
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

            SimpleDateFormat formattercalIkindi = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendarIkindi = Calendar.getInstance();
            calendarIkindi.setTime(formattercalIkindi.parse(ikindiTime));
            longdateIkindi = calendarIkindi.getTimeInMillis();
            System.out.println("Tarih " + calendarIkindi);
            System.out.println("TarihLong " + longdateIkindi);


        } catch (Exception e) {

            System.out.println("ogle long alınamadı");

        }

        bildirimGonderVaktinde("İkindi Vakti", vIkindiSesStr,4,longdateIkindi,"ayet");
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

            SimpleDateFormat formattercalAksam = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendarAksam = Calendar.getInstance();
            calendarAksam.setTime(formattercalAksam.parse(aksamTime));
            longDateAksam = calendarAksam.getTimeInMillis();
            System.out.println("Tarih " + calendarAksam);
            System.out.println("TarihLong " + longDateAksam);

        } catch (Exception e) {

            System.out.println("ogle long alınamadı");

        }

        bildirimGonderVaktinde("Akşam Vakti", vAksamSesStr,5,longDateAksam,"hadis");
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

            SimpleDateFormat formattercalYatsi = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendarYatsi = Calendar.getInstance();
            calendarYatsi.setTime(formattercalYatsi.parse(yatsiTime));
            longDateYatsi = calendarYatsi.getTimeInMillis();
            System.out.println("Tarih " + calendarYatsi);
            System.out.println("TarihLong " + longDateYatsi);

        } catch (Exception e) {

            System.out.println("ogle long alınamadı");

        }


        bildirimGonderVaktinde("Yatsı Vakti", vYatsiSesStr,6,longDateYatsi,"ayet");
        bildirimGonderVaktindenOnce("Yatsı Vakti",vOYatsiSesStr,12,longDateYatsi,vOYatsiSureInt);


        // tomorrowImsak

        String[] tomorrowsplitImsak = tomorrowStr.split("[.]");

        StringBuilder tomorrowimsakTimeBuild = new StringBuilder();
        tomorrowimsakTimeBuild.append(tomorrowsplitImsak[2]);
        tomorrowimsakTimeBuild.append("-");
        tomorrowimsakTimeBuild.append(tomorrowsplitImsak[1]);
        tomorrowimsakTimeBuild.append("-");
        tomorrowimsakTimeBuild.append(tomorrowsplitImsak[0]);
        tomorrowimsakTimeBuild.append(" ");
        tomorrowimsakTimeBuild.append(imsakVaktiTomorrow);
        tomorrowimsakTimeBuild.append(":00");
        String tomorrowimsakTime = tomorrowimsakTimeBuild.toString();

        try {

            SimpleDateFormat formattercalImsaktomorrow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendarImsakTomorrow = Calendar.getInstance();
            calendarImsakTomorrow.setTime(formattercalImsaktomorrow.parse(tomorrowimsakTime));
            longdateImsakTomorrow = calendarImsakTomorrow.getTimeInMillis();
            System.out.println("Tarih " + calendarImsakTomorrow);
            System.out.println("TarihLong " + longdateImsakTomorrow);


        } catch (Exception e) {

            System.out.println("imsak tomorrow long alınamadı");

        }

        bildirimGonderVaktinde("İmsak Vakti", vImsakSesStr,13,longdateImsakTomorrow,"hadis");
        bildirimGonderVaktindenOnce("İmsak Vakti",vOImsakSesStr,14,longdateImsakTomorrow,vOImsakSureInt);


        // tomorrowGunes

        String[] tomorrowsplitGunes = tomorrowStr.split("[.]");

        StringBuilder tomorrowGunesTimeBuild = new StringBuilder();
        tomorrowGunesTimeBuild.append(tomorrowsplitGunes[2]);
        tomorrowGunesTimeBuild.append("-");
        tomorrowGunesTimeBuild.append(tomorrowsplitGunes[1]);
        tomorrowGunesTimeBuild.append("-");
        tomorrowGunesTimeBuild.append(tomorrowsplitGunes[0]);
        tomorrowGunesTimeBuild.append(" ");
        tomorrowGunesTimeBuild.append(gunesVaktiTomorrow);
        tomorrowGunesTimeBuild.append(":00");
        String tomorrowGunesTime = tomorrowGunesTimeBuild.toString();

        try {

            SimpleDateFormat formattercalGunestomorrow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendarGunesTomorrow = Calendar.getInstance();
            calendarGunesTomorrow.setTime(formattercalGunestomorrow.parse(tomorrowGunesTime));
            longdateGunesTomorrow = calendarGunesTomorrow.getTimeInMillis();
            System.out.println("Tarih " + calendarGunesTomorrow);
            System.out.println("TarihLong " + longdateGunesTomorrow);


        } catch (Exception e) {

            System.out.println("gunes tomorrow long alınamadı");

        }

        bildirimGonderVaktinde("Güneş Doğdu", vGunesSesStr,15,longdateGunesTomorrow,"ayet");
        bildirimGonderVaktindenOnce("Güneş Vakti",vOGunesSesStr,16,longdateGunesTomorrow,vOGunesSureInt);


        // tomorrowOgle

        String[] tomorrowsplitOgle = tomorrowStr.split("[.]");

        StringBuilder tomorrowOgleTimeBuild = new StringBuilder();
        tomorrowOgleTimeBuild.append(tomorrowsplitOgle[2]);
        tomorrowOgleTimeBuild.append("-");
        tomorrowOgleTimeBuild.append(tomorrowsplitOgle[1]);
        tomorrowOgleTimeBuild.append("-");
        tomorrowOgleTimeBuild.append(tomorrowsplitOgle[0]);
        tomorrowOgleTimeBuild.append(" ");
        tomorrowOgleTimeBuild.append(ogleVaktiTomorrow);
        tomorrowOgleTimeBuild.append(":00");
        String tomorroOgleTime = tomorrowOgleTimeBuild.toString();

        try {

            SimpleDateFormat formattercalOgletomorrow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendarOgleTomorrow = Calendar.getInstance();
            calendarOgleTomorrow.setTime(formattercalOgletomorrow.parse(tomorroOgleTime));
            longdateOgleTomorrow = calendarOgleTomorrow.getTimeInMillis();
            System.out.println("Tarih " + calendarOgleTomorrow);
            System.out.println("TarihLong " + longdateOgleTomorrow);


        } catch (Exception e) {

            System.out.println("gunes tomorrow long alınamadı");

        }

        bildirimGonderVaktinde("Öğle Vakti", vOgleSesStr,17,longdateOgleTomorrow,"hadis");
        bildirimGonderVaktindenOnce("Öğle Vakti",vOOgleSesStr,18,longdateOgleTomorrow,vOOgleSureInt);


        // TomorrowIkindiVaktiBildirim

        String[] tomorrowsplitIkindi = todayStr.split("[.]");

        StringBuilder tomorrowikindiTimeBuild = new StringBuilder();
        tomorrowikindiTimeBuild.append(tomorrowsplitIkindi[2]);
        tomorrowikindiTimeBuild.append("-");
        tomorrowikindiTimeBuild.append(tomorrowsplitIkindi[1]);
        tomorrowikindiTimeBuild.append("-");
        tomorrowikindiTimeBuild.append(tomorrowsplitIkindi[0]);
        tomorrowikindiTimeBuild.append(" ");
        tomorrowikindiTimeBuild.append(ikindiVaktiTomorrow);
        tomorrowikindiTimeBuild.append(":00");
        String tomorrowikindiTime = tomorrowikindiTimeBuild.toString();


        try {

            SimpleDateFormat formattercalIkindiTomorrow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendarIkindiTomorrow = Calendar.getInstance();
            calendarIkindiTomorrow.setTime(formattercalIkindiTomorrow.parse(tomorrowikindiTime));
            longdateIkindiTomorrow = calendarIkindiTomorrow.getTimeInMillis();
            System.out.println("Tarih " + calendarIkindiTomorrow);
            System.out.println("TarihLong " + longdateIkindiTomorrow);


        } catch (Exception e) {

            System.out.println("tomorrow ogle long alınamadı");

        }

        bildirimGonderVaktinde("İkindi Vakti", vIkindiSesStr,19,longdateIkindiTomorrow,"ayet");
        bildirimGonderVaktindenOnce("İkindi Vakti",vOIkindiSesStr,20,longdateIkindiTomorrow,vOIkindiSureInt);

        // tomorrow Aksam Vakti Bildirim

        String[] tomorrowsplitAksam = todayStr.split("[.]");

        StringBuilder tomorrowaksamTimeBuild = new StringBuilder();
        tomorrowaksamTimeBuild.append(tomorrowsplitAksam[2]);
        tomorrowaksamTimeBuild.append("-");
        tomorrowaksamTimeBuild.append(tomorrowsplitAksam[1]);
        tomorrowaksamTimeBuild.append("-");
        tomorrowaksamTimeBuild.append(tomorrowsplitAksam[0]);
        tomorrowaksamTimeBuild.append(" ");
        tomorrowaksamTimeBuild.append(aksamVaktiTomorrow);
        tomorrowaksamTimeBuild.append(":00");
        String tomorrowaksamTime = tomorrowaksamTimeBuild.toString();


        try {

            SimpleDateFormat formattercalAksamTomorrow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendarAksamTomorrow = Calendar.getInstance();
            calendarAksamTomorrow.setTime(formattercalAksamTomorrow.parse(tomorrowaksamTime));
            longDateAksamTomorrow = calendarAksamTomorrow.getTimeInMillis();
            System.out.println("Tarih " + calendarAksamTomorrow);
            System.out.println("TarihLong " + longDateAksamTomorrow);

        } catch (Exception e) {

            System.out.println("tomorrow Aksam long alınamadı");

        }

        bildirimGonderVaktinde("Akşam Vakti", vAksamSesStr,21,longDateAksamTomorrow,"hadis");
        bildirimGonderVaktindenOnce("Akşam Vakti",vOAksamSesStr,22,longDateAksamTomorrow,vOAksamSureInt);


        // tomorrow yatsiBildirim

        String[] tomorrowsplitYatsi = todayStr.split("[.]");

        StringBuilder tomorrowyatsiTimeBuild = new StringBuilder();
        tomorrowyatsiTimeBuild.append(tomorrowsplitYatsi[2]);
        tomorrowyatsiTimeBuild.append("-");
        tomorrowyatsiTimeBuild.append(tomorrowsplitYatsi[1]);
        tomorrowyatsiTimeBuild.append("-");
        tomorrowyatsiTimeBuild.append(tomorrowsplitYatsi[0]);
        tomorrowyatsiTimeBuild.append(" ");
        tomorrowyatsiTimeBuild.append(yatsiVaktiTomorrow);
        tomorrowyatsiTimeBuild.append(":00");
        String tomorrowyatsiTime = tomorrowyatsiTimeBuild.toString();

        try {

            SimpleDateFormat formattercalYatsiTomorrow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendarYatsiTomorrow = Calendar.getInstance();
            calendarYatsiTomorrow.setTime(formattercalYatsiTomorrow.parse(tomorrowyatsiTime));
            longDateYatsiTomorrow = calendarYatsiTomorrow.getTimeInMillis();
            System.out.println("Tarih " + calendarYatsiTomorrow);
            System.out.println("TarihLong " + longDateYatsiTomorrow);

        } catch (Exception e) {

            System.out.println("ogle long alınamadı");

        }


        bildirimGonderVaktinde("Yatsı Vakti", vYatsiSesStr,23,longDateYatsiTomorrow,"ayet");
        bildirimGonderVaktindenOnce("Yatsı Vakti",vOYatsiSesStr,24,longDateYatsiTomorrow,vOYatsiSureInt);



        // tomorrowImsak

        String[] gunasirisplitImsak = gunAsiriStr.split("[.]");

        StringBuilder gunasiriimsakTimeBuild = new StringBuilder();
        gunasiriimsakTimeBuild.append(gunasirisplitImsak[2]);
        gunasiriimsakTimeBuild.append("-");
        gunasiriimsakTimeBuild.append(gunasirisplitImsak[1]);
        gunasiriimsakTimeBuild.append("-");
        gunasiriimsakTimeBuild.append(gunasirisplitImsak[0]);
        gunasiriimsakTimeBuild.append(" ");
        gunasiriimsakTimeBuild.append(imsakVaktiGunAsiri);
        gunasiriimsakTimeBuild.append(":00");
        String gunasiriimsakTime = gunasiriimsakTimeBuild.toString();

        try {

            SimpleDateFormat formattercalImsakGunasiri = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendarImsakGunasiri = Calendar.getInstance();
            calendarImsakGunasiri.setTime(formattercalImsakGunasiri.parse(gunasiriimsakTime));
            longdateImsakGunAsiri = calendarImsakGunasiri.getTimeInMillis();
            System.out.println("Tarih " + calendarImsakGunasiri);
            System.out.println("TarihLong " + longdateImsakGunAsiri);


        } catch (Exception e) {

            System.out.println("imsak gunasiri long alınamadı");

        }

        bildirimGonderVaktinde("İmsak Vakti", vImsakSesStr,25,longdateImsakGunAsiri,"hadis");
        bildirimGonderVaktindenOnce("İmsak Vakti",vOImsakSesStr,26,longdateImsakGunAsiri,vOImsakSureInt);


        // tomorrowGunes

        String[] gunasirisplitGunes = tomorrowStr.split("[.]");

        StringBuilder gunasiriGunesTimeBuild = new StringBuilder();
        gunasiriGunesTimeBuild.append(gunasirisplitGunes[2]);
        gunasiriGunesTimeBuild.append("-");
        gunasiriGunesTimeBuild.append(gunasirisplitGunes[1]);
        gunasiriGunesTimeBuild.append("-");
        gunasiriGunesTimeBuild.append(gunasirisplitGunes[0]);
        gunasiriGunesTimeBuild.append(" ");
        gunasiriGunesTimeBuild.append(gunesVaktiTomorrow);
        gunasiriGunesTimeBuild.append(":00");
        String gunasiriGunesTime = gunasiriGunesTimeBuild.toString();

        try {

            SimpleDateFormat formattercalGunesgunasiri = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendarGunesGunasiri = Calendar.getInstance();
            calendarGunesGunasiri.setTime(formattercalGunesgunasiri.parse(gunasiriGunesTime));
            longdateGunesGunAsiri = calendarGunesGunasiri.getTimeInMillis();
            System.out.println("Tarih " + calendarGunesGunasiri);
            System.out.println("TarihLong " + longdateGunesGunAsiri);


        } catch (Exception e) {

            System.out.println("gunes gunasirilong alınamadı");

        }

        bildirimGonderVaktinde("Güneş Doğdu", vGunesSesStr,27,longdateGunesGunAsiri,"ayet");
        bildirimGonderVaktindenOnce("Güneş Vakti",vOGunesSesStr,28,longdateGunesGunAsiri,vOGunesSureInt);


        // tomorrowOgle

        String[] gunasirisplitOgle = tomorrowStr.split("[.]");

        StringBuilder gunasiriOgleTimeBuild = new StringBuilder();
        gunasiriOgleTimeBuild.append(gunasirisplitOgle[2]);
        gunasiriOgleTimeBuild.append("-");
        gunasiriOgleTimeBuild.append(gunasirisplitOgle[1]);
        gunasiriOgleTimeBuild.append("-");
        gunasiriOgleTimeBuild.append(gunasirisplitOgle[0]);
        gunasiriOgleTimeBuild.append(" ");
        gunasiriOgleTimeBuild.append(ogleVaktiTomorrow);
        gunasiriOgleTimeBuild.append(":00");
        String gunasiriOgleTime = gunasiriOgleTimeBuild.toString();

        try {

            SimpleDateFormat formattercalOglegunasiri = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendarOgleGunasiri = Calendar.getInstance();
            calendarOgleGunasiri.setTime(formattercalOglegunasiri.parse(gunasiriOgleTime));
            longdateOgleGunAsiri = calendarOgleGunasiri.getTimeInMillis();
            System.out.println("Tarih " + calendarOgleGunasiri);
            System.out.println("TarihLong " + longdateOgleGunAsiri);


        } catch (Exception e) {

            System.out.println("gunes guneasiri long alınamadı");

        }

        bildirimGonderVaktinde("Öğle Vakti", vOgleSesStr,29,longdateOgleGunAsiri,"hadis");
        bildirimGonderVaktindenOnce("Öğle Vakti",vOOgleSesStr,30,longdateOgleGunAsiri,vOOgleSureInt);


        // TomorrowIkindiVaktiBildirim

        String[] gunasirisplitIkindi = todayStr.split("[.]");

        StringBuilder gunasiriikindiTimeBuild = new StringBuilder();
        gunasiriikindiTimeBuild.append(gunasirisplitIkindi[2]);
        gunasiriikindiTimeBuild.append("-");
        gunasiriikindiTimeBuild.append(gunasirisplitIkindi[1]);
        gunasiriikindiTimeBuild.append("-");
        gunasiriikindiTimeBuild.append(gunasirisplitIkindi[0]);
        gunasiriikindiTimeBuild.append(" ");
        gunasiriikindiTimeBuild.append(ikindiVaktiTomorrow);
        gunasiriikindiTimeBuild.append(":00");
        String gunasiriikindiTime = gunasiriikindiTimeBuild.toString();


        try {

            SimpleDateFormat formattercalIkindiGunasiri = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendarIkindiGunasiri = Calendar.getInstance();
            calendarIkindiGunasiri.setTime(formattercalIkindiGunasiri.parse(gunasiriikindiTime));
            longdateIkindiGunAsiri = calendarIkindiGunasiri.getTimeInMillis();
            System.out.println("Tarih " + calendarIkindiGunasiri);
            System.out.println("TarihLong " + longdateIkindiGunAsiri);


        } catch (Exception e) {

            System.out.println("gunasiri ogle long alınamadı");

        }

        bildirimGonderVaktinde("İkindi Vakti", vIkindiSesStr,31,longdateIkindiGunAsiri,"ayet");
        bildirimGonderVaktindenOnce("İkindi Vakti",vOIkindiSesStr,32,longdateIkindiGunAsiri,vOIkindiSureInt);

        // tomorrow Aksam Vakti Bildirim

        String[] gunasirisplitAksam = todayStr.split("[.]");

        StringBuilder gunasiriaksamTimeBuild = new StringBuilder();
        gunasiriaksamTimeBuild.append(gunasirisplitAksam[2]);
        gunasiriaksamTimeBuild.append("-");
        gunasiriaksamTimeBuild.append(gunasirisplitAksam[1]);
        gunasiriaksamTimeBuild.append("-");
        gunasiriaksamTimeBuild.append(gunasirisplitAksam[0]);
        gunasiriaksamTimeBuild.append(" ");
        gunasiriaksamTimeBuild.append(aksamVaktiTomorrow);
        gunasiriaksamTimeBuild.append(":00");
        String gunasiriaksamTime = gunasiriaksamTimeBuild.toString();


        try {

            SimpleDateFormat formattercalAksamgunasiri = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendarAksamGunasiri = Calendar.getInstance();
            calendarAksamGunasiri.setTime(formattercalAksamgunasiri.parse(gunasiriaksamTime));
            longDateAksamGunAsiri = calendarAksamGunasiri.getTimeInMillis();
            System.out.println("Tarih " + calendarAksamGunasiri);
            System.out.println("TarihLong " + longDateAksamGunAsiri);

        } catch (Exception e) {

            System.out.println("gunasiri Aksam long alınamadı");

        }

        bildirimGonderVaktinde("Akşam Vakti", vAksamSesStr,33,longDateAksamGunAsiri,"hadis");
        bildirimGonderVaktindenOnce("Akşam Vakti",vOAksamSesStr,34,longDateAksamGunAsiri,vOAksamSureInt);


        // tomorrow yatsiBildirim

        String[] gunasirisplitYatsi = todayStr.split("[.]");

        StringBuilder gunasiriyatsiTimeBuild = new StringBuilder();
        gunasiriyatsiTimeBuild.append(gunasirisplitYatsi[2]);
        gunasiriyatsiTimeBuild.append("-");
        gunasiriyatsiTimeBuild.append(gunasirisplitYatsi[1]);
        gunasiriyatsiTimeBuild.append("-");
        gunasiriyatsiTimeBuild.append(gunasirisplitYatsi[0]);
        gunasiriyatsiTimeBuild.append(" ");
        gunasiriyatsiTimeBuild.append(yatsiVaktiTomorrow);
        gunasiriyatsiTimeBuild.append(":00");
        String gunasiriyatsiTime = gunasiriyatsiTimeBuild.toString();

        try {

            SimpleDateFormat formattercalYatsiGunasiri = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendarYatsiGunAsiri = Calendar.getInstance();
            calendarYatsiGunAsiri.setTime(formattercalYatsiGunasiri.parse(gunasiriyatsiTime));
            longDateYatsiGunAsiri = calendarYatsiGunAsiri.getTimeInMillis();
            System.out.println("Tarih " + calendarYatsiGunAsiri);
            System.out.println("TarihLong " + longDateYatsiGunAsiri);

        } catch (Exception e) {

            System.out.println("gunasiri yatsi long alınamadı");

        }


        bildirimGonderVaktinde("Yatsı Vakti", vYatsiSesStr,35,longDateYatsiGunAsiri,"ayet");
        bildirimGonderVaktindenOnce("Yatsı Vakti",vOYatsiSesStr,36,longDateYatsiGunAsiri,vOYatsiSureInt);



    }




}