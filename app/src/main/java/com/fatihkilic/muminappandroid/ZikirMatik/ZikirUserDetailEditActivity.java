package com.fatihkilic.muminappandroid.ZikirMatik;

import static com.fatihkilic.muminappandroid.ZikirMatik.ZikirUsersActivity.zikirDocumentIDUsers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.User.MyAccountEditActivity;
import com.fatihkilic.muminappandroid.databinding.ActivityZikirUserDetailEditBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityZikirUsersBinding;
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

public class ZikirUserDetailEditActivity extends AppCompatActivity {


    private ActivityZikirUserDetailEditBinding binding;

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    private static final String ONESIGNAL_APP_ID = "1966721c-a30c-4299-9d7a-38e084b98072";

    Integer zikirCountInt;
    Integer zikirCompleteCount;
    String zikirDocumentId;
    String userNameUser;

    ArrayList<Integer> zikirTotalCountArrayList;
    ArrayList<Integer> zikirTotalCompleteCountArrayList;
    Integer zikirTotalcount;
    Integer zikirTotalCompleteCount;

    Integer zikirGenelCountInteger;
    Integer zikirGenelCompleteCountInteger;


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

        zikirTotalCountArrayList = new ArrayList<>();
        zikirTotalCompleteCountArrayList = new ArrayList<>();
        getzikirTotalcount();




        Intent userEditIntent = getIntent();

        userNameUser = userEditIntent.getStringExtra("inviteUserName");
        zikirDocumentId = userEditIntent.getStringExtra("");
        zikirCountInt = userEditIntent.getIntExtra("zikirCountIntent",0);
        zikirCompleteCount = userEditIntent.getIntExtra("zikirCompleteCountIntent",0);

        String zikrCount = String.valueOf(userEditIntent.getIntExtra("zikirCountIntent",0));
        String zikirtotalCount = String.valueOf(userEditIntent.getIntExtra("zikirCompleteCountIntent",0));
        String inviteAnsver = userEditIntent.getStringExtra("inviteAnsver");
        String usersEmail = userEditIntent.getStringExtra("usersEmail");

        binding.emptyBackground.setVisibility(View.INVISIBLE);
        binding.zikirSayisiBackground.setVisibility(View.INVISIBLE);




