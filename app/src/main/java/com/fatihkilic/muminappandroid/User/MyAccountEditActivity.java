package com.fatihkilic.muminappandroid.User;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.fatihkilic.muminappandroid.R;

import com.fatihkilic.muminappandroid.ZikirMatik.ZikirAddActivity;
import com.fatihkilic.muminappandroid.databinding.ActivityMyAccountEditBinding;
import com.fatihkilic.muminappandroid.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.onesignal.OneSignal;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MyAccountEditActivity extends AppCompatActivity {

    private ActivityMyAccountEditBinding binding;

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    private static final String ONESIGNAL_APP_ID = "1966721c-a30c-4299-9d7a-38e084b98072";

    String userName;
    String name;
    String surName;
    String birthdayString;
    String gender;
    String email;
    String userDescription;
    String country;
    String province;
    String image;
    Date dateOfRegistration;
    Date userNameDate;

    Calendar bithday;

    String pickerStatus;

    ArrayList<String> genderArray;
    ArrayList<String> countryArray;
    ArrayList<String> stateArray;

    ActivityResultLauncher<Intent> activityResultLauncher;
    ActivityResultLauncher<String> permisionLauncher;
    Uri ppImageData;
    Bitmap selectedBitmap;
    String currentEmail;

    DatePicker  bdDAteBicker;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_edit);

        binding = ActivityMyAccountEditBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        bdDAteBicker = binding.birthdayDatePicker;
        bdDAteBicker.setVisibility(View.INVISIBLE);

        registerLauncher();


        Button saveButton = binding.saveButton;


        currentEmail = auth.getCurrentUser().getEmail();

        getProfile();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (ppImageData != null ) {

                    storageReference.child(currentEmail).child("profilPhoto").putFile(ppImageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(@NonNull UploadTask.TaskSnapshot taskSnapshot) {


                            StorageReference newreference = firebaseStorage.getReference("imagdata yazılacak");
                            newreference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(@NonNull Uri uri) {

                                    String downloadPPImageData = uri.toString();

                                    HashMap<String, Object>profileEditData = new HashMap<>();
                                    profileEditData.put("image", downloadPPImageData);
                                    profileEditData.put("userName", binding.usernameTextView.getText().toString());
                                    profileEditData.put("name",binding.NameTextView.getText().toString());
                                    profileEditData.put("surName",binding.surNameTextView.getText().toString());
                                    profileEditData.put("birthday",binding.birthdayTextView.getText().toString());
                                    profileEditData.put("gender",binding.genderTextView.getText().toString());
                                    profileEditData.put("description",binding.descriptionTextView.getText().toString());
                                    profileEditData.put("country",binding.countryTextView.getText().toString());
                                    profileEditData.put("state",binding.provinceTextView.getText().toString());
                                    profileEditData.put("userNameDate", FieldValue.serverTimestamp());

                                    firebaseFirestore.collection("User").document(currentEmail).set(profileEditData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(@NonNull Void unused) {

                                            Toast.makeText(MyAccountEditActivity.this, "Tebrikler. profiliniz güncellendi.", Toast.LENGTH_LONG).show();


                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                            Toast.makeText(MyAccountEditActivity.this, "Güncelleme başarısız! İnternet bağlantısında bir problem var.", Toast.LENGTH_LONG).show();


                                        }
                                    });


                                }
                            });







                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(MyAccountEditActivity.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                        }
                    });

                } else {




                    HashMap<String, Object>profileEditData = new HashMap<>();
                    profileEditData.put("image", "downloadPPImageData");
                    profileEditData.put("userName", binding.usernameTextView.getText().toString());
                    profileEditData.put("name",binding.NameTextView.getText().toString());
                    profileEditData.put("surName",binding.surNameTextView.getText().toString());
                    profileEditData.put("birthday",binding.birthdayTextView.getText().toString());
                    profileEditData.put("gender",binding.genderTextView.getText().toString());
                    profileEditData.put("description",binding.descriptionTextView.getText().toString());
                    profileEditData.put("country",binding.countryTextView.getText().toString());
                    profileEditData.put("state",binding.provinceTextView.getText().toString());
                    profileEditData.put("userNameDate", FieldValue.serverTimestamp());

                    firebaseFirestore.collection("User").document(currentEmail).set(profileEditData).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(@NonNull Void unused) {

                            Toast.makeText(MyAccountEditActivity.this, "Tebrikler. profiliniz güncellendi.", Toast.LENGTH_LONG).show();


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(MyAccountEditActivity.this, "Güncelleme başarısız! İnternet bağlantısında bir problem var.", Toast.LENGTH_LONG).show();


                        }
                    });




                }

            }
        });



        binding.genderpickerList.setVisibility(View.INVISIBLE);
        binding.countrypickerList.setVisibility(View.INVISIBLE);
        binding.statepickerList.setVisibility(View.INVISIBLE);
        binding.pickerBackground.setVisibility(View.INVISIBLE);
        binding.pickerSaveButton.setVisibility(View.INVISIBLE);




        PickerListCountry.initCountryList();

        binding.countrypickerList.setMaxValue(PickerListCountry.getPickerListCountryArrayList().size()-1);
        binding.countrypickerList.setMinValue(0);
        binding.countrypickerList.setDisplayedValues(PickerListCountry.countryNames());

        binding.countrypickerList.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                binding.countryTextView.setText(PickerListCountry.getPickerListCountryArrayList().get(newVal).getCountry());

            }
        });



        PickerListState.initStateList();

        binding.statepickerList.setMaxValue(PickerListState.getPickerListStateArrayList().size()-1);
        binding.statepickerList.setMinValue(0);
        binding.statepickerList.setDisplayedValues(PickerListState.stateNames());



        binding.statepickerList.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                binding.provinceTextView.setText(PickerListState.getPickerListStateArrayList().get(newVal).getState());

            }
        });


        Button pickerSaveButton = binding.pickerSaveButton;
        pickerSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (pickerStatus.equals("Gender")) {

                    binding.pickerBackground.setVisibility(View.INVISIBLE);
                    binding.genderpickerList.setVisibility(View.INVISIBLE);
                    binding.pickerSaveButton.setVisibility(View.INVISIBLE);
                    binding.saveButton.setEnabled(true);


                } else if (pickerStatus.equals("Country")){

                    binding.pickerBackground.setVisibility(View.INVISIBLE);
                    binding.countrypickerList.setVisibility(View.INVISIBLE);
                    binding.pickerSaveButton.setVisibility(View.INVISIBLE);
                    binding.saveButton.setEnabled(true);

                    binding.countryTextView.setText("Türkiye");


                } else if (pickerStatus.equals("State")) {

                    binding.pickerBackground.setVisibility(View.INVISIBLE);
                    binding.statepickerList.setVisibility(View.INVISIBLE);
                    binding.pickerSaveButton.setVisibility(View.INVISIBLE);
                    binding.saveButton.setEnabled(true);




                } else if (pickerStatus.equals("Birthday")) {

                    binding.pickerBackground.setVisibility(View.INVISIBLE);
                    bdDAteBicker.setVisibility(View.INVISIBLE);
                    binding.pickerSaveButton.setVisibility(View.INVISIBLE);
                    binding.saveButton.setEnabled(true);

                }



            }
        });




        binding.genderTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                binding.genderpickerList.setVisibility(View.VISIBLE);
                binding.pickerBackground.setVisibility(View.VISIBLE);
                binding.pickerSaveButton.setVisibility(View.VISIBLE);
                binding.saveButton.setEnabled(false);
                pickerStatus = "Gender";

            }
        });

        binding.countryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                binding.countrypickerList.setVisibility(View.VISIBLE);
                binding.pickerBackground.setVisibility(View.VISIBLE);
                binding.pickerSaveButton.setVisibility(View.VISIBLE);
                binding.saveButton.setEnabled(false);
                pickerStatus = "Country";

            }
        });

        binding.provinceTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                binding.statepickerList.setVisibility(View.VISIBLE);
                binding.pickerBackground.setVisibility(View.VISIBLE);
                binding.pickerSaveButton.setVisibility(View.VISIBLE);
                binding.saveButton.setEnabled(false);
                pickerStatus = "State";

            }
        });


        binding.ppImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ContextCompat.checkSelfPermission(MyAccountEditActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.shouldShowRequestPermissionRationale(MyAccountEditActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)) {

                        Snackbar.make(view,"Resim galerisine erişim izni gerekiyor.", Snackbar.LENGTH_INDEFINITE).setAction("İzin ver", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                permisionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);

                            }
                        }).show();

                    } else {

                        permisionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
                    }

                } else {

                    Intent intentToGalery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activityResultLauncher.launch(intentToGalery);

                }
            }
        });

    }
    
    
    private void registerLauncher() {
        
        
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                if (result.getResultCode() == RESULT_OK) {

                    Intent intentFromResult = result.getData();

                    if (intentFromResult != null) {

                       ppImageData = intentFromResult.getData();

                       binding.ppImageView.setImageURI(ppImageData);

                  


                    }


                }

            }
        });
        
        permisionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {

                if (result) {

                    Intent intentToGalery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activityResultLauncher.launch(intentToGalery);


                } else {


                    Toast.makeText(MyAccountEditActivity.this,"Fotoğraf galerisine erişmek için izin gerekli!",Toast.LENGTH_LONG).show();

                }



            }
        });
        
    }


    public void getProfile () {

        DocumentReference usdRef = firebaseFirestore.collection("User").document(currentEmail);
        usdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        userName = (String) document.get("userName");
                        name = (String) document.get("name");
                        surName = (String) document.get("surName");
                        birthdayString = (String) document.get("birthday");
                        gender = (String) document.get("gender");
                        userDescription = (String) document.get("description");
                        country = (String) document.get("country");
                        province = (String) document.get("state");
                        email = (String) document.get("email");
                        image = (String) document.get("image");





                        Picasso.get().load(image).into(binding.ppImageView);
                        binding.usernameTextView.setText(userName);
                        binding.NameTextView.setText(name);
                        binding.surNameTextView.setText(surName);
                        binding.birthdayTextView.setText(birthdayString);
                        binding.genderTextView.setText(gender);
                        binding.descriptionTextView.setText(userDescription);
                        binding.countryTextView.setText(country);
                        binding.provinceTextView.setText(province);

                        if (userName.equals("")) {

                            binding.usernameTextView.setEnabled(false);

                        }

                        if (birthdayString.equals("")) {

                            binding.birthdayTextView.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {



                                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {


                                        Calendar nowTime = Calendar.getInstance();
                                        int nowYear = nowTime.get(Calendar.YEAR);
                                        int nowMonth = nowTime.get(Calendar.MONTH);
                                        int nowDay = nowTime.get(Calendar.DAY_OF_MONTH);


                                        DatePickerDialog datePickerDialog = new DatePickerDialog(MyAccountEditActivity.this,
                                                new DatePickerDialog.OnDateSetListener() {
                                                    @Override
                                                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                                                        binding.birthdayTextView.setText(day + "." + month + "." + year) ;


                                                    }
                                                },  nowYear, nowMonth, nowDay);





                                        datePickerDialog.show();



                                    } else {

                                        Calendar nowTime = Calendar.getInstance();
                                        int nowYear = nowTime.get(Calendar.YEAR);
                                        int nowMonth = nowTime.get(Calendar.MONTH);
                                        int nowDay = nowTime.get(Calendar.DAY_OF_MONTH);



                                        bdDAteBicker.setVisibility(View.VISIBLE);
                                        binding.pickerBackground.setVisibility(View.VISIBLE);
                                        binding.pickerSaveButton.setVisibility(View.VISIBLE);
                                        binding.saveButton.setEnabled(false);
                                        pickerStatus = "Birthday";

                                        bdDAteBicker.init(nowYear,nowMonth,nowDay,null);

                                        bdDAteBicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                                            @Override
                                            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                                StringBuilder birtdayString = new StringBuilder();

                                                birtdayString.append(dayOfMonth).append(".").append(monthOfYear).append(".").append(year);

                                                binding.birthdayTextView.setText(birtdayString);


                                            }
                                        });


                                    }

                                }

                            });




                        } else {


                            binding.birthdayTextView.setEnabled(false);


                        }

                        if (gender.equals("")) {

                            PickerListGender.initgenderList();

                            binding.genderpickerList.setMaxValue(PickerListGender.getPickerListGenderArrayList().size()-1);
                            binding.genderpickerList.setMinValue(0);
                            binding.genderpickerList.setDisplayedValues(PickerListGender.genderNames());

                            binding.genderpickerList.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                                @Override
                                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                                    binding.genderTextView.setText(PickerListGender.getPickerListGenderArrayList().get(newVal).getGender());

                                }
                            });



                        } else {

                            binding.genderTextView.setEnabled(false);


                        }

                        if (name.equals("")) {



                        } else {

                            binding.NameTextView.setEnabled(false);

                        }

                        if (surName.equals("")) {



                        } else {

                            binding.surNameTextView.setEnabled(false);

                        }



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