package com.fatihkilic.muminappandroid.ZikirMatik;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fatihkilic.muminappandroid.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class ZikirMatikMainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zikir_matik_main);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();



    }






    private void getZikirlerim() {

        DocumentReference zikirlerimDocument = firebaseFirestore.collection("ZikirMatik").document(auth.getCurrentUser().getEmail());
        zikirlerimDocument.collection("myZikir").whereEqualTo("ZikirStatus", "1").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    System.out.println("Zikirlerim cekilemedi");

                }

                if (value != null) {


                    for (DocumentSnapshot snapshot : value.getDocuments()) {


                        Map<String, Object> data = snapshot.getData();





                    }

                }

            }
        });




    }

}