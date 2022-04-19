package com.fatihkilic.muminappandroid.DayInfo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.applovin.mediation.ads.MaxAdView;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityDayInfoBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityZekatMatikBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class DayInfoActivity extends AppCompatActivity {

    private ActivityDayInfoBinding binding;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<mealOfDayModel> mealOfDayModelArrayList;
    mealOfDayAdapter mealOfDayAdapter;
    String getDetailınfo;

    String sistemTarihiStr;


    ArrayList<todayInHistoryModel> todayInHistoryArrayList;
    todayInHistoryAdapter todayInHistoryAdapter;

    private MaxAdView adView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_info);

        binding = ActivityDayInfoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        sistemTarihiVoid();

        mealOfDayModelArrayList = new ArrayList<>();
        todayInHistoryArrayList = new ArrayList<>();

        firebaseFirestore = FirebaseFirestore.getInstance();

        Intent getDetailınfoIntent = getIntent();
        getDetailınfo = getDetailınfoIntent.getStringExtra("DayInfoPageInfo");

        AppLovinSdk.getInstance( this ).setMediationProvider( "max" );
        AppLovinSdk.initializeSdk( this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {



                createBannerAd();


            }
        } );



        if (getDetailınfo.equals("Meal")) {

            binding.MealRecyclerView.setVisibility(View.VISIBLE);
            binding.todayInHistoryRecylerView.setVisibility(View.INVISIBLE);

        } else if (getDetailınfo.equals("Tih")) {

            binding.todayInHistoryRecylerView.setVisibility(View.VISIBLE);
            binding.MealRecyclerView.setVisibility(View.INVISIBLE);

        }

        getMealOfTheDay();
        binding.MealRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mealOfDayAdapter = new mealOfDayAdapter(mealOfDayModelArrayList);
        binding.MealRecyclerView.setAdapter(mealOfDayAdapter);

        getTodayInHistory();
        binding.todayInHistoryRecylerView.setLayoutManager(new LinearLayoutManager(this));
        todayInHistoryAdapter = new todayInHistoryAdapter(todayInHistoryArrayList);
        binding.todayInHistoryRecylerView.setAdapter(todayInHistoryAdapter);


    }

    private void createBannerAd() {


        adView = new MaxAdView( "b06d01f284423f8f", this );


        // Stretch to the width of the screen for banners to be fully functional
        int width = ViewGroup.LayoutParams.MATCH_PARENT;

        // Banner height on phones and tablets is 50 and 90, respectively
        int heightPx = getResources().getDimensionPixelSize( R.dimen.banner_height );

        adView.setLayoutParams( new FrameLayout.LayoutParams( width, heightPx ) );

        // Set background or background color for banners to be fully functional


        ViewGroup rootView = binding.maxAdView;
        rootView.addView( adView );

        // Load the ad
        adView.loadAd();



    }


    public void getMealOfTheDay () {

        System.out.println("day2"+sistemTarihiStr);

        firebaseFirestore.collection("DayInfo").document(sistemTarihiStr).collection("recipe").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    System.out.println("Yemekleri çekemiyor");

                }

                if (value != null) {

                    for (DocumentSnapshot snapshot : value.getDocuments()) {

                        Map<String, Object> data = snapshot.getData();


                        String eatMaterials = (String) data.get("eatMaterials");
                        String eatName = (String) data.get("eatName");
                        String eatPerson = (String) data.get("eatPerson");
                        String eatRecipe = (String) data.get("eatRecipe");
                        String eatTime = (String) data.get("eatTime");
                        String eatUrl = (String) data.get("eatUrl");
                        String eatImage = (String) data.get("eatImage");

                        mealOfDayModel mealOfDay = new mealOfDayModel(eatMaterials,eatName,eatPerson,eatRecipe,eatTime,eatUrl,eatImage);

                        mealOfDayModelArrayList.add(mealOfDay);


                    }

                    mealOfDayAdapter.notifyDataSetChanged();

                }

            }
        });

    }


    public void getTodayInHistory () {


        firebaseFirestore.collection("DayInfo").document(sistemTarihiStr).collection("todayInHistory").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    System.out.println("Tarihi Olayları çekemiyor");

                }

                if (value != null) {

                    for (DocumentSnapshot snapshot : value.getDocuments()) {

                        Map<String, Object> data = snapshot.getData();


                        String tihDescription = (String) data.get("tihDescription");
                        String tihYear = (String) data.get("tihYear");



                        todayInHistoryModel todayInHistory = new todayInHistoryModel(tihDescription,tihYear);

                        todayInHistoryArrayList.add(todayInHistory);


                    }

                    todayInHistoryAdapter.notifyDataSetChanged();

                }

            }
        });



    }

    void sistemTarihiVoid() {

        SimpleDateFormat sistemtarih = new SimpleDateFormat("dd.MM.yyyy");
        Date sistemtarihi = new Date();
        sistemTarihiStr = sistemtarih.format(sistemtarihi);



    }

}