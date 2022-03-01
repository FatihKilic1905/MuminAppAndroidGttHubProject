package com.fatihkilic.muminappandroid.Kutuphane;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityTesbihatBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityVedaHutbesiBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.intellij.lang.annotations.Language;

public class TesbihatActivity extends AppCompatActivity {

    private ActivityTesbihatBinding binding;
    private AdView mAdView;
    String language = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tesbihat);


        binding = ActivityTesbihatBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().setTitle("Tesbihat");

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = binding.adView;
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        binding.sabahButton.setVisibility(View.INVISIBLE);
        binding.ogleButton.setVisibility(View.INVISIBLE);
        binding.ikindiButton.setVisibility(View.INVISIBLE);
        binding.aksamButton.setVisibility(View.INVISIBLE);
        binding.yatsiButton.setVisibility(View.INVISIBLE);
        binding.tesbihatTextview.setVisibility(View.INVISIBLE);
        binding.sabahButton.setBackground(getResources().getDrawable(R.drawable.corner_4_radius));
        binding.sabahButton.setTextColor(getResources().getColor(R.color.white));
        binding.ogleButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
        binding.ogleButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
        binding.ikindiButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
        binding.ikindiButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
        binding.aksamButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
        binding.aksamButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
        binding.yatsiButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
        binding.yatsiButton.setTextColor(getResources().getColor(R.color.muminAppGreen));





        Button arapcaButton = binding.arapcaButton;
        arapcaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                language = "ARAP";
                binding.sabahButton.setVisibility(View.VISIBLE);
                binding.ogleButton.setVisibility(View.VISIBLE);
                binding.ikindiButton.setVisibility(View.VISIBLE);
                binding.aksamButton.setVisibility(View.VISIBLE);
                binding.yatsiButton.setVisibility(View.VISIBLE);
                binding.arapcaButton.setVisibility(View.INVISIBLE);
                binding.turkceButton.setVisibility(View.INVISIBLE);
                binding.tesbihatTextview.setVisibility(View.VISIBLE);
                sabahArapca();


            }
        });

        Button turkceButton = binding.turkceButton;
        turkceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                language = "TUR";

                binding.sabahButton.setVisibility(View.VISIBLE);
                binding.ogleButton.setVisibility(View.VISIBLE);
                binding.ikindiButton.setVisibility(View.VISIBLE);
                binding.aksamButton.setVisibility(View.VISIBLE);
                binding.yatsiButton.setVisibility(View.VISIBLE);
                binding.arapcaButton.setVisibility(View.INVISIBLE);
                binding.turkceButton.setVisibility(View.INVISIBLE);
                binding.tesbihatTextview.setVisibility(View.VISIBLE);
                sabahturkce();


            }
        });

        Button sabahButton = binding.sabahButton;
        sabahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (language.equals("TUR")) {

                    sabahturkce();

                } else if (language.equals("ARAP")) {

                    sabahArapca();

                }

                binding.sabahButton.setBackground(getResources().getDrawable(R.drawable.corner_4_radius));
                binding.sabahButton.setTextColor(getResources().getColor(R.color.white));
                binding.ogleButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                binding.ogleButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                binding.ikindiButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                binding.ikindiButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                binding.aksamButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                binding.aksamButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                binding.yatsiButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                binding.yatsiButton.setTextColor(getResources().getColor(R.color.muminAppGreen));



            }
        });

        Button ogleButton = binding.ogleButton;
        ogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (language.equals("TUR")) {

                    ogleturkce();

                } else if (language.equals("ARAP")) {

                    ogleArapca();

                }

                binding.sabahButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                binding.sabahButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                binding.ogleButton.setBackground(getResources().getDrawable(R.drawable.corner_4_radius));
                binding.ogleButton.setTextColor(getResources().getColor(R.color.white));
                binding.ikindiButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                binding.ikindiButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                binding.aksamButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                binding.aksamButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                binding.yatsiButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                binding.yatsiButton.setTextColor(getResources().getColor(R.color.muminAppGreen));



            }
        });

        Button ikindiButton = binding.ikindiButton;
        ikindiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (language.equals("TUR")) {

                    ikinditurkce();

                } else if (language.equals("ARAP")) {

                    ikindiArapca();

                }

                binding.sabahButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                binding.sabahButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                binding.ogleButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                binding.ogleButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                binding.ikindiButton.setBackground(getResources().getDrawable(R.drawable.corner_4_radius));
                binding.ikindiButton.setTextColor(getResources().getColor(R.color.white));
                binding.aksamButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                binding.aksamButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                binding.yatsiButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                binding.yatsiButton.setTextColor(getResources().getColor(R.color.muminAppGreen));



            }
        });

        Button aksamButton = binding.aksamButton;
        aksamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (language.equals("TUR")) {

                    aksamturkce();

                } else if (language.equals("ARAP")) {

                    aksamArapca();

                }

                binding.sabahButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                binding.sabahButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                binding.ogleButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                binding.ogleButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                binding.ikindiButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                binding.ikindiButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                binding.aksamButton.setBackground(getResources().getDrawable(R.drawable.corner_4_radius));
                binding.aksamButton.setTextColor(getResources().getColor(R.color.white));
                binding.yatsiButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                binding.yatsiButton.setTextColor(getResources().getColor(R.color.muminAppGreen));



            }
        });

        Button yatsiButton = binding.yatsiButton;
        yatsiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (language.equals("TUR")) {

                    yatsiturkce();

                } else if (language.equals("ARAP")) {

                    yatsiArapca();

                }

                binding.sabahButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                binding.sabahButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                binding.ogleButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                binding.ogleButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                binding.ikindiButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                binding.ikindiButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                binding.aksamButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                binding.aksamButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                binding.yatsiButton.setBackground(getResources().getDrawable(R.drawable.corner_4_radius));
                binding.yatsiButton.setTextColor(getResources().getColor(R.color.white));




            }
        });





    }


    public void sabahArapca () {


        String tesbihatStr = "Sabah namazının farzını kılıb, selamı müteakib:\n" +
                "\n" +
                "اَللّٰهُمَّ اَنْتَ السَّلَامُ وَ مِنْكَ السَّلَامُ تَبَارَكْتَ يَا ذَا الْجَلَالِ وَ اْلاِكْرَامِ\n" +
                "\n" +
                "Dedikten sonra elleri yukarı kaldırıp, el açarak:\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَ عَلَٓى اٰلِ سَيِّدِنَا مُحَمَّدٍ صَلَاةً تُنْج۪ينَا بِهَا مِنْ جَم۪يعِ الْاَهْوَالِ وَ اْلاٰفَاتِ وَ تَقْض۪ى لَنَا بِهَا جَم۪يعَ الْحَاجَاتِ وَ تُطَهِّرُنَا بِهَا مِنْ جَم۪يعِ السَّيِّئَاتِ وَ تَرْفَعُنَا بِهَا عِنْدَكَ اَعْلَى الدَّرَجَاتِ وَ تُبَلِّغُنَا بِهَٓا اَقْصَى الْغَايَاتِ مِنْ جَم۪يعِ الْخَيْرَاتِ فِى الْحَيَاةِ وَ بَعْدَ الْمَمَاتِ اٰم۪ينَ يَا مُج۪يبَ الدَّعَوَاتِ وَالْحَمْدُ لِلّٰهِ رَبِّ الْعَالَم۪ينَ\n" +
                "\n" +
                "Dedikten sonra\n" +
                "\n" +
                "اَللّٰهُمَّ اِنَّا نُقَدِّمُ اِلَيْكَ بَيْنَ يَدَىْ كُلِّ نَفَسٍ وَ لَمْحَةٍ وَ لَحْظَةٍ وَ طَرْفَةٍ يَطْرِفُ بِهَٓا اَهْلُ السَّمٰوَاتِ وَ اَهْلُ الْاَرَض۪ينَ شَهَادَةً اَشْهَدُ اَنْ\n" +
                "\n" +
                "Buraya kadar bir defa deyip\n" +
                "\n" +
                "لَٓا اِلٰهَ اِلَّا اللّٰهُ وَحْدَهُ لَٓا شَر۪يكَ لَهُ لَهُ الْمُلْكُ وَ لَهُ الْحَمْدُ يُحْي۪ى وَ يُم۪يتُ وَ هُوَ حَىٌّ لَا يَمُوتُ بِيَدِهِ الْخَيْرُ وَ هُوَ عَلٰى كُلِّ شَىْءٍ قَد۪يرٌ\n" +
                "\n" +
                "On defa tekrar edilerek onuncusunda\n" +
                "\n" +
                "وَ اِلَيْهِ الْمَص۪يرُ\n" +
                "\n" +
                "Deyip, elleri kaldırıp avuçları aşağıya çevirerek\n" +
                "\n" +
                "اَللّٰهُمَّ اَجِرْنَا مِنَ النَّارِ\n" +
                "\n" +
                "3-5-7 defa tekrar edilir. Müteakiben:\n" +
                "\n" +
                "اَللّٰهُمَّ اَجِرْنَا مِنْ كُلِّ نَارٍ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ فِتْنَةِ الدّ۪ينِيَّةِ وَ الدُّنْيَوِيَّةِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ فِتْنَةِ اٰخِرِ الزَّمَانِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ فِتْنَةِ الْمَس۪يحِ الدَّجَّالِ وَ السُّفْيَانِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنَ الضَّلَالَاتِ وَ الْبِدْعِيَّاتِ وَ الْبَلِيَّاتِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ شَرِّ النَّفْسِ الْاَمَّارَةِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ شُرُورِ النُّفُوسِ الْاَمَّارَاتِ الْفِرْعَوْنِيَّةِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ شَرِّ النِّسَٓاءِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ بَلَٓاءِ النِّسَٓاءِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ فِتْنَةِ النِّسَٓاءِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ عَذَابِ الْقَبْرِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ عَذَابِ يَوْمِ الْقِيٰمَةِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ عَذَابِ جَهَنَّمَ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ عَذَابِ قَهْرِكَ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ نَارِ قَهْرِكَ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ عَذَابِ الْقَبْرِ وَ ال۪نّيرَانِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنَ الرِّيَاءِ وَ السُّمْعَةِ وَ الْعُجُبِ وَ الْفَخْرِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ تَجَاوُزِ الْمُلْحِد۪ينَ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ شَرِّ الْمُنَافِق۪ينَ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ فِتْنَةِ الْفَاسِق۪ينَ ❁ اَللّٰهُمَّ اَجِرْنَا وَ اَجِرْ وَالِدَيْنَا وَ طَلَبَةَ رَسَٓائِلِ النُّورِ الصَّادِق۪ينَ ف۪ى خِدْمَةِ الْقُرْاٰنِ وَ اْلا۪يمَانِ وَ اَحْبَابَنَا الْمُؤْمِن۪ينَ الْمُخْلِص۪ينَ وَ اَقْرِبَٓائَنَا وَ اَجْدَادَنَا مِنَ النَّارِ\n" +
                "\n" +
                "Elleri yukarı çevirerek\n" +
                "\n" +
                "بِعَفْوِكَ يَا مُج۪يرُ بِفَضْلِكَ يَا غَفَّارُ ❁ اَللّٰهُمَّ اَدْخِلْنَا الْجَنَّةَ مَعَ الْاَبْرَارِ ❁ اَللّٰهُمَّ اَدْخِلْنَا الْجَنَّةَ مَعَ الْاَبْرَارِ ❁ اَللّٰهُمَّ اَدْخِلْنَا وَ اَدْخِلْ اُسْتَادَنَا سَع۪يدَ النُّورْس۪ى رَضِىَ اللّٰهُ عَنْهُ وَ وَالِدَيْنَا وَ طَلَبَةَ رَسَٓائِلِ النُّورِ الصَّادِق۪ينَ وَ اِخْوَانَنَا وَ اَخَوَاتِنَا وَ اَقْرِبَٓائَنَا وَ اَجْدَادَنَا وَ اَحْبَابَنَا الْمُؤْمِن۪ينَ الْمُخْلِص۪ينَ ف۪ى خِدْمَةِ اْلا۪يمَانِ وَ الْقُرْاٰنِ اَلْجَنَّةَ مَعَ الْاَبْرَارِ صَلِّ عَلٰى نَبِيِّكَ الْمُخْتَارِ وَ اٰلِه۪ الْاَطْهَارِ وَ اَصْحَابِهِ الْاَخْيَارِ وَ سَلِّمْ مَادَامَ الَّيْلُ وَ النَّهَارُ اٰم۪ينَ وَالْحَمْدُ لِلّٰهِ رَبِّ الْعَالَم۪ينَ\n" +
                "\n" +
                "bundan sonra şu dua okunur\n" +
                "\n" +
                "سُبْحَانَ اللّٰهِ وَالْحَمْدُ لِلّٰهِ وَلَا إِلَهَ إِلَّا اللَّهُ وَ اللّٰهُ اَكْبَرْ وَلَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللّٰهِ الْعَلِيِّ الْعَظِيمِْ\n" +
                "\n" +
                "Âyet-ül Kürsi okunur\n" +
                "\n" +
                "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّح۪يمِ\n" +
                "\n" +
                "اَللّٰهُ لَٓا اِلٰهَ اِلَّا هُوَۚ اَلْحَيُّ الْقَيُّومُۚ لَا تَأْخُذُهُ سِنَةٌ وَلَا نَوْمٌۜ لَهُ مَا فِي السَّمٰوَاتِ وَمَا فِي الْاَرْضِۜ مَنْ ذَا الَّذ۪ي يَشْفَعُ عِنْدَهُٓ اِلَّا بِاِذْنِه۪ۜ يَعْلَمُ مَا بَيْنَ اَيْد۪يهِمْ وَمَا خَلْفَهُمْۚ وَلَا يُح۪يطُونَ بِشَيْءٍ مِنْ عِلْمِه۪ٓ اِلَّا بِمَا شَٓاءَۚ وَسِعَ كُرْسِيُّهُ السَّمٰوَاتِ وَالْاَرْضَۚ وَلَا يَؤُ۫دُهُ حِفْظُهُمَاۚ وَهُوَ الْعَلِيُّ الْعَظ۪يمُ\n" +
                "\n" +
                "ardından 33 defa\n" +
                "\n" +
                "سُبْحَانَ اللّٰهِ\n" +
                "\n" +
                "33 defa\n" +
                "\n" +
                "اَلْحَمْدُ لِلّٰهِ\n" +
                "\n" +
                "33 defa\n" +
                "\n" +
                "اَللّٰهُ اَكْبَرُ\n" +
                "\n" +
                "Dedikten sonra namazın duası yapılır. Ve\n" +
                "\n" +
                "فَاعْلَمْ اَنَّهُ\n" +
                "\n" +
                "Bir defa ve\n" +
                "\n" +
                "لَٓا اِلٰهَ اِلَّا اللّٰهُ\n" +
                "\n" +
                "33 defa okunur.\n" +
                "\n" +
                "{Haşiye: Sabah ve yatsı namazlarından sonra isteyen 100 defa okuyabilir.}\n" +
                "\n" +
                "Ve sonra:\n" +
                "\n" +
                "مُحَمَّدٌ رَسُولُ اللّٰهِ صَلَّى اللّٰهُ تَعَالٰى عَلَيْهِ وَ سَلَّمَ\n" +
                "\n" +
                "Deyip yalnız sabah namazına mahsus 10 defa:\n" +
                "\n" +
                "لَٓا اِلٰهَ اِلَّا اللّٰهُ اَلْمَلِكُ الْحَقُّ الْمُب۪ينُ مُحَمَّدٌ رَسُولُ اللّٰهِ صَادِقُ الْوَعْدِ الْاَم۪ينُ\n" +
                "\n" +
                "Der ve ba’dehu\n" +
                "\n" +
                "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّح۪يمِ\n" +
                "\n" +
                "اِنَّ اللّٰهَ وَ مَلَٓئِكَتَهُ يُصَلُّونَ عَلَى النَّبِىِّ يَٓا اَيُّهَا الَّذ۪ينَ اٰمَنُوا صَلُّوا عَلَيْهِ وَ سَلِّمُوا تَسْل۪يمًا ❁ لَبَّيْكَ\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَعَلَٓى اٰلِ سَيِّدِنَا مُحَمَّدٍ بِعَدَدِ كُلِّ دَٓاءٍ وَدَوَٓاءٍ وَبَارِكْ وَسَلِّمْ عَلَيْهِ وَعَلَيْهِمْ كَث۪يرًا\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَعَلَٓى اٰلِ سَيِّدِنَا مُحَمَّدٍ بِعَدَدِ كُلِّ دَٓاءٍ وَدَوَٓاءٍ وَبَارِكْ وَسَلِّمْ عَلَيْهِ وَعَلَيْهِمْ كَث۪يرًا\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَعَلَٓى اٰلِ سَيِّدِنَا مُحَمَّدٍ بِعَدَدِ كُلِّ دَٓاءٍ وَدَوَٓاءٍ وَبَارِكْ وَسَلِّمْ عَلَيْهِ وَعَلَيْهِمْ كَث۪يرًا كَث۪يرًا\n" +
                "\n" +
                "صَلِّ وَ سَلِّمْ يَا رَبِّ عَلٰى حَب۪يبِكَ مُحَمَّدٍ وَ عَلٰى جَم۪يعِ الْاَنْبِيَٓاءِ وَ الْمُرْسَل۪ينَ وَ عَلَٓى اٰلِ كُلٍّ وَ صَحْبِ كُلٍّ اَجْمَع۪ينَ اٰم۪ينَ وَالْحَمْدُ لِلّٰهِ رَبِّ الْعَالَم۪ينَ\n" +
                "\n" +
                "اَلْفُ اَلْفِ صَلَاةٍ وَ اَلْفُ اَلْفِ سَلَامٍ عَلَيْكَ يَا رَسُولَ اللّٰهِ\n" +
                "\n" +
                "اَلْفُ اَلْفِ صَلَاةٍ وَ اَلْفُ اَلْفِ سَلَامٍ عَلَيْكَ يَا حَب۪يبَ اللّٰهِ\n" +
                "\n" +
                "اَلْفُ اَلْفِ صَلَاةٍ وَ اَلْفُ اَلْفِ سَلَامٍ عَلَيْكَ يَٓا اَم۪ينَ وَحْىِ اللّٰهِ\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ وَ سَلِّمْ وَ بَارِكْ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَعَلَٓى اٰلِه۪ وَ اَصْحَابِه۪ بِعَدَدِ اَوْرَاقِ الْاَشْجَارِ وَ اَمْوَاجِ الْبِحَارِ وَ قَطَرَاتِ الْاَمْطَارِ وَ اغْفِرْلَنَا وَ ارْحَمْنَا وَ الْطُفْ بِنَا وَ بِاُسْتَادِنَا سَع۪يدِ النُّورْس۪ى رَضِىَ اللّٰهُ عَنْهُ وَ وَالِدَيْنَا وَ بِطَلَبَةِ رَسَٓائِلِ النُّورِ الصَّادِق۪ينَ يَٓا اِلٰهَنَا بِكُلِّ صَلَاةٍ مِنْهَٓا اَشْهَدُ اَنْ لَٓا اِلٰهَ اِلَّا اللّٰهُ وَ اَشْهَدُ اَنَّ مُحَمَّدًا رَسُولُ اللّٰهِ صَلَّى اللّٰهُ تَعَالٰى عَلَيْهِ وَ سَلَّمْ\n" +
                "\n" +
                "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّح۪يمِ\n" +
                "\n" +
                "سُبْحَانَك يَٓا ﴿اَللّٰهُ﴾ تَعَالَيْتَ يَا ﴿رَحْمٰنُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿رَح۪يمُ﴾ تَعَالَيْتَ يَا ﴿كَر۪يمُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿حَم۪يدُ﴾ تَعَالَيْتَ يَا ﴿حَك۪يمُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿مَج۪يدُ﴾ تَعَالَيْتَ يَا ﴿مَلِكُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰن\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿قُدُّوسُ﴾ تَعَالَيْتَ يَا ﴿سَلَامُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿مُؤْمِنُ﴾ تَعَالَيْتَ يَا ﴿مُهَيْمِنُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿عَز۪يزُ﴾ تَعَالَيْتَ يَا ﴿جَبَّارُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿مُتَكَبِّرُ﴾ تَعَالَيْتَ يَا ﴿خَالِقُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَٓا ﴿اَوَّلُ﴾ تَعَالَيْتَ يَٓا ﴿اٰخِرُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿ظَاهِرُ﴾ تَعَالَيْتَ يَا ﴿بَاطِنُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿بَارِىءُ﴾ تَعَالَيْتَ يَا ﴿مُصَوِّرُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿تَوَّابُ﴾ تَعَالَيْتَ يَا ﴿وَهَّابُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿بَاعِثُ﴾ تَعَالَيْتَ يَا ﴿وَارِثُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿قَد۪يمُ﴾ تَعَالَيْتَ يَا ﴿مُق۪يمُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿فَرْدُ﴾ تَعَالَيْتَ يَا ﴿وِتْرُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿نُورُ﴾ تَعَالَيْتَ يَا ﴿سَتَّارُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿جَل۪يلُ﴾ تَعَالَيْتَ يَا ﴿جَم۪يلُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿قَاهِرُ﴾ تَعَالَيْتَ يَا ﴿قَادِرُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿مَل۪يكُ﴾ تَعَالَيْتَ يَا ﴿مُقْتَدِرُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿عَل۪يمُ﴾ تَعَالَيْتَ يَا ﴿عَلَّا مُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿عَظ۪يمُ﴾ تَعَالَيْتَ يَا ﴿غَفُورُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿حَل۪يمُ﴾ تَعَالَيْتَ يَا ﴿وَدُودُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿شَه۪يدُ﴾ تَعَالَيْتَ يَا ﴿شَاهِدُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿كَب۪يرُ﴾ تَعَالَيْتَ يَا ﴿مُتَعَالُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿نُورُ﴾ تَعَالَيْتَ يَا ﴿لَط۪يفُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿سَم۪يعُ﴾ تَعَالَيْتَ يَا ﴿كَف۪يلُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿قَر۪يبُ﴾ تَعَالَيْتَ يَا ﴿بَص۪يرُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿حَقُّ﴾ تَعَالَيْتَ يَا ﴿مُب۪ينُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿رَؤُفُ﴾ تَعَالَيْتَ يَا ﴿رَح۪يمُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿طَاهِرُ﴾ تَعَالَيْتَ يَا ﴿مُطَهِّرُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿مُجَمِّلُ﴾ تَعَالَيْتَ يَا ﴿مُفَضِّلُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿مُظْهِرُ﴾ تَعَالَيْتَ يَا ﴿مُنْعِمُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿دَيَّانُ﴾ تَعَالَيْتَ يَا ﴿سُلْطَانُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿حَنَّانُ﴾ تَعَالَيْتَ يَا ﴿مَنَّانُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَٓا ﴿اَحَدُ﴾ تَعَالَيْتَ يَا ﴿صَمَدُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿حَىُّ﴾ تَعَالَيْتَ يَا ﴿قَيُّومُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿عَدْلُ﴾ تَعَالَيْتَ يَا ﴿حَكَمُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿فَرْدُ﴾ تَعَالَيْتَ يَا ﴿قُدُّوسُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ اٰهِيًّا شَرَاهِيًّا تَعَالَيْتَ لَٓا اِلٰهَ اِلَّآ اَنْتَ اَجِرْنَا وَ اَجِرْ اُسْتَادَنَا سَع۪يدَ النُّورْس۪ى رَضِىَ اللّٰهُ عَنْهُ وَ وَالِدَيْنَا وَ اِخْوَانَنَا وَ اَخَوَاتَنَا وَ طَلَبَةَ رَسَٓائِلِ النُّورِ وَ رُفَقَٓائَنَا وَ اَحْبَابَنَا الْمُخْلِص۪ينَ مِنَ النَّارِ وَ مِنْ كُلِّ نَارٍ وَ احْفَظْنَا مِنْ شَرِّ النَّفْسِ وَ الشَّيْطَانِ وَ مِنْ شَرِّ الْجِنِّ وَ اْلاِنْسَانِ وَ مِنْ شَرِّ الْبِدْعَةِ وَ الضَّلَالَاتِ وَ اْلاِلْحَادِ وَ الطُّغْيَانِ بِعَفْوِكَ يَا مُج۪يرُ بِفَضْلِكَ يَا غَفَّارُ بِرَحْمَتِكَ يَٓا اَرْحَمَ الرَّاحِم۪ينَ ❁ اَللّٰهُمَّ اَدْخِلْنَا الْجَنَّةَ مَعَ الْاَبْرَارِ بِشَفَاعَةِ نَبِيِّكَ الْمُخْتَارِ اٰم۪ينَ وَالْحَمْدُ لِلّٰهِ رَبِّ الْعَالَم۪ينَ\n" +
                "\n" +
                "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّح۪يمِ\n" +
                "\n" +
                "لَا يَسْتَو۪ٓى اَصْحَابُ النَّارِ وَاَصْحَابُ الْجَنَّةِۜ اَصْحَابُ الْجَنَّةِ هُمُ الْفَٓائِزُونَ ﴿٠٢﴾\n" +
                "\n" +
                "لَوْ اَنْزَلْنَا هٰذَا الْقُرْاٰنَ عَلٰى جَبَلٍ لَرَاَيْتَهُ خَاشِعًا مُتَصَدِّعًا مِنْ خَشْيَةِ اللّٰهِۜ وَتِلْكَ الْاَمْثَالُ نَضْرِبُهَا لِلنَّاسِ لَعَلَّهُمْ يَتَفَكَّرُونَ ﴿١٢﴾\n" +
                "\n" +
                "هُوَ اللّٰهُ الَّذ۪ى لَٓا اِلٰهَ اِلَّا هُوَۚ عَالِمُ الْغَيْبِ وَالشَّهَادَةِۚ هُوَ الرَّحْمٰنُ الرَّح۪يمُ ﴿٢٢﴾\n" +
                "\n" +
                "هُوَ اللّٰهُ الَّذ۪ى لَٓا اِلٰهَ اِلَّا هُوَۚ اَلْمَلِكُ الْقُدُّوسُ السَّلَامُ الْمُؤْمِنُ الْمُهَيْمِنُ الْعَز۪يزُ الْجَبَّارُ الْمُتَكَبِّرُۜ سُبْحَانَ اللّٰهِ عَمَّا يُشْرِكُونَ ﴿٣٢﴾\n" +
                "\n" +
                "هُوَ اللّٰهُ الْخَالِقُ الْبَارِىُٔ الْمُصَوِّرُ لَهُ الْاَسْمَٓاءُ الْحُسْنٰىۜ يُسَبِّحُ لَهُ مَا فِى السَّمٰوَاتِ وَالْاَرْضِۚ وَهُوَ الْعَز۪يزُ الْحَك۪يمُ ﴿٤٢﴾\n" +
                "\n";

        TextView ttv = binding.tesbihatTextview;
        ttv.setText(tesbihatStr);
        ttv.setMovementMethod(new ScrollingMovementMethod());




    }
    public void sabahturkce () {


        String tesbihatStr = "Sabah namazının farzı kılınıp selâm verildikten sonra,\n" +
                "\n" +
                "“Allâhümme ente’s-selâmü ve minke’s-selâm, tebârekte yâ ẕe’l-celâli ve’likrâm”\n" +
                "\n" +
                "denir ve aşağıdaki Salât-ı Münciye Duası, (yani Salâte’n-Tüncînâ) oku\u00ADnur:\n" +
                "\n" +
                "“Allahümme šalli alâ seyyidinâ Muḥammedin ve ʿalâ âli seyyidinâ Muḥammed. Šalâten tüncînâ bihâ min-cemîʿı’l-ehvâli ve’lâfât. (“ve’l-âfât” derken avuç içleri yere bakacak şekilde çevrilir, bitince de eski haline getirilir.) Ve takḍîlenâ bi-hâ cemîʿa’l-ḥâcât. Ve tüṭahhirunâ bi-hâ min-cemîʿı’s-seyyiât. Ve terfeʿunâ bi-hâ ʿındeke aʿle’d-derecât. Ve tübelliğunâ bi-hâ aḳša’l-ğâyât. Min-cemîʿı’lḫayrâti fi’l-ḥayâti ve baʿde’l-memât. Âmin yâ Mucîbe’d-deʿavâti ve’l-ḥamdü lillâhi Rabbi’lʿâlemîn. Âmin”\n" +
                "\n" +
                "denilir ve eller yüze sürülüp indirilir. Sonra tesbihâta şöyle devam edilir:\n" +
                "\n" +
                "“Allâhümme innâ nuḳaddimu ileyke beyne yedey külli nefesin ve lemḥatin ve lâḥẓatin ve ṭarfetin yaṭrifu bi-hâ ehlü’s-semâvâti ve ehlü’laraḍîne şehâdeten: Eşhedü en… (Buraya kadar bir defa söy\u00ADlenir) Lâ ilâhe illâllahü vaḥdehû lâ şerîke leh. Lehü’l-mülkü ve lehü’l-ḥamdü yuḥyî ve yümît. Ve hüve ḥayyün lâ yemût, biyedihi’l-ḫayr ve hüve ʿalâ külli şey’in ḳadîr” On defa tekrar edilir ve sonun\u00ADda “ve ileyhi’l-mašîr”\n" +
                "\n" +
                "denilerek Kelime-i Tev\u00ADhid bi\u00ADti\u00ADrilir. İstiâze Duası’yla tesbihâta devam edilir (dua eder\u00ADken eller kaldırılır ve avuç içleri yere bakacak şekilde tutulur):\n" +
                "\n" +
                "“Allahümme ecirnâ mine’n-nâr. (3, 5 veya 7 de\u00ADfa tekrar edilir.)\n" +
                "Allahümme ecirnâ min-külli nâr.\n" +
                "Allahümme ecirnâ min-fitneti’d-dîniyyeti ve’d-dünyeviyyeh.\n" +
                "Allahümme ecirnâ min-fitneti âḫiri’z-zemân.\n" +
                "Allahümme ecirnâ min-fitneti Mesîḥı’dDeccâli ve’s-Süfyân.\n" +
                "Allahümme ecirnâ mine’ḍ-ḍalâlâti ve’l-bidʿıyyâti ve’l-beliyyât.\n" +
                "Allahümme ecirnâ min-şerri’n-nefsi’lemmâreh.\n" +
                "Allahümme ecirnâ min-şürûri’n-nüfûsi’lemmârâti’l-firʿavniyyeh.\n" +
                "Allahümme ecirnâ min-şerri’n-nisâ.\n" +
                "Allahümme ecirnâ min-belâi’n-nisâ.\n" +
                "Allahümme ecirnâ min-fitneti’n-nisâ.\n" +
                "Allahümme ecirnâ min-ʿaẕâbi’l-ḳabr.\n" +
                "Allahümme ecirnâ min-ʿaẕâbi yevmi’lḳıyâmeh.\n" +
                "Allahümme ecirnâ min-ʿaẕâbi Cehennem.\n" +
                "Allahümme ecirnâ min-ʿaẕâbi ḳahrik.\n" +
                "Allahümme ecirnâ min-ʿnâri ḳahrik.\n" +
                "Allahümme ecirnâ min-ʿaẕâbi’l-ḳabri ve’nnîrân.\n" +
                "Allahümme ecirnâ mine’r-riyâi ve’ssümʿati ve’l-ʿucubi ve’l-faḫr.\n" +
                "Allahümme ecirnâmin-tecâvüzi’l-mülḥıdîn.\n" +
                "Allahümme ecirnâ min-şerri’l-münâfiḳîn.\n" +
                "Allahümme ecirnâ min-fitneti’l-fâsıḳîn.\n" +
                "Allahümme ecirnâ ve ecir vâlideynâ ve ṭalebete Resâili’n-Nûri’š- šâdiḳîne fî-ḫıdmeti’l-Ḳur’âni ve’l-îmân. Ve aḥbâbene’l-mü’minîne’l-muḫlišîne ve aḳrabâenâ ve ecdâdenâ mine’n-nâr.\n" +
                "(Bundan son\u00ADra avuç içleri yukarı çevrilir.)\n" +
                "\n" +
                "Bi-ʿafvike yâ Mücîr, bi-faḍlike yâ Ğaffâr. Allâhümme’d-ḫılne’l-Cennete meʿâ’l-ebrâr. Allâhümme’d-ḫılne’l-Cennete meʿâ’l-ebrâr. Allâhümme’d-ḫılnâ ve edḫı’l-Üstâẕenâ Saʿîde’n-Nursî (radiyallâhu ʿanh) ve vâlideynâ ve ṭalebete Resâili’n-Nûri’š-šâdıḳîne ve iḫvânenâ ve eḫavâtinâ ve aḳrabâenâ ve ecdâdenâ ve aḥbâbene’l-mü’minîne’l-muḫlišîne fî-ḫıdmeti’l-îmâni ve’l-Ḳur’ân. El-Cennete meʿa’l-ebrâr bi-şefâati nebiyyike’l-muḫtar ve âlihi’l-eṭhâr ve ešḥâbihi’l-aḫyâr ve sellim mâ dâme’l-leylü ve’n-nehâr. Âmîn, ve’l-ḥamdü lillâhi Rabbi’lʿÂlemîn”\n" +
                "\n" +
                "denilir ve eller yüze sü\u00ADrülür. Sonra namaz tesbihatına şu dua ile devam edi\u00ADlir:\n" +
                "\n" +
                "“Sübḥânallahi ve’l-ḥamdü lillâhi ve lâ ilâhe illâllahü vallahü ekber ve lâ ḥavle ve lâ ḳuvvete illâ billâhi’l-ʿaliyyi’l-ʿaẓîm.”\n" +
                "\n" +
                "Ve Âyete’l-Kürsî okunur:\n" +
                "\n" +
                "“Bismillâhirraḥmânirraḥîm. Allahü lâ ilâhe illâ hüve’l-ḥayyü’l-ḳayyûm. Lâ te’ḫuẕühû sinetün ve lâ nevm. Lehû mâ fi’s-semâvâti vemâ fi’l-arḍı. Menẕelleẕî yeşfeʿu ʿındehû illâ bi-iẕnih. Yâʿlemü mâ beyne eydîhim ve mâ ḫalfehüm ve lâ yuḥîṭûne bi-şey’in min-ʿılmihî illâ bi-mâ şâe. Vesiʿa kürsiyyühü’s-semâvâti ve’l-arḍi. Velâ yeûdü\u00AD hû ḥıfẓuhümâ ve hüve’l-ʿaliyyü’l-ʿaẓîm.”\n" +
                "\n" +
                "Şu tesbih sözleri tekrarlanır:\n" +
                "\n" +
                "“Ve hüve’l-‘aliyyü’l-‘az. îmu zü’l-celâli sübḥâ\u00AD nellâh” (1 defa),\n" +
                "\n" +
                "“Sübḥânallâh” (33 defa)\n" +
                "\n" +
                "“Sübḥâne’l-bâḳî dâimeni’l-ḥamdülillâh” (1 defa),\n" +
                "\n" +
                "“Elḥamdülillâh” (33 defa)\n" +
                "\n" +
                "“Rabbi’l-‘âlemîne te‘âlâ şânühü Allâhü Ekber” (1 defa),\n" +
                "\n" +
                "“Allâhü Ekber” (33 defa)\n" +
                "\n" +
                "“Lâ ilâhe illallâhü, vaḥdehû, lâ şerîke leh, lehü’l-mülkü ve lehü’l-ḥamdü, yuḥyî ve yümît ve hüve ḥayyün lâyemût, bi-yedihi’l-ḫayru ve hüve ʿalâ külli şey’in ḳadîrun ve ileyhi’l-mašîr” denilir ve na\u00ADmaz duası yapılır.\n" +
                "\n" +
                "Duâdan sonra tesbihata şöyle devam edilir:\n" +
                "\n" +
                "“Faʿlem ennehu Lâ ilâhe illâllah” (1 defa)\n" +
                "\n" +
                "“Lâ ilâhe illâllah” (33 defa)\n" +
                "\n" +
                "denilir ve ardından;\n" +
                "\n" +
                "“Muḥammedü’r-resûlüllâhi šallâllahü teʿâlâ ʿaleyhi vesellem.” (1 defa)\n" +
                "\n" +
                "“Lâilâhe illâllahü el-melikü’l-ḥaḳḳu’lmübîn, Muḥammedü’r-resûlullahi šâdiḳu’l-va ʿ di’l-emîn.” (10 de\u00ADfa tekrar edilir.)\n" +
                "\n" +
                "“Bismillâhirraḥmânirraḥîm. İnnallâhe ve melâiketehû yüšallûne ʿale’nnebiy. Yâ eyyühelleẕîne âmenû šallû ʿaleyhi ve sellimû teslîmâ. Lebbeyk.\n" +
                "\n" +
                "Allahümme šalli ʿalâ seyyidinâ Muḥammedin ve ʿalâ âli seyyidinâ Muḥammed. Biʿadedi külli dâin ve devâin ve bârik ve sellim ʿaleyhi ve ʿaleyhim kesîrâ.\n" +
                "Allahümme šalli ʿalâ seyyidinâ Muḥammedin ve ʿalâ âli seyyidinâ Muḥammed. Biʿadedi külli dâin ve devâin ve bârik ve sellim ʿaleyhi ve ʿaleyhim kesîrâ.\n" +
                "Allahümme šalli ʿalâ seyyidinâ Muḥammedin ve ʿalâ âli seyyidinâ Muḥammed. Biʿadedi külli dâin ve devâin ve bârik ve sellim ʿaleyhi ve ʿaleyhim kesîran kesîrâ.\n" +
                "Šalli ve sellim yâ Rabbi ʿalâ ḥabîbike Muḥammedin ve ʿalâ cemîʿı’l-enbiyâi ve’l-mürselîn ve ʿalâ âli küllin ve šahbi küllin ecmaʿîn. Âmin ve’l-ḥamdü lillâhi Rabbi’l-ʿâlemîn.\n" +
                " \n" +
                "\n" +
                "Elfü elfi šalâtin ve elfü elfi selâmin ʿaleyke yâ Resûlallah.\n" +
                "Elfü elfi šalâtin ve elfü elfi selâmin ʿaleyke yâ Ḥabîballah.\n" +
                "Elfü elfi šalâtin ve elfü elfi selâmin ʿaleyke yâ emîne vaḥyillâh.\n" +
                "Allahümme šalli ve sellim ve bârik ʿalâ seyyidinâ Muḥammedin ve ʿalâ âlihî ve ašḥâbihî bi-ʿadedi evraḳı’l-eşcâr ve emvâci’lbiḥâr ve ḳaṭarâti’l-emṭâr vağfir lenâ verḥamnâ velṭuf binâ ve bi-Üstâẕinâ Saʿîdi’n-Nursî (radiyallahu ʿanh) ve vâlideynâ ve bi-ṭalebeti Resâili’n-Nuri’š-šadıḳîne yâ ilâhenâ bi-külli šalâtin minhâ eşhedü en lâilâhe illâllah ve eşhedü enne Muḥammeden Resûlüllâhi Šallallâhü ʿAleyhi Ve sellem.”\n" +
                "\n" +
                "Aşağıdaki Duâ-i Tercümân-ı İsm-i A’zâm ile tes\u00ADbi\u00ADhata devam edilir:\n" +
                "\n" +
                "Sübḥâneke yâ Allah teʿâleyte yâ Raḥmân ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Raḥîm teʿâleyte yâ Kerîm ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ḥamîd teʿâleyte yâ Ḥakîm ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Mecîd teʿâleyte yâ Melîk ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ḳuddûs teʿâleyte yâ Selâm ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Mü’min teʿâleyte yâ Müheymin ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ ʿAzîz teʿâleyte yâ Cebbâr ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Mütekebbîr teʿâleyte yâ Ḫàlık ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Evvel teʿâleyte yâ Âḫir ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ẓâhir teʿâleyte yâ Bâṭın ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Bâri’ teʿâleyte yâ Mušavvir ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Tevvâb teʿâleyte yâ Vehhâb ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Bâis teʿâleyte yâ Vâris ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ḳadîm teʿâleyte yâ Muḳîm ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ferd teʿâleyte yâ Vitr ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Nûr teʿâleyte yâ Settâr ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Celîl teʿâleyte yâ Cemîl ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ḳàhir teʿâleyte yâ Ḳàdir ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Melîk teʿâleyte yâ Muḳtedir ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ ʿAlîm teʿâleyte yâ ʿAllâm ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ ʿAẓîm teʿâleyte yâ Ğafûr ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ḥalîm teʿâleyte yâ Vedûd ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Şehîd teʿâleyte yâ Şâhid ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Kebîr teʿâleyte yâ Müteʿâl ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Nûr teʿâleyte yâ Lâṭîf ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Semiʿ teʿâleyte yâ Kefîl ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ḳarîb teʿâleyte yâ Bašîr ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ḥaḳ teʿâleyte yâ Mübîn ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Raûf teʿâleyte yâ Raḥîm ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ṭâhir teʿâleyte yâ Muṭahhir ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Mücemmil teʿâleyte yâ Mufaḍ\u00AD ḍıl ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Muẓhir teʿâleyte yâ Münʿim ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Deyyân teʿâleyte yâ Sulṭân ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ḥannân teʿâleyte yâ Mennân ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Eḥad teʿâleyte yâ Šamed ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ḥayy teʿâleyte yâ Ḳayyûm ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ ʿAdl teʿâleyte yâ Ḥakem ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ferd teʿâleyte yâ Ḳuddûs ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Avuçlar yukarı gelecek şekilde eller kaldırılır:\n" +
                "\n" +
                "“Sübḥâneke âhiyyen şerâhiyyen teʿâleyte lâ ilâhe illâ ente ecirnâ ve ecir Üstâẕenâ Saʿîde’n-Nursî (raḍıyallâhu ʿanh) ve vâlideynâ ve iḫvânenâ ve eḫavâtinâ ve ṭalebete Resâili’n-Nûri ve rufeḳàenâ ve aḥbâbene’l-mü’minîne’lmuḫlišîne mine’n-nâr.\n" +
                "\n" +
                "(Avuç içleri yere bakacak şekilde çevrilir.)\n" +
                "\n" +
                "Ve min-külli nâr vaḥ\u00AD feẓnâ min-şerri’n-nefsi ve’ş-şeyṭân ve minşerri’l-cinni ve’l-insân ve min-şerri’l-bidʿati ve’ḍ-ḍalâlâti ve’l-ilḥâdi ve’ṭ-ṭuğyân.\n" +
                "\n" +
                "(Avuç iç\u00ADleri tekrar yukarıya baka\u00ADcak şekle getirilir.)\n" +
                "\n" +
                "Bi-ʿafvike yâ Mücîr, bi-faḍlike yâ Ğaffâr biraḥmetike yâ Erḥame’r-râḥimîn. Allahümm’e-dḫılne’l-Cennete meʿâ’l-ebrâr, bi\u00AD-şefâʿati nebiyyike’l-muḫtâr. Âmin, ve’lḥamdü lillâhi Rabbi’l-Âlemîn.”\n" +
                "\n" +
                "Haşir Sûresi’nin 20-24. âyetleri (Lâyestevî) o\u00ADku\u00ADna\u00ADrak tesbihat bitirilir.\n" +
                "\n" +
                "“Bismillâhirraḥmânirraḥîm Lâ yestevî ašḥâbü’n-nâri ve ašḥâbü’l-Cenneh. Ašḥâbü’l-Cenneti hümü’l-fâizûn. . Lev enzelnâ hâẕe’l-Ḳur’âne ʿalâ cebelin leraeytehû ḫâşiʿan mütešaddian min-ḫaşyetillâh. Ve tilke’l-emsâlü naḍribühâ linnâsi leʿallehüm yetefekkerûn. . Hüvallahü’l-leẕî lâ ilâhe illâ hû. ʿÂlimü’l-ğaybi ve’ş-şehâdeti. Hüve’rRaḥmânü’r-Raḥîm. . Hüvallâhü’l-leẕî lâ ilâhe illâ hû. El-Melikü’l-Ḳuddûsü’s-Selâmü’l-Mü’\u00AD minü’l-Müheyminü’l-ʿAzîzü’l-Cebbârü’l-Mü\u00AD tekebbir. Sübḥânellâhi ʿammâ yüşrikûn. . Hüvallâhü’l-Ḫàliḳu’l-Bâriü’l-Müšavviru lehü’lEsmâü’l-\u00ADḤüsnâ. Yüsebbiḥu lehû mâ fi’ssemâvâti ve’l-arḍi. Ve hüve’l-ʿAzîzü’l-Ḥakîm.”";
        TextView ttv = binding.tesbihatTextview;
        ttv.setText(tesbihatStr);
        ttv.setMovementMethod(new ScrollingMovementMethod());




    }
    public void ogleArapca () {


        String tesbihatStr = "Öğle namazının farzının selamını verdikten sonra:\n" +
                "\n" +
                "اَللّٰهُمَّ اَنْتَ السَّلَامُ وَ مِنْكَ السَّلَامُ تَبَارَكْتَ يَا ذَا الْجَلَالِ وَ اْلاِكْرَامِ\n" +
                "\n" +
                "Dedikten sonra elleri yukarı kaldırıp\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَ عَلَٓى اٰلِ سَيِّدِنَا مُحَمَّدٍ صَلَاةً تُنْج۪ينَا بِهَا مِنْ جَم۪يعِ الْاَهْوَالِ وَ اْلاٰفَاتِ وَ تَقْض۪ى لَنَا بِهَا جَم۪يعَ الْحَاجَاتِ وَ تُطَهِّرُنَا بِهَا مِنْ جَم۪يعِ السَّيِّئَاتِ وَ تَرْفَعُنَا بِهَا عِنْدَكَ اَعْلَى الدَّرَجَاتِ وَ تُبَلِّغُنَا بِهَٓا اَقْصَى الْغَايَاتِ مِنْ جَم۪يعِ الْخَيْرَاتِ فِى الْحَيَاةِ وَ بَعْدَ الْمَمَاتِ اٰم۪ينَ يَا مُج۪يبَ الدَّعَوَاتِ وَالْحَمْدُ لِلّٰهِ رَبِّ الْعَالَم۪ينَ\n" +
                "\n" +
                "bundan sonra şu dua okunur\n" +
                "\n" +
                "سُبْحَانَ اللّٰهِ وَالْحَمْدُ لِلّٰهِ وَلَا إِلَهَ إِلَّا اللَّهُ وَ اللّٰهُ اَكْبَرْ وَلَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللّٰهِ الْعَلِيِّ الْعَظِيمِْ\n" +
                "\n" +
                "Âyet-ül Kürsi okunur\n" +
                "\n" +
                "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّح۪يمِ\n" +
                "\n" +
                "اَللّٰهُ لَٓا اِلٰهَ اِلَّا هُوَۚ اَلْحَيُّ الْقَيُّومُۚ لَا تَأْخُذُهُ سِنَةٌ وَلَا نَوْمٌۜ لَهُ مَا فِي السَّمٰوَاتِ وَمَا فِي الْاَرْضِۜ مَنْ ذَا الَّذ۪ي يَشْفَعُ عِنْدَهُٓ اِلَّا بِاِذْنِه۪ۜ يَعْلَمُ مَا بَيْنَ اَيْد۪يهِمْ وَمَا خَلْفَهُمْۚ وَلَا يُح۪يطُونَ بِشَيْءٍ مِنْ عِلْمِه۪ٓ اِلَّا بِمَا شَٓاءَۚ وَسِعَ كُرْسِيُّهُ السَّمٰوَاتِ وَالْاَرْضَۚ وَلَا يَؤُ۫دُهُ حِفْظُهُمَاۚ وَهُوَ الْعَلِيُّ الْعَظ۪يمُ\n" +
                "\n" +
                "ardından 33 defa\n" +
                "\n" +
                "سُبْحَانَ اللّٰهِ\n" +
                "\n" +
                "33 defa\n" +
                "\n" +
                "اَلْحَمْدُ لِلّٰهِ\n" +
                "\n" +
                "33 defa\n" +
                "\n" +
                "اَللّٰهُ اَكْبَرُ\n" +
                "\n" +
                "ve namazın duası yapılır. Müteakiben 1 defa\n" +
                "\n" +
                "فَاعْلَمْ اَنَّهُ\n" +
                "\n" +
                "33 defa\n" +
                "\n" +
                "لَٓا اِلٰهَ اِلَّا اللّٰهُ\n" +
                "\n" +
                "deyip 33.de\n" +
                "\n" +
                "مُحَمَّدٌ رَسُولُ اللّٰهِ صَلَّى اللّٰهُ تَعَالٰى عَلَيْهِ وَ سَلَّمَ\n" +
                "\n" +
                "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّح۪يمِ\n" +
                "\n" +
                "اِنَّ اللّٰهَ وَ مَلَٓئِكَتَهُ يُصَلُّونَ عَلَى النَّبِىِّ يَٓا اَيُّهَا الَّذ۪ينَ اٰمَنُوا صَلُّوا عَلَيْهِ وَ سَلِّمُوا تَسْل۪يمًا ❁ لَبَّيْكَ\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَعَلَٓى اٰلِ سَيِّدِنَا مُحَمَّدٍ بِعَدَدِ كُلِّ دَٓاءٍ وَدَوَٓاءٍ وَبَارِكْ وَسَلِّمْ عَلَيْهِ وَعَلَيْهِمْ كَث۪يرًا\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَعَلَٓى اٰلِ سَيِّدِنَا مُحَمَّدٍ بِعَدَدِ كُلِّ دَٓاءٍ وَدَوَٓاءٍ وَبَارِكْ وَسَلِّمْ عَلَيْهِ وَعَلَيْهِمْ كَث۪يرًا\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَعَلَٓى اٰلِ سَيِّدِنَا مُحَمَّدٍ بِعَدَدِ كُلِّ دَٓاءٍ وَدَوَٓاءٍ وَبَارِكْ وَسَلِّمْ عَلَيْهِ وَعَلَيْهِمْ كَث۪يرًا كَث۪يرًا\n" +
                "\n" +
                "صَلِّ وَ سَلِّمْ يَا رَبِّ عَلٰى حَب۪يبِكَ مُحَمَّدٍ وَ عَلٰى جَم۪يعِ الْاَنْبِيَٓاءِ وَ الْمُرْسَل۪ينَ وَ عَلَٓى اٰلِ كُلٍّ وَ صَحْبِ كُلٍّ اَجْمَع۪ينَ اٰم۪ينَ وَالْحَمْدُ لِلّٰهِ رَبِّ الْعَالَم۪ينَ\n" +
                "\n" +
                "اَلْفُ اَلْفِ صَلَاةٍ وَ اَلْفُ اَلْفِ سَلَامٍ عَلَيْكَ يَا رَسُولَ اللّٰهِ\n" +
                "\n" +
                "اَلْفُ اَلْفِ صَلَاةٍ وَ اَلْفُ اَلْفِ سَلَامٍ عَلَيْكَ يَا حَب۪يبَ اللّٰهِ\n" +
                "\n" +
                "اَلْفُ اَلْفِ صَلَاةٍ وَ اَلْفُ اَلْفِ سَلَامٍ عَلَيْكَ يَٓا اَم۪ينَ وَحْىِ اللّٰهِ\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ وَ سَلِّمْ وَ بَارِكْ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَعَلَٓى اٰلِه۪ وَ اَصْحَابِه۪ بِعَدَدِ اَوْرَاقِ الْاَشْجَارِ وَ اَمْوَاجِ الْبِحَارِ وَ قَطَرَاتِ الْاَمْطَارِ وَ اغْفِرْلَنَا وَ ارْحَمْنَا وَ الْطُفْ بِنَا وَ بِاُسْتَادِنَا سَع۪يدِ النُّورْس۪ى رَضِىَ اللّٰهُ عَنْهُ وَ وَالِدَيْنَا وَ بِطَلَبَةِ رَسَٓائِلِ النُّورِ الصَّادِق۪ينَ يَٓا اِلٰهَنَا بِكُلِّ صَلَاةٍ مِنْهَٓا اَشْهَدُ اَنْ لَٓا اِلٰهَ اِلَّا اللّٰهُ وَ اَشْهَدُ اَنَّ مُحَمَّدًا رَسُولُ اللّٰهِ صَلَّى اللّٰهُ تَعَالٰى عَلَيْهِ وَ سَلَّمْ\n" +
                "\n" +
                "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّح۪يمِ\n" +
                "\n" +
                "يَا ﴿جَم۪يلُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿قَر۪يبُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مُج۪يبُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿حَب۪يبُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿رَؤُفُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿عَطُوفُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مَعْرُوفُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿لَط۪يفُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿عَظ۪يمُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿حَنَّانُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مَنَّانُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿دَيَّانُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿سُبْحَانُ﴾ يَٓا اَللّٰهُ ❁ يَٓا ﴿اَمَانُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿بُرْهَانُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿سُلْطَانُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مُسْتَعَانُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مُحْسِنُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مُتَعَالُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿رَحْمٰنُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿رَح۪يمُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿كَر۪يمُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مَج۪يدُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿فَرْدُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿وِتْرُ﴾ يَٓا اَللّٰهُ ❁ يَٓا ﴿اَحَدُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿صَمَدُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مَحْمُودُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿صَادِقَالْوَعْدِ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿عَلِىُّ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿غَنِىُّ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿شَاف۪ى﴾ يَٓا اَللّٰهُ ❁ يَا ﴿كَاف۪ى﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مُعَاف۪ى﴾ يَٓا اَللّٰهُ ❁ يَا ﴿بَاق۪ى﴾ يَٓا اَللّٰهُ ❁ يَا ﴿هَاد۪ى﴾ يَٓا اَللّٰهُ ❁ يَا ﴿قَادِرُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿سَاتِرُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿قَهَّارُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿جَبَّارُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿غَفَّارُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿فَتَّاحُ﴾ يَٓا اَللّٰهُ\n" +
                "\n" +
                "يَا رَبَّ السَّمٰوَاتِ وَ الْاَرْضِ يَا ذَا الْجَلَالِ وَ اْلاِكْرَامِ ❁ اَسْئَلُكَ بِحَقِّ هٰذِهِ الْاَسْمَٓاءِ كُلِّهَٓا اَنْ تُصَلِّىَ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَ عَلٰٓى اٰلِ مُحَمَّدٍ وَ ارْحَمْ مُحَمَّدًا كَمَا صَلَّيْتَ وَ سَلَّمْتَ وَ بَارَكْتَ وَ رَحِمْتَ وَ تَرَحَّمْتَ عَلٰٓى اِبرَاه۪يمَ وَ عَلٰٓى اٰلِ اِبْرَاه۪يمَ فِى الْعَالَم۪ينَ رَبَّنَٓا اِنَّكَ حَم۪يدٌ مَج۪يدٌ بِرَحْمَتِكَ يَٓا اَرْحَمَ الرَّاحِم۪ينَ وَ الْحَمْدُ لِلّٰهِ رَبِّ الْعَالَم۪ينَ\n" +
                "\n" +
                "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّح۪يمِ\n" +
                "\n" +
                "لَقَدْ صَدَقَ اللّٰهُ رَسُولَهُ الرُّءْيَا بِالْحَقِّۚ لَتَدْخُلُنَّ الْمَسْجِدَ الْحَرَامَ اِنْ شَٓاءَ اللّٰهُ اٰمِن۪ينَۙ مُحَلِّق۪ينَ رُوُ۫ٔسَكُمْ وَمُقَصِّر۪ينَۙ لَا تَخَافُونَۜ فَعَلِمَ مَا لَمْ تَعْلَمُوا فَجَعَلَ مِنْ دُونِ ذٰلِكَ فَتْحًا قَر۪يبًا ﴿٧٢﴾\n" +
                "\n" +
                "هُوَ الَّذ۪ٓى اَرْسَلَ رَسُولَهُ بِالْهُدٰى وَد۪ينِ الْحَقِّ لِيُظْهِرَهُ عَلَى الدّ۪ينِ كُلِّه۪ۜ وَكَفٰى بِاللّٰهِ شَه۪يدًاۜ ﴿٨٢﴾\n" +
                "\n" +
                "مُحَمَّدٌ رَسُولُ اللّٰهِۜ وَالَّذ۪ينَ مَعَهُٓ اَشِدَّٓاءُ عَلَى الْكُفَّارِ رُحَمَٓاءُ بَيْنَهُمْ تَرٰيهُمْ رُكَّعًا سُجَّدًا يَبْتَغُونَ فَضْلاً مِنَ اللّٰهِ وَرِضْوَانًاۘ س۪يمَاهُمْ ف۪ى وُجُوهِهِمْ مِنْ اَثَرِ السُّجُودِۜ ذٰلِكَ مَثَلُهُمْ فِى التَّوْرٰيةِۚۛ وَمَثَلُهُمْ فِى اْلاِنْج۪يلِ۠ۛ كَزَرْعٍ اَخْرَجَ شَطْئَهُ۫ فَاٰزَرَهُ فَاسْتَغْلَظَ فَاسْتَوٰى عَلٰى سُوقِه۪ يُعْجِبُ الزُّرَّاعَ لِيَغ۪يظَ بِهِمُ الْكُفَّارَۜ وَعَدَ اللّٰهُ الَّذ۪ينَ اٰمَنُوا وَعَمِلُوا الصَّالِحَاتِ مِنْهُمْ مَغْفِرَةً وَاَجْرًا عَظ۪يمًا ﴿٩٢﴾";

        TextView ttv = binding.tesbihatTextview;
        ttv.setText(tesbihatStr);
        ttv.setMovementMethod(new ScrollingMovementMethod());




    }
    public void ogleturkce () {


        String tesbihatStr = "Öğle namazının farzı kılınıp selâm verildikten sonra,\n" +
                "\n" +
                "“Allâhümme ente’s-selâmü ve minke’s-selâm, tebârekte yâ ẕe’l-celâli ve’likrâm” denir ve aşağıdaki Salat-ı MünciyeDuası (yani salat’en Tüncina) okunur.\n" +
                "“Allahümme šalli alâ seyyidinâ Muḥammedin ve ʿalâ âli seyyidinâ Muḥammed. Šalâten tüncînâ bihâ min-cemîʿı’l-ehvâli ve’l-âfât. (“ve’l- âfât” derken avuç içleri yere bakacak şekilde çevrilir, bitince de eski haline getirilir.) Ve takḍîlenâ bi-hâ cemîʿa’l-ḥâcât. Ve tüṭahhirunâ bi-hâ min-cemîʿı’s-seyyiât. Ve terfeʿunâ bi-hâ ʿindeke aʿle’d-derecât. Ve tübelliğunâ bi-hâ aḳša’l-ğâyât. Min-cemîʿi’l-ḫayrâti fi’l-ḥayâti ve baʿde’l-memât. Âmin yâ Mucîbe’d-deʿavâti ve’l-ḥamdü lillâhi Rabbi’l-ʿâlemîn. Âmin” denilir ve eller yüze sürülüp indirilir.\n" +
                "\n" +
                "Öğle namazının son sünneti kılınır. Selâm ver\u00ADdik\u00ADten sonra tesbihata şu dua ile devam edilir:\n" +
                "\n" +
                "“Sübḥânallahi velḥamdü lillâhi ve lâilâhe illâllahü vallahü ekber ve lâ ḥavle ve lâ ḳuvvete illâ billâhi’l-ʿaliyyi’l-ʿaẓîm.” Ve Âyete’l-Kürsî okunur: “Bismillâhirraḥmânirraḥîm. Allahü lâ ilâhe illâ hüve’l-ḥayyü’l-ḳayyûm. Lâ te’ḫuẕühû sinetün ve lâ nevm. Lehû mâ fi’s-semâvâti ve mâ fi’l-arḍi. Menẕelleẕî yeşfeʿu ʿindehû illâ bi-iẕnih. Yâʿlemü mâ beyne eydîhim ve mâ ḫalfehüm ve lâ yuḥîṭûne bişey’in min-ʿilmihî illâ bimâ şâe. Vesiʿa kürsiyyühü’s-semâvâti ve’l-arḍi. Ve lâ yeûdühû ḥıfẓuhümâ ve hüve’l-ʿaliyyü’l-ʿaẓîm.\n" +
                "\n" +
                "Şu tesbih sözleri tekrarlanır:\n" +
                "\n" +
                "“Ve hüve’l-‘aliyyü’l-‘az. îmu zü’l-celâli sübḥâ\u00AD nellâh” (1 defa),\n" +
                "\n" +
                "“Sübḥânallâh” (33 defa)\n" +
                "\n" +
                "“Sübḥâne’l-bâḳî dâimeni’l-ḥamdülillâh” (1 defa),\n" +
                "\n" +
                "“Elḥamdülillâh” (33 defa)\n" +
                "\n" +
                "“Rabbi’l-‘âlemîne te‘âlâ şânühü Allâhü Ekber” (1 defa),\n" +
                "\n" +
                "“Allâhü Ekber” (33 defa)\n" +
                "\n" +
                "“Lâ ilâhe illâllahü, vaḥdehû, lâ şerîke leh, lehü’l-mülkü ve lehü’l-ḥamdü, yuḥyî ve yümît ve hüve ḥayyün lâ yemût, bi-yedihi’l-ḫayru ve hüve ʿalâ külli şey’in ḳadîr ve ileyhi’l-mašîr”\n" +
                "\n" +
                "denilir ve namaz du\u00ADa\u00ADsı yapılır. Duâdan sonra tesbihata şöyle devam edilir:\n" +
                "\n" +
                "“Faʿlem ennehu” (1 defa)\n" +
                "\n" +
                "“Lâ ilâhe illâllah” (33 defa söylenir.)\n" +
                "\n" +
                "“Muḥammedü’r-resûlüllâhi šallâllahü teʿâlâ ʿaleyhi vesellem.” (1 defa)\n" +
                "\n" +
                "“Bismillâhirraḥmânirraḥîm. İnnallâhe ve melâiketehû yüšallûne ʿale’nnebiy. Yâ eyyühelleẕîne âmenû šallû ʿaleyhi ve sellimû teslîmâ. Lebbeyk. Tesbihâtın burasında Peygamberimize (a.s.m.) şöyle salât ve selâm edilir:\n" +
                "\n" +
                "Allahümme šalli ʿalâ seyyidinâ Muḥammedin ve ʿalâ âli seyyidinâ Muḥammed. Biʿadedi külli dâin ve devâin ve bârik ve sellim ʿaleyhi ve ʿaleyhim kesîrâ.\n" +
                "Allahümme šalli ʿalâ seyyidinâ Muḥammedin ve ʿalâ âli seyyidinâ Muḥammed. Biʿadedi külli dâin ve devâin ve bârik ve sellim ʿaleyhi ve ʿaleyhim kesîrâ.\n" +
                "Allahümme šalli ʿalâ seyyidinâ Muḥammedin ve ʿalâ âli seyyidinâ Muḥammed. Biʿadedi külli dâin ve devâin ve bârik ve sellim ʿaleyhi ve ʿaleyhim kesîran kesîrâ.\n" +
                "Šalli ve sellim yâ Rabbi ‘alâ ḥabîbike Muḥammedin ve ʿalâ cemîʿı’l-enbiyâi ve’l-mürselîn ve ʿalâ âli küllin ve šahbi küllin ecmaʿîn. Âmin ve’l-ḥamdü lillâhi Rabbi’l-âlemîn.\n" +
                "\n" +
                "Elfü elfi šalâtin ve elfü elfi selâmin ʿaleyke yâ Resûlallah.\n" +
                "Elfü elfi šalâtin ve elfü elfi selâmin ʿaleyke yâ Ḥabîballah.\n" +
                "Elfü elfi šalâtin ve elfü elfi selâmin ʿaleyke yâ emîne vaḥyillâh.\n" +
                "Allahümme šalli ve sellim ve bârik ʿalâ seyyidinâ Muḥammedin ve ʿalâ âlihî ve ašḥâbihî bi-ʿadedi evraḳı’l-eşcâr ve emvâci’lbiḥâr ve ḳaṭarâti’l-emṭâr vağfir lenâ verḥamnâ velṭuf binâ ve bi-Üstâẕinâ Saʿîdi’n-Nursî (raḍiyallahu ʿanh) ve vâlideynâ ve bi-ṭalebeti Resâili’n-Nuri’š-šadıḳîne yâ ilâhenâ bi-külli šalâtin minhâ eşhedü en-lâilâhe illâllah ve eşhedü enne Muḥammeden Resûlüllâhi Šallallâhü ʿAleyhi Vesellem.”\n" +
                "\n" +
                "İsm-i A’zâm duası okunur:\n" +
                "\n" +
                "“Bismillâhirraḥmânirraḥîm.\n" +
                "\n" +
                "Yâ Cemîlu yâ Allah,\n" +
                "Yâ Ḳarîbu yâ Allah\n" +
                "Yâ Mucîbu yâ Allah,\n" +
                "Yâ Ḥabîbu yâ Allah\n" +
                "Yâ Raûfu yâ Allah,\n" +
                "Yâ ʿAṭûfu yâ Allah\n" +
                "Yâ Maʿrûfu yâ Allah,\n" +
                "Yâ Lâṭîfu yâ Allah\n" +
                "Yâ ʿAẓîmu yâ Allah,\n" +
                "Yâ Ḥannânu yâ Allah\n" +
                "Yâ Mennânu yâ Allah,\n" +
                "Yâ Deyyânu yâ Allah\n" +
                "Yâ Sübḥânu yâ Allah,\n" +
                "Yâ Emânu yâ Allah\n" +
                "Yâ Bürhânu yâ Allah,\n" +
                "Yâ Sulṭânu yâ Allah\n" +
                "Yâ Müsteʿânu yâ Allah,\n" +
                "Yâ Muḥsinu yâ Allah\n" +
                "Yâ Müteʿâlu yâ Allah,\n" +
                "Yâ Raḥmânu yâ Allah\n" +
                "Yâ Raḥîmu yâ Allah,\n" +
                "Yâ Kerîmu yâ Allah\n" +
                "Yâ Mecîdu yâ Allah,\n" +
                "Yâ Ferdu yâ Allah\n" +
                "Yâ Vitru yâ Allah,\n" +
                "Yâ Eḥadu yâ Allah\n" +
                "Yâ Šamedu yâ Allah,\n" +
                "Yâ Maḥmûdu yâ Allah\n" +
                "Yâ Šâdıḳa’l-Vaʿdi yâ Allah,\n" +
                "Yâ ʿAliyyu yâ Allah\n" +
                "Yâ Ğaniyyu yâ Allah,\n" +
                "Yâ Şâfî yâ Allah\n" +
                "Yâ Kâfî yâ Allah,\n" +
                "Yâ Muʿâfî yâ Allah\n" +
                "Yâ Bâḳî yâ Allah,\n" +
                "Yâ Hâdî yâ Allah\n" +
                "Yâ Ḳàdiru yâ Allah,\n" +
                "Yâ Sâtiru yâ Allah\n" +
                "Yâ Ḳahhâru yâ Allah,\n" +
                "Yâ Cebbâru yâ Allah\n" +
                "Yâ Ğaffâru yâ Allah,\n" +
                "Yâ Fettâḥu yâ Allah”\n" +
                "Avuçlar yukarı gelecek şekilde eller açılır:\n" +
                "\n" +
                "Yâ Rabbe’s-semâvâti ve’l-arḍı, yâ ẕe’l-celâli ve’l-ikrâm. Es’elüke bi-ḥaḳḳı hâẕihi’l-esmâi küllihâ entüšalliye ʿalâ seyyidinâ Muḥammedin ve ʿalâ âli Muḥammedin verḥam Muḥammeden kemâ šalleyte ve sellemte ve bârekte ve raḥimte ve teraḥḥamte ʿalâ İbrâhîme ve ʿalâ âli İbrâhîme fi’l-ʿâlemîn, Rabbenâ inneke ḥamîdü’m-mecîd, bi-raḥmetike yâ erḥamerrâḥimîn, ve’l-ḥamdülillâhi Rabbi’l-ʿâlemîn.”\n" +
                "\n" +
                "Fetih Sûresi’nin 27-29. âyetleri (Leḳad Šadaḳal\u00ADlâhü) okunur ve tesbihat sona erer.\n" +
                "\n" +
                "Bismillâhirraḥmânirraḥîm\n" +
                "\n" +
                "Leḳad šadaḳallâhü resûlehü’r-rü’yâ bi’lhaḳḳi letedḫulünne’l-mescide’l-ḥarâme inşâallâhü âminîne muḥalliḳîne ruûseküm ve muḳaššırîne lâ teḫafûne, feʿalime mâ lem ta’lemû fecaʿale min-dûni ẕâlike fetḥan ḳarîbâ. . Hüvelleẕî ersele rasûlehû bi’l-hüdâ ve dîni’lhaḳḳı li-yuẓhirahû ʿale’d-dîni küllihî ve kefâ billâhi şehîdâ. . Muḥammedü’r-resûlüllâhi ve’lleẕîne meʿahû eşiddâü ʿale’l-küffâri ruḥamâü beynehüm terâhüm rukkeʿan sücceden yebteğûne faḍlem-minallâhi ve rıḍvânâ, sîmâhüm fî-vücûhihim min-eseri’s-sücûd. Ẕâlike meselühüm fi’t-tevrâti ve meselühüm fi’l-incîli kezerʿin aḫrace şaṭ’ehû feâzerehû festağleẓa festevâ ʿalâ sûḳıhî yuʿcibü’z-zürrâʿa li-yağîẓa bi-himü’lküffâr. Veʿadallâhü’l-leẕîne âmenü ve ʿamilü’š- šâliḥâti minhum mağfiraten ve ecran ʿaẓîmâ.";

        TextView ttv = binding.tesbihatTextview;
        ttv.setText(tesbihatStr);
        ttv.setMovementMethod(new ScrollingMovementMethod());




    }
    public void ikindiArapca () {


        String tesbihatStr = "İkindi namazının farzını kılıp selam verdikten sonra:\n" +
                "\n" +
                "اَللّٰهُمَّ اَنْتَ السَّلَامُ وَ مِنْكَ السَّلَامُ تَبَارَكْتَ يَا ذَا الْجَلَالِ وَ اْلاِكْرَامِ\n" +
                "\n" +
                "Dedikten sonra elleri yukarı kaldırıp\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَ عَلَٓى اٰلِ سَيِّدِنَا مُحَمَّدٍ صَلَاةً تُنْج۪ينَا بِهَا مِنْ جَم۪يعِ الْاَهْوَالِ وَ اْلاٰفَاتِ وَ تَقْض۪ى لَنَا بِهَا جَم۪يعَ الْحَاجَاتِ وَ تُطَهِّرُنَا بِهَا مِنْ جَم۪يعِ السَّيِّئَاتِ وَ تَرْفَعُنَا بِهَا عِنْدَكَ اَعْلَى الدَّرَجَاتِ وَ تُبَلِّغُنَا بِهَٓا اَقْصَى الْغَايَاتِ مِنْ جَم۪يعِ الْخَيْرَاتِ فِى الْحَيَاةِ وَ بَعْدَ الْمَمَاتِ اٰم۪ينَ يَا مُج۪يبَ الدَّعَوَاتِ وَالْحَمْدُ لِلّٰهِ رَبِّ الْعَالَم۪ينَ\n" +
                "\n" +
                "bundan sonra şu dua okunur\n" +
                "\n" +
                "سُبْحَانَ اللّٰهِ وَالْحَمْدُ لِلّٰهِ وَلَا إِلَهَ إِلَّا اللَّهُ وَ اللّٰهُ اَكْبَرْ وَلَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللّٰهِ الْعَلِيِّ الْعَظِيمِْ\n" +
                "\n" +
                "Âyet-ül Kürsi okunur\n" +
                "\n" +
                "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّح۪يمِ\n" +
                "\n" +
                "اَللّٰهُ لَٓا اِلٰهَ اِلَّا هُوَۚ اَلْحَيُّ الْقَيُّومُۚ لَا تَأْخُذُهُ سِنَةٌ وَلَا نَوْمٌۜ لَهُ مَا فِي السَّمٰوَاتِ وَمَا فِي الْاَرْضِۜ مَنْ ذَا الَّذ۪ي يَشْفَعُ عِنْدَهُٓ اِلَّا بِاِذْنِه۪ۜ يَعْلَمُ مَا بَيْنَ اَيْد۪يهِمْ وَمَا خَلْفَهُمْۚ وَلَا يُح۪يطُونَ بِشَيْءٍ مِنْ عِلْمِه۪ٓ اِلَّا بِمَا شَٓاءَۚ وَسِعَ كُرْسِيُّهُ السَّمٰوَاتِ وَالْاَرْضَۚ وَلَا يَؤُ۫دُهُ حِفْظُهُمَاۚ وَهُوَ الْعَلِيُّ الْعَظ۪يمُ\n" +
                "\n" +
                "ardından 33 defa\n" +
                "\n" +
                "سُبْحَانَ اللّٰهِ\n" +
                "\n" +
                "33 defa\n" +
                "\n" +
                "اَلْحَمْدُ لِلّٰهِ\n" +
                "\n" +
                "33 defa\n" +
                "\n" +
                "اَللّٰهُ اَكْبَرُ\n" +
                "\n" +
                "ve namazın duası yapılır. 1 defa\n" +
                "\n" +
                "فَاعْلَمْ اَنَّهُ\n" +
                "\n" +
                "Ve 33 defa\n" +
                "\n" +
                "لَٓا اِلٰهَ اِلَّا اللّٰهُ\n" +
                "\n" +
                "deyip 33.de\n" +
                "\n" +
                "مُحَمَّدٌ رَسُولُ اللّٰهِ صَلَّى اللّٰهُ تَعَالٰى عَلَيْهِ وَ سَلَّمَ\n" +
                "\n" +
                "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّح۪يمِ\n" +
                "\n" +
                "اِنَّ اللّٰهَ وَ مَلَٓئِكَتَهُ يُصَلُّونَ عَلَى النَّبِىِّ يَٓا اَيُّهَا الَّذ۪ينَ اٰمَنُوا صَلُّوا عَلَيْهِ وَ سَلِّمُوا تَسْل۪يمًا ❁ لَبَّيْكَ\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَعَلَٓى اٰلِ سَيِّدِنَا مُحَمَّدٍ بِعَدَدِ كُلِّ دَٓاءٍ وَدَوَٓاءٍ وَبَارِكْ وَسَلِّمْ عَلَيْهِ وَعَلَيْهِمْ كَث۪يرًا\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَعَلَٓى اٰلِ سَيِّدِنَا مُحَمَّدٍ بِعَدَدِ كُلِّ دَٓاءٍ وَدَوَٓاءٍ وَبَارِكْ وَسَلِّمْ عَلَيْهِ وَعَلَيْهِمْ كَث۪يرًا\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَعَلَٓى اٰلِ سَيِّدِنَا مُحَمَّدٍ بِعَدَدِ كُلِّ دَٓاءٍ وَدَوَٓاءٍ وَبَارِكْ وَسَلِّمْ عَلَيْهِ وَعَلَيْهِمْ كَث۪يرًا كَث۪يرًا\n" +
                "\n" +
                "صَلِّ وَ سَلِّمْ يَا رَبِّ عَلٰى حَب۪يبِكَ مُحَمَّدٍ وَ عَلٰى جَم۪يعِ الْاَنْبِيَٓاءِ وَ الْمُرْسَل۪ينَ وَ عَلَٓى اٰلِ كُلٍّ وَ صَحْبِ كُلٍّ اَجْمَع۪ينَ اٰم۪ينَ وَالْحَمْدُ لِلّٰهِ رَبِّ الْعَالَم۪ينَ\n" +
                "\n" +
                "اَلْفُ اَلْفِ صَلَاةٍ وَ اَلْفُ اَلْفِ سَلَامٍ عَلَيْكَ يَا رَسُولَ اللّٰهِ\n" +
                "\n" +
                "اَلْفُ اَلْفِ صَلَاةٍ وَ اَلْفُ اَلْفِ سَلَامٍ عَلَيْكَ يَا حَب۪يبَ اللّٰهِ\n" +
                "\n" +
                "اَلْفُ اَلْفِ صَلَاةٍ وَ اَلْفُ اَلْفِ سَلَامٍ عَلَيْكَ يَٓا اَم۪ينَ وَحْىِ اللّٰهِ\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ وَ سَلِّمْ وَ بَارِكْ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَعَلَٓى اٰلِه۪ وَ اَصْحَابِه۪ بِعَدَدِ اَوْرَاقِ الْاَشْجَارِ وَ اَمْوَاجِ الْبِحَارِ وَ قَطَرَاتِ الْاَمْطَارِ وَ اغْفِرْلَنَا وَ ارْحَمْنَا وَ الْطُفْ بِنَا وَ بِاُسْتَادِنَا سَع۪يدِ النُّورْس۪ى رَضِىَ اللّٰهُ عَنْهُ وَ وَالِدَيْنَا وَ بِطَلَبَةِ رَسَٓائِلِ النُّورِ الصَّادِق۪ينَ يَٓا اِلٰهَنَا بِكُلِّ صَلَاةٍ مِنْهَٓا اَشْهَدُ اَنْ لَٓا اِلٰهَ اِلَّا اللّٰهُ وَ اَشْهَدُ اَنَّ مُحَمَّدًا رَسُولُ اللّٰهِ صَلَّى اللّٰهُ تَعَالٰى عَلَيْهِ وَ سَلَّمْ\n" +
                "\n" +
                "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّح۪يمِ\n" +
                "\n" +
                "سُبْحَانَك يَٓا ﴿اَللّٰهُ﴾ تَعَالَيْتَ يَا ﴿رَحْمٰنُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿رَح۪يمُ﴾ تَعَالَيْتَ يَا ﴿كَر۪يمُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿حَم۪يدُ﴾ تَعَالَيْتَ يَا ﴿حَك۪يمُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿مَج۪يدُ﴾ تَعَالَيْتَ يَا ﴿مَلِكُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰن\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿قُدُّوسُ﴾ تَعَالَيْتَ يَا ﴿سَلَامُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿مُؤْمِنُ﴾ تَعَالَيْتَ يَا ﴿مُهَيْمِنُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿عَز۪يزُ﴾ تَعَالَيْتَ يَا ﴿جَبَّارُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿مُتَكَبِّرُ﴾ تَعَالَيْتَ يَا ﴿خَالِقُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَٓا ﴿اَوَّلُ﴾ تَعَالَيْتَ يَٓا ﴿اٰخِرُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿ظَاهِرُ﴾ تَعَالَيْتَ يَا ﴿بَاطِنُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿بَارِىءُ﴾ تَعَالَيْتَ يَا ﴿مُصَوِّرُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿تَوَّابُ﴾ تَعَالَيْتَ يَا ﴿وَهَّابُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿بَاعِثُ﴾ تَعَالَيْتَ يَا ﴿وَارِثُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿قَد۪يمُ﴾ تَعَالَيْتَ يَا ﴿مُق۪يمُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿فَرْدُ﴾ تَعَالَيْتَ يَا ﴿وِتْرُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿نُورُ﴾ تَعَالَيْتَ يَا ﴿سَتَّارُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿جَل۪يلُ﴾ تَعَالَيْتَ يَا ﴿جَم۪يلُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿قَاهِرُ﴾ تَعَالَيْتَ يَا ﴿قَادِرُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿مَل۪يكُ﴾ تَعَالَيْتَ يَا ﴿مُقْتَدِرُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿عَل۪يمُ﴾ تَعَالَيْتَ يَا ﴿عَلَّا مُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿عَظ۪يمُ﴾ تَعَالَيْتَ يَا ﴿غَفُورُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿حَل۪يمُ﴾ تَعَالَيْتَ يَا ﴿وَدُودُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿شَه۪يدُ﴾ تَعَالَيْتَ يَا ﴿شَاهِدُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿كَب۪يرُ﴾ تَعَالَيْتَ يَا ﴿مُتَعَالُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿نُورُ﴾ تَعَالَيْتَ يَا ﴿لَط۪يفُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿سَم۪يعُ﴾ تَعَالَيْتَ يَا ﴿كَف۪يلُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿قَر۪يبُ﴾ تَعَالَيْتَ يَا ﴿بَص۪يرُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿حَقُّ﴾ تَعَالَيْتَ يَا ﴿مُب۪ينُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿رَؤُفُ﴾ تَعَالَيْتَ يَا ﴿رَح۪يمُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿طَاهِرُ﴾ تَعَالَيْتَ يَا ﴿مُطَهِّرُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿مُجَمِّلُ﴾ تَعَالَيْتَ يَا ﴿مُفَضِّلُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿مُظْهِرُ﴾ تَعَالَيْتَ يَا ﴿مُنْعِمُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿دَيَّانُ﴾ تَعَالَيْتَ يَا ﴿سُلْطَانُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿حَنَّانُ﴾ تَعَالَيْتَ يَا ﴿مَنَّانُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَٓا ﴿اَحَدُ﴾ تَعَالَيْتَ يَا ﴿صَمَدُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿حَىُّ﴾ تَعَالَيْتَ يَا ﴿قَيُّومُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿عَدْلُ﴾ تَعَالَيْتَ يَا ﴿حَكَمُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ يَا ﴿فَرْدُ﴾ تَعَالَيْتَ يَا ﴿قُدُّوسُ﴾ اَجِرْنَا مِنَ النَّارِ بِعَفْوِكَ يَا رَحْمٰنُ\n" +
                "\n" +
                "سُبْحَانَكَ اٰهِيًّا شَرَاهِيًّا تَعَالَيْتَ لَٓا اِلٰهَ اِلَّآ اَنْتَ اَجِرْنَا وَ اَجِرْ اُسْتَادَنَا سَع۪يدَ النُّورْس۪ى رَضِىَ اللّٰهُ عَنْهُ وَ وَالِدَيْنَا وَ اِخْوَانَنَا وَ اَخَوَاتَنَا وَ طَلَبَةَ رَسَٓائِلِ النُّورِ وَ رُفَقَٓائَنَا وَ اَحْبَابَنَا الْمُخْلِص۪ينَ مِنَ النَّارِ وَ مِنْ كُلِّ نَارٍ وَ احْفَظْنَا مِنْ شَرِّ النَّفْسِ وَ الشَّيْطَانِ وَ مِنْ شَرِّ الْجِنِّ وَ اْلاِنْسَانِ وَ مِنْ شَرِّ الْبِدْعَةِ وَ الضَّلَالَاتِ وَ اْلاِلْحَادِ وَ الطُّغْيَانِ بِعَفْوِكَ يَا مُج۪يرُ بِفَضْلِكَ يَا غَفَّارُ بِرَحْمَتِكَ يَٓا اَرْحَمَ الرَّاحِم۪ينَ ❁ اَللّٰهُمَّ اَدْخِلْنَا الْجَنَّةَ مَعَ الْاَبْرَارِ بِشَفَاعَةِ نَبِيِّكَ الْمُخْتَارِ اٰم۪ينَ وَالْحَمْدُ لِلّٰهِ رَبِّ الْعَالَم۪ينَ\n" +
                "\n" +
                "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّح۪يمِ\n" +
                "\n" +
                "عَمَّ يَتَسَٓاءَلُونَۚ ﴿١﴾\n" +
                "\n" +
                "عَنِ النَّبَاِ الْعَظ۪يمِۙ ﴿٢﴾\n" +
                "\n" +
                "اَلَّذ۪ى هُمْ ف۪يهِ مُخْتَلِفُونَۜ ﴿٣﴾\n" +
                "\n" +
                "كَلَّا سَيَعْلَمُونَۙ ﴿٤﴾\n" +
                "\n" +
                "ثُمَّ كَلَّا سَيَعْلَمُونَ ﴿٥﴾\n" +
                "\n" +
                "اَلَمْ نَجْعَلِ الْاَرْضَ مِهَادًاۙ ﴿٦﴾\n" +
                "\n" +
                "وَالْجِبَالَ اَوْتَادًا۬ ﴿٧﴾\n" +
                "\n" +
                "وَخَلَقْنَاكُمْ اَزْوَاجًاۙ ﴿٨﴾\n" +
                "\n" +
                "وَجَعَلْنَا نَوْمَكُمْ سُبَاتًاۙ ﴿٩﴾\n" +
                "\n" +
                "وَجَعَلْنَا الَّيْلَ لِبَاسًاۙ ﴿٠١﴾\n" +
                "\n" +
                "وَجَعَلْنَا النَّهَارَ مَعَاشًا۬ ﴿١١﴾\n" +
                "\n" +
                "وَبَنَيْنَا فَوْقَكُمْ سَبْعًا شِدَادًاۙ ﴿٢١﴾\n" +
                "\n" +
                "وَجَعَلْنَا سِرَاجًا وَهَّاجًا۬ ﴿٣١﴾\n" +
                "\n" +
                "وَاَنْزَلْنَا مِنَ الْمُعْصِرَاتِ مَٓاءً ثَجَّاجًاۙ ﴿٤١﴾\n" +
                "\n" +
                "لِنُخْرِجَ بِه۪ حَبًّا وَنَبَاتًاۙ ﴿٥١﴾\n" +
                "\n" +
                "وَجَنَّاتٍ اَلْفَافًاۜ ﴿٦١﴾\n" +
                "\n" +
                "اِنَّ يَوْمَ الْفَصْلِ كَانَ م۪يقَاتًاۙ ﴿٧١﴾\n" +
                "\n" +
                "يَوْمَ يُنْفَخُ فِى الصُّورِ فَتَاْتُونَ اَفْوَاجًاۙ ﴿٨١﴾\n" +
                "\n" +
                "وَفُتِحَتِ السَّمَٓاءُ فَكَانَتْ اَبْوَابًاۙ ﴿٩١﴾\n" +
                "\n" +
                "وَسُيِّرَتِ الْجِبَالُ فَكَانَتْ سَرَابًاۜ ﴿٠٢﴾\n" +
                "\n" +
                "اِنَّ جَهَنَّمَ كَانَتْ مِرْصَادًاۙ ﴿١٢﴾\n" +
                "\n" +
                "لِلطَّاغ۪ينَ مَاٰبًاۙ ﴿٢٢﴾\n" +
                "\n" +
                "لَابِث۪ينَ ف۪يهَٓا اَحْقَابًاۚ ﴿٣٢﴾\n" +
                "\n" +
                "لَايَذُوقُونَ ف۪يهَا بَرْدًا وَلَا شَرَابًاۙ ﴿٤٢﴾\n" +
                "\n" +
                "اِلَّا حَم۪يمًا وَغَسَّاقًاۙ ﴿٥٢﴾\n" +
                "\n" +
                "جَزَٓاءً وِفَاقًا ﴿٦٢﴾\n" +
                "\n" +
                "اِنَّهُمْ كَانُوا لَايَرْجُونَ حِسَابًاۙ ﴿٧٢﴾\n" +
                "\n" +
                "وَكَذَّبُوا بِاٰيَاتِنَا كِذَّابًاۜ ﴿٨٢﴾\n" +
                "\n" +
                "وَكُلَّ شَىْءٍ اَحْصَيْنَاهُ كِتَابًا ﴿٩٢﴾\n" +
                "\n" +
                "فَذُوقُوا فَلَنْ نَز۪يدَكُمْ اِلَّا عَذَابًا۟ ﴿٠٣﴾\n" +
                "\n" +
                "اِنَّ لِلْمُتَّق۪ينَ مَفَازًاۙ ﴿١٣﴾\n" +
                "\n" +
                "حَدَٓائِقَ وَاَعْنَابًاۙ ﴿٢٣﴾\n" +
                "\n" +
                "وَكَوَاعِبَ اَتْرَابًاۙ ﴿٣٣﴾\n" +
                "\n" +
                "وَكَاْسًا دِهَاقًاۜ ﴿٤٣﴾\n" +
                "\n" +
                "لَايَسْمَعُونَ ف۪يهَالَغْوًا وَلَا كِذَّابًاۚ ﴿٥٣﴾\n" +
                "\n" +
                "جَزَٓاءً مِنْ رَبِّكَ عَطَٓاءً حِسَابًاۙ ﴿٦٣﴾\n" +
                "\n" +
                "رَبِّ السَّمٰوَاتِ وَالْاَرْضِ وَمَا بَيْنَهُمَاۙ الرَّحْمٰنِ لَايَمْلِكُونَ مِنْهُ خِطَابًاۙ ﴿٧٣﴾\n" +
                "\n" +
                "يَوْمَ يَقُومُ الرُّوحُ وَالْمَلٰٓئِكَةُ صَفًّاۜ لَايَتَكَلَّمُونَ اِلَّا مَنْ اَذِنَ لَهُ الرَّحْمٰنُ وَقَالَ صَوَابًا ﴿٨٣﴾\n" +
                "\n" +
                "ذٰلِكَ الْيَوْمُ الْحَقُّۚ فَمَنْ شَٓاءَ اتَّخَذَ اِلٰى رَبِّه۪ مَاٰبًا ﴿٩٣﴾\n" +
                "\n" +
                "اِنَّٓا اَنْذَرْنَاكُمْ عَذَابًا قَر۪يبًاۚ يَوْمَ يَنْظُرُ الْمَرْءُ مَا قَدَّمَتْ يَدَاهُ وَيَقُولُ الْكَافِرُ يَالَيْتَن۪ى كُنْتُ تُرَابًا ﴿٠٤﴾";

        TextView ttv = binding.tesbihatTextview;
        ttv.setText(tesbihatStr);
        ttv.setMovementMethod(new ScrollingMovementMethod());




    }
    public void ikinditurkce () {


        String tesbihatStr = "İkindi namazının farzı kılınıp selâm verildikten sonra, “Allâhümme ente’s-selâmü ve minke’s-selâm, tebârekte yâ ẕe’l-celâli ve’likrâm” denir ve aşağıdaki Salât-ı Münciye Duası, (yani Salâte’n-Tüncînâ) oku\u00ADnur: “Allahümme šalli alâ seyyidinâ Muḥammedin ve ʿalâ âli seyyidinâ Muḥammed. Šalâten tüncînâ bihâ min-cemîʿı’l-ehvâli ve’lâfât. (“ve’l-âfât” derken avuç içleri yere bakacak şekilde çevrilir, bitince de eski haline getirilir.) Ve takḍîlenâ bi-hâ cemîʿa’l-ḥâcât. Ve tüṭahhirunâ bi-hâ min-cemîʿı’s-seyyiât. Ve terfeʿunâ bi-hâ ʿındeke aʿle’d-derecât. Ve tübelliğunâ bi-hâ aḳša’l-ğâyât. Min-cemîʿı’lḫayrâti fi’l-ḥayâti ve baʿde’l-memât. Âmin yâ Mucîbe’d-deʿavâti ve’l-ḥamdü lillâhi Rabbi’lʿâlemîn. Âmin” denilir ve eller yüze sürülüp indirilir.\n" +
                "\n" +
                "Sonra namaz tesbihatına şu dua ile devam edi\u00ADlir: “Sübḥânallahi ve’l-ḥamdü lillâhi ve lâ ilâhe illâllahü vallahü ekber ve lâ ḥavle ve lâ ḳuvvete illâ billâhi’l-ʿaliyyi’l-ʿaẓîm.”Ve Âyete’l-Kürsî okunur: “Bismillâhirraḥmânirraḥîm. Allahü lâ ilâhe illâ hüve’l-ḥayyü’l-ḳayyûm. Lâ te’ḫuẕühû sinetün ve lâ nevm. Lehû mâ fi’s-semâvâti vemâ fi’l-arḍı. Menẕelleẕî yeşfeʿu ʿındehû illâ bi-iẕnih. Yâʿlemü mâ beyne eydîhim ve mâ ḫalfehüm ve lâ yuḥîṭûne bi-şey’in min-ʿılmihî illâ bi-mâ şâe. Vesiʿa kürsiyyühü’s-semâvâti ve’l-arḍi. Velâ yeûdü\u00AD hû ḥıfẓuhümâ ve hüve’l-ʿaliyyü’l-ʿaẓîm.”\n" +
                "\n" +
                "Şu tesbih sözleri tekrarlanır: “Ve hüve’l-‘aliyyü’l-‘az. îmu zü’l-celâli sübḥâ\u00AD nellâh” (1 defa), “Sübḥânallâh” (33 defa) “Sübḥâne’l-bâḳî dâimeni’l-ḥamdülillâh” (1 defa), “Elḥamdülillâh” (33 defa) “Rabbi’l-‘âlemîne te‘âlâ şânühü Allâhü Ekber” (1 defa), “Allâhü Ekber” (33 defa) “Lâ ilâhe illallâhü, vaḥdehû, lâ şerîke leh, lehü’l-mülkü ve lehü’l-ḥamdü, yuḥyî ve yümît ve hüve ḥayyün lâyemût, bi-yedihi’l-ḫayru ve hüve ʿalâ külli şey’in ḳadîrun ve ileyhi’l-mašîr” denilir ve na\u00ADmaz duası yapılır.\n" +
                "\n" +
                "Duâdan sonra tesbihata şöyle devam edilir: “Faʿlem ennehu” (1 defa)\n" +
                "“Lâ ilâhe illâllah” (33 defa) denilir ve ardından; “Muḥammedü’r-resûlüllâhi šallâllahü teʿâlâ ʿaleyhi vesellem.” (1 defa)\n" +
                "\n" +
                "“Bismillâhirraḥmânirraḥîm. İnnallâhe ve melâiketehû yüšallûne ʿale’nnebiy. Yâ eyyühelleẕîne âmenû šallû ʿaleyhi ve sellimû teslîmâ. Lebbeyk.\n" +
                "\n" +
                "Allahümme šalli ʿalâ seyyidinâ Muḥammedin ve ʿalâ âli seyyidinâ Muḥammed. Biʿadedi külli dâin ve devâin ve bârik ve sellim ʿaleyhi ve ʿaleyhim kesîrâ. Allahümme šalli ʿalâ seyyidinâ Muḥammedin ve ʿalâ âli seyyidinâ Muḥammed. Biʿadedi külli dâin ve devâin ve bârik ve sellim ʿaleyhi ve ʿaleyhim kesîrâ. Allahümme šalli ʿalâ seyyidinâ Muḥammedin ve ʿalâ âli seyyidinâ Muḥammed. Biʿadedi külli dâin ve devâin ve bârik ve sellim ʿaleyhi ve ʿaleyhim kesîran kesîrâ. Šalli ve sellim yâ Rabbi ʿalâ ḥabîbike Muḥammedin ve ʿalâ cemîʿı’l-enbiyâi ve’l-mürselîn ve ʿalâ âli küllin ve šahbi küllin ecmaʿîn. Âmin ve’l-ḥamdü lillâhi Rabbi’l-ʿâlemîn.\n" +
                "\n" +
                "Elfü elfi šalâtin ve elfü elfi selâmin ʿaleyke yâ Resûlallah.\n" +
                "Elfü elfi šalâtin ve elfü elfi selâmin ʿaleyke yâ Ḥabîballah.\n" +
                "Elfü elfi šalâtin ve elfü elfi selâmin ʿaleyke yâ emîne vaḥyillâh.\n" +
                "Allahümme šalli ve sellim ve bârik ʿalâ seyyidinâ Muḥammedin ve ʿalâ âlihî ve ašḥâbihî bi-ʿadedi evraḳı’l-eşcâr ve emvâci’lbiḥâr ve ḳaṭarâti’l-emṭâr vağfir lenâ verḥamnâ velṭuf binâ ve bi-Üstâẕinâ Saʿîdi’n-Nursî (radiyallahu ʿanh) ve vâlideynâ ve bi-ṭalebeti Resâili’n-Nuri’š-šadıḳîne yâ ilâhenâ bi-külli šalâtin minhâ eşhedü en lâilâhe illâllah ve eşhedü enne Muḥammeden Resûlüllâhi Šallallâhü ʿAleyhi Ve sellem.”Aşağıdaki Duâ-i Tercümân-ı İsm-i A’zâm ile tes\u00ADbi\u00ADhata devam edilir:\n" +
                "\n" +
                "Sübḥâneke yâ Allah teʿâleyte yâ Raḥmân ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Raḥîm teʿâleyte yâ Kerîm ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ḥamîd teʿâleyte yâ Ḥakîm ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Mecîd teʿâleyte yâ Melîk ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ḳuddûs teʿâleyte yâ Selâm ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Mü’min teʿâleyte yâ Müheymin ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ ʿAzîz teʿâleyte yâ Cebbâr ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Mütekebbîr teʿâleyte yâ Ḫàlık ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Evvel teʿâleyte yâ Âḫir ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ẓâhir teʿâleyte yâ Bâṭın ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Bâri’ teʿâleyte yâ Mušavvir ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Tevvâb teʿâleyte yâ Vehhâb ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Bâis teʿâleyte yâ Vâris ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ḳadîm teʿâleyte yâ Muḳîm ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ferd teʿâleyte yâ Vitr ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Nûr teʿâleyte yâ Settâr ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Celîl teʿâleyte yâ Cemîl ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ḳàhir teʿâleyte yâ Ḳàdir ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Melîk teʿâleyte yâ Muḳtedir ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ ʿAlîm teʿâleyte yâ ʿAllâm ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ ʿAẓîm teʿâleyte yâ Ğafûr ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ḥalîm teʿâleyte yâ Vedûd ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Şehîd teʿâleyte yâ Şâhid ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Kebîr teʿâleyte yâ Müteʿâl ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Nûr teʿâleyte yâ Lâṭîf ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Semiʿ teʿâleyte yâ Kefîl ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ḳarîb teʿâleyte yâ Bašîr ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ḥaḳ teʿâleyte yâ Mübîn ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Raûf teʿâleyte yâ Raḥîm ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ṭâhir teʿâleyte yâ Muṭahhir ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Mücemmil teʿâleyte yâ Mufaḍ\u00AD ḍıl ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Muẓhir teʿâleyte yâ Münʿim ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Deyyân teʿâleyte yâ Sulṭân ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ḥannân teʿâleyte yâ Mennân ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Eḥad teʿâleyte yâ Šamed ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ḥayy teʿâleyte yâ Ḳayyûm ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ ʿAdl teʿâleyte yâ Ḥakem ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Sübḥâneke yâ Ferd teʿâleyte yâ Ḳuddûs ecirnâ mine’n-nâr bi-ʿafvike yâ Raḥmân.\n" +
                "Avuçlar yukarı gelecek şekilde eller kaldırılır: “Sübḥâneke âhiyyen şerâhiyyen teʿâleyte lâ ilâhe illâ ente ecirnâ ve ecir Üstâẕenâ Saʿîde’n-Nursî (raḍıyallâhu ʿanh) ve vâlideynâ ve iḫvânenâ ve eḫavâtinâ ve ṭalebete Resâili’n-Nûri ve rufeḳàenâ ve aḥbâbene’l-mü’minîne’lmuḫlišîne mine’n-nâr. (Avuç içleri yere bakacak şekilde çevrilir.) Ve min-külli nâr vaḥ\u00AD feẓnâ min-şerri’n-nefsi ve’ş-şeyṭân ve minşerri’l-cinni ve’l-insân ve min-şerri’l-bidʿati ve’ḍ-ḍalâlâti ve’l-ilḥâdi ve’ṭ-ṭuğyân. (Avuç iç\u00ADleri tekrar yukarıya baka\u00ADcak şekle getirilir.) Bi-ʿafvike yâ Mücîr, bi-faḍlike yâ Ğaffâr biraḥmetike yâ Erḥame’r-râḥimîn. Allahümm’e-dḫılne’l-Cennete meʿâ’l-ebrâr, bi\u00AD-şefâʿati nebiyyike’l-muḫtâr. Âmin, ve’lḥamdü lillâhi Rabbi’l-Âlemîn.”\n" +
                "\n" +
                "Nebe’ (Amme) Sûresi okunur ve tesbihat sona erer.\n" +
                "\n" +
                "“Bismillâhirraḥmânirraḥîm ʿAmme yetesêelûn . ʿAni’n-nebei’l-ʿaẓîm . Elleẓî hüm fîhi muḫtelifûn . Kellâ seyʿalemûn . Sümme kellâ seyʿalemûn . Elem necʿali’l-arḍa mihâdâ . Ve’l-cibâle evtâdâ . Ve ḫalaḳnâküm ezvâcâ . Ve ceʿalnâ nevmeküm sübâtâ . Ve ceʿalne’l-leyle libâsâ . Ve ceʿalne’n-nehâra meʿâşâ . Ve beneynâ fevḳaküm sebʿan şidâdâ . Ve ceʿalnâ sirâcev-vehhâcâ . Ve enzelnâ mine’l-mu’širâti mâen seccâcâ . Linuḫrice bihî ḥabbev-venebâtâ . Ve cennâtin elfâfâ . İnne yevme’l-fašli kâne mîḳàtâ . Yevme yünfeḫu fiššûri fete’tûne efvâcâ . Ve fütiḥatissemâü fekânet ebvâbâ . Ve süyyirati’l-cibâlü fekânet serâbâ . İnne Cehenneme kânet miršâdâ . Li’ṭ-ṭâğîne meâbâ . Lâbisîne fîhâ eḥḳàbâ . Lâ yeẕûḳùne fîhâ berdev-velâ şarâbâ . İllâ ḥamîmev-veğassâḳâ .cezâev-vifâḳâ . İnnehüm kânû lâ yercûne ḥisâbâ . Ve keẕẕebû bi-âyâtinâ kiẕẕâbâ . Ve külle şey’in aḥšaynâhü kitâbâ . Feẕûḳû felen-nezîdeküm illâ ʿaẕâbâ . İnne li’l-mütte\u00AD ḳîne mefâzâ . Ḥadâiḳa ve aʿnâbâ . Ve kevâibe etrâbâ . Ve ke’sen dihâḳâ . Lâ yesmeʿûne fîhâ lağvev-velâ-kiẕẕâbâ . Cezâem-mi’rRabbike ʿaṭâen ḥisâbâ . Rabbi’s-semâvâti ve’l-arḍi vemâ beynehüme’r-raḥmâni lâ yemlikûne minhü ḫiṭâbâ . Yevme yeḳûmü’r-rûḥu ve’l-melâiketü šaffâ . Lâ yetekellemûne illâ men eẕine lehü’r-raḥmânü ve ḳâle šavâbâ . Ẕâlike’l-yevmü’l-haḳḳu femenşâetteḫaẕe ilâ Rabbihî meâbâ . İnnâ enẕernâküm ʿaẕâben ḳarîbâ, yevme yenẓuru’l-mer’ü mâ ḳaddemet yedâhü ve yeḳùlü’l-kâfiru yâ leytenî küntü tü\u00AD râbâ.";

        TextView ttv = binding.tesbihatTextview;
        ttv.setText(tesbihatStr);
        ttv.setMovementMethod(new ScrollingMovementMethod());




    }
    public void aksamArapca () {


        String tesbihatStr = "Akşam namazının farzının selamını verdikten sonra:\n" +
                "\n" +
                "اَللّٰهُمَّ اَنْتَ السَّلَامُ وَ مِنْكَ السَّلَامُ تَبَارَكْتَ يَا ذَا الْجَلَالِ وَ اْلاِكْرَامِ\n" +
                "\n" +
                "Dedikten sonra elleri yukarı kaldırıp\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَ عَلَٓى اٰلِ سَيِّدِنَا مُحَمَّدٍ صَلَاةً تُنْج۪ينَا بِهَا مِنْ جَم۪يعِ الْاَهْوَالِ وَ اْلاٰفَاتِ وَ تَقْض۪ى لَنَا بِهَا جَم۪يعَ الْحَاجَاتِ وَ تُطَهِّرُنَا بِهَا مِنْ جَم۪يعِ السَّيِّئَاتِ وَ تَرْفَعُنَا بِهَا عِنْدَكَ اَعْلَى الدَّرَجَاتِ وَ تُبَلِّغُنَا بِهَٓا اَقْصَى الْغَايَاتِ مِنْ جَم۪يعِ الْخَيْرَاتِ فِى الْحَيَاةِ وَ بَعْدَ الْمَمَاتِ اٰم۪ينَ يَا مُج۪يبَ الدَّعَوَاتِ وَالْحَمْدُ لِلّٰهِ رَبِّ الْعَالَم۪ينَ\n" +
                "\n" +
                "Deyip namazın sünneti eda edildikten sonra 1 defa\n" +
                "\n" +
                "اٰمَنَّا بَاَنَّهُ\n" +
                "\n" +
                "Ve 9 defa\n" +
                "\n" +
                "لَٓا اِلٰهَ اِلَّا اللّٰهُ وَحْدَهُ لَٓا شَر۪يكَ لَهُ لَهُ الْمُلْكُ وَ لَهُ الْحَمْدُ يُحْي۪ى وَ يُم۪يتُ بِيَدِهِ الْخَيْرُ وَ هُوَ عَلٰى كُلِّ شَىْءٍ قَد۪يرٌ\n" +
                "\n" +
                "10 nuncusunda\n" +
                "\n" +
                "لَٓا اِلٰهَ اِلَّا اللّٰهُ وَحْدَهُ لَٓا شَر۪يكَ لَهُ لَهُ الْمُلْكُ وَ لَهُ الْحَمْدُ يُحْي۪ى وَ يُم۪يتُ وَ هُوَ حَىٌّ لَا يَمُوتُ بِيَدِهِ الْخَيْرُ وَ هُوَ عَلٰى كُلِّ شَىْءٍ قَد۪يرٌ وَ اِلَيْهِ الْمَص۪يرُ\n" +
                "\n" +
                "Sonra elleri kaldırıp avuç içleri aşağı çevrilerek\n" +
                "\n" +
                "اَللّٰهُمَّ اَجِرْنَا مِنَ النَّارِ\n" +
                "\n" +
                "3-5-7 defa okunur\n" +
                "\n" +
                "اَللّٰهُمَّ اَجِرْنَا مِنْ كُلِّ نَارٍ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ فِتْنَةِ الدّ۪ينِيَّةِ وَ الدُّنْيَوِيَّةِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ فِتْنَةِ اٰخِرِ الزَّمَانِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ فِتْنَةِ الْمَس۪يحِ الدَّجَّالِ وَ السُّفْيَانِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنَ الضَّلَالَاتِ وَ الْبِدْعِيَّاتِ وَ الْبَلِيَّاتِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ شَرِّ النَّفْسِ الْاَمَّارَةِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ شُرُورِ النُّفُوسِ الْاَمَّارَاتِ الْفِرْعَوْنِيَّةِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ شَرِّ النِّسَٓاءِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ بَلَٓاءِ النِّسَٓاءِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ فِتْنَةِ النِّسَٓاءِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ عَذَابِ الْقَبْرِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ عَذَابِ يَوْمِ الْقِيٰمَةِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ عَذَابِ جَهَنَّمَ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ عَذَابِ قَهْرِكَ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ نَارِ قَهْرِكَ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ عَذَابِ الْقَبْرِ وَ ال۪نّيرَانِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنَ الرِّيَاءِ وَ السُّمْعَةِ وَ الْعُجُبِ وَ الْفَخْرِ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ تَجَاوُزِ الْمُلْحِد۪ينَ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ شَرِّ الْمُنَافِق۪ينَ ❁ اَللّٰهُمَّ اَجِرْنَا مِنْ فِتْنَةِ الْفَاسِق۪ينَ ❁ اَللّٰهُمَّ اَجِرْنَا وَ اَجِرْ وَالِدَيْنَا وَ طَلَبَةَ رَسَٓائِلِ النُّورِ الصَّادِق۪ينَ ف۪ى خِدْمَةِ الْقُرْاٰنِ وَ اْلا۪يمَانِ وَ اَحْبَابَنَا الْمُؤْمِن۪ينَ الْمُخْلِص۪ينَ وَ اَقْرِبَٓائَنَا وَ اَجْدَادَنَا مِنَ النَّارِ\n" +
                "\n" +
                "Ellerini yukarı çevirerek\n" +
                "\n" +
                "بِعَفْوِكَ يَا مُج۪يرُ بِفَضْلِكَ يَا غَفَّارُ ❁ اَللّٰهُمَّ اَدْخِلْنَا الْجَنَّةَ مَعَ الْاَبْرَارِ ❁ اَللّٰهُمَّ اَدْخِلْنَا الْجَنَّةَ مَعَ الْاَبْرَارِ ❁ اَللّٰهُمَّ اَدْخِلْنَا وَ اَدْخِلْ اُسْتَادَنَا سَع۪يدَ النُّورْس۪ى رَضِىَ اللّٰهُ عَنْهُ وَ وَالِدَيْنَا وَ طَلَبَةَ رَسَٓائِلِ النُّورِ الصَّادِق۪ينَ وَ اِخْوَانَنَا وَ اَخَوَاتِنَا وَ اَقْرِبَٓائَنَا وَ اَجْدَادَنَا وَ اَحْبَابَنَا الْمُؤْمِن۪ينَ الْمُخْلِص۪ينَ ف۪ى خِدْمَةِ اْلا۪يمَانِ وَ الْقُرْاٰنِ اَلْجَنَّةَ مَعَ الْاَبْرَارِ صَلِّ عَلٰى نَبِيِّكَ الْمُخْتَارِ وَ اٰلِه۪ الْاَطْهَارِ وَ اَصْحَابِهِ الْاَخْيَارِ وَ سَلِّمْ مَادَامَ الَّيْلُ وَ النَّهَارُ اٰم۪ينَ وَالْحَمْدُ لِلّٰهِ رَبِّ الْعَالَم۪ينَ\n" +
                "\n" +
                "Bundan sonra şu dua okunur\n" +
                "\n" +
                "سُبْحَانَ اللّٰهِ وَالْحَمْدُ لِلّٰهِ وَلَا إِلَهَ إِلَّا اللَّهُ وَ اللّٰهُ اَكْبَرْ وَلَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللّٰهِ الْعَلِيِّ الْعَظِيمِْ\n" +
                "\n" +
                "Âyet-ül Kürsi okunur\n" +
                "\n" +
                "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّح۪يمِ\n" +
                "\n" +
                "اَللّٰهُ لَٓا اِلٰهَ اِلَّا هُوَۚ اَلْحَيُّ الْقَيُّومُۚ لَا تَأْخُذُهُ سِنَةٌ وَلَا نَوْمٌۜ لَهُ مَا فِي السَّمٰوَاتِ وَمَا فِي الْاَرْضِۜ مَنْ ذَا الَّذ۪ي يَشْفَعُ عِنْدَهُٓ اِلَّا بِاِذْنِه۪ۜ يَعْلَمُ مَا بَيْنَ اَيْد۪يهِمْ وَمَا خَلْفَهُمْۚ وَلَا يُح۪يطُونَ بِشَيْءٍ مِنْ عِلْمِه۪ٓ اِلَّا بِمَا شَٓاءَۚ وَسِعَ كُرْسِيُّهُ السَّمٰوَاتِ وَالْاَرْضَۚ وَلَا يَؤُ۫دُهُ حِفْظُهُمَاۚ وَهُوَ الْعَلِيُّ الْعَظ۪يمُ\n" +
                "\n" +
                "ardından 33 defa\n" +
                "\n" +
                "سُبْحَانَ اللّٰهِ\n" +
                "\n" +
                "33 defa\n" +
                "\n" +
                "اَلْحَمْدُ لِلّٰهِ\n" +
                "\n" +
                "33 defa\n" +
                "\n" +
                "اَللّٰهُ اَكْبَرُ\n" +
                "\n" +
                "Dedikten sonra namazın duası yapılır. Ve\n" +
                "\n" +
                "فَاعْلَمْ اَنَّهُ\n" +
                "\n" +
                "Bir defa ve\n" +
                "\n" +
                "لَٓا اِلٰهَ اِلَّا اللّٰهُ\n" +
                "\n" +
                "33 defa okunur. 33.de\n" +
                "\n" +
                "مُحَمَّدٌ رَسُولُ اللّٰهِ صَلَّى اللّٰهُ تَعَالٰى عَلَيْهِ وَ سَلَّمَ\n" +
                "\n" +
                "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّح۪يمِ\n" +
                "\n" +
                "اِنَّ اللّٰهَ وَ مَلَٓئِكَتَهُ يُصَلُّونَ عَلَى النَّبِىِّ يَٓا اَيُّهَا الَّذ۪ينَ اٰمَنُوا صَلُّوا عَلَيْهِ وَ سَلِّمُوا تَسْل۪يمًا ❁ لَبَّيْكَ\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَعَلَٓى اٰلِ سَيِّدِنَا مُحَمَّدٍ بِعَدَدِ كُلِّ دَٓاءٍ وَدَوَٓاءٍ وَبَارِكْ وَسَلِّمْ عَلَيْهِ وَعَلَيْهِمْ كَث۪يرًا\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَعَلَٓى اٰلِ سَيِّدِنَا مُحَمَّدٍ بِعَدَدِ كُلِّ دَٓاءٍ وَدَوَٓاءٍ وَبَارِكْ وَسَلِّمْ عَلَيْهِ وَعَلَيْهِمْ كَث۪يرًا\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَعَلَٓى اٰلِ سَيِّدِنَا مُحَمَّدٍ بِعَدَدِ كُلِّ دَٓاءٍ وَدَوَٓاءٍ وَبَارِكْ وَسَلِّمْ عَلَيْهِ وَعَلَيْهِمْ كَث۪يرًا كَث۪يرًا\n" +
                "\n" +
                "صَلِّ وَ سَلِّمْ يَا رَبِّ عَلٰى حَب۪يبِكَ مُحَمَّدٍ وَ عَلٰى جَم۪يعِ الْاَنْبِيَٓاءِ وَ الْمُرْسَل۪ينَ وَ عَلَٓى اٰلِ كُلٍّ وَ صَحْبِ كُلٍّ اَجْمَع۪ينَ اٰم۪ينَ وَالْحَمْدُ لِلّٰهِ رَبِّ الْعَالَم۪ينَ\n" +
                "\n" +
                "اَلْفُ اَلْفِ صَلَاةٍ وَ اَلْفُ اَلْفِ سَلَامٍ عَلَيْكَ يَا رَسُولَ اللّٰهِ\n" +
                "\n" +
                "اَلْفُ اَلْفِ صَلَاةٍ وَ اَلْفُ اَلْفِ سَلَامٍ عَلَيْكَ يَا حَب۪يبَ اللّٰهِ\n" +
                "\n" +
                "اَلْفُ اَلْفِ صَلَاةٍ وَ اَلْفُ اَلْفِ سَلَامٍ عَلَيْكَ يَٓا اَم۪ينَ وَحْىِ اللّٰهِ\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ وَ سَلِّمْ وَ بَارِكْ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَعَلَٓى اٰلِه۪ وَ اَصْحَابِه۪ بِعَدَدِ اَوْرَاقِ الْاَشْجَارِ وَ اَمْوَاجِ الْبِحَارِ وَ قَطَرَاتِ الْاَمْطَارِ وَ اغْفِرْلَنَا وَ ارْحَمْنَا وَ الْطُفْ بِنَا وَ بِاُسْتَادِنَا سَع۪يدِ النُّورْس۪ى رَضِىَ اللّٰهُ عَنْهُ وَ وَالِدَيْنَا وَ بِطَلَبَةِ رَسَٓائِلِ النُّورِ الصَّادِق۪ينَ يَٓا اِلٰهَنَا بِكُلِّ صَلَاةٍ مِنْهَٓا اَشْهَدُ اَنْ لَٓا اِلٰهَ اِلَّا اللّٰهُ وَ اَشْهَدُ اَنَّ مُحَمَّدًا رَسُولُ اللّٰهِ صَلَّى اللّٰهُ تَعَالٰى عَلَيْهِ وَ سَلَّمْ\n" +
                "\n" +
                "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّح۪يمِ\n" +
                "\n" +
                "يَا ﴿جَم۪يلُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿قَر۪يبُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مُج۪يبُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿حَب۪يبُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿رَؤُفُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿عَطُوفُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مَعْرُوفُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿لَط۪يفُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿عَظ۪يمُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿حَنَّانُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مَنَّانُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿دَيَّانُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿سُبْحَانُ﴾ يَٓا اَللّٰهُ ❁ يَٓا ﴿اَمَانُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿بُرْهَانُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿سُلْطَانُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مُسْتَعَانُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مُحْسِنُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مُتَعَالُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿رَحْمٰنُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿رَح۪يمُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿كَر۪يمُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مَج۪يدُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿فَرْدُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿وِتْرُ﴾ يَٓا اَللّٰهُ ❁ يَٓا ﴿اَحَدُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿صَمَدُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مَحْمُودُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿صَادِقَالْوَعْدِ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿عَلِىُّ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿غَنِىُّ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿شَاف۪ى﴾ يَٓا اَللّٰهُ ❁ يَا ﴿كَاف۪ى﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مُعَاف۪ى﴾ يَٓا اَللّٰهُ ❁ يَا ﴿بَاق۪ى﴾ يَٓا اَللّٰهُ ❁ يَا ﴿هَاد۪ى﴾ يَٓا اَللّٰهُ ❁ يَا ﴿قَادِرُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿سَاتِرُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿قَهَّارُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿جَبَّارُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿غَفَّارُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿فَتَّاحُ﴾ يَٓا اَللّٰهُ\n" +
                "\n" +
                "يَا رَبَّ السَّمٰوَاتِ وَ الْاَرْضِ يَا ذَا الْجَلَالِ وَ اْلاِكْرَامِ ❁ اَسْئَلُكَ بِحَقِّ هٰذِهِ الْاَسْمَٓاءِ كُلِّهَٓا اَنْ تُصَلِّىَ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَ عَلٰٓى اٰلِ مُحَمَّدٍ وَ ارْحَمْ مُحَمَّدًا كَمَا صَلَّيْتَ وَ سَلَّمْتَ وَ بَارَكْتَ وَ رَحِمْتَ وَ تَرَحَّمْتَ عَلٰٓى اِبرَاه۪يمَ وَ عَلٰٓى اٰلِ اِبْرَاه۪يمَ فِى الْعَالَم۪ينَ رَبَّنَٓا اِنَّكَ حَم۪يدٌ مَج۪يدٌ بِرَحْمَتِكَ يَٓا اَرْحَمَ الرَّاحِم۪ينَ وَ الْحَمْدُ لِلّٰهِ رَبِّ الْعَالَم۪ينَ\n" +
                "\n" +
                "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّح۪يمِ\n" +
                "\n" +
                "لَا يَسْتَو۪ٓى اَصْحَابُ النَّارِ وَاَصْحَابُ الْجَنَّةِۜ اَصْحَابُ الْجَنَّةِ هُمُ الْفَٓائِزُونَ ﴿٠٢﴾\n" +
                "\n" +
                "لَوْ اَنْزَلْنَا هٰذَا الْقُرْاٰنَ عَلٰى جَبَلٍ لَرَاَيْتَهُ خَاشِعًا مُتَصَدِّعًا مِنْ خَشْيَةِ اللّٰهِۜ وَتِلْكَ الْاَمْثَالُ نَضْرِبُهَا لِلنَّاسِ لَعَلَّهُمْ يَتَفَكَّرُونَ ﴿١٢﴾\n" +
                "\n" +
                "هُوَ اللّٰهُ الَّذ۪ى لَٓا اِلٰهَ اِلَّا هُوَۚ عَالِمُ الْغَيْبِ وَالشَّهَادَةِۚ هُوَ الرَّحْمٰنُ الرَّح۪يمُ ﴿٢٢﴾\n" +
                "\n" +
                "هُوَ اللّٰهُ الَّذ۪ى لَٓا اِلٰهَ اِلَّا هُوَۚ اَلْمَلِكُ الْقُدُّوسُ السَّلَامُ الْمُؤْمِنُ الْمُهَيْمِنُ الْعَز۪يزُ الْجَبَّارُ الْمُتَكَبِّرُۜ سُبْحَانَ اللّٰهِ عَمَّا يُشْرِكُونَ ﴿٣٢﴾\n" +
                "\n" +
                "هُوَ اللّٰهُ الْخَالِقُ الْبَارِىُٔ الْمُصَوِّرُ لَهُ الْاَسْمَٓاءُ الْحُسْنٰىۜ يُسَبِّحُ لَهُ مَا فِى السَّمٰوَاتِ وَالْاَرْضِۚ وَهُوَ الْعَز۪يزُ الْحَك۪يمُ ﴿٤٢﴾";

        TextView ttv = binding.tesbihatTextview;
        ttv.setText(tesbihatStr);
        ttv.setMovementMethod(new ScrollingMovementMethod());




    }
    public void aksamturkce () {


        String tesbihatStr = "Akşam namazının farzı kılınıp selâm verildikten sonra, “Allâhümme ente’s-selâmü ve minke’s-selâm, tebârekte yâ ẕe’l-celâli ve’likrâm” denilir ve aşağıdaki Salât-ı Münciye Duası, (yani Salâte’n-Tüncînâ) oku\u00ADnur: “Allahümme šalli alâ seyyidinâ Muḥammedin ve ʿalâ âli seyyidinâ Muḥammed. Šalâten tüncînâ bihâ min-cemîʿı’l-ehvâli ve’lâfât. (“ve’l-âfât” derken avuç içleri yere bakacak şekilde çevrilir, bitince de eski haline getirilir.) Ve takḍîlenâ bi-hâ cemîʿa’l-ḥâcât. Ve tüṭahhirunâ bi-hâ min-cemîʿı’s-seyyiât. Ve terfeʿunâ bi-hâ ʿındeke aʿle’d-derecât. Ve tübelliğunâ bi-hâ aḳša’l-ğâyât. Min-cemîʿı’lḫayrâti fi’l-ḥayâti ve baʿde’l-memât. Âmin yâ Mucîbe’d-deʿavâti ve’l-ḥamdü lillâhi Rabbi’lʿâlemîn. Âmin” denilir ve eller yüze sürülüp indirilir.\n" +
                "Akşam namazının son sünneti kılınır.Selam verdikten sonra,\n" +
                "\n" +
                "“Âmennâ bi-ennehü” (1 defa söylenir.) “Lâ ilâhe illâllahü, vaḥdehû, lâ şerîke leh, lehü’l-mülkü ve lehü’l-ḥamdü, yuḥyî ve yümît, bi-yedihi’l-ḫayr ve hüve ʿalâ külli şey’in ḳadîr.” (9 defa söy\u00ADlenir) dedikten sonra onuncuda “Lâ ilâhe illâllahü, vaḥdehû, lâ şerîke leh, lehü’l-mülkü ve lehü’l-ḥamdü, yuḥyî ve yümît ve hüve ḥayyün lâ yemût, bi-yedihi’l-ḫayr ve hüve ʿalâ külli şey’in ḳadîr ve ileyhi’l-mašîr.”İstiâze Duası’yla tesbihâta devam edilir (dua eder\u00ADken eller kaldırılır ve avuç içleri yere bakacak şekilde tutulur):\n" +
                "\n" +
                "“Allahümme ecirnâ mine’n-nâr. (3, 5 veya 7 de\u00ADfa tekrar edilir.)\n" +
                "Allahümme ecirnâ min-külli nâr.\n" +
                "Allahümme ecirnâ min-fitneti’d-dîniyyeti ve’d-dünyeviyyeh.\n" +
                "Allahümme ecirnâ min-fitneti âḫiri’z-zemân.\n" +
                "Allahümme ecirnâ min-fitneti Mesîḥı’dDeccâli ve’s-Süfyân.\n" +
                "Allahümme ecirnâ mine’ḍ-ḍalâlâti ve’l-bidʿıyyâti ve’l-beliyyât.\n" +
                "Allahümme ecirnâ min-şerri’n-nefsi’lemmâreh.\n" +
                "Allahümme ecirnâ min-şürûri’n-nüfûsi’lemmârâti’l-firʿavniyyeh.\n" +
                "Allahümme ecirnâ min-şerri’n-nisâ.\n" +
                "Allahümme ecirnâ min-belâi’n-nisâ.\n" +
                "Allahümme ecirnâ min-fitneti’n-nisâ.\n" +
                "Allahümme ecirnâ min-ʿaẕâbi’l-ḳabr.\n" +
                "Allahümme ecirnâ min-ʿaẕâbi yevmi’lḳıyâmeh.\n" +
                "Allahümme ecirnâ min-ʿaẕâbi Cehennem.\n" +
                "Allahümme ecirnâ min-ʿaẕâbi ḳahrik.\n" +
                "Allahümme ecirnâ min-ʿnâri ḳahrik.\n" +
                "Allahümme ecirnâ min-ʿaẕâbi’l-ḳabri ve’nnîrân.\n" +
                "Allahümme ecirnâ mine’r-riyâi ve’ssümʿati ve’l-ʿucubi ve’l-faḫr.\n" +
                "Allahümme ecirnâmin-tecâvüzi’l-mülḥıdîn.\n" +
                "Allahümme ecirnâ min-şerri’l-münâfiḳîn.\n" +
                "Allahümme ecirnâ min-fitneti’l-fâsıḳîn.\n" +
                "Allahümme ecirnâ ve ecir vâlideynâ ve ṭalebete Resâili’n-Nûri’š- šâdiḳîne fî-ḫıdmeti’l-Ḳur’âni ve’l-îmân. Ve aḥbâbene’l-mü’minîne’l-muḫlišîne ve aḳrabâenâ ve ecdâdenâ mine’n-nâr. (Bundan son\u00ADra avuç içleri yukarı çevrilir.) Bi-ʿafvike yâ Mücîr, bi-faḍlike yâ Ğaffâr. Allâhümme’d-ḫılne’l-Cennete meʿâ’l-ebrâr. Allâhümme’d-ḫılne’l-Cennete meʿâ’l-ebrâr. Allâhümme’d-ḫılnâ ve edḫı’l-Üstâẕenâ Saʿîde’n-Nursî (radiyallâhu ʿanh) ve vâlideynâ ve ṭalebete Resâili’n-Nûri’š-šâdıḳîne ve iḫvânenâ ve eḫavâtinâ ve aḳrabâenâ ve ecdâdenâ ve aḥbâbene’l-mü’minîne’l-muḫlišîne fî-ḫıdmeti’l-îmâni ve’l-Ḳur’ân. El-Cennete meʿa’l-ebrâr bi-şefâati nebiyyike’l-muḫtar ve âlihi’l-eṭhâr ve ešḥâbihi’l-aḫyâr ve sellim mâ dâme’l-leylü ve’n-nehâr. Âmîn, ve’l-ḥamdü lillâhi Rabbi’lʿÂlemîn” denilir ve eller yüze sü\u00ADrülür.\n" +
                "\n" +
                "Sonra namaz tesbihatına şu dua ile devam edi\u00ADlir: “Sübḥânallahi ve’l-ḥamdü lillâhi ve lâ ilâhe illâllahü vallahü ekber ve lâ ḥavle ve lâ ḳuvvete illâ billâhi’l-ʿaliyyi’l-ʿaẓîm.”Ve Âyete’l-Kürsî okunur: “Bismillâhirraḥmânirraḥîm. Allahü lâ ilâhe illâ hüve’l-ḥayyü’l-ḳayyûm. Lâ te’ḫuẕühû sinetün ve lâ nevm. Lehû mâ fi’s-semâvâti vemâ fi’l-arḍı. Menẕelleẕî yeşfeʿu ʿındehû illâ bi-iẕnih. Yâʿlemü mâ beyne eydîhim ve mâ ḫalfehüm ve lâ yuḥîṭûne bi-şey’in min-ʿılmihî illâ bi-mâ şâe. Vesiʿa kürsiyyühü’s-semâvâti ve’l-arḍi. Velâ yeûdü\u00AD hû ḥıfẓuhümâ ve hüve’l-ʿaliyyü’l-ʿaẓîm.”\n" +
                "\n" +
                "Şu tesbih sözleri tekrarlanır: “Ve hüve’l-‘aliyyü’l-‘azîmu zü’l-celâli sübḥâ\u00ADnellâh” (1 defa), “Sübḥânallâh” (33 defa) “Sübḥâne’l-bâḳî dâimeni’l-ḥamdülillâh” (1 defa), “Elḥamdülillâh” (33 defa) “Rabbi’l-‘âlemîne te‘âlâ şânühü Allâhü Ekber” (1 defa), “Allâhü Ekber” (33 defa) “Lâ ilâhe illallâhü, vaḥdehû, lâ şerîke leh, lehü’l-mülkü ve lehü’l-ḥamdü, yuḥyî ve yümît ve hüve ḥayyün lâyemût, bi-yedihi’l-ḫayru ve hüve ʿalâ külli şey’in ḳadîrun ve ileyhi’l-mašîr” denilir ve na\u00ADmaz duası yapılır.\n" +
                "\n" +
                "Duâdan sonra tesbihata şöyle devam edilir: “Faʿlem ennehu” (1 defa) “Lâ ilâhe illâllah” (33 defa) denilir ve ardından; “Muḥammedü’r-resûlüllâhi šallâllahü teʿâlâ ʿaleyhi vesellem.” (1 defa)  “Bismillâhirraḥmânirraḥîm. İnnallâhe ve melâiketehû yüšallûne ʿale’nnebiy. Yâ eyyühelleẕîne âmenû šallû ʿaleyhi ve sellimû teslîmâ. Lebbeyk.\n" +
                "\n" +
                "Allahümme šalli ʿalâ seyyidinâ Muḥammedin ve ʿalâ âli seyyidinâ Muḥammed. Biʿadedi külli dâin ve devâin ve bârik ve sellim ʿaleyhi ve ʿaleyhim kesîrâ. Allahümme šalli ʿalâ seyyidinâ Muḥammedin ve ʿalâ âli seyyidinâ Muḥammed. Biʿadedi külli dâin ve devâin ve bârik ve sellim ʿaleyhi ve ʿaleyhim kesîrâ. Allahümme šalli ʿalâ seyyidinâ Muḥammedin ve ʿalâ âli seyyidinâ Muḥammed. Biʿadedi külli dâin ve devâin ve bârik ve sellim ʿaleyhi ve ʿaleyhim kesîran kesîrâ. Šalli ve sellim yâ Rabbi ʿalâ ḥabîbike Muḥammedin ve ʿalâ cemîʿı’l-enbiyâi ve’l-mürselîn ve ʿalâ âli küllin ve šahbi küllin ecmaʿîn. Âmin ve’l-ḥamdü lillâhi Rabbi’l-ʿâlemîn.\n" +
                "\n" +
                "Elfü elfi šalâtin ve elfü elfi selâmin ʿaleyke yâ Resûlallah.\n" +
                "Elfü elfi šalâtin ve elfü elfi selâmin ʿaleyke yâ Ḥabîballah.\n" +
                "Elfü elfi šalâtin ve elfü elfi selâmin ʿaleyke yâ emîne vaḥyillâh.\n" +
                "Allahümme šalli ve sellim ve bârik ʿalâ seyyidinâ Muḥammedin ve ʿalâ âlihî ve ašḥâbihî bi-ʿadedi evraḳı’l-eşcâr ve emvâci’lbiḥâr ve ḳaṭarâti’l-emṭâr vağfir lenâ verḥamnâ velṭuf binâ ve bi-Üstâẕinâ Saʿîdi’n-Nursî (radiyallahu ʿanh) ve vâlideynâ ve bi-ṭalebeti Resâili’n-Nuri’š-šadıḳîne yâ ilâhenâ bi-külli šalâtin minhâ eşhedü en lâilâhe illâllah ve eşhedü enne Muḥammeden Resûlüllâhi Šallallâhü ʿAleyhi Ve sellem.”Aşağıdaki Duâ-i Tercümân-ı İsm-i A’zâm ile tes\u00ADbi\u00ADhata devam edilir:\n" +
                "\n" +
                "Yâ Cemîlu yâ Allah,\n" +
                "Yâ Ḳarîbu yâ Allah\n" +
                "Yâ Mucîbu yâ Allah,\n" +
                "Yâ Ḥabîbu yâ Allah\n" +
                "Yâ Raûfu yâ Allah,\n" +
                "Yâ ʿAṭûfu yâ Allah\n" +
                "Yâ Maʿrûfu yâ Allah,\n" +
                "Yâ Lâṭîfu yâ Allah\n" +
                "Yâ ʿAẓîmu yâ Allah,\n" +
                "Yâ Ḥannânu yâ Allah\n" +
                "Yâ Mennânu yâ Allah,\n" +
                "Yâ Deyyânu yâ Allah\n" +
                "Yâ Sübḥânu yâ Allah,\n" +
                "Yâ Emânu yâ Allah\n" +
                "Yâ Bürhânu yâ Allah,\n" +
                "Yâ Sulṭânu yâ Allah\n" +
                "Yâ Müsteʿânu yâ Allah,\n" +
                "Yâ Muḥsinu yâ Allah\n" +
                "Yâ Müteʿâlu yâ Allah,\n" +
                "Yâ Raḥmânu yâ Allah\n" +
                "Yâ Raḥîmu yâ Allah,\n" +
                "Yâ Kerîmu yâ Allah\n" +
                "Yâ Mecîdu yâ Allah,\n" +
                "Yâ Ferdu yâ Allah\n" +
                "Yâ Vitru yâ Allah,\n" +
                "Yâ Eḥadu yâ Allah\n" +
                "Yâ Šamedu yâ Allah,\n" +
                "Yâ Maḥmûdu yâ Allah\n" +
                "Yâ Šâdıḳa’l-Vaʿdi yâ Allah,\n" +
                "Yâ ʿAliyyu yâ Allah\n" +
                "Yâ Ğaniyyu yâ Allah,\n" +
                "Yâ Şâfî yâ Allah\n" +
                "Yâ Kâfî yâ Allah,\n" +
                "Yâ Muʿâfî yâ Allah\n" +
                "Yâ Bâḳî yâ Allah,\n" +
                "Yâ Hâdî yâ Allah\n" +
                "Yâ Ḳàdiru yâ Allah,\n" +
                "Yâ Sâtiru yâ Allah\n" +
                "Yâ Ḳahhâru yâ Allah,\n" +
                "Yâ Cebbâru yâ Allah\n" +
                "Yâ Ğaffâru yâ Allah,\n" +
                "Yâ Fettâḥu yâ Allah\n" +
                "Avuçlar yukarı gelecek şekilde eller açılır:”Yâ Rabbe’s-semâvâti ve’l-arḍı, yâ ẕe’l-celâli ve’l-ikrâm. Es’elüke bi-ḥaḳḳı hâẕihi’l-esmâi küllihâ entüšalliye ʿalâ seyyidinâ Muḥammedin ve ʿalâ âli Muḥammedin verḥam Muḥammeden kemâ šalleyte ve sellemte ve bârekte ve raḥimte ve teraḥḥamte ʿalâ İbrâhîme ve ʿalâ âli İbrâhîme fi’l-ʿâlemîn, Rabbenâ inneke ḥamîdü’m-mecîd, bi-raḥmetike yâ erḥamerrâḥimîn, ve’l-ḥamdülillâhi Rabbi’l-ʿâlemîn.\n" +
                "Haşir Sûresi’nin 20-24. âyetleri (Lâyestevî) o\u00ADku\u00ADna\u00ADrak tesbihat bitirilir.\n" +
                "\n" +
                "“Bismillâhirraḥmânirraḥîm Lâ yestevî ašḥâbü’n-nâri ve ašḥâbü’l-Cenneh. Ašḥâbü’l-Cenneti hümü’l-fâizûn. . Lev enzelnâ hâẕe’l-Ḳur’âne ʿalâ cebelin leraeytehû ḫâşiʿan mütešaddian min-ḫaşyetillâh. Ve tilke’l-emsâlü naḍribühâ linnâsi leʿallehüm yetefekkerûn. . Hüvallahü’l-leẕî lâ ilâhe illâ hû. ʿÂlimü’l-ğaybi ve’ş-şehâdeti. Hüve’rRaḥmânü’r-Raḥîm. . Hüvallâhü’l-leẕî lâ ilâhe illâ hû. El-Melikü’l-Ḳuddûsü’s-Selâmü’l-Mü’\u00AD minü’l-Müheyminü’l-ʿAzîzü’l-Cebbârü’l-Mü\u00AD tekebbir. Sübḥânellâhi ʿammâ yüşrikûn. . Hüvallâhü’l-Ḫàliḳu’l-Bâriü’l-Müšavviru lehü’lEsmâü’l-\u00ADḤüsnâ. Yüsebbiḥu lehû mâ fi’ssemâvâti ve’l-arḍi. Ve hüve’l-ʿAzîzü’l-Ḥakîm.”";

        TextView ttv = binding.tesbihatTextview;
        ttv.setText(tesbihatStr);
        ttv.setMovementMethod(new ScrollingMovementMethod());




    }
    public void yatsiArapca () {


        String tesbihatStr = "Yatsı namazının farzının selamını verdikten sonra:\n" +
                "\n" +
                "اَللّٰهُمَّ اَنْتَ السَّلَامُ وَ مِنْكَ السَّلَامُ تَبَارَكْتَ يَا ذَا الْجَلَالِ وَ اْلاِكْرَامِ\n" +
                "\n" +
                "Dedikten sonra elleri yukarı kaldırıp\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَ عَلَٓى اٰلِ سَيِّدِنَا مُحَمَّدٍ صَلَاةً تُنْج۪ينَا بِهَا مِنْ جَم۪يعِ الْاَهْوَالِ وَ اْلاٰفَاتِ وَ تَقْض۪ى لَنَا بِهَا جَم۪يعَ الْحَاجَاتِ وَ تُطَهِّرُنَا بِهَا مِنْ جَم۪يعِ السَّيِّئَاتِ وَ تَرْفَعُنَا بِهَا عِنْدَكَ اَعْلَى الدَّرَجَاتِ وَ تُبَلِّغُنَا بِهَٓا اَقْصَى الْغَايَاتِ مِنْ جَم۪يعِ الْخَيْرَاتِ فِى الْحَيَاةِ وَ بَعْدَ الْمَمَاتِ اٰم۪ينَ يَا مُج۪يبَ الدَّعَوَاتِ وَالْحَمْدُ لِلّٰهِ رَبِّ الْعَالَم۪ينَ\n" +
                "\n" +
                "Son sünnet ve salat-ı vitr kılınıp dua okunur\n" +
                "\n" +
                "سُبْحَانَ اللّٰهِ وَالْحَمْدُ لِلّٰهِ وَلَا إِلَهَ إِلَّا اللَّهُ وَ اللّٰهُ اَكْبَرْ وَلَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللّٰهِ الْعَلِيِّ الْعَظِيمِْ\n" +
                "\n" +
                "Âyet-ül Kürsi okunur\n" +
                "\n" +
                "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّح۪يمِ\n" +
                "\n" +
                "اَللّٰهُ لَٓا اِلٰهَ اِلَّا هُوَۚ اَلْحَيُّ الْقَيُّومُۚ لَا تَأْخُذُهُ سِنَةٌ وَلَا نَوْمٌۜ لَهُ مَا فِي السَّمٰوَاتِ وَمَا فِي الْاَرْضِۜ مَنْ ذَا الَّذ۪ي يَشْفَعُ عِنْدَهُٓ اِلَّا بِاِذْنِه۪ۜ يَعْلَمُ مَا بَيْنَ اَيْد۪يهِمْ وَمَا خَلْفَهُمْۚ وَلَا يُح۪يطُونَ بِشَيْءٍ مِنْ عِلْمِه۪ٓ اِلَّا بِمَا شَٓاءَۚ وَسِعَ كُرْسِيُّهُ السَّمٰوَاتِ وَالْاَرْضَۚ وَلَا يَؤُ۫دُهُ حِفْظُهُمَاۚ وَهُوَ الْعَلِيُّ الْعَظ۪يمُ\n" +
                "\n" +
                "ardından 33 defa\n" +
                "\n" +
                "سُبْحَانَ اللّٰهِ\n" +
                "\n" +
                "33 defa\n" +
                "\n" +
                "اَلْحَمْدُ لِلّٰهِ\n" +
                "\n" +
                "33 defa\n" +
                "\n" +
                "اَللّٰهُ اَكْبَرُ\n" +
                "\n" +
                "Deyip namazın duası yapıldıktan sonra 1 defa\n" +
                "\n" +
                "فَاعْلَمْ اَنَّهُ\n" +
                "\n" +
                "33 defa\n" +
                "\n" +
                "لَٓا اِلٰهَ اِلَّا اللّٰهُ\n" +
                "\n" +
                "33.cüde\n" +
                "\n" +
                "مُحَمَّدٌ رَسُولُ اللّٰهِ صَلَّى اللّٰهُ تَعَالٰى عَلَيْهِ وَ سَلَّمَ\n" +
                "\n" +
                "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّح۪يمِ\n" +
                "\n" +
                "اِنَّ اللّٰهَ وَ مَلَٓئِكَتَهُ يُصَلُّونَ عَلَى النَّبِىِّ يَٓا اَيُّهَا الَّذ۪ينَ اٰمَنُوا صَلُّوا عَلَيْهِ وَ سَلِّمُوا تَسْل۪يمًا ❁ لَبَّيْكَ\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَعَلَٓى اٰلِ سَيِّدِنَا مُحَمَّدٍ بِعَدَدِ كُلِّ دَٓاءٍ وَدَوَٓاءٍ وَبَارِكْ وَسَلِّمْ عَلَيْهِ وَعَلَيْهِمْ كَث۪يرًا\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَعَلَٓى اٰلِ سَيِّدِنَا مُحَمَّدٍ بِعَدَدِ كُلِّ دَٓاءٍ وَدَوَٓاءٍ وَبَارِكْ وَسَلِّمْ عَلَيْهِ وَعَلَيْهِمْ كَث۪يرًا\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَعَلَٓى اٰلِ سَيِّدِنَا مُحَمَّدٍ بِعَدَدِ كُلِّ دَٓاءٍ وَدَوَٓاءٍ وَبَارِكْ وَسَلِّمْ عَلَيْهِ وَعَلَيْهِمْ كَث۪يرًا كَث۪يرًا\n" +
                "\n" +
                "صَلِّ وَ سَلِّمْ يَا رَبِّ عَلٰى حَب۪يبِكَ مُحَمَّدٍ وَ عَلٰى جَم۪يعِ الْاَنْبِيَٓاءِ وَ الْمُرْسَل۪ينَ وَ عَلَٓى اٰلِ كُلٍّ وَ صَحْبِ كُلٍّ اَجْمَع۪ينَ اٰم۪ينَ وَالْحَمْدُ لِلّٰهِ رَبِّ الْعَالَم۪ينَ\n" +
                "\n" +
                "اَلْفُ اَلْفِ صَلَاةٍ وَ اَلْفُ اَلْفِ سَلَامٍ عَلَيْكَ يَا رَسُولَ اللّٰهِ\n" +
                "\n" +
                "اَلْفُ اَلْفِ صَلَاةٍ وَ اَلْفُ اَلْفِ سَلَامٍ عَلَيْكَ يَا حَب۪يبَ اللّٰهِ\n" +
                "\n" +
                "اَلْفُ اَلْفِ صَلَاةٍ وَ اَلْفُ اَلْفِ سَلَامٍ عَلَيْكَ يَٓا اَم۪ينَ وَحْىِ اللّٰهِ\n" +
                "\n" +
                "اَللّٰهُمَّ صَلِّ وَ سَلِّمْ وَ بَارِكْ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَعَلَٓى اٰلِه۪ وَ اَصْحَابِه۪ بِعَدَدِ اَوْرَاقِ الْاَشْجَارِ وَ اَمْوَاجِ الْبِحَارِ وَ قَطَرَاتِ الْاَمْطَارِ وَ اغْفِرْلَنَا وَ ارْحَمْنَا وَ الْطُفْ بِنَا وَ بِاُسْتَادِنَا سَع۪يدِ النُّورْس۪ى رَضِىَ اللّٰهُ عَنْهُ وَ وَالِدَيْنَا وَ بِطَلَبَةِ رَسَٓائِلِ النُّورِ الصَّادِق۪ينَ يَٓا اِلٰهَنَا بِكُلِّ صَلَاةٍ مِنْهَٓا اَشْهَدُ اَنْ لَٓا اِلٰهَ اِلَّا اللّٰهُ وَ اَشْهَدُ اَنَّ مُحَمَّدًا رَسُولُ اللّٰهِ صَلَّى اللّٰهُ تَعَالٰى عَلَيْهِ وَ سَلَّمْ\n" +
                "\n" +
                "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّح۪يمِ\n" +
                "\n" +
                "يَا ﴿جَم۪يلُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿قَر۪يبُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مُج۪يبُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿حَب۪يبُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿رَؤُفُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿عَطُوفُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مَعْرُوفُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿لَط۪يفُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿عَظ۪يمُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿حَنَّانُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مَنَّانُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿دَيَّانُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿سُبْحَانُ﴾ يَٓا اَللّٰهُ ❁ يَٓا ﴿اَمَانُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿بُرْهَانُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿سُلْطَانُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مُسْتَعَانُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مُحْسِنُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مُتَعَالُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿رَحْمٰنُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿رَح۪يمُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿كَر۪يمُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مَج۪يدُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿فَرْدُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿وِتْرُ﴾ يَٓا اَللّٰهُ ❁ يَٓا ﴿اَحَدُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿صَمَدُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مَحْمُودُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿صَادِقَالْوَعْدِ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿عَلِىُّ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿غَنِىُّ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿شَاف۪ى﴾ يَٓا اَللّٰهُ ❁ يَا ﴿كَاف۪ى﴾ يَٓا اَللّٰهُ ❁ يَا ﴿مُعَاف۪ى﴾ يَٓا اَللّٰهُ ❁ يَا ﴿بَاق۪ى﴾ يَٓا اَللّٰهُ ❁ يَا ﴿هَاد۪ى﴾ يَٓا اَللّٰهُ ❁ يَا ﴿قَادِرُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿سَاتِرُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿قَهَّارُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿جَبَّارُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿غَفَّارُ﴾ يَٓا اَللّٰهُ ❁ يَا ﴿فَتَّاحُ﴾ يَٓا اَللّٰهُ\n" +
                "\n" +
                "يَا رَبَّ السَّمٰوَاتِ وَ الْاَرْضِ يَا ذَا الْجَلَالِ وَ اْلاِكْرَامِ ❁ اَسْئَلُكَ بِحَقِّ هٰذِهِ الْاَسْمَٓاءِ كُلِّهَٓا اَنْ تُصَلِّىَ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَ عَلٰٓى اٰلِ مُحَمَّدٍ وَ ارْحَمْ مُحَمَّدًا كَمَا صَلَّيْتَ وَ سَلَّمْتَ وَ بَارَكْتَ وَ رَحِمْتَ وَ تَرَحَّمْتَ عَلٰٓى اِبرَاه۪يمَ وَ عَلٰٓى اٰلِ اِبْرَاه۪يمَ فِى الْعَالَم۪ينَ رَبَّنَٓا اِنَّكَ حَم۪يدٌ مَج۪يدٌ بِرَحْمَتِكَ يَٓا اَرْحَمَ الرَّاحِم۪ينَ وَ الْحَمْدُ لِلّٰهِ رَبِّ الْعَالَم۪ينَ\n" +
                "\n" +
                "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّح۪يمِ\n" +
                "\n" +
                "اٰمَنَ الرَّسُولُ بِمَٓا اُنْزِلَ اِلَيْهِ مِنْ رَبِّه۪ وَالْمُؤْمِنُونَۜ كُلٌّ اٰمَنَ بِاللّٰهِ وَمَلٰٓئِكَتِه۪ وَكُتُبِه۪ وَرُسُلِه۪ۜ لَا نُفَرِّقُ بَيْنَ اَحَدٍ مِنْ رُسُلِه۪۠ وَقَالُوا سَمِعْنَا وَاَطَعْنَا غُفْرَانَكَ رَبَّنَا وَاِلَيْكَ الْمَص۪يرُ ﴿٥٨٢﴾\n" +
                "\n" +
                "لَا يُكَلِّفُ اللّٰهُ نَفْسًا اِلَّا وُسْعَهَاۜ لَهَا مَا كَسَبَتْ وَعَلَيْهَا مَااكْتَسَبَتْۜ رَبَّنَا لَا تُؤَاخِذْنَٓا اِنْ نَس۪ينَٓا اَوْ اَخْطَاْنَاۚ رَبَّنَا وَلَا تَحْمِلْ عَلَيْنَٓا اِصْرًا كَمَا حَمَلْتَهُ عَلَى الَّذ۪ينَ مِنْ قَبْلِنَاۚ رَبَّنَا وَلَا تُحَمِّلْنَا مَا لَا طَاقَةَ لَنَا بِه۪ۚ وَاعْفُ عَنَّا۠ وَاغْفِرْ لَنَا۠ وَارْحَمْنَا۠ اَنْتَ مَوْلٰينَا فَانْصُرْنَا عَلَى الْقَوْمِ الْكَافِر۪ينَ ﴿٦٨٢﴾";

        TextView ttv = binding.tesbihatTextview;
        ttv.setText(tesbihatStr);
        ttv.setMovementMethod(new ScrollingMovementMethod());




    }
    public void yatsiturkce () {


        String tesbihatStr = "Yatsı namazının farzı kılınıp selâm verildikten sonra, “Allâhümme ente’s-selâmü ve minke’s-selâm, tebârekte yâ ẕe’l-celâli ve’likrâm” denir. Daha sonra, “Allahümme šalli alâ seyyidinâ Muḥammedin ve ʿalâ âli seyyidinâ Muḥammed. Šalâten tüncînâ bihâ min-cemîʿı’l-ehvâli ve’l-âfât. (“ve’l- âfât” derken avuç içleri yere bakacak şekilde çevrilir, bitince de eski haline getirilir.) Ve takḍîlenâ bi-hâ cemîʿa’l-ḥâcât. Ve tüṭahhirunâ bi-hâ min-cemîʿı’s-seyyiât. Ve terfeʿunâ bi-hâ ʿindeke aʿle’d-derecât. Ve tübelliğunâ bi-hâ aḳša’l-ğâyât. Min-cemîʿi’l-ḫayrâti fi’l-ḥayâti ve baʿde’l-memât. Âmin yâ Mucîbe’d-deʿavâti ve’l-ḥamdü lillâhi Rabbi’l-ʿâlemîn. Âmin” denilir ve eller yüze sürülüp indirilir.\n" +
                "\n" +
                "Yatsı namazının son sünneti ve Vitir namazı kılındıktan sonra tesbihata şu dua ile devam edilir: “Sübḥânallahi velḥamdü lillâhi ve lâilâhe illâllahü vallahü ekber ve lâ ḥavle ve lâ ḳuvvete illâ billâhi’l-ʿaliyyi’l-ʿaẓîm.” Ve Âyete’l-Kürsî okunur: “Bismillâhirraḥmânirraḥîm. Allahü lâ ilâhe illâ hüve’l-ḥayyü’l-ḳayyûm. Lâ te’ḫuẕühû sinetün ve lâ nevm. Lehû mâ fi’s-semâvâti ve mâ fi’l-arḍi. Menẕelleẕî yeşfeʿu ʿindehû illâ bi-iẕnih. Yâʿlemü mâ beyne eydîhim ve mâ ḫalfehüm ve lâ yuḥîṭûne bişey’in min-ʿilmihî illâ bimâ şâe. Vesiʿa kürsiyyühü’s-semâvâti ve’l-arḍi. Ve lâ yeûdühû ḥıfẓuhümâ ve hüve’l-ʿaliyyü’l-ʿaẓîm.\n" +
                "\n" +
                "Şu tesbih sözleri tekrarlanır: “Ve hüve’l-‘aliyyü’l-‘az. îmu zü’l-celâli sübḥâ\u00AD nellâh” (1 defa), “Sübḥânallâh” (33 defa) “Sübḥâne’l-bâḳî dâimeni’l-ḥamdülillâh” (1 defa), “Elḥamdülillâh” (33 defa) “Rabbi’l-‘âlemîne te‘âlâ şânühü Allâhü Ekber” (1 defa), “Allâhü Ekber” (33 defa) “Lâ ilâhe illâllahü, vaḥdehû, lâ şerîke leh, lehü’l-mülkü ve lehü’l-ḥamdü, yuḥyî ve yümît ve hüve ḥayyün lâ yemût, bi-yedihi’l-ḫayru ve hüve ʿalâ külli şey’in ḳadîr ve ileyhi’l-mašîr” denilir ve namaz du\u00ADa\u00ADsı yapılır. Duâdan sonra tesbihata şöyle devam edilir: “Faʿlem ennehu” (1 defa) “Lâ ilâhe illâllah” (33 defa söylenir.) “Muḥammedü’r-resûlüllâhi šallâllahü teʿâlâ ʿaleyhi vesellem.” (1 defa) Tesbihâtın burasında Peygamberimize (a.s.m.) şöyle salât ve selâm edilir: “Bismillâhirraḥmânirraḥîm. İnnallâhe ve melâiketehû yüšallûne ʿale’nnebiy. Yâ eyyühelleẕîne âmenû šallû ʿaleyhi ve sellimû teslîmâ. Lebbeyk.\n" +
                "\n" +
                "Allahümme šalli ʿalâ seyyidinâ Muḥammedin ve ʿalâ âli seyyidinâ Muḥammed. Biʿadedi külli dâin ve devâin ve bârik ve sellim ʿaleyhi ve ʿaleyhim kesîrâ.\n" +
                "Allahümme šalli ʿalâ seyyidinâ Muḥammedin ve ʿalâ âli seyyidinâ Muḥammed. Biʿadedi külli dâin ve devâin ve bârik ve sellim ʿaleyhi ve ʿaleyhim kesîrâ.\n" +
                "Allahümme šalli ʿalâ seyyidinâ Muḥammedin ve ʿalâ âli seyyidinâ Muḥammed. Biʿadedi külli dâin ve devâin ve bârik ve sellim ʿaleyhi ve ʿaleyhim kesîran kesîrâ.\n" +
                "Šalli ve sellim yâ Rabbi ‘alâ ḥabîbike Muḥammedin ve ʿalâ cemîʿı’l-enbiyâi ve’l-mürselîn ve ʿalâ âli küllin ve šahbi küllin ecmaʿîn. Âmin ve’l-ḥamdü lillâhi Rabbi’l-âlemîn.\n" +
                "\n" +
                "Elfü elfi šalâtin ve elfü elfi selâmin ʿaleyke yâ Resûlallah.\n" +
                "Elfü elfi šalâtin ve elfü elfi selâmin ʿaleyke yâ Ḥabîballah.\n" +
                "Elfü elfi šalâtin ve elfü elfi selâmin ʿaleyke yâ emîne vaḥyillâh.\n" +
                "Allahümme šalli ve sellim ve bârik ʿalâ seyyidinâ Muḥammedin ve ʿalâ âlihî ve ašḥâbihî bi-ʿadedi evraḳı’l-eşcâr ve emvâci’lbiḥâr ve ḳaṭarâti’l-emṭâr vağfir lenâ verḥamnâ velṭuf binâ ve bi-Üstâẕinâ Saʿîdi’n-Nursî (raḍiyallahu ʿanh) ve vâlideynâ ve bi-ṭalebeti Resâili’n-Nuri’š-šadıḳîne yâ ilâhenâ bi-külli šalâtin minhâ eşhedü en-lâilâhe illâllah ve eşhedü enne Muḥammeden Resûlüllâhi Šallallâhü ʿAleyhi Vesellem.”\n" +
                "\n" +
                "İsm-i A’zâm duası okunur:\n" +
                "\n" +
                "“Bismillâhirraḥmânirraḥîm.\n" +
                "\n" +
                "Yâ Cemîlu yâ Allah,\n" +
                "Yâ Ḳarîbu yâ Allah\n" +
                "Yâ Mucîbu yâ Allah,\n" +
                "Yâ Ḥabîbu yâ Allah\n" +
                "Yâ Raûfu yâ Allah,\n" +
                "Yâ ʿAṭûfu yâ Allah\n" +
                "Yâ Maʿrûfu yâ Allah,\n" +
                "Yâ Lâṭîfu yâ Allah\n" +
                "Yâ ʿAẓîmu yâ Allah,\n" +
                "Yâ Ḥannânu yâ Allah\n" +
                "Yâ Mennânu yâ Allah,\n" +
                "Yâ Deyyânu yâ Allah\n" +
                "Yâ Sübḥânu yâ Allah,\n" +
                "Yâ Emânu yâ Allah\n" +
                "Yâ Bürhânu yâ Allah,\n" +
                "Yâ Sulṭânu yâ Allah\n" +
                "Yâ Müsteʿânu yâ Allah,\n" +
                "Yâ Muḥsinu yâ Allah\n" +
                "Yâ Müteʿâlu yâ Allah,\n" +
                "Yâ Raḥmânu yâ Allah\n" +
                "Yâ Raḥîmu yâ Allah,\n" +
                "Yâ Kerîmu yâ Allah\n" +
                "Yâ Mecîdu yâ Allah,\n" +
                "Yâ Ferdu yâ Allah\n" +
                "Yâ Vitru yâ Allah,\n" +
                "Yâ Eḥadu yâ Allah\n" +
                "Yâ Šamedu yâ Allah,\n" +
                "Yâ Maḥmûdu yâ Allah\n" +
                "Yâ Šâdıḳa’l-Vaʿdi yâ Allah,\n" +
                "Yâ ʿAliyyu yâ Allah\n" +
                "Yâ Ğaniyyu yâ Allah,\n" +
                "Yâ Şâfî yâ Allah\n" +
                "Yâ Kâfî yâ Allah,\n" +
                "Yâ Muʿâfî yâ Allah\n" +
                "Yâ Bâḳî yâ Allah,\n" +
                "Yâ Hâdî yâ Allah\n" +
                "Yâ Ḳàdiru yâ Allah,\n" +
                "Yâ Sâtiru yâ Allah\n" +
                "Yâ Ḳahhâru yâ Allah,\n" +
                "Yâ Cebbâru yâ Allah\n" +
                "Yâ Ğaffâru yâ Allah,\n" +
                "Yâ Fettâḥu yâ Allah”\n" +
                "Avuçlar yukarı gelecek şekilde eller açılır:\n" +
                "\n" +
                "Yâ Rabbe’s-semâvâti ve’l-arḍı, yâ ẕe’l-celâli ve’l-ikrâm. Es’elüke bi-ḥaḳḳı hâẕihi’l-esmâi küllihâ entüšalliye ʿalâ seyyidinâ Muḥammedin ve ʿalâ âli Muḥammedin verḥam Muḥammeden kemâ šalleyte ve sellemte ve bârekte ve raḥimte ve teraḥḥamte ʿalâ İbrâhîme ve ʿalâ âli İbrâhîme fi’l-ʿâlemîn, Rabbenâ inneke ḥamîdü’m-mecîd, bi-raḥmetike yâ erḥamerrâḥimîn, ve’l-ḥamdülillâhi Rabbi’l-ʿâlemîn.”\n" +
                "\n" +
                "Bakara Sûresi’nin285-286.âyetleri(Âmene’r-re\u00ADsûlü) okunur ve tesbihat sona erer.\n" +
                "\n" +
                "“Bismillâhirraḥmânirraḥîm Âmene’r-resûlü bimâ ünzile ileyhi mir’rabbihî ve’l-mü’minûn. Küllün âmene billâhi ve melâiketihî ve kütübihî ve rusulih. Lâ nûferriḳu beyne ehadim‑mirrusulih. Ve ḳàlû semiʿnâ ve aṭaʿnâ ğufrâneke rabbenâ ve ileyke’l-mašîr . Lâ yükellifullâhü nefsen illâ vüsʿahâ. Lehâ mâ kesebet ve ʿaleyhâ mektesebet. Rabbenâ lâ tüâḫiẕnâ in-nesînâ ev aḫṭaʿnâ. Rabbenâ velâ taḥmil ʿaleynâ išran kemâ ḥameltehû ʿalelleẕîne min-ḳablinâ. Rabbenâ velâ tüḥammilnâ mâ lâ ṭâḳate lenâ bih. Vaʿfü ʿannâ, vağfir le-nâ, verḥamnâ, ente mevlânâ fenšurnâ ʿale’l-ḳavmil kâfirîn.";

        TextView ttv = binding.tesbihatTextview;
        ttv.setText(tesbihatStr);
        ttv.setMovementMethod(new ScrollingMovementMethod());




    }



}