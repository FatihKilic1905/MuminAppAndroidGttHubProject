package com.fatihkilic.muminappandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fatihkilic.muminappandroid.databinding.ActivityMyAccountBinding;
import com.fatihkilic.muminappandroid.databinding.ActivitySifremiUnuttumBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class SifremiUnuttumActivity extends AppCompatActivity {

    private ActivitySifremiUnuttumBinding binding;

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifremi_unuttum);


        binding = ActivitySifremiUnuttumBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();


        binding.sifremiUnutumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = binding.emailTextView.getText().toString();


                if (email.equals("")) {


                    Toast.makeText(SifremiUnuttumActivity.this, "Lütfen Email adresiniz girin!",Toast.LENGTH_LONG).show();


                } else {

                    auth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(@NonNull Void unused) {


                            Toast.makeText(SifremiUnuttumActivity.this, "Lütfen Email adresinizi kontrol ediniz!! Email adresinize şifre sıfırlama linki gönderilmiştir.",Toast.LENGTH_LONG).show();
                            Intent resetIntent = new Intent(SifremiUnuttumActivity.this, MainActivity.class);
                            startActivity(resetIntent);


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(SifremiUnuttumActivity.this, "İnternet bağlantısında bir problem var. Lütfen daha sonra tekrar deneyin.",Toast.LENGTH_LONG).show();



                        }
                    });


                }



            }
        });





    }
}