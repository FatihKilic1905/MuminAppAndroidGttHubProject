package com.fatihkilic.muminappandroid.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.fatihkilic.muminappandroid.Kutuphane.EzanDuasiActivity;
import com.fatihkilic.muminappandroid.Kutuphane.HtmDuasi;
import com.fatihkilic.muminappandroid.Kutuphane.NamazHocasiActivity;
import com.fatihkilic.muminappandroid.Kutuphane.TesbihatActivity;
import com.fatihkilic.muminappandroid.Kutuphane.VedaHutbesiActivity;
import com.fatihkilic.muminappandroid.databinding.FragmentHomeBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    private AdView mAdView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = binding.adView;
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        Button namazHocasiButton = binding.namazHocasiButton;
        namazHocasiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent namazHocasiIntent = new Intent(getActivity(), NamazHocasiActivity.class);
                startActivity(namazHocasiIntent);

            }
        });

        Button tesbihatButton = binding.tesbihatButton;
        tesbihatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent tesbihatIntent = new Intent(getActivity(), TesbihatActivity.class);
                startActivity(tesbihatIntent);

            }
        });

        Button ezanDuasiButton = binding.ezanDuasiButton;
        ezanDuasiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ezanDuasiIntent = new Intent(getActivity(), EzanDuasiActivity.class);
                startActivity(ezanDuasiIntent);

            }
        });


        Button hatimDuasiButton = binding.hatimDuasiButton;
        hatimDuasiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent hatimDuasiIntent = new Intent(getActivity(), HtmDuasi.class);
                startActivity(hatimDuasiIntent);
            }
        });

        Button vedaHutbesiButton = binding.vedaHutbesiButton;
        vedaHutbesiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent vedaHutbesiIntent = new Intent(getActivity(), VedaHutbesiActivity.class);
                startActivity(vedaHutbesiIntent);
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}