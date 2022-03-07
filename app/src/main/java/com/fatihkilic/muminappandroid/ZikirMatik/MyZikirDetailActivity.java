package com.fatihkilic.muminappandroid.ZikirMatik;

import static com.google.firebase.inappmessaging.internal.Logging.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.User.FriendsDetailActivity;
import com.fatihkilic.muminappandroid.User.MyAccountEditActivity;
import com.fatihkilic.muminappandroid.databinding.ActivityMyZikirDetailBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityZikirAddBinding;
import com.fatihkilic.muminappandroid.databinding.ZikirUsersRowBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyZikirDetailActivity extends AppCompatActivity {

    private ActivityMyZikirDetailBinding binding;

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    private static final String ONESIGNAL_APP_ID = "1966721c-a30c-4299-9d7a-38e084b98072";

    private InterstitialAd mInterstitialAd;

    private AdView mAdView;

    String myzikirDocumentId;

    String istirakZikirDocumentId;
    String zikirOwnerEmail;
    String toVcZikir;

    String userName;

    Integer meZikirCompleteCount;
    Integer meZikirCountInt;
    String  zikirName;
    Integer zikirCountGenelInt;
    ArrayList<Integer> getZikirTotalCountArraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_zikir_detail);

        binding = ActivityMyZikirDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        AdRequest adRequestGecis = new AdRequest.Builder().build();

        InterstitialAd.load(MyZikirDetailActivity.this,"ca-app-pub-9766355257187956/4850077826", adRequestGecis,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });



        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = binding.adView;
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        binding.zikirDuasiTextview.setMovementMethod(new ScrollingMovementMethod());

        Intent myZikirIntent = getIntent();
        myzikirDocumentId = myZikirIntent.getStringExtra("myZikirDocumentId");
        toVcZikir = myZikirIntent.getStringExtra("goVcZikir");


        Intent istirakZikirIntent = getIntent();
        istirakZikirDocumentId = istirakZikirIntent.getStringExtra("istirakZikirDocumentId");
        toVcZikir = istirakZikirIntent.getStringExtra("goVcZikir");
        zikirOwnerEmail = istirakZikirIntent.getStringExtra("zikirOwnerEmail");

        System.out.println("tovc " + zikirOwnerEmail);



        getZikirTotalCountArraylist = new ArrayList<>();

        if (toVcZikir.equals("MyZikir")) {

            getMyzikirVoid();
            getMezikirVoid();
            getSupportActionBar().setTitle(zikirName);


        } else if (toVcZikir.equals("IstirakZikir")) {

            getIstirakZikirVoid();
            getUsersZikirVoid();
            getSupportActionBar().setTitle(zikirName);

        }






        binding.ZikirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (toVcZikir.equals("MyZikir")) {

                    StringBuilder finisMesaj = new StringBuilder();
                    finisMesaj.append(zikirName);
                    finisMesaj.append(" zikrinde çekmeniz gereken kısmı tamamladınız. İstirak kısmından takip edebilirsiniz.");

                    if (meZikirCompleteCount >= meZikirCountInt) {


                        AlertDialog.Builder finisAlert = new AlertDialog.Builder(MyZikirDetailActivity.this);

                        finisAlert.setTitle("Tebrikler");
                        finisAlert.setMessage(finisMesaj.toString());
                        finisAlert.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                if (mInterstitialAd != null) {
                                    mInterstitialAd.show(MyZikirDetailActivity.this);
                                } else {
                                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                                }


                            }
                        });

                        finisAlert.show();

                    } else {


                        meZikirCompleteCount += 1;

                        StringBuilder zikirCount = new StringBuilder();
                        zikirCount.append(meZikirCompleteCount);
                        zikirCount.append("/");
                        zikirCount.append(meZikirCountInt);

                        binding.myCountTextView.setText(zikirCount.toString());


                        HashMap<String, Object> updateZikirData = new HashMap<>();
                        updateZikirData.put("zikirCompleteCount", meZikirCompleteCount);


                        firebaseFirestore.collection("ZikirMatik").document(auth.getCurrentUser().getEmail()).collection("myZikir").document(myzikirDocumentId).collection("Users").document(auth.getCurrentUser().getEmail()).update(updateZikirData).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(@NonNull Void unused) {

                                System.out.println("totalCountBasarılı");


                                System.out.println("totalCount2" + getZikirTotalCountArraylist);
                                getTotalCountVoidMyzikir();

                                Integer totalZikirCount = 0;

                                for (int TC : getZikirTotalCountArraylist) {


                                    totalZikirCount = totalZikirCount + TC;

                                    System.out.println("totalCount " + totalZikirCount);


                                }

                                StringBuilder zikirCount = new StringBuilder();
                                zikirCount.append(totalZikirCount);
                                zikirCount.append("/");
                                zikirCount.append(zikirCountGenelInt);

                                binding.genelCountTextview.setText(zikirCount.toString());


                                HashMap<String, Object> updateZikirGenelCountData = new HashMap<>();
                                updateZikirGenelCountData.put("ZikirCompleteCount", totalZikirCount);


                                firebaseFirestore.collection("ZikirMatik").document(auth.getCurrentUser().getEmail()).collection("myZikir").document(myzikirDocumentId).update(updateZikirGenelCountData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(@NonNull Void unused) {


                                    }
                                });


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                System.out.println("totalCountBasarısız");


                            }
                        });


                    }


                } else if (toVcZikir.equals("IstirakZikir")) {


                    StringBuilder finisMesaj = new StringBuilder();
                    finisMesaj.append(zikirName);
                    finisMesaj.append(" zikrinde çekmeniz gereken kısmı tamamladınız. İstirak kısmından takip edebilirsiniz.");

                    if (meZikirCompleteCount >= meZikirCountInt) {


                        System.out.println("tamamlandı");
                        AlertDialog.Builder finisAlert = new AlertDialog.Builder(MyZikirDetailActivity.this);

                        finisAlert.setTitle("Tebrikler");
                        finisAlert.setMessage(finisMesaj.toString());
                        finisAlert.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                getProfil();

                                firebaseFirestore.collection("OneSignal").whereEqualTo("email",zikirOwnerEmail).addSnapshotListener(new EventListener<QuerySnapshot>() {
                                    @Override
                                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                                        if (error != null) {

                                            Toast.makeText(MyZikirDetailActivity.this,error.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                                        }

                                        if (value != null) {

                                            for (DocumentSnapshot snapshot : value.getDocuments()) {

                                                Map<String, Object> data = snapshot.getData();

                                                String playeridDocumentId = snapshot.getId();
                                                String PlayerIdFirebase = (String) data.get("player_id");

                                                StringBuilder mesaj = new StringBuilder();
                                                mesaj.append(userName);
                                                mesaj.append(" ");
                                                mesaj.append("söz verdiği ");
                                                mesaj.append(meZikirCountInt);
                                                mesaj.append(" adet zikri tamamladı.");


                                                try {
                                                    OneSignal.postNotification(new JSONObject("{'contents': {'en':'" + mesaj.toString() + "'}, 'include_player_ids': ['" + PlayerIdFirebase + "']}"), null);
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }

                                            }




                                        }
                                    }
                                });




                                if (mInterstitialAd != null) {
                                    mInterstitialAd.show(MyZikirDetailActivity.this);
                                } else {
                                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                                }


                            }
                        });

                        finisAlert.show();

                    } else {


                        meZikirCompleteCount += 1;

                        StringBuilder zikirCount = new StringBuilder();
                        zikirCount.append(meZikirCompleteCount);
                        zikirCount.append("/");
                        zikirCount.append(meZikirCountInt);

                        binding.myCountTextView.setText(zikirCount.toString());


                        HashMap<String, Object> updateZikirData = new HashMap<>();
                        updateZikirData.put("zikirCompleteCount", meZikirCompleteCount);


                        firebaseFirestore.collection("ZikirMatik").document(zikirOwnerEmail).collection("myZikir").document(istirakZikirDocumentId).collection("Users").document(auth.getCurrentUser().getEmail()).update(updateZikirData).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(@NonNull Void unused) {

                                System.out.println("totalCountBasarılı");


                                System.out.println("totalCount2" + getZikirTotalCountArraylist);
                                getTotalCountVoidIstirak();

                                Integer totalZikirCount = 0;

                                for (int TC : getZikirTotalCountArraylist) {


                                    totalZikirCount = totalZikirCount + TC;

                                    System.out.println("totalCount " + totalZikirCount);


                                }

                                StringBuilder zikirCount = new StringBuilder();
                                zikirCount.append(totalZikirCount);
                                zikirCount.append("/");
                                zikirCount.append(zikirCountGenelInt);

                                binding.genelCountTextview.setText(zikirCount.toString());


                                HashMap<String, Object> updateZikirGenelCountData = new HashMap<>();
                                updateZikirGenelCountData.put("ZikirCompleteCount", totalZikirCount);



                                firebaseFirestore.collection("ZikirMatik").document(zikirOwnerEmail).collection("myZikir").document(istirakZikirDocumentId).update(updateZikirGenelCountData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(@NonNull Void unused) {


                                    }
                                });


                                HashMap<String, Object> updateIstirakTotalZikirData = new HashMap<>();
                                updateIstirakTotalZikirData.put("zikirCompleteCount", totalZikirCount);


                                firebaseFirestore.collection("ZikirMatik").document(auth.getCurrentUser().getEmail()).collection("invitedZikir").document(istirakZikirDocumentId).update(updateIstirakTotalZikirData).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        System.out.println("istirakMeCountBasarısız");

                                    }
                                });


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                System.out.println("totalCountBasarısız");


                            }
                        });


                        HashMap<String, Object> updateIstirakZikirData = new HashMap<>();
                        updateIstirakZikirData.put("zikirMyCompleteCount", meZikirCompleteCount);

                        firebaseFirestore.collection("ZikirMatik").document(auth.getCurrentUser().getEmail()).collection("invitedZikir").document(istirakZikirDocumentId).update(updateIstirakZikirData).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                System.out.println("istirakMeCountBasarısız");

                            }
                        });







                    }







                }

            }
        });

        binding.usersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (toVcZikir.equals("MyZikir")) {

                    Intent usersIntent = new Intent(MyZikirDetailActivity.this, ZikirUsersActivity.class);
                    usersIntent.putExtra("myZikirDocumentId", myzikirDocumentId);
                    usersIntent.putExtra("goVcUsers","MyZikir");
                    startActivity(usersIntent);

                } else if (toVcZikir.equals("IstirakZikir")) {

                    Intent usersIntent = new Intent(MyZikirDetailActivity.this, ZikirUsersActivity.class);
                    usersIntent.putExtra("myZikirDocumentId", istirakZikirDocumentId);
                    usersIntent.putExtra("zikirOwnerEmail",zikirOwnerEmail);
                    usersIntent.putExtra("goVcUsers","IstirakZikir");
                    startActivity(usersIntent);
                }





            }
        });


    }


    public void getIstirakZikirVoid () {

        firebaseFirestore.collection("ZikirMatik").document(zikirOwnerEmail).collection("myZikir").document(istirakZikirDocumentId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {


                        binding.zikirAdiTextview.setText((String) document.get("zikirName"));

                        zikirName = (String) document.get("zikirName");

                        binding.bitisTarihiTextView.setText((String) document.get("endDate"));
                        binding.zikirNiyetiTextView.setText((String) document.get("zikirDescription"));
                        binding.zikirDuasiTextview.setText((String) document.get("zikirPray"));


                        Long zikirCompCount = (Long) document.get("ZikirCompleteCount");
                        Long zikirCountGenel = (Long) document.get("zikirCount");

                        zikirCountGenelInt = zikirCountGenel.intValue();

                        System.out.println("count " + zikirCompCount + " " + zikirCountGenel);

                        StringBuilder zikirCount = new StringBuilder();
                        zikirCount.append(zikirCompCount);
                        zikirCount.append("/");
                        zikirCount.append(zikirCountGenel);

                        binding.genelCountTextview.setText(zikirCount.toString());


                        if (zikirCompCount >= zikirCountGenel) {
                            binding.genelCountTextview.setBackground(getDrawable(R.drawable.layer_stroke_4_corner_red));
                            binding.genelCountTextview.setTextColor(Color.rgb(255, 0, 0));

                        }



                    }

                }



            }
        });


    }

    public void getUsersZikirVoid () {

        firebaseFirestore.collection("ZikirMatik").document(zikirOwnerEmail).collection("myZikir").document(istirakZikirDocumentId).collection("Users").document(auth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {



                        Long mezikirCompCount = (Long) document.get("zikirCompleteCount");
                        Long mezikirCountGenel = (Long) document.get("zikirCount");

                        meZikirCompleteCount = mezikirCompCount.intValue();
                        meZikirCountInt = mezikirCountGenel.intValue();



                        StringBuilder zikirCount = new StringBuilder();
                        zikirCount.append(meZikirCompleteCount);
                        zikirCount.append("/");
                        zikirCount.append(meZikirCountInt);

                        binding.myCountTextView.setText(zikirCount.toString());

                        if (meZikirCompleteCount >= meZikirCountInt) {

                            binding.ZikirButton.setEnabled(false);
                            binding.myCountTextView.setBackground(getDrawable(R.drawable.layer_stroke_4_corner_red));
                            binding.myCountTextView.setTextColor(Color.rgb(255,0,0));


                        }



                    }

                }

            }
        });


    }


    public void getMyzikirVoid() {




        firebaseFirestore.collection("ZikirMatik").document(auth.getCurrentUser().getEmail()).collection("myZikir").document(myzikirDocumentId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {


                        binding.zikirAdiTextview.setText((String) document.get("zikirName"));

                        zikirName = (String) document.get("zikirName");

                        binding.bitisTarihiTextView.setText((String) document.get("endDate"));
                        binding.zikirNiyetiTextView.setText((String) document.get("zikirDescription"));
                        binding.zikirDuasiTextview.setText((String) document.get("zikirPray"));


                        Long zikirCompCount = (Long) document.get("ZikirCompleteCount");
                        Long zikirCountGenel = (Long) document.get("zikirCount");

                        zikirCountGenelInt = zikirCountGenel.intValue();

                        System.out.println("count " + zikirCompCount + " " + zikirCountGenel);

                        StringBuilder zikirCount = new StringBuilder();
                        zikirCount.append(zikirCompCount);
                        zikirCount.append("/");
                        zikirCount.append(zikirCountGenel);

                        binding.genelCountTextview.setText(zikirCount.toString());


                        if (zikirCompCount >= zikirCountGenel) {
                            binding.genelCountTextview.setBackground(getDrawable(R.drawable.layer_stroke_4_corner_red));
                            binding.genelCountTextview.setTextColor(Color.rgb(255, 0, 0));

                        }



                    }

                }



            }
        });

    }


    public void getMezikirVoid() {


        firebaseFirestore.collection("ZikirMatik").document(auth.getCurrentUser().getEmail()).collection("myZikir").document(myzikirDocumentId).collection("Users").document(auth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {



                        Long mezikirCompCount = (Long) document.get("zikirCompleteCount");
                        Long mezikirCountGenel = (Long) document.get("zikirCount");

                        meZikirCompleteCount = mezikirCompCount.intValue();
                        meZikirCountInt = mezikirCountGenel.intValue();



                        StringBuilder zikirCount = new StringBuilder();
                        zikirCount.append(meZikirCompleteCount);
                        zikirCount.append("/");
                        zikirCount.append(meZikirCountInt);

                        binding.myCountTextView.setText(zikirCount.toString());

                        if (meZikirCompleteCount >= meZikirCountInt) {

                            binding.ZikirButton.setEnabled(false);
                            binding.myCountTextView.setBackground(getDrawable(R.drawable.layer_stroke_4_corner_red));
                            binding.myCountTextView.setTextColor(Color.rgb(255,0,0));


                        }



                    }

                }

            }
        });

    }


    public void getTotalCountVoidIstirak () {






        firebaseFirestore.collection("ZikirMatik").document(zikirOwnerEmail).collection("myZikir").document(istirakZikirDocumentId).collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    System.out.println("Zikirlerim cekilemedi");

                }

                if (value != null) {


                    getZikirTotalCountArraylist.clear();

                    for (DocumentSnapshot snapshot : value.getDocuments()) {

                        Map<String, Object> data = snapshot.getData();


                        Long zcc = (Long) data.get("zikirCompleteCount");

                        Integer zikirCompleteCount = zcc.intValue();

                        getZikirTotalCountArraylist.add(zikirCompleteCount);

                        System.out.println("totalCount array" + getZikirTotalCountArraylist);



                    }


                }

            }
        });




    }


    public void getTotalCountVoidMyzikir () {






        firebaseFirestore.collection("ZikirMatik").document(auth.getCurrentUser().getEmail()).collection("myZikir").document(myzikirDocumentId).collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    System.out.println("Zikirlerim cekilemedi");

                }

                if (value != null) {


                    getZikirTotalCountArraylist.clear();

                    for (DocumentSnapshot snapshot : value.getDocuments()) {

                        Map<String, Object> data = snapshot.getData();


                        Long zcc = (Long) data.get("zikirCompleteCount");

                        Integer zikirCompleteCount = zcc.intValue();

                        getZikirTotalCountArraylist.add(zikirCompleteCount);

                        System.out.println("totalCount array" + getZikirTotalCountArraylist);



                    }


                }

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