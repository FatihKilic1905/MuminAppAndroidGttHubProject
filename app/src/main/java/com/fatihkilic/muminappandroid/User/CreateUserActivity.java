package com.fatihkilic.muminappandroid.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.ZikirMatik.ZikirMatikMainActivity;
import com.fatihkilic.muminappandroid.databinding.ActivityCreateUserBinding;
import com.fatihkilic.muminappandroid.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreateUserActivity extends AppCompatActivity {

    private ActivityCreateUserBinding binding;


    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;

    ArrayList<String> userArray;
    ArrayList<String> emailArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);


        binding = ActivityCreateUserBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        userArray = new ArrayList<>();
        emailArray = new ArrayList<>();

        getUsersVoid();



        EditText userNameTextView = binding.usernameTextView;
        EditText emailTextView = binding.emailTextView;
        EditText sifreTextView = binding.paswordTextView;
        EditText sifreTekrarTextview = binding.againPasswordTextView;


        Button saveButton = binding.saveButton;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println(userArray);

               String un = userNameTextView.getText().toString();
               String em = emailTextView.getText().toString();
               String sifre = sifreTextView.getText().toString();
               String sifreTekrar = sifreTekrarTextview.getText().toString();


                if (un.equals("") || em.equals("") || sifre.equals("")|| sifreTekrar.equals("")) {


                    Toast.makeText(CreateUserActivity.this,"Lütfen bütün alanları doldurun!",Toast.LENGTH_LONG).show();


                } else {

                    if (sifre.equals(sifreTekrar)) {


                        if (un.contains(" ") || un.contains("A") || un.contains("B") || un.contains("C") || un.contains("Ç") || un.contains("D") || un.contains("E") || un.contains("F") || un.contains("G")
                                || un.contains("Ğ") || un.contains("H") || un.contains("I") || un.contains("İ") || un.contains("J") || un.contains("K") || un.contains("L") || un.contains("M") || un.contains("N") || un.contains("O")
                                || un.contains("Ö") || un.contains("P") || un.contains("R") || un.contains("S") || un.contains("Ş") || un.contains("T") || un.contains("U") || un.contains("Ü") || un.contains("V") || un.contains("Y")
                                || un.contains("Z") || un.contains("X") || un.contains("W") || un.contains("Q") || un.contains(",") || un.contains("-") || un.contains("=") || un.contains(")") || un.contains("(") || un.contains("/")
                                || un.contains("-") || un.contains("+") || un.contains("%") || un.contains("^") || un.contains("!") || un.contains("é") || un.contains("&") || un.contains(";") || un.contains("$") || un.contains("{")
                                || un.contains("}") || un.contains("[") || un.contains("]") || un.contains("?")) {


                            Toast.makeText(CreateUserActivity.this, "Kullanıcı adı büyük harf, boşluk, özel karakterler(!'^+%&/()=?-) içeremez.", Toast.LENGTH_LONG).show();

                        } else if (userArray.contains(un)) {

                            Toast.makeText(CreateUserActivity.this, "Bu kullanıcı adı daha önce kaydedilmiş. Lütfen farklı bir kullanıcı adı seçin.", Toast.LENGTH_LONG).show();

                        } else if (emailArray.contains(em)) {

                            Toast.makeText(CreateUserActivity.this, "Bu email kaydedilmiş. Lütfen farklı email adresi girin.", Toast.LENGTH_LONG).show();

                        } else {


                            auth.createUserWithEmailAndPassword(emailTextView.getText().toString(), sifreTextView.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(@NonNull AuthResult authResult) {


                                    HashMap<String, Object> createUserData = new HashMap<>();
                                    createUserData.put("image", "");
                                    createUserData.put("userName", un);
                                    createUserData.put("email", em);
                                    createUserData.put("name","");
                                    createUserData.put("surName","");
                                    createUserData.put("birthday","");
                                    createUserData.put("gender","");
                                    createUserData.put("description","");
                                    createUserData.put("country","");
                                    createUserData.put("state","");
                                    createUserData.put("dateOfRegistration", FieldValue.serverTimestamp());
                                    createUserData.put("userNameDate", FieldValue.serverTimestamp());

                                    firebaseFirestore.collection("User").document(em).set(createUserData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(@NonNull Void unused) {

                                            auth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(@NonNull Void unused) {

                                                    Toast.makeText(CreateUserActivity.this, "Tebrikler. Kaydınız başarı ile oluşturuldu. Email adresinize gelen mesajı onayladıktan sonra giriş yababilirsiniz.", Toast.LENGTH_LONG).show();
                                                    Intent saveIntent = new Intent(CreateUserActivity.this, SignInActivity.class);
                                                    finish();
                                                    startActivity(saveIntent);

                                                }
                                            });

                                        }
                                    });

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(CreateUserActivity.this, "Kaydınız tamamlanmadı. İnternet bağlantınızda bir problem olabilir.", Toast.LENGTH_LONG).show();

                                }
                            });
                        }

                    } else {


                        Toast.makeText(CreateUserActivity.this,"Şifreler aynı değil!",Toast.LENGTH_LONG).show();

                    }






                }




            }
        });





    }


    public void getUsersVoid () {


        firebaseFirestore.collection("User").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    Toast.makeText(CreateUserActivity.this,error.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                }

                if (value != null) {


                    for (DocumentSnapshot snapshot : value.getDocuments()) {

                        Map<String, Object> data = snapshot.getData();

                        String users = (String) data.get("userName");

                        userArray.add(users);

                        String email = (String) data.get("email");

                        emailArray.add(email);

                        System.out.println("user "+ userArray);


                    }

                }



            }
        });



    }





}