package com.fatihkilic.muminappandroid.DayInfo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityDayInfoBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityZekatMatikBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class DayInfoActivity extends AppCompatActivity {

    private ActivityDayInfoBinding binding;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<mealOfDayModel> mealOfDayModelArrayList;
    mealOfDayAdapter mealOfDayAdapter;
    String getDetailınfo;


    ArrayList<todayInHistoryModel> todayInHistoryArrayList;
    todayInHistoryAdapter todayInHistoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_info);

        binding = ActivityDayInfoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mealOfDayModelArrayList = new ArrayList<>();
        todayInHistoryArrayList = new ArrayList<>();

        firebaseFirestore = FirebaseFirestore.getInstance();

        Intent getDetailınfoIntent = getIntent();
        getDetailınfo = getDetailınfoIntent.getStringExtra("DayInfoPageInfo");

        if (getDetailınfo.equals("Meal")) {

            binding.MealRecyclerView.setVisibility(View.VISIBLE);
            binding.todayInHistoryRecylerView.setVisibility(View.INVISIBLE);

        } else if (getDetailınfo.equals("Tih")) {

            binding.todayInHistoryRecylerView.setVisibility(View.VISIBLE);
            binding.MealRecyclerView.setVisibility(View.INVISIBLE);

        }

        getMealOfTheDay();
        binding.MealRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mealOfDayAdapter = new mealOfDayAdapter(mealOfDayModelArrayList);
        binding.MealRecyclerView.setAdapter(mealOfDayAdapter);

        getTodayInHistory();
        binding.todayInHistoryRecylerView.setLayoutManager(new LinearLayoutManager(this));
        todayInHistoryAdapter = new todayInHistoryAdapter(todayInHistoryArrayList);
        binding.todayInHistoryRecylerView.setAdapter(todayInHistoryAdapter);









    }


    public void getMealOfTheDay () {

        firebaseFirestore.collection("DayInfo").document("Info").collection("recipe").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    System.out.println("Yemekleri çekemiyor");

                }

                if (value != null) {

                    for (DocumentSnapshot snapshot : value.getDocuments()) {

                        Map<String, Object> data = snapshot.getData();


                        String eatMaterials = (String) data.get("eatMaterials");
                        String eatName = (String) data.get("eatName");
                        String eatPerson = (String) data.get("eatPerson");
                        String eatRecipe = (String) data.get("eatRecipe");
                        String eatTime = (String) data.get("eatTime");
                        String eatUrl = (String) data.get("eatUrl");
                        String eatImage = (String) data.get("eatImage");

                        mealOfDayModel mealOfDay = new mealOfDayModel(eatMaterials,eatName,eatPerson,eatRecipe,eatTime,eatUrl,eatImage);

                        mealOfDayModelArrayList.add(mealOfDay);


                    }

                    mealOfDayAdapter.notifyDataSetChanged();

                }

            }
        });

    }


    public void getTodayInHistory () {


        firebaseFirestore.collection("DayInfo").document("Info").collection("todayInHistory").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    System.out.println("Tarihi Olayları çekemiyor");

                }

                if (value != null) {

                    for (DocumentSnapshot snapshot : value.getDocuments()) {

                        Map<String, Object> data = snapshot.getData();


                        String tihDescription = (String) data.get("tihDescription");
                        String tihYear = (String) data.get("tihYear");



                        todayInHistoryModel todayInHistory = new todayInHistoryModel(tihDescription,tihYear);

                        todayInHistoryArrayList.add(todayInHistory);


                    }

                    todayInHistoryAdapter.notifyDataSetChanged();

                }

            }
        });



    }
}