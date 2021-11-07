package com.fatihkilic.muminappandroid.Ilceler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.Sehirler.SehirlerAdapter;
import com.fatihkilic.muminappandroid.Sehirler.SehirlerModel;
import com.fatihkilic.muminappandroid.Sehirler.SehirlerPost;
import com.fatihkilic.muminappandroid.Sehirler.SehirlerService;
import com.fatihkilic.muminappandroid.databinding.ActivityIlcelerBinding;
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

public class IlcelerActivity extends AppCompatActivity {


    ArrayList<IlcelerModel> ilcelerModels;
    ArrayList<IlcelerPost> ilcelerPostArrayList;

    private String BaseUrl = "https://ezanvakti.herokuapp.com/";
    Retrofit retrofitIlce;

    private ActivityIlcelerBinding binding;


    IlcelerAdapter ilcelerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilceler);

        binding = ActivityIlcelerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Gson gsonIlce = new GsonBuilder().setLenient().create();

        retrofitIlce = new Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create(gsonIlce)).build();

        ilcelerPostArrayList = new ArrayList<>();

        loadilceler();

        binding.ilcelerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ilcelerAdapter = new IlcelerAdapter(ilcelerPostArrayList);
        binding.ilcelerRecyclerView.setAdapter(ilcelerAdapter);



    }


    private void loadilceler(){

        Intent sehirIDIntent = getIntent();
        String sehirIDGet = sehirIDIntent.getStringExtra("sehirID");


        IlcelerService ilcelerService = retrofitIlce.create(IlcelerService.class);
        Call<List<IlcelerModel>> call = ilcelerService.getılcelerData(sehirIDGet);
        call.enqueue(new Callback<List<IlcelerModel>>() {
            @Override
            public void onResponse(Call<List<IlcelerModel>> call, Response<List<IlcelerModel>> response) {

                if (response.isSuccessful()) {

                    List<IlcelerModel> responseList = response.body();
                    ilcelerModels = new ArrayList<>(responseList);



                    for (IlcelerModel ilcelerModel : ilcelerModels) {

                        IlcelerPost ilcelerPost = new IlcelerPost(ilcelerModel.IlceAdi,ilcelerModel.IlceAdiEn,ilcelerModel.IlceID);
                        ilcelerPostArrayList.add(ilcelerPost);



                    }


                    ilcelerAdapter.notifyDataSetChanged();


                }


            }

            @Override
            public void onFailure(Call<List<IlcelerModel>> call, Throwable t) {

                t.printStackTrace();

            }
        });



    }


}