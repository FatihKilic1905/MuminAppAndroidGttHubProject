package com.fatihkilic.muminappandroid.Ayarlar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
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
    String VaktindeSesString;
    String VOControl;
    MediaPlayer EzanSound1;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_settings_detail);

        Intent VakitIntent = getIntent();
        VakitInfo = VakitIntent.getStringExtra("VakitInfo");

        getSupportActionBar().setTitle(VakitInfo);

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

                VOControl = "VO";
                System.out.println(VOControl);

                EzanSound1 = MediaPlayer.create(SoundSettingsDetailActivity.this, R.raw.kussesi1);

                EzanSound1.start();

            }
        });

        Button VSesSecButton = binding.vaktindeSoundSelectButton;

        VSesSecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.soundPickerList.setVisibility(View.VISIBLE);
                binding.secimiTamamlaButton.setVisibility(View.VISIBLE);

                VOControl = "V";

                System.out.println(VOControl);

                EzanSound1 = MediaPlayer.create(SoundSettingsDetailActivity.this, R.raw.kussesi1);

                EzanSound1.start();

            }
        });

        Button secimiTamamlaButton = binding.secimiTamamlaButton;

        secimiTamamlaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.soundPickerList.setVisibility(View.INVISIBLE);
                binding.secimiTamamlaButton.setVisibility(View.INVISIBLE);
                EzanSound1.stop();

            }
        });

        Button kaydetButton = binding.kaydetButton;
        kaydetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (VakitInfo.equals("İmsak Vakti") && VOSesString.equals("Kuş Sesi")) {

                    sharedPreferences.edit().putString("imsakVOSes", "kussesi1.mp3").apply();



                } else if (VakitInfo.equals("İmsak Vakti") && VOSesString.equals("Kuş Sesi 2"))  {

                    sharedPreferences.edit().putString("imsakVOSes", "kussesi2.mp3").apply();



                } else if (VakitInfo.equals("İmsak Vakti") && VOSesString.equals("Ahmad Al Nafees")) {

                    sharedPreferences.edit().putString("imsakVOSes", "ahmadalnafes.mp3").apply();



                } else if  (VakitInfo.equals("İmsak Vakti") && VOSesString.equals("Masjid Al Haram")) {

                    sharedPreferences.edit().putString("imsakVOSes", "masjidalharam.mp3").apply();



                } else if  (VakitInfo.equals("İmsak Vakti") && VOSesString.equals("Mishary Rashid Alafasy")) {

                    sharedPreferences.edit().putString("imsakVOSes", "misharyrasidalafasy.mp3").apply();



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

                if (VOControl.equals("VO")) {

                    binding.vaktindenOnceSoundTitle.setText(EzanSoundList.getEzanSoundListArray().get(newVal).getSoundName());
                    VOSesString = EzanSoundList.getEzanSoundListArray().get(newVal).getSoundName();

                    if (VOSesString.equals("Kuş Sesi")) {

                        EzanSound1.stop();

                        EzanSound1 = MediaPlayer.create(SoundSettingsDetailActivity.this, R.raw.kussesi1);

                        EzanSound1.start();

                    } else if (VOSesString.equals("Kuş Sesi 2"))  {
                        EzanSound1.stop();

                        EzanSound1 = MediaPlayer.create(SoundSettingsDetailActivity.this, R.raw.kussesi2);

                        EzanSound1.start();

                    } else if (VOSesString.equals("Ahmad Al Nafees")) {

                        EzanSound1.stop();

                        EzanSound1 = MediaPlayer.create(SoundSettingsDetailActivity.this, R.raw.ahmadalnafes);

                        EzanSound1.start();

                    } else if  (VOSesString.equals("Masjid Al Haram")) {

                        EzanSound1.stop();

                        EzanSound1 = MediaPlayer.create(SoundSettingsDetailActivity.this, R.raw.masjidalharam);

                        EzanSound1.start();

                    } else if  (VOSesString.equals("Mishary Rashid Alafasy")) {

                        EzanSound1.stop();

                        EzanSound1 = MediaPlayer.create(SoundSettingsDetailActivity.this, R.raw.misharyrasidalafasy);

                        EzanSound1.start();

                    }

                } else if (VOControl.equals("V")) {

                    binding.vaktindeSoundTitle.setText(EzanSoundList.getEzanSoundListArray().get(newVal).getSoundName());
                    VaktindeSesString = EzanSoundList.getEzanSoundListArray().get(newVal).getSoundName();

                    if (VaktindeSesString.equals("Kuş Sesi")) {

                        EzanSound1.stop();

                        EzanSound1 = MediaPlayer.create(SoundSettingsDetailActivity.this, R.raw.kussesi1);

                        EzanSound1.start();

                    } else if (VaktindeSesString.equals("Kuş Sesi 2"))  {
                        EzanSound1.stop();

                        EzanSound1 = MediaPlayer.create(SoundSettingsDetailActivity.this, R.raw.kussesi2);

                        EzanSound1.start();

                    } else if (VaktindeSesString.equals("Ahmad Al Nafees")) {

                        EzanSound1.stop();

                        EzanSound1 = MediaPlayer.create(SoundSettingsDetailActivity.this, R.raw.ahmadalnafes);

                        EzanSound1.start();

                    } else if  (VaktindeSesString.equals("Masjid Al Haram")) {

                        EzanSound1.stop();

                        EzanSound1 = MediaPlayer.create(SoundSettingsDetailActivity.this, R.raw.masjidalharam);

                        EzanSound1.start();

                    } else if  (VaktindeSesString.equals("Mishary Rashid Alafasy")) {

                        EzanSound1.stop();

                        EzanSound1 = MediaPlayer.create(SoundSettingsDetailActivity.this, R.raw.misharyrasidalafasy);

                        EzanSound1.start();

                    }
                }



            }
        });





        //binding.vaktindenOnceBaslikBack.setBackground(ContextCompat.getDrawable(this, R.drawable.corner_layer_top_2_radius_red));










    }





}