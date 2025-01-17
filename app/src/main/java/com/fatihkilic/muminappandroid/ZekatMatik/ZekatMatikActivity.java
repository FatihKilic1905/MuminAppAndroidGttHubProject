package com.fatihkilic.muminappandroid.ZekatMatik;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fatihkilic.muminappandroid.Ayarlar.AyarlarActivity;
import com.fatihkilic.muminappandroid.EzanVaktiCoplete.EzanVaktiActivity;
import com.fatihkilic.muminappandroid.MainActivity;
import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityZekatMatikBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.ktx.Firebase;

import java.util.Map;

public class ZekatMatikActivity extends AppCompatActivity {

    double dolarCr;
    double euroCr;
    double fullGoldCr;
    double halfGoldCr;
    double quarterGoldCr;
    double carats14Cr;
    double carats18Cr;
    double carats22Cr;
    double gramCr;

    double varlikTl;
    double varlikDolar;
    double varlikeuro;
    double varlikFullGold;
    double varlikHalfGold;
    double varlikQuarterGold;
    double varlikcarat14;
    double varlikcarat18;
    double varlikcarat22;
    double varlikgram;

    double borcTl;
    double borcDolar;
    double borceuro;
    double borcFullGold;
    double borcHalfGold;
    double borcQuarterGold;
    double borccarat14;
    double borccarat18;
    double borccarat22;
    double borcgram;

    double toplamVarlik;
    double toplamBorc;

    double nisabMiktari;





