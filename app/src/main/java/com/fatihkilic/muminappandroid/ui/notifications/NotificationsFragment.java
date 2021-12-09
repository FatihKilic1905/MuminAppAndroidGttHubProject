package com.fatihkilic.muminappandroid.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.fatihkilic.muminappandroid.Ayarlar.AyarlarActivity;
import com.fatihkilic.muminappandroid.LiveTvActivity;
import com.fatihkilic.muminappandroid.Ulkeler.KonumActivity;
import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.ZekatMatik.ZekatMatikActivity;
import com.fatihkilic.muminappandroid.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button gecisyap = (Button)root.findViewById(R.id.ayarlarButton);
        gecisyap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent konum = new Intent(getActivity(), AyarlarActivity.class);
                startActivity(konum);
            }
        });

        Button zekatMatikButton = (Button)root.findViewById(R.id.ZekatmatikButton);
        zekatMatikButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent zekatmatikIntent = new Intent(getActivity(), ZekatMatikActivity.class);
                startActivity(zekatmatikIntent);

            }
        });

        Button KabeLiveButton = (Button)root.findViewById(R.id.canliButton);
        KabeLiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent liveIntent = new Intent(getActivity(), LiveTvActivity.class);
                startActivity(liveIntent);

            }
        });







        return root;



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void gecisyap() {





    }


}