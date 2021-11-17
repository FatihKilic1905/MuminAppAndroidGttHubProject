package com.fatihkilic.muminappandroid.Ayarlar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.Switch;

import com.fatihkilic.muminappandroid.MainActivity;
import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivitySoundSettingsDetailBinding;;

public class SoundSettingsDetailActivity extends AppCompatActivity {

    String VakitInfo;
    private ActivitySoundSettingsDetailBinding binding;
    Switch anahtarVaktinde;
    SharedPreferences sharedPreferences;
    String VOSesString;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_settings_detail);

        sharedPreferences = this.getSharedPreferences("com.fatihkilic.muminappandroid", Context.MODE_PRIVATE);

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

        Button VOSesSecButton = binding.vaktindenOnceSoundSelectButton;

        VOSesSecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.soundPickerList.setVisibility(View.VISIBLE);
                binding.secimiTamamlaButton.setVisibility(View.VISIBLE);

                System.out.println("Fatih");


            }
        });

        Button secimiTamamlaButton = binding.secimiTamamlaButton;

        secimiTamamlaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.soundPickerList.setVisibility(View.INVISIBLE);
                binding.secimiTamamlaButton.setVisibility(View.INVISIBLE);

            }
        });

        Button kaydetButton = binding.kaydetButton;
        kaydetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (VakitInfo.equals("İmsak Vakti") && VOSesString.equals("Kuş Sesi")) {

                    System.out.println("vakit11" + VakitInfo);
                    System.out.println("vakit22" + VOSesString);

                    sharedPreferences.edit().putString("imsakVOSes", "kusSesi").apply();

                } else if (VakitInfo.equals("İmsak Vakti") && VOSesString.equals("Kuş Sesi 2"))  {

                    sharedPreferences.edit().putString("imsakVOSes", "kusSesi2").apply();

                }


                Intent sesKaydetIntent = new Intent(SoundSettingsDetailActivity.this, MainActivity.class);
                startActivity(sesKaydetIntent);

                }

        });


        binding.soundPickerList.setVisibility(View.INVISIBLE);
        binding.secimiTamamlaButton.setVisibility(View.INVISIBLE);
        EzanSoundList.initEzanSound();

        binding.soundPickerList.setMaxValue(EzanSoundList.getEzanSoundListArray().size() - 1);
        binding.soundPickerList.setMinValue(0);
        binding.soundPickerList.setDisplayedValues(EzanSoundList.ezanNames());

        binding.soundPickerList.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                binding.vaktindenOnceSoundTitle.setText(EzanSoundList.getEzanSoundListArray().get(newVal).getSoundName());
                VOSesString = EzanSoundList.getEzanSoundListArray().get(newVal).getSoundName();
                System.out.println("Ses" + VOSesString + VakitInfo);


            }
        });






        Intent VakitIntent = getIntent();
        VakitInfo = VakitIntent.getStringExtra("VakitInfo");

        getSupportActionBar().setTitle(VakitInfo);


        //binding.vaktindenOnceBaslikBack.setBackground(ContextCompat.getDrawable(this, R.drawable.corner_layer_top_2_radius_red));

        binding.vaktindeSoundSelectButton.setBackgroundColor(Color.rgb(0,149,254));








    }





}