package com.fatihkilic.muminappandroid.ZikirMatik;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.User.PickerListCountry;
import com.fatihkilic.muminappandroid.databinding.ActivityMyAccountEditBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityZikirAddBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.onesignal.OneSignal;

import java.util.Calendar;

public class ZikirAddActivity extends AppCompatActivity {


    private ActivityZikirAddBinding binding;

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    DatePicker endDatePicker;

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


        endDatePicker = binding.endDateDatePicker;
        endDatePicker.setVisibility(View.INVISIBLE);

        binding.zikirNameEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                binding.emptyBackground.setVisibility(View.VISIBLE);
                binding.manuelZikirBackground.setVisibility(View.INVISIBLE);
                binding.manuelzikirEditText.setVisibility(View.INVISIBLE);
                binding.manurlzikirButton.setVisibility(View.INVISIBLE);
                binding.zikirlerpickerList.setVisibility(View.VISIBLE);
                binding.numberPickerBackground.setVisibility(View.VISIBLE);
                binding.numberPickerOkButton.setVisibility(View.VISIBLE);
                binding.zikirNameEditText.setEnabled(false);

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


        binding.zikirBitisTarihiEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("datepickerr");

                Calendar nowTime = Calendar.getInstance();
                int nowYear = nowTime.get(Calendar.YEAR);
                int nowMonth = nowTime.get(Calendar.MONTH);
                int nowDay = nowTime.get(Calendar.DAY_OF_MONTH);


                binding.zikirBitisTarihiEditText.setEnabled(false);
                binding.emptyBackground.setVisibility(View.VISIBLE);
                endDatePicker.setVisibility(View.VISIBLE);



                endDatePicker.init(nowYear,nowMonth,nowDay,null);

                endDatePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int i, int i1, int i2) {

                    }
                });


            }
        });


        binding.zikriBaslatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String zikirName = binding.zikirNameEditText.getText().toString();





            }
        });



    }
}