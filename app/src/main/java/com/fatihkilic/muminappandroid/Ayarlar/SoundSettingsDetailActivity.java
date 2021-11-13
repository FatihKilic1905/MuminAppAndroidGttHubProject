package com.fatihkilic.muminappandroid.Ayarlar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityAyarlarBinding;
import com.fatihkilic.muminappandroid.databinding.ActivitySoundSettingsDetailBinding;;

public class SoundSettingsDetailActivity extends AppCompatActivity {

    String VakitInfo;
    private ActivitySoundSettingsDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_settings_detail);

        binding = ActivitySoundSettingsDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        Intent VakitIntent = getIntent();
        VakitInfo = VakitIntent.getStringExtra("VakitInfo");

        getSupportActionBar().setTitle(VakitInfo);




    }
}