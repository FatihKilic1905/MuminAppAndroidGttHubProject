package com.fatihkilic.muminappandroid.ZikirMatik;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityKisilerBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityZikirAddBinding;
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

public class KisilerActivity extends AppCompatActivity {


    private ActivityKisilerBinding binding;

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;


    String currentUser;
    String myUserName;

    private static final String ONESIGNAL_APP_ID = "1966721c-a30c-4299-9d7a-38e084b98072";

    ArrayList<ModelKisiler> modelKisilerArrayList;
    AdapterKisiler adapterKisiler;

    static ArrayList<String> kisiEkleArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kisiler);


        binding = ActivityKisilerBinding.inflate(getLayoutInflater());
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

        currentUser = auth.getCurrentUser().getEmail();

        getFriends();

        modelKisilerArrayList = new ArrayList<>();
        kisiEkleArrayList = new ArrayList<>();


        binding.kisilerRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapterKisiler = new AdapterKisiler(modelKisilerArrayList);
        binding.kisilerRecyclerview.setAdapter(adapterKisiler);


        binding.secimiTamamlaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent secimiTamamlaIntent = new Intent(KisilerActivity.this, ZikirAddActivity.class);
                startActivity(secimiTamamlaIntent);


            }
        });


        binding.arkadasEkleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




            }
        });





    }



    public void getFriends () {


        firebaseFirestore.collection("User").document(currentUser).collection("Friends").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {


                if (error != null) {


                    Toast.makeText(KisilerActivity.this, "İnternet bağlantısında bir problem var",Toast.LENGTH_LONG).show();


                }

                if (value != null) {


                    for (DocumentSnapshot snapshot : value.getDocuments()) {


                        Map<String, Object> data = snapshot.getData();


                        String friendsUserName = (String) data.get("userName");
                        String friendsName = (String) data.get("name");
                        String friendsSurName = (String) data.get("surName");
                        String friendsImage = (String) data.get("image");


                        ModelKisiler modelKisiler = new ModelKisiler(friendsUserName,friendsName,friendsSurName,friendsImage);
                        modelKisilerArrayList.add(modelKisiler);




                    }

                    adapterKisiler.notifyDataSetChanged();

                }



            }
        });



    }











}