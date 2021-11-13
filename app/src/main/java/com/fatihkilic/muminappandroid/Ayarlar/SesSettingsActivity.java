package com.fatihkilic.muminappandroid.Ayarlar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fatihkilic.muminappandroid.R;

public class SesSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ses_settings);

        getSupportActionBar().setTitle("Ses Ayarları");


    }


    public void imsakSoundSettingsButton (View view) {

        String vakitInfo = "İmsak Vakti";

        Intent soundSettingIntent = new Intent(SesSettingsActivity.this,SoundSettingsDetailActivity.class);
        soundSettingIntent.putExtra("VakitInfo",vakitInfo);
        startActivity(soundSettingIntent);


    }

    public void gunesSoundSettingsButton (View view) {

        String vakitInfo = "Güneş Vakti";

        Intent soundSettingIntent = new Intent(SesSettingsActivity.this,SoundSettingsDetailActivity.class);
        soundSettingIntent.putExtra("VakitInfo",vakitInfo);
        startActivity(soundSettingIntent);


    }
    public void ogleSoundSettingsButton (View view) {

        String vakitInfo = "Öğle Vakti";

        Intent soundSettingIntent = new Intent(SesSettingsActivity.this,SoundSettingsDetailActivity.class);
        soundSettingIntent.putExtra("VakitInfo",vakitInfo);
        startActivity(soundSettingIntent);


    }

    public void ikindiSoundSettingsButton (View view) {

        String vakitInfo = "İkindi Vakti";

        Intent soundSettingIntent = new Intent(SesSettingsActivity.this,SoundSettingsDetailActivity.class);
        soundSettingIntent.putExtra("VakitInfo",vakitInfo);
        startActivity(soundSettingIntent);


    }

    public void aksamSoundSettingsButton (View view) {

        String vakitInfo = "Akşam Vakti";

        Intent soundSettingIntent = new Intent(SesSettingsActivity.this,SoundSettingsDetailActivity.class);
        soundSettingIntent.putExtra("VakitInfo",vakitInfo);
        startActivity(soundSettingIntent);


    }

    public void yatsiSoundSettingsButton (View view) {

        String vakitInfo = "Yatsı Vakti";

        Intent soundSettingIntent = new Intent(SesSettingsActivity.this,SoundSettingsDetailActivity.class);
        soundSettingIntent.putExtra("VakitInfo",vakitInfo);
        startActivity(soundSettingIntent);


    }



}