package com.fatihkilic.muminappandroid.Kutuphane;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityKuraniKerimBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityKuraniKerimMainBinding;
import com.google.firebase.firestore.core.OrderBy;

public class KuraniKerimActivity extends AppCompatActivity {




    private ActivityKuraniKerimBinding binding;

    SQLiteDatabase arapcaKuranDatabase;
    SQLiteDatabase turkceDibQuranDAtabase;


    static String kategoriName;
    static String surahName;



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



    }


    public void getSureArap () {


        arapcaKuranDatabase = openOrCreateDatabase("QuranDatabase", MODE_PRIVATE,null);
        arapcaKuranDatabase.execSQL("CREATE TABLE IF NOT EXISTS arapcaKuranDatabase(id INTEGER PRIMARY KEY, juzArap INTEGER, numberArap INTEGER, numberInSurahArap INTEGER, pageArap INTEGER, sajdaArap INTEGER, surahNameTrArap VARCHAR, surahNumberArap VARCHAR, textArap VARCHAR)");


        try {


            Cursor cursor = arapcaKuranDatabase.rawQuery("SELECT * FROM arapcaKuranDatabase WHERE surahNameTrArap = ? ORDER BY id", new String[]{surahName});

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




            }


            cursor.close();




        } catch (Exception e) {

            e.printStackTrace();

        }







    }






}