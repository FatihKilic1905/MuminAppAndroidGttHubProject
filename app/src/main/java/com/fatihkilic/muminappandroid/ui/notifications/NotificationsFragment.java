package com.fatihkilic.muminappandroid.ui.notifications;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;

    ArrayList<String> friendsRequestCount;
    ArrayList<String> zikirRequestCount;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        binding.zikrmatikNotButton.setVisibility(View.INVISIBLE);
        binding.usersNotButton.setVisibility(View.INVISIBLE);

        friendsRequestCount = new ArrayList<>();
        zikirRequestCount = new ArrayList<>();





        if (auth.getCurrentUser() != null) {



            System.out.println("kullanıcı  " + auth.getCurrentUser().getEmail());

           getZikirCount();
           getZikirCount();



        }



        ImageButton gecisyap = (ImageButton)root.findViewById(R.id.ayarlarButton);
        gecisyap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent konum = new Intent(getActivity(), AyarlarActivity.class);
                startActivity(konum);
            }
        });

        ImageButton zekatMatikButton = (ImageButton)root.findViewById(R.id.ZekatmatikButton);
        zekatMatikButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent zekatmatikIntent = new Intent(getActivity(), ZekatMatikActivity.class);
                startActivity(zekatmatikIntent);

            }
        });

        ImageButton KabeLiveButton = (ImageButton)root.findViewById(R.id.canliButton);
        KabeLiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent liveIntent = new Intent(getActivity(), LiveTvActivity.class);
                startActivity(liveIntent);

            }
        });

        ImageButton usersButton = binding.usersButton;
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
                    zikirmatikIntent.putExtra("ComePage", "MenuPageZikir");
                    startActivity(zikirmatikIntent);

                }

            }
        });






        return root;



    }

    @Override
    public void onResume() {
        super.onResume();


        if (auth.getCurrentUser() != null) {

            getfriendsCount();
            getZikirCount();



        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void getfriendsCount () {

        String currenUser = auth.getCurrentUser().getEmail();

        firebaseFirestore.collection("User").document(currenUser).collection("FriendsRequest").addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    Toast.makeText(getActivity(), "İnternet bağlantısında bir problem var", Toast.LENGTH_LONG).show();

                }

                if (value != null) {

                    friendsRequestCount.clear();

                    for (DocumentSnapshot snapshot : value.getDocuments()) {

                        Map<String, Object> data = snapshot.getData();

                        String emailList = (String) data.get("email");

                        friendsRequestCount.add(emailList);

                        if (friendsRequestCount.size() > 0) {

                            binding.usersNotButton.setVisibility(View.VISIBLE);
                            binding.usersNotButton.setText(String.valueOf(friendsRequestCount.size()));


                        }


                    }


                }


            }

        });




    }


    public void getZikirCount () {

        String currenUser = auth.getCurrentUser().getEmail();

        firebaseFirestore.collection("ZikirMatik").document(currenUser).collection("invitedZikir").whereEqualTo("inviteStatus","0").addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    Toast.makeText(getActivity(), "İnternet bağlantısında bir problem var", Toast.LENGTH_LONG).show();

                }


                if (value != null) {

                    zikirRequestCount.clear();

                    for (DocumentSnapshot snapshot : value.getDocuments()) {

                        Map<String, Object> data = snapshot.getData();

                        String emailList = (String) data.get("email");

                        zikirRequestCount.add(emailList);

                        if (zikirRequestCount.size() > 0) {

                            binding.zikrmatikNotButton.setVisibility(View.VISIBLE);
                            binding.zikrmatikNotButton.setText(String.valueOf(zikirRequestCount.size()));


                        }


                    }


                }


            }

        });




    }

    public void gecisyap() {





    }


}