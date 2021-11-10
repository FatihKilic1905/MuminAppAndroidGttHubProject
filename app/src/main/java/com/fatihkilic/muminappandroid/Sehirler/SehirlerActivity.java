package com.fatihkilic.muminappandroid.Sehirler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.Ulkeler.UlkeAdapter;
import com.fatihkilic.muminappandroid.Ulkeler.UlkelerModel;
import com.fatihkilic.muminappandroid.Ulkeler.UlkelerPost;
import com.fatihkilic.muminappandroid.databinding.ActivityKonumBinding;
import com.fatihkilic.muminappandroid.databinding.ActivitySehirlerBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SehirlerActivity extends AppCompatActivity {

    ArrayList<SehirlerModel> sehirlerModels;
    ArrayList<SehirlerPost> sehirlerPostArrayList;

    private String BaseUrl = "https://ezanvakti.herokuapp.com/";
    Retrofit retrofitSehir;

    private ActivitySehirlerBinding binding;

    SehirlerAdapter sehirlerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sehirler);

        binding = ActivitySehirlerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Gson gsonSehir = new GsonBuilder().setLenient().create();

        retrofitSehir = new Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create(gsonSehir)).build();

        sehirlerPostArrayList = new ArrayList<>();
        loadSehirler();

        binding.sehirlerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        sehirlerAdapter = new SehirlerAdapter(sehirlerPostArrayList);
        binding.sehirlerRecyclerView.setAdapter(sehirlerAdapter);



    }


    private void loadSehirler(){

        Intent sehirIDIntent = getIntent();
        String sehirIDGet = sehirIDIntent.getStringExtra("ulkeID");
        System.out.println("sehirid" + sehirIDGet);


        SehirlerService sehirlerService = retrofitSehir.create(SehirlerService.class);
        Call<List<SehirlerModel>> call = sehirlerService.getSehirlerData(sehirIDGet);
        call.enqueue(new Callback<List<SehirlerModel>>() {
            @Override
            public void onResponse(Call<List<SehirlerModel>> call, Response<List<SehirlerModel>> response) {

                if (response.isSuccessful()) {

                    List<SehirlerModel> responseList = response.body();
                    sehirlerModels = new ArrayList<>(responseList);



                    for (SehirlerModel sehirlerModel : sehirlerModels) {

                        SehirlerPost sehirlerPost = new SehirlerPost(sehirlerModel.SehirAdi,sehirlerModel.SehirAdiEn,sehirlerModel.SehirID);
                        sehirlerPostArrayList.add(sehirlerPost);



                    }


                    sehirlerAdapter.notifyDataSetChanged();


                }


            }

            @Override
            public void onFailure(Call<List<SehirlerModel>> call, Throwable t) {

                t.printStackTrace();

            }
        });



    }
}