        if (inviteAnsver.equals("1")) {

            binding.userNameTextView.setText(userEditIntent.getStringExtra("inviteUserName"));
            binding.countTextView.setText(zikirtotalCount + " / " + zikrCount);

            binding.davetGonderbutton.setVisibility(View.INVISIBLE);
            binding.davetIptalButton.setVisibility(View.INVISIBLE);


        } else if (inviteAnsver.equals("2")) {

            binding.userNameTextView.setText(userEditIntent.getStringExtra("inviteUserName"));
            binding.countTextView.setText("Katılmadı");

            binding.zikirdeCikarButton.setVisibility(View.INVISIBLE);
            binding.zikirCountEditButton.setVisibility(View.INVISIBLE);
            binding.davetIptalButton.setVisibility(View.INVISIBLE);




        } else if (inviteAnsver.equals("0")) {

            binding.userNameTextView.setText(userEditIntent.getStringExtra("inviteUserName"));
            binding.countTextView.setText("Cevap bekleniyor...");

            binding.zikirdeCikarButton.setVisibility(View.INVISIBLE);
            binding.zikirCountEditButton.setVisibility(View.INVISIBLE);
            binding.davetGonderbutton.setVisibility(View.INVISIBLE);
            binding.davetIptalButton.setVisibility(View.VISIBLE);




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


                zikirTotalCountArrayList.clear();

                firebaseFirestore.collection("ZikirMatik").document(auth.getCurrentUser().getEmail()).collection("myZikir").document(zikirDocumentIDUsers).collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
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







                        }

                    }
                });


                zikirTotalCompleteCountArrayList.clear();

                firebaseFirestore.collection("ZikirMatik").document(auth.getCurrentUser().getEmail()).collection("myZikir").document(zikirDocumentIDUsers).collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {

                            System.out.println("Zikirlerim cekilemedi");

                        }

                        if (value != null) {


                            for (DocumentSnapshot snapshot : value.getDocuments()) {


                                Map<String, Object> data = snapshot.getData();


                                Long zc = (Long) data.get("zikirCompleteCount");

                                Integer zikirCount = zc.intValue();

                                zikirTotalCompleteCountArrayList.add(zikirCount);

                                System.out.println("count " + zikirTotalCompleteCountArrayList);


                            }

                            Integer zikirTotalCountInteger = 0;

                            for (int TC : zikirTotalCompleteCountArrayList) {


                                zikirTotalCountInteger = zikirTotalCountInteger + TC;


                            }


                            zikirTotalCompleteCount = zikirTotalCountInteger;


                            binding.kucukTextView.setText(String.valueOf(zikirtotalCount));
                            binding.buyukTextView.setText(String.valueOf(zikirGenelCountInteger - zikirTotalcount + zikirCountInt ));

                            System.out.println("count " + zikirGenelCountInteger);

                            System.out.println("count " + zikirTotalcount);

                            System.out.println("count " + zikirCountInt);

                            System.out.println("count " + zikirTotalCompleteCount);



                        }

                    }
                });


                binding.zikirSayisiBackground.setVisibility(View.VISIBLE);
                binding.emptyBackground.setVisibility(View.VISIBLE);






                binding.emptyBackground.setVisibility(View.VISIBLE);
                binding.zikirSayisiBackground.setVisibility(View.VISIBLE);




            }
        });


        binding.davetIptalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder zikirdenCikarUyari = new AlertDialog.Builder(ZikirUserDetailEditActivity.this);


                zikirdenCikarUyari.setTitle("Uyarı");
                zikirdenCikarUyari.setMessage(userNameUser + "  adlı kullanıcıya gonderilen davet iptal edilecek");
                zikirdenCikarUyari.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        HashMap<String, Object> zikirdencikarmyzikirData = new HashMap<>();
                        zikirdencikarmyzikirData.put("inviteAnsver", "2");


                        firebaseFirestore.collection("ZikirMatik").document(auth.getCurrentUser().getEmail()).collection("myZikir").document(zikirDocumentIDUsers).collection("Users").document(usersEmail).update(zikirdencikarmyzikirData).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(@NonNull Void unused) {

                                HashMap<String, Object> zikirdencikarinviteData = new HashMap<>();
                                zikirdencikarinviteData.put("inviteStatus", "2");

                                firebaseFirestore.collection("ZikirMatik").document(usersEmail).collection("invitedZikir").document(zikirDocumentIDUsers).update(zikirdencikarinviteData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(@NonNull Void unused) {

                                        Toast.makeText(ZikirUserDetailEditActivity.this, userNameUser + "adlı kullanıcıya gönderilen davet iptal edildi.", Toast.LENGTH_LONG).show();
                                        onBackPressed();

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        Toast.makeText(ZikirUserDetailEditActivity.this, "Davet iptali başarısız! Lütfen daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();

                                    }
                                });




                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {




                                Toast.makeText(ZikirUserDetailEditActivity.this, "Davet iptali başarısız! Lütfen daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();


                            }
                        });

                    }
                });


                zikirdenCikarUyari.setNegativeButton("Vazgeç", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                zikirdenCikarUyari.show();




            }
        });



        binding.davetGonderbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder zikirdenCikarUyari = new AlertDialog.Builder(ZikirUserDetailEditActivity.this);


                zikirdenCikarUyari.setTitle("Uyarı");
                zikirdenCikarUyari.setMessage(userNameUser + " adlı kullanıcıya davet gonderilecek.");
                zikirdenCikarUyari.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        HashMap<String, Object> zikirdencikarmyzikirData = new HashMap<>();
                        zikirdencikarmyzikirData.put("inviteAnsver", "0");


                        firebaseFirestore.collection("ZikirMatik").document(auth.getCurrentUser().getEmail()).collection("myZikir").document(zikirDocumentIDUsers).collection("Users").document(usersEmail).update(zikirdencikarmyzikirData).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(@NonNull Void unused) {

                                HashMap<String, Object> zikirdencikarinviteData = new HashMap<>();
                                zikirdencikarinviteData.put("inviteStatus", "0");

                                firebaseFirestore.collection("ZikirMatik").document(usersEmail).collection("invitedZikir").document(zikirDocumentIDUsers).update(zikirdencikarinviteData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(@NonNull Void unused) {

                                        Toast.makeText(ZikirUserDetailEditActivity.this, userNameUser + "adlı kullanıcıya davet gönderildi.", Toast.LENGTH_LONG).show();
                                        onBackPressed();

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        Toast.makeText(ZikirUserDetailEditActivity.this, "Davet gönderme başarısız! Lütfen daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();

                                    }
                                });




                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {




                                Toast.makeText(ZikirUserDetailEditActivity.this, "Davet gönderme başarısız! Lütfen daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();


                            }
                        });

                    }
                });


                zikirdenCikarUyari.setNegativeButton("Vazgeç", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                zikirdenCikarUyari.show();


            }
        });


        binding.zikirdeCikarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder zikirdenCikarUyari = new AlertDialog.Builder(ZikirUserDetailEditActivity.this);


                zikirdenCikarUyari.setTitle("Uyarı");
                zikirdenCikarUyari.setMessage(userNameUser + " adlı kullanıcı zikir halkasından çıkarılcak. Bu şimdiye kadar çektiği zikirleri etkilemeyecek.");
                zikirdenCikarUyari.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        HashMap<String, Object> zikirdencikarmyzikirData = new HashMap<>();
                        zikirdencikarmyzikirData.put("inviteAnsver", "2");


                        firebaseFirestore.collection("ZikirMatik").document(auth.getCurrentUser().getEmail()).collection("myZikir").document(zikirDocumentIDUsers).collection("Users").document(usersEmail).update(zikirdencikarmyzikirData).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(@NonNull Void unused) {

                                HashMap<String, Object> zikirdencikarinviteData = new HashMap<>();
                                zikirdencikarinviteData.put("inviteStatus", "2");

                                firebaseFirestore.collection("ZikirMatik").document(usersEmail).collection("invitedZikir").document(zikirDocumentIDUsers).update(zikirdencikarinviteData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(@NonNull Void unused) {

                                        Toast.makeText(ZikirUserDetailEditActivity.this, userNameUser + "zikir halkasından çıkarıldı.", Toast.LENGTH_LONG).show();
                                        onBackPressed();

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        Toast.makeText(ZikirUserDetailEditActivity.this, "Kişi çıkarma başarısız! Lütfen daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();

                                    }
                                });




                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {




                                Toast.makeText(ZikirUserDetailEditActivity.this, "Kişi çıkarma başarısız! Lütfen daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();


                            }
                        });

                    }
                });


                zikirdenCikarUyari.setNegativeButton("Vazgeç", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                zikirdenCikarUyari.show();


            }
        });


        binding.vazgecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.emptyBackground.setVisibility(View.INVISIBLE);
                binding.zikirSayisiBackground.setVisibility(View.INVISIBLE);


            }
        });


        binding.KabulEtOnayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer zikirCountBuyuk = Integer.parseInt(binding.buyukTextView.getText().toString());

                Integer editInteger = Integer.parseInt(binding.manuelzikirEditText.getText().toString());

                if (editInteger > zikirCountBuyuk) {


                    Toast.makeText(ZikirUserDetailEditActivity.this, "Çekeceğiniz zikir sayısı " + zikirCountBuyuk + " adetten fazla olamaz.", Toast.LENGTH_LONG).show();

                } else {


                    HashMap<String, Object> updateUserZikriCountData = new HashMap<>();
                    updateUserZikriCountData.put("zikirCount", editInteger);



                    firebaseFirestore.collection("ZikirMatik").document(auth.getCurrentUser().getEmail()).collection("myZikir").document(zikirDocumentIDUsers).collection("Users").document(usersEmail).update(updateUserZikriCountData).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(@NonNull Void unused) {


                            HashMap<String, Object> updateUserZikriCountData = new HashMap<>();
                            updateUserZikriCountData.put("zikirCount", editInteger);



                            firebaseFirestore.collection("ZikirMatik").document(usersEmail).collection("invitedZikir").document(zikirDocumentIDUsers).update(updateUserZikriCountData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(@NonNull Void unused) {


                                    Toast.makeText(ZikirUserDetailEditActivity.this, userNameUser + "adlı kullanıcının zikir sayısı güncellendi", Toast.LENGTH_LONG).show();
                                    binding.buyukTextView.setText(String.valueOf(editInteger));
                                    binding.zikirSayisiBackground.setVisibility(View.INVISIBLE);
                                    binding.emptyBackground.setVisibility(View.INVISIBLE);

                                }

                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(ZikirUserDetailEditActivity.this, "Zikir sayısı güncellenmedi. Lütfen tekrar deneyin.", Toast.LENGTH_LONG).show();

                                }

                            });


                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(ZikirUserDetailEditActivity.this, "Zikir sayısı güncellenmedi. Lütfen tekrar deneyin.", Toast.LENGTH_LONG).show();

                        }

                    });


                }






            }
        });






    }



    public void getzikirTotalcount() {



        DocumentReference usdRef = firebaseFirestore.collection("ZikirMatik").document(auth.getCurrentUser().getEmail()).collection("myZikir").document(zikirDocumentIDUsers);
        usdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        Long zgc = (Long) document.get("zikirCount");

                        zikirGenelCountInteger = zgc.intValue();

                        System.out.println("count genel" + zikirGenelCountInteger);


                    } else {
                        System.out.println("Olumsuz");
                    }

                } else {

                    System.out.println("cekme başarısız");
                }

            }


        });


    }

    public void getzikirTotalCompletecount() {



        DocumentReference usdRef = firebaseFirestore.collection("ZikirMatik").document(auth.getCurrentUser().getEmail()).collection("myZikir").document(zikirDocumentIDUsers);
        usdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        Long zgc = (Long) document.get("zikirCompleteCount");

                        zikirGenelCompleteCountInteger = zgc.intValue();



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