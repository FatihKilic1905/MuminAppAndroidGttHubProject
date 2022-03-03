package com.fatihkilic.muminappandroid.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
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
    ArrayList<ModelGetFriendsRequest> modelGetFriendsRequestArrayList;
    AdapterGetFriends getFriendsPostAdapter;
    AdapterGetFriendsRequest getFriendsRequestadapter;


    ArrayList<String> searchUserNameArrayList;
    ArrayAdapter<String> searchAdapter;

    ArrayList<String> friendsRequestCount;

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
        modelGetFriendsRequestArrayList = new ArrayList<>();

        currentEmail = auth.getCurrentUser().getEmail();

        getfriends();
        getfriendsRequest();
        getAddFriends();

        friendsRequestCount = new ArrayList<>();


        binding.getFriendsRecylerView.setVisibility(View.VISIBLE);
        binding.getFriendsRequestRecylerView.setVisibility(View.INVISIBLE);
        binding.friendsSearchView.setVisibility(View.INVISIBLE);
        binding.friendsSearchListview.setVisibility(View.INVISIBLE);


        System.out.println("model array" + friendsRequestCount.size());


        searchUserNameArrayList = new ArrayList<String>();
        searchAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,searchUserNameArrayList);
        binding.friendsSearchListview.setAdapter(searchAdapter);

        binding.friendsSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                binding.friendsSearchListview.setVisibility(View.INVISIBLE);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                if (s.toString().equals("")){

                    binding.friendsSearchListview.setVisibility(View.INVISIBLE);

                    binding.uyariTextView.setVisibility(View.VISIBLE);

                } else {


                    binding.uyariTextView.setVisibility(View.INVISIBLE);
                    binding.friendsSearchListview.setVisibility(View.VISIBLE);
                    searchAdapter.getFilter().filter(s);

                }

                return false;
            }

        });


        ListView friendsSearcListView = binding.friendsSearchListview;
        friendsSearcListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String usename =  adapterView.getItemAtPosition(i).toString();

                System.out.println("email" + usename);

                firebaseFirestore.collection("User").whereEqualTo("userName", usename).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {

                            Toast.makeText(FriendsOperationActivity.this,error.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                        }

                        if (value != null) {

                            for (DocumentSnapshot snapshot : value.getDocuments()) {

                                Map<String, Object> data = snapshot.getData();

                                String playeridDocumentId = snapshot.getId();
                                String userEmail = (String) data.get("email");

                                System.out.println("email ADDD" + userEmail);

                                Intent friendsDetailIntent = new Intent(FriendsOperationActivity.this, FriendsDetailActivity.class);
                                friendsDetailIntent.putExtra("FriendsInfo", "FriendsSearch");
                                friendsDetailIntent.putExtra("FriendsEmail", userEmail);
                                startActivity(friendsDetailIntent);


                            }

                        }



                    }
                });

            }

        });






        binding.getFriendsRecylerView.setLayoutManager(new LinearLayoutManager(this));
        getFriendsPostAdapter = new AdapterGetFriends(modelGetFriendsArrayList);
        binding.getFriendsRecylerView.setAdapter(getFriendsPostAdapter);

        binding.getFriendsRequestRecylerView.setLayoutManager(new LinearLayoutManager(this));
        getFriendsRequestadapter = new AdapterGetFriendsRequest(modelGetFriendsRequestArrayList);
        binding.getFriendsRequestRecylerView.setAdapter(getFriendsRequestadapter);


        Button friendsButton = binding.friendsButton;
        friendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.getFriendsRecylerView.setVisibility(View.VISIBLE);
                binding.getFriendsRequestRecylerView.setVisibility(View.INVISIBLE);
                binding.friendsSearchView.setVisibility(View.INVISIBLE);
                binding.friendsSearchListview.setVisibility(View.INVISIBLE);



                if (modelGetFriendsRequestArrayList.size() > 0 ) {

                    binding.friendsButton.setBackground(getResources().getDrawable(R.drawable.corner_4_radius));
                    binding.friendsButton.setTextColor(getResources().getColor(R.color.white));
                    binding.isteklerButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner_red));
                    binding.isteklerButton.setTextColor(getResources().getColor(R.color.red));
                    binding.searchButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                    binding.searchButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                    binding.isteklerButton.setText("İstekler" + "(" + modelGetFriendsRequestArrayList.size() + ")");



                } else {


                    binding.friendsButton.setBackground(getResources().getDrawable(R.drawable.corner_4_radius));
                    binding.friendsButton.setTextColor(getResources().getColor(R.color.white));
                    binding.isteklerButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                    binding.isteklerButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                    binding.searchButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                    binding.searchButton.setTextColor(getResources().getColor(R.color.muminAppGreen));



                }

                if (modelGetFriendsArrayList.size() > 0) {

                    binding.uyariTextView.setVisibility(View.INVISIBLE);

                } else {

                    binding.uyariTextView.setVisibility(View.VISIBLE);
                    binding.uyariTextView.setText("Bu alanda arkadaşlarınızı görebilirsiniz.");


                }




            }
        });

        Button friendsRequestButton = binding.isteklerButton;
        friendsRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.getFriendsRecylerView.setVisibility(View.INVISIBLE);
                binding.getFriendsRequestRecylerView.setVisibility(View.VISIBLE);
                binding.friendsSearchView.setVisibility(View.INVISIBLE);
                binding.friendsSearchListview.setVisibility(View.INVISIBLE);



                if (modelGetFriendsRequestArrayList.size() > 0 ) {

                    binding.friendsButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                    binding.friendsButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                    binding.isteklerButton.setBackground(getResources().getDrawable(R.drawable.corner_4_radius_red));
                    binding.isteklerButton.setTextColor(getResources().getColor(R.color.white));
                    binding.searchButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                    binding.searchButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                    binding.isteklerButton.setText("İstekler" + "(" + modelGetFriendsRequestArrayList.size() + ")");



                } else {


                    binding.friendsButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                    binding.friendsButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                    binding.isteklerButton.setBackground(getResources().getDrawable(R.drawable.corner_4_radius));
                    binding.isteklerButton.setTextColor(getResources().getColor(R.color.white));
                    binding.searchButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                    binding.searchButton.setTextColor(getResources().getColor(R.color.muminAppGreen));



                }

                if (modelGetFriendsRequestArrayList.size() > 0) {

                    binding.uyariTextView.setVisibility(View.INVISIBLE);

                } else {

                    binding.uyariTextView.setVisibility(View.VISIBLE);
                    binding.uyariTextView.setText("Bu alanda arkadaşlık davetlerinizi görebilirsiniz.");


                }




            }
        });

        Button SearchButton = binding.searchButton;
        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                binding.getFriendsRecylerView.setVisibility(View.INVISIBLE);
                binding.getFriendsRequestRecylerView.setVisibility(View.INVISIBLE);
                binding.friendsSearchView.setVisibility(View.VISIBLE);
                binding.friendsSearchListview.setVisibility(View.INVISIBLE);

                binding.uyariTextView.setVisibility(View.VISIBLE);
                binding.uyariTextView.setText("Bu alanda arkadaşlarınızı arayıp arkadaş olarak ekleyebilirsiniz.");



                if (modelGetFriendsRequestArrayList.size() > 0 ) {

                    binding.friendsButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                    binding.friendsButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                    binding.isteklerButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner_red));
                    binding.isteklerButton.setTextColor(getResources().getColor(R.color.red));
                    binding.searchButton.setBackground(getResources().getDrawable(R.drawable.corner_4_radius));
                    binding.searchButton.setTextColor(getResources().getColor(R.color.white));
                    binding.isteklerButton.setText("İstekler" + "(" + modelGetFriendsRequestArrayList.size() + ")");



                } else {


                    binding.friendsButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                    binding.friendsButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                    binding.isteklerButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                    binding.isteklerButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                    binding.searchButton.setBackground(getResources().getDrawable(R.drawable.corner_4_radius));
                    binding.searchButton.setTextColor(getResources().getColor(R.color.white));



                }
















            }
        });





    }


    @Override
    protected void onResume() {
        super.onResume();

        getfriendsRequest();
        getfriends();
        getAddFriends();

        System.out.println("model array on resume" );

    }





    public void getfriends() {


        firebaseFirestore.collection("User").document(currentEmail).collection("Friends").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    Toast.makeText(FriendsOperationActivity.this,"İnternet bağlantısında bir problem var",Toast.LENGTH_LONG).show();

                }

                if (value != null) {

                    modelGetFriendsArrayList.clear();

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

                    if (modelGetFriendsRequestArrayList.size() > 0 ) {

                        binding.friendsButton.setBackground(getResources().getDrawable(R.drawable.corner_4_radius));
                        binding.friendsButton.setTextColor(getResources().getColor(R.color.white));
                        binding.isteklerButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner_red));
                        binding.isteklerButton.setTextColor(getResources().getColor(R.color.red));
                        binding.searchButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                        binding.searchButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                        binding.isteklerButton.setText("İstekler" + "(" + modelGetFriendsRequestArrayList.size() + ")");



                    } else {


                        binding.friendsButton.setBackground(getResources().getDrawable(R.drawable.corner_4_radius));
                        binding.friendsButton.setTextColor(getResources().getColor(R.color.white));
                        binding.isteklerButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                        binding.isteklerButton.setTextColor(getResources().getColor(R.color.muminAppGreen));
                        binding.searchButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner));
                        binding.searchButton.setTextColor(getResources().getColor(R.color.muminAppGreen));



                    }

                    if (modelGetFriendsArrayList.size() > 0) {

                        binding.uyariTextView.setVisibility(View.INVISIBLE);

                    } else {

                        binding.uyariTextView.setVisibility(View.VISIBLE);
                        binding.uyariTextView.setText("Bu alanda arkadaşlarınızı görebilirsiniz.");


                    }

                    getFriendsPostAdapter.notifyDataSetChanged();

                }

            }
        });

    }


    public void getfriendsRequest() {


        firebaseFirestore.collection("User").document(currentEmail).collection("FriendsRequest").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    Toast.makeText(FriendsOperationActivity.this,"İnternet bağlantısında bir problem var",Toast.LENGTH_LONG).show();

                }

                if (value != null) {

                    modelGetFriendsRequestArrayList.clear();

                    for (DocumentSnapshot snapshot : value.getDocuments()) {

                        Map<String, Object> data = snapshot.getData();

                        String frienduserName = (String) data.get("userName");
                        String frinedsName = (String) data.get("name");
                        String friendsSurName = (String) data.get("surName");
                        String friendsImage = (String) data.get("image");
                        String friendsEmail = (String) data.get("email");

                        ModelGetFriendsRequest modelGetFriendsRequest = new ModelGetFriendsRequest(frienduserName, frinedsName,friendsSurName,friendsImage,friendsEmail);
                        modelGetFriendsRequestArrayList.add(modelGetFriendsRequest);

                    }

                    getFriendsRequestadapter.notifyDataSetChanged();

                    if (modelGetFriendsRequestArrayList.size() > 0) {

                        binding.isteklerButton.setText("İstekler " + "(" + modelGetFriendsRequestArrayList.size() + ")");
                        binding.isteklerButton.setTextColor(Color.RED);
                        binding.isteklerButton.setBackground(getResources().getDrawable(R.drawable.layer_stroke_4_corner_red));

                    } else {





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

                        searchUserNameArrayList.add(userUserName);

                        System.out.println("usename" + searchUserNameArrayList);


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