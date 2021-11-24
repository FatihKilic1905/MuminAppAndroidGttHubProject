package com.fatihkilic.muminappandroid.ZekatMatik;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivitySesSettingsBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityZekatMatikBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.ktx.Firebase;

import java.util.Map;

public class ZekatMatikActivity extends AppCompatActivity {

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


        binding.borctlTextView.setVisibility(View.INVISIBLE);
        binding.borcdolarTextView.setVisibility(View.INVISIBLE);
        binding.borceuroTextView.setVisibility(View.INVISIBLE);
        binding.borctamAltinTexView.setVisibility(View.INVISIBLE);
        binding.borcyarimAltinTextView.setVisibility(View.INVISIBLE);
        binding.borcceyrekAltinTextView.setVisibility(View.INVISIBLE);
        binding.borcayar22TextView.setVisibility(View.INVISIBLE);
        binding.borcayar18TextView.setVisibility(View.INVISIBLE);
        binding.borcayar14TextView.setVisibility(View.INVISIBLE);




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

                binding.tlTextView.setVisibility(View.VISIBLE);
                binding.dolarTextView.setVisibility(View.VISIBLE);
                binding.euroTextView.setVisibility(View.VISIBLE);
                binding.tamAltinTexView.setVisibility(View.VISIBLE);
                binding.yarimAltinTextView.setVisibility(View.VISIBLE);
                binding.ceyrekAltinTextView.setVisibility(View.VISIBLE);
                binding.ayar22TextView.setVisibility(View.VISIBLE);
                binding.ayar18TextView.setVisibility(View.VISIBLE);
                binding.ayar14TextView.setVisibility(View.VISIBLE);

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

                binding.tlTextView.setVisibility(View.INVISIBLE);
                binding.dolarTextView.setVisibility(View.INVISIBLE);
                binding.euroTextView.setVisibility(View.INVISIBLE);
                binding.tamAltinTexView.setVisibility(View.INVISIBLE);
                binding.yarimAltinTextView.setVisibility(View.INVISIBLE);
                binding.ceyrekAltinTextView.setVisibility(View.INVISIBLE);
                binding.ayar22TextView.setVisibility(View.INVISIBLE);
                binding.ayar18TextView.setVisibility(View.INVISIBLE);
                binding.ayar14TextView.setVisibility(View.INVISIBLE);

            }
        });


    }

    private void getDolarCurrency() {

        firebaseFirestore.collection("CurrencyData").document("USD").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    System.out.println("Veriler Eksik");

                }

                if (value != null) {



                }


            }
        });


    }


}