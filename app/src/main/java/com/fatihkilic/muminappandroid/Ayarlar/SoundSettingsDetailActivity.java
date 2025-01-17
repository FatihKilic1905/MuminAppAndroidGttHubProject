package com.fatihkilic.muminappandroid.Ayarlar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
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
                    binding.vaktindenOnceSoundTitle.setText("Ahmad Al Nafees");
                    VOSesString = "Ahmad Al Nafees";

                } else {

                    binding.vaktindenOnceSoundTitle.setText("Kapalı");
                    VOSesString = "Kapalı";
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
                    binding.vaktindeSoundTitle.setText("Ahmad Al Nafees");
                    VaktindeSesString = "Ahmad Al Nafees";


                } else {

                    binding.vaktindeSoundTitle.setText("Kapalı");
                    VaktindeSesString = "Kapalı";

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

                } else {

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




            }
        });



        Button sureSecButton = binding.minuteSelectButton;

        sureSecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EzanSound1 = MediaPlayer.create(SoundSettingsDetailActivity.this, R.raw.kussesi1);

                EzanSound1.start();
                EzanSound1.stop();

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

                if (VakitInfo.equals("İmsak Vakti") && VOSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vOImsakSesStr", "Kapalı").apply();

                } else if (VakitInfo.equals("İmsak Vakti")) {


                    if (VakitInfo.equals("İmsak Vakti") && VOSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vOImsakSesStr", "kussesi1").apply();
                        sharedPreferences.edit().putInt("vOImsakSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("İmsak Vakti") && VOSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vOImsakSesStr", "kussesi2").apply();
                        sharedPreferences.edit().putInt("vOImsakSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("İmsak Vakti") && VOSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vOImsakSesStr", "ahmadalnafes").apply();
                        sharedPreferences.edit().putInt("vOImsakSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("İmsak Vakti") && VOSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vOImsakSesStr", "masjidalharam").apply();
                        sharedPreferences.edit().putInt("vOImsakSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("İmsak Vakti") && VOSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vOImsakSesStr", "misharyrasidalafasy").apply();
                        sharedPreferences.edit().putInt("vOImsakSureInt", VOsureInt).apply();

                    }



                }

                if (VakitInfo.equals("İmsak Vakti") && VaktindeSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vImsakSesStr", "Kapalı").apply();

                } else if (VakitInfo.equals("İmsak Vakti")) {

                    if (VakitInfo.equals("İmsak Vakti") && VaktindeSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vImsakSesStr", "kussesi1").apply();

                    } else if (VakitInfo.equals("İmsak Vakti") && VaktindeSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vImsakSesStr", "kussesi2").apply();

                    } else if (VakitInfo.equals("İmsak Vakti") && VaktindeSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vImsakSesStr", "ahmadalnafes").apply();

                    } else if (VakitInfo.equals("İmsak Vakti") && VaktindeSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vImsakSesStr", "masjidalharam").apply();

                    } else if (VakitInfo.equals("İmsak Vakti") && VaktindeSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vImsakSesStr", "misharyrasidalafasy").apply();

                    }

                }

                if (VakitInfo.equals("Güneş Vakti") && VOSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vOGunesSesStr", "Kapalı").apply();

                } else if (VakitInfo.equals("Güneş Vakti")) {

                    if (VakitInfo.equals("Güneş Vakti") && VOSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vOGunesSesStr", "kussesi1").apply();
                        sharedPreferences.edit().putInt("vOGunesSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Güneş Vakti") && VOSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vOGunesSesStr", "kussesi2").apply();
                        sharedPreferences.edit().putInt("vOGunesSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Güneş Vakti") && VOSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vOGunesSesStr", "ahmadalnafes").apply();
                        sharedPreferences.edit().putInt("vOGunesSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Güneş Vakti") && VOSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vOGunesSesStr", "masjidalharam").apply();
                        sharedPreferences.edit().putInt("vOGunesSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Güneş Vakti") && VOSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vOGunesSesStr", "misharyrasidalafasy").apply();
                        sharedPreferences.edit().putInt("vOGunesSureInt", VOsureInt).apply();

                    }

                }

                if (VakitInfo.equals("Güneş Vakti") && VaktindeSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vGunesSesStr", "Kapalı").apply();

                } else if (VakitInfo.equals("Güneş Vakti")) {

                    if (VakitInfo.equals("Güneş Vakti") && VaktindeSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vGunesSesStr", "kussesi1").apply();

                    } else if (VakitInfo.equals("Güneş Vakti") && VaktindeSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vGunesSesStr", "kussesi2").apply();

                    } else if (VakitInfo.equals("Güneş Vakti") && VaktindeSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vGunesSesStr", "ahmadalnafes").apply();

                    } else if (VakitInfo.equals("Güneş Vakti") && VaktindeSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vGunesSesStr", "masjidalharam").apply();

                    } else if (VakitInfo.equals("Güneş Vakti") && VaktindeSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vGunesSesStr", "misharyrasidalafasy").apply();

                    }

                }


                if (VakitInfo.equals("Öğle Vakti") && VOSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vOOgleSesStr", "Kapalı").apply();

                } else if (VakitInfo.equals("Öğle Vakti")) {

                    if (VakitInfo.equals("Öğle Vakti") && VOSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vOOgleSesStr", "kussesi1").apply();
                        sharedPreferences.edit().putInt("vOOgleSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Öğle Vakti") && VOSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vOOgleSesStr", "kussesi2").apply();
                        sharedPreferences.edit().putInt("vOOgleSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Öğle Vakti") && VOSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vOOgleSesStr", "ahmadalnafes").apply();
                        sharedPreferences.edit().putInt("vOOgleSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Öğle Vakti") && VOSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vOOgleSesStr", "masjidalharam").apply();
                        sharedPreferences.edit().putInt("vOOgleSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Öğle Vakti") && VOSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vOOgleSesStr", "misharyrasidalafasy").apply();
                        sharedPreferences.edit().putInt("vOOgleSureInt", VOsureInt).apply();

                    }

                }

                if (VakitInfo.equals("Öğle Vakti") && VaktindeSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vOgleSesStr", "Kapalı").apply();

                } else if (VakitInfo.equals("Öğle Vakti")) {

                    if (VakitInfo.equals("Öğle Vakti") && VaktindeSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vOgleSesStr", "kussesi1").apply();

                    } else if (VakitInfo.equals("Öğle Vakti") && VaktindeSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vOgleSesStr", "kussesi2").apply();

                    } else if (VakitInfo.equals("Öğle Vakti") && VaktindeSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vOgleSesStr", "ahmadalnafes").apply();

                    } else if (VakitInfo.equals("Öğle Vakti") && VaktindeSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vOgleSesStr", "masjidalharam").apply();

                    } else if (VakitInfo.equals("Öğle Vakti") && VaktindeSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vOgleSesStr", "misharyrasidalafasy").apply();

                    }

                }


                if (VakitInfo.equals("İkindi Vakti") && VOSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vOIkindiSesStr", "Kapalı").apply();

                } else if (VakitInfo.equals("İkindi Vakti")) {


                    if (VakitInfo.equals("İkindi Vakti") && VOSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vOIkindiSesStr", "kussesi1").apply();
                        sharedPreferences.edit().putInt("vOIkindiSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("İkindi Vakti") && VOSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vOIkindiSesStr", "kussesi2").apply();
                        sharedPreferences.edit().putInt("vOIkindiSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("İkindi Vakti") && VOSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vOIkindiSesStr", "ahmadalnafes").apply();
                        sharedPreferences.edit().putInt("vOIkindiSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("İkindi Vakti") && VOSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vOIkindiSesStr", "masjidalharam").apply();
                        sharedPreferences.edit().putInt("vOIkindiSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("İkindi Vakti") && VOSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vOIkindiSesStr", "misharyrasidalafasy").apply();
                        sharedPreferences.edit().putInt("vOIkindiSureInt", VOsureInt).apply();

                    }

                }

                if (VakitInfo.equals("İkindi Vakti") && VaktindeSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vIkindiSesStr", "Kapalı").apply();

                } else if (VakitInfo.equals("İkindi Vakti")) {


                    if (VakitInfo.equals("İkindi Vakti") && VaktindeSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vIkindiSesStr", "kussesi1").apply();

                    } else if (VakitInfo.equals("İkindi Vakti") && VaktindeSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vIkindiSesStr", "kussesi2").apply();

                    } else if (VakitInfo.equals("İkindi Vakti") && VaktindeSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vIkindiSesStr", "ahmadalnafes").apply();

                    } else if (VakitInfo.equals("İkindi Vakti") && VaktindeSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vIkindiSesStr", "masjidalharam").apply();

                    } else if (VakitInfo.equals("İkindi Vakti") && VaktindeSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vIkindiSesStr", "misharyrasidalafasy").apply();

                    }

                }


                if (VakitInfo.equals("Akşam Vakti") && VOSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vOAksamSesStr", "Kapalı").apply();

                } else if (VakitInfo.equals("Akşam Vakti")) {


                    if (VakitInfo.equals("Akşam Vakti") && VOSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vOAksamSesStr", "kussesi1").apply();
                        sharedPreferences.edit().putInt("vOAksamSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Akşam Vakti") && VOSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vOAksamSesStr", "kussesi2").apply();
                        sharedPreferences.edit().putInt("vOAksamSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Akşam Vakti") && VOSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vOAksamSesStr", "ahmadalnafes").apply();
                        sharedPreferences.edit().putInt("vOAksamSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Akşam Vakti") && VOSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vOAksamSesStr", "masjidalharam").apply();
                        sharedPreferences.edit().putInt("vOAksamSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Akşam Vakti") && VOSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vOAksamSesStr", "misharyrasidalafasy").apply();
                        sharedPreferences.edit().putInt("vOAksamSureInt", VOsureInt).apply();

                    }

                }

                if (VakitInfo.equals("Akşam Vakti") && VaktindeSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vAksamSesStr", "Kapalı").apply();

                } else if (VakitInfo.equals("Akşam Vakti")) {

                    if (VakitInfo.equals("Akşam Vakti") && VaktindeSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vAksamSesStr", "kussesi1").apply();

                    } else if (VakitInfo.equals("Akşam Vakti") && VaktindeSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vAksamSesStr", "kussesi2").apply();

                    } else if (VakitInfo.equals("Akşam Vakti") && VaktindeSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vAksamSesStr", "ahmadalnafes").apply();

                    } else if (VakitInfo.equals("Akşam Vakti") && VaktindeSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vAksamSesStr", "masjidalharam").apply();

                    } else if (VakitInfo.equals("Akşam Vakti") && VaktindeSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vAksamSesStr", "misharyrasidalafasy").apply();

                    }

                }


                if (VakitInfo.equals("Yatsı Vakti") && VOSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vOYatsiSesStr", "Kapalı").apply();

                } else  if (VakitInfo.equals("Yatsı Vakti")){

                    if (VakitInfo.equals("Yatsı Vakti") && VOSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vOYatsiSesStr", "kussesi1").apply();
                        sharedPreferences.edit().putInt("vOYatsiSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Yatsı Vakti") && VOSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vOYatsiSesStr", "kussesi2").apply();
                        sharedPreferences.edit().putInt("vOYatsiSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Yatsı Vakti") && VOSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vOYatsiSesStr", "ahmadalnafes").apply();
                        sharedPreferences.edit().putInt("vOYatsiSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Yatsı Vakti") && VOSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vOYatsiSesStr", "masjidalharam").apply();
                        sharedPreferences.edit().putInt("vOYatsiSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Yatsı Vakti") && VOSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vOYatsiSesStr", "misharyrasidalafasy").apply();
                        sharedPreferences.edit().putInt("vOYatsiSureInt", VOsureInt).apply();

                    }

                }

                if (VakitInfo.equals("Yatsı Vakti") && VaktindeSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vYatsiSesStr", "Kapalı").apply();

                } else if (VakitInfo.equals("Yatsı Vakti")) {

                    if (VakitInfo.equals("Yatsı Vakti") && VaktindeSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vYatsiSesStr", "kussesi1").apply();

                    } else if (VakitInfo.equals("Yatsı Vakti") && VaktindeSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vYatsiSesStr", "kussesi2").apply();

                    } else if (VakitInfo.equals("Yatsı Vakti") && VaktindeSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vYatsiSesStr", "ahmadalnafes").apply();

                    } else if (VakitInfo.equals("Yatsı Vakti") && VaktindeSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vYatsiSesStr", "masjidalharam").apply();

                    } else if (VakitInfo.equals("Yatsı Vakti") && VaktindeSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vYatsiSesStr", "misharyrasidalafasy").apply();

                    }

                }


               finish();

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

    @Override
    public void onBackPressed () {


        AlertDialog.Builder backAlert = new AlertDialog.Builder(SoundSettingsDetailActivity.this);
        backAlert.setTitle("Uyarı");
        backAlert.setMessage("Yaptığınız değişiklikler kaydesilsin mi?");
        backAlert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (VakitInfo.equals("İmsak Vakti") && VOSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vOImsakSesStr", "Kapalı").apply();

                } else if (VakitInfo.equals("İmsak Vakti")) {


                    if (VakitInfo.equals("İmsak Vakti") && VOSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vOImsakSesStr", "kussesi1").apply();
                        sharedPreferences.edit().putInt("vOImsakSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("İmsak Vakti") && VOSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vOImsakSesStr", "kussesi2").apply();
                        sharedPreferences.edit().putInt("vOImsakSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("İmsak Vakti") && VOSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vOImsakSesStr", "ahmadalnafes").apply();
                        sharedPreferences.edit().putInt("vOImsakSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("İmsak Vakti") && VOSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vOImsakSesStr", "masjidalharam").apply();
                        sharedPreferences.edit().putInt("vOImsakSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("İmsak Vakti") && VOSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vOImsakSesStr", "misharyrasidalafasy").apply();
                        sharedPreferences.edit().putInt("vOImsakSureInt", VOsureInt).apply();

                    }



                }

                if (VakitInfo.equals("İmsak Vakti") && VaktindeSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vImsakSesStr", "Kapalı").apply();

                } else if (VakitInfo.equals("İmsak Vakti")) {

                    if (VakitInfo.equals("İmsak Vakti") && VaktindeSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vImsakSesStr", "kussesi1").apply();

                    } else if (VakitInfo.equals("İmsak Vakti") && VaktindeSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vImsakSesStr", "kussesi2").apply();

                    } else if (VakitInfo.equals("İmsak Vakti") && VaktindeSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vImsakSesStr", "ahmadalnafes").apply();

                    } else if (VakitInfo.equals("İmsak Vakti") && VaktindeSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vImsakSesStr", "masjidalharam").apply();

                    } else if (VakitInfo.equals("İmsak Vakti") && VaktindeSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vImsakSesStr", "misharyrasidalafasy").apply();

                    }

                }

                if (VakitInfo.equals("Güneş Vakti") && VOSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vOGunesSesStr", "Kapalı").apply();

                } else if (VakitInfo.equals("Güneş Vakti")) {

                    if (VakitInfo.equals("Güneş Vakti") && VOSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vOGunesSesStr", "kussesi1").apply();
                        sharedPreferences.edit().putInt("vOGunesSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Güneş Vakti") && VOSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vOGunesSesStr", "kussesi2").apply();
                        sharedPreferences.edit().putInt("vOGunesSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Güneş Vakti") && VOSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vOGunesSesStr", "ahmadalnafes").apply();
                        sharedPreferences.edit().putInt("vOGunesSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Güneş Vakti") && VOSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vOGunesSesStr", "masjidalharam").apply();
                        sharedPreferences.edit().putInt("vOGunesSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Güneş Vakti") && VOSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vOGunesSesStr", "misharyrasidalafasy").apply();
                        sharedPreferences.edit().putInt("vOGunesSureInt", VOsureInt).apply();

                    }

                }

                if (VakitInfo.equals("Güneş Vakti") && VaktindeSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vGunesSesStr", "Kapalı").apply();

                } else if (VakitInfo.equals("Güneş Vakti")) {

                    if (VakitInfo.equals("Güneş Vakti") && VaktindeSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vGunesSesStr", "kussesi1").apply();

                    } else if (VakitInfo.equals("Güneş Vakti") && VaktindeSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vGunesSesStr", "kussesi2").apply();

                    } else if (VakitInfo.equals("Güneş Vakti") && VaktindeSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vGunesSesStr", "ahmadalnafes").apply();

                    } else if (VakitInfo.equals("Güneş Vakti") && VaktindeSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vGunesSesStr", "masjidalharam").apply();

                    } else if (VakitInfo.equals("Güneş Vakti") && VaktindeSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vGunesSesStr", "misharyrasidalafasy").apply();

                    }

                }


                if (VakitInfo.equals("Öğle Vakti") && VOSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vOOgleSesStr", "Kapalı").apply();

                } else if (VakitInfo.equals("Öğle Vakti")) {

                    if (VakitInfo.equals("Öğle Vakti") && VOSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vOOgleSesStr", "kussesi1").apply();
                        sharedPreferences.edit().putInt("vOOgleSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Öğle Vakti") && VOSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vOOgleSesStr", "kussesi2").apply();
                        sharedPreferences.edit().putInt("vOOgleSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Öğle Vakti") && VOSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vOOgleSesStr", "ahmadalnafes").apply();
                        sharedPreferences.edit().putInt("vOOgleSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Öğle Vakti") && VOSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vOOgleSesStr", "masjidalharam").apply();
                        sharedPreferences.edit().putInt("vOOgleSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Öğle Vakti") && VOSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vOOgleSesStr", "misharyrasidalafasy").apply();
                        sharedPreferences.edit().putInt("vOOgleSureInt", VOsureInt).apply();

                    }

                }

                if (VakitInfo.equals("Öğle Vakti") && VaktindeSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vOgleSesStr", "Kapalı").apply();

                } else if (VakitInfo.equals("Öğle Vakti")) {

                    if (VakitInfo.equals("Öğle Vakti") && VaktindeSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vOgleSesStr", "kussesi1").apply();

                    } else if (VakitInfo.equals("Öğle Vakti") && VaktindeSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vOgleSesStr", "kussesi2").apply();

                    } else if (VakitInfo.equals("Öğle Vakti") && VaktindeSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vOgleSesStr", "ahmadalnafes").apply();

                    } else if (VakitInfo.equals("Öğle Vakti") && VaktindeSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vOgleSesStr", "masjidalharam").apply();

                    } else if (VakitInfo.equals("Öğle Vakti") && VaktindeSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vOgleSesStr", "misharyrasidalafasy").apply();

                    }

                }


                if (VakitInfo.equals("İkindi Vakti") && VOSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vOIkindiSesStr", "Kapalı").apply();

                } else if (VakitInfo.equals("İkindi Vakti")) {


                    if (VakitInfo.equals("İkindi Vakti") && VOSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vOIkindiSesStr", "kussesi1").apply();
                        sharedPreferences.edit().putInt("vOIkindiSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("İkindi Vakti") && VOSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vOIkindiSesStr", "kussesi2").apply();
                        sharedPreferences.edit().putInt("vOIkindiSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("İkindi Vakti") && VOSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vOIkindiSesStr", "ahmadalnafes").apply();
                        sharedPreferences.edit().putInt("vOIkindiSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("İkindi Vakti") && VOSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vOIkindiSesStr", "masjidalharam").apply();
                        sharedPreferences.edit().putInt("vOIkindiSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("İkindi Vakti") && VOSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vOIkindiSesStr", "misharyrasidalafasy").apply();
                        sharedPreferences.edit().putInt("vOIkindiSureInt", VOsureInt).apply();

                    }

                }

                if (VakitInfo.equals("İkindi Vakti") && VaktindeSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vIkindiSesStr", "Kapalı").apply();

                } else if (VakitInfo.equals("İkindi Vakti")) {


                    if (VakitInfo.equals("İkindi Vakti") && VaktindeSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vIkindiSesStr", "kussesi1").apply();

                    } else if (VakitInfo.equals("İkindi Vakti") && VaktindeSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vIkindiSesStr", "kussesi2").apply();

                    } else if (VakitInfo.equals("İkindi Vakti") && VaktindeSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vIkindiSesStr", "ahmadalnafes").apply();

                    } else if (VakitInfo.equals("İkindi Vakti") && VaktindeSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vIkindiSesStr", "masjidalharam").apply();

                    } else if (VakitInfo.equals("İkindi Vakti") && VaktindeSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vIkindiSesStr", "misharyrasidalafasy").apply();

                    }

                }


                if (VakitInfo.equals("Akşam Vakti") && VOSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vOAksamSesStr", "Kapalı").apply();

                } else if (VakitInfo.equals("Akşam Vakti")) {


                    if (VakitInfo.equals("Akşam Vakti") && VOSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vOAksamSesStr", "kussesi1").apply();
                        sharedPreferences.edit().putInt("vOAksamSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Akşam Vakti") && VOSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vOAksamSesStr", "kussesi2").apply();
                        sharedPreferences.edit().putInt("vOAksamSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Akşam Vakti") && VOSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vOAksamSesStr", "ahmadalnafes").apply();
                        sharedPreferences.edit().putInt("vOAksamSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Akşam Vakti") && VOSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vOAksamSesStr", "masjidalharam").apply();
                        sharedPreferences.edit().putInt("vOAksamSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Akşam Vakti") && VOSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vOAksamSesStr", "misharyrasidalafasy").apply();
                        sharedPreferences.edit().putInt("vOAksamSureInt", VOsureInt).apply();

                    }

                }

                if (VakitInfo.equals("Akşam Vakti") && VaktindeSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vAksamSesStr", "Kapalı").apply();

                } else if (VakitInfo.equals("Akşam Vakti")) {

                    if (VakitInfo.equals("Akşam Vakti") && VaktindeSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vAksamSesStr", "kussesi1").apply();

                    } else if (VakitInfo.equals("Akşam Vakti") && VaktindeSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vAksamSesStr", "kussesi2").apply();

                    } else if (VakitInfo.equals("Akşam Vakti") && VaktindeSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vAksamSesStr", "ahmadalnafes").apply();

                    } else if (VakitInfo.equals("Akşam Vakti") && VaktindeSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vAksamSesStr", "masjidalharam").apply();

                    } else if (VakitInfo.equals("Akşam Vakti") && VaktindeSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vAksamSesStr", "misharyrasidalafasy").apply();

                    }

                }


                if (VakitInfo.equals("Yatsı Vakti") && VOSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vOYatsiSesStr", "Kapalı").apply();

                } else  if (VakitInfo.equals("Yatsı Vakti")){

                    if (VakitInfo.equals("Yatsı Vakti") && VOSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vOYatsiSesStr", "kussesi1").apply();
                        sharedPreferences.edit().putInt("vOYatsiSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Yatsı Vakti") && VOSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vOYatsiSesStr", "kussesi2").apply();
                        sharedPreferences.edit().putInt("vOYatsiSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Yatsı Vakti") && VOSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vOYatsiSesStr", "ahmadalnafes").apply();
                        sharedPreferences.edit().putInt("vOYatsiSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Yatsı Vakti") && VOSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vOYatsiSesStr", "masjidalharam").apply();
                        sharedPreferences.edit().putInt("vOYatsiSureInt", VOsureInt).apply();

                    } else if (VakitInfo.equals("Yatsı Vakti") && VOSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vOYatsiSesStr", "misharyrasidalafasy").apply();
                        sharedPreferences.edit().putInt("vOYatsiSureInt", VOsureInt).apply();

                    }

                }

                if (VakitInfo.equals("Yatsı Vakti") && VaktindeSesString.equals("Kapalı")) {

                    sharedPreferences.edit().putString("vYatsiSesStr", "Kapalı").apply();

                } else if (VakitInfo.equals("Yatsı Vakti")) {

                    if (VakitInfo.equals("Yatsı Vakti") && VaktindeSesString.equals("Kuş Sesi")) {

                        sharedPreferences.edit().putString("vYatsiSesStr", "kussesi1").apply();

                    } else if (VakitInfo.equals("Yatsı Vakti") && VaktindeSesString.equals("Kuş Sesi 2")) {

                        sharedPreferences.edit().putString("vYatsiSesStr", "kussesi2").apply();

                    } else if (VakitInfo.equals("Yatsı Vakti") && VaktindeSesString.equals("Ahmad Al Nafees")) {

                        sharedPreferences.edit().putString("vYatsiSesStr", "ahmadalnafes").apply();

                    } else if (VakitInfo.equals("Yatsı Vakti") && VaktindeSesString.equals("Masjid Al Haram")) {

                        sharedPreferences.edit().putString("vYatsiSesStr", "masjidalharam").apply();

                    } else if (VakitInfo.equals("Yatsı Vakti") && VaktindeSesString.equals("Mishary Rashid Alafasy")) {

                        sharedPreferences.edit().putString("vYatsiSesStr", "misharyrasidalafasy").apply();

                    }

                }

                finish();

            }




        });

        backAlert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                finish();

            }
        });

        backAlert.show();





    }

    public void getBildirimSound() {

        vOImsakSureInt = sharedPreferences.getInt("vOImsakSureInt", 45);
        vOImsakSesStr = sharedPreferences.getString("vOImsakSesStr", "ahmadalnafes");
        vImsakSesStr = sharedPreferences.getString("vImsakSesStr", "ahmadalnafes");

        vOGunesSureInt = sharedPreferences.getInt("vOGunesSureInt", 45);
        vOGunesSesStr = sharedPreferences.getString("vOGunesSesStr", "ahmadalnafes");
        vGunesSesStr = sharedPreferences.getString("vGunesSesStr", "ahmadalnafes");

        vOOgleSureInt = sharedPreferences.getInt("vOOgleSureInt", 45);
        vOOgleSesStr = sharedPreferences.getString("vOOgleSesStr", "ahmadalnafes");
        vOgleSesStr = sharedPreferences.getString("vOgleSesStr", "ahmadalnafes");

        vOIkindiSureInt = sharedPreferences.getInt("vOIkindiSureInt", 45);
        vOIkindiSesStr = sharedPreferences.getString("vOIkindiSesStr", "ahmadalnafes");
        vIkindiSesStr = sharedPreferences.getString("vIkindiSesStr", "ahmadalnafes");

        vOAksamSureInt = sharedPreferences.getInt("vOAksamSureInt", 45);
        vOAksamSesStr = sharedPreferences.getString("vOAksamSesStr", "ahmadalnafes");
        vAksamSesStr = sharedPreferences.getString("vAksamSesStr", "ahmadalnafes");

        vOYatsiSureInt = sharedPreferences.getInt("vOYatsiSureInt", 45);
        vOYatsiSesStr = sharedPreferences.getString("vOYatsiSesStr", "ahmadalnafes");
        vYatsiSesStr = sharedPreferences.getString("vYatsiSesStr", "ahmadalnafes");

        String voTitle;
        String vtitle;

        String notSesi1 = "Kuş Sesi";
        String notSesi2 = "Kuş Sesi 2";
        String notSesi3 = "Ahmad Al Nafees";
        String notSesi4 = "Masjid Al Haram";
        String notSesi5 = "Mishary Rashid Alafasy";

        String notSesi11 = "kussesi1";
        String notSesi22 = "kussesi2";
        String notSesi33 = "ahmadalnafes";
        String notSesi44 = "masjidalharam";
        String notSesi55 = "misharyrasidalafasy";




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

            } else if (voTitle.equals("Kapalı")) {

                binding.vaktindenOnceSoundTitle.setText("Kapalı");
                binding.vaktindenOnceSwitch.setChecked(false);




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

            } else if (vtitle.equals("Kapalı")) {

                binding.vaktindenOnceSoundTitle.setText("Kapalı");
                binding.vaktindeSwitch.setChecked(false);


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

            } else if (voTitle.equals("Kapalı")) {

                binding.vaktindenOnceSoundTitle.setText("Kapalı");
                binding.vaktindenOnceSwitch.setChecked(false);



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
            }  else if (vtitle.equals("Kapalı")) {

                binding.vaktindenOnceSoundTitle.setText("Kapalı");
                binding.vaktindeSwitch.setChecked(false);


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

            } else if (voTitle.equals("Kapalı")) {

                binding.vaktindenOnceSoundTitle.setText("Kapalı");
                binding.vaktindenOnceSwitch.setChecked(false);



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
            }  else if (vtitle.equals("Kapalı")) {

                binding.vaktindenOnceSoundTitle.setText("Kapalı");
                binding.vaktindeSwitch.setChecked(false);


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
            } else if (voTitle.equals("Kapalı")) {

                binding.vaktindenOnceSoundTitle.setText("Kapalı");
                binding.vaktindenOnceSwitch.setChecked(false);



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
            }  else if (vtitle.equals("Kapalı")) {

                binding.vaktindenOnceSoundTitle.setText("Kapalı");
                binding.vaktindeSwitch.setChecked(false);


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
            } else if (voTitle.equals("Kapalı")) {

                binding.vaktindenOnceSoundTitle.setText("Kapalı");
                binding.vaktindenOnceSwitch.setChecked(false);



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
            }  else if (vtitle.equals("Kapalı")) {

                binding.vaktindenOnceSoundTitle.setText("Kapalı");
                binding.vaktindeSwitch.setChecked(false);


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
            } else if (voTitle.equals("Kapalı")) {

                binding.vaktindenOnceSoundTitle.setText("Kapalı");
                binding.vaktindenOnceSwitch.setChecked(false);



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
            }  else if (vtitle.equals("Kapalı")) {

                binding.vaktindenOnceSoundTitle.setText("Kapalı");
                binding.vaktindeSwitch.setChecked(false);


            }

        }


    }

}