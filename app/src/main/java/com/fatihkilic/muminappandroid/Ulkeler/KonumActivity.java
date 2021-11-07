package com.fatihkilic.muminappandroid.Ulkeler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.fatihkilic.muminappandroid.EzanVaktiCoplete.EzanVakitleriModel;
import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityKonumBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KonumActivity extends AppCompatActivity {



    ArrayList<EzanVakitleriModel> ezanVakitleriModels;
    private String BaseUrl = "https://ezanvakti.herokuapp.com/";

    ArrayList<UlkelerModel> ulkelerModels;
    ArrayList<UlkelerPost> ulkelerPostArrayList;

    Retrofit retrofit;

    UlkeAdapter ulkeAdapter;

    private ActivityKonumBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konum);

        binding = ActivityKonumBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);





        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create(gson)).build();

        ulkelerPostArrayList = new ArrayList<>();
        loadUlkeler();

        binding.ulkelerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ulkeAdapter = new UlkeAdapter(ulkelerPostArrayList);
        binding.ulkelerRecyclerView.setAdapter(ulkeAdapter);




    }







    private void  loadUlkeler() {

        UlkelerService ulkelerService = retrofit.create(UlkelerService.class);
        Call<List<UlkelerModel>> call = ulkelerService.getUlkelerData();

        call.enqueue(new Callback<List<UlkelerModel>>() {
            @Override
            public void onResponse(Call<List<UlkelerModel>> call, Response<List<UlkelerModel>> response) {

                if (response.isSuccessful()) {

                    List<UlkelerModel> responseList = response.body();
                    ulkelerModels = new ArrayList<>(responseList);



                    for (UlkelerModel ulkelerModel : ulkelerModels) {

                        UlkelerPost ulkelerPost = new UlkelerPost(ulkelerModel.UlkeAdi,ulkelerModel.UlkeAdiEn,ulkelerModel.UlkeID);
                        ulkelerPostArrayList.add(ulkelerPost);

                        System.out.println(ulkelerModel.UlkeAdi);
                        System.out.println(ulkelerModel.UlkeAdiEn);
                        System.out.println(ulkelerModel.UlkeID);

                    }


                    ulkeAdapter.notifyDataSetChanged();


                }



            }

            @Override
            public void onFailure(Call<List<UlkelerModel>> call, Throwable t) {

                t.printStackTrace();

            }
        });



    }







}