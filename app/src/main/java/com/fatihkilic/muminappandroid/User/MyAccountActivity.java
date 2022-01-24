package com.fatihkilic.muminappandroid.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fatihkilic.muminappandroid.MainActivity;
import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityMyAccountBinding;
import com.fatihkilic.muminappandroid.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MyAccountActivity extends AppCompatActivity {

    private ActivityMyAccountBinding binding;

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;

    String userName;
    String name;
    String surname;
    String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);


        binding = ActivityMyAccountBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        getProfil();


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


                auth.signOut();
                Intent logoutIntent = new Intent(MyAccountActivity.this, MainActivity.class);
                startActivity(logoutIntent);
                finish();


            }
        });




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

                        binding.userNameTextView.setText(userName);

                        StringBuilder nameSurname = new StringBuilder();
                        nameSurname.append(name);
                        nameSurname.append("");
                        nameSurname.append(surname);

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



}