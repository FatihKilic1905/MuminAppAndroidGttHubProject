package com.fatihkilic.muminappandroid.EzanVaktiCoplete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fatihkilic.muminappandroid.Ayarlar.AyarlarActivity;
import com.fatihkilic.muminappandroid.Ayarlar.SesSettingsActivity;
import com.fatihkilic.muminappandroid.EzanVaktiService;
import com.fatihkilic.muminappandroid.Ilceler.IlcelerActivity;
import com.fatihkilic.muminappandroid.MainActivity;
import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.Ulkeler.KonumActivity;
import com.fatihkilic.muminappandroid.Ulkeler.UlkelerModel;
import com.fatihkilic.muminappandroid.Ulkeler.UlkelerPost;
import com.fatihkilic.muminappandroid.databinding.ActivityEzanVaktiBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityIlcelerBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;

public class EzanVaktiActivity extends AppCompatActivity {

    String IlkGirisInfo = "ikinciGiris";

    ArrayList<EzanVakitleriModel> ezanVakitleriModels;
    ArrayList<EzanVaktiPost> ezanVaktiPostArrayList;

    private String BaseUrl = "https://ezanvakti.herokuapp.com/";

    Retrofit retrofitIlce;

    private ActivityEzanVaktiBinding binding;

    SQLiteDatabase vakitDatabase;

    SharedPreferences sharedPreferences;


