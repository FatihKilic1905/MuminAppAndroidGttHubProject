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
import com.fatihkilic.muminappandroid.User.MyAccountActivity;
import com.fatihkilic.muminappandroid.User.SignInActivity;
import com.fatihkilic.muminappandroid.User.UsersActivity;
import com.fatihkilic.muminappandroid.ZekatMatik.ZekatMatikActivity;
import com.fatihkilic.muminappandroid.ZikirMatik.ZikirMatikMainActivity;
import com.fatihkilic.muminappandroid.databinding.FragmentNotificationsBinding;
import com.google.firebase.auth.FirebaseAuth;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    private FirebaseAuth auth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        auth = FirebaseAuth.getInstance();

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

        Button usersButton = binding.usersButton;
        usersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (auth.getCurrentUser() != null) {

                    Intent usersIntent = new Intent(getActivity(), MyAccountActivity.class);
                    startActivity(usersIntent);

                } else {

                    Intent usersIntent = new Intent(getActivity(), SignInActivity.class);
                    usersIntent.putExtra("ComePage", "UsersPage");
                    startActivity(usersIntent);


                }



            }
        });


        binding.zikirMatikButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (auth.getCurrentUser() != null) {

                    Intent zikirmatikIntent = new Intent(getActivity(), ZikirMatikMainActivity.class);
                    startActivity(zikirmatikIntent);

                } else {

                    Intent zikirmatikIntent = new Intent(getActivity(), SignInActivity.class);
                    zikirmatikIntent.putExtra("ComePage", "MenuPage");
                    startActivity(zikirmatikIntent);

                }

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