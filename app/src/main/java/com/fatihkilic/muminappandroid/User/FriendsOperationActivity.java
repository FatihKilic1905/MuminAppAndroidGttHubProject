package com.fatihkilic.muminappandroid.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityFriendsOperationBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityMyAccountBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class FriendsOperationActivity extends AppCompatActivity {


    private ActivityFriendsOperationBinding binding;

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;

    String currentEmail;

    String userName;
    String name;
    String surname;
    String image;

    ArrayList<ModelGetFriends> modelGetFriendsArrayList;
    AdapterGetFriends getFriendsPostAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_operation);

        binding = ActivityFriendsOperationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        modelGetFriendsArrayList = new ArrayList<>();

        currentEmail = auth.getCurrentUser().getEmail();

        getfriends();

        binding.getFriendsRecylerView.setLayoutManager(new LinearLayoutManager(this));
        getFriendsPostAdapter = new AdapterGetFriends(modelGetFriendsArrayList);
        binding.getFriendsRecylerView.setAdapter(getFriendsPostAdapter);


    }


    public void getfriends() {


        firebaseFirestore.collection("User").document(currentEmail).collection("Friends").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    Toast.makeText(FriendsOperationActivity.this,"İnternet bağlantısında bir problem var",Toast.LENGTH_LONG).show();

                }

                if (value != null) {

                    for (DocumentSnapshot snapshot : value.getDocuments()) {

                        Map<String, Object> data = snapshot.getData();

                        String frienduserName = (String) data.get("userName");
                        String frinedsName = (String) data.get("name");
                        String friendsSurName = (String) data.get("surName");
                        String friendsImage = (String) data.get("image");
                        String friendsEmail = (String) data.get("email");

                        ModelGetFriends modelGetFriends = new ModelGetFriends(frienduserName, frinedsName,friendsSurName,friendsImage,friendsEmail);
                        modelGetFriendsArrayList.add(modelGetFriends);


                    }


                    getFriendsPostAdapter.notifyDataSetChanged();

                }

            }
        });

    }



    public void getFriendsRequest () {

        firebaseFirestore.collection("User").document(currentEmail).collection("FriendsRequest").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    Toast.makeText(FriendsOperationActivity.this,"İnternet bağlantısında bir problem var",Toast.LENGTH_LONG).show();

                }

                if (value != null) {

                    for (DocumentSnapshot snapshot : value.getDocuments()) {

                        Map<String, Object> data = snapshot.getData();

                        String friendRequestuserName = (String) data.get("userName");
                        String frinedsRequestName = (String) data.get("name");
                        String friendsRequestSurName = (String) data.get("surName");
                        String friendsRequestImage = (String) data.get("image");
                        String friendsRequestEmail = (String) data.get("email");


                    }

                }

            }
        });



    }


    public void getAddFriends () {

        firebaseFirestore.collection("User").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    Toast.makeText(FriendsOperationActivity.this,"İnternet bağlantısında bir problem var",Toast.LENGTH_LONG).show();

                }

                if (value != null) {

                    for (DocumentSnapshot snapshot : value.getDocuments()) {

                        Map<String, Object> data = snapshot.getData();

                        String userUserName = (String) data.get("userName");
                        String userName = (String) data.get("name");
                        String userSurName = (String) data.get("surName");
                        String userImageImage = (String) data.get("image");
                        String userEmail = (String) data.get("email");


                    }

                }

            }
        });



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