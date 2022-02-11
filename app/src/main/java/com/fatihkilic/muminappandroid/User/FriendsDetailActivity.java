package com.fatihkilic.muminappandroid.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityFriendsDetailBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityMyAccountBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.onesignal.OneSignal;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FriendsDetailActivity extends AppCompatActivity {

    private ActivityFriendsDetailBinding binding;

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private static final String ONESIGNAL_APP_ID = "1966721c-a30c-4299-9d7a-38e084b98072";

    String friendsInfoIntent;
    String friendsemailIntent;

    String userName;
    String name;
    String surname;
    String image;
    String currentEmail;

    String friendsUserName;
    String friendsName;
    String friendsSurname;
    String friendsEmail;
    String friendsImage;

    String friendsRequestUserName;
    String friendsRequestName;
    String friendsRequestSurname;
    String friendsRequestEmail;
    String friendsRequesImage;

    String friendsSearchUserName;
    String friendsSearchName;
    String friendsSearchSurname;
    String friendsSearchEmail;
    String friendsSearchImage;

    ArrayList<String> oldFriendsListArray;
    ArrayList<String> oldFriendsRequestListArray;
    ArrayList<String> oldFriendsMyRequestListArray;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_detail);


        binding = ActivityFriendsDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        currentEmail = auth.getCurrentUser().getEmail();
        oldFriendsListArray = new ArrayList<String>();
        oldFriendsRequestListArray = new ArrayList<String>();
        oldFriendsMyRequestListArray = new ArrayList<String>();


        Intent friendsInfoIntentCome = getIntent();
        friendsInfoIntent = friendsInfoIntentCome.getStringExtra("FriendsInfo");
        friendsemailIntent = friendsInfoIntentCome.getStringExtra("FriendsEmail");

        getProfil();
        getFriends();


        Button friendsButton = binding.friendAddButton;
        friendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (friendsInfoIntent.equals("Friends")) {





                } else if (friendsInfoIntent.equals("FriendsRequest")) {

                    AlertDialog.Builder friendsAlert = new AlertDialog.Builder(FriendsDetailActivity.this);
                    friendsAlert.setTitle("Arkadaşlık isteği onayla");
                    friendsAlert.setPositiveButton("Kabul Et", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            HashMap<String, Object> friendsData = new HashMap<>();
                            friendsData.put("email", friendsRequestEmail);
                            friendsData.put("userName", friendsRequestUserName);
                            friendsData.put("name", friendsRequestName);
                            friendsData.put("surName", friendsRequestSurname);
                            friendsData.put("image", friendsRequesImage);


                            firebaseFirestore.collection("User").document(currentEmail).collection("Friends").document(friendsemailIntent).set(friendsData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {


                                    HashMap<String, Object> myProfileData = new HashMap<>();
                                    myProfileData.put("email", currentEmail);
                                    myProfileData.put("userName", userName);
                                    myProfileData.put("name", name);
                                    myProfileData.put("surName", surname);
                                    myProfileData.put("image", image);


                                    firebaseFirestore.collection("User").document(friendsRequestEmail).collection("Friends").document(currentEmail).set(myProfileData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            firebaseFirestore.collection("OneSignal").whereEqualTo("email",friendsRequestEmail).addSnapshotListener(new EventListener<QuerySnapshot>() {
                                                @Override
                                                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                                                    if (error != null) {

                                                        Toast.makeText(FriendsDetailActivity.this,error.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                                                    }

                                                    if (value != null) {

                                                        for (DocumentSnapshot snapshot : value.getDocuments()) {

                                                            Map<String, Object> data = snapshot.getData();

                                                            String playeridDocumentId = snapshot.getId();
                                                            String PlayerIdFirebase = (String) data.get("player_id");

                                                            StringBuilder mesaj = new StringBuilder();
                                                            mesaj.append(userName);
                                                            mesaj.append(" ");
                                                            mesaj.append("arkadaşlık isteğini kabul etti.");


                                                            try {
                                                                OneSignal.postNotification(new JSONObject("{'contents': {'en':'" + mesaj.toString() + "'}, 'include_player_ids': ['" + PlayerIdFirebase + "']}"), null);
                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }

                                                        }

                                                        friendsButton.setText("Arkadaşsınız");


                                                        firebaseFirestore.collection("User").document(currentEmail).collection("FriendsRequest").document(friendsRequestEmail).delete();


                                                    }
                                                }
                                            });


                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                            Toast.makeText(FriendsDetailActivity.this,"İnternet bağlantısında bir problem var",Toast.LENGTH_LONG).show();

                                        }
                                    });


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(FriendsDetailActivity.this,"İnternet bağlantısında bir problem var",Toast.LENGTH_LONG).show();

                                }
                            });

                        }
                    });


                    friendsAlert.setNegativeButton("Reddet", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            firebaseFirestore.collection("User").document(currentEmail).collection("FriendsRequest").document(friendsRequestEmail).delete();

                        }
                    });






                } else if (friendsInfoIntent.equals("FriendsSearch")) {

                    String buttonAddLabel = binding.friendAddButton.getText().toString();


                    if (buttonAddLabel.equals("Arkadaşsınız")) {




                    } else if (buttonAddLabel.equals("Arkadaşlık İsteği Gönderildi")) {



                    } else if (buttonAddLabel.equals("Cevapla")) {




                    } else if (buttonAddLabel.equals("İstek Gönder")) {


                        HashMap<String, Object> searchData = new HashMap<>();
                        searchData.put("email", friendsSearchEmail);
                        searchData.put("userName", friendsSearchUserName);
                        searchData.put("name", friendsSearchName);
                        searchData.put("surName", friendsSearchSurname);
                        searchData.put("image", friendsSearchImage);

                        firebaseFirestore.collection("User").document(friendsSearchEmail).collection("FriendsRequest").document(currentEmail).set(searchData).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                firebaseFirestore.collection("OneSignal").whereEqualTo("email",friendsSearchEmail).addSnapshotListener(new EventListener<QuerySnapshot>() {
                                    @Override
                                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                                        if (error != null) {

                                            Toast.makeText(FriendsDetailActivity.this,error.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                                        }

                                        if (value != null) {

                                            for (DocumentSnapshot snapshot : value.getDocuments()) {

                                                Map<String, Object> data = snapshot.getData();

                                                String playeridDocumentId = snapshot.getId();
                                                String PlayerIdFirebase = (String) data.get("player_id");

                                                StringBuilder mesaj = new StringBuilder();
                                                mesaj.append(userName);
                                                mesaj.append(" ");
                                                mesaj.append("adlı kullanıcı seninle arkadaş olmak istiyor.");


                                                try {
                                                    OneSignal.postNotification(new JSONObject("{'contents': {'en':'" + mesaj.toString() + "'}, 'include_player_ids': ['" + PlayerIdFirebase + "']}"), null);
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }

                                            }

                                            friendsButton.setText("Arkadaşlık İsteği Gönderildi");

                                            Toast.makeText(FriendsDetailActivity.this,"Arkadaşlık isteği gönderildi.",Toast.LENGTH_LONG).show();



                                        }
                                    }
                                });

                            }
                        });


                    }





                }


            }
        });


        Button seceneklerButton = binding.seceneklerButton;
        seceneklerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String buttonLabel = friendsButton.getText().toString();

                if (buttonLabel.equals("Arkadaşsınız")) {


                    AlertDialog.Builder friendsAlert = new AlertDialog.Builder(FriendsDetailActivity.this);
                    friendsAlert.setTitle("Arkadaşlarımdan çıkar");
                    friendsAlert.setPositiveButton("Onayla", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            firebaseFirestore.collection("User").document(currentEmail).collection("Friends").document(friendsemailIntent).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {



                                    firebaseFirestore.collection("User").document(friendsemailIntent).collection("Friends").document(currentEmail).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            Toast.makeText(FriendsDetailActivity.this,"Kişi arkadaş listenizden çıkarıldı.",Toast.LENGTH_LONG).show();
                                            friendsButton.setText("Arkadaş Ekle");

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                            Toast.makeText(FriendsDetailActivity.this,"İnternet bağlantısında bir problem var.",Toast.LENGTH_LONG).show();

                                        }
                                    });




                                }
                            });

                        }
                    });

                    friendsAlert.setNegativeButton("Vazgeç", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });


                    friendsAlert.show();



                } else if (buttonLabel.equals("Arkadaşlık İsteği Gönderildi")) {


                    AlertDialog.Builder friendsAlert = new AlertDialog.Builder(FriendsDetailActivity.this);
                    friendsAlert.setTitle("İsteği İptal Et");
                    friendsAlert.setPositiveButton("Onayla", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            firebaseFirestore.collection("User").document(currentEmail).collection("FriendsRequest").document(friendsemailIntent).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {


                                    Toast.makeText(FriendsDetailActivity.this,"Arkadaşlık isteği iptal edildi.",Toast.LENGTH_LONG).show();
                                    friendsButton.setText("Arkadaş Ekle");


                                }

                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(FriendsDetailActivity.this,"İnternet bağlantısında bir problem var.",Toast.LENGTH_LONG).show();

                                }
                            });

                        }
                    });

                    friendsAlert.setNegativeButton("Vazgeç", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    friendsAlert.show();


                }


            }
        });


    }


    public void getFriends () {


        if (friendsInfoIntent.equals("Friends")) {

            binding.friendAddButton.setText("Arkadaşsınız");

            firebaseFirestore.collection("User").document(friendsemailIntent).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                    if (task.isSuccessful()) {

                        DocumentSnapshot document = task.getResult();

                        if (document.exists()) {

                            friendsUserName = (String) document.get("userName");
                            binding.usernameTexView.setText(friendsUserName);
                            friendsName = (String) document.get("name");
                            friendsSurname = (String) document.get("surName");

                            StringBuilder ns = new StringBuilder();
                            ns.append(friendsName);
                            ns.append(" ");
                            ns.append(friendsSurname);

                            binding.nameSurnameTextView.setText(ns.toString());

                            friendsImage = (String) document.get("image");

                            if (friendsImage.equals("")) {

                                binding.ppImageView.setImageResource(R.drawable.ic_play);

                            } else {

                                Picasso.get().load(friendsImage).into(binding.ppImageView);

                            }

                        } else {
                            System.out.println("Olumsuz");
                        }

                    } else {

                        System.out.println("cekme başarısız");
                    }
                }
            });


        } else if (friendsInfoIntent.equals("FriendsRequest")) {

            binding.friendAddButton.setText("Cevapla");

            firebaseFirestore.collection("User").document(friendsemailIntent).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                    if (task.isSuccessful()) {

                        DocumentSnapshot document = task.getResult();

                        if (document.exists()) {

                            friendsUserName = (String) document.get("userName");
                            binding.usernameTexView.setText(friendsUserName);
                            friendsName = (String) document.get("name");
                            friendsSurname = (String) document.get("surName");

                            StringBuilder ns = new StringBuilder();
                            ns.append(friendsName);
                            ns.append(" ");
                            ns.append(friendsSurname);

                            binding.nameSurnameTextView.setText(ns.toString());

                            friendsRequesImage = (String) document.get("image");

                            if (friendsRequesImage.equals("")) {

                                binding.ppImageView.setImageResource(R.drawable.ic_menu_toolbar);

                            } else {

                                Picasso.get().load(friendsRequesImage).into(binding.ppImageView);

                            }

                        } else {
                            System.out.println("Olumsuz");
                        }

                    } else {

                        System.out.println("cekme başarısız");
                    }
                }
            });


        } else if (friendsInfoIntent.equals("FriendsSearch")) {


            firebaseFirestore.collection("User").document(currentEmail).collection("Friends").addSnapshotListener(new EventListener<QuerySnapshot>() {

                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                    if (error != null) {

                        Toast.makeText(FriendsDetailActivity.this, "İnternet bağlantısında bir problem var", Toast.LENGTH_LONG).show();

                    }

                    if (value != null) {

                        for (DocumentSnapshot snapshot : value.getDocuments()) {

                            Map<String, Object> data = snapshot.getData();

                            String emailList = (String) data.get("email");


                            oldFriendsListArray.add(emailList);

                            System.out.println("email friends" + oldFriendsListArray);

                        }

                            if (oldFriendsListArray.contains(friendsemailIntent)) {

                                binding.friendAddButton.setText("Arkadaşsınız");

                                firebaseFirestore.collection("User").document(friendsemailIntent).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                                        if (task.isSuccessful()) {

                                            DocumentSnapshot document = task.getResult();

                                            if (document.exists()) {

                                                friendsUserName = (String) document.get("userName");
                                                binding.usernameTexView.setText(friendsUserName);
                                                friendsName = (String) document.get("name");
                                                friendsSurname = (String) document.get("surName");

                                                StringBuilder ns = new StringBuilder();
                                                ns.append(friendsName);
                                                ns.append(" ");
                                                ns.append(friendsSurname);

                                                binding.nameSurnameTextView.setText(ns.toString());

                                                friendsSearchImage = (String) document.get("image");

                                                if (friendsSearchImage.equals("")) {

                                                    binding.ppImageView.setImageResource(R.drawable.ic_menu_toolbar);

                                                } else {

                                                    Picasso.get().load(friendsSearchImage).into(binding.ppImageView);

                                                }

                                            } else {
                                                System.out.println("Olumsuz");
                                            }

                                        } else {

                                            System.out.println("cekme başarısız");
                                        }
                                    }
                                });

                            } else {


                                System.out.println("email burası devrede");

                                firebaseFirestore.collection("User").document(currentEmail).collection("FriendsRequest").addSnapshotListener(new EventListener<QuerySnapshot>() {

                                    @Override
                                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                                        if (error != null) {

                                            Toast.makeText(FriendsDetailActivity.this, "İnternet bağlantısında bir problem var", Toast.LENGTH_LONG).show();

                                        }

                                        if (value != null) {

                                            for (DocumentSnapshot snapshot : value.getDocuments()) {

                                                Map<String, Object> data = snapshot.getData();

                                                String emailList = (String) data.get("email");


                                                oldFriendsMyRequestListArray.add(emailList);

                                                System.out.println("email friends" + oldFriendsMyRequestListArray);

                                            }

                                                if (oldFriendsMyRequestListArray.contains(friendsemailIntent)) {

                                                    binding.friendAddButton.setText("Cevapla");

                                                    firebaseFirestore.collection("User").document(friendsemailIntent).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                                                            if (task.isSuccessful()) {

                                                                DocumentSnapshot document = task.getResult();

                                                                if (document.exists()) {

                                                                    friendsUserName = (String) document.get("userName");
                                                                    binding.usernameTexView.setText(friendsUserName);
                                                                    friendsName = (String) document.get("name");
                                                                    friendsSurname = (String) document.get("surName");

                                                                    StringBuilder ns = new StringBuilder();
                                                                    ns.append(friendsName);
                                                                    ns.append(" ");
                                                                    ns.append(friendsSurname);

                                                                    binding.nameSurnameTextView.setText(ns.toString());

                                                                    friendsSearchImage = (String) document.get("image");

                                                                    if (friendsSearchImage.equals("")) {

                                                                        binding.ppImageView.setImageResource(R.drawable.ic_menu_toolbar);

                                                                    } else {

                                                                        Picasso.get().load(friendsSearchImage).into(binding.ppImageView);

                                                                    }

                                                                } else {
                                                                    System.out.println("Olumsuz");
                                                                }

                                                            } else {

                                                                System.out.println("cekme başarısız");
                                                            }
                                                        }
                                                    });

                                                } else {

                                                    System.out.println("email arkadas olarak eklenmemis");


                                                    System.out.println("email burası devrede");

                                                    firebaseFirestore.collection("User").document(friendsemailIntent).collection("FriendsRequest").addSnapshotListener(new EventListener<QuerySnapshot>() {

                                                        @Override
                                                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                                                            if (error != null) {

                                                                Toast.makeText(FriendsDetailActivity.this, "İnternet bağlantısında bir problem var", Toast.LENGTH_LONG).show();

                                                            }

                                                            if (value != null) {

                                                                for (DocumentSnapshot snapshot : value.getDocuments()) {

                                                                    Map<String, Object> data = snapshot.getData();

                                                                    String emailList = (String) data.get("email");


                                                                    oldFriendsRequestListArray.add(emailList);
                                                                }

                                                                    System.out.println("email friends" + oldFriendsRequestListArray);

                                                                    if (oldFriendsRequestListArray.contains(friendsemailIntent)) {

                                                                        binding.friendAddButton.setText("Arkadaşlık İsteği Gönderildi");

                                                                        firebaseFirestore.collection("User").document(friendsemailIntent).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                            @Override
                                                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                                                                                if (task.isSuccessful()) {

                                                                                    DocumentSnapshot document = task.getResult();

                                                                                    if (document.exists()) {

                                                                                        friendsUserName = (String) document.get("userName");
                                                                                        binding.usernameTexView.setText(friendsUserName);
                                                                                        friendsName = (String) document.get("name");
                                                                                        friendsSurname = (String) document.get("surName");

                                                                                        StringBuilder ns = new StringBuilder();
                                                                                        ns.append(friendsName);
                                                                                        ns.append(" ");
                                                                                        ns.append(friendsSurname);

                                                                                        binding.nameSurnameTextView.setText(ns.toString());

                                                                                        friendsSearchImage = (String) document.get("image");

                                                                                        if (friendsSearchImage.equals("")) {

                                                                                            binding.ppImageView.setImageResource(R.drawable.ic_menu_toolbar);

                                                                                        } else {

                                                                                            Picasso.get().load(friendsSearchImage).into(binding.ppImageView);

                                                                                        }

                                                                                    } else {
                                                                                        System.out.println("Olumsuz");
                                                                                    }

                                                                                } else {

                                                                                    System.out.println("cekme başarısız");
                                                                                }
                                                                            }
                                                                        });

                                                                    } else {

                                                                        System.out.println("email arkadas olarak eklenmemis");

                                                                        System.out.println("karşı tarafın istek listesi bos");

                                                                        binding.friendAddButton.setText("İstek Gönder");

                                                                        firebaseFirestore.collection("User").document(friendsemailIntent).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                            @Override
                                                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                                                                                if (task.isSuccessful()) {

                                                                                    DocumentSnapshot document = task.getResult();

                                                                                    if (document.exists()) {

                                                                                        friendsUserName = (String) document.get("userName");
                                                                                        binding.usernameTexView.setText(friendsUserName);
                                                                                        friendsName = (String) document.get("name");
                                                                                        friendsSurname = (String) document.get("surName");

                                                                                        StringBuilder ns = new StringBuilder();
                                                                                        ns.append(friendsName);
                                                                                        ns.append(" ");
                                                                                        ns.append(friendsSurname);

                                                                                        binding.nameSurnameTextView.setText(ns.toString());

                                                                                        friendsSearchImage = (String) document.get("image");

                                                                                        if (friendsSearchImage.equals("")) {

                                                                                            binding.ppImageView.setImageResource(R.drawable.ic_menu_toolbar);

                                                                                        } else {

                                                                                            Picasso.get().load(friendsSearchImage).into(binding.ppImageView);

                                                                                        }

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


                                                            if (value.isEmpty()) {


                                                                System.out.println("karşı tarafın istek listesi bos");

                                                                binding.friendAddButton.setText("İstek Gönder");

                                                                firebaseFirestore.collection("User").document(friendsemailIntent).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                                                                        if (task.isSuccessful()) {

                                                                            DocumentSnapshot document = task.getResult();

                                                                            if (document.exists()) {

                                                                                friendsUserName = (String) document.get("userName");
                                                                                binding.usernameTexView.setText(friendsUserName);
                                                                                friendsName = (String) document.get("name");
                                                                                friendsSurname = (String) document.get("surName");

                                                                                StringBuilder ns = new StringBuilder();
                                                                                ns.append(friendsName);
                                                                                ns.append(" ");
                                                                                ns.append(friendsSurname);

                                                                                binding.nameSurnameTextView.setText(ns.toString());

                                                                                friendsSearchImage = (String) document.get("image");

                                                                                if (friendsSearchImage.equals("")) {

                                                                                    binding.ppImageView.setImageResource(R.drawable.ic_menu_toolbar);

                                                                                } else {

                                                                                    Picasso.get().load(friendsSearchImage).into(binding.ppImageView);

                                                                                }

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

                                                    });


                                                }



                                        }


                                        if (value.isEmpty()) {


                                            System.out.println("karşı tarafın istek listesi bos");

                                            binding.friendAddButton.setText("İstek Gönder");

                                            firebaseFirestore.collection("User").document(friendsemailIntent).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                                                    if (task.isSuccessful()) {

                                                        DocumentSnapshot document = task.getResult();

                                                        if (document.exists()) {

                                                            friendsUserName = (String) document.get("userName");
                                                            binding.usernameTexView.setText(friendsUserName);
                                                            friendsName = (String) document.get("name");
                                                            friendsSurname = (String) document.get("surName");

                                                            StringBuilder ns = new StringBuilder();
                                                            ns.append(friendsName);
                                                            ns.append(" ");
                                                            ns.append(friendsSurname);

                                                            binding.nameSurnameTextView.setText(ns.toString());

                                                            friendsSearchImage = (String) document.get("image");

                                                            if (friendsSearchImage.equals("")) {

                                                                binding.ppImageView.setImageResource(R.drawable.ic_menu_toolbar);

                                                            } else {

                                                                Picasso.get().load(friendsSearchImage).into(binding.ppImageView);

                                                            }

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

                                });

                            }


                    }
                }
            });

        }
    }




    public void getProfil () {



        DocumentReference usdRef = firebaseFirestore.collection("User").document(currentEmail);
        usdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        userName = (String) document.get("userName");
                        name = (String) document.get("name");
                        surname = (String) document.get("surName");
                        image = (String) document.get("image");


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