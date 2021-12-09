package com.fatihkilic.muminappandroid.Ayarlar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.Ulkeler.KonumActivity;
import com.fatihkilic.muminappandroid.databinding.ActivityAyarlarBinding;

public class AyarlarActivity extends AppCompatActivity {


    private ActivityAyarlarBinding binding;
    WebView kabiLiveEwbView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayarlar);

        binding = ActivityAyarlarBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().setTitle("Ayarlar");



    }

    public void sesSettinsOnClick (View view) {


        Intent sesSettingsIntent = new Intent(getApplicationContext(), SesSettingsActivity.class);
        startActivity(sesSettingsIntent);

    }


    public void konumSettigsOnClick (View view) {


        Intent konumSettingsIntent = new Intent(getApplicationContext(), KonumActivity.class);
        startActivity(konumSettingsIntent);

    }




}