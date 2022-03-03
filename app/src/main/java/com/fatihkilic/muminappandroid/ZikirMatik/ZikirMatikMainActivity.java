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

    ArrayList<ModelZikirIstirak> modelZikirIstirakArrayList;
    AdapterIstirak adapterIstirak;

    ArrayList<ModelZikirDAvet> modelZikirDAvetArrayList;
    AdapterZikirDavet adapterZikirDavet;



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


        binding.myZikirRecyclerView.setVisibility(View.VISIBLE);
        binding.istirakRecyclerView.setVisibility(View.INVISIBLE);
        binding.davetRecyclerView.setVisibility(View.INVISIBLE);

        modelZikirlerimArrayList = new ArrayList<>();
        getZikirlerim();
        binding.myZikirRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterZikirlerim = new AdapterZikirlerim(modelZikirlerimArrayList);
        binding.myZikirRecyclerView.setAdapter(adapterZikirlerim);

        modelZikirIstirakArrayList = new ArrayList<>();
        getIstirak();
        binding.istirakRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterIstirak = new AdapterIstirak(modelZikirIstirakArrayList);
        binding.istirakRecyclerView.setAdapter(adapterIstirak);

        modelZikirDAvetArrayList = new ArrayList<>();
        getDavet();
        binding.davetRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterZikirDavet = new AdapterZikirDavet(modelZikirDAvetArrayList);
        binding.davetRecyclerView.setAdapter(adapterZikirDavet);




        if (modelZikirDAvetArrayList.size() > 0 ) {

            binding.zikirlerimButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
            binding.zikirlerimButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
            binding.istirakButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
            binding.istirakButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
            binding.davetButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner_red));
            binding.davetButton.setTextColor(getResources().getColor(R.color.red));
            binding.davetButton.setText("Davet " + "(" + modelZikirDAvetArrayList.size() + ")");



        } else {


            binding.zikirlerimButton.setBackground(getResources().getDrawable(R.drawable.corner_4_radius));
            binding.zikirlerimButton.setTextColor(getResources().getColor(R.color.white));
            binding.istirakButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
            binding.istirakButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
            binding.davetButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
            binding.davetButton.setTextColor(getResources().getColor(R.color.muminAppGreen));



        }












        binding.zikirBaslatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent zikirBaslatIntent = new Intent(ZikirMatikMainActivity.this, ZikirAddActivity.class);
                startActivity(zikirBaslatIntent);



            }
        });

        binding.zikirlerimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.myZikirRecyclerView.setVisibility(View.VISIBLE);
                binding.istirakRecyclerView.setVisibility(View.INVISIBLE);
                binding.davetRecyclerView.setVisibility(View.INVISIBLE);


                if (modelZikirlerimArrayList.size() > 0) {

                   binding.uyariTextView.setVisibility(View.INVISIBLE);

                } else {

                    binding.uyariTextView.setVisibility(View.VISIBLE);
                    binding.uyariTextView.setText("Bu alanda sizin oluşturduğunuz zikirleri görebilirsiniz.");


                }




                if (modelZikirDAvetArrayList.size() > 0 ) {

                    binding.zikirlerimButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                    binding.zikirlerimButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                    binding.istirakButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                    binding.istirakButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                    binding.davetButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner_red));
                    binding.davetButton.setTextColor(getResources().getColor(R.color.red));
                    binding.davetButton.setText("Davet " + "(" + modelZikirDAvetArrayList.size() + ")");



                } else {


                    binding.zikirlerimButton.setBackground(getResources().getDrawable(R.drawable.corner_4_radius));
                    binding.zikirlerimButton.setTextColor(getResources().getColor(R.color.white));
                    binding.istirakButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                    binding.istirakButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                    binding.davetButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                    binding.davetButton.setTextColor(getResources().getColor(R.color.muminAppGreen));



                }




            }
        });


        binding.istirakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.myZikirRecyclerView.setVisibility(View.INVISIBLE);
                binding.istirakRecyclerView.setVisibility(View.VISIBLE);
                binding.davetRecyclerView.setVisibility(View.INVISIBLE);


                if (modelZikirIstirakArrayList.size() > 0) {

                    binding.uyariTextView.setVisibility(View.INVISIBLE);

                } else {

                    binding.uyariTextView.setVisibility(View.VISIBLE);
                    binding.uyariTextView.setText("Bu alanda katıldığınız zikirleri görebilirsiniz.");


                }



                if (modelZikirDAvetArrayList.size() > 0 ) {

                    binding.zikirlerimButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                    binding.zikirlerimButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                    binding.istirakButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                    binding.istirakButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                    binding.davetButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner_red));
                    binding.davetButton.setTextColor(getResources().getColor(R.color.red));
                    binding.davetButton.setText("Davet " + "(" + modelZikirDAvetArrayList.size() + ")");



                } else {


                    binding.zikirlerimButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                    binding.zikirlerimButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                    binding.istirakButton.setBackground(getResources().getDrawable(R.drawable.corner_4_radius));
                    binding.istirakButton.setTextColor(getResources().getColor(R.color.white));
                    binding.davetButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                    binding.davetButton.setTextColor(getResources().getColor(R.color.muminAppGreen));


                }



            }
        });

        binding.davetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.myZikirRecyclerView.setVisibility(View.INVISIBLE);
                binding.istirakRecyclerView.setVisibility(View.INVISIBLE);
                binding.davetRecyclerView.setVisibility(View.VISIBLE);


                if (modelZikirDAvetArrayList.size() > 0) {

                    binding.uyariTextView.setVisibility(View.INVISIBLE);

                } else {

                    binding.uyariTextView.setVisibility(View.VISIBLE);
                    binding.uyariTextView.setText("Bu alanda zikir davetlerinizi görebilirsiniz.");


                }




                if (modelZikirDAvetArrayList.size() > 0 ) {

                    binding.zikirlerimButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                    binding.zikirlerimButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                    binding.istirakButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                    binding.istirakButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                    binding.davetButton.setBackground(getResources().getDrawable(R.drawable.corner_4_radius_red));
                    binding.davetButton.setTextColor(getResources().getColor(R.color.white));
                    binding.davetButton.setText("Davet " + "(" + modelZikirDAvetArrayList.size() + ")");



                } else {


                    binding.zikirlerimButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                    binding.zikirlerimButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                    binding.istirakButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                    binding.istirakButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                    binding.davetButton.setBackground(getResources().getDrawable(R.drawable.corner_4_radius));
                    binding.davetButton.setTextColor(getResources().getColor(R.color.white));


                }














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

                    modelZikirlerimArrayList.remove(modelZikirlerimArrayList);


                    for (DocumentSnapshot snapshot : value.getDocuments()) {


                        Map<String, Object> data = snapshot.getData();

                        String zikirName = (String) data.get("zikirName");
                        Long zcc = (Long) data.get("ZikirCompleteCount");
                        Long zc = (Long) data.get("zikirCount");
                        String endDate = (String) data.get("endDate");
                        String documentId = snapshot.getId();

                        System.out.println("documentId" + documentId);

                        Integer zikirCompleteCount = zcc.intValue();
                        Integer zikirCount = zc.intValue();



                        ModelZikirlerim modelZikirlerim = new ModelZikirlerim(zikirName,zikirCompleteCount,zikirCount,endDate,documentId);
                        modelZikirlerimArrayList.add(modelZikirlerim);
                        System.out.println("nerede " + modelZikirlerimArrayList);


                    }

                    if (modelZikirlerimArrayList.size() > 0) {

                        binding.uyariTextView.setVisibility(View.INVISIBLE);

                    } else {

                        binding.uyariTextView.setVisibility(View.VISIBLE);
                        binding.uyariTextView.setText("Bu alanda sizin oluşturduğunuz zikirleri görebilirsiniz.");


                    }


                    adapterZikirlerim.notifyDataSetChanged();

                }

            }
        });




    }


    private void getIstirak () {

        String email = auth.getCurrentUser().getEmail();

        System.out.println("nerede " + email);

        firebaseFirestore.collection("ZikirMatik").document(email).collection("invitedZikir").whereEqualTo("inviteStatus", "1").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    System.out.println("istirak cekilemedi");

                }

                if (value != null) {

                    modelZikirlerimArrayList.remove(modelZikirIstirakArrayList);

                    for (DocumentSnapshot snapshot : value.getDocuments()) {


                        Map<String, Object> data = snapshot.getData();

                        String documentId = snapshot.getId();
                        String nickName = (String) data.get("nickname");
                        String zikirName = (String) data.get("zikirName");
                        String zikirCount = (String) data.get("zikirCount");
                        String endDate = (String) data.get("endDate");
                        String email = (String) data.get("email");


                        Long zcc = (Long) data.get("zikirCompleteCount");
                        Long zmcc = (Long) data.get("zikirMyCompleteCount");
                        Long zmc = (Long) data.get("zikirMyCount");


                        System.out.println("documentId" + documentId);

                        Integer zikirCompleteCount = zcc.intValue();
                        Integer zikirMyCompleteCount = zmcc.intValue();
                        Integer zikirMyCount = zmc.intValue();



                        ModelZikirIstirak modelZikirIstirak = new ModelZikirIstirak(nickName,zikirName,zikirCount,endDate,email,documentId,zikirCompleteCount,zikirMyCompleteCount,zikirMyCount);
                        modelZikirIstirakArrayList.add(modelZikirIstirak);



                    }


                    adapterIstirak.notifyDataSetChanged();

                }

            }
        });




    }


    private void getDavet() {

        String email = auth.getCurrentUser().getEmail();

        System.out.println("nerede " + email);

        firebaseFirestore.collection("ZikirMatik").document(email).collection("invitedZikir").whereEqualTo("inviteStatus", "0").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    System.out.println("davet cekilemedi");

                }

                if (value != null) {

                    modelZikirDAvetArrayList.remove(modelZikirDAvetArrayList);

                    for (DocumentSnapshot snapshot : value.getDocuments()) {


                        Map<String, Object> data = snapshot.getData();

                        String documentId = snapshot.getId();
                        String nickName = (String) data.get("nickname");
                        String zikirName = (String) data.get("zikirName");







                        ModelZikirDAvet modelZikirDAvet = new ModelZikirDAvet(nickName,zikirName,documentId);
                        modelZikirDAvetArrayList.add(modelZikirDAvet);



                    }


                    adapterZikirDavet.notifyDataSetChanged();

                }

            }
        });




    }

}