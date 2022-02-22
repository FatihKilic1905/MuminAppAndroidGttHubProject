package com.fatihkilic.muminappandroid.ZikirMatik;

import static com.fatihkilic.muminappandroid.ZikirMatik.ZikirUsersActivity.zikirDocumentIDUsers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityZikirUserDetailEditBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityZikirUsersBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.onesignal.OneSignal;

public class ZikirUserDetailEditActivity extends AppCompatActivity {


    private ActivityZikirUserDetailEditBinding binding;

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    private static final String ONESIGNAL_APP_ID = "1966721c-a30c-4299-9d7a-38e084b98072";

    Integer zikirCount;
    Integer zikirCompleteCount;
    String zikirDocumentId;
    String userNameUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zikir_user_detail_edit);


        binding = ActivityZikirUserDetailEditBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);




        Intent userEditIntent = getIntent();

        String zikrCount = String.valueOf(userEditIntent.getIntExtra("zikirCountIntent",0));
        String zikirtotalCount = String.valueOf(userEditIntent.getIntExtra("zikirCompleteCountIntent",0));
        String inviteAnsver = userEditIntent.getStringExtra("inviteAnsver");


        if (inviteAnsver.equals("1")) {

            binding.userNameTextView.setText(userEditIntent.getStringExtra("inviteUserName"));
            binding.countTextView.setText(zikrCount + " / " + zikirtotalCount);
            binding.davetGonderbutton.setVisibility(View.INVISIBLE);


        } else if (inviteAnsver.equals("2")) {

            binding.userNameTextView.setText(userEditIntent.getStringExtra("inviteUserName"));
            binding.countTextView.setText("0" + " / " + "0");

            binding.zikirdeCikarButton.setVisibility(View.INVISIBLE);
            binding.zikirCountEditButton.setVisibility(View.INVISIBLE);


        }






        binding.geriDonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                onBackPressed();


            }
        });

        binding.zikirCountEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        binding.davetGonderbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        binding.zikirdeCikarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });







    }








}