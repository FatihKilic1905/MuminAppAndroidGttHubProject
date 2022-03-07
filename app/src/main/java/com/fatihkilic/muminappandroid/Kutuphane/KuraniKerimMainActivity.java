package com.fatihkilic.muminappandroid.Kutuphane;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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


    ArrayList<ModelKuranıKerimArapca> modelKuranıKerimArapcaArrayList;

    ArrayList<ModelKuraniKerimTurkceDib> modelKuraniKerimTurkceDibArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kurani_kerim_main);


        binding = ActivityKuraniKerimMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        modelKuranıKerimArapcaArrayList = new ArrayList<>();
        modelKuraniKerimTurkceDibArrayList = new ArrayList<>();




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



                        ModelKuranıKerimArapca modelKuranıKerimArapca = new ModelKuranıKerimArapca(juz,number,numberInSurah,page,sajda,surahNameTr,surahNumber,text);
                        modelKuranıKerimArapcaArrayList.add(modelKuranıKerimArapca);


                    }


                    for (ModelKuranıKerimArapca arapcaKuran : modelKuranıKerimArapcaArrayList) {

                        arapcaKuranDatabase.execSQL("CREATE TABLE IF NOT EXISTS arapcaKuranDatabase(id INTEGER PRIMARY KEY, juzArap INTEGER, numberArap INTEGER, numberInSurahArap INTEGER, pageArap INTEGER, sajdaArap INTEGER, surahNameTrArap VARCHAR, surahNumberArap VARCHAR, textArap VARCHAR)");
                        String ArapcaKuranString = "INSERT INTO arapcaKuranDatabase(juzArap, numberArap, numberInSurahArap, pageArap, sajdaArap, surahNameTrArap, surahNumberArap, textArap) VALUES(?,?,?,?,?,?,?,?)";
                        SQLiteStatement sqLiteStatementArapcaKuran = arapcaKuranDatabase.compileStatement(ArapcaKuranString);
                        sqLiteStatementArapcaKuran.bindLong(1, arapcaKuran.juzArap);
                        sqLiteStatementArapcaKuran.bindLong(2, arapcaKuran.numberArap);
                        sqLiteStatementArapcaKuran.bindLong(3, arapcaKuran.numberInSurahArap);
                        sqLiteStatementArapcaKuran.bindLong(4, arapcaKuran.pageArap);
                        sqLiteStatementArapcaKuran.bindLong(5, arapcaKuran.sajdaArap);
                        sqLiteStatementArapcaKuran.bindString(6, vakitleriModel.Yatsi);
                        sqLiteStatementArapcaKuran.bindString(7, vakitleriModel.MiladiTarihKisa);
                        sqLiteStatementArapcaKuran.bindString(8, vakitleriModel.MiladiTarihUzun);
                        sqLiteStatementArapcaKuran.bindString(9, vakitleriModel.HicriTarihUzun);



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



                        ModelKuraniKerimTurkceDib modelKuranıKerimArapca = new ModelKuraniKerimTurkceDib(juz,number,numberInSurah,page,sajda,surahNameTr,surahNumber,text);
                        modelKuraniKerimTurkceDibArrayList.add(modelKuranıKerimArapca);


                    }


                }


            }

        });






    }



}