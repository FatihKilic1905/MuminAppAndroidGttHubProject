package com.fatihkilic.muminappandroid.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivitySignInBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityZekatMatikBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private ActivitySignInBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();



        Button signInButton = binding.signInButton;
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = binding.emailTextView.getText().toString();
                String password = binding.paswordTextView.getText().toString();

                if (email.equals("") || password.equals("")){

                    Toast.makeText(SignInActivity.this,"Email ve şifre alanlarını doldurunuz.",Toast.LENGTH_LONG).show();

                } else {

                    auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(@NonNull AuthResult authResult) {

                           if (authResult.getUser().isEmailVerified() == true) {

                               

                           }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(SignInActivity.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                        }
                    });


                }

            }
        });

        Button signUpButton = binding.signUpButton;
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });




    }
}