package com.fatihkilic.muminappandroid.Sehirler;

import android.content.res.Resources;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.Ulkeler.UlkelerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SehirlerService {



    @GET("sehirler/{sehirID}")
    Call<List<SehirlerModel>> getSehirlerData(@Path("sehirID") String sehirID);

}
