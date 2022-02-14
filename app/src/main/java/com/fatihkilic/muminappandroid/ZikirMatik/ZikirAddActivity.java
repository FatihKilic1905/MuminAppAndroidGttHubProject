package com.fatihkilic.muminappandroid.ZikirMatik;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.User.FriendsDetailActivity;
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
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.onesignal.OneSignal;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
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

    static ArrayList<String> kisiekleArray;
    static ArrayList<String> kisiEkleArrayList;
    static ArrayList<String> kiekleEmailArrayList;

    static String reZikirName;
    static String reZikirNiyeti;
    static String reZikirSayısı;
    static String reZikirBitisTarihi;
    static String reZikirDuasi;


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


        if (reZikirName == null) {

            binding.zikirNameEditText.setText("");
            binding.zikirNiyetiEditText.setText("");
            binding.zikirSayisiEditText.setText("");
            binding.zikirBitisTarihiEditText.setText("");
            binding.zikirDuasiEditText.setText("");


        } else {

            binding.zikirNameEditText.setText(reZikirName);
            binding.zikirNiyetiEditText.setText(reZikirNiyeti);
            binding.zikirSayisiEditText.setText(reZikirSayısı);
            binding.zikirBitisTarihiEditText.setText(reZikirBitisTarihi);
            binding.zikirDuasiEditText.setText(reZikirDuasi);
            binding.zikirDuasiEditText.setEnabled(false);

        }


        if (kisiEkleArrayList == null) {


            kisiEkleArrayList = new ArrayList<>();
            kiekleEmailArrayList = new ArrayList<>();
            StringBuilder buttonName = new StringBuilder();
            buttonName.append("Kişi Ekle ");
            buttonName.append("(");
            buttonName.append("0");
            buttonName.append(")");
            binding.kisiEkleButton.setText(buttonName.toString());

        } else {

            kisiekleArray = new ArrayList<>();
            kisiekleArray = kisiEkleArrayList;
            System.out.println("kisiekle " + kisiekleArray);
            System.out.println("kisiekle " + kiekleEmailArrayList);


            if (kisiekleArray == null) {

                StringBuilder buttonName = new StringBuilder();
                buttonName.append("Kişi Ekle ");
                buttonName.append("(");
                buttonName.append("0");
                buttonName.append(")");
                binding.kisiEkleButton.setText(buttonName.toString());


            } else {

                StringBuilder buttonName = new StringBuilder();
                buttonName.append("Kişi Ekle ");
                buttonName.append("(");
                buttonName.append(kisiekleArray.size());
                buttonName.append(")");
                binding.kisiEkleButton.setText(buttonName.toString());

            }


        }


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


        binding.kisiEkleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                reZikirName = binding.zikirNameEditText.getText().toString();
                reZikirNiyeti = binding.zikirNiyetiEditText.getText().toString();
                reZikirSayısı = binding.zikirSayisiEditText.getText().toString();
                reZikirBitisTarihi = binding.zikirBitisTarihiEditText.getText().toString();
                reZikirDuasi = binding.zikirDuasiEditText.getText().toString();
                Intent kisiEkleIntent = new Intent(ZikirAddActivity.this, KisilerActivity.class);
                startActivity(kisiEkleIntent);

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

                                    binding.zikirBitisTarihiEditText.setText(day + "." + month + "." + year);


                                }
                            }, nowYear, nowMonth, nowDay);


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

                    Toast.makeText(ZikirAddActivity.this, "Lütfen zikir adı giriniz.", Toast.LENGTH_LONG).show();


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

        binding.zikirlerpickerList.setMaxValue(PickerListZikirler.getPickerListZikirlerArrayList().size() - 1);
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

                    Toast.makeText(ZikirAddActivity.this, "Lütfen bütün alanları doldurunuz", Toast.LENGTH_LONG).show();


                } else if (kisiekleArray == null) {

                    Toast.makeText(ZikirAddActivity.this, "En az 1 Kişi eklemelisiniz", Toast.LENGTH_LONG).show();

                } else {

                    HashMap<String, Object> newZikirData = new HashMap<>();

                    newZikirData.put("beginDate", FieldValue.serverTimestamp());

                    newZikirData.put("endDate", zikirBitisTarihi);
                    newZikirData.put("zikirDescription", zikirNiyeti);
                    newZikirData.put("zikirCount", Integer.parseInt(zikirSayisi));
                    newZikirData.put("zikirName", zikirName);
                    newZikirData.put("ZikirCompleteCount", 0);
                    newZikirData.put("zikirPray", zikirDuasi);
                    newZikirData.put("nickname", myUserName);
                    newZikirData.put("zikirStatus", "1");


                    firebaseFirestore.collection("ZikirMatik").document(currentUser).collection("myZikir").document(UID).set(newZikirData).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(@NonNull Void unused) {

                            StringBuilder tebrikMesaji = new StringBuilder();
                            tebrikMesaji.append("Tebrikler");
                            tebrikMesaji.append(binding.zikirNameEditText.getText().toString());
                            tebrikMesaji.append("zikriniz başlatıldı. Zikirlerim kısmından zikrinizi takip edebilirsiniz.");


                            Toast.makeText(ZikirAddActivity.this, tebrikMesaji.toString(), Toast.LENGTH_LONG).show();

                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(ZikirAddActivity.this, "Zikir başlatılamadı!! Lütfen tekrar deneyin.", Toast.LENGTH_LONG).show();

                        }

                    });

                }


                // Zikirdavetiye Katılımcılar


                for (String usersUserName : kisiekleArray) {

                    for (String usersEmail : kiekleEmailArrayList) {


                        HashMap<String, Object> usersData = new HashMap<>();

                        usersData.put("email", usersEmail);
                        usersData.put("inviteAnsver", "0");
                        usersData.put("zikirCompleteCount", 0);
                        usersData.put("nickName", usersUserName);
                        usersData.put("zikirCount", 0);
                        usersData.put("zikirStatus", "1");

                        firebaseFirestore.collection("ZikirMatik").document(auth.getCurrentUser().getEmail()).collection("myZikir").document(UID).collection("Users").document(usersEmail).set(usersData).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(@NonNull Void unused) {


                                HashMap<String, Object> inviteData = new HashMap<>();

                                inviteData.put("email", auth.getCurrentUser().getEmail());
                                inviteData.put("zikirName", reZikirName);
                                inviteData.put("zikirCount", reZikirSayısı);
                                inviteData.put("endDate", reZikirBitisTarihi);
                                inviteData.put("nickname", myUserName);
                                inviteData.put("inviteStatus", "0");
                                inviteData.put("zikirStatus", "1");
                                inviteData.put("zikirDescription", reZikirNiyeti);
                                inviteData.put("zikirCompleteCount", 0);
                                inviteData.put("zikirMyCompleteCount", 0);
                                inviteData.put("zikirMyCount", 0);

                                firebaseFirestore.collection("ZikirMatik").document(usersEmail).collection("invitedZikir").document(UID).set(inviteData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(@NonNull Void unused) {

                                        firebaseFirestore.collection("OneSignal").whereEqualTo("email", usersEmail).addSnapshotListener(new EventListener<QuerySnapshot>() {
                                            @Override
                                            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                                                if (error != null) {

                                                    Toast.makeText(ZikirAddActivity.this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                                                }

                                                if (value != null) {

                                                    for (DocumentSnapshot snapshot : value.getDocuments()) {

                                                        Map<String, Object> data = snapshot.getData();

                                                        String playeridDocumentId = snapshot.getId();
                                                        String PlayerIdFirebase = (String) data.get("player_id");

                                                        StringBuilder mesaj = new StringBuilder();
                                                        mesaj.append(myUserName);
                                                        mesaj.append(" ");
                                                        mesaj.append(reZikirName);
                                                        mesaj.append(" ");
                                                        mesaj.append("zikretmeye davet etti.");


                                                        try {
                                                            OneSignal.postNotification(new JSONObject("{'contents': {'en':'" + mesaj.toString() + "'}, 'include_player_ids': ['" + PlayerIdFirebase + "']}"), null);
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }

                                                    }

                                                }
                                            }

                                        });

                                    }
                                });


                            }

                        });

                    }

                }

                HashMap<String, Object> usersData = new HashMap<>();

                usersData.put("email", auth.getCurrentUser().getEmail());
                usersData.put("inviteAnsver", "0");
                usersData.put("zikirCompleteCount", 0);
                usersData.put("nickName", myUserName);
                usersData.put("zikirCount", 0);
                usersData.put("zikirStatus", "1");

                firebaseFirestore.collection("ZikirMatik").document(auth.getCurrentUser().getEmail()).collection("myZikir").document(UID).collection("Users").document(auth.getCurrentUser().getEmail()).set(usersData);

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