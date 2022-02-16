package com.fatihkilic.muminappandroid.ZikirMatik;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityZikirAddBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityZikirMatikMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
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

public class ZikirMatikMainActivity extends AppCompatActivity {


    private ActivityZikirMatikMainBinding binding;

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    private static final String ONESIGNAL_APP_ID = "1966721c-a30c-4299-9d7a-38e084b98072";

    ArrayList<ModelZikirlerim> modelZikirlerimArrayList;

    AdapterZikirlerim adapterZikirlerim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zikir_matik_main);

        binding = ActivityZikirMatikMainBinding.inflate(getLayoutInflater());
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


        modelZikirlerimArrayList = new ArrayList<>();
        getZikirlerim();
        binding.myZikirRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterZikirlerim = new AdapterZikirlerim(modelZikirlerimArrayList);
        binding.myZikirRecyclerView.setAdapter(adapterZikirlerim);








        

        binding.zikirBaslatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent zikirBaslatIntent = new Intent(ZikirMatikMainActivity.this, ZikirAddActivity.class);
                startActivity(zikirBaslatIntent);



            }
        });









    }






    private void getZikirlerim() {

        String email = auth.getCurrentUser().getEmail();

        System.out.println("nerede " + email);

         firebaseFirestore.collection("ZikirMatik").document(email).collection("myZikir").whereEqualTo("zikirStatus", "1").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    System.out.println("Zikirlerim cekilemedi");

                }

                if (value != null) {


                    for (DocumentSnapshot snapshot : value.getDocuments()) {


                        Map<String, Object> data = snapshot.getData();

                        String zikirName = (String) data.get("zikirName");
                        Long zcc = (Long) data.get("ZikirCompleteCount");
                        Long zc = (Long) data.get("zikirCount");
                        String endDate = (String) data.get("endDate");

                        Integer zikirCompleteCount = zcc.intValue();
                        Integer zikirCount = zc.intValue();



                        ModelZikirlerim modelZikirlerim = new ModelZikirlerim(zikirName,zikirCompleteCount,zikirCount,endDate);
                        modelZikirlerimArrayList.add(modelZikirlerim);
                        System.out.println("nerede " + modelZikirlerimArrayList);


                    }


                    adapterZikirlerim.notifyDataSetChanged();

                }

            }
        });




    }

}