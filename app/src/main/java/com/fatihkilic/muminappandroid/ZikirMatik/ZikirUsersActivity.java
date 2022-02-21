package com.fatihkilic.muminappandroid.ZikirMatik;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityZikirMatikMainBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityZikirUsersBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.onesignal.OneSignal;

import java.util.ArrayList;
import java.util.Map;

public class ZikirUsersActivity extends AppCompatActivity {


    private ActivityZikirUsersBinding binding;

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    private static final String ONESIGNAL_APP_ID = "1966721c-a30c-4299-9d7a-38e084b98072";

    String zikirDocumentID;

    ArrayList<ModelZikirUsers> modelZikirUsersArrayList;
    AdapterZikirUsers adapterZikirUser;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zikir_users);


        binding = ActivityZikirUsersBinding.inflate(getLayoutInflater());
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

        Intent usersIntent = getIntent();
        zikirDocumentID = usersIntent.getStringExtra("myZikirDocumentId");

        modelZikirUsersArrayList = new ArrayList<>();
        getUsersMyzikir();
        binding.zikirUsersRecykerview.setLayoutManager(new LinearLayoutManager(this));
        adapterZikirUser = new AdapterZikirUsers(modelZikirUsersArrayList);
        binding.zikirUsersRecykerview.setAdapter(adapterZikirUser);






    }



    public void getUsersMyzikir () {


        firebaseFirestore.collection("ZikirMatik").document(auth.getCurrentUser().getEmail()).collection("myZikir").document(zikirDocumentID).collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    System.out.println("Zikirlerim cekilemedi");

                }

                if (value != null) {


                    for (DocumentSnapshot snapshot : value.getDocuments()) {


                        Map<String, Object> data = snapshot.getData();

                        String userName = (String) data.get("nickName");
                        Long zcc = (Long) data.get("zikirCompleteCount");
                        Long zc = (Long) data.get("zikirCount");


                        Integer zikirCompleteCount = zcc.intValue();
                        Integer zikirCount = zc.intValue();



                        ModelZikirUsers modelZikirUsers = new ModelZikirUsers(userName,zikirCompleteCount,zikirCount);
                        modelZikirUsersArrayList.add(modelZikirUsers);



                    }


                    adapterZikirUser.notifyDataSetChanged();

                }

            }
        });






    }





}