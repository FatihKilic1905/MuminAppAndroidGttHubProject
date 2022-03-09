package com.fatihkilic.muminappandroid.Kutuphane;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityKuraniKerimBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityKuraniKerimMainBinding;

public class KuraniKerimActivity extends AppCompatActivity {




    private ActivityKuraniKerimBinding binding;


    static String kategoriName;
    static String surahName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kurani_kerim);


        binding = ActivityKuraniKerimBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent kategoriIntent = getIntent();
        surahName = kategoriIntent.getStringExtra("SureName");
        kategoriName = kategoriIntent.getStringExtra("KategoriName");



    }


    public void getSure () {








    }






}