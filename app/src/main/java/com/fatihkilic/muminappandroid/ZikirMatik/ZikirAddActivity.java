package com.fatihkilic.muminappandroid.ZikirMatik;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.User.PickerListCountry;
import com.fatihkilic.muminappandroid.databinding.ActivityMyAccountEditBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityZikirAddBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.onesignal.OneSignal;

public class ZikirAddActivity extends AppCompatActivity {


    private ActivityZikirAddBinding binding;

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

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



        PickerListZikirler.initCountryList();

        binding.zikirlerpickerList.setMaxValue(PickerListZikirler.getPickerListZikirlerArrayList().size()-1);
        binding.zikirlerpickerList.setMinValue(0);
        binding.zikirlerpickerList.setDisplayedValues(PickerListZikirler.zikirNames());

        binding.zikirlerpickerList.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                binding.zikirNameEditText.setText(PickerListZikirler.getPickerListZikirlerArrayList().get(newVal).getZikirler());

                String zikirNames = binding.zikirNameEditText.getText().toString();

                if (zikirNames.equals("Subhanallah")) {

                    binding.zikirDuasiEditText.setEnabled(false);

                    binding.zikirDuasiEditText.setText("Subhanallahi velhamdülillahi ve la ilaheillallahu vallahu ekber");

                } else if (zikirNames.equals("İstiğfar")) {

                    binding.zikirDuasiEditText.setEnabled(false);

                    binding.zikirDuasiEditText.setText("Estağfirullah. Estağfirullahe'l-azîm el-kerîm, ellezî lâ ilâhe illâ hüve'l-hayyü'l-kayyûmü ve etûbü ileyhi, tevbete abdin zâlimin li-nefsihî, lâ yemlikü li-nefsihî mevten velâ hayâten velâ nüşûrâ. Ve es-elühü't-tevbete ve'l-mağfirete ve'l-hidâyete lenâ, innehû, hüve't-tevvâbü'r-rahîm.” “Ya rabbi!");

                } else if (zikirNames.equals("Salatı Tefriciye")) {

                    binding.zikirDuasiEditText.setEnabled(false);

                    binding.zikirDuasiEditText.setText("Allahumme salli salâten kâmileten ve sellim selâmen tâmmen alâ-seyyidina Muhammedin ellezi tenhallü bihi'l 'ukadu ve tenfericu bihi'l-kürabu ve tukdâ bihi'l-havâicu ve tünâlü bihi'r-rağâibu ve husnu'l-havâtimi ve yusteska'l-ğamâmu bi-vechihi'l-kerîmi ve 'alâ âlihi ve sahbihi fî-külli lemhatin ve nefesin bi-'adedi külli ma'lûmin lek.");

                } else if (zikirNames.equals("Salavatı Şerif")) {

                    binding.zikirDuasiEditText.setEnabled(false);

                    binding.zikirDuasiEditText.setText("Allahümme salli alâ Muhammed'in ve alâ âli Muhammedin, kemâ salleyte alâ İbrahime ve alâ âli İbrahim, inneke hamîdun mecîd.");

                } else if (zikirNames.equals("Hasbunallah")) {

                    binding.zikirDuasiEditText.setEnabled(false);

                    binding.zikirDuasiEditText.setText("Hasbünallahü Ve Nimel Vekil");

                } else if (zikirNames.equals("Kelime-i Tevhid")) {

                    binding.zikirDuasiEditText.setEnabled(false);

                    binding.zikirDuasiEditText.setText("Lâ iâhe illallah Muhammedür-resûlüllah");

                } else if (zikirNames.equals("Ben Ekleyeceğim")) {

                    binding.zikirDuasiEditText.setEnabled(true);

                    String myZikirName = binding.zikirNameEditText.getText().toString();

                    binding.zikirDuasiEditText.setHint("Lütfen" + myZikirName + "izimli zikrin duasını giriniz.");

                }



            }
        });




    }
}