    Date currentTime = Calendar.getInstance().getTime();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ezan_vakti);

        binding = ActivityEzanVaktiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);

        Gson gsonIlce = new GsonBuilder().setLenient().create();

        retrofitIlce = new Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create(gsonIlce)).build();

        sharedPreferences = this.getSharedPreferences("com.fatihkilic.muminappandroid", Context.MODE_PRIVATE);


        setEzanVakti();


        IlkGirisInfo = sharedPreferences.getString("IlkGiris","0");




        Button gecisyap = (Button) view.findViewById(R.id.konumOnaylaButton);
        gecisyap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadEzanVakti();

                if (IlkGirisInfo.equals("1")) {

                    Intent konum = new Intent(EzanVaktiActivity.this, SesSettingsActivity.class);
                    konum.putExtra("ilkGiris2","ilkGiris2");
                    startActivity(konum);


                } else {


                    Intent konum = new Intent(EzanVaktiActivity.this, MainActivity.class);
                    startActivity(konum);
                    finish();

                }



            }
        });


    }

    public void loadEzanVakti() {


        vakitDatabase = openOrCreateDatabase("EZANVAKITLERIDATA", MODE_PRIVATE, null);
        vakitDatabase.execSQL("CREATE TABLE IF NOT EXISTS ezanvakitleridatabase(id INTEGER PRIMARY KEY, imsakVakti VARCHAR, gunesVakti VARCHAR, ogleVakti VARCHAR, ikindiVakti VARCHAR, aksamVakti VARCHAR, yatsiVakti VARCHAR, miladiKisa VARCHAR, miladiUzun VARCHAR, hicriUzun VARCHAR)");




        Cursor cursor =  vakitDatabase.rawQuery("SELECT * FROM ezanvakitleridatabase", null);
            System.out.println("cursor " + cursor.getCount());

            if (cursor.getCount() != 0) {

                vakitDatabase.execSQL("DROP TABLE  ezanvakitleridatabase");

                Intent ilceIDIntent = getIntent();
                String ilceIDGet = ilceIDIntent.getStringExtra("ilceID");
                String ilceNameGet = ilceIDIntent.getStringExtra("ilceName");
                binding.konumTextView.setText(ilceNameGet);

                sharedPreferences.edit().putString("storedKonum", ilceNameGet).apply();



                System.out.println("cursor 2 " + cursor.getCount());

                EzanVaktiService ezanVaktiService = retrofitIlce.create(EzanVaktiService.class);
                Call<List<EzanVakitleriModel>> call = ezanVaktiService.getEzanData(ilceIDGet);

                call.enqueue(new Callback<List<EzanVakitleriModel>>() {
                    @Override
                    public void onResponse(Call<List<EzanVakitleriModel>> call, Response<List<EzanVakitleriModel>> response) {

                        if (response.isSuccessful()) {

                            List<EzanVakitleriModel> responseList = response.body();
                            ezanVakitleriModels = new ArrayList<>(responseList);

                            for (EzanVakitleriModel vakitleriModel : ezanVakitleriModels) {

                                try {


                                    vakitDatabase.execSQL("CREATE TABLE IF NOT EXISTS ezanvakitleridatabase(id INTEGER PRIMARY KEY, imsakVakti VARCHAR, gunesVakti VARCHAR, ogleVakti VARCHAR, ikindiVakti VARCHAR, aksamVakti VARCHAR, yatsiVakti VARCHAR, miladiKisa VARCHAR, miladiUzun VARCHAR, hicriUzun VARCHAR)");
                                    String EzanVakitleriDatabaseString = "INSERT INTO ezanvakitleridatabase(imsakVakti, gunesVakti, ogleVakti, ikindiVakti, aksamVakti, yatsiVakti, miladiKisa, miladiUzun, hicriUzun) VALUES(?,?,?,?,?,?,?,?,?)";
                                    SQLiteStatement sqLiteStatementEzanVakti = vakitDatabase.compileStatement(EzanVakitleriDatabaseString);
                                    sqLiteStatementEzanVakti.bindString(1, vakitleriModel.Imsak);
                                    sqLiteStatementEzanVakti.bindString(2, vakitleriModel.Gunes);
                                    sqLiteStatementEzanVakti.bindString(3, vakitleriModel.Ogle);
                                    sqLiteStatementEzanVakti.bindString(4, vakitleriModel.Ikindi);
                                    sqLiteStatementEzanVakti.bindString(5, vakitleriModel.Aksam);
                                    sqLiteStatementEzanVakti.bindString(6, vakitleriModel.Yatsi);
                                    sqLiteStatementEzanVakti.bindString(7, vakitleriModel.MiladiTarihKisa);
                                    sqLiteStatementEzanVakti.bindString(8, vakitleriModel.MiladiTarihUzun);
                                    sqLiteStatementEzanVakti.bindString(9, vakitleriModel.HicriTarihUzun);
                                    sqLiteStatementEzanVakti.execute();

                                    System.out.println("vakitler" + vakitleriModel.Yatsi);




                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<List<EzanVakitleriModel>> call, Throwable t) {

                        t.printStackTrace();

                    }
                });



            } else if (cursor.getCount() == 0){

                Intent ilceIDIntent = getIntent();
                String ilceIDGet = ilceIDIntent.getStringExtra("ilceID");
                String ilceNameGet = ilceIDIntent.getStringExtra("ilceName");
                binding.konumTextView.setText(ilceNameGet);

                sharedPreferences.edit().putString("storedKonum", ilceNameGet).apply();

                EzanVaktiService ezanVaktiService = retrofitIlce.create(EzanVaktiService.class);
                Call<List<EzanVakitleriModel>> call = ezanVaktiService.getEzanData(ilceIDGet);

                call.enqueue(new Callback<List<EzanVakitleriModel>>() {
                    @Override
                    public void onResponse(Call<List<EzanVakitleriModel>> call, Response<List<EzanVakitleriModel>> response) {

                        if (response.isSuccessful()) {

                            List<EzanVakitleriModel> responseList = response.body();
                            ezanVakitleriModels = new ArrayList<>(responseList);

                            for (EzanVakitleriModel vakitleriModel : ezanVakitleriModels) {

                                try {



                                    String EzanVakitleriDatabaseString = "INSERT INTO ezanvakitleridatabase(imsakVakti, gunesVakti, ogleVakti, ikindiVakti, aksamVakti, yatsiVakti, miladiKisa, miladiUzun, hicriUzun) VALUES(?,?,?,?,?,?,?,?,?)";
                                    SQLiteStatement sqLiteStatementEzanVakti = vakitDatabase.compileStatement(EzanVakitleriDatabaseString);
                                    sqLiteStatementEzanVakti.bindString(1, vakitleriModel.Imsak);
                                    sqLiteStatementEzanVakti.bindString(2, vakitleriModel.Gunes);
                                    sqLiteStatementEzanVakti.bindString(3, vakitleriModel.Ogle);
                                    sqLiteStatementEzanVakti.bindString(4, vakitleriModel.Ikindi);
                                    sqLiteStatementEzanVakti.bindString(5, vakitleriModel.Aksam);
                                    sqLiteStatementEzanVakti.bindString(6, vakitleriModel.Yatsi);
                                    sqLiteStatementEzanVakti.bindString(7, vakitleriModel.MiladiTarihKisa);
                                    sqLiteStatementEzanVakti.bindString(8, vakitleriModel.MiladiTarihUzun);
                                    sqLiteStatementEzanVakti.bindString(9, vakitleriModel.HicriTarihUzun);
                                    sqLiteStatementEzanVakti.execute();

                                    System.out.println("sql yukleme başarılı");




                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        }

                    }



                    @Override
                    public void onFailure(Call<List<EzanVakitleriModel>> call, Throwable t) {

                        t.printStackTrace();

                    }
                });


            }

    }


    public void getEzanVakti() {

        SimpleDateFormat sistemtarih = new SimpleDateFormat("dd.M.yyyy");
        Date sistemtarihi = new Date();
        String sistemTarihiString = sistemtarih.format(sistemtarihi);



        vakitDatabase = openOrCreateDatabase("EZANVAKITLERIDATA", MODE_PRIVATE, null);

        try {

            Cursor cursor = vakitDatabase.rawQuery("SELECT * FROM ezanvakitleridatabase WHERE miladiKisa = ?", new String[]{sistemTarihiString});
            int imsakVaktiIx = cursor.getColumnIndex("imsakVakti");
            int gunesVaktiIx = cursor.getColumnIndex("gunesVakti");
            int ogleVaktiIx = cursor.getColumnIndex("ogleVakti");
            int ikindiVaktiIx = cursor.getColumnIndex("ikindiVakti");
            int aksamVaktiIx = cursor.getColumnIndex("aksamVakti");
            int yatsiVaktiIx = cursor.getColumnIndex("yatsiVakti");


            while (cursor.moveToNext()) {

                binding.imsakTime.setText(cursor.getString(imsakVaktiIx));
                binding.gunesTime.setText(cursor.getString(gunesVaktiIx));
                binding.ogleTime.setText(cursor.getString(ogleVaktiIx));
                binding.ikindiTime.setText(cursor.getString(ikindiVaktiIx));
                binding.aksamTime.setText(cursor.getString(aksamVaktiIx));
                binding.yatsiTime.setText(cursor.getString(yatsiVaktiIx));


            }

            cursor.close();

        } catch (Exception e) {

            e.printStackTrace();

        }


    }

    public void setEzanVakti() {


        vakitDatabase = openOrCreateDatabase("EZANVAKITLERIDATA", MODE_PRIVATE, null);
        Intent ilceIDIntent = getIntent();
        String ilceIDGet = ilceIDIntent.getStringExtra("ilceID");
        String ilceNameGet = ilceIDIntent.getStringExtra("ilceName");
        binding.konumTextView.setText(ilceNameGet);

        EzanVaktiService ezanVaktiService = retrofitIlce.create(EzanVaktiService.class);
        Call<List<EzanVakitleriModel>> call = ezanVaktiService.getEzanData(ilceIDGet);

        call.enqueue(new Callback<List<EzanVakitleriModel>>() {
            @Override
            public void onResponse(Call<List<EzanVakitleriModel>> call, Response<List<EzanVakitleriModel>> response) {

                if (response.isSuccessful()) {

                    List<EzanVakitleriModel> responseList = response.body();
                    ezanVakitleriModels = new ArrayList<>(responseList);

                    for (EzanVakitleriModel vakitleriModel : ezanVakitleriModels) {

                        try {


                            binding.imsakTime.setText(vakitleriModel.Imsak);
                            binding.gunesTime.setText(vakitleriModel.Gunes);
                            binding.ogleTime.setText(vakitleriModel.Ogle);
                            binding.ikindiTime.setText(vakitleriModel.Ikindi);
                            binding.aksamTime.setText(vakitleriModel.Aksam);
                            binding.yatsiTime.setText(vakitleriModel.Yatsi);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }

            }

            @Override
            public void onFailure(Call<List<EzanVakitleriModel>> call, Throwable t) {

                t.printStackTrace();

            }
        });


    }


}







