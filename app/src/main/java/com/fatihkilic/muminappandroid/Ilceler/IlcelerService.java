package com.fatihkilic.muminappandroid.Ilceler;

import com.fatihkilic.muminappandroid.Sehirler.SehirlerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IlcelerService {

    @GET("ilceler/{ilceID}")
    Call<List<IlcelerModel>> getılcelerData(@Path("ilceID") String ilceID);


}
