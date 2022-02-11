package com.fatihkilic.muminappandroid.ZikirMatik;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.User.MyAccountEditActivity;
import com.fatihkilic.muminappandroid.User.PickerListCountry;
import com.fatihkilic.muminappandroid.User.PickerListGender;
import com.fatihkilic.muminappandroid.databinding.ActivityMyAccountEditBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityZikirAddBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.onesignal.OneSignal;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

public class ZikirAddActivity extends AppCompatActivity {


    private ActivityZikirAddBinding binding;

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    DatePicker endDatePicker;

    String currentUser;
    String myUserName;



    private static final String ONESIGNAL_APP_ID = "1966721c-a30c-4299-9d7a-38e084b98072";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zikir_add);

        binding = ActivityZikirAddBinding.inflate(getLayoutInflater());
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

        binding.emptyBackground.setVisibility(View.INVISIBLE);
        binding.manuelZikirBackground.setVisibility(View.INVISIBLE);
        binding.manuelzikirEditText.setVisibility(View.INVISIBLE);
        binding.manurlzikirButton.setVisibility(View.INVISIBLE);

        binding.zikirlerpickerList.setVisibility(View.INVISIBLE);

        binding.numberPickerBackground.setVisibility(View.INVISIBLE);
        binding.numberPickerOkButton.setVisibility(View.INVISIBLE);

        binding.datePickerBackground.setVisibility(View.INVISIBLE);
        binding.datePickerHiddenButton.setVisibility(View.INVISIBLE);


        endDatePicker = binding.endDateDatePicker;
        endDatePicker.setVisibility(View.INVISIBLE);

        currentUser = auth.getCurrentUser().getEmail();
        getProfile();

        binding.zikirNameEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                binding.emptyBackground.setVisibility(View.VISIBLE);
                binding.zikirlerpickerList.setVisibility(View.VISIBLE);
                binding.numberPickerBackground.setVisibility(View.VISIBLE);
                binding.numberPickerOkButton.setVisibility(View.VISIBLE);
                binding.zikirNameEditText.setEnabled(false);

            }
        });

        binding.zikirBitisTarihiEditText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {




                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {


                    Calendar nowTime = Calendar.getInstance();
                    int nowYear = nowTime.get(Calendar.YEAR);
                    int nowMonth = nowTime.get(Calendar.MONTH);
                    int nowDay = nowTime.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(ZikirAddActivity.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                                    binding.zikirBitisTarihiEditText.setText(day + "." + month + "." + year) ;


                                }
                            },  nowYear, nowMonth, nowDay);





                    datePickerDialog.show();



                } else {


                    System.out.println("datepickerr");


                    binding.emptyBackground.setVisibility(View.VISIBLE);
                    endDatePicker.setVisibility(View.VISIBLE);
                    binding.zikirBitisTarihiEditText.setEnabled(false);
                    binding.datePickerBackground.setVisibility(View.VISIBLE);
                    binding.datePickerHiddenButton.setVisibility(View.VISIBLE);

                    Calendar nowTime = Calendar.getInstance();
                    int nowYear = nowTime.get(Calendar.YEAR);
                    int nowMonth = nowTime.get(Calendar.MONTH);
                    int nowDay = nowTime.get(Calendar.DAY_OF_MONTH);


                    endDatePicker.init(nowYear, nowMonth, nowDay, null);

                    endDatePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                        @Override
                        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                            StringBuilder birtdayString = new StringBuilder();

                            birtdayString.append(dayOfMonth).append(".").append(monthOfYear).append(".").append(year);

                            binding.zikirBitisTarihiEditText.setText(birtdayString);




                        }
                    });


                }

            }
        });


        binding.datePickerHiddenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                binding.emptyBackground.setVisibility(View.INVISIBLE);
                endDatePicker.setVisibility(View.INVISIBLE);
                binding.datePickerBackground.setVisibility(View.INVISIBLE);
                binding.datePickerHiddenButton.setVisibility(View.INVISIBLE);
                binding.zikirBitisTarihiEditText.setEnabled(true);


            }
        });



        binding.numberPickerOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String zikirNames = binding.zikirNameEditText.getText().toString();

                if (zikirNames.equals("Subhanallah")) {

                    binding.zikirDuasiEditText.setEnabled(false);

                    binding.zikirDuasiEditText.setText("Subhanallahi velhamdülillahi ve la ilaheillallahu vallahu ekber");

                    binding.zikirNameEditText.setEnabled(true);
                    binding.numberPickerBackground.setVisibility(View.INVISIBLE);
                    binding.numberPickerOkButton.setVisibility(View.INVISIBLE);
                    binding.emptyBackground.setVisibility(View.INVISIBLE);

                } else if (zikirNames.equals("İstiğfar")) {

                    binding.zikirDuasiEditText.setEnabled(false);

                    binding.zikirDuasiEditText.setText("Estağfirullah. Estağfirullahe'l-azîm el-kerîm, ellezî lâ ilâhe illâ hüve'l-hayyü'l-kayyûmü ve etûbü ileyhi, tevbete abdin zâlimin li-nefsihî, lâ yemlikü li-nefsihî mevten velâ hayâten velâ nüşûrâ. Ve es-elühü't-tevbete ve'l-mağfirete ve'l-hidâyete lenâ, innehû, hüve't-tevvâbü'r-rahîm.” “Ya rabbi!");

                    binding.zikirNameEditText.setEnabled(true);
                    binding.numberPickerBackground.setVisibility(View.INVISIBLE);
                    binding.numberPickerOkButton.setVisibility(View.INVISIBLE);
                    binding.emptyBackground.setVisibility(View.INVISIBLE);

                } else if (zikirNames.equals("Salatı Tefriciye")) {

                    binding.zikirDuasiEditText.setEnabled(false);

                    binding.zikirDuasiEditText.setText("Allahumme salli salâten kâmileten ve sellim selâmen tâmmen alâ-seyyidina Muhammedin ellezi tenhallü bihi'l 'ukadu ve tenfericu bihi'l-kürabu ve tukdâ bihi'l-havâicu ve tünâlü bihi'r-rağâibu ve husnu'l-havâtimi ve yusteska'l-ğamâmu bi-vechihi'l-kerîmi ve 'alâ âlihi ve sahbihi fî-külli lemhatin ve nefesin bi-'adedi külli ma'lûmin lek.");

                    binding.zikirNameEditText.setEnabled(true);
                    binding.numberPickerBackground.setVisibility(View.INVISIBLE);
                    binding.numberPickerOkButton.setVisibility(View.INVISIBLE);
                    binding.emptyBackground.setVisibility(View.INVISIBLE);

                } else if (zikirNames.equals("Salavatı Şerif")) {

                    binding.zikirDuasiEditText.setEnabled(false);

                    binding.zikirDuasiEditText.setText("Allahümme salli alâ Muhammed'in ve alâ âli Muhammedin, kemâ salleyte alâ İbrahime ve alâ âli İbrahim, inneke hamîdun mecîd.");

                    binding.zikirNameEditText.setEnabled(true);
                    binding.numberPickerBackground.setVisibility(View.INVISIBLE);
                    binding.numberPickerOkButton.setVisibility(View.INVISIBLE);
                    binding.emptyBackground.setVisibility(View.INVISIBLE);

                } else if (zikirNames.equals("Hasbunallah")) {

                    binding.zikirDuasiEditText.setEnabled(false);

                    binding.zikirDuasiEditText.setText("Hasbünallahü Ve Nimel Vekil");

                    binding.zikirNameEditText.setEnabled(true);
                    binding.numberPickerBackground.setVisibility(View.INVISIBLE);
                    binding.numberPickerOkButton.setVisibility(View.INVISIBLE);
                    binding.emptyBackground.setVisibility(View.INVISIBLE);

                } else if (zikirNames.equals("Kelime-i Tevhid")) {

                    binding.zikirDuasiEditText.setEnabled(false);

                    binding.zikirDuasiEditText.setText("Lâ iâhe illallah Muhammedür-resûlüllah");

                    binding.zikirNameEditText.setEnabled(true);
                    binding.numberPickerBackground.setVisibility(View.INVISIBLE);
                    binding.emptyBackground.setVisibility(View.INVISIBLE);

                } else if (zikirNames.equals("Ben Ekleyeceğim")) {


                    binding.numberPickerBackground.setVisibility(View.INVISIBLE);
                    binding.emptyBackground.setVisibility(View.VISIBLE);
                    binding.manuelZikirBackground.setVisibility(View.VISIBLE);
                    binding.manuelzikirEditText.setVisibility(View.VISIBLE);
                    binding.manurlzikirButton.setVisibility(View.VISIBLE);
                    binding.zikirlerpickerList.setVisibility(View.INVISIBLE);
                    binding.numberPickerBackground.setVisibility(View.INVISIBLE);
                    binding.numberPickerOkButton.setVisibility(View.INVISIBLE);
                    binding.zikirDuasiEditText.setEnabled(true);
                    binding.zikirNameEditText.setEnabled(true);

                }







            }
        });


        binding.manurlzikirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String zikirName = binding.manuelzikirEditText.getText().toString();

                if (zikirName.equals("")) {

                    Toast.makeText(ZikirAddActivity.this, "Lütfen zikir adı giriniz.",Toast.LENGTH_LONG).show();


                } else {


                    binding.zikirNameEditText.setText(zikirName);

                    binding.emptyBackground.setVisibility(View.INVISIBLE);
                    binding.manuelZikirBackground.setVisibility(View.INVISIBLE);
                    binding.manuelzikirEditText.setVisibility(View.INVISIBLE);
                    binding.manurlzikirButton.setVisibility(View.INVISIBLE);

                    binding.zikirDuasiEditText.setText("");

                    binding.zikirDuasiEditText.setHint("Lütfen " + zikirName + " isimli zikrin duasını giriniz.");

                }

            }
        });


        PickerListZikirler.initCountryList();

        binding.zikirlerpickerList.setMaxValue(PickerListZikirler.getPickerListZikirlerArrayList().size()-1);
        binding.zikirlerpickerList.setMinValue(0);
        binding.zikirlerpickerList.setDisplayedValues(PickerListZikirler.zikirNames());

        binding.zikirlerpickerList.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                binding.zikirNameEditText.setText(PickerListZikirler.getPickerListZikirlerArrayList().get(newVal).getZikirler());



            }
        });





        binding.zikriBaslatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String zikirName = binding.zikirNameEditText.getText().toString();
                String zikirNiyeti = binding.zikirNiyetiEditText.getText().toString();
                String zikirSayisi = binding.zikirSayisiEditText.getText().toString();
                String zikirBitisTarihi = binding.zikirBitisTarihiEditText.getText().toString();
                String zikirDuasi = binding.zikirDuasiEditText.getText().toString();

                String UID = UUID.randomUUID().toString();



                if (zikirName.equals("") || zikirNiyeti.equals("") || zikirSayisi.equals("") || zikirBitisTarihi.equals("") || zikirDuasi.equals("")) {

                    Toast.makeText(ZikirAddActivity.this, "Lütfen bütün alanları doldurunuz",Toast.LENGTH_LONG).show();



                } else {

                  HashMap<String, Object> newZikirData = new HashMap<>();

                  newZikirData.put("beginDate", FieldValue.serverTimestamp());

                  newZikirData.put("endDate", zikirBitisTarihi);
                  newZikirData.put("zikirDescription",zikirNiyeti);
                  newZikirData.put("zikirCount", Integer.parseInt(zikirSayisi));
                  newZikirData.put("zikirName", zikirName);
                  newZikirData.put("ZikirCompleteCount", 0);
                  newZikirData.put("zikirPray",zikirDuasi);
                  newZikirData.put("nickname",myUserName);
                  newZikirData.put("zikirStatus","1");



                    firebaseFirestore.collection("ZikirMatik").document(currentUser).collection("myZikir").document(UID).set(newZikirData).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(@NonNull Void unused) {

                            Toast.makeText(ZikirAddActivity.this, "Tebrikler. profiliniz güncellendi.", Toast.LENGTH_LONG).show();

                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(ZikirAddActivity.this, "Güncelleme başarısız! İnternet bağlantısında bir problem var.", Toast.LENGTH_LONG).show();

                        }

                    });

                }


                // Zikirdavetiye Katılımcılar


                // Zikir kendi kullanıcı adını ekle





            }
        });



    }


    public void getProfile () {

        DocumentReference usdRef = firebaseFirestore.collection("User").document(currentUser);
        usdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        myUserName = (String) document.get("userName");



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