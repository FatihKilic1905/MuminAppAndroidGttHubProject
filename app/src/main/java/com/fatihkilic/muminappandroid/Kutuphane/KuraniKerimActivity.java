package com.fatihkilic.muminappandroid.Kutuphane;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityKuraniKerimBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityKuraniKerimMainBinding;
import com.google.firebase.firestore.core.OrderBy;

import java.util.ArrayList;

public class KuraniKerimActivity extends AppCompatActivity {




    private ActivityKuraniKerimBinding binding;

    SQLiteDatabase arapcaKuranDatabase;
    SQLiteDatabase turkceDibQuranDAtabase;


    static String kategoriName;
    static String surahName;


    ArrayList<ModelKuranıKerimPageArapca> modelKuranıKerimPageArapcaArrayList;
    ArrayList<ModelKraniKerimPageMeal> modelKraniKerimPageMealArrayList;

    AdapterKuraniKerimPage adapterKuraniKerimPage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kurani_kerim);


        binding = ActivityKuraniKerimBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent kategoriIntent = getIntent();
        surahName = kategoriIntent.getStringExtra("SureName");
        kategoriName = kategoriIntent.getStringExtra("KategoriName");


        modelKuranıKerimPageArapcaArrayList = new ArrayList<>();
        modelKraniKerimPageMealArrayList = new ArrayList<>();

        getSureArap();
        getSureMeal();

        getSupportActionBar().setTitle( surahName + " Suresi");

        binding.kuraniKerimRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterKuraniKerimPage = new AdapterKuraniKerimPage(modelKuranıKerimPageArapcaArrayList);

        binding.kuraniKerimRecyclerView.setAdapter(adapterKuraniKerimPage);

    }


    public void getSureArap () {


        arapcaKuranDatabase = openOrCreateDatabase("QuranDatabase", MODE_PRIVATE,null);
        arapcaKuranDatabase.execSQL("CREATE TABLE IF NOT EXISTS arapcaKuranDatabase(id INTEGER PRIMARY KEY, juzArap INTEGER, numberArap INTEGER, numberInSurahArap INTEGER, pageArap INTEGER, sajdaArap INTEGER, surahNameTrArap VARCHAR, surahNumberArap VARCHAR, textArap VARCHAR)");


        try {


            Cursor cursor = arapcaKuranDatabase.rawQuery("SELECT * FROM arapcaKuranDatabase WHERE surahNameTrArap = ? ORDER BY numberInSurahArap ASC", new String[]{surahName});

            int juzArapIx = cursor.getColumnIndex("juzArap");
            int numberArapIx = cursor.getColumnIndex("numberArap");
            int numberInSurahArapIx = cursor.getColumnIndex("numberInSurahArap");
            int pageArapIx = cursor.getColumnIndex("pageArap");
            int sajdaArapIx = cursor.getColumnIndex("sajdaArap");
            int surahNameTrArapIx = cursor.getColumnIndex("surahNameTrArap");
            int surahNumberArapIx = cursor.getColumnIndex("surahNumberArap");
            int textArapıx = cursor.getColumnIndex("textArap");

            while (cursor.moveToNext()) {

                int juz = cursor.getInt(juzArapIx);
                int number = cursor.getInt(numberArapIx);
                int numberInSurah = cursor.getInt(numberInSurahArapIx);
                int page = cursor.getInt(pageArapIx);
                int sajda = cursor.getInt(sajdaArapIx);
                String surahNametr = cursor.getString(surahNameTrArapIx);
                String surahNumber = cursor.getString(surahNumberArapIx);
                String text = cursor.getString(textArapıx);

                ModelKuranıKerimPageArapca modelKuranıKerimPageArapca = new ModelKuranıKerimPageArapca(juz,number,numberInSurah,page,sajda,surahNametr,surahNumber,text);
                modelKuranıKerimPageArapcaArrayList.add(modelKuranıKerimPageArapca);


            }


            cursor.close();


        } catch (Exception e) {

            e.printStackTrace();

        }







    }


    public void getSureMeal () {


        turkceDibQuranDAtabase = openOrCreateDatabase("QuranDatabase", MODE_PRIVATE,null);
        turkceDibQuranDAtabase.execSQL("CREATE TABLE IF NOT EXISTS turkceDibQuranDatabase(id INTEGER PRIMARY KEY, juzTrDib INTEGER, numberTrDib INTEGER, numberInSurahTrDib INTEGER, pageTrDib INTEGER, sajdaTrDib INTEGER, surahNameTrDib VARCHAR, surahNumberTrDib VARCHAR, textTrdib VARCHAR)");


        try {


            Cursor cursor = turkceDibQuranDAtabase.rawQuery("SELECT * FROM turkceDibQuranDatabase WHERE surahNameTrDib = ? ORDER BY numberInSurahTrDib ASC", new String[]{surahName});

            int juzTrDibIx = cursor.getColumnIndex("juzTrDib");
            int numberTrDibIx = cursor.getColumnIndex("numberTrDib");
            int numberInSurahTrDibIx = cursor.getColumnIndex("numberInSurahTrDib");
            int pageTrDibIx = cursor.getColumnIndex("pageTrDib");
            int sajdaTrDibIx = cursor.getColumnIndex("sajdaTrDib");
            int surahNameTrDibIx = cursor.getColumnIndex("surahNameTrDib");
            int surahNumberTrDibIx = cursor.getColumnIndex("surahNumberTrDib");
            int textTrdibıx = cursor.getColumnIndex("textTrdib");

            while (cursor.moveToNext()) {

                int juz = cursor.getInt(juzTrDibIx);
                int number = cursor.getInt(numberTrDibIx);
                int numberInSurah = cursor.getInt(numberInSurahTrDibIx);
                int page = cursor.getInt(pageTrDibIx);
                int sajda = cursor.getInt(sajdaTrDibIx);
                String surahNametr = cursor.getString(surahNameTrDibIx);
                String surahNumber = cursor.getString(surahNumberTrDibIx);
                String text = cursor.getString(textTrdibıx);

                ModelKraniKerimPageMeal modelKraniKerimPageMeal = new ModelKraniKerimPageMeal(juz,number,numberInSurah,page,sajda,surahNametr,surahNumber,text);
                modelKraniKerimPageMealArrayList.add(modelKraniKerimPageMeal);


            }


            cursor.close();


        } catch (Exception e) {

            e.printStackTrace();

        }







    }






}