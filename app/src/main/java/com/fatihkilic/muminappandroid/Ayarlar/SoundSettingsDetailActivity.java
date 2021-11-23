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
import com.fatihkilic.muminappandroid.databinding.ActivitySoundSettingsDetailBinding;;import java.util.DoubleSummaryStatistics;

public class SoundSettingsDetailActivity extends AppCompatActivity {

    String VakitInfo;

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

    String mainvOTitle;
    String mainVtitle;


    private ActivitySoundSettingsDetailBinding binding;
    Switch anahtarVaktinde;
    Switch anahtarVaktindenOnce;
    SharedPreferences sharedPreferences;

    String VOSesString;
    String VaktindeSesString;
    int VOsureInt;

    String VOControl;
    MediaPlayer EzanSound1;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_settings_detail);

        binding = ActivitySoundSettingsDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent VakitIntent = getIntent();
        VakitInfo = VakitIntent.getStringExtra("VakitInfo");

        sharedPreferences = this.getSharedPreferences("com.fatihkilic.muminappandroid", Context.MODE_PRIVATE);

        getSupportActionBar().setTitle(VakitInfo);


        binding.soundPickerList.setVisibility(View.INVISIBLE);
        binding.secimiTamamlaButton.setVisibility(View.INVISIBLE);
        binding.surePickerList.setVisibility(View.INVISIBLE);
        binding.pickerListBack.setVisibility(View.INVISIBLE);

        EzanSoundList.initEzanSound();

        binding.soundPickerList.setMaxValue(EzanSoundList.getEzanSoundListArray().size() - 1);
        binding.soundPickerList.setMinValue(0);
        binding.soundPickerList.setDisplayedValues(EzanSoundList.ezanNames());

        binding.surePickerList.setMaxValue(60);
        binding.surePickerList.setMinValue(5);
        binding.surePickerList.setValue(45);

        anahtarVaktinde = binding.vaktindeSwitch;
        anahtarVaktindenOnce = binding.vaktindenOnceSwitch;
        binding.vaktindenOnceSwitch.setChecked(true);
        binding.vaktindeSwitch.setChecked(true);

        anahtarVaktindenOnce.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    binding.vaktindenOnceSoundTitle.setText(mainvOTitle);
                    binding.vaktindenOnceSoundSelectButton.setVisibility(View.VISIBLE);
                    binding.minuteSelectButton.setVisibility(View.VISIBLE);
                    binding.minuteTitle.setVisibility(View.VISIBLE);
                    binding.dakikaOnceTitle.setVisibility(View.VISIBLE);

                } else {

                    binding.vaktindenOnceSoundTitle.setText("Kapalı");
                    binding.vaktindenOnceSoundSelectButton.setVisibility(View.INVISIBLE);
                    binding.minuteSelectButton.setVisibility(View.INVISIBLE);
                    binding.minuteTitle.setVisibility(View.INVISIBLE);
                    binding.dakikaOnceTitle.setVisibility(View.INVISIBLE);

                }


            }
        });

        anahtarVaktinde.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    // binding.vaktindenOnceBaslikBack.setBackground(ContextCompat.getDrawable(Context, R.drawable.corner_layer_top_2_radius_red));


                    binding.vaktindeSoundTitle.setText(mainVtitle);
                    binding.vaktindeSoundSelectButton.setVisibility(View.VISIBLE);


                } else {

                    binding.vaktindeSoundTitle.setText("Kapalı");
                    binding.vaktindeSoundSelectButton.setVisibility(View.INVISIBLE);

                }


            }
        });

        getBildirimSound();









        Button VOSesSecButton = binding.vaktindenOnceSoundSelectButton;

        VOSesSecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.soundPickerList.setVisibility(View.VISIBLE);
                binding.secimiTamamlaButton.setVisibility(View.VISIBLE);
                binding.pickerListBack.setVisibility(View.VISIBLE);
                binding.pickerListBack.setVisibility(View.VISIBLE);
                binding.kaydetButton.setVisibility(View.INVISIBLE);
                binding.vaktindenOnceSoundSelectButton.setVisibility(View.INVISIBLE);
                binding.vaktindeSoundSelectButton.setVisibility(View.INVISIBLE);
                binding.minuteSelectButton.setVisibility(View.INVISIBLE);
                binding.vaktindenOnceSwitch.setVisibility(View.INVISIBLE);
                binding.vaktindeSwitch.setVisibility(View.INVISIBLE);

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
                binding.pickerListBack.setVisibility(View.VISIBLE);
                binding.pickerListBack.setVisibility(View.VISIBLE);
                binding.kaydetButton.setVisibility(View.INVISIBLE);
                binding.vaktindenOnceSoundSelectButton.setVisibility(View.INVISIBLE);
                binding.vaktindeSoundSelectButton.setVisibility(View.INVISIBLE);
                binding.minuteSelectButton.setVisibility(View.INVISIBLE);
                binding.vaktindenOnceSwitch.setVisibility(View.INVISIBLE);
                binding.vaktindeSwitch.setVisibility(View.INVISIBLE);
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

                if (EzanSound1.isPlaying()) {

                    EzanSound1.stop();

                }

                binding.soundPickerList.setVisibility(View.INVISIBLE);
                binding.secimiTamamlaButton.setVisibility(View.INVISIBLE);
                binding.surePickerList.setVisibility(View.INVISIBLE);
                binding.pickerListBack.setVisibility(View.INVISIBLE);
                binding.kaydetButton.setVisibility(View.VISIBLE);
                binding.vaktindenOnceSoundSelectButton.setVisibility(View.VISIBLE);
                binding.vaktindeSoundSelectButton.setVisibility(View.VISIBLE);
                binding.minuteSelectButton.setVisibility(View.VISIBLE);
                binding.vaktindenOnceSwitch.setVisibility(View.VISIBLE);
                binding.vaktindeSwitch.setVisibility(View.VISIBLE);

                System.out.println(VOSesString);
                System.out.println(VaktindeSesString);
                System.out.println(VOsureInt);


            }
        });

        Button sureSecButton = binding.minuteSelectButton;

        sureSecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.surePickerList.setVisibility(View.VISIBLE);
                binding.secimiTamamlaButton.setVisibility(View.VISIBLE);
                binding.pickerListBack.setVisibility(View.VISIBLE);
                binding.pickerListBack.setVisibility(View.VISIBLE);
                binding.kaydetButton.setVisibility(View.INVISIBLE);
                binding.vaktindenOnceSoundSelectButton.setVisibility(View.INVISIBLE);
                binding.vaktindeSoundSelectButton.setVisibility(View.INVISIBLE);
                binding.minuteSelectButton.setVisibility(View.INVISIBLE);
                binding.vaktindenOnceSwitch.setVisibility(View.INVISIBLE);
                binding.vaktindeSwitch.setVisibility(View.INVISIBLE);


            }
        });

        Button kaydetButton = binding.kaydetButton;

        kaydetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("Kaydet" + VOSesString);
                System.out.println("Kaydet" + VaktindeSesString);
                System.out.println("Kaydet" + VOsureInt);

                if (VOSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vOImsakSesStr", "Kapalı").apply();

                } else {

                    sharedPreferences.edit().putInt("vOImsakSureInt", VOsureInt).apply();

                    if (VakitInfo.equals("İmsak Vakti") && VOSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vOImsakSesStr", "kussesi1.mp3").apply();

                    } else if (VakitInfo.equals("İmsak Vakti") && VOSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vOImsakSesStr", "kussesi2.mp3").apply();

                    } else if (VakitInfo.equals("İmsak Vakti") && VOSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vOImsakSesStr", "ahmadalnafes.mp3").apply();

                    } else if (VakitInfo.equals("İmsak Vakti") && VOSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vOImsakSesStr", "masjidalharam.mp3").apply();

                    } else if (VakitInfo.equals("İmsak Vakti") && VOSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vOImsakSesStr", "misharyrasidalafasy.mp3").apply();
                    }

                }

                if (VaktindeSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vImsakSesStr", "Kapalı").apply();

                } else {

                    if (VakitInfo.equals("İmsak Vakti") && VaktindeSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vImsakSesStr", "kussesi1.mp3").apply();

                    } else if (VakitInfo.equals("İmsak Vakti") && VaktindeSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vImsakSesStr", "kussesi2.mp3").apply();

                    } else if (VakitInfo.equals("İmsak Vakti") && VaktindeSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vImsakSesStr", "ahmadalnafes.mp3").apply();

                    } else if (VakitInfo.equals("İmsak Vakti") && VaktindeSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vImsakSesStr", "masjidalharam.mp3").apply();

                    } else if (VakitInfo.equals("İmsak Vakti") && VaktindeSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vImsakSesStr", "misharyrasidalafasy.mp3").apply();

                    }

                }

                if (VOSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vOGunesSesStr", "Kapalı").apply();

                } else {

                    if (VakitInfo.equals("Güneş Vakti") && VOSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vOGunesSesStr", "kussesi1.mp3").apply();
                        sharedPreferences.edit().putInt("vOGunesSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Güneş Vakti") && VOSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vOGunesSesStr", "kussesi2.mp3").apply();
                        sharedPreferences.edit().putInt("vOGunesSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Güneş Vakti") && VOSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vOGunesSesStr", "ahmadalnafes.mp3").apply();
                        sharedPreferences.edit().putInt("vOGunesSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Güneş Vakti") && VOSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vOGunesSesStr", "masjidalharam.mp3").apply();
                        sharedPreferences.edit().putInt("vOGunesSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Güneş Vakti") && VOSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vOGunesSesStr", "misharyrasidalafasy.mp3").apply();
                        sharedPreferences.edit().putInt("vOGunesSureInt", VOsureInt).apply();

                    }

                }

                if (VaktindeSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vGunesSesStr", "Kapalı").apply();

                } else {

                    if (VakitInfo.equals("Güneş Vakti") && VaktindeSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vGunesSesStr", "kussesi1.mp3").apply();

                    } else if (VakitInfo.equals("Güneş Vakti") && VaktindeSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vGunesSesStr", "kussesi2.mp3").apply();

                    } else if (VakitInfo.equals("Güneş Vakti") && VaktindeSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vGunesSesStr", "ahmadalnafes.mp3").apply();

                    } else if (VakitInfo.equals("Güneş Vakti") && VaktindeSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vGunesSesStr", "masjidalharam.mp3").apply();

                    } else if (VakitInfo.equals("Güneş Vakti") && VaktindeSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vGunesSesStr", "misharyrasidalafasy.mp3").apply();

                    }

                }


                if (VOSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vOOgleSesStr", "Kapalı").apply();

                } else {

                    if (VakitInfo.equals("Öğle Vakti") && VOSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vOOgleSesStr", "kussesi1.mp3").apply();
                        sharedPreferences.edit().putInt("vOOgleSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Öğle Vakti") && VOSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vOOgleSesStr", "kussesi2.mp3").apply();
                        sharedPreferences.edit().putInt("vOOgleSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Öğle Vakti") && VOSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vOOgleSesStr", "ahmadalnafes.mp3").apply();
                        sharedPreferences.edit().putInt("vOOgleSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Öğle Vakti") && VOSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vOOgleSesStr", "masjidalharam.mp3").apply();
                        sharedPreferences.edit().putInt("vOOgleSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Öğle Vakti") && VOSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vOOgleSesStr", "misharyrasidalafasy.mp3").apply();
                        sharedPreferences.edit().putInt("vOOgleSureInt", VOsureInt).apply();

                    }

                }

                if (VaktindeSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vOgleSesStr", "Kapalı").apply();

                } else {

                    if (VakitInfo.equals("Öğle Vakti") && VaktindeSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vOgleSesStr", "kussesi1.mp3").apply();

                    } else if (VakitInfo.equals("Öğle Vakti") && VaktindeSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vOgleSesStr", "kussesi2.mp3").apply();

                    } else if (VakitInfo.equals("Öğle Vakti") && VaktindeSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vOgleSesStr", "ahmadalnafes.mp3").apply();

                    } else if (VakitInfo.equals("Öğle Vakti") && VaktindeSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vOgleSesStr", "masjidalharam.mp3").apply();

                    } else if (VakitInfo.equals("Öğle Vakti") && VaktindeSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vOgleSesStr", "misharyrasidalafasy.mp3").apply();

                    }

                }


                if (VOSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vOIkindiSesStr", "Kapalı").apply();

                } else {


                    if (VakitInfo.equals("İkindi Vakti") && VOSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vOIkindiSesStr", "kussesi1.mp3").apply();
                        sharedPreferences.edit().putInt("vOIkindiSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("İkindi Vakti") && VOSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vOIkindiSesStr", "kussesi2.mp3").apply();
                        sharedPreferences.edit().putInt("vOIkindiSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("İkindi Vakti") && VOSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vOIkindiSesStr", "ahmadalnafes.mp3").apply();
                        sharedPreferences.edit().putInt("vOIkindiSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("İkindi Vakti") && VOSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vOIkindiSesStr", "masjidalharam.mp3").apply();
                        sharedPreferences.edit().putInt("vOIkindiSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("İkindi Vakti") && VOSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vOIkindiSesStr", "misharyrasidalafasy.mp3").apply();
                        sharedPreferences.edit().putInt("vOIkindiSureInt", VOsureInt).apply();

                    }

                }

                if (VaktindeSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vIkindiSesStr", "Kapalı").apply();

                } else {


                    if (VakitInfo.equals("İkindi Vakti") && VaktindeSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vIkindiSesStr", "kussesi1.mp3").apply();

                    } else if (VakitInfo.equals("İkindi Vakti") && VaktindeSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vIkindiSesStr", "kussesi2.mp3").apply();

                    } else if (VakitInfo.equals("İkindi Vakti") && VaktindeSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vIkindiSesStr", "ahmadalnafes.mp3").apply();

                    } else if (VakitInfo.equals("İkindi Vakti") && VaktindeSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vIkindiSesStr", "masjidalharam.mp3").apply();

                    } else if (VakitInfo.equals("İkindi Vakti") && VaktindeSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vIkindiSesStr", "misharyrasidalafasy.mp3").apply();

                    }

                }


                if (VOSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vOAksamSesStr", "Kapalı").apply();

                } else {


                    if (VakitInfo.equals("Akşam Vakti") && VOSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vOAksamSesStr", "kussesi1.mp3").apply();
                        sharedPreferences.edit().putInt("vOAksamSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Akşam Vakti") && VOSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vOAksamSesStr", "kussesi2.mp3").apply();
                        sharedPreferences.edit().putInt("vOAksamSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Akşam Vakti") && VOSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vOAksamSesStr", "ahmadalnafes.mp3").apply();
                        sharedPreferences.edit().putInt("vOAksamSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Akşam Vakti") && VOSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vOAksamSesStr", "masjidalharam.mp3").apply();
                        sharedPreferences.edit().putInt("vOAksamSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Akşam Vakti") && VOSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vOAksamSesStr", "misharyrasidalafasy.mp3").apply();
                        sharedPreferences.edit().putInt("vOAksamSureInt", VOsureInt).apply();

                    }

                }

                if (VaktindeSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vAksamSesStr", "Kapalı").apply();

                } else {

                    if (VakitInfo.equals("Akşam Vakti") && VaktindeSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vAksamSesStr", "kussesi1.mp3").apply();

                    } else if (VakitInfo.equals("Akşam Vakti") && VaktindeSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vAksamSesStr", "kussesi2.mp3").apply();

                    } else if (VakitInfo.equals("Akşam Vakti") && VaktindeSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vAksamSesStr", "ahmadalnafes.mp3").apply();

                    } else if (VakitInfo.equals("Akşam Vakti") && VaktindeSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vAksamSesStr", "masjidalharam.mp3").apply();

                    } else if (VakitInfo.equals("Akşam Vakti") && VaktindeSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vAksamSesStr", "misharyrasidalafasy.mp3").apply();

                    }

                }


                if (VOSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vOYatsiSesStr", "Kapalı").apply();

                } else {

                    if (VakitInfo.equals("Yatsı Vakti") && VOSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vOYatsiSesStr", "kussesi1.mp3").apply();
                        sharedPreferences.edit().putInt("vOYatsiSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Yatsı Vakti") && VOSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vOYatsiSesStr", "kussesi2.mp3").apply();
                        sharedPreferences.edit().putInt("vOYatsiSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Yatsı Vakti") && VOSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vOYatsiSesStr", "ahmadalnafes.mp3").apply();
                        sharedPreferences.edit().putInt("vOYatsiSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Yatsı Vakti") && VOSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vOYatsiSesStr", "masjidalharam.mp3").apply();
                        sharedPreferences.edit().putInt("vOYatsiSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Yatsı Vakti") && VOSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vOYatsiSesStr", "misharyrasidalafasy.mp3").apply();
                        sharedPreferences.edit().putInt("vOYatsiSureInt", VOsureInt).apply();

                    }

                }

                if (VaktindeSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vYatsiSesStr", "Kapalı").apply();

                } else {

                    if (VakitInfo.equals("Yatsı Vakti") && VaktindeSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vYatsiSesStr", "kussesi1.mp3").apply();

                    } else if (VakitInfo.equals("Yatsı Vakti") && VaktindeSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vYatsiSesStr", "kussesi2.mp3").apply();

                    } else if (VakitInfo.equals("Yatsı Vakti") && VaktindeSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vYatsiSesStr", "ahmadalnafes.mp3").apply();

                    } else if (VakitInfo.equals("Yatsı Vakti") && VaktindeSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vYatsiSesStr", "masjidalharam.mp3").apply();

                    } else if (VakitInfo.equals("Yatsı Vakti") && VaktindeSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vYatsiSesStr", "misharyrasidalafasy.mp3").apply();

                    }

                }


                Intent sesKaydetIntent = new Intent(SoundSettingsDetailActivity.this, MainActivity.class);
                startActivity(sesKaydetIntent);

            }

        });

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

                    } else if (VOSesString.equals("Kuş Sesi 2")) {
                        EzanSound1.stop();

                        EzanSound1 = MediaPlayer.create(SoundSettingsDetailActivity.this, R.raw.kussesi2);

                        EzanSound1.start();

                    } else if (VOSesString.equals("Ahmad Al Nafees")) {

                        EzanSound1.stop();

                        EzanSound1 = MediaPlayer.create(SoundSettingsDetailActivity.this, R.raw.ahmadalnafes);

                        EzanSound1.start();

                    } else if (VOSesString.equals("Masjid Al Haram")) {

                        EzanSound1.stop();

                        EzanSound1 = MediaPlayer.create(SoundSettingsDetailActivity.this, R.raw.masjidalharam);

                        EzanSound1.start();

                    } else if (VOSesString.equals("Mishary Rashid Alafasy")) {

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

                    } else if (VaktindeSesString.equals("Kuş Sesi 2")) {
                        EzanSound1.stop();

                        EzanSound1 = MediaPlayer.create(SoundSettingsDetailActivity.this, R.raw.kussesi2);

                        EzanSound1.start();

                    } else if (VaktindeSesString.equals("Ahmad Al Nafees")) {

                        EzanSound1.stop();

                        EzanSound1 = MediaPlayer.create(SoundSettingsDetailActivity.this, R.raw.ahmadalnafes);

                        EzanSound1.start();

                    } else if (VaktindeSesString.equals("Masjid Al Haram")) {

                        EzanSound1.stop();

                        EzanSound1 = MediaPlayer.create(SoundSettingsDetailActivity.this, R.raw.masjidalharam);

                        EzanSound1.start();

                    } else if (VaktindeSesString.equals("Mishary Rashid Alafasy")) {

                        EzanSound1.stop();

                        EzanSound1 = MediaPlayer.create(SoundSettingsDetailActivity.this, R.raw.misharyrasidalafasy);

                        EzanSound1.start();

                    }
                }


            }
        });

        binding.surePickerList.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {


                VOsureInt = newVal;
                System.out.println(VOsureInt);

                String newValue = String.valueOf(newVal);

                binding.minuteTitle.setText(newValue);


            }
        });



    }

    public void getBildirimSound() {

        vOImsakSureInt = sharedPreferences.getInt("vOImsakSureInt", 0);
        vOImsakSesStr = sharedPreferences.getString("vOImsakSesStr", "");
        vImsakSesStr = sharedPreferences.getString("vImsakSesStr", "");

        vOGunesSureInt = sharedPreferences.getInt("vOGunesSureInt", 0);
        vOGunesSesStr = sharedPreferences.getString("vOGunesSesStr", "");
        vGunesSesStr = sharedPreferences.getString("vGunesSesStr", "");

        vOOgleSureInt = sharedPreferences.getInt("vOOgleSureInt", 0);
        vOOgleSesStr = sharedPreferences.getString("vOOgleSesStr", "");
        vOgleSesStr = sharedPreferences.getString("vOgleSesStr", "");

        vOIkindiSureInt = sharedPreferences.getInt("vOIkindiSureInt", 0);
        vOIkindiSesStr = sharedPreferences.getString("vOIkindiSesStr", "");
        vIkindiSesStr = sharedPreferences.getString("vIkindiSesStr", "");

        vOAksamSureInt = sharedPreferences.getInt("vOAksamSureInt", 0);
        vOAksamSesStr = sharedPreferences.getString("vOAksamSesStr", "");
        vAksamSesStr = sharedPreferences.getString("vAksamSesStr", "");

        vOYatsiSureInt = sharedPreferences.getInt("vOYatsiSureInt", 0);
        vOYatsiSesStr = sharedPreferences.getString("vOYatsiSesStr", "");
        vYatsiSesStr = sharedPreferences.getString("vYatsiSesStr", "");

        String voTitle;
        String vtitle;

        String notSesi1 = "Kuş Sesi";
        String notSesi2 = "Kuş Sesi 2";
        String notSesi3 = "Ahmad Al Nafees";
        String notSesi4 = "Masjid Al Haram";
        String notSesi5 = "Mishary Rashid Alafasy";

        String notSesi11 = "kussesi1.mp3";
        String notSesi22 = "kussesi2.mp3";
        String notSesi33 = "ahmadalnafes.mp3";
        String notSesi44 = "masjidalharam.mp3";
        String notSesi55 = "misharyrasidalafasy.mp3";




        if (VakitInfo.equals("İmsak Vakti")) {

            VOSesString = vOImsakSesStr;
            VaktindeSesString = vImsakSesStr;
            VOsureInt = vOImsakSureInt;

            String sure = String.valueOf(vOImsakSureInt);
            binding.minuteTitle.setText(sure);

            voTitle = vOImsakSesStr;
            vtitle = vImsakSesStr;

            if (voTitle.equals(notSesi11)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi1);
                mainvOTitle = notSesi1;

            } else if (voTitle.equals(notSesi22)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi2);
                mainvOTitle = notSesi2;

            } else if (voTitle.equals(notSesi33)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi3);
                mainvOTitle = notSesi3;

            } else if (voTitle.equals(notSesi44)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi4);
                mainvOTitle = notSesi4;

            } else if (voTitle.equals(notSesi55)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi5);
                mainvOTitle = notSesi5;
            }


            if (vtitle.equals(notSesi11)) {

                binding.vaktindeSoundTitle.setText(notSesi1);
                mainVtitle = notSesi1;

            } else if (vtitle.equals(notSesi22)) {

                binding.vaktindeSoundTitle.setText(notSesi2);
                mainVtitle = notSesi2;

            } else if (vtitle.equals(notSesi33)) {

                binding.vaktindeSoundTitle.setText(notSesi3);
                mainVtitle = notSesi3;

            } else if (vtitle.equals(notSesi44)) {

                binding.vaktindeSoundTitle.setText(notSesi4);
                mainVtitle = notSesi4;

            } else if (vtitle.equals(notSesi55)) {

                binding.vaktindeSoundTitle.setText(notSesi5);
                mainVtitle = notSesi5;
            }


        } else if (VakitInfo.equals("Güneş Vakti")) {

            VOSesString = vOGunesSesStr;
            VaktindeSesString = vGunesSesStr;
            VOsureInt = vOGunesSureInt;

            String sure = String.valueOf(vOGunesSureInt);
            binding.minuteTitle.setText(sure);

            voTitle = vOGunesSesStr;
            vtitle = vGunesSesStr;

            if (voTitle.equals(notSesi11)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi1);
                mainvOTitle = notSesi1;

            } else if (voTitle.equals(notSesi22)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi2);
                mainvOTitle = notSesi2;

            } else if (voTitle.equals(notSesi33)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi3);
                mainvOTitle = notSesi3;

            } else if (voTitle.equals(notSesi44)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi4);
                mainvOTitle = notSesi4;

            } else if (voTitle.equals(notSesi55)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi5);
                mainvOTitle = notSesi5;
            }


            if (vtitle.equals(notSesi11)) {

                binding.vaktindeSoundTitle.setText(notSesi1);
                mainVtitle = notSesi1;

            } else if (vtitle.equals(notSesi22)) {

                binding.vaktindeSoundTitle.setText(notSesi2);
                mainVtitle = notSesi2;

            } else if (vtitle.equals(notSesi33)) {

                binding.vaktindeSoundTitle.setText(notSesi3);
                mainVtitle = notSesi3;

            } else if (vtitle.equals(notSesi44)) {

                binding.vaktindeSoundTitle.setText(notSesi4);
                mainVtitle = notSesi4;

            } else if (vtitle.equals(notSesi55)) {

                binding.vaktindeSoundTitle.setText(notSesi5);
                mainVtitle = notSesi5;
            }


        } else if (VakitInfo.equals("Öğle Vakti")) {

            VOSesString = vOOgleSesStr;
            VaktindeSesString = vOgleSesStr;
            VOsureInt = vOOgleSureInt;

            String sure = String.valueOf(vOOgleSureInt);
            binding.minuteTitle.setText(sure);

            voTitle = vOOgleSesStr;
            vtitle = vOgleSesStr;

            if (voTitle.equals(notSesi11)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi1);
                mainvOTitle = notSesi1;

            } else if (voTitle.equals(notSesi22)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi2);
                mainvOTitle = notSesi2;

            } else if (voTitle.equals(notSesi33)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi3);
                mainvOTitle = notSesi3;

            } else if (voTitle.equals(notSesi44)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi4);
                mainvOTitle = notSesi4;

            } else if (voTitle.equals(notSesi55)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi5);
                mainvOTitle = notSesi5;
            }


            if (vtitle.equals(notSesi11)) {

                binding.vaktindeSoundTitle.setText(notSesi1);
                mainVtitle = notSesi1;

            } else if (vtitle.equals(notSesi22)) {

                binding.vaktindeSoundTitle.setText(notSesi2);
                mainVtitle = notSesi2;

            } else if (vtitle.equals(notSesi33)) {

                binding.vaktindeSoundTitle.setText(notSesi3);
                mainVtitle = notSesi3;

            } else if (vtitle.equals(notSesi44)) {

                binding.vaktindeSoundTitle.setText(notSesi4);
                mainVtitle = notSesi4;

            } else if (vtitle.equals(notSesi55)) {

                binding.vaktindeSoundTitle.setText(notSesi5);
                mainVtitle = notSesi5;
            }


        } else if (VakitInfo.equals("İkindi Vakti")) {

            VOSesString = vOIkindiSesStr;
            VaktindeSesString = vIkindiSesStr;
            VOsureInt = vOIkindiSureInt;

            String sure = String.valueOf(vOIkindiSureInt);
            binding.minuteTitle.setText(sure);

            voTitle = vOIkindiSesStr;
            vtitle = vIkindiSesStr;

            if (voTitle.equals(notSesi11)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi1);
                mainvOTitle = notSesi1;

            } else if (voTitle.equals(notSesi22)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi2);
                mainvOTitle = notSesi2;

            } else if (voTitle.equals(notSesi33)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi3);
                mainvOTitle = notSesi3;

            } else if (voTitle.equals(notSesi44)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi4);
                mainvOTitle = notSesi4;

            } else if (voTitle.equals(notSesi55)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi5);
                mainvOTitle = notSesi5;
            }


            if (vtitle.equals(notSesi11)) {

                binding.vaktindeSoundTitle.setText(notSesi1);
                mainVtitle = notSesi1;

            } else if (vtitle.equals(notSesi22)) {

                binding.vaktindeSoundTitle.setText(notSesi2);
                mainVtitle = notSesi2;

            } else if (vtitle.equals(notSesi33)) {

                binding.vaktindeSoundTitle.setText(notSesi3);
                mainVtitle = notSesi3;

            } else if (vtitle.equals(notSesi44)) {

                binding.vaktindeSoundTitle.setText(notSesi4);
                mainVtitle = notSesi4;

            } else if (vtitle.equals(notSesi55)) {

                binding.vaktindeSoundTitle.setText(notSesi5);
                mainVtitle = notSesi5;
            }


        } else if (VakitInfo.equals("Akşam Vakti")) {

            VOSesString = vOAksamSesStr;
            VaktindeSesString = vAksamSesStr;
            VOsureInt = vOAksamSureInt;

            String sure = String.valueOf(vOAksamSureInt);
            binding.minuteTitle.setText(sure);

            voTitle = vOAksamSesStr;
            vtitle = vAksamSesStr;

            if (voTitle.equals(notSesi11)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi1);
                mainvOTitle = notSesi1;

            } else if (voTitle.equals(notSesi22)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi2);
                mainvOTitle = notSesi2;

            } else if (voTitle.equals(notSesi33)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi3);
                mainvOTitle = notSesi3;

            } else if (voTitle.equals(notSesi44)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi4);
                mainvOTitle = notSesi4;

            } else if (voTitle.equals(notSesi55)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi5);
                mainvOTitle = notSesi5;
            }


            if (vtitle.equals(notSesi11)) {

                binding.vaktindeSoundTitle.setText(notSesi1);
                mainVtitle = notSesi1;

            } else if (vtitle.equals(notSesi22)) {

                binding.vaktindeSoundTitle.setText(notSesi2);
                mainVtitle = notSesi2;

            } else if (vtitle.equals(notSesi33)) {

                binding.vaktindeSoundTitle.setText(notSesi3);
                mainVtitle = notSesi3;

            } else if (vtitle.equals(notSesi44)) {

                binding.vaktindeSoundTitle.setText(notSesi4);
                mainVtitle = notSesi4;

            } else if (vtitle.equals(notSesi55)) {

                binding.vaktindeSoundTitle.setText(notSesi5);
                mainVtitle = notSesi5;
            }


        } else if (VakitInfo.equals("Yatsı Vakti")) {

            VOSesString = vOYatsiSesStr;
            VaktindeSesString = vYatsiSesStr;
            VOsureInt = vOYatsiSureInt;

            String sure = String.valueOf(vOYatsiSureInt);
            binding.minuteTitle.setText(sure);

            voTitle = vOYatsiSesStr;
            vtitle = vYatsiSesStr;

            if (voTitle.equals(notSesi11)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi1);
                mainvOTitle = notSesi1;

            } else if (voTitle.equals(notSesi22)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi2);
                mainvOTitle = notSesi2;

            } else if (voTitle.equals(notSesi33)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi3);
                mainvOTitle = notSesi3;

            } else if (voTitle.equals(notSesi44)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi4);
                mainvOTitle = notSesi4;

            } else if (voTitle.equals(notSesi55)) {

                binding.vaktindenOnceSoundTitle.setText(notSesi5);
                mainvOTitle = notSesi5;
            }


            if (vtitle.equals(notSesi11)) {

                binding.vaktindeSoundTitle.setText(notSesi1);
                mainVtitle = notSesi1;

            } else if (vtitle.equals(notSesi22)) {

                binding.vaktindeSoundTitle.setText(notSesi2);
                mainVtitle = notSesi2;

            } else if (vtitle.equals(notSesi33)) {

                binding.vaktindeSoundTitle.setText(notSesi3);
                mainVtitle = notSesi3;

            } else if (vtitle.equals(notSesi44)) {

                binding.vaktindeSoundTitle.setText(notSesi4);
                mainVtitle = notSesi4;

            } else if (vtitle.equals(notSesi55)) {

                binding.vaktindeSoundTitle.setText(notSesi5);
                mainVtitle = notSesi5;
            }

        }


    }

}