package com.fatihkilic.muminappandroid.Kutuphane;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.User.MyAccountActivity;
import com.fatihkilic.muminappandroid.databinding.ActivityKuraniKerimMainBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityMyAccountBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class KuraniKerimMainActivity extends AppCompatActivity {

    private ActivityKuraniKerimMainBinding binding;

    private FirebaseFirestore firebaseFirestore;

    SQLiteDatabase arapcaKuranDatabase;
    SQLiteDatabase turkceDibQuranDAtabase;


    ArrayList<ModelKuranıKerimArapca> modelKuranıKerimArapcaArrayList;

    ArrayList<ModelKuraniKerimTurkceDib> modelKuraniKerimTurkceDibArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kurani_kerim_main);


        binding = ActivityKuraniKerimMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        firebaseFirestore = FirebaseFirestore.getInstance();

        modelKuranıKerimArapcaArrayList = new ArrayList<>();
        modelKuraniKerimTurkceDibArrayList = new ArrayList<>();


        binding.surelerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent surahIntent = new Intent(KuraniKerimMainActivity.this, KuraniKerimKategoriActivity.class);
                startActivity(surahIntent);


            }
        });

        arapcaKuranDatabase = openOrCreateDatabase("QuranDatabase", MODE_PRIVATE,null);
        arapcaKuranDatabase.execSQL("CREATE TABLE IF NOT EXISTS arapcaKuranDatabase(id INTEGER PRIMARY KEY, juzArap INTEGER, numberArap INTEGER, numberInSurahArap INTEGER, pageArap INTEGER, sajdaArap INTEGER, surahNameTrArap VARCHAR, surahNumberArap VARCHAR, textArap VARCHAR)");


        try {


            Cursor cursor = arapcaKuranDatabase.rawQuery("SELECT * FROM arapcaKuranDatabase ", null);

            System.out.println("cursor" + cursor.getCount());

            if (cursor.getCount() == 0) {

               getKuraniKerimArapca();
               getKuraniKerimTrDib();
               binding.surelerButton.setVisibility(View.INVISIBLE);
               binding.progressBar2.setVisibility(View.VISIBLE);
                binding.uyariTextview.setVisibility(View.VISIBLE);




            } else {

                System.out.println("cursor" + cursor.getCount());
                binding.surelerButton.setVisibility(View.VISIBLE);
                binding.progressBar2.setVisibility(View.INVISIBLE);
                binding.uyariTextview.setVisibility(View.INVISIBLE);

            }


        } catch (Exception e) {

            e.printStackTrace();
        }



    }


    public void getKuraniKerimArapca () {





        firebaseFirestore.collection("QuranCloud").document("data").collection("QuranText").addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    Toast.makeText(KuraniKerimMainActivity.this, "İnternet bağlantısında bir problem var", Toast.LENGTH_LONG).show();

                }

                if (value != null) {

                    modelKuranıKerimArapcaArrayList.clear();

                    for (DocumentSnapshot snapshot : value.getDocuments()) {

                        Map<String, Object> data = snapshot.getData();

                        Long juzLong = (Long) data.get("juz");
                        Long numberLong = (Long) data.get("number");
                        Long numberInSurahLong = (Long) data.get("numberInSurah");
                        Long pageLong = (Long) data.get("page");
                        Boolean sajda = (Boolean) data.get("sajda");
                        String surahNameTr = (String) data.get("surahNameTr");
                        String surahNumber = (String) data.get("surahNumber");
                        String text = (String) data.get("text");

                        Integer juz = juzLong.intValue();
                        Integer number = numberLong.intValue();
                        Integer numberInSurah = numberInSurahLong.intValue();
                        Integer page = pageLong.intValue();

                        Integer sajdaInt = null;

                        if (sajda == true) {

                            sajdaInt = 1;

                        } else if (sajda == false) {

                            sajdaInt = 0;

                        }



                        ModelKuranıKerimArapca modelKuranıKerimArapca = new ModelKuranıKerimArapca(juz,number,numberInSurah,page,sajdaInt,surahNameTr,surahNumber,text);
                        modelKuranıKerimArapcaArrayList.add(modelKuranıKerimArapca);

                        if (modelKuranıKerimArapcaArrayList.size() == 6236) {


                            binding.surelerButton.setVisibility(View.VISIBLE);
                            binding.progressBar2.setVisibility(View.INVISIBLE);
                            binding.uyariTextview.setVisibility(View.INVISIBLE);

                        }


                    }


                    for (ModelKuranıKerimArapca arapcaKuran : modelKuranıKerimArapcaArrayList) {


                        try {

                            arapcaKuranDatabase = openOrCreateDatabase("QuranDatabase", MODE_PRIVATE,null);
                            arapcaKuranDatabase.execSQL("CREATE TABLE IF NOT EXISTS arapcaKuranDatabase(id INTEGER PRIMARY KEY, juzArap INTEGER, numberArap INTEGER, numberInSurahArap INTEGER, pageArap INTEGER, sajdaArap INTEGER, surahNameTrArap VARCHAR, surahNumberArap VARCHAR, textArap VARCHAR)");
                            String ArapcaKuranString = "INSERT INTO arapcaKuranDatabase(juzArap, numberArap, numberInSurahArap, pageArap, sajdaArap, surahNameTrArap, surahNumberArap, textArap) VALUES(?,?,?,?,?,?,?,?)";
                            SQLiteStatement sqLiteStatementArapcaKuran = arapcaKuranDatabase.compileStatement(ArapcaKuranString);
                            sqLiteStatementArapcaKuran.bindLong(1, arapcaKuran.juzArap);
                            sqLiteStatementArapcaKuran.bindLong(2, arapcaKuran.numberArap);
                            sqLiteStatementArapcaKuran.bindLong(3, arapcaKuran.numberInSurahArap);
                            sqLiteStatementArapcaKuran.bindLong(4, arapcaKuran.pageArap);
                            sqLiteStatementArapcaKuran.bindLong(5, arapcaKuran.sajdaArap);
                            sqLiteStatementArapcaKuran.bindString(6, arapcaKuran.surahNameTrArap);
                            sqLiteStatementArapcaKuran.bindString(7, arapcaKuran.surahNumberArap);
                            sqLiteStatementArapcaKuran.bindString(8, arapcaKuran.textArap);
                            sqLiteStatementArapcaKuran.execute();

                        } catch (Exception e) {

                            e.printStackTrace();

                        }


                    }


                }


            }

        });


    }



    public void getKuraniKerimTrDib () {

        firebaseFirestore.collection("QuranCloud").document("data").collection("QuranMeal").document("tr").collection("tr-diyanet").addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    Toast.makeText(KuraniKerimMainActivity.this, "İnternet bağlantısında bir problem var", Toast.LENGTH_LONG).show();

                }

                if (value != null) {

                    modelKuraniKerimTurkceDibArrayList.clear();

                    for (DocumentSnapshot snapshot : value.getDocuments()) {

                        Map<String, Object> data = snapshot.getData();

                        Long juzLong = (Long) data.get("juz");
                        Long numberLong = (Long) data.get("number");
                        Long numberInSurahLong = (Long) data.get("numberInSurah");
                        Long pageLong = (Long) data.get("page");
                        Boolean sajda = (Boolean) data.get("sajda");
                        String surahNameTr = (String) data.get("surahNameTr");
                        String surahNumber = (String) data.get("surahNumber");
                        String text = (String) data.get("text");

                        Integer juz = juzLong.intValue();
                        Integer number = numberLong.intValue();
                        Integer numberInSurah = numberInSurahLong.intValue();
                        Integer page = pageLong.intValue();


                        Integer sajdaInt = null;

                        if (sajda == true) {

                            sajdaInt = 1;

                        } else if (sajda == false) {

                            sajdaInt = 0;

                        }


                        ModelKuraniKerimTurkceDib modelKuranıKerimArapca = new ModelKuraniKerimTurkceDib(juz,number,numberInSurah,page,sajdaInt,surahNameTr,surahNumber,text);
                        modelKuraniKerimTurkceDibArrayList.add(modelKuranıKerimArapca);

                        if (modelKuraniKerimTurkceDibArrayList.size() == 6236) {


                            binding.surelerButton.setVisibility(View.VISIBLE);
                            binding.progressBar2.setVisibility(View.INVISIBLE);
                            binding.uyariTextview.setVisibility(View.INVISIBLE);

                        }

                    }


                    for (ModelKuraniKerimTurkceDib turkceDibKuran : modelKuraniKerimTurkceDibArrayList) {


                        try {

                            turkceDibQuranDAtabase = openOrCreateDatabase("QuranDatabase", MODE_PRIVATE,null);
                            turkceDibQuranDAtabase.execSQL("CREATE TABLE IF NOT EXISTS turkceDibQuranDatabase(id INTEGER PRIMARY KEY, juzTrDib INTEGER, numberTrDib INTEGER, numberInSurahTrDib INTEGER, pageTrDib INTEGER, sajdaTrDib INTEGER, surahNameTrDib VARCHAR, surahNumberTrDib VARCHAR, textTrdib VARCHAR)");
                            String TurkceKuranDibString = "INSERT INTO arapcaKuranDatabase(juzTrDib, numberTrDib, numberInSurahTrDib, pageTrDib, sajdaTrDib, surahNameTrDib, surahNumberTrDib, textTrdib) VALUES(?,?,?,?,?,?,?,?)";
                            SQLiteStatement sqLiteStatementTurkceDibKuran = turkceDibQuranDAtabase.compileStatement(TurkceKuranDibString);
                            sqLiteStatementTurkceDibKuran.bindLong(1, turkceDibKuran.juzTrDib);
                            sqLiteStatementTurkceDibKuran.bindLong(2, turkceDibKuran.numberTrDib);
                            sqLiteStatementTurkceDibKuran.bindLong(3, turkceDibKuran.numberInSurahTrDib);
                            sqLiteStatementTurkceDibKuran.bindLong(4, turkceDibKuran.pageTrDib);
                            sqLiteStatementTurkceDibKuran.bindLong(5, turkceDibKuran.sajdaTrDib);
                            sqLiteStatementTurkceDibKuran.bindString(6, turkceDibKuran.surahNameTrDib);
                            sqLiteStatementTurkceDibKuran.bindString(7, turkceDibKuran.surahNumberTrDib);
                            sqLiteStatementTurkceDibKuran.bindString(8, turkceDibKuran.textTrdib);
                            sqLiteStatementTurkceDibKuran.execute();

                        } catch (Exception e) {

                            e.printStackTrace();

                        }


                    }


                }


            }

        });






    }



}