    private ActivityZekatMatikBinding binding;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zekat_matik);

        binding = ActivityZekatMatikBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        firebaseFirestore = FirebaseFirestore.getInstance();

        getDolarCurrency();
        getEuroCurrency();
        getFullGoldCurrency();
        getHalfGoldCurrency();
        getQuarterGoldCurrency();
        getcarats14Currency();
        getcarrats18Currency();
        getcarrats22Currency();
        getGramCurrency();


        binding.borctlTextView.setVisibility(View.INVISIBLE);
        binding.borcdolarTextView.setVisibility(View.INVISIBLE);
        binding.borceuroTextView.setVisibility(View.INVISIBLE);
        binding.borctamAltinTexView.setVisibility(View.INVISIBLE);
        binding.borcyarimAltinTextView.setVisibility(View.INVISIBLE);
        binding.borcceyrekAltinTextView.setVisibility(View.INVISIBLE);
        binding.borcayar22TextView.setVisibility(View.INVISIBLE);
        binding.borcayar18TextView.setVisibility(View.INVISIBLE);
        binding.borcayar14TextView.setVisibility(View.INVISIBLE);
        binding.borcgramAltinTextView.setVisibility(View.INVISIBLE);
        binding.borclarButton.setVisibility(View.INVISIBLE);
        binding.hesaplaButton.setVisibility(View.INVISIBLE);
        binding.varliklarButton.setVisibility(View.INVISIBLE);
        binding.nisabMiktariText.setVisibility(View.INVISIBLE);
        binding.nisabMiktariDeger.setVisibility(View.INVISIBLE);
        binding.zekatMiktariText.setVisibility(View.INVISIBLE);
        binding.zekatMiktariDeger.setVisibility(View.INVISIBLE);
        binding.sifirlaButton.setVisibility(View.INVISIBLE);
        binding.duzenleButton.setVisibility(View.INVISIBLE);
        binding.kapatButton.setVisibility(View.INVISIBLE);





        Button varlikButton = binding.varliklarButton;

        varlikButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.borctlTextView.setVisibility(View.INVISIBLE);
                binding.borcdolarTextView.setVisibility(View.INVISIBLE);
                binding.borceuroTextView.setVisibility(View.INVISIBLE);
                binding.borctamAltinTexView.setVisibility(View.INVISIBLE);
                binding.borcyarimAltinTextView.setVisibility(View.INVISIBLE);
                binding.borcceyrekAltinTextView.setVisibility(View.INVISIBLE);
                binding.borcayar22TextView.setVisibility(View.INVISIBLE);
                binding.borcayar18TextView.setVisibility(View.INVISIBLE);
                binding.borcayar14TextView.setVisibility(View.INVISIBLE);
                binding.borcgramAltinTextView.setVisibility(View.INVISIBLE);

                binding.tlTextView.setVisibility(View.VISIBLE);
                binding.dolarTextView.setVisibility(View.VISIBLE);
                binding.euroTextView.setVisibility(View.VISIBLE);
                binding.tamAltinTexView.setVisibility(View.VISIBLE);
                binding.yarimAltinTextView.setVisibility(View.VISIBLE);
                binding.ceyrekAltinTextView.setVisibility(View.VISIBLE);
                binding.ayar22TextView.setVisibility(View.VISIBLE);
                binding.ayar18TextView.setVisibility(View.VISIBLE);
                binding.ayar14TextView.setVisibility(View.VISIBLE);
                binding.gramAltinTextView.setVisibility(View.VISIBLE);

            }
        });

        Button borclarButton = binding.borclarButton;

        borclarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.borctlTextView.setVisibility(View.VISIBLE);
                binding.borcdolarTextView.setVisibility(View.VISIBLE);
                binding.borceuroTextView.setVisibility(View.VISIBLE);
                binding.borctamAltinTexView.setVisibility(View.VISIBLE);
                binding.borcyarimAltinTextView.setVisibility(View.VISIBLE);
                binding.borcceyrekAltinTextView.setVisibility(View.VISIBLE);
                binding.borcayar22TextView.setVisibility(View.VISIBLE);
                binding.borcayar18TextView.setVisibility(View.VISIBLE);
                binding.borcayar14TextView.setVisibility(View.VISIBLE);
                binding.borcgramAltinTextView.setVisibility(View.VISIBLE);

                binding.tlTextView.setVisibility(View.INVISIBLE);
                binding.dolarTextView.setVisibility(View.INVISIBLE);
                binding.euroTextView.setVisibility(View.INVISIBLE);
                binding.tamAltinTexView.setVisibility(View.INVISIBLE);
                binding.yarimAltinTextView.setVisibility(View.INVISIBLE);
                binding.ceyrekAltinTextView.setVisibility(View.INVISIBLE);
                binding.ayar22TextView.setVisibility(View.INVISIBLE);
                binding.ayar18TextView.setVisibility(View.INVISIBLE);
                binding.ayar14TextView.setVisibility(View.INVISIBLE);
                binding.gramAltinTextView.setVisibility(View.INVISIBLE);



            }
        });

        Button hesaplaButton = binding.hesaplaButton;

        hesaplaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!binding.tlTextView.getText().toString().matches("")) {

                    double data = Double.parseDouble(binding.tlTextView.getText().toString());
                    varlikTl = data;


                }

                if (!binding.dolarTextView.getText().toString().matches("")) {

                    double data = Double.parseDouble(binding.dolarTextView.getText().toString());
                    varlikDolar = data * dolarCr;

                }

                if (!binding.euroTextView.getText().toString().matches("")) {

                    double data = Double.parseDouble(binding.euroTextView.getText().toString());
                    varlikeuro = data * euroCr;

                }

                if (!binding.tamAltinTexView.getText().toString().matches("")) {

                    double data = Double.parseDouble(binding.tamAltinTexView.getText().toString());
                    varlikFullGold = data * fullGoldCr;

                }

                if (!binding.yarimAltinTextView.getText().toString().matches("")) {

                    double data = Double.parseDouble(binding.yarimAltinTextView.getText().toString());
                    varlikHalfGold = data * halfGoldCr;

                }

                if (!binding.ceyrekAltinTextView.getText().toString().matches("")) {

                    double data = Double.parseDouble(binding.ceyrekAltinTextView.getText().toString());
                    varlikQuarterGold = data * quarterGoldCr;

                }

                if (!binding.ayar14TextView.getText().toString().matches("")) {

                    double data = Double.parseDouble(binding.ayar14TextView.getText().toString());
                    varlikcarat14 = data * carats14Cr;

                }

                if (!binding.ayar18TextView.getText().toString().matches("")) {

                    double data = Double.parseDouble(binding.ayar18TextView.getText().toString());
                    varlikcarat18 = data * carats18Cr;

                }

                if (!binding.ayar22TextView.getText().toString().matches("")) {

                    double data = Double.parseDouble(binding.ayar22TextView.getText().toString());
                    varlikcarat22 = data * carats22Cr;

                }

                if (!binding.gramAltinTextView.getText().toString().matches("")) {

                    double data = Double.parseDouble(binding.gramAltinTextView.getText().toString());
                    varlikgram = data * gramCr;

                }





                if (!binding.borctlTextView.getText().toString().matches("")) {

                    double data = Double.parseDouble(binding.borctlTextView.getText().toString());
                    borcTl = data;

                }

                if (!binding.borcdolarTextView.getText().toString().matches("")) {

                    double data = Double.parseDouble(binding.borcdolarTextView.getText().toString());
                    borcDolar = data * dolarCr;

                }

                if (!binding.borceuroTextView.getText().toString().matches("")) {

                    double data = Double.parseDouble(binding.borceuroTextView.getText().toString());
                    borceuro = data * euroCr;

                }

                if (!binding.borctamAltinTexView.getText().toString().matches("")) {

                    double data = Double.parseDouble(binding.borctamAltinTexView.getText().toString());
                    borcFullGold = data * fullGoldCr;

                }

                if (!binding.borcyarimAltinTextView.getText().toString().matches("")) {

                    double data = Double.parseDouble(binding.borcyarimAltinTextView.getText().toString());
                    borcHalfGold = data * halfGoldCr;

                }
                if (!binding.borcceyrekAltinTextView.getText().toString().matches("")) {

                    double data = Double.parseDouble(binding.borcceyrekAltinTextView.getText().toString());
                    borcQuarterGold = data * quarterGoldCr;

                }

                if (!binding.borcayar14TextView.getText().toString().matches("")) {

                    double data = Double.parseDouble(binding.borcayar14TextView.getText().toString());
                    borccarat14 = data * carats14Cr;

                }

                if (!binding.borcayar18TextView.getText().toString().matches("")) {

                    double data = Double.parseDouble(binding.borcayar18TextView.getText().toString());
                    borccarat18 = data * carats18Cr;

                }

                if (!binding.borcayar22TextView.getText().toString().matches("")) {

                    double data = Double.parseDouble(binding.borcayar22TextView.getText().toString());
                    borccarat22 = data * carats22Cr;

                }


                if (!binding.borcgramAltinTextView.getText().toString().matches("")) {

                    double data = Double.parseDouble(binding.borcgramAltinTextView.getText().toString());
                    borcgram = data * gramCr;

                }


                double varlikToplam = varlikTl + varlikDolar + varlikeuro + varlikFullGold + varlikHalfGold + varlikQuarterGold + varlikcarat14 + varlikcarat18 + varlikcarat22 + varlikgram;
                double borcToplam = borcTl + borcDolar + borceuro + borcFullGold + borcHalfGold + borcQuarterGold + borccarat14 + borccarat18 + borccarat22 + borcgram;

                double toplamVarlık = varlikToplam - borcToplam;
                System.out.println("Toplam Varlık" + toplamVarlık);

                double nisabMiktariDouble = toplamVarlık / gramCr;
                double zekat = toplamVarlık / 40;


                if (nisabMiktariDouble >= 80.18) {




                    System.out.println("nisabs" +nisabMiktariDouble);
                    nisabMiktariDouble = Math.floor(nisabMiktariDouble*100) / 100;
                    StringBuilder nisabMiktariStr = new StringBuilder();
                    nisabMiktariStr.append(nisabMiktariDouble);
                    nisabMiktariStr.append(" Altın");

                    StringBuilder zekatMiktariStr = new StringBuilder();
                    zekatMiktariStr.append(Math.round(zekat));
                    zekatMiktariStr.append(" TL");


                    binding.infoBack.setVisibility(View.VISIBLE);
                    binding.nisabMiktariText.setVisibility(View.VISIBLE);
                    binding.nisabMiktariDeger.setVisibility(View.VISIBLE);
                    binding.zekatMiktariText.setVisibility(View.VISIBLE);
                    binding.zekatMiktariDeger.setVisibility(View.VISIBLE);
                    binding.borclarButton.setVisibility(View.INVISIBLE);
                    binding.varliklarButton.setVisibility(View.INVISIBLE);
                    binding.hesaplaButton.setVisibility(View.INVISIBLE);
                    binding.nisabMiktariDeger.setText(nisabMiktariStr);
                    binding.zekatMiktariDeger.setText(zekatMiktariStr);
                    binding.sifirlaButton.setVisibility(View.VISIBLE);
                    binding.duzenleButton.setVisibility(View.VISIBLE);
                    binding.kapatButton.setVisibility(View.VISIBLE);


                } else {


                    System.out.println("nisabs" +nisabMiktariDouble);
                    nisabMiktariDouble = Math.floor(nisabMiktariDouble*100) / 100;
                    System.out.println("nisabs" +nisabMiktariDouble);
                    StringBuilder nisabMiktariStr = new StringBuilder();
                    nisabMiktariStr.append(nisabMiktariDouble);

                    nisabMiktariStr.append(" Altın");

                    binding.infoBack.setVisibility(View.VISIBLE);
                    binding.nisabMiktariText.setVisibility(View.VISIBLE);
                    binding.nisabMiktariDeger.setVisibility(View.VISIBLE);
                    binding.zekatMiktariText.setVisibility(View.VISIBLE);
                    binding.zekatMiktariDeger.setVisibility(View.VISIBLE);
                    binding.borclarButton.setVisibility(View.INVISIBLE);
                    binding.varliklarButton.setVisibility(View.INVISIBLE);
                    binding.hesaplaButton.setVisibility(View.INVISIBLE);
                    binding.nisabMiktariDeger.setText(nisabMiktariStr);
                    binding.zekatMiktariDeger.setText("Varlıklarınız 80.18 Gr altın miktarı altında olduğu için zekat vermenize gerek yoktur.");
                    binding.sifirlaButton.setVisibility(View.VISIBLE);
                    binding.duzenleButton.setVisibility(View.VISIBLE);
                    binding.kapatButton.setVisibility(View.VISIBLE);

                }



                Button sifirlaButton = binding.sifirlaButton;
                sifirlaButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        binding.infoBack.setVisibility(View.INVISIBLE);
                        binding.nisabMiktariText.setVisibility(View.INVISIBLE);
                        binding.nisabMiktariDeger.setVisibility(View.INVISIBLE);
                        binding.zekatMiktariText.setVisibility(View.INVISIBLE);
                        binding.zekatMiktariDeger.setVisibility(View.INVISIBLE);
                        binding.borclarButton.setVisibility(View.VISIBLE);
                        binding.varliklarButton.setVisibility(View.VISIBLE);
                        binding.hesaplaButton.setVisibility(View.VISIBLE);
                        binding.sifirlaButton.setVisibility(View.INVISIBLE);
                        binding.duzenleButton.setVisibility(View.INVISIBLE);
                        binding.kapatButton.setVisibility(View.INVISIBLE);

                        binding.tlTextView.setText("");
                        binding.dolarTextView.setText("");
                        binding.euroTextView.setText("");
                        binding.tamAltinTexView.setText("");
                        binding.yarimAltinTextView.setText("");
                        binding.ceyrekAltinTextView.setText("");
                        binding.ayar14TextView.setText("");
                        binding.ayar18TextView.setText("");
                        binding.ayar22TextView.setText("");
                        binding.gramAltinTextView.setText("");

                        binding.borctlTextView.setText("");
                        binding.borcdolarTextView.setText("");
                        binding.borceuroTextView.setText("");
                        binding.borctamAltinTexView.setText("");
                        binding.borcyarimAltinTextView.setText("");
                        binding.borcceyrekAltinTextView.setText("");
                        binding.borcayar14TextView.setText("");
                        binding.borcayar18TextView.setText("");
                        binding.borcayar22TextView.setText("");
                        binding.borcgramAltinTextView.setText("");

                        varlikTl = 0;
                        varlikDolar = 0;
                        varlikeuro = 0;
                        varlikFullGold = 0;
                        varlikHalfGold = 0;
                        varlikQuarterGold = 0;
                        varlikcarat14 = 0;
                        varlikcarat18 = 0;
                        varlikcarat22 = 0;
                        varlikgram = 0;

                        borcTl = 0;
                        borcDolar = 0;
                        borceuro = 0;
                        borcFullGold = 0;
                        borcHalfGold = 0;
                        borcQuarterGold = 0;
                        borccarat14 = 0;
                        borccarat18 = 0;
                        borccarat22 = 0;
                        borcgram = 0;


                    }
                });

                Button duzenleButton = binding.duzenleButton;
                duzenleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        binding.infoBack.setVisibility(View.INVISIBLE);
                        binding.nisabMiktariText.setVisibility(View.INVISIBLE);
                        binding.nisabMiktariDeger.setVisibility(View.INVISIBLE);
                        binding.zekatMiktariText.setVisibility(View.INVISIBLE);
                        binding.zekatMiktariDeger.setVisibility(View.INVISIBLE);
                        binding.borclarButton.setVisibility(View.VISIBLE);
                        binding.varliklarButton.setVisibility(View.VISIBLE);
                        binding.hesaplaButton.setVisibility(View.VISIBLE);
                        binding.sifirlaButton.setVisibility(View.INVISIBLE);
                        binding.duzenleButton.setVisibility(View.INVISIBLE);
                        binding.kapatButton.setVisibility(View.INVISIBLE);

                    }
                });

                Button kapatButtonn = binding.kapatButton;

                kapatButtonn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent closeZekatIntent = new Intent(ZekatMatikActivity.this, MainActivity.class);
                        startActivity(closeZekatIntent);

                    }
                });













            }
        });

        Button infoCloseButton = binding.infoBackCloseButton;

        infoCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.infoBack.setVisibility(View.INVISIBLE);
                binding.girisText.setVisibility(View.INVISIBLE);
                binding.borclarButton.setVisibility(View.VISIBLE);
                binding.hesaplaButton.setVisibility(View.VISIBLE);
                binding.varliklarButton.setVisibility(View.VISIBLE);
                binding.infoBackCloseButton.setVisibility(View.INVISIBLE);



            }
        });





    }

    private void getDolarCurrency() {

        DocumentReference usdRef = firebaseFirestore.collection("CurrencyData").document("USD");
        usdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        dolarCr = (double) document.get("selling");
                        System.out.println(dolarCr);

                        binding.dolarTextView.setHint("Dolar (" + dolarCr + " TL)");
                        binding.borcdolarTextView.setHint("Dolar (" + dolarCr + " TL)");

                    } else {
                        System.out.println("Olumsuz");
                    }

                } else {

                    System.out.println("cekme başarısız");
                }

            }
        });





    }

    private void getEuroCurrency() {

        DocumentReference usdRef = firebaseFirestore.collection("CurrencyData").document("EUR");
        usdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        euroCr = (double) document.get("selling");
                        System.out.println(euroCr);

                        binding.euroTextView.setHint("Euro (" + euroCr + " TL)");
                        binding.borceuroTextView.setHint("Euro (" + euroCr + " TL)");

                    } else {
                        System.out.println("Olumsuz");
                    }

                } else {

                    System.out.println("cekme başarısız");
                }

            }
        });





    }

    private void getFullGoldCurrency() {

        DocumentReference usdRef = firebaseFirestore.collection("CurrencyData").document("FullGold");
        usdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        fullGoldCr = (double) document.get("selling");
                        System.out.println(fullGoldCr);

                        binding.tamAltinTexView.setHint("Tam Altın (" + fullGoldCr + " TL)");
                        binding.borctamAltinTexView.setHint("Tam Altın (" + fullGoldCr + " TL)");

                    } else {
                        System.out.println("Olumsuz");
                    }

                } else {

                    System.out.println("cekme başarısız");
                }

            }
        });





    }

    private void getHalfGoldCurrency() {

        DocumentReference usdRef = firebaseFirestore.collection("CurrencyData").document("HalfGold");
        usdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        halfGoldCr = (double) document.get("selling");
                        System.out.println(halfGoldCr);

                        binding.yarimAltinTextView.setHint("Yarım Altın (" + halfGoldCr + " TL)");
                        binding.borcyarimAltinTextView.setHint("Yarım Altın (" + halfGoldCr + " TL)");

                    } else {
                        System.out.println("Olumsuz");
                    }

                } else {

                    System.out.println("cekme başarısız");
                }

            }
        });





    }

    private void getQuarterGoldCurrency() {

        DocumentReference usdRef = firebaseFirestore.collection("CurrencyData").document("QuarterGold");
        usdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        quarterGoldCr = (double) document.get("selling");
                        System.out.println(quarterGoldCr);

                        binding.ceyrekAltinTextView.setHint("Çeyrek Altın (" + quarterGoldCr + " TL)");
                        binding.borcceyrekAltinTextView.setHint("Çeyrek Altın (" + quarterGoldCr + " TL)");

                    } else {
                        System.out.println("Olumsuz");
                    }

                } else {

                    System.out.println("cekme başarısız");
                }

            }
        });





    }

    private void getcarats14Currency() {

        DocumentReference usdRef = firebaseFirestore.collection("CurrencyData").document("Gold14");
        usdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        carats14Cr = (double) document.get("selling");
                        System.out.println(carats14Cr);

                        binding.ayar14TextView.setHint("14 Ayar Altın (" + carats14Cr + " TL)");
                        binding.borcayar14TextView.setHint("14 Ayar Altın (" + carats14Cr + " TL)");

                    } else {
                        System.out.println("Olumsuz");
                    }

                } else {

                    System.out.println("cekme başarısız");
                }

            }
        });





    }

    private void getcarrats18Currency() {

        DocumentReference usdRef = firebaseFirestore.collection("CurrencyData").document("Gold18");
        usdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        carats18Cr = (double) document.get("selling");
                        System.out.println(carats18Cr);

                        binding.ayar18TextView.setHint("18 Ayar Altın (" + carats18Cr + " TL)");
                        binding.borcayar18TextView.setHint("18 Ayar Altın (" + carats18Cr + " TL)");

                    } else {
                        System.out.println("Olumsuz");
                    }

                } else {

                    System.out.println("cekme başarısız");
                }

            }
        });





    }

    private void getcarrats22Currency() {

        DocumentReference usdRef = firebaseFirestore.collection("CurrencyData").document("Gold22");
        usdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        carats22Cr = (double) document.get("selling");
                        System.out.println(carats22Cr);

                        binding.ayar22TextView.setHint("22 Ayar Altın (" + carats22Cr + " TL)");
                        binding.borcayar22TextView.setHint("22 Ayar Altın (" + carats22Cr + " TL)");

                    } else {
                        System.out.println("Olumsuz");
                    }

                } else {

                    System.out.println("cekme başarısız");
                }

            }
        });





    }

    private void getGramCurrency() {

        DocumentReference usdRef = firebaseFirestore.collection("CurrencyData").document("GramGold");
        usdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        gramCr = (double) document.get("selling");
                        System.out.println(gramCr);

                        binding.gramAltinTextView.setHint("Gram Altın (" + gramCr + " TL)");
                        binding.borcgramAltinTextView.setHint("Gram Altın (" + gramCr + " TL)");

                    } else {
                        System.out.println("Olumsuz");
                    }

                } else {

                    System.out.println("cekme başarısız");
                }

            }
        });





    }




}