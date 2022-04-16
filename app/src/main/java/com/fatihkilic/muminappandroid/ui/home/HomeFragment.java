package com.fatihkilic.muminappandroid.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.applovin.mediation.ads.MaxAdView;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.fatihkilic.muminappandroid.Kutuphane.EzanDuasiActivity;
import com.fatihkilic.muminappandroid.Kutuphane.HtmDuasi;
import com.fatihkilic.muminappandroid.Kutuphane.KuraniKerimMainActivity;
import com.fatihkilic.muminappandroid.Kutuphane.NamazHocasiActivity;
import com.fatihkilic.muminappandroid.Kutuphane.TesbihatActivity;
import com.fatihkilic.muminappandroid.Kutuphane.VedaHutbesiActivity;
import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.FragmentHomeBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    private MaxAdView adView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        AppLovinSdk.getInstance( requireContext() ).setMediationProvider( "max" );
        AppLovinSdk.initializeSdk( requireContext(), new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {

                createBannerAd();




            }
        } );




        binding.kuranKerimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent kuraniKerimIntent = new Intent(getActivity(), KuraniKerimMainActivity.class);
                startActivity(kuraniKerimIntent);


            }
        });


        ImageButton tesbihatButton = binding.tesbihatButton;
        tesbihatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent tesbihatIntent = new Intent(getActivity(), TesbihatActivity.class);
                startActivity(tesbihatIntent);

            }
        });


        ImageButton ezanDuasiButton = binding.ezanDuasiButton;
        ezanDuasiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ezanDuasiIntent = new Intent(getActivity(), EzanDuasiActivity.class);
                startActivity(ezanDuasiIntent);

            }
        });


        ImageButton hatimDuasiButton = binding.hatimDuasiButton;
        hatimDuasiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent hatimDuasiIntent = new Intent(getActivity(), HtmDuasi.class);
                startActivity(hatimDuasiIntent);
            }
        });

        ImageButton vedaHutbesiButton = binding.vedaHutbesiButton;
        vedaHutbesiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent vedaHutbesiIntent = new Intent(getActivity(), VedaHutbesiActivity.class);
                startActivity(vedaHutbesiIntent);
            }
        });


        return root;
    }

    private void createBannerAd() {


        adView = new MaxAdView( "b06d01f284423f8f", requireActivity() );


        // Stretch to the width of the screen for banners to be fully functional
        int width = ViewGroup.LayoutParams.MATCH_PARENT;

        // Banner height on phones and tablets is 50 and 90, respectively
        int heightPx = getResources().getDimensionPixelSize( R.dimen.banner_height );

        adView.setLayoutParams( new FrameLayout.LayoutParams( width, heightPx ) );

        // Set background or background color for banners to be fully functional


        ViewGroup rootView = binding.adViewAppLovin1;
        rootView.addView( adView );

        // Load the ad
        adView.loadAd();



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}