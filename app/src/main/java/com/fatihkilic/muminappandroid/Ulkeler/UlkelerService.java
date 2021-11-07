package com.fatihkilic.muminappandroid.Ulkeler;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UlkelerService {

    String myString =  "ulkeler";

    @GET(myString)
    Call<List<UlkelerModel>> getUlkelerData();

}
