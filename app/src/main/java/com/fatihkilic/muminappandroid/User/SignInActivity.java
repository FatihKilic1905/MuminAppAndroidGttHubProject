package com.fatihkilic.muminappandroid.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.ZikirMatik.ZikirMatikMainActivity;
import com.fatihkilic.muminappandroid.databinding.ActivitySignInBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityZekatMatikBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.onesignal.OSDeviceState;
import com.onesignal.OneSignal;

import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {


    private ActivitySignInBinding binding;

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private static final String ONESIGNAL_APP_ID = "1966721c-a30c-4299-9d7a-38e084b98072";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);


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

                               OSDeviceState device = OneSignal.getDeviceState();
                               String OsPlayerId = device.getUserId();

                               String email = auth.getCurrentUser().getEmail();

                               firebaseFirestore.collection("OneSignal").whereEqualTo("email", email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                   @Override
                                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                       System.out.println("One signal id daha once kaydedilmiş");
                                   }
                               }).addOnFailureListener(new OnFailureListener() {
                                   @Override
                                   public void onFailure(@NonNull Exception e) {

                                       HashMap<String, Object> OnesignalData = new HashMap<>();
                                       OnesignalData.put("email", email);
                                       OnesignalData.put("player_id", OsPlayerId);

                                       firebaseFirestore.collection("OneSignal").add(OnesignalData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                           @Override
                                           public void onSuccess(@NonNull DocumentReference documentReference) {

                                               Intent girisYapIntent = new Intent(SignInActivity.this, ZikirMatikMainActivity.class);

                                               startActivity(girisYapIntent);

                                           }
                                       }).addOnFailureListener(new OnFailureListener() {
                                           @Override
                                           public void onFailure(@NonNull Exception e) {



                                           }
                                       });

                                   }
                               });

                           } else {

                               try {

                                   auth.signOut();
                                   Toast.makeText(SignInActivity.this, "Kullanıcı bilgileri eksik, yanlış veya E-posta onayı hala yapılmamış..", Toast.LENGTH_LONG).show();

                               } catch (Exception e) {

                                   System.out.println("cıkıs yapılmadı");

                               }


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