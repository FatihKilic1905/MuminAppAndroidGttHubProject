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
import com.google.android.gms.tasks.OnSuccessListener;
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

    String myUserName;
    String myName;
    String mySurname;
    String myImage;
    String myCurrentEmail;

    String friendsUserName;
    String friendsName;
    String friendsSurname;
    String friendsImage;


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


        oldFriendsListArray = new ArrayList<String>();
        oldFriendsRequestListArray = new ArrayList<String>();
        oldFriendsMyRequestListArray = new ArrayList<String>();

        myCurrentEmail = auth.getCurrentUser().getEmail();

        Intent friendsInfoIntentCome = getIntent();
        friendsInfoIntent = friendsInfoIntentCome.getStringExtra("FriendsInfo");
        friendsemailIntent = friendsInfoIntentCome.getStringExtra("FriendsEmail");

        System.out.println("friendss" + friendsInfoIntent);
        System.out.println("friends " + friendsemailIntent);

        getProfil();
        getFriends();
        getFriendsProfil();


        Button friendsButton = binding.friendAddButton;
        friendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String buttonAddLabel = binding.friendAddButton.getText().toString();


                if (buttonAddLabel.equals("Arkadaşsınız")) {


                    AlertDialog.Builder friendsAlert = new AlertDialog.Builder(FriendsDetailActivity.this);
                    friendsAlert.setTitle("Arkadaşlarımdan çıkar");
                    friendsAlert.setPositiveButton("Onayla", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            firebaseFirestore.collection("User").document(myCurrentEmail).collection("Friends").document(friendsemailIntent).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {


                                    firebaseFirestore.collection("User").document(friendsemailIntent).collection("Friends").document(myCurrentEmail).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            Toast.makeText(FriendsDetailActivity.this, "Kişi arkadaş listenizden çıkarıldı.", Toast.LENGTH_LONG).show();
                                            friendsButton.setText("İstek Gönder");

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                            Toast.makeText(FriendsDetailActivity.this, "İnternet bağlantısında bir problem var.", Toast.LENGTH_LONG).show();

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


                } else if (buttonAddLabel.equals("Arkadaşlık İsteği Gönderildi")) {


                    AlertDialog.Builder friendsAlert = new AlertDialog.Builder(FriendsDetailActivity.this);
                    friendsAlert.setTitle("İsteği İptal Et");
                    friendsAlert.setPositiveButton("Onayla", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            firebaseFirestore.collection("User").document(friendsemailIntent).collection("FriendsRequest").document(myCurrentEmail).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {


                                    Toast.makeText(FriendsDetailActivity.this, "Arkadaşlık isteği iptal edildi.", Toast.LENGTH_LONG).show();
                                    friendsButton.setText("İstek Gönder");


                                }

                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(FriendsDetailActivity.this, "İnternet bağlantısında bir problem var.", Toast.LENGTH_LONG).show();

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


                } else if (buttonAddLabel.equals("Cevapla")) {


                    AlertDialog.Builder friendsAlert = new AlertDialog.Builder(FriendsDetailActivity.this);
                    friendsAlert.setTitle("Arkadaşlık isteği onayla");
                    friendsAlert.setPositiveButton("Kabul Et", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                            // Karsı taraf profil kendine


                            HashMap<String, Object> friendsData = new HashMap<>();
                            friendsData.put("email", friendsemailIntent);
                            friendsData.put("userName", friendsUserName);
                            friendsData.put("name", friendsName);
                            friendsData.put("surName", friendsSurname);
                            friendsData.put("image", friendsImage);


                            firebaseFirestore.collection("User").document(myCurrentEmail).collection("Friends").document(friendsemailIntent).set(friendsData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {


                                    // kendi profilim karsı tarafa

                                    HashMap<String, Object> myProfileData = new HashMap<>();
                                    myProfileData.put("email", myCurrentEmail);
                                    myProfileData.put("userName", myUserName);
                                    myProfileData.put("name", myName);
                                    myProfileData.put("surName", mySurname);
                                    myProfileData.put("image", myImage);


                                    firebaseFirestore.collection("User").document(friendsemailIntent).collection("Friends").document(myCurrentEmail).set(myProfileData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {


                                            // bildirim gonder karsı tarafa

                                            firebaseFirestore.collection("OneSignal").whereEqualTo("email", friendsemailIntent).addSnapshotListener(new EventListener<QuerySnapshot>() {
                                                @Override
                                                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                                                    if (error != null) {

                                                        Toast.makeText(FriendsDetailActivity.this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                                                    }

                                                    if (value != null) {

                                                        for (DocumentSnapshot snapshot : value.getDocuments()) {

                                                            Map<String, Object> data = snapshot.getData();

                                                            String playeridDocumentId = snapshot.getId();
                                                            String PlayerIdFirebase = (String) data.get("player_id");

                                                            StringBuilder mesaj = new StringBuilder();
                                                            mesaj.append(myUserName);
                                                            mesaj.append(" ");
                                                            mesaj.append("arkadaşlık isteğini kabul etti.");


                                                            try {
                                                                OneSignal.postNotification(new JSONObject("{'contents': {'en':'" + mesaj.toString() + "'}, 'include_player_ids': ['" + PlayerIdFirebase + "']}"), null);
                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }

                                                        }

                                                        friendsButton.setText("Arkadaşsınız");


                                                        firebaseFirestore.collection("User").document(myCurrentEmail).collection("FriendsRequest").document(friendsemailIntent).delete();


                                                    }
                                                }
                                            });


                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                            Toast.makeText(FriendsDetailActivity.this, "İnternet bağlantısında bir problem var", Toast.LENGTH_LONG).show();

                                        }
                                    });


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(FriendsDetailActivity.this, "İnternet bağlantısında bir problem var", Toast.LENGTH_LONG).show();

                                }
                            });

                        }
                    });


                    friendsAlert.setNegativeButton("Reddet", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            firebaseFirestore.collection("User").document(myCurrentEmail).collection("FriendsRequest").document(friendsemailIntent).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(@NonNull Void unused) {

                                    binding.friendAddButton.setText("İstek Gönder");
                                    onBackPressed();



                                }
                            });

                        }
                    });


                    friendsAlert.show();


                } else if (buttonAddLabel.equals("İstek Gönder")) {


                    AlertDialog.Builder istekGonderAlert = new AlertDialog.Builder(FriendsDetailActivity.this);
                    istekGonderAlert.setTitle("Uyarı");
                    istekGonderAlert.setMessage(friendsUserName + "adlı kullanıcıya arkadaşlık isteği gönderilecek!");
                    istekGonderAlert.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                            HashMap<String, Object> searchData = new HashMap<>();
                            searchData.put("email", myCurrentEmail);
                            searchData.put("userName", myUserName);
                            searchData.put("name", myName);
                            searchData.put("surName", mySurname);
                            searchData.put("image", myImage);

                            firebaseFirestore.collection("User").document(friendsemailIntent).collection("FriendsRequest").document(myCurrentEmail).set(searchData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    firebaseFirestore.collection("OneSignal").whereEqualTo("email", friendsemailIntent).addSnapshotListener(new EventListener<QuerySnapshot>() {
                                        @Override
                                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                                            if (error != null) {

                                                Toast.makeText(FriendsDetailActivity.this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                                            }

                                            if (value != null) {

                                                for (DocumentSnapshot snapshot : value.getDocuments()) {

                                                    Map<String, Object> data = snapshot.getData();

                                                    String playeridDocumentId = snapshot.getId();
                                                    String PlayerIdFirebase = (String) data.get("player_id");

                                                    StringBuilder mesaj = new StringBuilder();
                                                    mesaj.append(myUserName);
                                                    mesaj.append(" ");
                                                    mesaj.append("adlı kullanıcı seninle arkadaş olmak istiyor.");


                                                    try {
                                                        OneSignal.postNotification(new JSONObject("{'contents': {'en':'" + mesaj.toString() + "'}, 'include_player_ids': ['" + PlayerIdFirebase + "']}"), null);
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }

                                                }

                                                friendsButton.setText("Arkadaşlık İsteği Gönderildi");

                                                Toast.makeText(FriendsDetailActivity.this, "Arkadaşlık isteği gönderildi.", Toast.LENGTH_LONG).show();


                                            }
                                        }
                                    });

                                }
                            });


                        }
                    });

                    istekGonderAlert.setNegativeButton("Vazgeç", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    istekGonderAlert.show();


                }


            }
        });


    }


    public void getFriends() {


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

                            friendsImage = (String) document.get("image");

                            if (friendsImage.equals("")) {

                                binding.ppImageView.setImageResource(R.drawable.ic_menu_toolbar);

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


        } else if (friendsInfoIntent.equals("FriendsSearch")) {


            firebaseFirestore.collection("User").document(myCurrentEmail).collection("Friends").addSnapshotListener(new EventListener<QuerySnapshot>() {

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

                                            friendsImage = (String) document.get("image");

                                            if (friendsImage.equals("")) {

                                                binding.ppImageView.setImageResource(R.drawable.ic_menu_toolbar);

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

                        } else {


                            System.out.println("email burası devrede");

                            firebaseFirestore.collection("User").document(myCurrentEmail).collection("FriendsRequest").addSnapshotListener(new EventListener<QuerySnapshot>() {

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

                                                            friendsImage = (String) document.get("image");

                                                            if (friendsImage.equals("")) {

                                                                binding.ppImageView.setImageResource(R.drawable.ic_menu_toolbar);

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

                                        } else {


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

                                                        if (oldFriendsRequestListArray.contains(myCurrentEmail)) {

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

                                                                            friendsImage = (String) document.get("image");

                                                                            if (friendsImage.equals("")) {

                                                                                binding.ppImageView.setImageResource(R.drawable.ic_menu_toolbar);

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

                                                        } else {


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

                                                                            friendsImage = (String) document.get("image");

                                                                            if (friendsImage.equals("")) {

                                                                                binding.ppImageView.setImageResource(R.drawable.ic_menu_toolbar);

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

                                                                        friendsImage = (String) document.get("image");

                                                                        if (friendsImage.equals("")) {

                                                                            binding.ppImageView.setImageResource(R.drawable.ic_menu_toolbar);

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

                                                        friendsImage = (String) document.get("image");

                                                        if (friendsImage.equals("")) {

                                                            binding.ppImageView.setImageResource(R.drawable.ic_menu_toolbar);

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


                                    }

                                }

                            });

                        }


                    }
                }
            });

        }
    }


    public void getProfil() {


        DocumentReference usdRef = firebaseFirestore.collection("User").document(myCurrentEmail);
        usdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        myUserName = (String) document.get("userName");
                        myName = (String) document.get("name");
                        mySurname = (String) document.get("surName");
                        myImage = (String) document.get("image");


                    } else {
                        System.out.println("Olumsuz");
                    }

                } else {

                    System.out.println("cekme başarısız");
                }

            }


        });


    }


    public void getFriendsProfil() {


        DocumentReference usdRef = firebaseFirestore.collection("User").document(friendsemailIntent);
        usdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        friendsUserName = (String) document.get("userName");
                        friendsName = (String) document.get("name");
                        friendsSurname = (String) document.get("surName");
                        friendsImage = (String) document.get("image");


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