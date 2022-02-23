package com.fatihkilic.muminappandroid.ZikirMatik;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityZikirDavetDetailBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityZikirMatikMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.onesignal.OneSignal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ZikirDavetDetailActivity extends AppCompatActivity {


    private ActivityZikirDavetDetailBinding binding;

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    private static final String ONESIGNAL_APP_ID = "1966721c-a30c-4299-9d7a-38e084b98072";

    String zikirDavetDocumentID;
    String davetEmeilString;

    ArrayList<Integer> zikirTotalCountArrayList;
    Integer zikirTotalcount;
    Integer zikirCountDavet;
    String userName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zikir_davet_detail);

        binding = ActivityZikirDavetDetailBinding.inflate(getLayoutInflater());
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

        Intent davetIntent = getIntent();
        zikirDavetDocumentID = davetIntent.getStringExtra("ZikirDavetDocumentId");

        zikirTotalCountArrayList = new ArrayList<>();

        getDavet();
        getProfil();

        binding.zikirSayisiBackground.setVisibility(View.INVISIBLE);
        binding.emptyBackground.setVisibility(View.INVISIBLE);


        binding.kabulEtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseFirestore.collection("ZikirMatik").document(davetEmeilString).collection("myZikir").document(zikirDavetDocumentID).collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {

                            System.out.println("Zikirlerim cekilemedi");

                        }

                        if (value != null) {


                            for (DocumentSnapshot snapshot : value.getDocuments()) {


                                Map<String, Object> data = snapshot.getData();


                                Long zc = (Long) data.get("zikirCount");

                                Integer zikirCount = zc.intValue();

                                zikirTotalCountArrayList.add(zikirCount);

                                System.out.println("count " + zikirTotalCountArrayList);


                            }

                            Integer zikirTotalCountInteger = 0;

                            for (int TC : zikirTotalCountArrayList) {


                                zikirTotalCountInteger = zikirTotalCountInteger + TC;


                            }


                            zikirTotalcount = zikirTotalCountInteger;

                            System.out.println("count " + zikirTotalCountInteger);

                            System.out.println("count " + zikirTotalcount);

                            System.out.println("count " + zikirTotalCountArrayList);

                            binding.kucukTextView.setText("0");
                            binding.buyukTextView.setText(String.valueOf(zikirCountDavet - zikirTotalcount));


                        }

                    }
                });


                binding.zikirSayisiBackground.setVisibility(View.VISIBLE);
                binding.emptyBackground.setVisibility(View.VISIBLE);

            }
        });


        binding.KabulEtOnayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Integer zikirCountBuyuk = Integer.parseInt(binding.buyukTextView.getText().toString());

                Integer editInteger = Integer.parseInt(binding.manuelzikirEditText.getText().toString());

                if (editInteger > zikirCountBuyuk) {


                    Toast.makeText(ZikirDavetDetailActivity.this, "Çekeceğiniz zikir sayısı " + zikirCountBuyuk + " adetten fazla olamaz.", Toast.LENGTH_LONG).show();

                } else {


                    HashMap<String, Object> invitemydata = new HashMap<>();

                    invitemydata.put("beginDate", FieldValue.serverTimestamp());

                    invitemydata.put("inviteStatus", "1");
                    invitemydata.put("zikirMyCount", editInteger);


                    firebaseFirestore.collection("ZikirMatik").document(auth.getCurrentUser().getEmail()).collection("invitedZikir").document(zikirDavetDocumentID).update(invitemydata).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(@NonNull Void unused) {

                            HashMap<String, Object> inviteownerData = new HashMap<>();

                            inviteownerData.put("zikirCompleteCount", 0);
                            inviteownerData.put("inviteAnsver", "1");
                            inviteownerData.put("nickName", userName);
                            inviteownerData.put("zikirCount", editInteger);


                            firebaseFirestore.collection("ZikirMatik").document(davetEmeilString).collection("myZikir").document(zikirDavetDocumentID).collection("Users").document(auth.getCurrentUser().getEmail()).update(inviteownerData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(@NonNull Void unused) {


                                    Toast.makeText(ZikirDavetDetailActivity.this, "Katılımızını için teşekkür ediyoruz. İştirak sayfasından zikriniz takipedebilirsiniz.", Toast.LENGTH_LONG).show();
                                    Intent kabulIntent = new Intent(ZikirDavetDetailActivity.this, ZikirMatikMainActivity.class);
                                    startActivity(kabulIntent);

                                }

                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(ZikirDavetDetailActivity.this, "Cevap verilemedi. Lütfen tekrar deneyin.", Toast.LENGTH_LONG).show();

                                }

                            });


                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(ZikirDavetDetailActivity.this, "Cevap verilemedi. Lütfen tekrar deneyin.", Toast.LENGTH_LONG).show();

                        }

                    });


                }


            }





        });

        binding.vazgecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                binding.emptyBackground.setVisibility(View.INVISIBLE);
                binding.zikirSayisiBackground.setVisibility(View.INVISIBLE);


            }
        });


        binding.reddetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                HashMap<String, Object> inviteDeclineData = new HashMap<>();

                inviteDeclineData.put("inviteAnsver", "2");
                inviteDeclineData.put("nickName", userName);



                firebaseFirestore.collection("ZikirMatik").document(davetEmeilString).collection("myZikir").document(zikirDavetDocumentID).collection("Users").document(auth.getCurrentUser().getEmail()).update(inviteDeclineData).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(@NonNull Void unused) {



                        firebaseFirestore.collection("ZikirMatik").document(auth.getCurrentUser().getEmail()).collection("invitedZikir").document(zikirDavetDocumentID).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(@NonNull Void unused) {

                                Intent kabulIntent = new Intent(ZikirDavetDetailActivity.this, ZikirMatikMainActivity.class);
                                startActivity(kabulIntent);


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(ZikirDavetDetailActivity.this, "İnternet bağlantısında bir problem var. Lütfen tekrar deneyin.", Toast.LENGTH_LONG).show();

                            }
                        });


                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(ZikirDavetDetailActivity.this, "İnternet bağlantısında bir problem var. Lütfen tekrar deneyin.", Toast.LENGTH_LONG).show();

                    }

                });



            }
        });


    }


    public void getDavet() {


        DocumentReference usdRef = firebaseFirestore.collection("ZikirMatik").document(auth.getCurrentUser().getEmail()).collection("invitedZikir").document(zikirDavetDocumentID);
        usdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        String documentId = document.getId();
                        String nickName = (String) document.get("nickname");
                        String zikirName = (String) document.get("zikirName");
                        String zikirCount = (String) document.get("zikirCount");
                        String zikirDescription = (String) document.get("zikirDescription");
                        String endDate = (String) document.get("endDate");
                        String email = (String) document.get("email");

                        binding.zikirUzerTextView.setText(nickName);
                        binding.zikirAdiTextView.setText(zikirName);
                        binding.zikirNiyetTextView.setText(zikirDescription + " İçin");
                        binding.zikirAdetTextView.setText(zikirCount + " Adet");
                        binding.zikirBitisTarihiTextView.setText("Son gün " + endDate);
                        binding.zikirUzerTextView.setText(nickName);
                        davetEmeilString = email;

                        zikirCountDavet = Integer.valueOf(zikirCount);

                        System.out.println("count " + zikirCountDavet);


                    } else {
                        System.out.println("Olumsuz");
                    }

                } else {

                    System.out.println("cekme başarısız");
                }

            }

        });

    }







        public void getProfil() {

        String email = auth.getCurrentUser().getEmail();

        DocumentReference usdRef = firebaseFirestore.collection("User").document(email);
        usdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        userName = (String) document.get("userName");


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