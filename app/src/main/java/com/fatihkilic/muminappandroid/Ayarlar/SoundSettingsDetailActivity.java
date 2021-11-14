package com.fatihkilic.muminappandroid.Ayarlar;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.Switch;

import com.fatihkilic.muminappandroid.EzanSoundList;
import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivitySoundSettingsDetailBinding;;

public class SoundSettingsDetailActivity extends AppCompatActivity {

    String VakitInfo;
    private ActivitySoundSettingsDetailBinding binding;
    Switch anahtarVaktinde;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_settings_detail);

        binding = ActivitySoundSettingsDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        anahtarVaktinde = binding.vaktindenOnceSwitch;
        binding.vaktindenOnceSwitch.setChecked(true);

        anahtarVaktinde.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                   // binding.vaktindenOnceBaslikBack.setBackground(ContextCompat.getDrawable(Context, R.drawable.corner_layer_top_2_radius_red));

                    binding.vaktindenOnceSoundSelectButton.setBackgroundColor(Color.rgb(119,195,68));
                    binding.vaktindenOnceSoundTitle.setText("Sound");
                    binding.vaktindenOnceSoundSelectButton.setEnabled(true);


                } else {

                    binding.vaktindenOnceSoundSelectButton.setBackgroundColor(Color.rgb(255,0,0));
                    binding.vaktindenOnceSoundTitle.setText("Kapalı");
                    binding.vaktindenOnceSoundSelectButton.setEnabled(false);


                }


            }
        });

        EzanSoundList.initEzanSound();

        binding.soundPickerList.setMaxValue(EzanSoundList.getEzanSoundListArray().size() - 1);
        binding.soundPickerList.setMinValue(0);
        binding.soundPickerList.setDisplayedValues(EzanSoundList.ezanNames());

        binding.soundPickerList.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });




        Intent VakitIntent = getIntent();
        VakitInfo = VakitIntent.getStringExtra("VakitInfo");

        getSupportActionBar().setTitle(VakitInfo);


        //binding.vaktindenOnceBaslikBack.setBackground(ContextCompat.getDrawable(this, R.drawable.corner_layer_top_2_radius_red));

        binding.vaktindeSoundSelectButton.setBackgroundColor(Color.rgb(0,149,254));








    }





}