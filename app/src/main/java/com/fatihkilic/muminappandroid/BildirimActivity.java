package com.fatihkilic.muminappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.fatihkilic.muminappandroid.databinding.ActivityBildirimBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityHtmDuasiBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class BildirimActivity extends AppCompatActivity {

    private ActivityBildirimBinding binding;
    private AdView mAdView;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bildirim);

        sharedPreferences = this.getSharedPreferences("com.fatihkilic.muminappandroid", Context.MODE_PRIVATE);

        binding = ActivityBildirimBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().setTitle("");


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = binding.adView;
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Intent getIntent = getIntent();
        binding.vaktinAHTitleTextView.setText(getIntent.getStringExtra("InfoNot"));
        binding.vaktinAHTextView.setText(getIntent.getStringExtra("DescNot"));


    }
}