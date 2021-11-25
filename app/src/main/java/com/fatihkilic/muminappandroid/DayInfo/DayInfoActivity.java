package com.fatihkilic.muminappandroid.DayInfo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_info);

        binding = ActivityDayInfoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mealOfDayModelArrayList = new ArrayList<>();

        firebaseFirestore = FirebaseFirestore.getInstance();

        getMealOfTheDay();






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

                        int No = (Integer) data.get("No");
                        String eatMaterials = (String) data.get("eatMaterials");
                        String eatName = (String) data.get("eatName");
                        String eatPerson = (String) data.get("eatPerson");
                        String eatRecipe = (String) data.get("eatRecipe");
                        String eatTime = (String) data.get("eatTime");
                        String eatUrl = (String) data.get("eatUrl");
                        String eatImage = (String) data.get("eatImage");

                        mealOfDayModel mealOfDay = new mealOfDayModel(No,eatMaterials,eatName,eatPerson,eatRecipe,eatTime,eatUrl,eatImage);

                        mealOfDayModelArrayList.add(mealOfDay);


                    }

                }

            }
        });

    }
}