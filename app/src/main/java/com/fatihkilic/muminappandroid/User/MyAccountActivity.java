package com.fatihkilic.muminappandroid.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fatihkilic.muminappandroid.MainActivity;
import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityMyAccountBinding;
import com.fatihkilic.muminappandroid.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;

public class MyAccountActivity extends AppCompatActivity {

    private ActivityMyAccountBinding binding;

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;

    String userName;
    String name;
    String surname;
    String image;

    ArrayList<String> friendsRequestCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);


        binding = ActivityMyAccountBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        friendsRequestCount = new ArrayList<>();

        getProfil();
        getfriendsCount();


        Button friendsButton = binding.friendsButton;
        friendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent friendsIntent = new Intent(MyAccountActivity.this, FriendsOperationActivity.class);
                startActivity(friendsIntent);

            }
        });

        Button editProfileButton = binding.editProfilButtons;
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent editProfileIntent = new Intent(MyAccountActivity.this, MyAccountEditActivity.class);
                startActivity(editProfileIntent);

            }
        });


        Button logOutButton = binding.LogOutButton;
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder logoutAlert = new AlertDialog.Builder(MyAccountActivity.this);

                logoutAlert.setTitle("Uyarı");
                logoutAlert.setMessage("Oturumunuz kapatılacak!");
                logoutAlert.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        try {

                            auth.signOut();
                            Intent logoutIntent = new Intent(MyAccountActivity.this, MainActivity.class);
                            startActivity(logoutIntent);
                            finish();


                        } catch (Exception e) {


                            Toast.makeText(MyAccountActivity.this, "Çıkış yapılamadı! Lütfen tekrar deneyin",Toast.LENGTH_LONG).show();


                        }




                    }
                });

                logoutAlert.setNegativeButton("Vazgeç", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });


                logoutAlert.show();



            }
        });




    }


    @Override
    protected void onResume() {
        super.onResume();

        getfriendsCount();
    }

    public void getProfil () {

        String email = auth.getCurrentUser().getEmail();

        DocumentReference usdRef = firebaseFirestore.collection("User").document(email);
        usdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        userName = (String) document.get("userName");
                        name = (String) document.get("name");
                        surname = (String) document.get("surName");
                        image = (String) document.get("image");

                        binding.userNameTextView.setText(userName);

                        StringBuilder nameSurname = new StringBuilder();
                        nameSurname.append(name);
                        nameSurname.append("");
                        nameSurname.append(surname);
                        Picasso.get().load(image).into(binding.ppImageView);

                        binding.nameTextView.setText(nameSurname.toString());


                    } else {
                        System.out.println("Olumsuz");
                    }

                } else {

                    System.out.println("cekme başarısız");
                }

            }


        });





    }

    public void getfriendsCount () {



        firebaseFirestore.collection("User").document(auth.getCurrentUser().getEmail()).collection("FriendsRequest").addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    Toast.makeText(MyAccountActivity.this, "İnternet bağlantısında bir problem var", Toast.LENGTH_LONG).show();

                }

                if (value != null) {

                    friendsRequestCount.clear();

                    for (DocumentSnapshot snapshot : value.getDocuments()) {

                        Map<String, Object> data = snapshot.getData();

                        String emailList = (String) data.get("email");

                        friendsRequestCount.add(emailList);

                        if (friendsRequestCount.size() > 0) {


                            binding.friendsButton.setBackground(getResources().getDrawable(R.drawable.corner_4_radius_red));



                        }


                    }


                }


            }

        });




    }



}