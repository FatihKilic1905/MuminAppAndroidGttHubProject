package com.fatihkilic.muminappandroid.Kutuphane;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityEzanDuasiBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityVedaHutbesiBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class EzanDuasiActivity extends AppCompatActivity {

    private ActivityEzanDuasiBinding binding;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ezan_duasi);

        binding = ActivityEzanDuasiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().setTitle("Ezan Duası");

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = binding.adView;
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        String arapca = "أَللّٰهُمَّ رَبَّ هٰذِهِ الدَّعْوَةِ التَّآمَّةِ وَالصَّلاَةِ الْقَآئِمَةِ اٰتِ مُحَمَّدًا الْوَسِيلَةَ وَالْفَضِيلَةَ وَابْعَثْهُ مَقَامًا مَحْمُودًا الَّذ۪ى وَعَدْتَهُ";

        String tukce = "Allahumme Rabbe hazihi'd-da'veti't-tamme. Vesselatil kâimeti ati Muhammedenil vesilete vel fazilete ved-dereceter-refîah. Vebashu makamen Mahmudenillezi veadteh. İnneke lâ tühlifü'l-mîâd.";

        String Meal = "'Ey bu eksiksiz davetin ve kılınan namazın sahibi! Muhammed'e vesîle'yi ve fazîleti ver. O'nu, vaat ettiğin Makam-ı Mahmûd üzere dirilt'";

        binding.textView.setText(arapca);

        Button ArapcaButton = binding.arapcaButton;
        ArapcaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.textView.setText(arapca);
            }
        });

        Button turkceButton = binding.turkceButton;
        turkceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.textView.setText(tukce);
            }
        });

        Button mealButton = binding.mealButton;
        mealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.textView.setText(Meal);
            }
        });

    }



}