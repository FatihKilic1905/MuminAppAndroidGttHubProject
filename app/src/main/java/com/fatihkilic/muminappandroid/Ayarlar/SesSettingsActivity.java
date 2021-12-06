package com.fatihkilic.muminappandroid.Ayarlar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fatihkilic.muminappandroid.EzanVaktiCoplete.EzanVaktiActivity;
import com.fatihkilic.muminappandroid.MainActivity;
import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivitySesSettingsBinding;
import com.fatihkilic.muminappandroid.databinding.ActivitySoundSettingsDetailBinding;

public class SesSettingsActivity extends AppCompatActivity {

    private ActivitySesSettingsBinding binding;

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

    SharedPreferences sharedPreferences;

    String IlkGirisInfo = "ikinciGiris";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ses_settings);

        binding = ActivitySesSettingsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        sharedPreferences = this.getSharedPreferences("com.fatihkilic.muminappandroid", Context.MODE_PRIVATE);
        getSupportActionBar().setTitle("Ses Ayarları");

        getBildirimSound();

        Intent getIlkGirisIntent = getIntent();
        IlkGirisInfo = getIlkGirisIntent.getStringExtra("ilkGiris");

        System.out.println(IlkGirisInfo);

        if (IlkGirisInfo.equals("")) {

            binding.saveButton.setVisibility(View.INVISIBLE);


        } else {

            binding.saveButton.setVisibility(View.VISIBLE);

        }

        Button saveButton = binding.saveButton;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });


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

        binding.imsakVOLabel.setText("Vaktinden " + vOImsakSureInt + " Dakika Önce");
        binding.gunesVOTitle.setText("Vaktinden " + vOGunesSureInt + " Dakika Önce");
        binding.ogleVOtitle.setText("Vaktinden " + vOOgleSureInt + " Dakika Önce");
        binding.ikindiVOTitle.setText("Vaktinden " + vOIkindiSureInt + " Dakika Önce");
        binding.aksamVOTitle.setText("Vaktinden " + vOAksamSureInt + " Dakika Önce");
        binding.yatsiVOTitle.setText("Vaktinden " + vOYatsiSureInt + " Dakika Önce");

        if (vOImsakSesStr.equals(notSesi11)) {

            binding.imsakVOSoundTitle.setText(notSesi1);

        } else if (vOImsakSesStr.equals(notSesi22)) {

            binding.imsakVOSoundTitle.setText(notSesi2);

        } else if (vOImsakSesStr.equals(notSesi33)) {

            binding.imsakVOSoundTitle.setText(notSesi3);

        } else if (vOImsakSesStr.equals(notSesi44)) {

            binding.imsakVOSoundTitle.setText(notSesi4);

        } else if (vOImsakSesStr.equals(notSesi55)) {

            binding.imsakVOSoundTitle.setText(notSesi5);
        }

        if (vImsakSesStr.equals(notSesi11)) {

            binding.imsakVSoundTitle.setText(notSesi1);

        } else if (vImsakSesStr.equals(notSesi22)) {

            binding.imsakVSoundTitle.setText(notSesi2);

        } else if (vImsakSesStr.equals(notSesi33)) {

            binding.imsakVSoundTitle.setText(notSesi3);

        } else if (vImsakSesStr.equals(notSesi44)) {

            binding.imsakVSoundTitle.setText(notSesi4);

        } else if (vImsakSesStr.equals(notSesi55)) {

            binding.imsakVSoundTitle.setText(notSesi5);
        }




        if (vOYatsiSesStr.equals(notSesi11)) {

            binding.yatsiVOSoundTitle.setText(notSesi1);

        } else if (vOYatsiSesStr.equals(notSesi22)) {

            binding.yatsiVOSoundTitle.setText(notSesi2);

        } else if (vOYatsiSesStr.equals(notSesi33)) {

            binding.yatsiVOSoundTitle.setText(notSesi3);

        } else if (vOYatsiSesStr.equals(notSesi44)) {

            binding.yatsiVOSoundTitle.setText(notSesi4);

        } else if (vOYatsiSesStr.equals(notSesi55)) {

            binding.yatsiVOSoundTitle.setText(notSesi5);
        }

        if (vYatsiSesStr.equals(notSesi11)) {

            binding.yatsiVSoundTitle.setText(notSesi1);

        } else if (vImsakSesStr.equals(notSesi22)) {

            binding.yatsiVSoundTitle.setText(notSesi2);

        } else if (vImsakSesStr.equals(notSesi33)) {

            binding.yatsiVSoundTitle.setText(notSesi3);

        } else if (vImsakSesStr.equals(notSesi44)) {

            binding.yatsiVSoundTitle.setText(notSesi4);

        } else if (vImsakSesStr.equals(notSesi55)) {

            binding.yatsiVSoundTitle.setText(notSesi5);
        }




        if (vOGunesSesStr.equals(notSesi11)) {

            binding.gunesVOSoundTitle.setText(notSesi1);

        } else if (vOGunesSesStr.equals(notSesi22)) {

            binding.gunesVOSoundTitle.setText(notSesi2);

        } else if (vOGunesSesStr.equals(notSesi33)) {

            binding.gunesVOSoundTitle.setText(notSesi3);

        } else if (vOGunesSesStr.equals(notSesi44)) {

            binding.gunesVOSoundTitle.setText(notSesi4);

        } else if (vOGunesSesStr.equals(notSesi55)) {

            binding.gunesVOSoundTitle.setText(notSesi5);
        }

        if (vGunesSesStr.equals(notSesi11)) {

            binding.gunesVSoundTitle.setText(notSesi1);

        } else if (vGunesSesStr.equals(notSesi22)) {

            binding.gunesVSoundTitle.setText(notSesi2);

        } else if (vGunesSesStr.equals(notSesi33)) {

            binding.gunesVSoundTitle.setText(notSesi3);

        } else if (vGunesSesStr.equals(notSesi44)) {

            binding.gunesVSoundTitle.setText(notSesi4);

        } else if (vGunesSesStr.equals(notSesi55)) {

            binding.gunesVSoundTitle.setText(notSesi5);
        }




        if (vOOgleSesStr.equals(notSesi11)) {

            binding.ogleVOSoundTitle.setText(notSesi1);

        } else if (vOOgleSesStr.equals(notSesi22)) {

            binding.ogleVOSoundTitle.setText(notSesi2);

        } else if (vOOgleSesStr.equals(notSesi33)) {

            binding.ogleVOSoundTitle.setText(notSesi3);

        } else if (vOOgleSesStr.equals(notSesi44)) {

            binding.ogleVOSoundTitle.setText(notSesi4);

        } else if (vOOgleSesStr.equals(notSesi55)) {

            binding.ogleVOSoundTitle.setText(notSesi5);
        }

        if (vOgleSesStr.equals(notSesi11)) {

            binding.ogleVSoundTitle.setText(notSesi1);

        } else if (vOgleSesStr.equals(notSesi22)) {

            binding.ogleVSoundTitle.setText(notSesi2);

        } else if (vOgleSesStr.equals(notSesi33)) {

            binding.ogleVSoundTitle.setText(notSesi3);

        } else if (vOgleSesStr.equals(notSesi44)) {

            binding.ogleVSoundTitle.setText(notSesi4);

        } else if (vOgleSesStr.equals(notSesi55)) {

            binding.ogleVSoundTitle.setText(notSesi5);
        }




        if (vOIkindiSesStr.equals(notSesi11)) {

            binding.ikindiVOSoundTitle.setText(notSesi1);

        } else if (vOIkindiSesStr.equals(notSesi22)) {

            binding.ikindiVOSoundTitle.setText(notSesi2);

        } else if (vOIkindiSesStr.equals(notSesi33)) {

            binding.ikindiVOSoundTitle.setText(notSesi3);

        } else if (vOIkindiSesStr.equals(notSesi44)) {

            binding.ikindiVOSoundTitle.setText(notSesi4);

        } else if (vOIkindiSesStr.equals(notSesi55)) {

            binding.ikindiVOSoundTitle.setText(notSesi5);
        }

        if (vIkindiSesStr.equals(notSesi11)) {

            binding.ikindiVSoundTitle.setText(notSesi1);

        } else if (vIkindiSesStr.equals(notSesi22)) {

            binding.ikindiVSoundTitle.setText(notSesi2);

        } else if (vIkindiSesStr.equals(notSesi33)) {

            binding.ikindiVSoundTitle.setText(notSesi3);

        } else if (vIkindiSesStr.equals(notSesi44)) {

            binding.ikindiVSoundTitle.setText(notSesi4);

        } else if (vIkindiSesStr.equals(notSesi55)) {

            binding.ikindiVSoundTitle.setText(notSesi5);
        }




        if (vOAksamSesStr.equals(notSesi11)) {

            binding.aksamVOSoundTitle.setText(notSesi1);

        } else if (vOAksamSesStr.equals(notSesi22)) {

            binding.aksamVOSoundTitle.setText(notSesi2);

        } else if (vOAksamSesStr.equals(notSesi33)) {

            binding.aksamVOSoundTitle.setText(notSesi3);

        } else if (vOAksamSesStr.equals(notSesi44)) {

            binding.aksamVOSoundTitle.setText(notSesi4);

        } else if (vOAksamSesStr.equals(notSesi55)) {

            binding.aksamVOSoundTitle.setText(notSesi5);
        }

        if (vAksamSesStr.equals(notSesi11)) {

            binding.aksamVSoundTitle.setText(notSesi1);

        } else if (vAksamSesStr.equals(notSesi22)) {

            binding.aksamVSoundTitle.setText(notSesi2);

        } else if (vAksamSesStr.equals(notSesi33)) {

            binding.aksamVSoundTitle.setText(notSesi3);

        } else if (vAksamSesStr.equals(notSesi44)) {

            binding.aksamVSoundTitle.setText(notSesi4);

        } else if (vAksamSesStr.equals(notSesi55)) {

            binding.aksamVSoundTitle.setText(notSesi5);
        }





    }



}