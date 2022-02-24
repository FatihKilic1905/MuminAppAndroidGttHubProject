package com.fatihkilic.muminappandroid.ZikirMatik;

import static com.fatihkilic.muminappandroid.ZikirMatik.ZikirAddActivity.kisiekleArray;

import androidx.annotation.NonNull;
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
import com.fatihkilic.muminappandroid.User.FriendsOperationActivity;
import com.fatihkilic.muminappandroid.databinding.ActivityKisilerBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityZikirAddBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KisilerActivity extends AppCompatActivity {


    static String toVcKisilerStaticNew;
    static ArrayList<String> zikirNewUserArraylist;
    static ArrayList<String> zikirNewEmailArrayList;


    private ActivityKisilerBinding binding;

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;


    String currentUser;
    String myUserName;

    String zikirNameUsers;
    String zikirSayisiUsers;
    String zikirBitisTarihiUsers;
    String zikirNiyetiUsers;

    private static final String ONESIGNAL_APP_ID = "1966721c-a30c-4299-9d7a-38e084b98072";

    ArrayList<ModelKisiler> modelKisilerArrayList;
    AdapterKisiler adapterKisiler;

    String UIDUsers;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kisiler);


        binding = ActivityKisilerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent govcIntent = getIntent();
        toVcKisilerStaticNew = govcIntent.getStringExtra("goVcKisiler");
        zikirNewUserArraylist = new ArrayList<>();
        zikirNewEmailArrayList = new ArrayList<>();
        UIDUsers = govcIntent.getStringExtra("UsersUID");





        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        getProfil();
        getZikirInfo();

        currentUser = auth.getCurrentUser().getEmail();

        getFriends();

        modelKisilerArrayList = new ArrayList<>();





        binding.kisilerRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapterKisiler = new AdapterKisiler(modelKisilerArrayList);
        binding.kisilerRecyclerview.setAdapter(adapterKisiler);


        binding.secimiTamamlaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (toVcKisilerStaticNew.equals("ZikirAddPage")) {

                    Intent secimiTamamlaIntent = new Intent(KisilerActivity.this, ZikirAddActivity.class);
                    startActivity(secimiTamamlaIntent);
                    finish();


                } else if (toVcKisilerStaticNew.equals("ZikirUsersPage")) {


                    for (String usersUserName : zikirNewUserArraylist) {

                        for (String usersEmail : zikirNewEmailArrayList) {


                            HashMap<String, Object> usersData = new HashMap<>();

                            usersData.put("email", usersEmail);
                            usersData.put("inviteAnsver", "0");
                            usersData.put("zikirCompleteCount", 0);
                            usersData.put("nickName", usersUserName);
                            usersData.put("zikirCount", 0);
                            usersData.put("zikirStatus", "1");

                            firebaseFirestore.collection("ZikirMatik").document(auth.getCurrentUser().getEmail()).collection("myZikir").document(UIDUsers).collection("Users").document(usersEmail).set(usersData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(@NonNull Void unused) {



                                    HashMap<String, Object> inviteData = new HashMap<>();

                                    inviteData.put("email", auth.getCurrentUser().getEmail());
                                    inviteData.put("zikirName", zikirNameUsers);
                                    inviteData.put("zikirCount", zikirSayisiUsers);
                                    inviteData.put("endDate", zikirBitisTarihiUsers);
                                    inviteData.put("nickname", myUserName);
                                    inviteData.put("inviteStatus", "0");
                                    inviteData.put("zikirStatus", "1");
                                    inviteData.put("zikirDescription", zikirNiyetiUsers);
                                    inviteData.put("zikirCompleteCount", 0);
                                    inviteData.put("zikirMyCompleteCount", 0);
                                    inviteData.put("zikirMyCount", 0);

                                    firebaseFirestore.collection("ZikirMatik").document(usersEmail).collection("invitedZikir").document(UIDUsers).set(inviteData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(@NonNull Void unused) {

                                            firebaseFirestore.collection("OneSignal").whereEqualTo("email", usersEmail).addSnapshotListener(new EventListener<QuerySnapshot>() {
                                                @Override
                                                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                                                    if (error != null) {

                                                        Toast.makeText(KisilerActivity.this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                                                    }

                                                    if (value != null) {

                                                        for (DocumentSnapshot snapshot : value.getDocuments()) {

                                                            Map<String, Object> data = snapshot.getData();

                                                            String playeridDocumentId = snapshot.getId();
                                                            String PlayerIdFirebase = (String) data.get("player_id");

                                                            StringBuilder mesaj = new StringBuilder();
                                                            mesaj.append(myUserName);
                                                            mesaj.append(" ");
                                                            mesaj.append(zikirNameUsers);
                                                            mesaj.append(" ");
                                                            mesaj.append("zikretmeye davet etti.");


                                                            try {
                                                                OneSignal.postNotification(new JSONObject("{'contents': {'en':'" + mesaj.toString() + "'}, 'include_player_ids': ['" + PlayerIdFirebase + "']}"), null);
                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }

                                                        }

                                                    }
                                                }

                                            });

                                        }
                                    });

                                }

                            });

                        }

                    }




                    Intent secimiTamamlaIntent = new Intent(KisilerActivity.this, ZikirAddActivity.class);
                    startActivity(secimiTamamlaIntent);
                    finish();


                }




            }
        });


        binding.arkadasEkleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent KisilerIntent = new Intent(KisilerActivity.this, FriendsOperationActivity.class);
                    startActivity(KisilerIntent);


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


                    modelKisilerArrayList.removeAll(modelKisilerArrayList);
                    for (DocumentSnapshot snapshot : value.getDocuments()) {


                        Map<String, Object> data = snapshot.getData();


                        String friendsUserName = (String) data.get("userName");
                        String friendsName = (String) data.get("name");
                        String friendsSurName = (String) data.get("surName");
                        String friendsImage = (String) data.get("image");
                        String friendEmail = (String) data.get("email");


                        ModelKisiler modelKisiler = new ModelKisiler(friendsUserName,friendsName,friendsSurName,friendsImage,friendEmail);
                        modelKisilerArrayList.add(modelKisiler);




                    }

                    adapterKisiler.notifyDataSetChanged();

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

                        myUserName = (String) document.get("userName");


                    } else {
                        System.out.println("Olumsuz");
                    }

                } else {

                    System.out.println("cekme başarısız");
                }

            }


        });


    }

    public void getZikirInfo () {

        DocumentReference usdRef = firebaseFirestore.collection("ZikirMatik").document(auth.getCurrentUser().getEmail()).collection("myZikir").document(UIDUsers);
        usdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        zikirNameUsers = (String) document.get("zikirName");
                        zikirSayisiUsers = (String) document.get("zikirCount");
                        zikirBitisTarihiUsers = (String) document.get("endDate");
                        zikirNiyetiUsers = (String) document.get("zikirDescription");



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