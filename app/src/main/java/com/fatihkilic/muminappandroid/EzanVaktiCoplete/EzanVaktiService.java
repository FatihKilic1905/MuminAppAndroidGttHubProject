package com.fatihkilic.muminappandroid;

import com.fatihkilic.muminappandroid.EzanVaktiCoplete.EzanVakitleriModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EzanVaktiService {



    @GET("vakitler/{ilceID}")
    Call<List<EzanVakitleriModel>> getEzanData(@Path("ilceID") String ilceID);


}
